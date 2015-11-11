package gr.iti.mklab.verify;

import gr.iti.mklab.extractfeatures.ItemFeatures;
import gr.iti.mklab.extractfeatures.ItemFeaturesExtractorJSON;
import gr.iti.mklab.extractfeatures.UserFeatures;
import gr.iti.mklab.extractfeatures.UserFeaturesExtractorJSON;
import gr.iti.mklab.verifyutils.DataHandler;
import gr.iti.mklab.verifyutils.VerifHandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 * Main class that organizes the verification by using the agreement-based classification.
 * 1.Training sets are formed based on the text files provided by the program. Tweet features and User features are provided in two different files.
 * 2.Testing sets are formed based on the json files provided by the users.
 * 3.Cross validation on Tweet features data and User features data. Keep the scores.
 * 4.Train two bagging classifiers (one for each features; type) for classifying the test data.
 * 5.Compare the results of the above two classifiers; if agreed keep the result; if disagreed decide the result based on their confidence along with their CV scores.
 * @author Christina Boididou
 * updated 11.11.2015
 */
public class AgreementBasedClassification {

	static Instances[] trainDatasets = new Instances[2];
	static Instances[] testDatasets = new Instances[2];
	static Instances[] sets = new Instances[2];

	static Double itemScore, userScore;

	/**
	 * initialize the parameters
	 */
	public static void initializeParameters() {
		iter = 0;
		// Bagging.internal = 0;
	}

	/**
	 * Forms the training datasets from the arff files given containing the
	 * features
	 * @return Instances[] the training datasets for Item and User training
	 */
	public Instances[] getTrainDatasets() {

		Instances trainDatasets[] = new Instances[2];
		try {
			
			DataSource ds = new DataSource(prop.getProperty("TRAININGDATA_ITEM_FEATURES"));
			Instances temp = ds.getDataSet();			
			temp.setClassIndex(temp.numAttributes() - 1);
			trainDatasets[0] = ItemClassifier.reformatInstances(temp);
			
			
			DataSource ds1 = new DataSource(prop.getProperty("TRAININGDATA_USER_FEATURES"));
			Instances temp1 = ds1.getDataSet();	
			temp1.setClassIndex(temp1.numAttributes() - 1);
			trainDatasets[1] = UserClassifier.reformatInstances(temp1);

		} catch (Exception e) {
			System.out.println("Error! Cannot fetch datasets...");
			e.printStackTrace();
		}

		return trainDatasets;
	}

	public Instances[] getTestDatasets(ItemFeatures itemFeats, UserFeatures userFeats) {
			
		try {
			
			testDatasets[0] = ItemClassifier.formTestingSet(itemFeats);
			testDatasets[1] = UserClassifier.formTestingSet(userFeats);
		} catch (Exception e) {
			System.out.println("Error! Cannot fetch datasets...");
			e.printStackTrace();
		}
			
		return testDatasets;
	}

	static int iter = 0;

	/**
	 * Auxiliary recursive method to find the common ids among different set of
	 * Instances
	 * 
	 * @param currentSet
	 *            the set compared on every iteration
	 * @param ids
	 *            List<String> the common ids of the previous iteration
	 * @return List<String> the final list of the common ids
	 */
	public List<String> findCommon(Instances currentSet, List<String> ids) {

		iter++;
		// declare the list of common ids
		List<String> commonIds = new ArrayList<String>();

		// iterate through the set
		// compare with the previous list of ids
		for (Instance inst : currentSet) {
			if (ids.contains(inst.stringValue(0))) {
				commonIds.add(inst.stringValue(0));
			}
		}

		if (iter < testDatasets.length) {
			commonIds = findCommon(testDatasets[iter], commonIds);
		}

		return commonIds;
	}

	/**
	 * Finds the common items among the two testDatasets according to their id
	 * 
	 * @param datasets
	 *            the Item and User testing sets
	 * @return Instances[] the two sets containing the common items
	 */
	public Instances[] findCommonSets(Instances[] datasets) {

		// initialize the Item and User sets that will keep the common Item and
		// User Instances will be found below
		sets[0] = new Instances("Rel1", ItemClassifier.getFvAttributes(), datasets[0].size());
		sets[1] = new Instances("Rel2", UserClassifier.getFvAttributes(), datasets[1].size());

		sets[0].setClassIndex(ItemClassifier.getFvAttributes().size() - 1);
		sets[1].setClassIndex(UserClassifier.getFvAttributes().size() - 1);

		// save the ids of the datasets[0] set...
		List<String> ids = new ArrayList<String>();
		for (int i = 0; i < datasets[0].size(); i++) {
			ids.add(datasets[0].get(i).stringValue(0));
		}
		// ..and call the findCommon to find the common ids among the
		// datasets[0] and datasets[1]
		List<String> commonIds = findCommon(datasets[0], ids);

		// iterate through the commonIds list found exactly before
		// method that makes sure that the order of instances in the two sets
		// will be same
		for (int i = 0; i < commonIds.size(); i++) {
			// search for the instance with each id for the first test set
			for (int j = 0; j < datasets[0].size(); j++) {
				if (commonIds.get(i).equals(datasets[0].get(j).stringValue(0))) {
					sets[0].add(datasets[0].get(j));
				}
			}
			// search for the instance with each id for the second test set
			for (int j = 0; j < datasets[1].size(); j++) {
				if (commonIds.get(i).equals(datasets[1].get(j).stringValue(0))) {
					sets[1].add(datasets[1].get(j));
				}
			}
		}

		int countFake = 0, countReal = 0;
		// count the number of fake and real items the sets[0] contains. sets[1]
		// has the same number of fake and real as the sets[0].
		for (int i = 0; i < sets[0].size(); i++) {
			if (sets[0].classAttribute()
					.value((int) sets[0].get(i).classValue()).equals("fake")) {
				countFake++;
			} else {
				countReal++;
			}
		}

		return sets;
	}

