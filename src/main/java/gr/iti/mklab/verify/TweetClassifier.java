package gr.iti.mklab.verify;

import gr.iti.mklab.extractfeatures.TweetFeatures;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//import com.aliasi.util.Arrays;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;


public class TweetClassifier {
	
	//private Instances isTrainingSet, isTestingSet;
	private ArrayList<Attribute> fvAttributes = new ArrayList<Attribute>();

	
	//constructor
	public TweetClassifier() {
		setFvAttributes(declareAttributes());
	}
	
	
	public ArrayList<Attribute> getFvAttributes() {
		return fvAttributes;
	}
	
	public void setFvAttributes(ArrayList<Attribute> list) {
		this.fvAttributes = list;
	}
	

	private ArrayList<Attribute> declareAttributes() {
		// declare numeric attributes

		Attribute ItemLength = new Attribute("item_length");
		Attribute numWords = new Attribute("num_words");
		Attribute numQuestionMark = new Attribute("num_questionmark");
		Attribute numExclamationMark = new Attribute("num_exclamationmark");
		Attribute numUppercaseChars = new Attribute("num_uppercasechars");
		Attribute numPosSentiWords = new Attribute("num_pos_sentiment_words");
		Attribute numNegSentiWords = new Attribute("num_neg_sentiment_words");
		Attribute numMentions = new Attribute("num_mentions");
		Attribute numHashtags = new Attribute("num_hashtags");
		Attribute numURLs = new Attribute("num_URLs");
		Attribute retweetCount = new Attribute("retweet_count");
		Attribute numSlangs = new Attribute("num_slangs");
		Attribute numNouns = new Attribute("num_nouns");
		Attribute wotTrust = new Attribute("wot_trust");
		Attribute urlIndegree = new Attribute("indegree");
		Attribute readability = new Attribute("readability");
		Attribute urlHarmonic = new Attribute("harmonic");
		Attribute alexaCountryRank = new Attribute("alexa_country_rank");
		Attribute alexaDeltaRank = new Attribute("alexa_delta_rank");
		Attribute alexaPopularity = new Attribute("alexa_popularity");
		Attribute alexaReachRank = new Attribute("alexa_reach_rank");

			
		// declare nominal attributes
		List<String> fvnominal1 = new ArrayList<String>(2);
		fvnominal1.add("true");
		fvnominal1.add("false");
		Attribute containsQuestionMark = new Attribute("contains_question_mark",
				fvnominal1);

		List<String> fvnominal2 = new ArrayList<String>(2);
		fvnominal2.add("true");
		fvnominal2.add("false");
		Attribute containsExclamationMark = new Attribute(
				"contains_exclamation_mark", fvnominal2);

		List<String> fvnominal3 = new ArrayList<String>(2);
		fvnominal3.add("true");
		fvnominal3.add("false");
		Attribute containsHappyEmo = new Attribute("contains_happy_emo",
				fvnominal3);

		List<String> fvnominal4 = new ArrayList<String>(2);
		fvnominal4.add("true");
		fvnominal4.add("false");
		Attribute containsSadEmo = new Attribute("contains_sad_emo", fvnominal4);

		List<String> fvnominal5 = new ArrayList<String>(2);
		fvnominal5.add("true");
		fvnominal5.add("false");
		Attribute containsFirstOrderPron = new Attribute(
				"contains_first_order_pron", fvnominal5);

		List<String> fvnominal6 = new ArrayList<String>(2);
		fvnominal6.add("true");
		fvnominal6.add("false");
		Attribute containsSecondOrderPron = new Attribute(
				"contains_second_order_pron", fvnominal6);

		List<String> fvnominal7 = new ArrayList<String>(2);
		fvnominal7.add("true");
		fvnominal7.add("false");
		Attribute containsThirdOrderPron = new Attribute(
				"contains_third_order_pron", fvnominal7);

		List<String> fvnominal8 = new ArrayList<String>(2);
		fvnominal8.add("true");
		fvnominal8.add("false");
		Attribute hasColon = new Attribute("has_colon", fvnominal8);

		List<String> fvnominal9 = new ArrayList<String>(2);
		fvnominal9.add("true");
		fvnominal9.add("false");
		Attribute hasPlease = new Attribute("has_please", fvnominal9);

		List<String> fvnominal10 = new ArrayList<String>(2);
		fvnominal10.add("true");
		fvnominal10.add("false");
		Attribute hasExternalLink = new Attribute("has_external_link",
				fvnominal10);

		List<String> fvnominal11 = null;
		Attribute id = new Attribute("id", fvnominal11);
	

		List<String> fvClass = new ArrayList<String>(2);
		fvClass.add("real");
		fvClass.add("fake");
		Attribute ClassAttribute = new Attribute("theClass", fvClass);

		ArrayList<Attribute> fvAttributes = new ArrayList<Attribute>();
		
		
		
		fvAttributes.add(id);

		fvAttributes.add(ItemLength);
		fvAttributes.add(numWords);
		fvAttributes.add(containsQuestionMark);
		fvAttributes.add(containsExclamationMark);
		fvAttributes.add(hasExternalLink); // new
		fvAttributes.add(numNouns); // new 
		fvAttributes.add(containsHappyEmo);
		fvAttributes.add(containsSadEmo);
		fvAttributes.add(containsFirstOrderPron);
		fvAttributes.add(containsSecondOrderPron);
		fvAttributes.add(containsThirdOrderPron);
		fvAttributes.add(numUppercaseChars);
		fvAttributes.add(numPosSentiWords);
		fvAttributes.add(numNegSentiWords);
		fvAttributes.add(numMentions);
		fvAttributes.add(numHashtags);
		fvAttributes.add(numURLs);
		fvAttributes.add(retweetCount);
		
		fvAttributes.add(numSlangs); // new
		fvAttributes.add(hasColon); // new
		fvAttributes.add(hasPlease); // new
		fvAttributes.add(wotTrust); // new
		fvAttributes.add(numQuestionMark);
		fvAttributes.add(numExclamationMark);
		fvAttributes.add(readability); // new
		fvAttributes.add(urlIndegree); // new
		fvAttributes.add(urlHarmonic); // new
		fvAttributes.add(alexaCountryRank); // new
		fvAttributes.add(alexaDeltaRank);// new
		fvAttributes.add(alexaPopularity);// new
		fvAttributes.add(alexaReachRank);// new
		
		fvAttributes.add(ClassAttribute);
		

		return fvAttributes;
	}

	
	


