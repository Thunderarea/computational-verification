package gr.iti.mklab.extractfeatures;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONArray;
import org.json.JSONObject;
import org.tartarus.snowball.SnowballStemmer;

import weka.core.Instances;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.Morphology;
import edu.stanford.nlp.trees.Tree;
import gr.iti.mklab.readability.Readability;
import gr.iti.mklab.utils.AlexaRankingManager;
import gr.iti.mklab.utils.RunnableRank;
import gr.iti.mklab.utils.TextProcessing;
import gr.iti.mklab.utils.URLProcessing;
import gr.iti.mklab.utils.Vars;
import gr.iti.mklab.utils.WebOfTrustManager;
import gr.iti.mklab.verify.AgreementBasedClassification;

public class ItemFeaturesExtractorJSON {

	static String[] tokens;
	static String itemTitle;
	public static Instances isTrainingSet;
	// public static Long startTime = 1350864000L;
	public static LexicalizedParser lp = LexicalizedParser.loadModel(AgreementBasedClassification.prop.getProperty("MODEL_PARSER"));
	

	public static void setItemTitle(String itemTitle) {
		ItemFeaturesExtractorJSON.itemTitle = itemTitle;
	}

	static String text = "";

	public static List<ItemFeatures> getListItemFeatures(){
		
		List<ItemFeatures> listItemFeatures = new ArrayList<ItemFeatures>();
		
		//for ()
		
		return listItemFeatures;
	}
	
