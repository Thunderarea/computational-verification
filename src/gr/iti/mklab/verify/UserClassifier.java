package gr.iti.mklab.verify;

import gr.iti.mklab.extractfeatures.UserFeatures;
import gr.iti.mklab.extractfeatures.UserFeaturesAnnotation;

import java.util.ArrayList;
import java.util.List;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Class to organize the Item classification using User features by defining the
 * attributes, creating the testing set and perform the classification.
 * 
 * @author boididou
 */
public class UserClassifier {

	static ArrayList<Attribute> fvAttributes = new ArrayList<Attribute>();

	public static ArrayList<Attribute> getFvAttributes() {
		return fvAttributes;
	}

	/**
	 * @return List of attributes needed for the classification
	 */
	public static List<Attribute> declareAttributes() {

		// numeric attributes
		Attribute numFriends = new Attribute("numFriends");
		Attribute numFollowers = new Attribute("numFollowers");
		Attribute FolFrieRatio = new Attribute("FolFrieRatio");
		Attribute timesListed = new Attribute("timesListed");
		Attribute numTweets = new Attribute("numTweets");
		Attribute numMediaContent = new Attribute("numMediaContent");
		Attribute wotTrustUser = new Attribute("wotTrustUser");
		Attribute accountAge = new Attribute("accountAge");
		Attribute tweetRatio = new Attribute("tweetRatio");
		//Attribute indegreeUser = new Attribute("indegreeUser");
		//Attribute harmonicUser = new Attribute("harmonicUser");
		Attribute alexaCountryRankUser = new Attribute("alexaCountryRankUser");
		Attribute alexaDeltaRankUser = new Attribute("alexaDeltaRankUser");
		Attribute alexaPopularityUser = new Attribute("alexaPopularityUser");
		Attribute alexaReachRankUser = new Attribute("alexaReachRankUser");

		List<String> fvnominal1 = new ArrayList<String>(2);
		fvnominal1.add("true");
		fvnominal1.add("false");
		Attribute hasUrl = new Attribute("hasUrl", fvnominal1);

		List<String> fvnominal2 = new ArrayList<String>(2);
		fvnominal2.add("true");
		fvnominal2.add("false");
		Attribute isVerified = new Attribute("isVerified", fvnominal2);

		List<String> fvnominal3 = new ArrayList<String>(2);
		fvnominal3.add("true");
		fvnominal3.add("false");
		Attribute hasBio = new Attribute("hasBio", fvnominal3);

		List<String> fvnominal4 = new ArrayList<String>(2);
		fvnominal4.add("true");
		fvnominal4.add("false");
		Attribute hasLocation = new Attribute("hasLocation", fvnominal4);

		List<String> fvnominal5 = new ArrayList<String>(2);
		fvnominal5.add("true");
		fvnominal5.add("false");
		Attribute hasExistingLocation = new Attribute("hasExistingLocation",
				fvnominal5);

		List<String> fvnominal6 = new ArrayList<String>(2);
		fvnominal6.add("true");
		fvnominal6.add("false");
		Attribute hasProfileImg = new Attribute("hasProfileImg", fvnominal6);

		List<String> fvnominal7 = new ArrayList<String>(2);
		fvnominal7.add("true");
		fvnominal7.add("false");
		Attribute hasHeaderImg = new Attribute("hasHeaderImg", fvnominal7);

		List<String> fvnominal8 = null;
		Attribute id = new Attribute("id", fvnominal8);

		List<String> fvClass = new ArrayList<String>(2);
		fvClass.add("real");
		fvClass.add("fake");
		Attribute ClassAttribute = new Attribute("theClass", fvClass);

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
		//fvAttributes.add(indegreeUser);
		//fvAttributes.add(harmonicUser);
		fvAttributes.add(alexaCountryRankUser); // new
		fvAttributes.add(alexaDeltaRankUser);
		fvAttributes.add(alexaPopularityUser);
		fvAttributes.add(alexaReachRankUser);

		fvAttributes.add(ClassAttribute);

		return fvAttributes;
	}

