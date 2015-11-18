package gr.iti.mklab.verify;

import gr.iti.mklab.extractfeatures.ItemFeatures;
import gr.iti.mklab.extractfeatures.ItemFeaturesExtractorJSON;
import gr.iti.mklab.extractfeatures.UserFeatures;
import gr.iti.mklab.extractfeatures.UserFeaturesExtractorJSON;
import gr.iti.mklab.verifyutils.DataHandler;
import gr.iti.mklab.verifyutils.VerifHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.json.JSONObject;

import weka.classifiers.Classifier;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;
import weka.filters.unsupervised.attribute.Remove;

/**
 * Main class that organizes the verification by using the agreement-based classification.
 * 1.Call the initialization function initializeParameters()
 * 2.Call the verifyTweet() method to classify each tweet
 * @author Christina Boididou
 * updated 11.11.2015
 */
public class AgreementBasedClassification {

	//static final long serialVersionUID = 1116839470751428698L;
	
	//static Instances[] trainDatasets = new Instances[2];
	static Instances[] testDatasets = new Instances[2];

	static Double itemScore, userScore;

	public static Classifier[] classifiersTweet = new Classifier[Bagging.randomVals.length];
	public static Classifier[] classifiersUser = new Classifier[Bagging.randomVals.length];
	
	public static Classifier[][] lr_models_tweet = new Classifier[Bagging.randomVals.length][13];
	public static Classifier[][] lr_models_user = new Classifier[Bagging.randomVals.length][8];

	public static Normalize[] norm_models_tweet = new Normalize[Bagging.randomVals.length];
	public static Normalize[] norm_models_user  = new Normalize[Bagging.randomVals.length];
	
