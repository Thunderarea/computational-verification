package gr.iti.mklab.verify;

import gr.iti.mklab.extractfeatures.ItemFeatures;
import gr.iti.mklab.extractfeatures.ItemFeaturesAnnotation;
import gr.iti.mklab.extractfeatures.UserFeatures;
import gr.iti.mklab.extractfeatures.UserFeaturesAnnotation;
import gr.iti.mklab.utils.FileManager;
import gr.iti.mklab.verifyutils.AttributeSelectionHandler;
import gr.iti.mklab.verifyutils.DataHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Classifier;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.MultiFilter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.filters.unsupervised.attribute.Normalize;
import weka.filters.unsupervised.attribute.Remove;

public class Bagging {
	
	static List<ItemFeatures> itemfeatsfake = new ArrayList <ItemFeatures>();
	static List<ItemFeatures> itemfeatsreal = new ArrayList <ItemFeatures>();
	static List<UserFeatures> userfeatsfake = new ArrayList <UserFeatures>();
	static List<UserFeatures> userfeatsreal = new ArrayList <UserFeatures>();
	static List<ItemFeaturesAnnotation> itemFeaturesAnnot  = new ArrayList<ItemFeaturesAnnotation>();
	static List<ItemFeaturesAnnotation> itemFeaturesAnnot2 = new ArrayList<ItemFeaturesAnnotation>();
	static List<UserFeaturesAnnotation> userFeaturesAnnot  = new ArrayList<UserFeaturesAnnotation>();
	static List<UserFeaturesAnnotation> userFeaturesAnnot2 = new ArrayList<UserFeaturesAnnotation>();
	
	static Normalize normFilter = new Normalize();
	static Normalize normFilterUser = new Normalize();
	static HashMap<Double,List<Double>> ranksItem = new HashMap<Double,List<Double>>();
	static HashMap<Double,List<Double>> ranksUser = new HashMap<Double,List<Double>>();

	public static HashMap<Double,List<Double>> getRankedItemMap() {
		return ranksItem;
	}
	public static HashMap<Double,List<Double>> getRankedUserMap() {
		return ranksUser;
	}
	