	/**
	 * @param listUserFeatures
	 *            the features of the current User
	 * @return Instance created
	 */
	public static Instance createInstance(UserFeatures list) {

		Instance inst = new DenseInstance(fvAttributes.size());

		String id = list.getId().replaceAll("[^\\d.]", "");

		inst.setValue((Attribute) fvAttributes.get(0), id);

		inst.setValue((Attribute) fvAttributes.get(1), list.getNumFriends());
		inst.setValue((Attribute) fvAttributes.get(2),
				list.getNumFollowers());
		inst.setValue((Attribute) fvAttributes.get(3),
				list.getFolFrieRatio());
		inst.setValue((Attribute) fvAttributes.get(4),
				list.getTimesListed());
		inst.setValue((Attribute) fvAttributes.get(5), list.gethasURL()
				.toString());
		inst.setValue((Attribute) fvAttributes.get(6), list.getisVerified()
				.toString());
		inst.setValue((Attribute) fvAttributes.get(7), list.getNumTweets());

		inst.setValue((Attribute) fvAttributes.get(8), list.gethasBio()
				.toString());
		inst.setValue((Attribute) fvAttributes.get(9), list
				.gethasLocation().toString());
		inst.setValue((Attribute) fvAttributes.get(10), list
				.gethasExistingLocation().toString());

		if (list.getWotTrustUser() != null) {
			inst.setValue((Attribute) fvAttributes.get(11),
					list.getWotTrustUser());
		}

		inst.setValue((Attribute) fvAttributes.get(12),
				list.getNumMediaContent());

		if (list.getAccountAge() != null) {
			inst.setValue((Attribute) fvAttributes.get(13),
					list.getAccountAge());
		}
		if (list.getHasProfileImg() != null) {
			inst.setValue((Attribute) fvAttributes.get(14), list
					.getHasProfileImg().toString());
		}
		if (list.getHasHeaderImg() != null) {
			inst.setValue((Attribute) fvAttributes.get(15), list
					.getHasHeaderImg().toString());
		}

		if (list.getTweetRatio() != null) {
			inst.setValue((Attribute) fvAttributes.get(16),
					list.getTweetRatio());
		}
		
		if (list.getAlexaCountryRank() != null) {
			inst.setValue((Attribute) fvAttributes.get(17),
					list.getAlexaCountryRank());
		}
		if (list.getAlexaDeltaRank() != null) {
			inst.setValue((Attribute) fvAttributes.get(18),
					list.getAlexaDeltaRank());
		}
		if (list.getAlexaPopularity() != null) {
			inst.setValue((Attribute) fvAttributes.get(19),
					list.getAlexaPopularity());
		}
		
		if (list.getAlexaReachRank() != null) {
			
			inst.setValue((Attribute) fvAttributes.get(20),
					list.getAlexaReachRank());
		}

		return inst;
	}

	/**
	 * @param list
	 *            of the UserFeatures computed
	 * @param itemFeaturesAnnot
	 *            list of the users' annotation details
	 * @return Instances that form the testing set
	 * @throws Exception
	 */
	public static Instances createTestingSet(
			List<UserFeatures> listUserFeatures,
			List<UserFeaturesAnnotation> listFeaturesAnnot) throws Exception {

		int index = 0;

		if (UserClassifier.getFvAttributes().size() == 0) {
			fvAttributes = (ArrayList<Attribute>) declareAttributes();
		}

		// create an empty training set and then keep the instances
		Instances isTestingSet = new Instances("Rel", fvAttributes,	listUserFeatures.size());
		// Set class index
		isTestingSet.setClassIndex(fvAttributes.size() - 1);

		for (int i = 0; i < listUserFeatures.size(); i++) {

			// declare instance and define its values
			Instance inst = createInstance(listUserFeatures.get(i));

			for (int j = 0; j < listFeaturesAnnot.size(); j++) {
				if (listUserFeatures.get(i).getUsername().equals(listFeaturesAnnot.get(j).getUsername())) {
					index = j;
				}
			}

			inst.setValue((Attribute) fvAttributes.get(fvAttributes.size() - 1), listFeaturesAnnot.get(index).getReliability());
			// add the instance to the testing set
			isTestingSet.add(inst);
		}

		return isTestingSet;
	}

