package gr.iti.mklab.verify;

import gr.iti.mklab.util.FileManager;
import gr.iti.mklab.verifyutils.DataHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import weka.classifiers.Classifier;
import weka.classifiers.functions.Logistic;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.MultiFilter;
import weka.filters.unsupervised.attribute.Remove;

public class Bagging {

	private static final Logger LOGGER = Logger.getLogger( DoubleVerifyBagging.class.getName() );
	public static String output_files = "D:/TweetVerification/Reproducibility/TestNewFeatures/Output/";
	
	static HashMap<Double,List<Double>> ranksItem = new HashMap<Double,List<Double>>();
	static HashMap<Double,List<Double>> ranksUser = new HashMap<Double,List<Double>>();

	public static HashMap<Double,List<Double>> getRankedItemMap() {
		return ranksItem;
	}
	public static HashMap<Double,List<Double>> getRankedUserMap() {
		return ranksUser;
	}
	

	//constructor
	public Bagging(int[] randomValues) {
		setRandomVals(randomValues);
	}
	
	private Instances[] testingSets = new Instances[9];
	private Instances[] testingSetsUser = new Instances[9];
	private Instances[] testingSetsConcat = new Instances[9];

	private int[] randomVals = new int[9];
	
	public Instances[] getTestingSets() {
		return testingSets;
	}
	public void setTestingSets(Instances[] testingSets) {
		this.testingSets = testingSets;
	}
	
	public Instances[] getTestingSetsConcat() {
		return testingSetsConcat;
	}
	public void setTestingSetsConcat(Instances[] testingSetsConcat) {
		this.testingSetsConcat = testingSetsConcat;
	}

	public Instances[] getTestingSetsUser() {
		return testingSetsUser;
	}
	public void setTestingSetsUser(Instances[] testingSetsUser) {
		this.testingSetsUser = testingSetsUser;
	}
		
	public int[] getRandomVals() {
		return randomVals;
	}
	public void setRandomVals(int[] randomVals) {
		this.randomVals = randomVals;
	}
	
