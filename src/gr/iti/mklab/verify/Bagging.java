package gr.iti.mklab.verify;

import gr.iti.mklab.verifyutils.DataHandler;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;

public class Bagging {
	
	static int[] randomVals = new int[7];
	static Instances[] testingSets = new Instances[randomVals.length];
	static Instances[] testingSetsUser = new Instances[randomVals.length];

	
	public static Instances[] getTestingSets() {
		return testingSets;
	}
	public static void setTestingSets(Instances[] testingSets) {
		Bagging.testingSets = testingSets;
	}	
	public static Instances[] getTestingSetsUser() {
		return testingSetsUser;
	}
	public static void setTestingSetsUser(Instances[] testingSetsUser) {
		Bagging.testingSetsUser = testingSetsUser;
	}
	public static int[] initializeRandomVals() {
		
		//int[] random = new int[] {6,7,8,90,32,34,56,9,10};
		int[] random = new int[] {6,7,8,90,32,34,56};
			
		return random;
	}
	
	/*public static void generateTrainingSetsGroupsTweet(Instances training2, int trainingSize) throws Exception {
		
		Instances training = new Instances(training2);
		int countFake=0;
		int countReal=0;
		
		for (int j=0; j<randomVals.length; j++) {
			
			Instances currentTrain = new Instances("rel", ItemClassifier.getFvAttributes(), trainingSize);
			
			
			Collections.shuffle(training, new Random(randomVals[j]));
			countFake = 0; 
			countReal = 0;
			
			
			for (int i=0; i<training.size(); i++) {
				if ( countFake < (trainingSize >> 1) ) {
					if (training.classAttribute().value((int) training.get(i).classValue()).equals("fake")) {
						currentTrain.add(training.get(i));
						countFake++;
					}
				}
							
			}

			for (int i=0; i<training.size(); i++) {
				if ( countReal < (trainingSize >> 1) ) {
					if (training.classAttribute().value((int) training.get(i).classValue()).equals("real")) {
						currentTrain.add(training.get(i));
						countReal++;
					}
				}
			}
		
			//System.out.println(countFake+" "+countReal);
			currentTrainInstancesTweet[j] = currentTrain;	
			//System.out.println(currentTrainInstancesTweet[j].size());
			//System.out.println(currentTrainInstancesTweet[j].get(0));
			
		}
		
	}*/
	static public int counter = 0;
	public void generateRespectiveTestingSetsTweet(Instances testing2) throws Exception {
				
		Instances testing  = new Instances(testing2);
		
		for (int j=0; j<randomVals.length; j++) {
			counter = j;
			//Linear Regression approach
			//currentTrainInstancesTweet[j] = DataHandler.getInstance().getTransformedTrainingOverall(currentTrainInstancesTweet[j]);				
			testingSets[j] = DataHandler.getInstance().getTransformedTestingOverall(testing);
			
			
			//System.out.println("currentTrainInstancesTweet: "+currentTrainInstancesTweet[j].size());
			//System.out.println(currentTrainInstancesTweet[j].get(0));
			
			/*FilteredClassifier fc = new FilteredClassifier();
			Remove rm = new Remove();
			rm.setAttributeIndices("1");
			fc.setFilter(rm);
			fc.setClassifier(new RandomForest());
			fc.buildClassifier(currentTrainInstancesTweet[j]);
			weka.core.SerializationHelper.write("resources/models/tweetModel"+j+".model", fc);
			AgreementBasedClassification.classifiersTweet[j] = fc;*/
		}
		
	}
	
	
	/*public static void generateTrainingSetsGroupsUser(Instances training2, int trainingSize) {
		
		Instances training = new Instances(training2);
		int countFake=0;
		int countReal=0;
		
		for (int j=0; j<randomVals.length; j++) {
			
			Instances currentTrain = new Instances("rel", UserClassifier.getFvAttributes(), trainingSize);
			Collections.shuffle(training, new Random(randomVals[j]));
			countFake = 0; 
			countReal = 0;
			
			for (int i=0; i<training.size(); i++) {
				if (countFake < (trainingSize >> 1)) {
					if (training.classAttribute().value((int) training.get(i).classValue()).equals("fake")) {
						countFake++;
						currentTrain.add(training.get(i));
					}
				}
							
			}
						
			for (int i=0; i<training.size(); i++) {
				if ( countReal < (trainingSize >> 1) ) {
					if (training.classAttribute().value((int) training.get(i).classValue()).equals("real")) {
						currentTrain.add(training.get(i));
						countReal++;
					}
				}
			}
			//System.out.println("fake "+countFake+" real "+countReal+" all "+currentTrain.size());
			
			currentTrainInstancesUser[j] = currentTrain;
			//System.out.println(currentTrainInstancesUser[j].size());
			//System.out.println(currentTrainInstancesUser[j].get(10));
		}

	}*/
	
	
	public void generateRespectiveTestingSetsUser(Instances testing2) throws Exception {
		
		Instances testing  = new Instances(testing2);
			
		for (int j=0; j<randomVals.length; j++) {
			
			counter = j;
			//Linear Regression approach
			//long st = System.currentTimeMillis();
			//currentTrainInstancesUser[j] = DataHandler.getInstance().getTransformedTrainingUserOverall(currentTrainInstancesUser[j]);
			//long end = System.currentTimeMillis();
			testingSetsUser[j] = DataHandler.getInstance().getTransformedTestingUserOverall(testing);
			
			/*FilteredClassifier fc = new FilteredClassifier();
			Remove rm = new Remove();
			rm.setAttributeIndices("1");
			fc.setFilter(rm);
			fc.setClassifier(new RandomForest());
			System.out.println("currentTrainInstancesUser: "+ currentTrainInstancesUser[j].size());
			fc.buildClassifier(currentTrainInstancesUser[j]);
			weka.core.SerializationHelper.write("resources/models/userModel"+j+".model", fc);
			AgreementBasedClassification.classifiersUser[j] = fc;*/
			
			//System.out.println("transform "+(double)(end-st)/1000+"sec");
		}
		
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
	
	
	
	public static VerificationResult classifyItems(Classifier[] classifiers,Instances[] testingSets) {
		
		VerificationResult finalPrediction = new VerificationResult();
		VerificationResult[] predicted = new VerificationResult[classifiers.length];
		
		
		String id="";
		//for (int j=0; j<sizeTest; j++) {
			
			for (int i=0; i<testingSets.length; i++) {
				//System.out.println("instance "+testingSets[i].get(j));
				predicted[i] = classifyItem(classifiers[i], testingSets[i].get(0));
				//actual = testingSets[i].classAttribute().value((int) testingSets[i].get(j).classValue());
				id = testingSets[i].get(0).stringValue(0);
			}
			
			
			finalPrediction = getMajorityPred(predicted);
			finalPrediction.setId(id);
			
		
		return finalPrediction;
	}
	
	

}
