package gr.iti.mklab.verify;

import gr.iti.mklab.extractfeatures.UserFeatures;
import gr.iti.mklab.extractfeatures.UserFeaturesAnnotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class UserClassifier {

		
	
	private ArrayList<Attribute> fvAttributes = new ArrayList<Attribute>();
	
	public UserClassifier() {
		setFvAttributes(declareAttributes());
	}

	public void setFvAttributes(ArrayList<Attribute> list) {
		this.fvAttributes = list;
	}
	
	public ArrayList<Attribute> getFvAttributes() {
		return fvAttributes;
	}
	
	private ArrayList<Attribute> declareAttributes() {

		// numeric attributes
		Attribute numFriends = new Attribute("numFriends");
		Attribute numFollowers = new Attribute("numFollowers");
		Attribute FolFrieRatio = new Attribute("FolFrieRatio");
		Attribute timesListed = new Attribute("timesListed");
		Attribute numTweets = new Attribute("numTweets");
		Attribute numMediaContent = new Attribute("numMediaContent");
		Attribute wotTrustUser = new Attribute("wotTrustUser");
		// Attribute wotSafeUser = new Attribute("wotSafeUser");
		Attribute accountAge = new Attribute("accountAge");
		Attribute tweetRatio = new Attribute("tweetRatio");
		Attribute indegreeUser = new Attribute("indegreeUser");
		Attribute harmonicUser = new Attribute("harmonicUser");
		Attribute alexaCountryRankUser = new Attribute("alexaCountryRankUser");
		Attribute alexaDeltaRankUser = new Attribute("alexaDeltaRankUser");
		Attribute alexaPopularityUser = new Attribute("alexaPopularityUser");
		Attribute alexaReachRankUser = new Attribute("alexaReachRankUser");

		// declare nominal attributes
		List<String> fvnominal1 = new ArrayList<String>(2);
		fvnominal1.add("true");
		fvnominal1.add("false");
		Attribute hasUrl = new Attribute("hasUrl", fvnominal1);
		// Attribute hasUrl = new Attribute("hasUrl");	

		// declare nominal attributes
		List<String> fvnominal2 = new ArrayList<String>(2);
		fvnominal2.add("true");
		fvnominal2.add("false");
		Attribute isVerified = new Attribute("isVerified", fvnominal2);
		// Attribute isVerified = new Attribute("isVerified");

		// declare nominal attributes
		List<String> fvnominal3 = new ArrayList<String>(2);
		fvnominal3.add("true");
		fvnominal3.add("false");
		Attribute hasBio = new Attribute("hasBio", fvnominal3);
		// Attribute hasBio = new Attribute("hasBio");

		// declare nominal attributes
		List<String> fvnominal4 = new ArrayList<String>(2);
		fvnominal4.add("true");
		fvnominal4.add("false");
		Attribute hasLocation = new Attribute("hasLocation", fvnominal4);
		// Attribute hasLocation = new Attribute("hasLocation");

		// declare nominal attributes
		List<String> fvnominal5 = new ArrayList<String>(2);
		fvnominal5.add("true");
		fvnominal5.add("false");
		Attribute hasExistingLocation = new Attribute("hasExistingLocation",
				fvnominal5);
		// Attribute hasExistingLocation = new Attribute("hasExistingLocation");

		List<String> fvnominal6 = new ArrayList<String>(2);
		fvnominal6.add("true");
		fvnominal6.add("false");
		Attribute hasProfileImg = new Attribute("hasProfileImg", fvnominal6);
		// Attribute hasProfileImg = new Attribute("hasProfileImg");

		List<String> fvnominal7 = new ArrayList<String>(2);
		fvnominal7.add("true");
		fvnominal7.add("false");
		Attribute hasHeaderImg = new Attribute("hasHeaderImg", fvnominal7);
		// Attribute hasHeaderImg = new Attribute("hasHeaderImg");

		List<String> fvnominal8 = null;
		Attribute id = new Attribute("id", fvnominal8);

		List<String> fvClass = new ArrayList<String>(2);
		fvClass.add("real");
		fvClass.add("fake");
		Attribute ClassAttribute = new Attribute("theClass", fvClass);
		// Attribute ClassAttribute = new Attribute("theClass");

		fvAttributes.add(id);

		fvAttributes.add(numFriends);
		fvAttributes.add(numFollowers);
		fvAttributes.add(FolFrieRatio);
		fvAttributes.add(timesListed);
		fvAttributes.add(hasUrl);
		fvAttributes.add(isVerified);
		fvAttributes.add(numTweets);
		fvAttributes.add(hasBio);
		fvAttributes.add(hasLocation);
		fvAttributes.add(hasExistingLocation);
		fvAttributes.add(wotTrustUser);
		fvAttributes.add(numMediaContent);
		fvAttributes.add(accountAge);
		fvAttributes.add(hasProfileImg);
		fvAttributes.add(hasHeaderImg);
		fvAttributes.add(tweetRatio);
		fvAttributes.add(indegreeUser);
		fvAttributes.add(harmonicUser);
		fvAttributes.add(alexaCountryRankUser); // new
		fvAttributes.add(alexaDeltaRankUser);
		fvAttributes.add(alexaPopularityUser);
		fvAttributes.add(alexaReachRankUser);

		fvAttributes.add(ClassAttribute);

		return fvAttributes;
	}



	public Instance createInstance(UserFeatures list) {
		//System.out.println(list.toString());
		Instance iExample = new DenseInstance(fvAttributes.size());

		String id = list.getId().replaceAll("[^\\d.]", "");
		//System.out.println(id);
		iExample.setValue((Attribute) fvAttributes.get(0), id);
		//System.out.println(list.getNumFriends());
		
		iExample.setValue((Attribute) fvAttributes.get(1), list.getNumFriends());
		iExample.setValue((Attribute) fvAttributes.get(2),
				list.getNumFollowers());
		iExample.setValue((Attribute) fvAttributes.get(3),
				list.getFolFrieRatio());
		iExample.setValue((Attribute) fvAttributes.get(4),
				list.getTimesListed());
		iExample.setValue((Attribute) fvAttributes.get(5), list.getHasURL()
				.toString());
		iExample.setValue((Attribute) fvAttributes.get(6), list.getIsVerified()
				.toString());
		iExample.setValue((Attribute) fvAttributes.get(7), list.getNumTweets());

		iExample.setValue((Attribute) fvAttributes.get(8), list.getHasBio()
				.toString());
		iExample.setValue((Attribute) fvAttributes.get(9), list
				.getHasLocation().toString());
		iExample.setValue((Attribute) fvAttributes.get(10), list
				.getHasExistingLocation().toString());

		if (list.getWotTrustUser() != null) {
			iExample.setValue((Attribute) fvAttributes.get(11),
					list.getWotTrustUser());
		}

		iExample.setValue((Attribute) fvAttributes.get(12),
				list.getNumMediaContent());

		if (list.getAccountAge() != null) {
			iExample.setValue((Attribute) fvAttributes.get(13),
					list.getAccountAge());
		}
		if (list.getHasProfileImg() != null) {
			iExample.setValue((Attribute) fvAttributes.get(14), list
					.getHasProfileImg().toString());
		}
		if (list.getHasHeaderImg() != null) {
			iExample.setValue((Attribute) fvAttributes.get(15), list
					.getHasHeaderImg().toString());
		}

		if (list.getTweetRatio() != null) {
			iExample.setValue((Attribute) fvAttributes.get(16),
					list.getTweetRatio());
		}
		if (list.getIndegree() != null) {
			iExample.setValue((Attribute) fvAttributes.get(17),
					list.getIndegree());
		}
		if (list.getHarmonic() != null) {
			iExample.setValue((Attribute) fvAttributes.get(18),
					list.getHarmonic());
		}
		if (list.getAlexaCountryRank() != null) {
			iExample.setValue((Attribute) fvAttributes.get(19),
					list.getAlexaCountryRank());
		}
		if (list.getAlexaDeltaRank() != null) {
			iExample.setValue((Attribute) fvAttributes.get(20),
					list.getAlexaDeltaRank());
		}
		if (list.getAlexaPopularity() != null) {
			iExample.setValue((Attribute) fvAttributes.get(21),
					list.getAlexaPopularity());
		}
		if (list.getAlexaReachRank() != null) {
			iExample.setValue((Attribute) fvAttributes.get(22),
					list.getAlexaReachRank());
		}
		
		/*if (list.getAnnotation() != null) {
			iExample.setValue((Attribute) fvAttributes.get(23),
					list.getAnnotation());
		}*/
		return iExample;
	}





	public Instances createTrainingSet(List<UserFeatures> list, List<FeaturesAnnotationItem> annotation) throws Exception {
		// create an empty training set
		Instances isTrainingSet = new Instances("Rel", fvAttributes, list.size());
		// Set class index
		isTrainingSet.setClassIndex(fvAttributes.size() - 1);
		
		//save the <id,label> pair to a map
		HashMap<String,String> map = new HashMap<String, String>();
		
		for (int j = 0; j < annotation.size(); j++) {
			
			map.put(annotation.get(j).getId(), annotation.get(j).getLabel());
		}
		
		for (int i = 0; i < list.size(); i++) {		
			Instance iExample = createInstance(list.get(i));
			iExample.setValue((Attribute) fvAttributes.get(fvAttributes.size() - 1), map.get(list.get(i).getId()) );

			isTrainingSet.add(iExample);
		}

		return isTrainingSet;
	}

	public Instances createTestingSet(List<UserFeatures> listTest, List<UserFeaturesAnnotation> userFeaturesAnnot) throws Exception {

		int index = 0;

		// create an empty training set
		Instances isTestingSet = new Instances("Rel", fvAttributes, listTest.size());
		// Set class index
		isTestingSet.setClassIndex(fvAttributes.size() - 1);

		//save the <id,label> pair to a map
		HashMap<String,String> map = new HashMap<String, String>();
		
		for (int j = 0; j < userFeaturesAnnot.size(); j++) {
			
			map.put(userFeaturesAnnot.get(j).getId(), userFeaturesAnnot.get(j).getReliability());
		}
		
		//iterate through list of UserFeatures
		for (int i = 0; i < listTest.size(); i++) {
			//create an Instance
			Instance iExample = createInstance(listTest.get(i));
			//find the reliability value of this feature from the map and put it to the Instance object just created
			iExample.setValue((Attribute) fvAttributes.get(fvAttributes.size() - 1), map.get(listTest.get(i).getId()) );
			//add the complete Instance to the Instances object
			isTestingSet.add(iExample);
		}

		return isTestingSet;
	}

	/*public void doClassification() throws Exception {

		// define the list of itemFeatures that are used for training and
		// testing
		List<UserFeatures> itemFeaturesTraining = new ArrayList<UserFeatures>();
		List<UserFeatures> itemFeaturesTesting = new ArrayList<UserFeatures>();
		// define the list of annotations of the items trained
		List<UserFeaturesAnnotation> itemFeaturesAnnot = new ArrayList<UserFeaturesAnnotation>();
		List<UserFeaturesAnnotation> itemFeaturesAnnot2 = new ArrayList<UserFeaturesAnnotation>();

		--------FAKE ITEMS--------------
		// make a list with the items retrieved from mongodb
		UserFeaturesExtractor ife = new UserFeaturesExtractor();
		List<UserFeatures> listFake = ife.getLatestItems("FeaturesObjects",
				"UserFeaturesFakes2", 0);

		// annotate and add to the itemFeaturesAnnot list
		// add item to the itemFeaturesTraining list
		int size = listFake.size();
		for (int i = 1500; i < 5011; i++) {
			UserFeaturesAnnotation itemAnnot = new UserFeaturesAnnotation();
			itemAnnot.setUsername(listFake.get(i).getUsername());
			itemAnnot.setReliability("fake");
			itemFeaturesAnnot.add(itemAnnot);
			itemFeaturesTraining.add(listFake.get(i));
		}
		System.out.println("Training size of fake items : "
				+ itemFeaturesTraining.size());

		UserFeaturesExtractor ifex = new UserFeaturesExtractor();
		List<UserFeatures> listFakeSusp = ifex.getLatestItems(
				"FeaturesObjects", "BostonUserNo", 0);
		for (int i = 0; i < listFakeSusp.size(); i++) {
			UserFeaturesAnnotation itemAnnot = new UserFeaturesAnnotation();
			itemAnnot.setUsername(listFakeSusp.get(i).getUsername());
			itemAnnot.setReliability("fake");
			itemFeaturesAnnot2.add(itemAnnot);
			itemFeaturesTesting.add(listFakeSusp.get(i));
		}
		System.out.println("Testing size of fake items : "
				+ itemFeaturesTesting.size());
		int realtest = itemFeaturesTesting.size();

		--------REAL ITEMS--------------
		// make a list with the items retrieved from mongodb
		UserFeaturesExtractor ife2 = new UserFeaturesExtractor();
		List<UserFeatures> listReal = ife2.getLatestItems("FeaturesObjects",
				"UserFeaturesReals2", 0); // getLatestItems:method implemented
											// on UserFeaturesExtractor to
											// extract ItemFeatures items from
											// mongodb

		// decide to shuffle or not to transform the fakeItems training set
		// Collections.shuffle(listReal);

		// annotate and add to the itemFeaturesAnnot list
		// add item to the itemFeaturesTraining list

		for (int i = 0; i < listReal.size(); i++) {
			UserFeaturesAnnotation itemAnnot = new UserFeaturesAnnotation();
			itemAnnot.setUsername(listReal.get(i).getUsername());
			itemAnnot.setReliability("real");
			itemFeaturesAnnot.add(itemAnnot);
			itemFeaturesTraining.add(listReal.get(i));
		}
		System.out
				.println("Training size of real items : " + (listReal.size()));

		UserFeaturesExtractor ifex2 = new UserFeaturesExtractor();
		List<UserFeatures> listRealSusp = ifex2.getLatestItems(
				"FeaturesObjects", "BostonUserYes", 0);

		for (int i = 0; i < listFakeSusp.size(); i++) {
			UserFeaturesAnnotation itemAnnot = new UserFeaturesAnnotation();
			itemAnnot.setUsername(listRealSusp.get(i).getUsername());
			itemAnnot.setReliability("real");
			itemFeaturesAnnot2.add(itemAnnot);
			itemFeaturesTesting.add(listRealSusp.get(i));
		}
		System.out.println("Testing size of real items : "
				+ (itemFeaturesTesting.size() - realtest));

		isTrainingSet = createTrainingSet(itemFeaturesTraining,
				itemFeaturesAnnot);
		isTestingSet = createTestingSet(itemFeaturesTesting,
				itemFeaturesAnnot2);

		System.out.println("Total size of training set : "
				+ isTrainingSet.size());
		System.out
				.println("Total size of testing set : " + isTestingSet.size());

	}*/

	/*public void classifyItems(Instances isTestSet) throws Exception {

		int count = 0, countFake = 0, countReal = 0, predictedFake = 0, actualFake = 0;
		SerializedClassifier classifier = new SerializedClassifier();
		classifier.setModelFile(new File(Vars.MODEL_PATH3));
		// HashMap<String,String> mapResults = new HashMap<String,String>();

		System.out
				.println("-----------------TEST SET RESULTS----------------------");
		int instaSize = isTestSet.numInstances();
		for (int i = 0; i < instaSize; i++) {

			Instance inst = isTestSet.instance(i);
			// System.out.println(inst.stringValue(0));
			double pred = classifier.classifyInstance(inst);
			// System.out.println(set.get(i)+ " " + isTestSet.instance(i));
			String actual = isTestSet.classAttribute().value(
					(int) inst.classValue());
			String predicted = isTestSet.classAttribute().value((int) pred);

			if (actual == "real") {
				actualFake++;
			}
			// System.out.print("actual: " + actual);
			// System.out.println(", predicted: " + predicted);
			if (actual.equals(predicted)) {
				count++;
			}
			if (actual == "fake" && actual.equals(predicted)) {
				countFake++;
			} else if (actual == "real" && actual.equals(predicted)) {
				countReal++;
			}
			
			 * System.out.println(map); System.out.println(inst);
			 
			// String instaId = map.get(inst);
			if (predicted == "fake") {
				predictedFake++;
			}

			// mapResults.put(set.get(i),predicted);

		}
		System.out.println("Actual reals " + actualFake);
		// FileManager.getInstance().writeHashmapToFile(mapResults);

		System.out.println("Total items " + instaSize);
		System.out.println("Items classified correctly:" + count);
		//System.out.println("Fake items classified correctly: " + countFake
			//	+ ". Percentage: "
			//	+ ((double) countFake / (instaSize - sizetest)) * 100);
		//System.out.println("Real items classified correctly: " + countReal
				//+ ". Percentage: " + ((double) countReal / (sizetest)) * 100);

		//System.out.println("Items predicted as fake " + predictedFake);
		//System.out.println("Percentage " + ((double) count / instaSize) * 100);

	}*/

	

	

}