	/**
	 * @param listUserFeatures
	 *            the features for the MediaItem
	 * @param listFeaturesAnnot
	 *            the User's annotation details
	 * @return Instances that form the testing set
	 * @throws Exception
	 */
	public static Instances createTestingSet(UserFeatures listUserFeatures,
			UserFeaturesAnnotation listFeaturesAnnot) throws Exception {

		// create an empty training set and then keep the instances
		Instances isTestingSet = new Instances("Rel", fvAttributes, 1);
		// Set class index
		isTestingSet.setClassIndex(fvAttributes.size() - 1);

		// declare instance and define its values
		Instance inst = createInstance(listUserFeatures);
		inst.setValue((Attribute) fvAttributes.get(fvAttributes.size() - 1),
				listFeaturesAnnot.getReliability());

		// add the instance to the testing set
		isTestingSet.add(inst);

		return isTestingSet;
	}

	/**
	 * @param isTestSet
	 *            Instances of the test set
	 * @return Boolean table of reliability values of the test set instances
	 * @throws Exception
	 */

	public static Instances reformatInstances(Instances instances) {
		
		UserClassifier.declareAttributes();
		
		Instances newInstances = new Instances("data", UserClassifier.getFvAttributes(), instances.size());
		
		for (int i=0; i<instances.numInstances(); i++) {
			
			Instance inst = new DenseInstance(UserClassifier.getFvAttributes().size());
			
			inst.setValue((Attribute) fvAttributes.get(0), instances.instance(0).stringValue(0));
			inst.setValue((Attribute) fvAttributes.get(1), instances.instance(i).value(1));
			inst.setValue((Attribute) fvAttributes.get(2), instances.instance(i).value(2));
			inst.setValue((Attribute) fvAttributes.get(3), instances.instance(i).value(3));
			inst.setValue((Attribute) fvAttributes.get(4), instances.instance(i).value(4));
			inst.setValue((Attribute) fvAttributes.get(5), instances.instance(i).value(5));
			inst.setValue((Attribute) fvAttributes.get(6), instances.instance(i).value(6));
			inst.setValue((Attribute) fvAttributes.get(7), instances.instance(i).value(7));
			inst.setValue((Attribute) fvAttributes.get(8), instances.instance(i).value(8));
			inst.setValue((Attribute) fvAttributes.get(9), instances.instance(i).value(9));
			inst.setValue((Attribute) fvAttributes.get(10), instances.instance(i).value(10));
			inst.setValue((Attribute) fvAttributes.get(11), instances.instance(i).value(11));
			inst.setValue((Attribute) fvAttributes.get(12),	instances.instance(i).value(12));
			inst.setValue((Attribute) fvAttributes.get(13),	instances.instance(i).value(13));
			inst.setValue((Attribute) fvAttributes.get(14),	instances.instance(i).value(14));
			inst.setValue((Attribute) fvAttributes.get(15),	instances.instance(i).value(15));
			inst.setValue((Attribute) fvAttributes.get(16),	instances.instance(i).value(16));
			//inst.setValue((Attribute) fvAttributes.get(17),	instances.instance(i).value(17));
			//inst.setValue((Attribute) fvAttributes.get(18),	instances.instance(i).value(18));
			inst.setValue((Attribute) fvAttributes.get(17), instances.instance(i).value(19));
			inst.setValue((Attribute) fvAttributes.get(18), instances.instance(i).value(20));
			inst.setValue((Attribute) fvAttributes.get(19),	instances.instance(i).value(21));
			inst.setValue((Attribute) fvAttributes.get(20),	instances.instance(i).value(22));
			inst.setValue((Attribute) fvAttributes.get(21),	instances.instance(i).stringValue(23));
			
			inst.setDataset(newInstances);
			newInstances.add(inst);
			

		}
		
		return newInstances;
		
	}
	
	public static Instances formTestingSet(UserFeatures userFeats) throws Exception {

		if (UserClassifier.getFvAttributes().size()==0){
			fvAttributes = (ArrayList<Attribute>) declareAttributes();
		}
		
		Instances testingSet = null;
		//UserFeatures userFeats;

		try {
			// compute and get the Item features of the current JSON object
			//userFeats = UserFeaturesExtractorJSON.extractFeatures(json);
			// create the Item annotation instance
			UserFeaturesAnnotation userAnnot = new UserFeaturesAnnotation();
			userAnnot.setId(userFeats.getId());
			userAnnot.setReliability("fake");
			// create the testing set using the above data
			testingSet = createTestingSet(userFeats, userAnnot);

		} catch (Exception e) {
			System.out.println("Error on forming the User features testing set...!");
			e.printStackTrace();
		}

		return testingSet;
	}

}
