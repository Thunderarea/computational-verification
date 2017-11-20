package gr.iti.mklab.verify;


import gr.iti.mklab.extractfeatures.TweetFeatures;
import gr.iti.mklab.extractfeatures.UserFeatures;
import gr.iti.mklab.util.FileManager;
import gr.iti.mklab.verifyutils.DataHandler;
import gr.iti.mklab.verifyutils.VerifHandler;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.stream.Stream;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import weka.classifiers.Classifier;
import weka.classifiers.functions.Logistic;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.MultiFilter;
import weka.filters.unsupervised.attribute.Remove;


public class DoubleVerifyBagging {
	
	private Instances[] trainDatasets = new Instances[2];
	private Instances[] testDatasets = new Instances[2];
	private Instances[] sets = new Instances[2];
	private int iter;
	private Double itemScore;
	private Double userScore;
	private int datasetPointer;
	private ArrayList<int[]> randomValues = new ArrayList<int[]>();
	List<String> tweetRegrClass = new LinkedList<String>();
	List<String> userRegrClass = new LinkedList<String>();
	List<String> concatRegrClass = new LinkedList<String>();
	private int[] currentRandomValues = new int[9];
	public static int expNo;
	public static String experimentId;
	private List<String> testIDS;
	public static String outputFolder;
	public static String outputFolderPerRun;
	public static String method;
	public static boolean verbose;
	public static boolean runConcatenated;
	public static String classifier;

	private static final Logger LOGGER = Logger.getLogger("gr.iti.mklab.verify.DoubleVerifyBagging");

	public DoubleVerifyBagging() {
		initializeParameters();
	}

	public void initializeParameters() {
	
		setRandomValues(initializeRandomVals());
		setTweetRegressionClasses(tweetRegressionClasses());
		setUserRegressionClasses(userRegressionClasses());
		/**
		 * Select method
		 * 	     1 -- Classify disagreed on agreed without bagging
    	         2 -- Classify disagreed on agreed with bagging
    	         3 -- Classify disagreed on Updated existing model (initial training set + agreed) with bagging
    	         4 -- Classify disagreed on Updated existing model (initial training set + agreed) without bagging 
    	         5 -- All above 
		 */
		setMethod("5");  
		/**
		 * Value true for detailed results
		 */
		setVerbose(true);
		setRunConcatenated(false);
		if (getRunConcatenated()){
			setConcatRegressionClasses(concatRegressionClasses());
		}

		/**
		 * Define classifier
		 * RF: Random Forest
		 * LG: Logistic regression
		 */
		setClassifier("LG");
		
	}

	
	
	public static boolean getRunConcatenated() {
		return runConcatenated;
	}

	public static void setRunConcatenated(boolean runConcatenated) {
		DoubleVerifyBagging.runConcatenated = runConcatenated;
	}

	public Instances[] getSets() {
		return sets;
	}
	
	public void setSets(Instances[] sets) {
		this.sets = sets;
	}
	
	public void setTrainDatasets(Instances[] trainDatasets) {
		this.trainDatasets = trainDatasets;
	}

	public void setTestDatasets(Instances[] testDatasets) {
		this.testDatasets = testDatasets;
	}
	
	public Instances[] getTestDatasets() {
		return testDatasets;
	}

	public Instances[] getTrainDatasets() {
		return trainDatasets;
	}
	
	public void setFinalTestIDs(List<String> testIDS) {
		this.testIDS = testIDS;
	}

	public List<String> getFinalTestIDs() {
		return testIDS;
	}	
	
	

	public static String getClassifier() {
		return classifier;
	}

	public static void setClassifier(String classifier) {
		DoubleVerifyBagging.classifier = classifier;
	}
	
	public Classifier getCurrentClassifier(String classifierName){
		if (classifierName.equalsIgnoreCase("RF")){
			System.out.println("Random Forest classifier");
			return new RandomForest();
		}else if (classifierName.equalsIgnoreCase("LG")){
			System.out.println("Logistic classifier");
			return new Logistic();
		}else{
			return new RandomForest();
		}
	}

	public Double getItemScore() {
		return itemScore;
	}

	public void setItemScore(Double itemScore) {
		this.itemScore = itemScore;
	}

	public Double getUserScore() {
		return userScore;
	}

	public void setUserScore(Double userScore) {
		this.userScore = userScore;
	}	

	public int getDatasetPointer() {
		return datasetPointer;
	}

	public void setDatasetPointer(int datasetPointer) {
		this.datasetPointer = datasetPointer;
	}
	
	public ArrayList<int[]> getRandomValues() {
		return randomValues;
	}

	public void setRandomValues(ArrayList<int[]> randomValues) {
		this.randomValues = randomValues;
	}
	
	public List<String> getTweetRegressionClasses() {
		return tweetRegrClass;
	}

	public void setTweetRegressionClasses(List<String> tweetRegrClass) {
		this.tweetRegrClass = tweetRegrClass;
	}
	
	public List<String> getUserRegressionClasses() {
		return userRegrClass;
	}

	public void setUserRegressionClasses(List<String> userRegrClass) {
		this.userRegrClass = userRegrClass;
	}
	
	public List<String> getConcatRegressionClasses() {
		return concatRegrClass;
	}

	public void setConcatRegressionClasses(List<String> concatRegrClass) {
		this.concatRegrClass = concatRegrClass;
	}
	
	public int[] getCurrentRandomValues() {
		return currentRandomValues;
	}

	public void setCurrentRandomValues(int[] currentRandomValues) {
		this.currentRandomValues = currentRandomValues;
	}
	
	public static int getExpNo() {
		return expNo;
	}

	public static void setExpNo(int expNo) {
		DoubleVerifyBagging.expNo = expNo;
	}
	

	public static String getMethod(){
		return method;
	}
	public static void setMethod(String method){
		DoubleVerifyBagging.method = method;
	}
	
	public static String getExperimentId() {
		return experimentId;
	}

	public static void setExperimentId(String experimentId) {
		DoubleVerifyBagging.experimentId = experimentId;
	}
	
	public static String getOutputFolder() {
		return outputFolder;
	}

	public static void setOutputFolder(String outputFolder) {
		DoubleVerifyBagging.outputFolder = outputFolder;
	}
	
	public static String getOutputFolderPerRun() {
		return outputFolderPerRun;
	}

	public static void setOutputFolderPerRun(String outputFolderPerRun) {
		DoubleVerifyBagging.outputFolderPerRun = outputFolderPerRun;
	}
	
	public static boolean getVerbose() {
		return verbose;
	}

	public static void setVerbose(boolean verbose) {
		DoubleVerifyBagging.verbose = verbose;
	}
	
	public static ArrayList<int[]> initializeRandomVals() {

		ArrayList<int[]> random = new ArrayList<int[]>();
	
		//random.add(new int[] { 6, 7, 8, 90, 32, 69, 777, 188, 149 });
		//random.add(new int[] { 56, 38, 58, 42, 59, 65, 71, 18, 19 });
		//random.add(new int[] { 33, 46, 11, 88, 99, 27, 35, 29, 20 });
		
		//random.add(new int[] { 32, 45, 90, 589, 12, 1, 77, 18, 19 });
		random.add(new int[] { 32, 45, 901, 589, 12, 1, 77, 18, 19 });
		
		random.add(new int[] { 561, 328, 580, 422, 31, 79, 741, 1856, 123 });
		random.add(new int[] { 331, 146, 171, 238, 919, 271, 315, 219, 920 });
				
		random.add(new int[] { 10, 55, 13, 81, 199, 127, 235, 329, 420 });
		random.add(new int[] { 28, 29, 30, 31, 39, 327, 335, 429, 520 });
		
		random.add(new int[] { 1, 2, 3, 4, 55, 55, 6, 77, 66 });
		//random.add(new int[] { 74, 31, 66, 92, 101, 26, 94, 11, 444 });
		
		random.add(new int[] { 51, 62, 333, 334, 955, 255, 36, 877, 626 });
		random.add(new int[] { 27, 227, 123, 234, 135, 147, 336, 890, 891 });
		random.add(new int[] { 9, 10, 11, 22, 783, 472, 90, 91, 99 });
		
		
		random.add(new int[] { 761, 634, 697, 878, 819, 9108, 8697, 2345, 152 });
		
	/*	random.add(new int[] { 32, 45, 901, 589, 12, 1, 77, 18, 19 });
		random.add(new int[] { 32, 45, 90, 589, 12, 1, 77, 18, 19 });
		
		random.add(new int[] { 561, 328, 580, 422, 31, 79, 741, 1856, 123 });
		//random.add(new int[] { 331, 146, 171, 238, 919, 271, 315, 219, 920 });
				
		random.add(new int[] { 10, 55, 13, 81, 199, 127, 235, 329, 420 });
		random.add(new int[] { 28, 29, 30, 31, 39, 327, 335, 429, 520 });
		
		random.add(new int[] { 1, 2, 3, 4, 55, 55, 6, 77, 66 });
		random.add(new int[] { 51, 62, 333, 334, 955, 255, 36, 877, 626 });
		random.add(new int[] { 27, 227, 123, 234, 135, 147, 336, 890, 891 });
		random.add(new int[] { 9, 10, 11, 22, 783, 472, 90, 91, 99 });	
		random.add(new int[] { 761, 634, 697, 878, 819, 9108, 8697, 2345, 152 });*/

		return random;
	}
	
	
	public static List<String> tweetRegressionClasses(){
		
		List<String> regressionClass = new LinkedList<String>();
		
		regressionClass.add("wot_trust");
		regressionClass.add("readability");
		regressionClass.add("alexa_popularity");
		regressionClass.add("alexa_delta_rank");
		regressionClass.add("num_neg_sentiment_words");
		regressionClass.add("num_slangs");
		regressionClass.add("indegree");
		regressionClass.add("alexa_country_rank");
		regressionClass.add("harmonic");
		regressionClass.add("num_pos_sentiment_words");
		regressionClass.add("alexa_reach_rank");
		regressionClass.add("num_nouns");
		regressionClass.add("contains_first_order_pron");
		regressionClass.add("contains_second_order_pron");
		regressionClass.add("contains_third_order_pron");
	

			return regressionClass;	
	}	  
	
