package gr.iti.mklab.extractfeatures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.tartarus.snowball.SnowballStemmer;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.Morphology;
import edu.stanford.nlp.trees.Tree;
import gr.iti.mklab.util.URLProcessing;
import gr.iti.mklab.util.WebOfTrustManager;
import gr.iti.mklab.readability.Readability;
import gr.iti.mklab.util.AlexaRankingManager;
import gr.iti.mklab.util.Configuration;
import gr.iti.mklab.util.RunnableRank;
import gr.iti.mklab.util.TextProcessing;
import weka.core.Instances;

public class TweetFeaturesExtractor {
	
	static String[] tokens;
	static String itemTitle;
	public static Instances isTrainingSet;
	// public static Long startTime = 1350864000L;
	public static final String MODEL_PARSER = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
	public static LexicalizedParser lp = LexicalizedParser
			.loadModel(MODEL_PARSER);
	public static List<String> indegreeLines = new ArrayList<String>();
	public static List<String> harmonicLines = new ArrayList<String>();

	private static final Logger LOGGER = Logger.getLogger( TweetFeaturesExtractor.class.getName() );
	

	
	static String text = "";
	

	
	
	
	public static TweetFeatures extractFeatures(JSONObject json)
			throws Exception {

		LOGGER.setLevel(Level.OFF);
		String currentTweetId = json.getString("id_str");
		//LOGGER.setLevel(Level.OFF);
		// info
		LOGGER.info("Extracting Item features for the " + currentTweetId);

		// define the ItemFeatures object that holds the features
		TweetFeatures feat = new TweetFeatures();
		
		try {
			// if it is retweet
			text = json.getJSONObject("retweeted_status").getString("text");
		} catch (Exception e) {
			// if it is simple tweet
			text = json.getString("text");
		}
		//System.out.println("Text " + text);

		// preprocess the text
		String str = text.replaceAll("http+s*+://[^ ]+", "")
				.replaceAll("@[^ ]+", "").replaceAll("#[^ ]+ ", "")
				.replaceAll("RT", "").toLowerCase().trim();
		/** Features depending on the Item(Tweet) **/
		// id
		feat.setId(currentTweetId);

		feat.setItemLength(text.length());
		LOGGER.info("Tweet text length: " + text.length());

		feat.setNumWords(getNumItemWords());
		LOGGER.info("Number of words: " + feat.getNumWords());
		
		
		feat.setContainsQuestionMark(containsSymbol("?"));
		LOGGER.info("Contains question mark "
				+ feat.getContainsQuestionMark());

		feat.setContainsExclamationMark(containsSymbol("!"));
		LOGGER.info("Contains exclamation mark "
				+ feat.getContainsExclamationMark());
		
		feat.setNumExclamationMark(getNumSymbol("!"));
		LOGGER.info("Number of exclamation marks: "
				+ feat.getNumExclamationMark());
	
		feat.setNumQuestionMark(getNumSymbol("?"));
		LOGGER.info("Number of question marks: "
				+ feat.getNumQuestionMark());
	
		feat.setContainsHappyEmo(containsEmo(Configuration.RESOURCES_PATH + "/emoticons/happy-emoticons.txt"));
		LOGGER.info("Contains happy emoticon: "
				+ feat.getContainsHappyEmo());
		
		feat.setContainsSadEmo(containsEmo(Configuration.RESOURCES_PATH + "/emoticons/sad-emoticons.txt"));	
		LOGGER.info("Contains sad emoticon: " + feat.getContainsSadEmo());
		
		feat.setNumUppercaseChars(getNumUppercaseChars());
		LOGGER.info("Number of uppercase characters: "
				+ feat.getNumUppercaseChars());
		
		int numMentions = json.getJSONObject("entities")
				.getJSONArray("user_mentions").length();
		feat.setNumMentions(numMentions);
		LOGGER.info("Number of mentions: " + numMentions);
		
		int numHashtags = json.getJSONObject("entities")
				.getJSONArray("hashtags").length();
		feat.setNumHashtags(numHashtags);
		LOGGER.info("Number of hashtags: " + numHashtags);

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
		LOGGER.info("Number of urls " + (numURLs + numURLs2));

		Long retweetCount = json.getLong("retweet_count");
		feat.setRetweetCount(retweetCount);
		LOGGER.info("Retweet count: " + retweetCount);

		feat.setHasColon(containsSymbol(":"));
		LOGGER.info("Has ':' symbol: " + feat.getHasColon());

		feat.setHasPlease(containsSymbol("please"));
		LOGGER.info("Has 'please' word: " + feat.getHasPlease());
		// external links (except for the image link)

		HashSet<String> extLinks = checkForExternalLinks(json);
		feat.setHasExternalLink(!(extLinks.isEmpty()));
		LOGGER.info("Number of external links: "
				+ feat.getHasExternalLink());
		
		LOGGER.info("External links: " + extLinks);

		WebOfTrustManager wot = new WebOfTrustManager();

		for (String extLink : extLinks) {

			Integer[] values = wot.getWotValues(extLink);

			if (values[0] != 0 && values[1] != 0) {
				feat.setWotTrust(values[0]);
			}
			// info
			LOGGER.info("For " + extLink + ": ");
			LOGGER.info("WOT trust value: " + feat.getWotTrust());

			// some necessary preprocessing
			extLink = URLProcessing.getInstance()
					.processUrlForRunnable(extLink);

			Float indegree = organizeRunRank("indegree-" + currentTweetId,
					extLink, Configuration.RESOURCES_PATH + "/centralities/hostgraph-indegree_split_1/hostgraph-indegree");

			Float harmonic = null;

			if (indegree != null) // if only there is indegree value, search for
									// harmonic
				harmonic = organizeRunRank("harmonic-" + currentTweetId,
						extLink, Configuration.RESOURCES_PATH + "/centralities/hostgraph-h_split_2/hostgraph-h");

			feat.setUrlIndegree(indegree);
			feat.setUrlHarmonic(harmonic);
			LOGGER.info("In-degree centrality value: "
					+ feat.getUrlIndegree());
			LOGGER.info("Harmonic centrality value: "
					+ feat.getUrlHarmonic());

			AlexaRankingManager arm = new AlexaRankingManager();
			Integer[] alexaRankings = arm.getAlexaRanking(extLink);

			feat.setAlexaPopularity(alexaRankings[0]);
			feat.setAlexaReachRank(alexaRankings[1]);
			feat.setAlexaDeltaRank(alexaRankings[2]);
			feat.setAlexaCountryRank(alexaRankings[3]);

			LOGGER.info("Alexa Popularity: " + feat.getAlexaPopularity());
			LOGGER.info("Alexa Reach Rank: " + feat.getAlexaReachRank());
			LOGGER.info("Alexa Delta Rank: " + feat.getAlexaDeltaRank());
			LOGGER.info("Alexa Country Rank: "
					+ feat.getAlexaCountryRank());

		}

		//** Additional features depending on the Tweet's language **//*

		String lang = TextProcessing.getInstance().getLanguage(str);

		// english
		if (lang.equals("en")) {
			// num of positive sentiment words
			feat.setNumPosSentiWords(getNumSentiWords(Configuration.RESOURCES_PATH + "/senti_words/positive-words.txt"));
			LOGGER.info("Number of positive sentiment words: "
					+ feat.getNumPosSentiWords());
			// num of negative sentiment words
			LOGGER.info("Number of negative words: " + Configuration.RESOURCES_PATH + "/senti_words/negative-words.txt");
			feat.setNumNegSentiWords(getNumSentiWords(Configuration.RESOURCES_PATH + "/senti_words/negative-words.txt"));
			LOGGER.info("Number of negative words: "
					+ feat.getNumNegSentiWords());

			// contains first,second and third order pronoun
			feat.setContainsFirstOrderPron(containsPronoun(Configuration.RESOURCES_PATH + "/pronouns/first-order-prons.txt"));
			LOGGER.info("Contains 1st person pronoun: "
					+ feat.getContainsFirstOrderPron());
			feat.setContainsSecondOrderPron(containsPronoun(Configuration.RESOURCES_PATH + "/pronouns/second-order-prons.txt"));
			LOGGER.info("Contains 2nd person pronoun: "
					+ feat.getContainsSecondOrderPron());
			feat.setContainsThirdOrderPron(containsPronoun(Configuration.RESOURCES_PATH + "/pronouns/third-order-prons.txt"));
			LOGGER.info("Contains 3rd person pronoun: "
					+ feat.getContainsThirdOrderPron());

			// Features only available in english
			// num of slang words
			feat.setNumSlangs(getNumSlangs(Configuration.RESOURCES_PATH + "/slang_words/slangwords-english.txt", "en"));
			LOGGER.info("Number of slang words: " + feat.getNumSlangs());
			// number of nouns
			feat.setNumNouns(getNumNouns(Configuration.RESOURCES_PATH + "/stanford-labels.txt"));
			LOGGER.info("Number of nouns: " + feat.getNumNouns());

			// redability score
			Double readability = getReadability();
			if (readability != null) {
				feat.setReadability(readability);
			}
			LOGGER.info("Readability score: " + feat.getReadability());

			// spanish
		} else if (lang.equals("es")) {
			feat.setNumPosSentiWords(getNumSentiWords(Configuration.RESOURCES_PATH + "/senti_words/positive-words-spanish.txt"));
			LOGGER.info("Number of positive sentiment words: "
					+ feat.getNumPosSentiWords());
			feat.setNumNegSentiWords(getNumSentiWords(Configuration.RESOURCES_PATH + "/senti_words/negative-words-spanish.txt"));
			LOGGER.info("Number of negative words: "
					+ feat.getNumNegSentiWords());

			feat.setContainsFirstOrderPron(containsPronoun(Configuration.RESOURCES_PATH + "/pronouns/first-order-prons-spanish.txt"));
			LOGGER.info("Contains 1st person pronoun: "
					+ feat.getContainsFirstOrderPron());

			feat.setContainsSecondOrderPron(containsPronoun(Configuration.RESOURCES_PATH + "/pronouns/second-order-prons-spanish.txt"));
			LOGGER.info("Contains 2nd person pronoun: "
					+ feat.getContainsSecondOrderPron());

			feat.setContainsThirdOrderPron(containsPronoun(Configuration.RESOURCES_PATH + "/pronouns/third-order-prons-spanish.txt"));
			LOGGER.info("Contains 3rd person pronoun: "
					+ feat.getContainsThirdOrderPron());

			feat.setNumSlangs(getNumSlangs(Configuration.RESOURCES_PATH + "/slang_words/slangwords-spanish.txt", "es"));
			LOGGER.info("Number of slang words: " + feat.getNumSlangs());

			// german
		} else if (lang.equals("de")) {
			feat.setNumPosSentiWords(getNumSentiWords(Configuration.RESOURCES_PATH + "/senti_words/positive-words-german.txt"));
			LOGGER.info("Number of positive sentiment words: "
					+ feat.getNumPosSentiWords());

			feat.setNumNegSentiWords(getNumSentiWords(Configuration.RESOURCES_PATH + "/senti_words/negative-words-german.txt"));
			LOGGER.info("Number of negative words: "
					+ feat.getNumNegSentiWords());

			feat.setContainsFirstOrderPron(containsPronoun(Configuration.RESOURCES_PATH + "/pronouns/first-order-prons-german.txt"));
			LOGGER.info("Contains 1st person pronoun: "
					+ feat.getContainsFirstOrderPron());
			feat.setContainsSecondOrderPron(containsPronoun(Configuration.RESOURCES_PATH + "/pronouns/second-order-prons-german.txt"));
			LOGGER.info("Contains 2nd person pronoun: "
					+ feat.getContainsSecondOrderPron());
			feat.setContainsThirdOrderPron(containsPronoun(Configuration.RESOURCES_PATH + "/pronouns/third-order-prons-german.txt"));
			LOGGER.info("Contains 3rd person pronoun: "
					+ feat.getContainsThirdOrderPron());

		}
		//feat.setAnnotation("fake");
		System.out.println("-");
		return feat;
	}
	
	
	/**
	 * Calculates the number of words contained in the tweet's text
	 * 
	 * @return Integer the number of words found
	 */
	public static Integer getNumItemWords() {

		tokens = TextProcessing.getInstance().tokenizeText(text);
		// find the number of words
		Integer numWords = tokens.length;
		return numWords;
	}
	
