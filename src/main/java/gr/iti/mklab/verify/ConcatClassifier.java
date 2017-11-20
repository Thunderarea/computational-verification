package gr.iti.mklab.verify;

import gr.iti.mklab.extractfeatures.TweetFeatures;
import gr.iti.mklab.extractfeatures.UserFeatures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//import com.aliasi.util.Arrays;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;


public class ConcatClassifier {
	
	//private Instances isTrainingSet, isTestingSet;
	private ArrayList<Attribute> fvAttributes = new ArrayList<Attribute>();

	
	//constructor
	public ConcatClassifier() {
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
				List<String> fvnominal1u = new ArrayList<String>(2);
				fvnominal1u.add("true");
				fvnominal1u.add("false");
				Attribute hasUrl = new Attribute("hasUrl", fvnominal1u);
				// Attribute hasUrl = new Attribute("hasUrl");	

				// declare nominal attributes
				List<String> fvnominal2u = new ArrayList<String>(2);
				fvnominal2u.add("true");
				fvnominal2u.add("false");
				Attribute isVerified = new Attribute("isVerified", fvnominal2u);
				// Attribute isVerified = new Attribute("isVerified");

				// declare nominal attributes
				List<String> fvnominal3u = new ArrayList<String>(2);
				fvnominal3u.add("true");
				fvnominal3u.add("false");
				Attribute hasBio = new Attribute("hasBio", fvnominal3u);
				// Attribute hasBio = new Attribute("hasBio");

				// declare nominal attributes
				List<String> fvnominal4u = new ArrayList<String>(2);
				fvnominal4u.add("true");
				fvnominal4u.add("false");
				Attribute hasLocation = new Attribute("hasLocation", fvnominal4u);
				// Attribute hasLocation = new Attribute("hasLocation");

				// declare nominal attributes
				List<String> fvnominal5u = new ArrayList<String>(2);
				fvnominal5u.add("true");
				fvnominal5u.add("false");
				Attribute hasExistingLocation = new Attribute("hasExistingLocation",
						fvnominal5u);
				// Attribute hasExistingLocation = new Attribute("hasExistingLocation");

				List<String> fvnominal6u = new ArrayList<String>(2);
				fvnominal6u.add("true");
				fvnominal6u.add("false");
				Attribute hasProfileImg = new Attribute("hasProfileImg", fvnominal6u);
				// Attribute hasProfileImg = new Attribute("hasProfileImg");

				List<String> fvnominal7u = new ArrayList<String>(2);
				fvnominal7u.add("true");
				fvnominal7u.add("false");
				Attribute hasHeaderImg = new Attribute("hasHeaderImg", fvnominal7u);
	

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

	
	


	
	public Instance createInstance(TweetFeatures listItemFeatures, UserFeatures listUserFeatures) {

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
		
		
		iExample.setValue((Attribute) fvAttributes.get(32), listUserFeatures.getNumFriends());
		iExample.setValue((Attribute) fvAttributes.get(33),
				listUserFeatures.getNumFollowers());
		iExample.setValue((Attribute) fvAttributes.get(34),
				listUserFeatures.getFolFrieRatio());
		iExample.setValue((Attribute) fvAttributes.get(35),
				listUserFeatures.getTimesListed());
		iExample.setValue((Attribute) fvAttributes.get(36), listUserFeatures.getHasURL()
				.toString());
		iExample.setValue((Attribute) fvAttributes.get(37), listUserFeatures.getIsVerified()
				.toString());
		iExample.setValue((Attribute) fvAttributes.get(38), listUserFeatures.getNumTweets());

		iExample.setValue((Attribute) fvAttributes.get(39), listUserFeatures.getHasBio()
				.toString());
		iExample.setValue((Attribute) fvAttributes.get(40), listUserFeatures
				.getHasLocation().toString());
		iExample.setValue((Attribute) fvAttributes.get(41), listUserFeatures
				.getHasExistingLocation().toString());

		if (listUserFeatures.getWotTrustUser() != null) {
			iExample.setValue((Attribute) fvAttributes.get(42),
					listUserFeatures.getWotTrustUser());
		}

		iExample.setValue((Attribute) fvAttributes.get(43),
				listUserFeatures.getNumMediaContent());

		if (listUserFeatures.getAccountAge() != null) {
			iExample.setValue((Attribute) fvAttributes.get(44),
					listUserFeatures.getAccountAge());
		}
		if (listUserFeatures.getHasProfileImg() != null) {
			iExample.setValue((Attribute) fvAttributes.get(45), listUserFeatures
					.getHasProfileImg().toString());
		}
		if (listUserFeatures.getHasHeaderImg() != null) {
			iExample.setValue((Attribute) fvAttributes.get(46), listUserFeatures
					.getHasHeaderImg().toString());
		}

		if (listUserFeatures.getTweetRatio() != null) {
			iExample.setValue((Attribute) fvAttributes.get(47),
					listUserFeatures.getTweetRatio());
		}
		if (listUserFeatures.getIndegree() != null) {
			iExample.setValue((Attribute) fvAttributes.get(48),
					listUserFeatures.getIndegree());
		}
		if (listUserFeatures.getHarmonic() != null) {
			iExample.setValue((Attribute) fvAttributes.get(49),
					listUserFeatures.getHarmonic());
		}
		if (listUserFeatures.getAlexaCountryRank() != null) {
			iExample.setValue((Attribute) fvAttributes.get(50),
					listUserFeatures.getAlexaCountryRank());
		}
		if (listUserFeatures.getAlexaDeltaRank() != null) {
			iExample.setValue((Attribute) fvAttributes.get(51),
					listUserFeatures.getAlexaDeltaRank());
		}
		if (listUserFeatures.getAlexaPopularity() != null) {
			iExample.setValue((Attribute) fvAttributes.get(52),
					listUserFeatures.getAlexaPopularity());
		}
		if (listUserFeatures.getAlexaReachRank() != null) {
			iExample.setValue((Attribute) fvAttributes.get(53),
					listUserFeatures.getAlexaReachRank());
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
	public Instances createTrainingSet(List<TweetFeatures> listTweet, List<UserFeatures> listUser, List<FeaturesAnnotationItem> annotation) throws Exception {

		// Create an empty training set
		Instances isTrainingSet = new Instances("Rel", getFvAttributes(), listUser.size());
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
	   for (int i = 0; i < listTweet.size(); i++) {
			//create an Instance
			Instance iExample = createInstance(listTweet.get(i), listUser.get(i));
			iExample.setValue((Attribute) fvAttributes.get(fvAttributes.size() - 1), map.get(listTweet.get(i).getId()) );
			isTrainingSet.add(iExample);
						
		}
		

		return isTrainingSet;
	}


	
}