	public static List<String> userRegressionClasses(){
		
		List<String> regressionClass = new LinkedList<String>();
			
			regressionClass.add("wotTrustUser");
			regressionClass.add("accountAge");
			regressionClass.add("tweetRatio");
			regressionClass.add("indegreeUser");
			regressionClass.add("harmonicUser");
			regressionClass.add("alexaCountryRankUser");
			regressionClass.add("alexaDeltaRankUser");
			regressionClass.add("alexaPopularityUser");
			regressionClass.add("alexaReachRankUser");	

			return regressionClass;	
	}	
	
public static List<String> concatRegressionClasses(){
		
		List<String> regressionClass = new LinkedList<String>();
		
		regressionClass.add("wot_trust");
		regressionClass.add("readability");
		regressionClass.add("alexa_popularity");
		regressionClass.add("alexa_delta_rank");
		regressionClass.add("num_neg_sentiment_words");
		regressionClass.add("num_slangs");
		regressionClass.add("indegree");
		regressionClass.add("alexa_country_rank");
		regressionClass.add("harmonic");
		regressionClass.add("num_pos_sentiment_words");
		regressionClass.add("alexa_reach_rank");
		regressionClass.add("num_nouns");
		regressionClass.add("contains_first_order_pron");
		regressionClass.add("contains_second_order_pron");
		regressionClass.add("contains_third_order_pron");
		regressionClass.add("wotTrustUser");
		regressionClass.add("accountAge");
		regressionClass.add("tweetRatio");
		regressionClass.add("indegreeUser");
		regressionClass.add("harmonicUser");
		regressionClass.add("alexaCountryRankUser");
		regressionClass.add("alexaDeltaRankUser");
		regressionClass.add("alexaPopularityUser");
		regressionClass.add("alexaReachRankUser");	
	

			return regressionClass;	
	}	  

	public static Instances loadTweetFeature(List<TweetFeatures> tweetFeatsList, List<FeaturesAnnotationItem> annotation) throws Exception{
		Instances features = null;	
		
		TweetClassifier ic = new TweetClassifier();
		features = ic.createTrainingSet(tweetFeatsList, annotation);

		return features;
	}
	
	public static Instances loadUserFeature(List<UserFeatures> userFeatsList, List<FeaturesAnnotationItem> annotation) throws Exception{
		Instances features = null;	
		
		UserClassifier uc = new UserClassifier();
		features = uc.createTrainingSet(userFeatsList, annotation);
		return features;
	}
	
	public static Instances loadConcatFeature(List<TweetFeatures> tweetFeatsList, List<UserFeatures> userFeatsList, List<FeaturesAnnotationItem> annotation) throws Exception{
		Instances features = null;	
		
		ConcatClassifier uc = new ConcatClassifier();
		features = uc.createTrainingSet(tweetFeatsList, userFeatsList, annotation);
		return features;
	}
	
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

		if (iter < getTestDatasets().length) {
			commonIds = findCommon(getTestDatasets()[iter], commonIds);
		}