	public Classifier getCurrentClassifier(String classifierName){
		if (classifierName.equalsIgnoreCase("RF")){
			return new RandomForest();
		}else if (classifierName.equalsIgnoreCase("LG")){
			return new Logistic();
		}else{
			return new RandomForest();
		}
	}

	
	/**
	 * Method that creates a concrete number of classifiers by randomly selecting a number of them (trainingSize value)
	 * @param training2 Instances that consist the training set
	 * @param testing2 Instances that consist the testing set
	 * @param trainingSize the number of samples that are used for building each of the classifiers 
	 * @return array of Classifier[]
	 * @throws Exception
	 */
	public Classifier[] createClassifiersTweet(Instances training2,
						Instances testing2, 
							int trainingSize,
								List<String> regressionClasses, String StoreID) throws Exception {
		
		int countFake=0, countReal=0;
		Classifier[] classifiers = new Classifier[getRandomVals().length];
		LOGGER.info("CREATE CLASSIFIERS: TWEET");
		

		Instances testing  = new Instances(testing2);
		Instances[] testingNew = new Instances[9];
		
		for (int j=0; j<getRandomVals().length; j++) {
			
			Instances training = new Instances(training2);
			Instances currentTrain = new Instances(training,0);
	
			Collections.shuffle(training, new Random(getRandomVals()[j]));
			countFake = 0; 
			countReal = 0;
			
			
			for (int i=0; i<training.size(); i++) {
				if (countFake <(trainingSize/2)) {
					if (training.classAttribute().value((int) training.get(i).classValue()).equals("fake")) {
						currentTrain.add(training.get(i));
						countFake++;
					}
				}
							
			}
			for (int i=0; i<training.size(); i++) {
				if ( countReal <(trainingSize/2)) {
					if (training.classAttribute().value((int) training.get(i).classValue()).equals("real")) {
						currentTrain.add(training.get(i));
						countReal++;
					}
				}
			}
			//case in which training set has just one instance
			if (currentTrain.size()==0 && training.size()==1) {
				currentTrain.add(training.get(0));
				if (training.classAttribute().value((int) training.get(0).classValue()).equals("fake")) {
					countFake++;
				}
				else countReal++;
			}			
		
			/**
			 * Apply Linear Regression approach to fill missing values
			 */
			testingNew[j]  =  new Instances(testing);
			for (int i = 0; i < regressionClasses.size(); i++){

				currentTrain = DataHandler.getInstance().getTransformedTrainingTweet(currentTrain, regressionClasses.get(i));				
				testingNew[j] = DataHandler.getInstance().getTransformedTestingTweet(testingNew[j] , regressionClasses.get(i));
			}
			
			currentTrain =  DataHandler.getInstance().normalizationTweet(currentTrain);
			testingNew[j] =  DataHandler.getInstance().normalizationTestingTweet(testingNew[j]);
			
			//classifier details
			FilteredClassifier fc = new FilteredClassifier();
			Classifier tree = getCurrentClassifier(DoubleVerifyBagging.getClassifier());
			
			Remove rm = new Remove();
			rm.setAttributeIndices("1");
		
			MultiFilter mf = new MultiFilter();		
				Filter[] filters = {rm};
				mf.setFilters(filters);					
			try {
				fc.setFilter(mf);
				fc.setClassifier(tree);				
				fc.buildClassifier(currentTrain);
				classifiers[j] = fc;				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		setTestingSets(testingNew);				
		return classifiers;
	}
	
	
	
	/**
	 * Method that creates a concrete number of classifiers by randomly selecting a number of them (trainingSize value)
	 * @param training2 Instances that consist the training set
	 * @param testing2 Instances that consist the testing set
	 * @param trainingSize the number of samples that are used for building each of the classifiers 
	 * @return array of Classifier[]
	 * @throws Exception
	 */
	public Classifier[] createClassifiersTweet(Instances training2,
						Instances testing2, 
							int trainingSize,
								List<String> regressionClasses, List<String> testIDs) throws Exception {
		
		//Instances testing  = new Instances(testing2);
		
		int countFake=0, countReal=0;
		Classifier[] classifiers = new Classifier[getRandomVals().length];
		//FileManager.getInstance().writeDataToFile("D:/TweetVerification/Reproducibility/Example/training2.arff", training2);
		System.out.println("CREATE CLASSIFIERS: TWEET");
		
		Remove rm2 = new Remove();
		rm2.setAttributeIndices("1");
		rm2.setInputFormat(testing2);
		testing2 = Filter.useFilter(testing2, rm2);
		Instances testing  = new Instances(testing2);
		Instances[] testingNew = new Instances[9];
		
		for (int j=0; j<getRandomVals().length; j++) {
			//System.out.println(" Random values into createClassifiersTweet " + getRandomVals()[j]);
			Instances training = new Instances(training2);
			Instances currentTrain = new Instances(training,0);

			Collections.shuffle(training, new Random(getRandomVals()[j]));
			countFake = 0; 
			countReal = 0;
			
			
			for (int i=0; i<training.size(); i++) {
				if (countFake <(trainingSize/2)) {
					if (training.classAttribute().value((int) training.get(i).classValue()).equals("fake")) {
						currentTrain.add(training.get(i));
						countFake++;
					}
				}
							
			}
			//FileManager.getInstance().writeDataToFile("D:/TweetVerification/Reproducibility/Example/currentTrainAddFake_" + j + ".arff", currentTrain);
			for (int i=0; i<training.size(); i++) {
				if ( countReal <(trainingSize/2)) {
					if (training.classAttribute().value((int) training.get(i).classValue()).equals("real")) {
						currentTrain.add(training.get(i));
						countReal++;
					}
				}
			}
		
			//case in which training set has just one instance
			if (currentTrain.size()==0 && training.size()==1) {
				currentTrain.add(training.get(0));
				if (training.classAttribute().value((int) training.get(0).classValue()).equals("fake")) {
					countFake++;
				}
				else countReal++;
			}
			
		
			/**
			 * Apply Linear Regression approach to fill missing values
			 */
			testingNew[j]  =  new Instances(testing);
		//	FileManager.getInstance().writeDataToFile("D:/TweetVerification/Reproducibility/Example/testingNewBefore_" + j + ".arff", testingNew[j] );
			for (int i = 0; i < regressionClasses.size(); i++){

				currentTrain = DataHandler.getInstance().getTransformedTrainingTweet(currentTrain, regressionClasses.get(i));				
				testingNew[j] = DataHandler.getInstance().getTransformedTestingTweet(testingNew[j] , regressionClasses.get(i));
			}
			
			currentTrain =  DataHandler.getInstance().normalizationTweet(currentTrain);
			testingNew[j] =  DataHandler.getInstance().normalizationTestingTweet(testingNew[j]);
			
			//classifier details
			FilteredClassifier fc = new FilteredClassifier();
			Classifier tree = getCurrentClassifier(DoubleVerifyBagging.getClassifier());
			
			Remove rm = new Remove();
			rm.setAttributeIndices("1");
		
			MultiFilter mf = new MultiFilter();
		
				Filter[] filters = {rm};
				mf.setFilters(filters);		
			
			
			try {
				fc.setFilter(mf);
				fc.setClassifier(tree);				
				fc.buildClassifier(currentTrain);
				classifiers[j] = fc;
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		setTestingSets(testingNew);
				
		return classifiers;
	}
	
	
	public Classifier[] createClassifiersUser(Instances training2, 
												Instances testing2,
													int trainingSize,
													List<String> regressionClasses, String StoreID) throws Exception {
		
		
		Instances testing  = new Instances(testing2);		
		int countFake=0, countReal=0;
		Classifier[] classifiers = new Classifier[getRandomVals().length];
		
		System.out.println("CREATE CLASSIFIERS: USER");
		
		Instances[] testingNew = new Instances[9];
		
		for (int j=0; j<getRandomVals().length; j++) {
			
			Instances training = new Instances(training2);
			Instances currentTrain = new Instances(training,0);
			Collections.shuffle(training, new Random(getRandomVals()[j]));
			
			countFake = 0; 
			countReal = 0;
			
			for (int i=0; i<training.size(); i++) {
				if (countFake<(trainingSize/2)) {
					if (training.classAttribute().value((int) training.get(i).classValue()).equals("fake")) {
						countFake++;
						currentTrain.add(training.get(i));
					}
				}
							
			}
						
			for (int i=0; i<training.size(); i++) {
				if ( countReal < (trainingSize/2) ) {
					if (training.classAttribute().value((int) training.get(i).classValue()).equals("real")) {
						currentTrain.add(training.get(i));
						countReal++;
					}
				}
			}
			
			//Linear Regression approach
			testingNew[j] = new Instances(testing);
			for (int i = 0; i < regressionClasses.size(); i++){
				currentTrain = DataHandler.getInstance().getTransformedTrainingUser(currentTrain, regressionClasses.get(i));
				testingNew[j] = DataHandler.getInstance().getTransformedTestingUser(testingNew[j], regressionClasses.get(i));
			}
			
			currentTrain =  DataHandler.getInstance().normalizationUser(currentTrain);
			testingNew[j] =  DataHandler.getInstance().normalizationTestingUser(testingNew[j]);

			
			//classifier details
			FilteredClassifier fc = new FilteredClassifier();
			Classifier tree = getCurrentClassifier(DoubleVerifyBagging.getClassifier());
			
			System.out.println("Classifier Name " + DoubleVerifyBagging.getClassifier());
			System.out.println("Classifier " + tree.toString());
			
			Remove rm = new Remove();
			rm.setAttributeIndices("1");
		//	rm.setInputFormat(currentTrain);
			
			MultiFilter mf = new MultiFilter();
				Filter[] filters = {rm};
				mf.setFilters(filters);
	
			
			try {
				fc.setClassifier(tree);
				fc.setFilter(mf);				
				fc.buildClassifier(currentTrain);
				classifiers[j] = fc;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		setTestingSetsUser(testingNew);
		
		return classifiers;
	}
	
	
	/**
	 * Method that creates a concrete number of classifiers by randomly selecting a number of them (trainingSize value)
	 * @param training2 Instances that consist the training set
	 * @param testing2 Instances that consist the testing set
	 * @param trainingSize the number of samples that are used for building each of the classifiers 
	 * @return array of Classifier[]
	 * @throws Exception
	 */
	public Classifier[] createClassifiersConcat(Instances training2,
						Instances testing2, 
							int trainingSize,
								List<String> regressionClasses, String StoreID) throws Exception {
		
		int countFake=0, countReal=0;
		Classifier[] classifiers = new Classifier[getRandomVals().length];
		LOGGER.info("CREATE CLASSIFIERS: Concatenated");
		

		Instances testing  = new Instances(testing2);
		Instances[] testingNew = new Instances[9];
		
		for (int j=0; j<getRandomVals().length; j++) {
			
			Instances training = new Instances(training2);
			Instances currentTrain = new Instances(training,0);
	
			
			//FileManager.getInstance().writeDataToFile(DoubleVerifyBagging.getOutputFolderPerRun() + "CreateClassifierConcat_training.txt", training);
			Collections.shuffle(training, new Random(getRandomVals()[j]));
			countFake = 0; 
			countReal = 0;
			
			
			for (int i=0; i<training.size(); i++) {
				if (countFake <(trainingSize/2)) {
					if (training.classAttribute().value((int) training.get(i).classValue()).equals("fake")) {
						currentTrain.add(training.get(i));
						countFake++;
					}
				}
							
			}
			for (int i=0; i<training.size(); i++) {
				if ( countReal <(trainingSize/2)) {
					if (training.classAttribute().value((int) training.get(i).classValue()).equals("real")) {
						currentTrain.add(training.get(i));
						countReal++;
					}
				}
			}
			//case in which training set has just one instance
			if (currentTrain.size()==0 && training.size()==1) {
				currentTrain.add(training.get(0));
				if (training.classAttribute().value((int) training.get(0).classValue()).equals("fake")) {
					countFake++;
				}
				else countReal++;
			}	
			
			//FileManager.getInstance().writeDataToFile(DoubleVerifyBagging.getOutputFolderPerRun() + j + "_CreateClassifierConcat_currentTrain.txt", currentTrain);

		
			/**
			 * Apply Linear Regression approach to fill missing values
			 */
			testingNew[j]  =  new Instances(testing);
			//FileManager.getInstance().writeDataToFile("D:/TweetVerification/Reproducibility/Example/testingNewBefore_" + j + ".arff", testingNew[j] );
			for (int i = 0; i < regressionClasses.size(); i++){

				currentTrain = DataHandler.getInstance().getTransformedTrainingTweet(currentTrain, regressionClasses.get(i));				
				testingNew[j] = DataHandler.getInstance().getTransformedTestingTweet(testingNew[j] , regressionClasses.get(i));
			}
			
			currentTrain =  DataHandler.getInstance().normalizationTweet(currentTrain);
			testingNew[j] =  DataHandler.getInstance().normalizationTestingTweet(testingNew[j]);
			
			//FileManager.getInstance().writeDataToFile(DoubleVerifyBagging.getOutputFolderPerRun() + j + "_CreateClassifierConcat_currentTrainAfterReg.txt", currentTrain);
			//FileManager.getInstance().writeDataToFile(DoubleVerifyBagging.getOutputFolderPerRun() + j + "_CreateClassifierConcat_testingAfterReg.txt", testingNew[j]);
			//classifier details
			FilteredClassifier fc = new FilteredClassifier();
			Classifier tree = getCurrentClassifier(DoubleVerifyBagging.getClassifier());
			
			Remove rm = new Remove();
			rm.setAttributeIndices("1");
		
			MultiFilter mf = new MultiFilter();		
				Filter[] filters = {rm};
				mf.setFilters(filters);					
			try {
				fc.setFilter(mf);
				fc.setClassifier(tree);				
				fc.buildClassifier(currentTrain);
				classifiers[j] = fc;				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		setTestingSetsConcat(testingNew);				
		return classifiers;
	}
	
	
	public VerificationResult getMajorityPred(VerificationResult[] predicted) {
		
		double sumScores = 0.0;
		
		for (int j=0; j<predicted.length; j++) {
			System.out.println("prediction "+predicted[j].getPrediction() + " " + predicted[j].getProb());
			//if fake add the probability of being fake prob(fake) to the sum 
			if (predicted[j].getPrediction().equals("fake")) {
				sumScores += predicted[j].getProb();
			}
			//else if real add the (1 - prob(real)) = prob(fake) to the sum
			else {
				sumScores += (1 - predicted[j].getProb());
			}
			
		}
		
		double avgProb = (double) sumScores / predicted.length;
		//System.out.println("Average prob: "+avgProb);
		
		VerificationResult overallRes = new VerificationResult();
		if (avgProb == 0.5) {
			overallRes.setPrediction("fake");
			overallRes.setProb(avgProb);
			overallRes.setDecided(false);
		}
		else if (avgProb < 0.5) {
			overallRes.setPrediction("real");
			overallRes.setProb(1-avgProb);
			overallRes.setDecided(true);
		}
		else {
			overallRes.setPrediction("fake");
			overallRes.setProb(avgProb);
			overallRes.setDecided(true);
		}
		
		//System.out.println(overallRes.getPrediction() + " " + overallRes.getProb());
		
		
		return overallRes;
	}
	
	
private VerificationResult getMajorityPred(VerificationResult[] predicted, String StoreID) {
		
		double sumScores = 0.0;
		
		for (int j=0; j<predicted.length; j++) {
			//System.out.println("prediction "+predicted[j].getPrediction() + " " + predicted[j].getProb());
			//if fake add the probability of being fake prob(fake) to the sum 
			if (predicted[j].getPrediction().equals("fake")) {
				sumScores += predicted[j].getProb();
			//	System.out.println("prediction "+predicted[j].getPrediction() + " " + predicted[j].getProb());
			}
			//else if real add the (1 - prob(real)) = prob(fake) to the sum
			else {
				sumScores += (1 - predicted[j].getProb());			
			}					
		}

		double avgProb = (double) sumScores / predicted.length;
			
		VerificationResult overallRes = new VerificationResult();
		if (avgProb == 0.5) {
			overallRes.setPrediction("fake");
			overallRes.setProb(avgProb);
			overallRes.setDecided(false);
		}
		else if (avgProb < 0.5) {
			overallRes.setPrediction("real");
			overallRes.setProb(1-avgProb);
			overallRes.setDecided(true);
		}
		else {
			overallRes.setPrediction("fake");
			overallRes.setProb(avgProb);
			overallRes.setDecided(true);
		}
				
		return overallRes;
	}
	
	
	
	static Double allper;

	
public VerificationResult[] classifyItems(Classifier[] classifiers,Instances[] testingSets, String StoreID) throws Exception {
		
		int sizeTest = testingSets[0].size();
		
		VerificationResult[] finalPredictions = new VerificationResult[sizeTest];
		VerificationResult[] predicted = new VerificationResult[classifiers.length];
		
		int count = 0,countFake=0,countReal=0, fake=0, real=0;
		String actual = "";
		
		HashMap<String,String> idsLabels = new HashMap<String, String>();
		List<Double> listScores = new ArrayList<Double>();
		List<String> listPred = new ArrayList<String>();
		
		String id="";
		for (int j=0; j<sizeTest; j++) {
			for (int i=0; i<testingSets.length; i++) {			
				
				VerificationResult vr = new VerificationResult();
				double pred = classifiers[i].classifyInstance(testingSets[i].instance(j));				
				actual = testingSets[i].classAttribute().value((int) testingSets[i].instance(j).classValue());
				String predictedd = testingSets[i].instance(j).classAttribute().value((int) pred);
				
				id = testingSets[i].instance(j).stringValue(0);	
				double[] probDistr = classifiers[i].distributionForInstance(testingSets[i].instance(j));
				vr.setId(id);
				vr.setPrediction(predictedd);
				vr.setProb(probDistr[(int)pred]);
				
				predicted[i] = vr;
				
				if (predictedd.equals("fake")) {
					listScores.add(probDistr[(int)pred]);
					listPred.add("fake");
				}
				//else if real add the (1 - prob(real)) = prob(fake) to the sum
				else {
					listScores.add(1 - probDistr[(int)pred]);
					listPred.add("real");
				}	
				
			}
			
			
			VerificationResult predict = getMajorityPred(predicted, StoreID);
			predict.setId(id);
			idsLabels.put(id, predict.getPrediction());
			predict.setActual(actual);
			finalPredictions[j] = predict;
			
			if (predict.getPrediction().equals(actual)) {
				count++;
						
			}
			if (actual.equals("fake") && actual.equals(predict.getPrediction())) {
				countFake++;
			} else if (actual.equals("real") && actual.equals(predict.getPrediction())) {
				countReal++;
			}
			
			if (actual.equals("fake")) fake++;
			else real++;
			
			if (DoubleVerifyBagging.getVerbose()){
					
					// ID	ACTUAL	PREDICTED	PREDICTED_PER_MODEL		AVERAGE_SCORE 	SCORE_PER_MODEL
					if (predict.getPrediction().equals("fake")) {
						FileManager.getInstance().writePlainDataToFile(predict.getId()+"\t"+ 
								actual + "\t" + 
								predict.getPrediction() + "\t" +
								StringUtils.join(listPred, " ") + "\t" +					
								(predict.getProb()) + "\t" +
								StringUtils.join(listScores, " "), 
								DoubleVerifyBagging.getOutputFolderPerRun() +  StoreID + "Predictions.txt");
					}else{
						FileManager.getInstance().writePlainDataToFile(predict.getId()+"\t"+ 
								actual + "\t" + 
								predict.getPrediction() + "\t" +
								StringUtils.join(listPred, " ") + "\t" +					
								(1 - predict.getProb()) + "\t" +
								StringUtils.join(listScores, " "), 
								DoubleVerifyBagging.getOutputFolderPerRun() +  StoreID + "Predictions.txt");
			
					}
			}
			listScores.clear();
			listPred.clear();
		}
			
		
		
		System.out.println("Total items " + sizeTest+" fake: "+fake+" real:"+real);
		System.out.println("Items classified correctly:" + count);
		System.out.println("Accuracy "+  (double)count/testingSets[0].size() * 100 );
		System.out.println("Fake items classified correctly: " + countFake	+ ". Percentage: " + ((double) countFake / fake)* 100);
		System.out.println("Real items classified correctly: " + countReal	+ ". Percentage: " + ((double) countReal / real)* 100);
		System.out.println("Percentage " + ((double) count / sizeTest) * 100);
		
		allper = ((double) count / sizeTest) * 100;
	
		
		if (DoubleVerifyBagging.getVerbose()){
			FileManager.getInstance().writePlainDataToFile("Items classified correctly: " + StoreID + " INSTANCE:", DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
			FileManager.getInstance().writePlainDataToFile("Fake items classified correctly " + countFake + " Percentage " + ((double) countFake / fake)* 100,  DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
			FileManager.getInstance().writePlainDataToFile("Real items classified correctly " + countReal + " Percentage " + ((double) countReal / real)* 100 ,  DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");
			FileManager.getInstance().writePlainDataToFile("Accuracy " +  (double)count/testingSets[0].size() * 100,  DoubleVerifyBagging.getOutputFolderPerRun() + "Results.txt");

		}
		
		if (StoreID.equalsIgnoreCase("CL1") || StoreID.equalsIgnoreCase("CL2")){
			FileManager.getInstance().writePlainDataToFileNonl((double)count/testingSets[0].size() * 100 + "\t",
				DoubleVerifyBagging.getOutputFolder() + "AverageResultsPerRun.txt");
		}
		

		
		return finalPredictions;
	}
	

}