	public static Instances[] getInitialSplit() {
		
		Collections.shuffle(itemfeatsfake, new Random(74));
		Collections.shuffle(itemfeatsreal, new Random(15));
		
		int testingSize = 1822; //1200 fake and 1200 real
	
		
		List<ItemFeatures> training = new ArrayList<ItemFeatures>();
		List<ItemFeatures> testing  = new ArrayList<ItemFeatures>();
		
		//create testing set
		for (int i=0; i<testingSize; i++) {
			ItemFeaturesAnnotation itemAnnot = new ItemFeaturesAnnotation();
			itemAnnot.setId(itemfeatsfake.get(i).getId());
			itemAnnot.setReliability("fake");
			itemFeaturesAnnot.add(itemAnnot);
			testing.add(itemfeatsfake.get(i));
		}
		for (int i=0; i<testingSize; i++) {
			ItemFeaturesAnnotation itemAnnot = new ItemFeaturesAnnotation();
			itemAnnot.setId(itemfeatsreal.get(i).getId());
			itemAnnot.setReliability("real");
			itemFeaturesAnnot.add(itemAnnot);
			testing.add(itemfeatsreal.get(i));
		}
		
		for (int i=0; i<itemfeatsfake.size()-testingSize; i++) {
			ItemFeaturesAnnotation itemAnnot = new ItemFeaturesAnnotation();
			itemAnnot.setId(itemfeatsfake.get(i).getId());
			itemAnnot.setReliability("fake");
			itemFeaturesAnnot2.add(itemAnnot);
			training.add(itemfeatsfake.get(i));
		}
		
		for (int i=0; i<itemfeatsreal.size() - testingSize; i++) {
			ItemFeaturesAnnotation itemAnnot = new ItemFeaturesAnnotation();
			itemAnnot.setId(itemfeatsreal.get(i).getId());
			itemAnnot.setReliability("real");
			itemFeaturesAnnot2.add(itemAnnot);
			training.add(itemfeatsreal.get(i));
		}
		
		Instances[] insts = new Instances[2];
		try {
			
			Instances trainingSet = ItemClassifier.createTrainingSet(training, itemFeaturesAnnot2);
			Instances testingSet  = ItemClassifier.createTestingSet(testing, itemFeaturesAnnot);
						
			insts[0] = trainingSet;
			insts[1] = testingSet;
			
			System.out.println(trainingSet.size());
			System.out.println(testingSet.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return insts;
	}
	
	public static Instances[] getInitialSplitUser() {
		
		Collections.shuffle(userfeatsfake, new Random(89));
		Collections.shuffle(userfeatsreal, new Random(105));
		
		int testingSize = 1200; //1200 fake and 1200 real
	
		
		List<UserFeatures> training = new ArrayList<UserFeatures>();
		List<UserFeatures> testing  = new ArrayList<UserFeatures>();
		
		//create testing set
		for (int i=0; i<testingSize; i++) {
			UserFeaturesAnnotation itemAnnot = new UserFeaturesAnnotation();
			itemAnnot.setId(userfeatsfake.get(i).getId());
			itemAnnot.setReliability("fake");
			userFeaturesAnnot.add(itemAnnot);
			testing.add(userfeatsfake.get(i));
		}
		for (int i=0; i<testingSize; i++) {
			UserFeaturesAnnotation itemAnnot = new UserFeaturesAnnotation();
			itemAnnot.setId(userfeatsreal.get(i).getId());
			itemAnnot.setReliability("real");
			userFeaturesAnnot.add(itemAnnot);
			testing.add(userfeatsreal.get(i));
		}
		
		
		for (int i=0; i<userfeatsfake.size()-testingSize; i++) {
			UserFeaturesAnnotation itemAnnot = new UserFeaturesAnnotation();
			itemAnnot.setId(userfeatsfake.get(i).getId());
			itemAnnot.setReliability("fake");
			userFeaturesAnnot2.add(itemAnnot);
			training.add(userfeatsfake.get(i));
		}
		
		for (int i=0; i<userfeatsreal.size() - testingSize; i++) {
			UserFeaturesAnnotation itemAnnot = new UserFeaturesAnnotation();
			itemAnnot.setId(userfeatsreal.get(i).getId());
			itemAnnot.setReliability("real");
			userFeaturesAnnot2.add(itemAnnot);
			training.add(userfeatsreal.get(i));
		}
		
		Instances[] insts = new Instances[2];
		try {
			
			Instances trainingSet = UserClassifier.createTrainingSet(training, userFeaturesAnnot2);
			Instances testingSet  = UserClassifier.createTestingSet(testing, userFeaturesAnnot);
						
			insts[0] = trainingSet;
			insts[1] = testingSet;
			
			System.out.println(trainingSet.size());
			System.out.println(testingSet.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return insts;
	}
	
	
	
	static Instances[] testingSets = new Instances[5];
	
	public static Instances[] getTestingSets() {
		return testingSets;
	}
	
	public static void setTestingSets(Instances[] testingSets) {
		Bagging.testingSets = testingSets;
	}
	
	public static ArrayList<int[]> initializeRandomVals() {
		
		ArrayList<int[]> random = new ArrayList<int[]>();
		
		//1st
		random.add(new int[] {6,7,8,90,32});
		/*random.add(new int[] {56,38,58,42,59,65,71,18,19});
		random.add(new int[] {33,46,11,88,99,27,35,29,20});
		random.add(new int[] {10,55,13,81,199,127,235,329,420});
		random.add(new int[] {28,29,30,31,39,327,335,429,520});
		random.add(new int[] {1,2,3,4,55,55,6,77,66});
		random.add(new int[] {51,62,333,334,955,255,36,877,626});
		random.add(new int[] {27,227,123,234,135,147,336,890,891});
		random.add(new int[] {9,10,11,22,783,472,90,91,99});
		random.add(new int[] {31,34,67,88,89,908,869,245,12});*/
		
		//2nd
		/*random.add(new int[] {6,7,8,90,32,334,777,188,149});
		//random.add(new int[] {5,9,77,2,19,6,71,81,98});
		//random.add(new int[] {56,38,58,42,59,65,71,18,19});
		random.add(new int[] {33,46,11,88,99,27,35,29,20});
		random.add(new int[] {10,55,13,81,199,127,235,329,420});
		//random.add(new int[] {28,29,30,31,39,327,335,429,520});
		//random.add(new int[] {1,2,3,4,55,55,6,77,66});
		random.add(new int[] {51,62,333,334,955,255,36,877,626});
		//random.add(new int[] {27,227,123,234,135,147,336,890,891});
		//random.add(new int[] {9,10,11,22,783,472,90,91,99});
		random.add(new int[] {31,34,67,88,89,908,869,245,12});
		//random.add(new int[] {1,4,47,818,819,9081,8691,2451,121});
		random.add(new int[] {21,24,427,8128,8192,90821,86921,24251,1221});
		//random.add(new int[] {24,12,4,82,819,982,892,225,12});
*/		
		return random;
	}
	
	static int[] randomVals = new int[10];
	
	
	//static int trainingSize = 3174;
	
	public Classifier[] createClassifiers(Instances training2, Instances testing2, int trainingSize) throws Exception {
		
		Instances training = new Instances(training2);
		Instances testing  = new Instances(testing2);
		
		int countFake=0, countReal=0;
		Classifier[] classifiers = new Classifier[randomVals.length];
		
		//System.out.println("CREATE CLASSIFIERS: ITEM");
		//System.out.println("TRAINING SIZE/2 = "+ training.size()/2);
		
		for (int j=0; j<randomVals.length; j++) {
			
			Instances currentTrain = new Instances("rel", ItemClassifier.getFvAttributes(), trainingSize);
			
			Collections.shuffle(training, new Random(randomVals[j]));
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
			//System.out.println("fake "+countFake+" real "+countReal+" all "+currentTrain.size());
					
		
			
			//Linear Regression approach
			currentTrain = DataHandler.getInstance().getTransformedTrainingOverall(currentTrain);				
			testingSets[j] = DataHandler.getInstance().getTransformedTestingOverall(testing);

			
			
			//ReplaceMissingValues approach
			//if (tree instanceof LibSVM) {
			/*ReplaceMissingValues replace = new ReplaceMissingValues();
			replace.setInputFormat(currentTrain);
			currentTrain = DataHandler.getInstance().replaceMissingValues(currentTrain, replace);
			testingSets[j] = DataHandler.getInstance().replaceMissingValues(testingSets[j],replace);*/
			//}
			
			//classifier details
			FilteredClassifier fc = new FilteredClassifier();
			AgreementBasedRetraining dvb = new AgreementBasedRetraining();
			Classifier tree = dvb.getCurrentClassifier();
			Remove rm = new Remove();
			rm.setAttributeIndices("1");
						

			String sb="";
			MultiFilter mf = new MultiFilter();
			
			try {
				fc.setFilter(rm);
				fc.setClassifier(tree);				
				fc.buildClassifier(currentTrain);
				classifiers[j] = fc;
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return classifiers;
	}
	
	
	
	static Instances[] testingSetsUser = new Instances[5];
	
	public static Instances[] getTestingSetsUser() {
		return testingSetsUser;
	}
	
	public static void setTestingSetsUser(Instances[] testingSetsUser) {
		Bagging.testingSetsUser = testingSetsUser;
	}
	
	static int overall = 0;
	public Classifier[] createClassifiersUser(Instances training2, Instances testing2, int trainingSize) throws Exception {
		
		Instances training = new Instances(training2);
		Instances testing  = new Instances(testing2);
		
		int countFake=0, countReal=0;
		Classifier[] classifiers = new Classifier[randomVals.length];
		
		//System.out.println("CREATE CLASSIFIERS: USER");
		
		for (int j=0; j<randomVals.length; j++) {
			
			Instances currentTrain = new Instances("rel", UserClassifier.getFvAttributes(), trainingSize);
			Collections.shuffle(training, new Random(randomVals[j]));
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
			//System.out.println("training size "+training.size());
			//System.out.println(trainingSize+" "+countFake);
			
			for (int i=0; i<training.size(); i++) {
				if ( countReal < (trainingSize/2) ) {
					if (training.classAttribute().value((int) training.get(i).classValue()).equals("real")) {
						currentTrain.add(training.get(i));
						countReal++;
					}
				}
			}
			//System.out.println("fake "+countFake+" real "+countReal+" all "+currentTrain.size());
			
			//Linear Regression approach
			currentTrain = DataHandler.getInstance().getTransformedTrainingUserOverall(currentTrain);
			testingSetsUser[j] = DataHandler.getInstance().getTransformedTestingUserOverall(testing);
			
				
			
			
			//classifier details
			FilteredClassifier fc = new FilteredClassifier();
			AgreementBasedRetraining dvb = new AgreementBasedRetraining();
			Classifier tree = dvb.getCurrentClassifier();
			Remove rm = new Remove();
			rm.setAttributeIndices("1");
							
			
			try {
				fc.setClassifier(tree);
				fc.setFilter(rm);				
				fc.buildClassifier(currentTrain);
				classifiers[j] = fc;
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return classifiers;
	}
	
	public static VerificationResult classifyItem(Classifier cls,Instance inst) {
		
		
		String predicted = null;
		VerificationResult prediction = new VerificationResult();
		double pred;
		try {
			//classify the instance
			pred = cls.classifyInstance(inst);
			//get the distribution for this instance
			double[] probDistr = cls.distributionForInstance(inst);
			//get the probability for the predicted class
			double prob = probDistr[(int)pred];
			//get the class predicted in String format
			predicted = inst.classAttribute().value((int) pred);	
			
			prediction.setPrediction(predicted);
			prediction.setProb(prob);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return prediction;
	}
	
	/*public static String classifyItem(Classifier cls,Instance inst) {
		
		String predicted = null;
		
		double pred;
		try {
			pred = cls.classifyInstance(inst);
			predicted = inst.classAttribute().value((int) pred);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return predicted;
	}*/
	
	
	
	/*public static VerificationResult getMajorityPred(VerificationResult[] predicted) {
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		for (int i=0;i<predicted.length;i++) {
			String prd = predicted[i].getPrediction();
			if (map.get(prd) == null) {
				map.put(prd,1);
			}
			else {
				map.put(prd, map.get(prd)+1);
			}
		}
		
		//System.out.println("Classifiers' decision: "+map);
		
		String resultStr = "";
		if (map.get("fake") == null) resultStr = "real";
		if (map.get("real") == null) resultStr = "fake";
		
		if (resultStr.equals("")) {
			if (map.get("fake") > map.get("real")) {
				resultStr = "fake";
			}
			else {
				resultStr = "real";
			}
		}
		
		double sum = 0.0;
		int counter = 0;
		for (int i=0; i<predicted.length; i++) {
			if (predicted[i].getPrediction().equals(resultStr)) {
				sum+=predicted[i].getProb();
				counter++;
			}
		}
		VerificationResult overallRes = new VerificationResult();
		overallRes.setPrediction(resultStr);
		overallRes.setProb(sum/counter);
		
		return overallRes;
	}*/
	
	
	public static VerificationResult getMajorityPred(VerificationResult[] predicted) {
		
		double sumScores = 0.0;
		
		for (int j=0; j<predicted.length; j++) {
			//System.out.println("id "+predicted[j].getPrediction() + " " + predicted[j].getProb());
			//if fake add the probability of being fake prob(fake) to the sum 
			if (predicted[j].getPrediction().equals("fake")) {
				sumScores += predicted[j].getProb();
			}
			//else if real add the (1 - prob(real)) = prob(fake) to the sum
			else {
				sumScores += (1 - predicted[j].getProb());
			}
		}
		
		double avgProb = sumScores / predicted.length;
		
		
		VerificationResult overallRes = new VerificationResult();
		if (avgProb < 0.5) {
			overallRes.setPrediction("real");
			overallRes.setProb(1-avgProb);
		}
		else {
			overallRes.setPrediction("fake");
			overallRes.setProb(avgProb);
		}
		
		//System.out.println(overallRes.getPrediction() + " " + overallRes.getProb());
		
		
		return overallRes;
	}
	
	
	static int internal = 0;
	static Double allper;
	
	public static VerificationResult[] classifyItems(Classifier[] classifiers,Instances[] testingSets) {
		
		int sizeTest = testingSets[0].size();
		VerificationResult[] finalPredictions = new VerificationResult[sizeTest];
		VerificationResult[] predicted = new VerificationResult[classifiers.length];
		int count = 0,countFake=0,countReal=0, fake=0, real=0;
		String actual = "";
		
		HashMap<String,String> idsLabels = new HashMap<String, String>();
		
		String id="";
		for (int j=0; j<sizeTest; j++) {
			
			for (int i=0; i<testingSets.length; i++) {
				//System.out.println("instance "+testingSets[i].get(j));
				predicted[i] = classifyItem(classifiers[i], testingSets[i].get(j));
				actual = testingSets[i].classAttribute().value((int) testingSets[i].get(j).classValue());
				id = testingSets[i].get(j).stringValue(0);
			}
			
			
			VerificationResult predict = getMajorityPred(predicted);
			predict.setId(id);
			idsLabels.put(id, predict.getPrediction());
			
			finalPredictions[j] = predict;
			//System.out.println("actual "+actual);
			//System.out.println("prediction "+predict);
			if (predict.getPrediction().equals(actual)) {
				count++;
			}
			if (actual == "fake" && actual.equals(predict.getPrediction())) {
				countFake++;
			} else if (actual == "real" && actual.equals(predict.getPrediction())) {
				countReal++;
			}
			if (actual == "fake") fake++;
			else real++;
		}
			
		System.out.println("Total items " + sizeTest);
		System.out.println("Items classified correctly:" + count);
		System.out.println("Fake items classified correctly: " + countFake	+ ". Percentage: " + ((double) countFake / fake)* 100);
		System.out.println("Real items classified correctly: " + countReal	+ ". Percentage: " + ((double) countReal / real)* 100);
		System.out.println("Percentage " + ((double) count / sizeTest) * 100);
		
		Double fakeper = ((double) countFake / fake)* 100;
		Double realper =  ((double) countReal / real)* 100;
		allper = ((double) count / sizeTest) * 100;
		
		
		return finalPredictions;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		
		
	}

}