	public Instance createInstanceOld(TweetFeatures listItemFeatures) {

		Instance iExample = new DenseInstance(fvAttributes.size());
		String id = listItemFeatures.getId().replaceAll("[^\\d.]", "");
		iExample.setValue((Attribute) fvAttributes.get(0), id);

		iExample.setValue((Attribute) fvAttributes.get(1),
				listItemFeatures.getItemLength());
		iExample.setValue((Attribute) fvAttributes.get(2),
				listItemFeatures.getNumWords());
		iExample.setValue((Attribute) fvAttributes.get(3),
				String.valueOf(listItemFeatures.getContainsQuestionMark()));

		iExample.setValue((Attribute) fvAttributes.get(4),
				String.valueOf(listItemFeatures.getContainsExclamationMark()));

		iExample.setValue((Attribute) fvAttributes.get(5),
				String.valueOf(listItemFeatures.getContainsHappyEmo()));
		iExample.setValue((Attribute) fvAttributes.get(6),
				String.valueOf(listItemFeatures.getContainsSadEmo()));

		if (listItemFeatures.getContainsFirstOrderPron() != null) {
			iExample.setValue((Attribute) fvAttributes.get(7), String
					.valueOf(listItemFeatures.getContainsFirstOrderPron()));
		}
		if (listItemFeatures.getContainsSecondOrderPron() != null) {
			iExample.setValue((Attribute) fvAttributes.get(8), String
					.valueOf(listItemFeatures.getContainsSecondOrderPron()));
		}
		if (listItemFeatures.getContainsThirdOrderPron() != null) {
			iExample.setValue((Attribute) fvAttributes.get(9), String
					.valueOf(listItemFeatures.getContainsThirdOrderPron()));
		}

		iExample.setValue((Attribute) fvAttributes.get(10),
				listItemFeatures.getNumUppercaseChars());

		if (listItemFeatures.getNumPosSentiWords() != null) {
			iExample.setValue((Attribute) fvAttributes.get(11),
					listItemFeatures.getNumPosSentiWords());
		}
		if (listItemFeatures.getNumNegSentiWords() != null) {
			iExample.setValue((Attribute) fvAttributes.get(12),
					listItemFeatures.getNumNegSentiWords());
		}
		iExample.setValue((Attribute) fvAttributes.get(13),
				listItemFeatures.getNumMentions());
		iExample.setValue((Attribute) fvAttributes.get(14),
				listItemFeatures.getNumHashtags());
		iExample.setValue((Attribute) fvAttributes.get(15),
				listItemFeatures.getNumURLs());
		iExample.setValue((Attribute) fvAttributes.get(16),
				listItemFeatures.getRetweetCount());

		iExample.setValue((Attribute) fvAttributes.get(17),
				listItemFeatures.getNumQuestionMark());
		iExample.setValue((Attribute) fvAttributes.get(18),
				listItemFeatures.getNumExclamationMark());

		return iExample;
	}