	public static ItemFeatures extractFeatures(JSONObject json) throws Exception {

		String currentTweetId = json.getString("id_str");

		// info
		System.out.println("Extracting Item features for the " + currentTweetId
				+ "...");

		// define the ItemFeatures object that holds the features
		ItemFeatures feat = new ItemFeatures();

		// define the value of the text...
		try {
			// if it is retweet
			text = json.getJSONObject("retweeted_status").getString("text");
		} catch (Exception e) {
			// if it is simple tweet
			text = json.getString("text");
		}

		// preprocess the text
		String str = text.replaceAll("http+s*+://[^ ]+", "")
				.replaceAll("@[^ ]+", "").replaceAll("#[^ ]+ ", "")
				.replaceAll("RT", "").toLowerCase().trim();

		/** Features depending on the Item(Tweet) **/
		// id
		feat.setId(currentTweetId);
		// item length
		feat.setItemLength(text.length());
		System.out.println("Tweet text length: " + text.length());
		// num of words
		feat.setNumWords(getNumItemWords());
		System.out.println("Number of words: " + feat.getNumWords());
		// contains "?"
		feat.setContainsQuestionMark(containsSymbol("?"));
		System.out.println("Contains question mark "
				+ feat.getContainsQuestionMark());
		// contains "!"
		feat.setContainsExclamationMark(containsSymbol("!"));
		System.out.println("Contains exclamation mark "
				+ feat.getContainsExclamationMark());
		// num of "!"
		feat.setNumExclamationMark(getNumSymbol("!"));
		System.out.println("Number of exclamation marks: "
				+ feat.getNumExclamationMark());
		// num of "?"
		feat.setNumQuestionMark(getNumSymbol("?"));
		System.out.println("Number of question marks: "
				+ feat.getNumQuestionMark());

		// contains happy emoticon
		feat.setContainsHappyEmo(containsEmo(AgreementBasedClassification.prop.getProperty("HAPPY_EMO_PATH")));
		System.out.println("Contains happy emoticon: "
				+ feat.getContainsHappyEmo());
		// contains sad emoticon
		feat.setContainsSadEmo(containsEmo(AgreementBasedClassification.prop.getProperty("SAD_EMO_PATH")));
		System.out
				.println("Contains sad emoticon: " + feat.getContainsSadEmo());

		// num of uppercase chars
		feat.setNumUppercaseChars(getNumUppercaseChars());
		System.out.println("Number of uppercase characters: "
				+ feat.getNumUppercaseChars());
		// num of mentions
		int numMentions = json.getJSONObject("entities")
				.getJSONArray("user_mentions").length();
		feat.setNumMentions(numMentions);
		System.out.println("Number of mentions: " + numMentions);
		// num of hashtags
		int numHashtags = json.getJSONObject("entities")
				.getJSONArray("hashtags").length();
		feat.setNumHashtags(numHashtags);
		System.out.println("Number of hashtags: " + numHashtags);

		// num of urls
		int numURLs = json.getJSONObject("entities").getJSONArray("urls")
				.length();
		int numURLs2;
		try {
			numURLs2 = json.getJSONObject("entities").getJSONArray("media")
					.length();
		} catch (Exception e) {
			numURLs2 = 0;
		}
		feat.setNumURLs(numURLs + numURLs2);
		System.out.println("Number of urls " + (numURLs + numURLs2));

		// num of retweets
		Long retweetCount = json.getLong("retweet_count");
		feat.setRetweetCount(retweetCount);
		System.out.println("Retweet count: " + retweetCount);
		// has colon
		feat.setHasColon(containsSymbol(":"));
		System.out.println("Has ':' symbol: " + feat.getHasColon());
		// has please
		feat.setHasPlease(containsSymbol("please"));
		System.out.println("Has 'please' word: " + feat.getHasPlease());
		// external links (except for the image link)

		HashSet<String> extLinks = checkForExternalLinks(json);
		feat.setHasExternalLink(!(extLinks.isEmpty()));
		System.out.println("Number of external links: "
				+ feat.getHasExternalLink());
		System.out.println("External links: " + extLinks);

		// variables used inside for loop
		WebOfTrustManager wot = new WebOfTrustManager();

		for (String extLink : extLinks) {

			Integer[] values = wot.getWotValues(extLink);

			if (values[0] != 0 && values[1] != 0) {
				feat.setWotTrust(values[0]);
				// feat.setWotSafe(values[1]);
			}
			// info
			System.out.println("For " + extLink + ": ");
			System.out.println("WOT trust value: " + feat.getWotTrust());

			// some necessary preprocessing
			extLink = URLProcessing.getInstance()
					.processUrlForRunnable(extLink);

			// UserFeatures
			/*Float indegree = organizeRunRank("indegree-" + currentTweetId,
					extLink, Vars.INDEGREE_FILE);

			Float harmonic = null;

			if (indegree != null) // if only there is indegree value, search for
									// harmonic
				harmonic = organizeRunRank("harmonic-" + currentTweetId,
						extLink, Vars.HARMONIC_FILE);

			feat.setUrlIndegree(indegree);
			feat.setUrlHarmonic(harmonic);
			System.out.println("In-degree centrality value: "
					+ feat.getUrlIndegree());
			System.out.println("Harmonic centrality value: "
					+ feat.getUrlHarmonic());*/

			AlexaRankingManager arm = new AlexaRankingManager();
			int[] alexaRankings = arm.getAlexaRanking(extLink);

			feat.setAlexaPopularity(alexaRankings[0]);
			feat.setAlexaReachRank(alexaRankings[1]);
			feat.setAlexaDeltaRank(alexaRankings[2]);
			feat.setAlexaCountryRank(alexaRankings[3]);

			System.out
					.println("Alexa Popularity: " + feat.getAlexaPopularity());
			System.out.println("Alexa Reach Rank: " + feat.getAlexaReachRank());
			System.out.println("Alexa Delta Rank: " + feat.getAlexaDeltaRank());
			System.out.println("Alexa Country Rank: "
					+ feat.getAlexaCountryRank());

		}

		/** Additional features depending on the Tweet's language **/

		String lang = TextProcessing.getInstance().getLanguage(str);

		// english
		if (lang.equals("en")) {
			// num of positive sentiment words
			feat.setNumPosSentiWords(getNumSentiWords(AgreementBasedClassification.prop.getProperty("POS_WORDS_ENG_PATH")));
			System.out.println("Number of positive sentiment words: "
					+ feat.getNumPosSentiWords());
			// num of negative sentiment words
			feat.setNumNegSentiWords(getNumSentiWords(AgreementBasedClassification.prop.getProperty("NEG_WORDS_ENG_PATH")));
			System.out.println("Number of negative words: "
					+ feat.getNumNegSentiWords());

			// contains first,second and third order pronoun
			feat.setContainsFirstOrderPron(containsPronoun(AgreementBasedClassification.prop.getProperty("FIRST_PRON_PATH")));
			System.out.println("Contains 1st person pronoun: "
					+ feat.getContainsFirstOrderPron());
			feat.setContainsSecondOrderPron(containsPronoun(AgreementBasedClassification.prop.getProperty("SECOND_PRON_PATH")));
			System.out.println("Contains 2nd person pronoun: "
					+ feat.getContainsSecondOrderPron());
			feat.setContainsThirdOrderPron(containsPronoun(AgreementBasedClassification.prop.getProperty("THIRD_PRON_PATH")));
			System.out.println("Contains 3rd person pronoun: "
					+ feat.getContainsThirdOrderPron());

			// Features only available in english
			// num of slang words
			feat.setNumSlangs(getNumSlangs(AgreementBasedClassification.prop.getProperty("SLANG_ENG_PATH"), "en"));
			System.out.println("Number of slang words: " + feat.getNumSlangs());
			// number of nouns
			feat.setNumNouns(getNumNouns());
			System.out.println("Number of nouns: " + feat.getNumNouns());

			// redability score
			Double readability = getReadability();
			if (readability != null) {
				feat.setReadability(readability);
			}
			System.out.println("Readability score: " + feat.getReadability());

			// spanish
		} else if (lang.equals("es")) {
			feat.setNumPosSentiWords(getNumSentiWords(AgreementBasedClassification.prop.getProperty("POS_WORDS_ES_PATH")));
			System.out.println("Number of positive sentiment words: "
					+ feat.getNumPosSentiWords());
			feat.setNumNegSentiWords(getNumSentiWords(AgreementBasedClassification.prop.getProperty("NEG_WORDS_ES_PATH")));
			System.out.println("Number of negative words: "
					+ feat.getNumNegSentiWords());

			feat.setContainsFirstOrderPron(containsPronoun(AgreementBasedClassification.prop.getProperty("FIRST_PRON_ES_PATH")));
			System.out.println("Contains 1st person pronoun: "
					+ feat.getContainsFirstOrderPron());

			feat.setContainsSecondOrderPron(containsPronoun(AgreementBasedClassification.prop.getProperty("SECOND_PRON_ES_PATH")));
			System.out.println("Contains 2nd person pronoun: "
					+ feat.getContainsSecondOrderPron());

			feat.setContainsThirdOrderPron(containsPronoun(AgreementBasedClassification.prop.getProperty("THIRD_PRON_ES_PATH")));
			System.out.println("Contains 3rd person pronoun: "
					+ feat.getContainsThirdOrderPron());

			feat.setNumSlangs(getNumSlangs(AgreementBasedClassification.prop.getProperty("SLANG_ES_PATH"), "es"));
			System.out.println("Number of slang words: " + feat.getNumSlangs());

			// german
		} else if (lang.equals("de")) {
			feat.setNumPosSentiWords(getNumSentiWords(AgreementBasedClassification.prop.getProperty("POS_WORDS_DE_PATH")));
			System.out.println("Number of positive sentiment words: "
					+ feat.getNumPosSentiWords());

			feat.setNumNegSentiWords(getNumSentiWords(AgreementBasedClassification.prop.getProperty("NEG_WORDS_DE_PATH")));
			System.out.println("Number of negative words: "
					+ feat.getNumNegSentiWords());

			feat.setContainsFirstOrderPron(containsPronoun(AgreementBasedClassification.prop.getProperty("FIRST_PRON_DE_PATH")));
			System.out.println("Contains 1st person pronoun: "
					+ feat.getContainsFirstOrderPron());
			feat.setContainsSecondOrderPron(containsPronoun(AgreementBasedClassification.prop.getProperty("SECOND_PRON_DE_PATH")));
			System.out.println("Contains 2nd person pronoun: "
					+ feat.getContainsSecondOrderPron());
			feat.setContainsThirdOrderPron(containsPronoun(AgreementBasedClassification.prop.getProperty("THIRD_PRON_DE_PATH")));
			System.out.println("Contains 3rd person pronoun: "
					+ feat.getContainsThirdOrderPron());

		}

		System.out.println("-");
		return feat;
	}