	/**
	 * Checks if the given symbol exists in text
	 * 
	 * @param symbol
	 * @return
	 */
	
	public static Boolean containsSymbol(String symbol) {

		String str = text.replaceAll("http://[^ ]+", " "); // drop urls
		return str.contains(symbol);
	}
	
	/**
	 * Check every single character of text for the given symbol and calculate 
	 * the number of occurrences of the symbol in the sentence 
	 * @param symbol
	 * @return
	 */
	
	public static Integer getNumSymbol(String symbol) {
		Integer numSymbols = 0;

		for (int i = 0; i < text.length(); i++) {
			Character ch = text.charAt(i);
			if (ch.toString().equals(symbol)) {
				numSymbols++;
			}
		}
		return numSymbols;
	}
	
	/**
	 * Check if text contains emoticon
	 * Emoticons are read from a text file
	 * @param filePath
	 * @return
	 */
	
	public static Boolean containsEmo(String filePath) {
		Boolean containsEmo = false;
		// hashset that contains the emoticons from the txt file
		Set<String> emoticons = new HashSet<String>();
		try {
			File fileEmoticons = new File(filePath);
			if (!fileEmoticons.exists()) {
				System.out.println("FIle emoticons does not exist " + filePath);
				fileEmoticons.createNewFile();
			}
			emoticons = Files.lines(new File(filePath).toPath()).collect(Collectors.toSet());		
			// use the iterator to get elements from the hashset
			// check if text contains each of the elements
			Iterator<String> iterator = emoticons.iterator();
			while (iterator.hasNext()) {
				String emo = iterator.next().toString();
				if (text.contains(emo)) {
					containsEmo = true;
				}
			}				
			LOGGER.info("Contains emoticon: " + containsEmo);		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return containsEmo;
	}
	
	/**
	 * Count number of Uppercase characters
	 * @return
	 */
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
		// Remove RT if exists
		if (text.contains("RT ") && numUppercaseChars > 1) {
			numUppercaseChars = numUppercaseChars - 2;
		}

		return numUppercaseChars;
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
				LOGGER.info("url " +	 arrayUrls.getJSONObject(0).getString("expanded_url"));

			}
		} catch (Exception e) {
		}
		// ..check the media array too
		try {
			JSONArray arrayMedia = json.getJSONObject("entities").getJSONArray(
					"media");
			for (int i = 0; i < arrayMedia.length(); i++) {
				urls.add(arrayMedia.getJSONObject(0).getString("media_url"));
				LOGGER.info("url "
						+ arrayMedia.getJSONObject(0).getString("media_url"));

			}
		} catch (Exception e) {
			// System.out.println("No media urls found");
		}

		// define the external links list
		HashSet<String> extLinks = new HashSet<String>();
		
		for (String url : urls) {

			boolean isAnImage = URLProcessing.getInstance().isAnImage(url);
			// checking each url if it is an Image or not

			if (isAnImage) {
				LOGGER.info(url + " is an Image!");
			} else {
				// handle instagram
				LOGGER.info("Checking " + url+ " for external link possibility...");
				
				//instagram
				url = WebOfTrustManager.getInstance().handleInstagram(url);

				String longUrl = WebOfTrustManager.getInstance().expandUrl(url);
				if (longUrl == null || longUrl.length()<3)
					longUrl = url;
				LOGGER.info(url + " converted to expanded as " + longUrl);

				if (URLProcessing.getInstance().isAppropriateUrl(longUrl)) {
					extLinks.add(longUrl);
					LOGGER.info(longUrl+ " finally added as an external!");
				}
			}

		}

		LOGGER.info("External links found: " + extLinks);

		return extLinks;
	}
	
	
	public static Float organizeRunRank(String caseRank, String url, String type) throws InterruptedException, ExecutionException {

		Float result = null;

		int numberThreads = 114;

		ExecutorService service = Executors.newFixedThreadPool(5);
		CompletionService<Float> completionService = new ExecutorCompletionService<Float>(
				service);

		for (int i = 0; i < numberThreads; i++) {
			completionService.submit(new RunnableRank(caseRank + "_thread" + i,
					url, type + "_" + (i + 1) + ".tsv"));
		}

		try {
			for (int t = 0, n = numberThreads; t < n; t++) {
				Future<Float> f = completionService.take();
				Float val = f.get();
				if (val != null) {
					result = val;
					service.shutdownNow();
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
	

	public static Integer getNumSentiWords(String filePath) {
		Integer numSentiWords = 0;
	 // use hashset to save the words from the txt file
		Set<String> sentiwords = new HashSet<String>();
		
		try {
			sentiwords = Files.lines(new File(filePath).toPath()).collect(Collectors.toSet());	

			for (int i = 0; i < tokens.length; i++) {
				String clearToken = tokens[i].replaceAll("[^A-Za-z0-9 ]", "")
						.toLowerCase();
				if (sentiwords.contains(clearToken)) {
					numSentiWords++;
				}
			}
			// print info
			LOGGER.info("Number of senti words: " + numSentiWords);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return numSentiWords;
	}
	
	public static Boolean containsPronoun(String filePath) {

		Boolean containsPron = false;

		// hash set that contains the words from the txt file
		Set<String> pronounWords = new HashSet<String>();

		try {			
			pronounWords = Files.lines(new File(filePath).toPath()).collect(Collectors.toSet());				
			for (int j = 0; j < tokens.length; j++) {
				if (pronounWords.contains(tokens[j].replaceAll("[^A-Za-z0-9 ]",
						"").toLowerCase())) {
					containsPron = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return containsPron;
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
			for (String token : tokens) {

				token = token.replaceAll("[^A-Za-z0-9 ]", "");
				if (token != null) {
					if (lang == "en") {

						Word wrd = new Word(token);
						wrd = m.stem(wrd);
						wrdResult = wrd.toString();
					} else if (lang == "es") {

						SnowballStemmer stemmer = (SnowballStemmer) stemClass
								.newInstance();
						stemmer.setCurrent(token);
						stemmer.stem();
						wrdResult = stemmer.getCurrent();
					}
					justTokens[i] = wrdResult;
					i++;
				}
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
					}

					for (String token : justTokens) {
						if (token != null) {
							if (token.equals(word) || token.startsWith(prefix)
									|| token.endsWith(suffix)) {
								indexHolder = Arrays.asList(justTokens)
										.indexOf(word);
								if (indexHolder > prevIndexHolder) {
									prevIndexHolder = indexHolder;
									foundCounter++;
								}
								if (words.length == 1) {
									foundCounter++;
									break;
								}
							}
						}
					}

				}
			
				if (foundCounter == words.length) {
					numSlangs++;
				}

			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return numSlangs;
	}
	
	/**
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
	
	
	public static Integer getNumNouns(String filePath_stafordLavels) {
		Integer numNouns = 0;

		List<CoreLabel> rawWords = SentenceUtils.toCoreLabelList(tokens);

		Tree tree = lp.apply(rawWords);
		
		Set<String> stafordLabels = new HashSet<String>();
		String[] set_stopwords = {"rt"};
		Set<String> stopwords = new HashSet<String>(Arrays.asList(set_stopwords));

		try {			
			stafordLabels = Files.lines(new File(filePath_stafordLavels).toPath()).collect(Collectors.toSet());				
			for (Tree subtree : tree) {
				if (stafordLabels.contains(subtree.label().value())
						&& !stopwords.contains(subtree.getChild(0).value())) {
					numNouns++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return numNouns;
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


}