	/**
	 * initialize the parameters
	 * @throws Exception 
	 */
	public static void initializeParameters() throws Exception {
		
		System.out.println("Reading properties file...");
		//get the properties that have been defined in the config.properties file
		defineProperties();
		
		System.out.println("Initializing the values...");
		//initialize CV values
		itemScore = Double.valueOf(prop.getProperty("ITEM_SCORE"));
		userScore = Double.valueOf(prop.getProperty("USER_SCORE"));
		
		Bagging.randomVals = Bagging.initializeRandomVals();
		ItemClassifier.declareAttributes();
		UserClassifier.declareAttributes();
		
		
		System.out.println("Initializing files...");
		UserFeaturesExtractorJSON.initializeFiles();
		ItemFeaturesExtractorJSON.initializeFiles();
		
		System.out.println("Reading the training data...");
		/** TRAINING SET **/
		// get the training datasets (based on the Item(Tweet) and User features) 
		//trainDatasets = AgreementBasedClassification.getTrainDatasets();
		
				
		System.out.println("Loading the models...");
		try {
		//load the models
			int size = classifiersTweet.length;
			for (int j=0; j<size; j++) {
				
				Classifier cls = (Classifier) weka.core.SerializationHelper.read(prop.getProperty("TWEET_MODELS_FOLDER")+"tweetModel"+j+".model");
				classifiersTweet[j] = cls;
				
				Classifier cls2 = (Classifier) weka.core.SerializationHelper.read(prop.getProperty("USER_MODELS_FOLDER")+"userModel"+j+".model");
				classifiersUser[j]  = cls2;
			}
			int size2 = Bagging.randomVals.length;
			
			for (int i=0; i<size2;i++) {

				for (int j=0; j<13; j++) {
					FilteredClassifier cls = (FilteredClassifier) weka.core.SerializationHelper.read(prop.getProperty("TWEET_LR_MODELS_FOLDER")+"lr_model_"+i+"_"+j+".model");
					lr_models_tweet[i][j] = cls;
				}
				for (int j=0; j<8; j++) {
					FilteredClassifier cls = (FilteredClassifier) weka.core.SerializationHelper.read(prop.getProperty("TWEET_LR_MODELS_FOLDER")+"lr_user_model_"+i+"_"+j+".model");
					lr_models_user[i][j] = cls;
				}
				
				Normalize cls = (Normalize) weka.core.SerializationHelper.read(prop.getProperty("TWEET_NORM_MODELS_FOLDER")+"norm_model_"+i+".model");
				norm_models_tweet[i] = cls;
				
				Normalize cls2 = (Normalize) weka.core.SerializationHelper.read(prop.getProperty("USER_NORM_MODELS_FOLDER")+"norm_user_model_"+i+".model");
				norm_models_user[i] = cls2;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Forms the training datasets from the arff files given containing the
	 * features
	 * @return Instances[] the training datasets for Item and User training
	 */
	public static Instances[] getTrainDatasets() {

		Instances trainDatasets[] = new Instances[2];
		try {
			
			DataSource ds = new DataSource(prop.getProperty("TRAININGDATA_ITEM_FEATURES"));
			Instances temp = ds.getDataSet();			
			temp.setClassIndex(temp.numAttributes() - 1);
			trainDatasets[0] = ItemClassifier.reformatInstances(temp);
			trainDatasets[0].setClassIndex(trainDatasets[0].numAttributes() - 1);
			
			DataSource ds1 = new DataSource(prop.getProperty("TRAININGDATA_USER_FEATURES"));
			Instances temp1 = ds1.getDataSet();	
			temp1.setClassIndex(temp1.numAttributes() - 1);
			trainDatasets[1] = UserClassifier.reformatInstances(temp1);
			trainDatasets[1].setClassIndex(trainDatasets[1].numAttributes() - 1);

		} catch (Exception e) {
			System.out.println("Error! Cannot fetch datasets...");
			e.printStackTrace();
		}

		return trainDatasets;
	}

	public Instances[] getTestDatasets(ItemFeatures itemFeats, UserFeatures userFeats) {
			
		try {
			
			testDatasets[0] = ItemClassifier.createTestingSet(itemFeats);
			testDatasets[1] = UserClassifier.createTestingSet(userFeats);
		} catch (Exception e) {
			System.out.println("Error! Cannot fetch datasets...");
			e.printStackTrace();
		}
			
		return testDatasets;
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

	public static TweetVerificationResult getElemAnnotation(VerificationResult verif1, VerificationResult verif2) {

		TweetVerificationResult tvr = new TweetVerificationResult();
		
		if (verif1.getPrediction().equals(verif2.getPrediction())) {
			tvr.setPredicted(verif1.getPrediction());
			tvr.setConfidenceValue((verif1.getProb() + verif2.getProb()) / 2);
			tvr.setAgreed(true);
		} else {
			
			if (itemScore*verif1.getProb() > userScore*verif2.getProb()) {
				tvr.setConfidenceValue(verif1.getProb());
				tvr.setPredicted(verif1.getPrediction());
			}
			else {
				tvr.setConfidenceValue(verif2.getProb());
				tvr.setPredicted(verif2.getPrediction());
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
	public TweetVerificationResult classifyItem(Classifier[] itemCls, Classifier[] userCls) throws Exception {

		/*
		 * define the TweetVerificationResult that holds information about
		 * if the tweets classification
		 */
		
		TweetVerificationResult tvr = new TweetVerificationResult();
		
		// applies bagging technique for the item and user case and gets the
		// predictions for the testing instances
		VerificationResult tweetPred = Bagging.classifyItems(itemCls, Bagging.getTestingSets());
		VerificationResult userPred  = Bagging.classifyItems(userCls, Bagging.getTestingSetsUser());
		
		String id = testDatasets[0].get(0).stringValue(0);
		
		// find the details of the TweetVerificationResult object
		tvr = getElemAnnotation(tweetPred, userPred);
		tvr.setId(id);
		
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
	public static JSONObject verifyTweet(JSONObject jsonObject) {

		
		JSONObject resultedJSONObject =  new JSONObject();
		
		/** TESTING SET **/
		//get the file that contains the testing set from the path defined in config file
								
		ItemFeatures itemFeats;
		UserFeatures userFeats;
		
		try {
			itemFeats = ItemFeaturesExtractorJSON.extractFeatures(jsonObject);
			userFeats = UserFeaturesExtractorJSON.extractFeatures(jsonObject);
			
			AgreementBasedClassification abc = new AgreementBasedClassification();
			
			// call method to create the testing sets with the lists given above
			testDatasets = abc.getTestDatasets(itemFeats, userFeats);
			//sets = abc.findCommonSets(testDatasets);
			

			Bagging bg = new Bagging();

			// call method to create the bagging classifiers with the
			// trainDatasets and sets for item and user case
			
			bg.generateRespectiveTestingSetsTweet(testDatasets[0]);
			
			bg.generateRespectiveTestingSetsUser(testDatasets[1]);

			// classify testing sets using the classifiers created above
			TweetVerificationResult tvr = abc.classifyItem(classifiersTweet, classifiersUser);

			resultedJSONObject = createResultJSONObject(itemFeats, userFeats, tvr);
					
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return resultedJSONObject;

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
	 * Main method
	 * @param args
	 * @throws Exception
	 */
	/*START FROM HERE*/
	public static void main(String[] args) throws Exception {

		/**STEP 1: call the initialization function**/
		initializeParameters();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("results.json",true));
		BufferedReader br = null;
		//get a JSON from a file or from another location 
		br = new BufferedReader (new FileReader( prop.getProperty("TESTINGDATA_JSON")));
		//convert to JSONObject
		String line;
		
		while ((line=br.readLine())!=null) {
			
			JSONObject json = new JSONObject(line);
			
			/**STEP 2: call the verifyTweet(json) function**/
			JSONObject resultedJson = verifyTweet(json);
			bw.write(resultedJson.toString());
			bw.newLine();
			bw.flush();
			System.out.println("Resulted JSON: ");
			System.out.println(resultedJson);
			System.out.println("===");
			
		}
		
		bw.close();
		
		br.close();
	}

}