	/**
	 * Function that returns a new instance of the current classifier being used
	 * 
	 * @return Classifier cls
	 */
	public Classifier getCurrentClassifier() {
		RandomForest cls = new RandomForest();
		return cls;
	}

	public static TweetVerificationResult getElemAnnotation(Instance inst1, VerificationResult verif1, VerificationResult verif2) {

		TweetVerificationResult tvr = new TweetVerificationResult();
		
		// set id of the element
		tvr.setId(inst1.stringValue(0));
		
		if (verif1.getPrediction().equals(verif2.getPrediction())) {
			tvr.setPredicted(verif1.getPrediction());
			tvr.setConfidenceValue((verif1.getProb() + verif2.getProb()) / 2);
			tvr.setAgreed(true);
		} else {
			
			if (itemScore*verif1.getProb() > userScore*verif2.getProb()) {
				tvr.setConfidenceValue(verif1.getProb());
			}
			else {
				tvr.setConfidenceValue(verif2.getProb());
			}
			tvr.setAgreed(false);
		}
		
		return tvr;
	}

	/**
	 * Classifies the set of items given a set of item and user classifiers
	 * 
	 * @param itemCls
	 *            Classifier[] from the bagging procedure for the Item case
	 * @param userCls
	 *            Classifier[] from the bagging procedure for the User case
	 * @return List<ElementAnnotation> list that contains the elements and their
	 *         annotation details after the classification process(classifiers
	 *         agreed, classifiers disagreed).
	 * @throws Exception
	 */
	public TweetVerificationResult classifyItems(Classifier[] itemCls, Classifier[] userCls) throws Exception {

		/*
		 * define the TweetVerificationResult that holds information about
		 * if the tweets classification
		 */
		
		TweetVerificationResult tvr = new TweetVerificationResult();
		
		int instaSize = sets[0].size();

		// applies bagging technique for the item and user case and gets the
		// predictions for the testing instances
		VerificationResult[] itemClsPreds = Bagging.classifyItems(itemCls, Bagging.getTestingSets());
		
		VerificationResult[] userClsPreds = Bagging.classifyItems(userCls, Bagging.getTestingSetsUser());

		Remove rm = new Remove();
		rm.setAttributeIndices("1");


		// iterate through the predictions
		for (int i = 0; i < itemClsPreds.length; i++) {

			for (int j = 0; j < userClsPreds.length; j++) {

				//compare ids to find the same item in both arrays
				if (itemClsPreds[i].getId().equals(userClsPreds[j].getId())) {

					Instance inst1 = sets[0].get(i);
					
					// find the details of the TweetVerificationResult object
					tvr = getElemAnnotation(inst1, itemClsPreds[i], userClsPreds[i]);

				}
			}
		}
		return tvr;
	}

	
		
	