		return commonIds;
	}

	public void findCommonSets(Instances[] datasets) {

		LOGGER.info("Sizes " + datasets[0].size() + " " + datasets[1].size());

		Instances set1 = new Instances(datasets[0],0);
		Instances set2 = new Instances(datasets[1],0);
		
		set1.setClassIndex(datasets[0].classIndex());
		set2.setClassIndex(datasets[1].classIndex());

		List<String> ids = new ArrayList<String>();
		for (int i = 0; i < datasets[0].size(); i++) {
			ids.add(datasets[0].get(i).stringValue(0));
		}

		List<String> commonIds = findCommon(datasets[0], ids);
		LOGGER.info("common ids size "+commonIds.size());
		
		// iterate through the commonIds list found exactly before
		// method that makes sure that the order of instances in the two sets
		// will be same
		for (int i = 0; i < commonIds.size(); i++) {
			// search for the instance with each id for the first test set
			for (int j = 0; j < datasets[0].size(); j++) {
				if (commonIds.get(i).equals(datasets[0].get(j).stringValue(0))) {
					set1.add(datasets[0].get(j));
				}
			}
			// search for the instance with each id for the second test set
			for (int j = 0; j < datasets[1].size(); j++) {
				if (commonIds.get(i).equals(datasets[1].get(j).stringValue(0))) {
					set2.add(datasets[1].get(j));
				}
			}
		}

		int countFake = 0, countReal = 0;

		for (int i = 0; i < set1.size(); i++) {
			if (set1.classAttribute().value((int) set2.get(i).classValue()).equals("fake")) {
				countFake++;
			} else {
				countReal++;
			}
		}

		LOGGER.info("Common items found " + commonIds.size() + ". Fake "	+ countFake + ", real " + countReal);
		LOGGER.info("sets " + set1.size() + " " +set2.size());

		setSets(new Instances[] {set1,set2});
		
		
		//return sets;
	}
	
	

	
	

	/**
	 * Function that computes the cross-validation score given a specific
	 * dataset Before the computation, applies normalization and linear
	 * regression to the missing values, as well as feature selection if the
	 * 'featSelect' is enabled.
	 * 
	 * @param train
	 *            Instances for which the score is computed
	 * @param datasetId
	 *            int with value 0 or 1, depending on the case
	 * @return Double resulted score of the cross-validation
	 */
	public Double getScore(Instances train, int datasetId) {

		Double score = 0D; // result
		
		Remove rm = new Remove(); // remove filter
		rm.setAttributeIndices("1");
		Instances trainNew = new Instances(train);
		String currentCase = "TWEET";

		
		LOGGER.info("train here "+train.size());
		
		try {
			if (datasetId == 0) {
				
				for (int i = 0; i < getTweetRegressionClasses().size(); i++){
						trainNew = DataHandler.getInstance().getTransformedTrainingTweet(trainNew, getTweetRegressionClasses().get(i));				
				}
				trainNew =  DataHandler.getInstance().normalizationTweet(trainNew);
			} else {
				for (int i = 0; i < getUserRegressionClasses().size(); i++){
					trainNew = DataHandler.getInstance().getTransformedTrainingUser(trainNew, getUserRegressionClasses().get(i));					
				}
				trainNew =  DataHandler.getInstance().normalizationUser(trainNew);
				currentCase = "USER";
			}

			
			Classifier tree = getCurrentClassifier(getClassifier());

			rm.setInputFormat(trainNew);
			Instances trainNew2 = Filter.useFilter(trainNew, rm);
			int countF=0, countR=0;
			for (int i=0; i<trainNew2.size(); i++) {
				if (trainNew2.get(i).stringValue(trainNew2.numAttributes()-1).equals("fake")) {
					countF++;
				}else {
					countR++;
				}				
			}
			
			LOGGER.info("FOUND "+countF+" "+countR);
			LOGGER.info("Cross validation of the training set with the "	+ currentCase + " features");
			score = VerifHandler.getInstance().crossValidate(tree, trainNew2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return score;
	}
	
	/**
	 * Method that classifies the testing instances by creating a model with the training instances.
	 * Note that the method uses equal number of instances from the two classes.
	 * @param training2 Instances variable containing the training set
	 * @param testing Instances variable containing the testing set
	 * @param pointer int number, equals to 0 or 1 when using the Item or the User features respectively
	 * @throws IOException 
	 * @throws Exception
	 */
	public void classifyWithRandomTrainInstance(Instances training2, Instances testing, int pointer, List<String> regressionClasses) throws IOException{

		int counter2 = 0, counterFake = 0, counterReal = 0;
		Instances training = new Instances(training2);

		
		
		// shuffle with a random number equal to the randomVals[3] value
		Collections.shuffle(training, new Random(getCurrentRandomValues()[3]));
		
		// count the fake and real samples
		for (int i = 0; i < training.size(); i++) {
			if (training.classAttribute()
					.value((int) training.instance(i).classValue())
					.equals("fake")) {
				counterFake++;
			} else
				counterReal++;
		}

		// grab the min value of the fake and real samples
		int min = Math.min(counterFake, counterReal);
		System.out.println(counterFake + " " + counterReal);
		System.out.println("min " + min);

		Instances trainingInst = null;
		// Item or User case	
		trainingInst = new Instances(training,0);
		trainingInst.setClassIndex(training.numAttributes() - 1);	

		counterFake = 0;
		counterReal = 0;

		for (int i = 0; i < training.size(); i++) {
			if (counterFake < min) {
				if (training.classAttribute()
						.value((int) training.get(i).classValue())
						.equals("fake")) {
					counterFake++;
					trainingInst.add(training.get(i));

				}
			}
		}
		for (int i = 0; i < training.size(); i++) {
			if (counterReal < min) {
				if (training.classAttribute()
						.value((int) training.get(i).classValue())
						.equals("real")) {
					trainingInst.add(training.get(i));
					counterReal++;

				}
			}
		}
		
		LOGGER.info("New counterReal " + counterReal + " -- counterFake " + counterFake);

		String currentCase = "TWEET";
		try {
		if (pointer == 0) {
			
			for (int i = 0; i < getTweetRegressionClasses().size(); i++){
				trainingInst = DataHandler.getInstance().getTransformedTrainingTweet(trainingInst, getTweetRegressionClasses().get(i));	
				testing = DataHandler.getInstance().getTransformedTestingTweet(testing, getTweetRegressionClasses().get(i));
			}
			trainingInst =  DataHandler.getInstance().normalizationTweet(trainingInst);
			testing =  DataHandler.getInstance().normalizationTestingTweet(testing);

		} else {
			for (int i = 0; i < getUserRegressionClasses().size(); i++){
				trainingInst = DataHandler.getInstance().getTransformedTrainingUser(trainingInst, getUserRegressionClasses().get(i));
				testing = DataHandler.getInstance().getTransformedTestingUser(testing, getUserRegressionClasses().get(i));
			}
			
			trainingInst =  DataHandler.getInstance().normalizationUser(trainingInst);
			testing =  DataHandler.getInstance().normalizationTestingUser(testing);
			currentCase = "USER";
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// classifier details
		FilteredClassifier fc = new FilteredClassifier();
		Classifier tree = getCurrentClassifier(getClassifier());
		Remove rm = new Remove();
		rm.setAttributeIndices("1");
				
		MultiFilter mf = new MultiFilter();
		Filter[] filters = { rm };
		mf.setFilters(filters);
		

		try {
			// set the parameters and build the classifier
			fc.setFilter(mf);
			fc.setClassifier(tree);
			fc.buildClassifier(trainingInst);
			//System.out.println(fc);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		counter2 = 0;
		counterFake = 0;
		counterReal = 0;
		int fakes = 0, reals = 0;
		try {
			
			for (int i = 0; i < testing.size(); i++) {
	
				double pred = fc.classifyInstance(testing.instance(i));
				
				String actual = testing.classAttribute().value((int) testing.instance(i).classValue());
		
				String predicted = testing.instance(i).classAttribute().value((int) pred);		
	
				if (actual.equals(predicted)) {
					counter2++;
					
					if (actual.equals("fake")) counterFake++;
					else	counterReal++;
				}
				
				if (actual.equals("fake")) fakes++;
				else	reals++;
								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
		LOGGER.info("BASED ON THE RANDOM TRAIN " + currentCase + " INSTANCE:");		
		LOGGER.info("Fake items predicted right " + counterFake + " out of " + fakes + " " + ((double)counterFake/fakes)*100 );
		LOGGER.info("Real items predicted right " + counterReal + " out of " + reals + " " + ((double)counterReal/reals)*100 );
		LOGGER.info("Accuracy " + (double) counter2 / testing.size()	* 100);
		System.out.println();	
	}
	
	
	
	
	/**
	 * 
	 * @param training
	 * @return
	 */
	public int determineTrainingSize (Instances training) {
		
		int trainingSize = 0;
		int size = training.size();
		LOGGER.info("Determine: "+ size);
		
		//count fake and real samples in the given training data
		int fakes=0, reals=0;
		for (int i=0;i<size;i++) {
			String actual = training.classAttribute().value((int) training.instance(i).classValue());
			if (actual.equals("fake"))	fakes++;
			else reals++;
		}
		
		/*if (fakes<reals) {
			trainingSize = 2*fakes;
		}
		else {
			trainingSize = 2*reals;
		}*/
		
		if (size < 150) {
			trainingSize = size;
		} else if (size < 250) {
			trainingSize = size / 2;
		} else {
			trainingSize = size / 3;
		}
		
		LOGGER.info("Fakes: "+fakes+ ", reals: "+reals);
	
		
		if (fakes==0)	trainingSize = 2*reals;
		if (reals==0)	trainingSize = 2*fakes;
		
		LOGGER.info("Decided: " + trainingSize);
		
		return trainingSize;
	}
	
	public ElementAnnotation getElemAnnotation(Instance inst1, String predicted1, String predicted2, boolean decided) {

		ElementAnnotation ela = new ElementAnnotation();
		// set id of the element
		ela.setId(inst1.stringValue(0));
		// set its actual value
		String actual = getSets()[0].classAttribute().value((int) inst1.classValue());
		ela.setActual(actual);
		// set its predicted value (only for the agreed elements)
		// set the agreed value of the element
		if (predicted1.equals(predicted2)) {
			ela.setPredicted(predicted1);
			ela.setAgreed(true);
		} else {
			ela.setAgreed(false);
		}
		return ela;
	}
	
	public List<ElementAnnotation> classifyItems(VerificationResult[] itemClsPreds,	VerificationResult[] userClsPreds) throws Exception {

		List<ElementAnnotation> listEla = new ArrayList<ElementAnnotation>();

		int instaSize = getSets()[0].size();

		for (int i = 0; i < itemClsPreds.length; i++) {

			for (int j = 0; j < userClsPreds.length; j++) {

				if (itemClsPreds[i].getId().equals(userClsPreds[j].getId())) {

					Instance inst1 = getSets()[0].get(i);

					String predicted1 = itemClsPreds[i].getPrediction();
					String predicted2 = userClsPreds[i].getPrediction();
					// System.out.println(predicted1+" " +predicted2);

					boolean decided = itemClsPreds[i].isDecided() && userClsPreds[i].isDecided();
					
					// find the details of the ElementAnnotation object
					ElementAnnotation ela = getElemAnnotation(inst1, predicted1, predicted2, decided);

					// add the element to the list
					listEla.add(ela);
				}
			}
		}

		int counter = 0, counterRightPred = 0;
		int counterFake = 0, counterReal = 0, counterFakeDis = 0, counterRealDis = 0, counterRightFake = 0, counterRightReal = 0;

		HashMap<String, String> idsLabels = new HashMap<String, String>();
		List<String> idsLabelsDisagreed = new ArrayList<String>();

		for (int i = 0; i < listEla.size(); i++) {

			// agreed case
			if (listEla.get(i).getAgreed()) {
				// count the agreed ones
				counter++;

				idsLabels.put(listEla.get(i).getId(), listEla.get(i).getPredicted());

				// count the agreed fake and real ones
				if (listEla.get(i).getActual().equals("fake")) {
					counterFake++;
				} else {
					counterReal++;
				}

				// count the agreed (fake and real) right-predicted ones
				if (listEla.get(i).getActual().equals(listEla.get(i).getPredicted())) {
					counterRightPred++;
					if (listEla.get(i).getActual().equals("fake")) {
						counterRightFake++;
					} else {
						counterRightReal++;
					}
				}
			}

			// disagreed case
			else {
				// count the disagreed fake and real ones
				if (listEla.get(i).getActual().equals("fake")) {
					counterFakeDis++;
				} else {

					counterRealDis++;
				}
				idsLabelsDisagreed.add(listEla.get(i).getId());
				
			}
		}

		if (getVerbose()){
			FileManager.getInstance().writeListToFile(idsLabelsDisagreed,  DoubleVerifyBagging.getOutputFolderPerRun() + "DisAgreed.txt");
			FileManager.getInstance().writeStringHashmapToFile(idsLabels,  DoubleVerifyBagging.getOutputFolderPerRun() + "Agreed.txt");
		}

		LOGGER.info("=== ENSEMBLE CLASSIFICATION ===");
		LOGGER.info("== AGREED ==");
		LOGGER.info("Number of agreed items " + counter + " (fake " + counterFake + ",real " + counterReal + ").");
		LOGGER.info("Percentage of agreed items " + (double) counter	/ instaSize * 100);
		LOGGER.info("Accuracy: Predicted right: " + counterRightPred);
		LOGGER.info("Fake right predicted " + counterRightFake	+ " real right predicted " + counterRightReal);
		LOGGER.info("Accuracy of the agreed percentage "	+ (double) counterRightPred / counter * 100);
		
		LOGGER.info("== DISAGREED ==");
		LOGGER.info("Number of disagreed items " + (instaSize - counter)	+ "(fake " + counterFakeDis + ",real " + counterRealDis) ;
		LOGGER.info("Percentage of disagreed items "+ (double) (instaSize - counter) / instaSize * 100);
		
		
		if (getVerbose()){
				FileManager.getInstance().writePlainDataToFile("=== ENSEMBLE CLASSIFICATION ===", DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
				FileManager.getInstance().writePlainDataToFile("== AGREED ==", DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
				FileManager.getInstance().writePlainDataToFile("Number of agreed items " + counter + " (fake " + counterFake + ",real " + counterReal + ").", DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
				FileManager.getInstance().writePlainDataToFile("Percentage of agreed items " + (double) counter	/ instaSize * 100, DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
				FileManager.getInstance().writePlainDataToFile("Accuracy: Predicted right: " + counterRightPred, DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
				FileManager.getInstance().writePlainDataToFile("Fake right predicted " + counterRightFake	+ " real right predicted " + counterRightReal, DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
				FileManager.getInstance().writePlainDataToFile("Accuracy of the agreed percentage "	+ (double) counterRightPred / counter * 100, DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
				FileManager.getInstance().writePlainDataToFile("== DISAGREED ==", DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
				FileManager.getInstance().writePlainDataToFile("Number of disagreed items " + (instaSize - counter)	+ "(fake " + counterFakeDis + ",real " + counterRealDis, DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
				FileManager.getInstance().writePlainDataToFile("Percentage of disagreed items "+ (double) (instaSize - counter) / instaSize * 100, DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
		}

		return listEla;
	}
	
	public Instances[] createAgreedDisagreedDatasets(List<ElementAnnotation> listEla)
			throws Exception {

		Instances[] returnedAgreedDisagreed = new Instances[2];
		
		List<String> ids_agreed = new ArrayList<String>();
		List<String> ids_disagreed = new ArrayList<String>();

		HashMap<String, String> results = new HashMap<String, String>();

		// iterate through listEla and separate tweet ids according to their
		// agreed value

		for (int i = 0; i < listEla.size(); i++) {

			if (listEla.get(i).getAgreed()) {
				ids_agreed.add(listEla.get(i).getId());
				results.put(listEla.get(i).getId(), listEla.get(i)
						.getPredicted());
			} else {
				ids_disagreed.add(listEla.get(i).getId());
			}
		}

		Instances training, testing;
		int pointer = getDatasetPointer();

		training = new Instances(getSets()[pointer], ids_agreed.size());
		testing = new Instances(getSets()[pointer], ids_disagreed.size());
		training.setClassIndex(getSets()[pointer].numAttributes() - 1);
		testing.setClassIndex(getSets()[pointer].numAttributes() - 1);


		// create the new training and testing sets
		for (int i = 0; i < getSets()[pointer].size(); i++) {

			Instance inst = getSets()[pointer].get(i);

			if (ids_agreed.contains(inst.stringValue(0))) {
				training.add(inst);
			} else if (ids_disagreed.contains(inst.stringValue(0))) {
				testing.add(inst);
			}

		}

		// change the class value of the agreed instances. Set the agreed
		// predicted value of the class, not the actual one.
		int counter3 = 0;
		for (int i = 0; i < training.size(); i++) {

			for (int j = 0; j < listEla.size(); j++) {

				if (training.get(i).stringValue(0).equals(listEla.get(j).getId())) {

					String classBefore = training.classAttribute().value((int) training.instance(i).classValue());
					training.get(i).setClassValue(listEla.get(j).getPredicted());
					String classAfter = training.classAttribute().value((int) training.instance(i).classValue());

					if (!classBefore.equals(classAfter)) {
						counter3++;
					}
				}

			}
		}
		System.out.println("class changed in " + counter3 + " items.");

		System.out.println("Training size(agreed items) " + training.size());

		returnedAgreedDisagreed[0] = training;
		returnedAgreedDisagreed[1] = testing;
		
		System.out.println("Agreed disagreed datasets [training]: "+returnedAgreedDisagreed[0].size()+", [testing]: "+returnedAgreedDisagreed[1].size());
		
		return returnedAgreedDisagreed;
 		
	}
	
	public void classifyDisagreedOnAgreed(Instances training, Instances testing) throws Exception {

		/** classify disagreed building model on the agreed **/
		FilteredClassifier fc = new FilteredClassifier();

		Classifier tree = getCurrentClassifier(getClassifier());
		Remove rm = new Remove();
		rm.setAttributeIndices("1");

		MultiFilter mf = new MultiFilter();
		int pointer = getDatasetPointer();
				
		Filter[] filters = {rm};
		mf.setFilters(filters);
			
		/**
		 * Linear Regression for missing values - 
		 * these are new features which came from the testing set and they are not fed in the linear regression method previously
		 */
		
		if (pointer==0) {
			for (int i = 0; i < getTweetRegressionClasses().size(); i++){
				training = DataHandler.getInstance().getTransformedTrainingTweet(training, getTweetRegressionClasses().get(i));
				testing  = DataHandler.getInstance().getTransformedTestingTweet(testing, getTweetRegressionClasses().get(i));
			}
			
			training =  DataHandler.getInstance().normalizationTweet(training);
			testing =  DataHandler.getInstance().normalizationTestingTweet(testing);
			
		}else {
			for (int i = 0; i < getUserRegressionClasses().size(); i++){

				training = DataHandler.getInstance().getTransformedTrainingUser(training, getUserRegressionClasses().get(i));
				testing  = DataHandler.getInstance().getTransformedTestingUser(testing, getUserRegressionClasses().get(i));
			}
			training =  DataHandler.getInstance().normalizationUser(training);
			testing =  DataHandler.getInstance().normalizationTestingUser(testing);
		}

		try {
			fc.setFilter(mf);
			fc.setClassifier(tree);
			fc.buildClassifier(training);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
		// define hashmap which holds the ids and labels decided by the
		// classifier-made for jar purposes
		HashMap<String, String> idsLabels = new HashMap<String, String>();

		int counter2 = 0, counterFake = 0, counterReal = 0;

		for (int i = 0; i < testing.size(); i++) {

			double pred = fc.classifyInstance(testing.instance(i));
			String actual = testing.classAttribute().value((int) testing.instance(i).classValue());
			String predicted = testing.classAttribute().value((int) pred);
			double[] probDistr = fc.distributionForInstance(testing.instance(i));
			// put result to hashmap(jar purposes)
			String id = testing.instance(i).stringValue(0);

			idsLabels.put(id, predicted);

			if (actual.equals(predicted)) {
				counter2++;
				if (actual.equals("fake")) {
					counterFake++;
				} else {
					counterReal++;
				}
			}
			if (getVerbose()){			
				if (predicted.equals("fake")) {
					FileManager.getInstance().writePlainDataToFile(id+"\t"+ 
							actual + "\t" + 
							predicted + "\t" +
							(probDistr[(int)pred]),
							DoubleVerifyBagging.getOutputFolderPerRun()  + "CL_ag_WithoutBagging_Predictions.txt");
					
					
				}else{
					FileManager.getInstance().writePlainDataToFile(id+"\t"+ 
							actual + "\t" + 
							predicted + "\t" +
							(1 - probDistr[(int)pred]),
							DoubleVerifyBagging.getOutputFolderPerRun()  + "CL_ag_WithoutBagging_Predictions.txt");
					
				}
			}

		}

		System.out.println("simple testing ");
		System.out.println("BASED ON THE AGREED: Disagreed accuracy "	+ (double) counter2 / testing.size() * 100);
		System.out.println("Fake items predicted right " + counterFake);
		System.out.println("Real items predicted right " + counterReal);
		
		if (getVerbose()){
			FileManager.getInstance().writePlainDataToFile("simple testing - BASED ON THE AGREED: Disagreed accuracy: " + (double) counter2 / testing.size() * 100 , DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
			FileManager.getInstance().writePlainDataToFile("Fake items predicted right " + counterFake , DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
			FileManager.getInstance().writePlainDataToFile("Real items predicted right  " + counterReal  , DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
		}

		
		/** end of classify disagreed building model on the agreed **/

	}
	
	public static Instances concatInstances (Instances inst1, Instances inst2)
	{
			ArrayList<Instance> instAL = new ArrayList<Instance>();
			for (int i=0; i<inst2.numInstances(); i++)
			instAL.add(inst2.instance(i));
			for (int i=0; i<instAL.size(); i++)
			inst1.add(instAL.get(i));
			return (inst1);
	}
	

	public Instances[] classifyDisagreedOnUpdatedExistingModel2(Instances training,	Instances testing) throws Exception {
	
	Instances[] updatedAgreedDisagreedDatasets =  new Instances[2];
	
		// Classifier and Filter settings
		FilteredClassifier fc = new FilteredClassifier();
		Classifier tree = getCurrentClassifier(getClassifier());
		Remove rm = new Remove();
		rm.setAttributeIndices("1");
	
	   // Check where are the string attributes
	    int asize = training.numAttributes();
	    boolean strings_pos[] = new boolean[asize];
	    for(int i=0; i<asize; i++)
	    {
	        Attribute att = training.attribute(i);
	        strings_pos[i] = ((att.type() == Attribute.STRING));
	    }
	    
		int pointer = getDatasetPointer();
		Instances init_train = getTrainDatasets()[pointer];	
	
	    Instances dest = new Instances(training);
	
	    dest.setRelationName(training.relationName() + "+" + init_train.relationName());
	    
	    for (int j = 0; j < init_train.numInstances(); j ++){
	    	Instance inst_temp = init_train.instance(j);
	    	dest.add(inst_temp);
	    	
	    	 // Copy string attributes
	        for(int i=0; i<asize; i++) {
	            if(strings_pos[i]) {
	            	//System.out.println(strings_pos[i]);
	                dest.instance(dest.numInstances()-1)
	                    .setValue(i,inst_temp.stringValue(i));
	            }
	        }
	    }
	   				
		MultiFilter mf = new MultiFilter();
		
		Filter[] filters = { rm };
		mf.setFilters(filters);
		
	
		Instances transNewtraining = new Instances(dest);
		Instances transTesting	   = new Instances(testing);
	
	
		if (pointer==0) {
			for (int i = 0; i < getTweetRegressionClasses().size(); i++){
	
				transNewtraining = DataHandler.getInstance().getTransformedTrainingTweet(transNewtraining,  getTweetRegressionClasses().get(i));
				transTesting		= DataHandler.getInstance().getTransformedTestingTweet(transTesting,  getTweetRegressionClasses().get(i));
			}
			transNewtraining =  DataHandler.getInstance().normalizationTweet(transNewtraining);
			transTesting =  DataHandler.getInstance().normalizationTestingTweet(transTesting);
		}
		else {
			for (int i = 0; i < getUserRegressionClasses().size(); i++){
	
				transNewtraining 	= DataHandler.getInstance().getTransformedTrainingUser(transNewtraining, getUserRegressionClasses().get(i));
				transTesting		= DataHandler.getInstance().getTransformedTestingUser(transTesting, getUserRegressionClasses().get(i));
			}
			transNewtraining =  DataHandler.getInstance().normalizationUser(transNewtraining);
			transTesting =  DataHandler.getInstance().normalizationTestingUser(transTesting);
		}
		
	
		
		try {
			fc.setFilter(mf);
			fc.setClassifier(tree);
			fc.buildClassifier(transNewtraining);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		int counter2 = 0, counterFake = 0, counterReal = 0;
	
		for (int i = 0; i < transTesting.size(); i++) {
			String actual = transTesting.classAttribute().value(
					(int) transTesting.instance(i).classValue());
	
			if (actual.equals("fake")) {
				counterFake++;
			} else {
				counterReal++;
			}
	
		}
		
		//System.out.println(" *****************Classifier " + fc.toString());
	
		System.out.println("fake count " + counterFake + ", real count "
				+ counterReal);
	
		// jar
		HashMap<String, String> idsLabels = new HashMap<String, String>();
	
		counter2 = 0;
		counterFake = 0;
		counterReal = 0;
		for (int i = 0; i < transTesting.size(); i++) {
			double pred = fc.classifyInstance(transTesting.instance(i));
			// System.out.print("ID: " + testing.instance(i).stringValue(0));
	
			String actual = transTesting.classAttribute().value(
					(int) transTesting.instance(i).classValue());
	
			String predicted = transTesting.classAttribute().value((int) pred);
	
			// put result to hashmap(jar purposes)
			String id = transTesting.instance(i).stringValue(0);
	
			idsLabels.put(id, predicted);
	
			if (actual.equals(predicted)) {
				counter2++;
				if (actual.equals("fake")) {
					counterFake++;
				} else {
					counterReal++;
				}
			}
			
		}
	
		System.out.println("simple testing");
		System.out
				.println("BASED ON THE UPDATED EXISTING MODEL - simple testing: Disagreed accuracy "
						+ (double) counter2 / testing.size() * 100);
		System.out.println("Fake items predicted right " + counterFake);
		System.out.println("Real items predicted right " + counterReal);
	
		if (getVerbose()){
				FileManager.getInstance().writePlainDataToFile("simple testing - BASED ON THE UPDATED EXISTING MODEL: Disagreed accuracy: " + (double) counter2 / testing.size() * 100 , DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
				FileManager.getInstance().writePlainDataToFile("Fake items predicted right " + counterFake , DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
				FileManager.getInstance().writePlainDataToFile("Real items predicted right  " + counterReal  , DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
		}
	
		updatedAgreedDisagreedDatasets[0] = dest;
		updatedAgreedDisagreedDatasets[1] = testing;
		
		return updatedAgreedDisagreedDatasets;
	}

		
	public void classifyDisagreedOnUpdatedExistingModelInstance2(Instances training, Instances testing)
			throws Exception {

		int counter2 = 0, counterFake = 0, counterReal = 0;

		// Classifier and Filter settings
		FilteredClassifier fc = new FilteredClassifier();
		Classifier tree = getCurrentClassifier(getClassifier());
		Remove rm = new Remove();
		rm.setAttributeIndices("1");
		MultiFilter mf = new MultiFilter();
		
		int pointer = getDatasetPointer();
		Instances init_train = getTrainDatasets()[pointer];
		
		 int asize = training.numAttributes();
		    boolean strings_pos[] = new boolean[asize];
		    for(int i=0; i<asize; i++)
		    {
		        Attribute att = training.attribute(i);
		        strings_pos[i] = ((att.type() == Attribute.STRING));
		    }		    
		    
		    Instances dest = new Instances(training);
		    dest.setRelationName(training.relationName() + "+" + init_train.relationName());
		    
		    for (int j = 0; j < init_train.numInstances(); j ++){
		    	Instance inst_temp = init_train.instance(j);
		    	dest.add(inst_temp);
		    	
		    	 // Copy string attributes
		        for(int i=0; i<asize; i++) {
		            if(strings_pos[i]) {
		                dest.instance(dest.numInstances()-1)
		                    .setValue(i,inst_temp.stringValue(i));
		            }
		        }
		    }
		
		Instances newtraining = new Instances(dest);

		Collections.shuffle(newtraining, new Random(getCurrentRandomValues()[4]));

		counterFake = 0;
		counterReal = 0;
		for (int i = 0; i < newtraining.size(); i++) {
			if (newtraining.classAttribute()
					.value((int) newtraining.instance(i).classValue())
					.equals("fake")) {
				counterFake++;
			} else
				counterReal++;
		}

		int min = Math.min(counterFake, counterReal);

		Instances trainingInst = new Instances(newtraining,0);

		counterFake = 0;
		counterReal = 0;

		for (int i = 0; i < newtraining.size(); i++) {
			if (counterFake < min) {
				if (newtraining.classAttribute().value((int) newtraining.get(i).classValue()).equals("fake")) {
					counterFake++;
					trainingInst.add(newtraining.get(i));
				}
			}
		}

		for (int i = 0; i < newtraining.size(); i++) {
			if (counterReal < min) {
				if (newtraining.classAttribute().value((int) newtraining.get(i).classValue()).equals("real")) {
					counterReal++;
					trainingInst.add(newtraining.get(i));
				}
			}
		}

		Filter[] filters = { rm };
		mf.setFilters(filters);
		
		
		if (pointer==0) {
			for (int i = 0; i < getTweetRegressionClasses().size(); i++){
				trainingInst = DataHandler.getInstance().getTransformedTrainingTweet(trainingInst, getTweetRegressionClasses().get(i));
				testing		 = DataHandler.getInstance().getTransformedTestingTweet(testing, getTweetRegressionClasses().get(i));
			}
			trainingInst =  DataHandler.getInstance().normalizationTweet(trainingInst);
			testing =  DataHandler.getInstance().normalizationTestingTweet(testing);
		}else {
			for (int i = 0; i < getUserRegressionClasses().size(); i++){
				trainingInst = DataHandler.getInstance().getTransformedTrainingUser(trainingInst, getUserRegressionClasses().get(i));
				testing		 = DataHandler.getInstance().getTransformedTestingUser(testing, getUserRegressionClasses().get(i));
			}
			trainingInst =  DataHandler.getInstance().normalizationUser(trainingInst);
			testing =  DataHandler.getInstance().normalizationTestingUser(testing);
		}
		
		
		try {
			fc.setFilter(mf);
			fc.setClassifier(tree);
			fc.buildClassifier(trainingInst);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// jar
		HashMap<String, String> idsLabels = new HashMap<String, String>();

		counter2 = 0;
		counterFake = 0;
		counterReal = 0;
		for (int i = 0; i < testing.size(); i++) {
			double pred = fc.classifyInstance(testing.instance(i));
			String actual = testing.classAttribute().value(
					(int) testing.instance(i).classValue());	
			String predicted = testing.classAttribute().value((int) pred);		
			
			double[] probDistr = fc.distributionForInstance(testing.instance(i));
			// put result to hashmap(jar purposes)
			String id = testing.instance(i).stringValue(0);			
			idsLabels.put(id, predicted);
			if (actual.equals(predicted)) {
				counter2++;
				if (actual.equals("fake")) {
					counterFake++;
				} else {
					counterReal++;
				}
			}
			
			if (getVerbose()){
			
				if (predicted.equals("fake")) {
					FileManager.getInstance().writePlainDataToFile(id+"\t"+ 
							actual + "\t" + 
							predicted + "\t" +
							(probDistr[(int)pred]),
							DoubleVerifyBagging.getOutputFolderPerRun()  + "CL_tot_WithoutBagging_Predictions.txt");
					
					
				}else{
					FileManager.getInstance().writePlainDataToFile(id+"\t"+ 
							actual + "\t" + 
							predicted + "\t" +
							(1 - probDistr[(int)pred]),
							DoubleVerifyBagging.getOutputFolderPerRun()  + "CL_tot_WithoutBagging_Predictions.txt");
					
				}
			}
			
				
		}
		
		
	

		System.out.println();
		System.out.println("BASED ON THE UPDATED EXISTING MODEL INSTANCE: Disagreed accuracy "+ (double) counter2 / testing.size() * 100);
		System.out.println("Fake items predicted right " + counterFake);
		System.out.println("Real items predicted right " + counterReal);
		if (getVerbose()){
			FileManager.getInstance().writePlainDataToFile("BASED ON THE UPDATED EXISTING MODEL INSTANCE: Disagreed accuracy:" + (double) counter2 / testing.size() * 100 , DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
			FileManager.getInstance().writePlainDataToFile("Fake items predicted right " + counterFake , DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
			FileManager.getInstance().writePlainDataToFile("Real items predicted right  " + counterReal  , DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
		}
	
	}
	
	
	public static void startVerification(
			String fileNameUser, 
			String fileNameTweet, 
			String trainPosts, 
			String testPosts,
			String outputFolder) throws Exception{
		
		DoubleVerifyBagging dvb = new DoubleVerifyBagging();
		LOGGER.setLevel(Level.OFF);
				
		setVerbose(verbose);
		
		/**
		 * Load features
		 */
	
		List<String> trainIds = new ArrayList<String>();
		LOGGER.info("Read Train Annotation " + trainPosts);
		List<FeaturesAnnotationItem> trainAnnotationList = new ArrayList<FeaturesAnnotationItem>();
		try (Stream<String> stream = Files.lines(Paths.get(trainPosts), StandardCharsets.UTF_8)) {			
			stream.forEach(line -> {
				
				JSONObject json = new JSONObject(line);
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
						.create();	
				FeaturesAnnotationItem item = gson.fromJson(json.toString(), FeaturesAnnotationItem.class);
				
				trainAnnotationList.add(item);	
				trainIds.add(item.getId());
				
			});		   
		} catch (IOException e) {
			e.printStackTrace();		        
		}
		
		List<String> testIds = new ArrayList<String>();
		LOGGER.info("Read Test Annotation " + testPosts);
		List<FeaturesAnnotationItem> testAnnotationList = new ArrayList<FeaturesAnnotationItem>();
		try (Stream<String> stream = Files.lines(Paths.get(testPosts), StandardCharsets.UTF_8)) {			
			stream.forEach(line -> {
				
				JSONObject json = new JSONObject(line);
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
						.create();	
				FeaturesAnnotationItem item = gson.fromJson(json.toString(), FeaturesAnnotationItem.class);
				testAnnotationList.add(item);	
				testIds.add(item.getId());
				
			});		   
		} catch (IOException e) {
			e.printStackTrace();		        
		}
		
		LOGGER.info("Read Tweet-based Features from JSON file " + fileNameTweet);
		List<TweetFeatures> tweetFeatsTrainList = new ArrayList<TweetFeatures>();
		List<TweetFeatures> tweetFeatsTestList = new ArrayList<TweetFeatures>();

		try (Stream<String> stream = Files.lines(Paths.get(fileNameTweet), StandardCharsets.UTF_8)) {			
			stream.forEach(line -> {
				
				JSONObject json = new JSONObject(line);
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
						.create();	
				TweetFeatures item = gson.fromJson(json.toString(), TweetFeatures.class);
				if (trainIds.contains(item.getId().trim())){
					LOGGER.info("This tweet belongs to training set " + item.getId());
				//	System.out.println(item.getUrlHarmonic());
					tweetFeatsTrainList.add(item);
				}else if (testIds.contains(item.getId().trim())){
					LOGGER.info("This tweet belongs to testing set " + item.getId());

					tweetFeatsTestList.add(item);	
				}else{
					LOGGER.info("This tweet does not belong neither to training nor to testing set " + item.getId());
				}
				
			});		   
		} catch (IOException e) {
			e.printStackTrace();		        
		}	
		
		LOGGER.info("Read User-based Features from JSON file " + fileNameUser);
		List<UserFeatures> userFeatsTrainList = new ArrayList<UserFeatures>();
		List<UserFeatures> userFeatsTestList = new ArrayList<UserFeatures>();

		try (Stream<String> stream = Files.lines(Paths.get(fileNameUser), StandardCharsets.UTF_8)) {			
			stream.forEach(line -> {
				
				JSONObject json = new JSONObject(line);
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
						.create();	
				UserFeatures item = gson.fromJson(json.toString(), UserFeatures.class);
				if (trainIds.contains(item.getId().trim())){
					LOGGER.info("This tweet belongs to training set " + item.getId());
					
					userFeatsTrainList.add(item);	
				}else if (testIds.contains(item.getId().trim())){
					LOGGER.info("This tweet belongs to testing set " + item.getId());
					userFeatsTestList.add(item);
				}else{
					LOGGER.info("This tweet does not belong neither to training nor to testing set " + item.getId());
				}
			});		   
		} catch (IOException e) {
			e.printStackTrace();		        
		}
		
		int numberOfTestingTweets = testAnnotationList.size();
				
		LOGGER.info("LOAD FEATURES");
		Instances trainingUser = loadUserFeature(userFeatsTrainList, trainAnnotationList);
		Instances trainingTweet = loadTweetFeature(tweetFeatsTrainList, trainAnnotationList);
		
		Instances testingUser = loadUserFeature(userFeatsTestList, testAnnotationList);
		Instances testingTweet = loadTweetFeature(tweetFeatsTestList, testAnnotationList);
		
		Instances[] trainDatasets = new Instances[] {trainingTweet, trainingUser};
		dvb.setTrainDatasets(trainDatasets);
		
		Instances[] testDatasets = new Instances[] {testingTweet, testingUser};
		dvb.setTestDatasets(testDatasets);
		setOutputFolder(outputFolder + "/");
		
		Instances trainConcat = null, testConcat = null;
		if (getRunConcatenated()){
			 trainConcat = loadConcatFeature(tweetFeatsTrainList, userFeatsTrainList, trainAnnotationList );
			 testConcat = loadConcatFeature(tweetFeatsTestList, userFeatsTestList, testAnnotationList );
			
			if (getVerbose()){
				FileManager.getInstance().writeDataToFile(getOutputFolder() + "testingConcat.txt", testConcat);
				FileManager.getInstance().writeDataToFile(getOutputFolder() + "trainingConcat.txt", trainConcat);
			}
		}
		
		if (getVerbose()){
				FileManager.getInstance().writeDataToFile(getOutputFolder() + "trainingUB.txt", trainingUser);
				FileManager.getInstance().writeDataToFile(getOutputFolder() + "trainingTB.txt", trainingTweet);
				FileManager.getInstance().writeDataToFile(getOutputFolder() + "testingUB.txt", testingUser);
				FileManager.getInstance().writeDataToFile(getOutputFolder() + "testingTB.txt", testingTweet);			
		}
		
		/**
		 *  Check the testing dataset if there are both USER-based and TWEET-based features for all tweets
		 *  There are cases, such as when the user is suspended or the tweet is no longer available, where one of the feature types is missing.
		 */
		LOGGER.info("Check if both User and Tweet - based dataset have equal number of instances");
		dvb.findCommonSets(dvb.getTestDatasets());
		
		/**
		 *  get the tweet-based/user-based score from cross validation process
		 *  This will be user to select the "stronger" classifier
		 */
		LOGGER.info("Apply Cross Validation to define the strongest feature type");
		Double tweetScore = dvb.getScore(dvb.getTrainDatasets()[0], 0);
		dvb.setItemScore(tweetScore);
		Double userScore = dvb.getScore(dvb.getTrainDatasets()[1], 1);
		dvb.setUserScore(userScore);
		
		/**
		 *  decide between Tweet (dataset pointer = 0) or User features (dataset pointer = 1)
		 */
		dvb.setDatasetPointer((dvb.getItemScore() > dvb.getUserScore()) ? 0 : 1);
		LOGGER.info("Strongest Feature type " + dvb.getDatasetPointer());
		/**
		 *  Iterate through the list of random values to retrieve the results among the different trials
		 *  This is done do to the bagging technique
		 */	
	
		FileManager.getInstance().writePlainDataToFile("RUN" + "\t" + "CL1" + "\t" + "CL2" + "\t" + "CL_ag" + "\t" + "CL_tot",  getOutputFolder() + "AverageResultsPerRun.txt");	
		FileManager.getInstance().writePlainDataToFile("CL" + "\t" + "CL_ag" + "\t" + "CL_tot",  getOutputFolder() + "AverageResults.txt");	
		ArrayList<int[]> randomValues = dvb.getRandomValues();
		
		
		List<Double>  cl_accAvg = new ArrayList<Double>();
		List<Double>  clAG_accAvg =new ArrayList<Double>();
		List<Double>  clTOT_accAvg =  new ArrayList<Double>(); 
		
		for (int i = 0; i < randomValues.size(); i++) {
			setExpNo(i);
									
			/**
			 * set the number of experiment 
			 * (how many times we perform the same experiment, though with different random value)
			 */
		
			
			if (getVerbose()){
				LOGGER.info("Random RUN " + getOutputFolder() + "/" + getExpNo());
				File outputFolderRun = new File(getOutputFolder() + "/" + getExpNo() + "/");
				if (!outputFolderRun.exists())
					outputFolderRun.mkdirs();
				
				setOutputFolderPerRun(getOutputFolder() + "/" + getExpNo() + "/");
			}
			FileManager.getInstance().writePlainDataToFileNonl(getExpNo() + "\t",
					DoubleVerifyBagging.getOutputFolder() + "AverageResultsPerRun.txt");
		
			/**
			 *  Assign the corresponding random values to the randomVals variable
			 *  of the Bagging class
			 */
			dvb.setCurrentRandomValues(randomValues.get(i));
			Bagging bg = new Bagging(dvb.getCurrentRandomValues());
					
			LOGGER.info("RANDOM TRAIN");
			dvb.classifyWithRandomTrainInstance(dvb.getTrainDatasets()[0], dvb.getSets()[0], 0, dvb.getTweetRegressionClasses());
			dvb.classifyWithRandomTrainInstance(dvb.getTrainDatasets()[1], dvb.getSets()[1], 1, dvb.getUserRegressionClasses());

			LOGGER.info("Define the size of the training items bagging will use");
			int trainingSize = dvb.determineTrainingSize(dvb.getTrainDatasets()[0]);
						
			Classifier[] itemCls = bg.createClassifiersTweet(dvb.getTrainDatasets()[0], dvb.getSets()[0], trainingSize, dvb.getTweetRegressionClasses(), "CL1");
			System.out.println();
			LOGGER.info("Initial tweet classification with Item features and bagging technique");
			VerificationResult[] itemClsPreds = null;
			itemClsPreds = bg.classifyItems(itemCls, bg.getTestingSets(), "CL1");
		
			LOGGER.info(" Number of verification results " + itemClsPreds.length);
	
			System.out.println();
			trainingSize = dvb.determineTrainingSize(dvb.getTrainDatasets()[1]);
			
			Classifier[] userCls = bg.createClassifiersUser(dvb.getTrainDatasets()[1], dvb.getSets()[1], trainingSize, dvb.getUserRegressionClasses(), "CL2");		
			System.out.println();
			LOGGER.info("Initial tweet classification with User features and bagging technique");
			VerificationResult[] userClsPreds = null;
			userClsPreds = bg.classifyItems(userCls, bg.getTestingSetsUser(), "CL2");
			
			if (getRunConcatenated()){
				Classifier[] concatCls = bg.createClassifiersConcat(trainConcat, testConcat, trainingSize, dvb.getConcatRegressionClasses(), "CL_cat");
				VerificationResult[] concatClsPreds = bg.classifyItems(concatCls, bg.getTestingSetsConcat(), "CL_cat");
			}
			
			/**
			 *  Check if Tweet-based and User-based classifiers agreed or not
			 */
			List<ElementAnnotation> listEla = dvb.classifyItems(itemClsPreds, userClsPreds);
			
			
			/**
			 *  Separate agreed and disagreed datasets
			 */
			Instances[] returnedAgreedDisagreedDatasets = dvb.createAgreedDisagreedDatasets(listEla);
			
			
			/**
			 * Classify disagreed on agreed. Train new classifier using only the agreed tweets with bagging.
			 * The features are selected based on the cross validation score calculated at the beginning.
			 * 
			 * 
			 */
			VerificationResult[] agClsPreds = null;
			if (returnedAgreedDisagreedDatasets[0].size() > 0 && returnedAgreedDisagreedDatasets[1].size()>0) {
				
				if (getMethod().equals("1") || getMethod().equals("5")){
					LOGGER.info("Classify disagreed on agreed without bagging");
					dvb.classifyDisagreedOnAgreed(returnedAgreedDisagreedDatasets[0], returnedAgreedDisagreedDatasets[1]);
				}
				
				if (getMethod().equals("2") || getMethod().equals("5")){
							LOGGER.info("Classify disagreed on agreed with bagging");
						int size = dvb.determineTrainingSize(returnedAgreedDisagreedDatasets[0]);
						
						if (dvb.getDatasetPointer() == 0) {
							Classifier[] itemClassifiers = bg.createClassifiersTweet(returnedAgreedDisagreedDatasets[0], returnedAgreedDisagreedDatasets[1], size, dvb.getTweetRegressionClasses(), "CL_ag");
							agClsPreds = bg.classifyItems(itemClassifiers, bg.getTestingSets(), "CL_ag");
							
						} else {	
							Classifier[] userClassifiers = bg.createClassifiersUser(returnedAgreedDisagreedDatasets[0], returnedAgreedDisagreedDatasets[1], size, dvb.getUserRegressionClasses(), "CL_ag");
							agClsPreds = bg.classifyItems(userClassifiers, bg.getTestingSetsUser(), "CL_ag");
						}
				}				
			}else {
				System.out.println("Not enough instances to perform on-agreed-classification");
			}
			
			
			
			//classify DISAGREED ON UPDATED EXISTING MODEL
			Instances[] updatedAgreedDisagreedDatasets = new Instances[2];
			VerificationResult[] totClsPreds = null;
			if (returnedAgreedDisagreedDatasets[1].size() > 0) {
				if (getMethod().equals("3") || getMethod().equals("5")){
						updatedAgreedDisagreedDatasets = dvb.classifyDisagreedOnUpdatedExistingModel2(returnedAgreedDisagreedDatasets[0], returnedAgreedDisagreedDatasets[1]);
						
						int size2 = dvb.determineTrainingSize(updatedAgreedDisagreedDatasets[0]);
						LOGGER.info("Classify disagreed on Updated existing model with bagging");
						// OLGA 
						if (dvb.getDatasetPointer() == 0) {
							Classifier[] itemClassifiers = bg.createClassifiersTweet(updatedAgreedDisagreedDatasets[0], updatedAgreedDisagreedDatasets[1], size2, dvb.getTweetRegressionClasses(), "CL_tot");
							totClsPreds = bg.classifyItems(itemClassifiers, bg.getTestingSets(), "CL_tot");
						} else {
							Classifier[] userClassifiers = bg.createClassifiersUser(updatedAgreedDisagreedDatasets[0], updatedAgreedDisagreedDatasets[1], size2, dvb.getUserRegressionClasses(), "CL_tot");
							totClsPreds = bg.classifyItems(userClassifiers, bg.getTestingSetsUser(), "CL_tot");
						}
				}	
				if (getMethod().equals("4") || getMethod().equals("5")){
					//classify DISAGREED ON UPDATED EXISTING MODEL INSTANCE
					LOGGER.info("Classify disagreed on Updated existing model without bagging");
	
					dvb.classifyDisagreedOnUpdatedExistingModelInstance2(returnedAgreedDisagreedDatasets[0], returnedAgreedDisagreedDatasets[1]);
				}
				
				int cnt_agreed = 0, cnt_true_Cl = 0, cnt_true_CLag = 0, cnt_true_CLtot = 0, cnt_agreed_true =0, cnt_all_Cl = 0;
				if (dvb.getDatasetPointer() == 0){
				
					for (int tw = 0; tw < itemClsPreds.length; tw ++){
						VerificationResult verRes = itemClsPreds[tw];
						if (verRes.getActual().equals(verRes.getPrediction())){
							cnt_true_Cl =  cnt_true_Cl +1;
						}
						cnt_all_Cl = cnt_all_Cl + 1;
					}
										
					//List<ElementAnnotation> listEla
					for (int tw = 0; tw < listEla.size(); tw ++){
						ElementAnnotation verRes = listEla.get(tw);
						if (verRes.getAgreed()){
							cnt_agreed = cnt_agreed + 1;
							FileManager.getInstance().writePlainDataToFile(verRes.getId() + "\t" + verRes.getPredicted(),
									DoubleVerifyBagging.getOutputFolder() + "CL_ag_predictions_" + getExpNo() + ".txt");

							FileManager.getInstance().writePlainDataToFile(verRes.getId() + "\t" + verRes.getPredicted(),
									DoubleVerifyBagging.getOutputFolder() + "CL_tot_predictions_" + getExpNo() + ".txt");
							if (verRes.getActual().equals(verRes.getPredicted())){
								cnt_agreed_true =cnt_agreed_true + 1;
							}
						}						
					}
					
					for (int tw = 0; tw < agClsPreds.length; tw ++){
						VerificationResult verRes = agClsPreds[tw];
						if (verRes.getActual().equals(verRes.getPrediction())){
							cnt_true_CLag =  cnt_true_CLag +1;
						}
						FileManager.getInstance().writePlainDataToFile(verRes.getId() + "\t" + verRes.getPrediction(),
								DoubleVerifyBagging.getOutputFolder() + "CL_ag_predictions_" + getExpNo() + ".txt");
					}
					
					for (int tw = 0; tw < totClsPreds.length; tw ++){
						VerificationResult verRes = totClsPreds[tw];
						FileManager.getInstance().writePlainDataToFile(verRes.getId() + "\t" + verRes.getPrediction(),
								DoubleVerifyBagging.getOutputFolder() + "CL_tot_predictions_" + getExpNo() + ".txt");
						if (verRes.getActual().equals(verRes.getPrediction())){
							cnt_true_CLtot =  cnt_true_CLtot +1;
						}
					}
				
					

					double cl_acc = (double) (cnt_true_Cl)/ (cnt_all_Cl);
					double clAG_acc = (double) (cnt_agreed_true + cnt_true_CLag)/ (cnt_agreed + (numberOfTestingTweets - cnt_agreed));
					double clTOT_acc = (double) (cnt_agreed_true + cnt_true_CLtot)/ (cnt_agreed + (numberOfTestingTweets - cnt_agreed));
					cl_accAvg.add(cl_acc);
					clAG_accAvg.add(clAG_acc);
					clTOT_accAvg.add(clTOT_acc);
					FileManager.getInstance().writePlainDataToFile(clAG_acc + "\t" + clTOT_acc,
							DoubleVerifyBagging.getOutputFolder() + "AverageResultsPerRun.txt");
					

					
				}else{
					for (int tw = 0; tw < userClsPreds.length; tw ++){
						VerificationResult verRes = userClsPreds[tw];
						if (verRes.getActual().equals(verRes.getPrediction())){
							cnt_true_Cl =  cnt_true_Cl +1;
						}
						cnt_agreed = cnt_agreed + 1;
					}
					
					//List<ElementAnnotation> listEla
					for (int tw = 0; tw < listEla.size(); tw ++){
						ElementAnnotation verRes = listEla.get(tw);
						if (verRes.getAgreed()){
							cnt_agreed = cnt_agreed + 1;
							FileManager.getInstance().writePlainDataToFile(verRes.getId() + "\t" + verRes.getPredicted(),
									DoubleVerifyBagging.getOutputFolder() + "CL_ag_predictions_" + getExpNo() + ".txt");

							FileManager.getInstance().writePlainDataToFile(verRes.getId() + "\t" + verRes.getPredicted(),
									DoubleVerifyBagging.getOutputFolder() + "CL_tot_predictions_" + getExpNo() + ".txt");
							if (verRes.getActual().equals(verRes.getPredicted())){
								cnt_agreed_true =cnt_agreed_true + 1;
							}
						}						
					}
					
					for (int tw = 0; tw < agClsPreds.length; tw ++){
						VerificationResult verRes = agClsPreds[tw];
						if (verRes.getActual().equals(verRes.getPrediction())){
							cnt_true_CLag =  cnt_true_CLag +1;
						}
						FileManager.getInstance().writePlainDataToFile(verRes.getId() + "\t" + verRes.getPrediction(),
								DoubleVerifyBagging.getOutputFolder() + "CL_ag_predictions_" + getExpNo() + ".txt");
					}				
					
					
					for (int tw = 0; tw < totClsPreds.length; tw ++){
						VerificationResult verRes = totClsPreds[tw];
						FileManager.getInstance().writePlainDataToFile(verRes.getId() + "\t" + verRes.getPrediction(),
								DoubleVerifyBagging.getOutputFolder() + "CL_tot_predictions_" + getExpNo() + ".txt");
						if (verRes.getActual().equals(verRes.getPrediction())){
							cnt_true_CLtot =  cnt_true_CLtot +1;
						}
					}
				
					

					double cl_acc = (double) (cnt_true_Cl)/ (cnt_all_Cl);
					double clAG_acc = (double) (cnt_agreed_true + cnt_true_CLag)/ (cnt_agreed + (numberOfTestingTweets - cnt_agreed));
					double clTOT_acc = (double) (cnt_agreed_true + cnt_true_CLtot)/ (cnt_agreed + (numberOfTestingTweets - cnt_agreed));
					cl_accAvg.add(cl_acc);
					clAG_accAvg.add(clAG_acc);
					clTOT_accAvg.add(clTOT_acc);
					
					FileManager.getInstance().writePlainDataToFile(clAG_acc + "\t" + clTOT_acc,
							DoubleVerifyBagging.getOutputFolder() + "AverageResultsPerRun.txt");
				}
				
			}else {
				LOGGER.info("Testing size equal to zero, cannot perform classification!");
			}			
		
		}
		
		FileManager.getInstance().writePlainDataToFile(cl_accAvg.stream().mapToDouble(Double::doubleValue).sum() / cl_accAvg.size() + "\t" +
				clAG_accAvg.stream().mapToDouble(Double::doubleValue).sum() / clAG_accAvg.size() + "\t" +
				clTOT_accAvg.stream().mapToDouble(Double::doubleValue).sum() / clTOT_accAvg.size(),
				DoubleVerifyBagging.getOutputFolder() + "AverageResults.txt");
		
	}
		

}
