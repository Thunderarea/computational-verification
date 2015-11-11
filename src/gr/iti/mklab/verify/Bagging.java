package gr.iti.mklab.verify;

import gr.iti.mklab.extractfeatures.ItemFeatures;
import gr.iti.mklab.extractfeatures.ItemFeaturesAnnotation;
import gr.iti.mklab.extractfeatures.UserFeatures;
import gr.iti.mklab.extractfeatures.UserFeaturesAnnotation;
import gr.iti.mklab.verifyutils.DataHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instance;
import weka.core.Instances;
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
	


	
	static Instances[] testingSets = new Instances[5];
	
	public static Instances[] getTestingSets() {
		return testingSets;
	}
	
	public static void setTestingSets(Instances[] testingSets) {
		Bagging.testingSets = testingSets;
	}
	
	public static int[] initializeRandomVals() {
		
		int[] random = new int[] {6,7,8,90,32};
			
		return random;
	}
	
	static int[] randomVals = new int[10];
	
	
	//static int trainingSize = 3174;
	
	public Classifier[] createClassifiers(Instances training2, Instances testing2, int trainingSize) throws Exception {
		
		Bagging.randomVals = Bagging.initializeRandomVals();
		
		Instances training = new Instances(training2);
		Instances testing  = new Instances(testing2);
		
		int countFake=0, countReal=0;
		Classifier[] classifiers = new Classifier[randomVals.length];
		
		
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
		
			//Linear Regression approach
			currentTrain = DataHandler.getInstance().getTransformedTrainingOverall(currentTrain);				
			testingSets[j] = DataHandler.getInstance().getTransformedTestingOverall(testing);

			
		
			
			//classifier details
			FilteredClassifier fc = new FilteredClassifier();
			AgreementBasedClassification dvb = new AgreementBasedClassification();
			Classifier tree = dvb.getCurrentClassifier();
			Remove rm = new Remove();
			rm.setAttributeIndices("1");
						

			
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
	
	public Classifier[] createClassifiersUser(Instances training2, Instances testing2, int trainingSize) throws Exception {
		
		Bagging.randomVals = Bagging.initializeRandomVals();
		
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
			AgreementBasedClassification dvb = new AgreementBasedClassification();
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
	

	
	public static VerificationResult getMajorityPred(VerificationResult[] predicted) {
		
		double sumScores = 0.0;
		
		for (int j=0; j<predicted.length; j++) {
			
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
			
		
		return overallRes;
	}
	
	
	
	public static VerificationResult[] classifyItems(Classifier[] classifiers,Instances[] testingSets) {
		
		int sizeTest = testingSets[0].size();
		VerificationResult[] finalPredictions = new VerificationResult[sizeTest];
		VerificationResult[] predicted = new VerificationResult[classifiers.length];
		
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
		
			/*//System.out.println("actual "+actual);
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
		*/
		}
		
		/*System.out.println("Total items " + sizeTest);
		System.out.println("Items classified correctly:" + count);
		System.out.println("Fake items classified correctly: " + countFake	+ ". Percentage: " + ((double) countFake / fake)* 100);
		System.out.println("Real items classified correctly: " + countReal	+ ". Percentage: " + ((double) countReal / real)* 100);
		System.out.println("Percentage " + ((double) count / sizeTest) * 100);*/
		
		return finalPredictions;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		
		
	}

}