	/**
	 * Function that computes the cross-validation score given a specific
	 * dataset Before the computation, applies normalization and linear
	 * regression to the missing values, as well as feature selection if the
	 * 'featSelect' is enabled.
	 * 
	 * @param train
	 *            Instances for which the score is computed
	 * @param pointer
	 *            int with value 0 or 1, depending on the case
	 * @return Double resulted score of the cross-validation
	 */
	public Double getCrossValidationScore(Instances train, int pointer) {

		Double score = 0D; // result

		Remove rm = new Remove(); // remove filter
		rm.setAttributeIndices("1");
		Instances trainNew;
		String currentCase = "ITEM";

				
		try {
			if (pointer == 0) {
				trainNew = DataHandler.getInstance().getTransformedTrainingOverall(train);
			} else {
				trainNew = DataHandler.getInstance().getTransformedTrainingUserOverall(train);
				currentCase = "USER";
			}

			
			Classifier tree = getCurrentClassifier();

			rm.setInputFormat(trainNew);
			Instances trainNew2 = Filter.useFilter(trainNew, rm);

			System.out.println("Cross validation of the training set with the "	+ currentCase + " features");

			score = VerifHandler.getInstance().crossValidate(tree, trainNew2);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return score;
	}
	
	/**
	 * Method that creates a merged JSONObject from the three objects given.
	 * @param itemFeats contains the Item(Tweet) features extracted
	 * @param userFeats contains the User features extracted
	 * @param ela TweetVerificationResult contains the details of the verification results
	 * @return JSONObject the result of the procedure
	 */
	public static JSONObject createResultJSONObject(ItemFeatures itemFeats, UserFeatures userFeats, TweetVerificationResult ela) {
		
		JSONObject resultedJSON = new JSONObject();
		
		resultedJSON = new JSONObject(ela.toJSONString());
		JSONObject jsonItemFeats = new JSONObject(itemFeats.toJSONString());
		JSONObject jsonUserFeats = new JSONObject(userFeats.toJSONString());

		resultedJSON.put("tweet_features", jsonItemFeats);
		resultedJSON.put("user_features", jsonUserFeats);
		
		return resultedJSON;
	}
	
	/**
	 * Organizes the verification procedure.
	 * *calls the functions to form the training and testing sets
	 * *calls the methods to compute the cross validation scores
	 * *calls the functions to compute the verification result for the tweets
	 * @return List<JSONObject> a list with the resulted JSON objects, one for each of the tweets given as input
	 */
	public static List<JSONObject> verifyTweets() {

		
		List<JSONObject> resultedJSONObjects = new ArrayList<JSONObject>();
		
		//get the properties that have been defined in the config.properties file
		defineProperties();
		
		// define a set of random values used to shuffle data during the trials.
		//Bagging.randomVals = Bagging.initializeRandomVals();
		
		// create a DoubleVerifyBagging object
		AgreementBasedClassification abr = new AgreementBasedClassification();

		/** TRAINING SET **/
		// get the training datasets (based on the Item(Tweet) and User features) 
		trainDatasets = abr.getTrainDatasets();
	
		//cross validate the training datasets to get the scores based on the item and user features respectively
		itemScore = abr.getCrossValidationScore(trainDatasets[0], 0);
		userScore = abr.getCrossValidationScore(trainDatasets[1], 1);

		/** TESTING SET **/
		//get the file that contains the testing set from the path defined in config file
		BufferedReader br = null;
		try {
			br = new BufferedReader (new FileReader(prop.getProperty("TESTINGDATA_JSON")) );
			
			String obj;
			while ( (obj = br.readLine()) != null ) {		
						
				JSONObject jsonObject = new JSONObject(obj);
				ItemFeatures itemFeats = ItemFeaturesExtractorJSON.extractFeatures(jsonObject);
				UserFeatures userFeats = UserFeaturesExtractorJSON.extractFeatures(jsonObject);
				
				// call method to create the testing sets with the lists given above
				testDatasets = abr.getTestDatasets(itemFeats, userFeats);

				// values initialization before each trial execution
				initializeParameters();
		
				// call method to find the common sets among the testing sets
				sets = abr.findCommonSets(testDatasets);
				/* * Even if we define one set of testing items, feature extraction
				 * may not be performed for some of them, i.e a user's account may
				 * be suspended, so there will be no user features for this one, but
				 * only item features. So, we aim to find those items that co-exist
				 * in the two sets and have both item and user features.*/
				 
				
				// define the set of classifiers for each case
				Classifier[] itemCls;
				Classifier[] userCls;
		
				try {
		
					Bagging bg = new Bagging();
		
					// call method to create the bagging classifiers with the
					// trainDatasets and sets for item and user case
					int trainingSize = trainDatasets[0].size() / 3;
					itemCls = bg.createClassifiers(trainDatasets[0], sets[0], trainingSize);
					trainingSize = trainDatasets[1].size() / 3;
					userCls = bg.createClassifiersUser(trainDatasets[1], sets[1], trainingSize);
		
					// classify testing sets using the classifiers created above
					TweetVerificationResult tvr = abr.classifyItems(itemCls, userCls);
					
					JSONObject resultedJSONObject = createResultJSONObject(itemFeats, userFeats, tvr);
					
					resultedJSONObjects.add(resultedJSONObject);
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultedJSONObjects;

	}

	static public Properties prop = new Properties();

	public static void defineProperties() {

		InputStream input = null;

		try {
			input = new FileInputStream("conf/config.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Main method that triggers the verification process by calling the verifyTweets() method
	 * @param args
	 * @throws Exception
	 */
	/*START FROM HERE*/
	public static void main(String[] args) throws Exception {

		List<JSONObject> resultedJSONObjects = verifyTweets();
		
		for (JSONObject json:resultedJSONObjects) {
			System.out.println("Tweet ID: "+json.getString("id"));
			System.out.println("Resulted json: "+ json);
		}

	}

}