	public Instance createInstance(TweetFeatures listItemFeatures) {

		Instance iExample = new DenseInstance(fvAttributes.size());
		String id = listItemFeatures.getId().replaceAll("[^\\d.]", "");
		//System.out.println("ID " + id);
		iExample.setValue((Attribute) fvAttributes.get(0), id);

		iExample.setValue((Attribute) fvAttributes.get(1),
				listItemFeatures.getItemLength());
		iExample.setValue((Attribute) fvAttributes.get(2),
				listItemFeatures.getNumWords());
		iExample.setValue((Attribute) fvAttributes.get(3),
				String.valueOf(listItemFeatures.getContainsQuestionMark()));

		iExample.setValue((Attribute) fvAttributes.get(4),
				String.valueOf(listItemFeatures.getContainsExclamationMark()));
		iExample.setValue((Attribute) fvAttributes.get(5),
				String.valueOf(listItemFeatures.getHasExternalLink()));
		if (listItemFeatures.getNumNouns() != null) {
			iExample.setValue((Attribute) fvAttributes.get(6),
					listItemFeatures.getNumNouns());
		}
		iExample.setValue((Attribute) fvAttributes.get(7),
				String.valueOf(listItemFeatures.getContainsHappyEmo()));
		iExample.setValue((Attribute) fvAttributes.get(8),
				String.valueOf(listItemFeatures.getContainsSadEmo()));

		if (listItemFeatures.getContainsFirstOrderPron() != null) {
			iExample.setValue((Attribute) fvAttributes.get(9), String
					.valueOf(listItemFeatures.getContainsFirstOrderPron()));
		}
		if (listItemFeatures.getContainsSecondOrderPron() != null) {
			iExample.setValue((Attribute) fvAttributes.get(10), String
					.valueOf(listItemFeatures.getContainsSecondOrderPron()));
		}
		if (listItemFeatures.getContainsThirdOrderPron() != null) {
			iExample.setValue((Attribute) fvAttributes.get(11), String
					.valueOf(listItemFeatures.getContainsThirdOrderPron()));
		}

		iExample.setValue((Attribute) fvAttributes.get(12),
				listItemFeatures.getNumUppercaseChars());

		if (listItemFeatures.getNumPosSentiWords() != null) {
			iExample.setValue((Attribute) fvAttributes.get(13),
					listItemFeatures.getNumPosSentiWords());
		}
		if (listItemFeatures.getNumNegSentiWords() != null) {
			iExample.setValue((Attribute) fvAttributes.get(14),
					listItemFeatures.getNumNegSentiWords());
		}
		iExample.setValue((Attribute) fvAttributes.get(15),
				listItemFeatures.getNumMentions());
		iExample.setValue((Attribute) fvAttributes.get(16),
				listItemFeatures.getNumHashtags());
		iExample.setValue((Attribute) fvAttributes.get(17),
				listItemFeatures.getNumURLs());
		iExample.setValue((Attribute) fvAttributes.get(18),
				listItemFeatures.getRetweetCount());

		if (listItemFeatures.getNumSlangs() != null) {
			iExample.setValue((Attribute) fvAttributes.get(19),
					listItemFeatures.getNumSlangs());
		}
		iExample.setValue((Attribute) fvAttributes.get(20),
				String.valueOf(listItemFeatures.getHasColon()));
		iExample.setValue((Attribute) fvAttributes.get(21),
				String.valueOf(listItemFeatures.getHasPlease()));

		if (listItemFeatures.getWotTrust() != null) {
			iExample.setValue((Attribute) fvAttributes.get(22),
					listItemFeatures.getWotTrust());
		}
		iExample.setValue((Attribute) fvAttributes.get(23),
				listItemFeatures.getNumQuestionMark());
		iExample.setValue((Attribute) fvAttributes.get(24),
				listItemFeatures.getNumExclamationMark());

		if (listItemFeatures.getReadability() != null) {
			iExample.setValue((Attribute) fvAttributes.get(25),
					listItemFeatures.getReadability());
		}

		if (listItemFeatures.getUrlIndegree() != null) {
			iExample.setValue((Attribute) fvAttributes.get(26),
					listItemFeatures.getUrlIndegree());
		}
		if (listItemFeatures.getUrlHarmonic() != null) {
			iExample.setValue((Attribute) fvAttributes.get(27),
					listItemFeatures.getUrlHarmonic());
		}
		if (listItemFeatures.getAlexaCountryRank() != null) {
			iExample.setValue((Attribute) fvAttributes.get(28),
					listItemFeatures.getAlexaCountryRank());
		}
		if (listItemFeatures.getAlexaDeltaRank() != null) {
			iExample.setValue((Attribute) fvAttributes.get(29),
					listItemFeatures.getAlexaDeltaRank());
		}
		if (listItemFeatures.getAlexaPopularity() != null) {
			iExample.setValue((Attribute) fvAttributes.get(30),
					listItemFeatures.getAlexaPopularity());
		}
		
		if (listItemFeatures.getAlexaReachRank() != null) {
			
			iExample.setValue((Attribute) fvAttributes.get(31),
					listItemFeatures.getAlexaReachRank());
		}	
		
		if (listItemFeatures.getAlexaReachRank() != null) {
			
			iExample.setValue((Attribute) fvAttributes.get(31),
					listItemFeatures.getAlexaReachRank());
		}	
		
		/*if (listItemFeatures.getAnnotation()!= null) {
			
			iExample.setValue((Attribute) fvAttributes.get(32),
					listItemFeatures.getAnnotation());
		}	*/
		return iExample;
	}

	
	/**
	 * Method that creates the training set for either the cross validation or
	 * the classification method.
	 * 
	 * @param list
	 *            <ItemFeatures>
	 * @param itemFeaturesAnnot
	 *            <ItemFeaturesAnnotation> list
	 * @return Instances created
	 * @throws Exception
	 */
	public Instances createTrainingSet(List<TweetFeatures> list, List<FeaturesAnnotationItem> annotation) throws Exception {

		// Create an empty training set
		Instances isTrainingSet = new Instances("Rel", getFvAttributes(), list.size());
		// Set class index
		isTrainingSet.setClassIndex(fvAttributes.size() - 1);
		
		//save the <id,label> pair to a map
				HashMap<String,String> map = new HashMap<String, String>();
				
				for (int j = 0; j < annotation.size(); j++) {
					
					map.put(annotation.get(j).getId(), annotation.get(j).getLabel());
				}
	/*			
		
		for (Attribute a : getFvAttributes()) {
			System.out.print(a.index() + " " + a + " ");
		}
		System.out.println();
		*/
	   for (int i = 0; i < list.size(); i++) {
			//create an Instance
			Instance iExample = createInstance(list.get(i));
			iExample.setValue((Attribute) fvAttributes.get(fvAttributes.size() - 1), map.get(list.get(i).getId()) );
			isTrainingSet.add(iExample);
						
		}
		

		return isTrainingSet;
	}

	
	public Instances createTestingSet(List<TweetFeatures> listTest, List<FeaturesAnnotationItem> annotation) {

		
		// Create an empty training set
		Instances isTestSet = new Instances("Rel", fvAttributes,
				listTest.size());
		// Set class index
		isTestSet.setClassIndex(fvAttributes.size() - 1);
		
		//save the <id,label> pair to a map
		HashMap<String,String> map = new HashMap<String, String>();
		
		for (int j = 0; j < annotation.size(); j++) {
			
			map.put(annotation.get(j).getId(), annotation.get(j).getLabel());
		}
	
		//iterate through list of ItemFeatures
		for (int i = 0; i < listTest.size(); i++) {
			//create an Instance
			//System.out.println(listTest.get(i).getContainsQuestionMark());
			Instance iExample = createInstance(listTest.get(i));
			iExample.setValue((Attribute) fvAttributes.get(fvAttributes.size() - 1), map.get(listTest.get(i).getId()) );

			isTestSet.add(iExample);
		}
		
		
		return isTestSet;

	}

	/*public Instances formTrainingSetFromJsons(String traindatafile,	String filePath) throws Exception {

		HashMap<JSONObject, String> trainData = FileManager.getInstance()
				.loadJsonsfromFile(traindatafile);

		List<ItemFeatures> itemFeaturesTraining = new ArrayList<ItemFeatures>();
		List<ItemFeaturesAnnotation> itemFeaturesAnnot = new ArrayList<ItemFeaturesAnnotation>();

		Iterator iter = trainData.entrySet().iterator();

		for (JSONObject json : trainData.keySet()) {

			ItemFeaturesAnnotation itemAnnot = new ItemFeaturesAnnotation();
			ItemFeatures ifeat = ItemFeaturesExtractorJSON
					.extractFeatures(json);

			itemAnnot.setId(json.getString("id_str"));
			itemAnnot.setReliability(trainData.get(json));

			itemFeaturesAnnot.add(itemAnnot);
			itemFeaturesTraining.add(ifeat);

		}

		Instances trainingSet = createTrainingSet(itemFeaturesTraining, itemFeaturesAnnot);

		return trainingSet;

	}*/

	
	
	
}