	/**
	 * Calculates the number of words contained in the tweet's text
	 * 
	 * @return Integer the number of words found
	 */
	public static Integer getNumItemWords() {

		// create the string from the tweet's title
		// String itemTitle = item.getTitle().toString();

		// call the tokenizer to get the words of the string

		tokens = TextProcessing.getInstance().tokenizeText(text);

		// find the number of words
		Integer numWords = tokens.length;

		return numWords;

	}

	public static Boolean containsSymbol(String symbol) {

		String str = text.replaceAll("http://[^ ]+", " "); // drop urls

		// check if the text contains the given symbol

		// print info
		// System.out.println("Symbol: " + symbol + " " + str.contains(symbol));

		return str.contains(symbol);
	}

	public static Integer getNumSymbol(String symbol) {
		Integer numSymbols = 0;

		// String itemTitle = item.getTitle().toString();

		// check every single character of text for the given symbol
		for (int i = 0; i < text.length(); i++) {
			Character ch = text.charAt(i);
			if (ch.toString().equals(symbol)) {
				numSymbols++;
			}
		}
		// print info
		// System.out.println("num of " + symbol + ": " + numSymbols);
		return numSymbols;
	}

	public static Boolean containsEmo(String filePath) {
		Boolean containsEmo = false;
		BufferedReader br = null;
		// String itemTitle = item.getTitle().toString();

		// hashset that contains the emoticons from the txt file
		HashSet<String> emoticons = new HashSet<String>();

		try {
			File fileEmoticons = new File(filePath);
			if (!fileEmoticons.exists()) {
				fileEmoticons.createNewFile();
			}
			String currentLine;
			// create the file reader
			br = new BufferedReader(new FileReader(fileEmoticons));
			// read the txt file and add each line to the hash set
			while ((currentLine = br.readLine()) != null) {
				emoticons.add(currentLine);
			}

			// use the iterator to get elements from the hashset
			// check if text contains each of the elements
			Iterator<String> iterator = emoticons.iterator();
			while (iterator.hasNext()) {
				String emo = iterator.next().toString();
				if (text.contains(emo)) {
					containsEmo = true;
				}
			}

			br.close();

			// print info
			System.out.println("Contains emoticon: " + containsEmo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return containsEmo;

	}

	public static Integer getNumUppercaseChars() {
		Integer numUppercaseChars = 0;
		Character ch = null;

		// drop all URLs, hashtags and mentions ("http://", "#anyhashtag",
		// "@anymention", "@anymentionwithspace")- no need to count the
		// uppercase
		// chars on them

		String str = text.replaceAll("http://[^ ]+", "")
				.replaceAll("@ [^ ]+ ", "").replaceAll("@[^ ]+", "")
				.replaceAll("#[^ ]+", "");

		// count the uppercase chars
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if (Character.isUpperCase(ch)) {
				numUppercaseChars++;
			}
		}
		if (text.contains("RT ") && numUppercaseChars > 1) {
			numUppercaseChars = numUppercaseChars - 2;
		}
		// print info
		// System.out.println("Num of uppercase chars: " + numUppercaseChars);

		return numUppercaseChars;
	}

	public static Boolean containsPronoun(String filePath) {

		Boolean containsPron = false;
		BufferedReader br = null;

		// hash set that contains the words from the txt file
		HashSet<String> pronounWords = new HashSet<String>();

		try {
			File Prons = new File(filePath);
			if (!Prons.exists()) {
				Prons.createNewFile();
			}
			String currentLine;
			br = new BufferedReader(new FileReader(Prons));

			// save to hashset every line of the txt file
			while ((currentLine = br.readLine()) != null) {
				pronounWords.add(currentLine);
			}

			for (int j = 0; j < tokens.length; j++) {
				if (pronounWords.contains(tokens[j].replaceAll("[^A-Za-z0-9 ]",
						"").toLowerCase())) {
					containsPron = true;
				}
			}

			// print info
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return containsPron;
	}

	public static Integer getNumMentions() {
		Integer numMentions = 0;

		// check if any token is a mention, so if it starts with @
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].startsWith("@")) {
				numMentions++;
			}
		}
		// print info
		// System.out.println("Num of mentions: " + numMentions);
		return numMentions;
	}

	public static Integer getNumHashtags() {
		Integer numHashtags = 0;
		// check if any token is a hashtag, so if it starts with #
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].startsWith("#")) {
				numHashtags++;
			}
		}
		// print info
		// System.out.println("Num of hashtags: " + numHashtags);
		return numHashtags;
	}

	public static Integer getNumURLs() {
		Integer numURLs = 0;
		// String itemTitle = item.getTitle().toString();

		// count the urls by checking if text contains "http" string
		if (text.contains("http://")) {
			numURLs++;
		}
		// print info
		// System.out.println("Num of URLs:" + numURLs);
		return numURLs;
	}

	public static Integer getNumSentiWords(String filePath) {
		Integer numSentiWords = 0;
		BufferedReader br = null;
		// String itemTitle = item.getTitle().toString();

		// use hashset to save the words from the txt file
		HashSet<String> sentiwords = new HashSet<String>();
		try {
			File sentiWords = new File(filePath);
			if (!sentiWords.exists()) {
				sentiWords.createNewFile();
			}
			String currentLine;
			br = new BufferedReader(new FileReader(sentiWords));

			while ((currentLine = br.readLine()) != null) {
				sentiwords.add(currentLine);
			}

			for (int i = 0; i < tokens.length; i++) {

				String clearToken = tokens[i].replaceAll("[^A-Za-z0-9 ]", "")
						.toLowerCase();

				if (sentiwords.contains(clearToken)) {
					numSentiWords++;
					// print info
					// System.out.println("Senti word found:" + tokens[i]);
				}
			}

			// print info
			System.out.println("Number of senti words: " + numSentiWords);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return numSentiWords;
	}

	/*
	 * public static Long getRetweetsCount(Item item) { Long numRetweets = 0L;
	 * 
	 * if (item.getPopularity() != null) { numRetweets =
	 * Long.parseLong(item.getPopularity().get("retweets").toString());
	 * 
	 * } else { numRetweets = 0L; }
	 * 
	 * return numRetweets; }
	 * 
	 * public static Integer getRetweetsCount2(Item item) { Integer numRetweets
	 * = 0;
	 * 
	 * Document doc = null;
	 * 
	 * String link = "http://twitter.com/" + item.getStreamUser().getUsername()
	 * + "/status/" + item.getId().split("Twitter::")[1]; // String perma = //
	 * "https://twitter.com/carlosshue/status/263045401291157504"; try { doc =
	 * Jsoup.connect(link).get(); } catch (IOException e) { doc = null; } if
	 * (doc != null) { Elements els = doc.select(".js-stat-retweets"); String
	 * str = els.select("strong").text().replace(",", "");
	 * 
	 * if (str.contains("K")) { str = str.replace(".", "").replace("K",
	 * "").concat("00"); } else if (str.contains("M")) { str = str.replace(".",
	 * "").replace("M", "").concat("000"); }
	 * 
	 * if (str.equals("")) { numRetweets = 0; } else { numRetweets =
	 * Integer.parseInt(str);
	 * 
	 * } }
	 * 
	 * return numRetweets; }
	 */

	public static HashSet<String> loadFakeWords(String filePath) {
		BufferedReader br = null;
		HashSet<String> hs = new HashSet<String>();
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(filePath));
			while ((sCurrentLine = br.readLine()) != null) {
				hs.add(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return hs;
	}

	public static Integer getNumSlangs(String filePath, String lang)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {

		// declare the class to use
		Class stemClass = Class
				.forName("org.tartarus.snowball.ext.spanishStemmer");

		// declare the auxiliary variables
		Integer numSlangs = 0, foundCounter = 0, indexHolder = 0, prevIndexHolder = 0, i = 0;

		// array of the tokens of the tweet text
		String[] justTokens = new String[tokens.length];

		// variable to declare the Word attributes (by stanford parser)
		Morphology m = new Morphology();

		// buffered to read the file containing the slangs
		BufferedReader br = null;

		try {
			File slangWords = new File(filePath);
			if (!slangWords.exists()) {
				slangWords.createNewFile();
			}
			String currentLine;
			br = new BufferedReader(new FileReader(slangWords));

			String wrdResult = null;
			// create the hashset that contains the tokens
			for (String token : tokens) {

				token = token.replaceAll("[^A-Za-z0-9 ]", "");
				if (token != null) {
					if (lang == "en") {

						Word wrd = new Word(token);
						wrd = m.stem(wrd);
						wrdResult = wrd.toString();
						// System.out.println(token + " " + wrdResult);

					} else if (lang == "es") {

						SnowballStemmer stemmer = (SnowballStemmer) stemClass
								.newInstance();
						stemmer.setCurrent(token);
						stemmer.stem();
						wrdResult = stemmer.getCurrent();
						// System.out.println(token + " " + wrdResult);

					}
					// titletokens.add(wrdResult);
					justTokens[i] = wrdResult;
					i++;
				}
				// System.out.print(wrdResult+" ");
			}

			// check every line of the file
			while ((currentLine = br.readLine()) != null) {
				String regex = " ";
				if (currentLine.contains("-")) {
					if (currentLine.indexOf("-") != 0
							|| (currentLine.indexOf("-") != (currentLine
									.length() - 1))) {
						regex = "-";
					}
				}
				String[] words = currentLine.split(regex);
				foundCounter = 0;
				indexHolder = 0;
				prevIndexHolder = 0;

				for (String word : words) {

					String prefix = "#";
					if (word.endsWith("-")) {
						prefix = word.replace("-", "");
					}
					String suffix = "!";
					if (word.startsWith("-")) {
						suffix = word.replace("-", "");
					}
					if (lang == "en") {

						Word wrd = new Word(word);
						wrd = m.stem(wrd);
						word = wrd.toString();

					} else if (lang == "es") {
						SnowballStemmer stemmer = (SnowballStemmer) stemClass
								.newInstance();
						stemmer.setCurrent(word);
						stemmer.stem();
						word = stemmer.getCurrent();
						// System.out.println(word+" "+wrdResult);
					}

					for (String token : justTokens) {
						if (token != null) {
							if (token.equals(word) || token.startsWith(prefix)
									|| token.endsWith(suffix)) {
								indexHolder = Arrays.asList(justTokens)
										.indexOf(word);
								// System.out.println(token+" "+word);
								// System.out.println();
								// System.out.println("Found " + word +
								// " with index "+ indexHolder);

								if (indexHolder > prevIndexHolder) {
									prevIndexHolder = indexHolder;
									foundCounter++;
								}
								if (words.length == 1) {
									foundCounter++;
									// System.out.println(foundCounter);
									break;
								}
							}
						}
					}

				}
				// System.out.println(foundCounter);

				if (foundCounter == words.length) {
					numSlangs++;
					// System.out.println("num slangs " + numSlangs+
					// " for phrase ");
					for (String p : words) {
						// System.out.println("- " + p);
					}
				}

			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("NUM TO RETURN " + numSlangs);
		return numSlangs;
	}

	/*
	 * public static Long getTimeFromStart(Item item) {
	 * 
	 * Long timeFromStart = 0L; timeFromStart = item.getPublicationTime() -
	 * startTime;
	 * 
	 * String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
	 * .format(new java.util.Date(item.getPublicationTime() * 1000)); //
	 * System.out.println(item.getId()); // System.out.println(timeFromStart +
	 * " "+ date);
	 * 
	 * return timeFromStart; }
	 */

	public static Integer getNumNouns() {
		Integer numNouns = 0;

		List<CoreLabel> rawWords = Sentence.toCoreLabelList(tokens);

		Tree tree = lp.apply(rawWords);

		for (Tree subtree : tree) {
			if (Vars.LABELS.contains(subtree.label().value())
					&& !Vars.STOP_WORDS.contains(subtree.getChild(0).value())) {
				numNouns++;
				// System.out.println("child = "+subtree.getChild(0));
			}
		}
		// print info
		// System.out.println("Nouns found " + numNouns);
		return numNouns;
	}

	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

	public static String getUrl() {

		String str;

		int index = itemTitle.indexOf("http");
		str = itemTitle.substring(index);
		str = str.split(" ")[0];

		return str;
	}

	/**
	 * Expands the given shortened url
	 * 
	 * @param shortenedUrl
	 *            String that holds the shortened url
	 * @return String long transformed url
	 */
	public static String expandUrl(String shortenedUrl) {

		String expandedURL = null;
		try {
			URL url = new URL(shortenedUrl);

			// open connection
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection(Proxy.NO_PROXY);

			// stop following browser redirect
			httpURLConnection.setInstanceFollowRedirects(false);

			// extract location header containing the actual destination URL
			expandedURL = httpURLConnection.getHeaderField("Location");
			httpURLConnection.disconnect();
		} catch (IOException e) {
			expandedURL = null;
		}

		return expandedURL;
	}

	/**
	 * Expands the given shortened url
	 * 
	 * @param shortUrl
	 *            String that holds the shortened url
	 * @return String long transformed url
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static String getLongUrl(String shortUrl)
			throws MalformedURLException, IOException {
		String result = shortUrl;
		String header;
		try {
			do {
				URL url = new URL(result);
				HttpURLConnection.setFollowRedirects(false);
				URLConnection conn = url.openConnection();
				header = conn.getHeaderField(null);
				System.out.println(header);
				String location = conn.getHeaderField("location");
				if (location != null) {
					result = location;
				}
			} while (header.contains("301"));
		} catch (Exception e) {
			System.out.println("header not found");
		}

		return result;
	}

	static ArrayList<Integer[]> occurrenceArr = new ArrayList<Integer[]>();

	/**
	 * Function that given a string, a pattern and an initial index, finds the
	 * indexes where the pattern is observed. The method is recursive, given as
	 * initial index for searching, the last index of the previous iteration.
	 * 
	 * @param str
	 *            String that holds the adapted text of the original tweet text
	 * @param pattern
	 *            String that holds the pattern whose existence is checked
	 * @param index
	 *            the start index from where the pattern is searched
	 * @return ArrayList<Integer[]> list of the pairs [startIndex, endIndex] of
	 *         the patern occurrences found
	 */
	public static ArrayList<Integer[]> findPatternOccurrence(String str,
			String pattern, int index) {

		Integer occurStart = str.indexOf("http", index);
		// System.out.println("start " + occurStart);
		// System.out.println("string length "+str.length());
		Integer[] occurs = new Integer[2];
		Integer occurEnd = -1;
		if (occurStart != -1) {
			occurs[0] = occurStart;
			occurEnd = str.indexOf(" ", occurStart);

			if (str.substring(occurStart, occurEnd).contains("\n")) {
				occurEnd = str.indexOf("\n", occurStart);

			}
			// System.out.println("end " + occurEnd);
		}

		if (occurEnd.equals(-1)) {
			occurs[1] = str.length();
			// System.out.println("end again "+occurs[1]);
		} else {
			occurs[1] = occurEnd;
		}
		// System.out.println("string "+itemTitle.substring(occurStart,occurs[1]));

		if (occurs[0] != null && occurs[1] != null
				&& ((occurs[1] - occurs[0]) > 7)) {
			// System.out.println("occurs before added "+occurs[0]+" "+occurs[1]);
			occurrenceArr.add(occurs);
		}
		// System.out.println(occurEnd+" "+occurStart);
		if (occurEnd < str.length() && !occurStart.equals(-1)
				&& !occurEnd.equals(-1)) {
			// System.out.println("hello");
			findPatternOccurrence(str, pattern, occurEnd + 1);

		}

		return occurrenceArr;
	}

	/**
	 * Function that finds in the tweet text the external links provided (if
	 * any) except for the links of the media content provided. The steps
	 * followed: 1. Erase all the useless characters from the text. 2. Call the
	 * findPatternOccurrence(text, "http://", 0) to spot the pairs of indexes
	 * [startIndex, endIndex] of the links that exist in the text (see
	 * findPatternOccurrence method for more). 3. For each link found, check if
	 * it is the same with the media link or not. 4. Keep as external links, all
	 * the links except for the media links provided.
	 * 
	 * @param item
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static HashSet<String> checkForExternalLinks(JSONObject json)
			throws MalformedURLException, IOException, InterruptedException {

		// define the list which handles the urls found in the tweet text
		List<String> urls = new ArrayList<String>();
		// check the urls array
		try {
			JSONArray arrayUrls = json.getJSONObject("entities").getJSONArray(
					"urls");
			for (int i = 0; i < arrayUrls.length(); i++) {
				urls.add(arrayUrls.getJSONObject(0).getString("expanded_url"));
				System.out.println("url " +	 arrayUrls.getJSONObject(0).getString("expanded_url"));

			}
		} catch (Exception e) {
			// System.out.println("No urls found");
		}
		// ..check the media array too
		try {
			JSONArray arrayMedia = json.getJSONObject("entities").getJSONArray(
					"media");
			for (int i = 0; i < arrayMedia.length(); i++) {
				urls.add(arrayMedia.getJSONObject(0).getString("media_url"));
				System.out.println("url "
						+ arrayMedia.getJSONObject(0).getString("media_url"));

			}
		} catch (Exception e) {
			// System.out.println("No media urls found");
		}

		// define the external links list
		HashSet<String> extLinks = new HashSet<String>();
		Image image = null;

		for (String url : urls) {

			boolean isAnImage = URLProcessing.getInstance().isAnImage(url);
			// checking each url if it is an Image or not

			if (isAnImage) {
				System.out.println(url + " is an Image!");
			} else {
				// handle instagram
				System.out.println("Checking " + url+ " for external link possibility...");
				
				//instagram
				url = WebOfTrustManager.getInstance().handleInstagram(url);
				// System.out.println("Instagram ok!");

				String longUrl = WebOfTrustManager.getInstance().expandUrl(url);
				if (longUrl == null || longUrl.length()<3)
					longUrl = url;
				System.out.println(url + " converted to expanded as " + longUrl);

				if (URLProcessing.getInstance().isAppropriateUrl(longUrl)) {
					extLinks.add(longUrl);
					System.out.println(longUrl+ " finally added as an external!");
				}
			}

		}

		// info
		 System.out.println("External links found: " + extLinks);

		return extLinks;
	}

	/**
	 * Function that finds in the tweet text the external links provided (if
	 * any) except for the links of the media content provided. The steps
	 * followed: 1. Erase all the useless characters from the text. 2. Call the
	 * findPatternOccurrence(text, "http://", 0) to spot the pairs of indexes
	 * [startIndex, endIndex] of the links that exist in the text (see
	 * findPatternOccurrence method for more). 3. For each link found, check if
	 * it is the same with the media link or not. 4. Keep as external links, all
	 * the links except for the media links provided.
	 * 
	 * @param item
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */

	/**
	 * Organizes the procedure of computing the readability of the tweet's text
	 * 
	 * @return Double the calculated value of readability
	 */
	public static Double getReadability() {

		Double readability = null;

		String str = text.replaceAll("http+s*+://[^ ]+", "")
				.replaceAll("#[^ ]+ ", "").replaceAll("@[^ ]+", "");
		str = str.replaceAll("  ", " ");

		if (!str.isEmpty()) {
			Readability r = new Readability(str);
			readability = r.getFleschReadingEase();

		}
		return readability;
	}

	public static Float organizeRunRank(String caseRank, String url,
			String filePath) throws InterruptedException, ExecutionException {

		Float result = null;

		int numberThreads = 114;

		ExecutorService service = Executors.newFixedThreadPool(5);
		CompletionService<Float> completionService = new ExecutorCompletionService<Float>(
				service);

		for (int i = 0; i < numberThreads; i++) {
			completionService.submit(new RunnableRank(caseRank + "_thread" + i,
					url, filePath + "_" + (i + 1) + ".tsv"));
		}

		try {
			for (int t = 0, n = numberThreads; t < n; t++) {
				Future<Float> f = completionService.take();
				Float val = f.get();
				// System.out.print("RESULT FOUND: " + val);
				// System.out.println();
				if (val != null) {
					result = val;
					service.shutdownNow();
					// System.out.println("break!");
					break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		} catch (ExecutionException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		} finally {
			if (service != null) {
				service.shutdownNow();
			}
		}

		return result;
	}

	public static List<URL> defineUrls(String filePath) throws Exception {
		List<URL> listUrls = new ArrayList<URL>();
		BufferedReader br = null;

		try {
			File sentiWords = new File(filePath);
			if (!sentiWords.exists()) {
				sentiWords.createNewFile();
			}
			String currentLine;
			br = new BufferedReader(new FileReader(sentiWords));

			while ((currentLine = br.readLine()) != null) {
				listUrls.add(new URL(currentLine));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		br.close();
		return listUrls;
	}

	static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
			.create();

	public static synchronized ItemFeatures create(String json) {
		synchronized (gson) {
			ItemFeatures item = gson.fromJson(json, ItemFeatures.class);
			return item;
		}
	}

	
	/*public List<ItemFeatures> getLatestItems(String dbString,
			String collection, int n) {

		List<ItemFeatures> results = new ArrayList<ItemFeatures>();
		Mongo mongo;
		try {
			mongo = new MongoClient(Vars.LOCALHOST_IP);
			DB db = mongo.getDB(dbString);
			DBCollection coll = db.getCollection(collection);
			System.out.println("Fetching items from " + db + " " + collection
					+ "...");

			List<String> jsonStrings = new ArrayList<String>();
			DBCursor cursor = coll.find();
			while (cursor.hasNext()) {
				String s = JSON.serialize(cursor.next());
				jsonStrings.add(s);
			}

			for (String json : jsonStrings) {
				results.add(create(json));
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return results;

	}*/

	/*public List<String> getExistingTweetsIds(String dbString, String coll,
			String idFieldName) throws UnknownHostException {

		List<String> ids = new ArrayList<String>();

		Mongo mongo = new MongoClient(Vars.LOCALHOST_IP);
		DB db = mongo.getDB(dbString);

		// get a single collection
		DBCollection collection = db.getCollection(coll);

		DBCursor cursor = collection.find();

		while (cursor.hasNext()) {
			String s = JSON.serialize(cursor.next());
			JSONObject json = new JSONObject(s);

			String id = json.getString(idFieldName).replaceAll("[^\\d.]", "");
			ids.add(id);

		}

		return ids;
	}*/
	
	/*public static void main(String[] args) throws Exception {
		
		MongoHandler mh = null;
		try {
			mh = new MongoHandler(Vars.LOCALHOST_IP, "TestSetFeaturesAll");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<JSONObject> jsons = DBHandler.getInstance().getExistingTweets("TestSet", "GarissaAttackFakes");

		List<String> idsexist = DBHandler.getInstance().getExistingTweetsIds("TestSetFeaturesAll", "GarissaAttack_fake_Item", "id");
		
		for (JSONObject json : jsons) {
			if (!idsexist.contains(json.getString("id_str"))) {
				ItemFeatures ifeats = extractFeatures(json);
				mh.insert(ifeats, "GarissaAttack_fake_Item");
			}
		}
		

		

	}*/

}
