package gr.iti.mklab.extractfeatures;

import eu.socialsensor.framework.client.mongo.MongoHandler;
import eu.socialsensor.geo.Countrycoder;
import gr.iti.mklab.utils.AlexaRankingManager;
import gr.iti.mklab.utils.RunnableRank;
import gr.iti.mklab.utils.StringProcessing;
import gr.iti.mklab.utils.TextProcessing;
import gr.iti.mklab.utils.URLProcessing;
import gr.iti.mklab.utils.Vars;
import gr.iti.mklab.utils.WebOfTrustManager;
import gr.iti.mklab.verify.AgreementBasedClassification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class UserFeaturesExtractorJSON {

	// latitude and longitude of event's location (New York)
	static double[] coordinates = { 40.7143528, -74.00597309999999 };

	// geo-files
	static String rootGeonamesDir = "";
	static String citiesFile = "";
	static String countryInfoFile = "";
	static String adminNamesFile = "";

	// centralities
	public static List<String> indegreeLines = new ArrayList<String>();
	public static List<String> harmonicLines = new ArrayList<String>();

	/**
	 * Function that finds the number of friends of a Twitter account
	 * 
	 * @param doc
	 *            the Document of the user's profile
	 * @return Long number of the friends
	 */
	public static Long getNumFriends(JSONObject json) {

		Long numFriends = Long.parseLong(json.getJSONObject("user")
				.get("friends_count").toString());
		return numFriends;
	}

	/**
	 * Function that finds the number of followers of a Twitter account
	 * 
	 * @param doc
	 *            the Document of the user's profile
	 * @return Long number of the followers
	 */
	public static Long getNumFollowers(JSONObject json) {

		Long numFollowers = Long.parseLong(json.getJSONObject("user")
				.get("followers_count").toString());

		return numFollowers;
	}

	/**
	 * Function that calculates the ratio numFollowers/numFriends of a Twitter
	 * account
	 * 
	 * @param numFr
	 *            the number of friends of a user
	 * @param numFol
	 *            the number of followers of a user
	 * @return Float number of the ratio calculated
	 */
	public static Float getFollowerFriendRatio(Long numFr, Long numFol) {

		Float ratio = (float) 0;
		if (numFr != 0)
			ratio = (float) numFol / numFr;
		//System.out.println("Ratio " + ratio);

		return ratio;
	}

	/**
	 * Function that finds the times that the user was listed in Twitter
	 * 
	 * @param doc
	 *            the Document of the user's profile
	 * @return Long number, the times the user was listed
	 */
	public static Long getTimesListed(JSONObject json) {

		Long times = Long.parseLong(json.getJSONObject("user")
				.get("listed_count").toString());

		return times;
	}

	/**
	 * Function that finds whether the user provides url in his/her Twitter
	 * account It uses the getUserUrl(Document doc) function
	 * 
	 * @param doc
	 *            the Document of the user's profile
	 * @return Boolean whether the user provides url
	 */
	public static Boolean hasUrl(JSONObject json) {

		String url = json.getJSONObject("user").get("url").toString();

		if (url != null && !url.equals("null"))
			return true;

		return false;
	}

	/**
	 * Function that finds the user's url that provides in his/her Twitter
	 * account The function supports both the old and the new version of the
	 * Twitter profile.
	 * 
	 * @param doc
	 *            the Document of the user's profile
	 * @return String the url that the user provides or null if he/she does not
	 *         provide one.
	 */
	public static String getUserUrl(JSONObject json) {

		Object urlObj = json.getJSONObject("user").get("url");

		if (urlObj != null) {
			return urlObj.toString();
		} else {
			String nothing = "";
			return nothing;
		}
	}

	/**
	 * Function that finds if the user provides description in his/her Twitter
	 * profile The function supports both the old and the new version Twitter
	 * profile.
	 * 
	 * @param doc
	 *            the Document of the user's profile
	 * @return Boolean variable about whether the user provides description
	 */
	public static Boolean hasBio(JSONObject json) {

		JSONObject user = json.getJSONObject("user");

		if (user.get("description") != null
				&& !user.get("description").equals("null")) {
			return true;
		}

		return false;
	}

	/**
	 * Function that finds if the user account is verified or not The function
	 * supports both the old and new version of the Twitter profile.
	 * 
	 * @param doc
	 *            the Document of the user's profile
	 * @return Boolean whether the account is verified
	 */
	public static Boolean isVerifiedUser(JSONObject json) {

		Boolean isVerified = (Boolean) json.getJSONObject("user").get(
				"verified");

		return isVerified;
	}

	/**
	 * Function that finds the number of tweets that the user posted The
	 * function supports both the old and new version of the Twitter profile.
	 * 
	 * @param doc
	 *            the Document of the user's profile
	 * @return Integer the number of tweets the user posted
	 */
	public static Long getNumTweets(JSONObject json) {

		Long numTweets = Long.parseLong(json.getJSONObject("user")
				.get("statuses_count").toString());

		return numTweets;
	}

	/**
	 * Function that finds the number of Media content that the user shared via
	 * his twitter account This information is provided only in the new type of
	 * Twitter profile.
	 * 
	 * @param doc
	 *            the Document of the user's profile page
	 * @return Integer the number of the media content found
	 */
	public static Long getNumMediaContent(Document doc) {

		Long numMediaContent = 0L;
		String media = null;
		Elements numMediaHtml = null;

		numMediaHtml = doc.select(".PhotoRail .PhotoRail-headingWithCount");

		media = numMediaHtml.text().split(" ")[0];
		if (!media.equals("")) {
			media = StringProcessing.getInstance().removeUnit(media);
			numMediaContent = Long.parseLong(media);
		} else {
			numMediaContent = 0L;
		}

		return numMediaContent;
	}

	/**
	 * 
	 * @param doc
	 * @return
	 */
	public static Long getNumFavorites(JSONObject json) {

		Long numFavorites = Long.parseLong(json.getJSONObject("user")
				.get("favourites_count").toString());
		return numFavorites;
	}

	/**
	 * Function that finds the location that the user provides in his/her
	 * profile
	 * 
	 * @param doc
	 *            the Document of the user's profile page
	 * @return String that holds the location found
	 */
	public static String getLocation(JSONObject json) {

		String location = json.getJSONObject("user").getString("location");

		return location;

	}

	/**
	 * Function that finds whether the user provides information about location
	 * or not
	 * 
	 * @param doc
	 *            the Document of the User
	 * @return Boolean variable whether the user provides information about
	 *         location
	 * @throws MalformedURLException
	 * @throws JSONException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static Boolean hasLocation(String location)
			throws MalformedURLException, JSONException, IOException,
			InterruptedException {

		Boolean hasLoc = false;

		System.out.println("Location " + location);
		// double[] geos = new double[2];

		// location in case of using google maps api
		/*
		 * if (!location.equals("Worldwide trends")){ if
		 * (location.contains(" ")){ location = location.replaceAll(" ", "%20");
		 * } geos = getGeos(location); if(geos[0]!=0 || geos[1]!=0){ hasLoc =
		 * true; } }
		 */

		// location in case of using location as String only
		if (!location.equals("Worldwide trends") && (location.length() != 0)) {
			hasLoc = true;
		}

		System.out.println("has location " + hasLoc);

		return hasLoc;
	}

	/**
	 * Function that creates a String from the InputStream given
	 * 
	 * @param is
	 *            InputStream to process
	 * @return String got by the InputStream
	 */
	public static String getStringFromInputStream(InputStream is) {

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

	public static Long getAccountAge(JSONObject json) throws ParseException {

		String age = json.getJSONObject("user").getString("created_at");

		DateFormat dateFormat = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
		Date date = dateFormat.parse(age);
		Long accountAge = date.getTime() / 1000;
		System.out.println("age " + accountAge);
		return accountAge;
	}

	/**
	 * Function that gets the ranking (indegree or centrality) of a url
	 * 
	 * @param url
	 *            String
	 * @param filePath
	 *            String the path of the file that holds the rankings
	 * @return Float the ranking
	 * @throws IOException
	 * @throws Exception
	 */
	public static Float getRank(String url, String filePath)
			throws IOException, Exception {

		long start = System.currentTimeMillis();

		Float rank = null;
		BufferedReader in;

		// [optional] expand the url if appeared in a shortened form
		String host0 = WebOfTrustManager.getInstance().expandUrl(url);
		if (host0 == null) {
			host0 = url;
		}

		host0 = url;

		System.out.println("Ranking the: " + host0);
		host0 = host0.replaceAll(
				"(http://|https://|http://www\\.|https://www\\.|www\\.)", "");
		String host;
		if (host0.contains("/")) {
			host = host0.split("/")[0];
		} else {
			host = host0;
		}
		System.out.println("Simple form " + host);

		try {
			in = new BufferedReader(new FileReader(filePath));
			String line = "";

			while ((line = in.readLine()) != null) {
				String part[] = line.split("\t");
				// System.out.println(line);
				if (URLProcessing.getInstance().isTumblrPage(host)) {
					// Tumblr users' pages have the form :
					// "username.tumblr.com".
					// Just remove the username part resulting in "tumblr.com".
					host = host.substring(host.indexOf(".") + 1, host.length());
				}
				if (part[0].equals(host)) {
					rank = Float.parseFloat(part[1]);
					break;
				}

			}
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Url: " + host + ". Score " + rank);

		long end = System.currentTimeMillis();

		System.out.println((double) (end - start) / 1000 + " seconds");

		return rank;
	}

	

	public static Boolean hasProfileImg(JSONObject json) {

		Boolean profileImg = (Boolean) json.getJSONObject("user").get(
				"default_profile_image");

		return profileImg;
	}

	public static Boolean hasHeaderImg(JSONObject json) {

		Boolean headerImg = (Boolean) json.getJSONObject("user").get(
				"profile_use_background_image");

		return headerImg;
	}

	public static UserFeatures extractFeatures(JSONObject json)
			throws Exception, JSONException, IOException {

		String currentTweetId = json.getString("id_str");
		//info
		System.out.println("Extracting User features for " + currentTweetId + "...");
		
		
		initializeFiles();

		String permalink = "http://twitter.com/"
				+ json.getJSONObject("user").getString("screen_name");
		
		Document doc = null;
		try {
			doc = Jsoup.connect(permalink).get();
		} catch (IOException e) {
			doc = null;

		}

		// Declare the values of the features

		UserFeatures uf = null;

		Long numFriends = getNumFriends(json);
		System.out.println("Number of friends: " + numFriends);
		Long numFollowers = getNumFollowers(json);
		System.out.println("Number of followers: " + numFollowers);
		Float FolFrieRatio = getFollowerFriendRatio(numFriends, numFollowers);
		System.out.println("Follower/Friend ratio: " + FolFrieRatio);
		
		Long timesListed = getTimesListed(json);
		System.out.println("Times listed: " + timesListed);
		Boolean hasURL = hasUrl(json);
		System.out.println("Has url in his profile description: " + hasURL);
		Boolean hasBio = hasBio(json);
		System.out.println("Has bio description: " + hasBio);
		Boolean isVerified = isVerifiedUser(json);
		System.out.println("Is verified by Twitter: " + isVerified);
		Long numTweets = getNumTweets(json);
		System.out.println("Number of tweets shared: " + numTweets);

		Long numMediaContent = 0L;
		if (doc != null) {
			numMediaContent = getNumMediaContent(doc);
			System.out.println("Number of media content shared: " + numMediaContent);
		}
		Long numFavorites = getNumFavorites(json);
		System.out.println("Number of favorites: " + numFavorites);
		
		String location = getLocation(json);
		Boolean hasLocation = hasLocation(location);
		System.out.println("Has location the profile: " + hasLocation);
		
		Long accountAge = getAccountAge(json);
		System.out.println("Age of the account (timestamp): " + accountAge);
		
		Float tweetRatio;
		long epoch = System.currentTimeMillis() / 1000;
		if (accountAge != null) {
			tweetRatio = (((float) numTweets / (float) (epoch - accountAge)) * 86400L);
			System.out.println("Tweet ratio (number of tweets/accountAge): " + tweetRatio);
		} else
			tweetRatio = (float) 0;

		Float indegree = null;
		Float harmonic = null;

		
		String jsonUrl = getUserUrl(json);

		Integer wotTrustUser = null;
		Integer wotSafeUser = null;
		Integer[] values = { 0, 0 };

		
		int[] alexaRankings = new int[4];
		if (hasURL) {
			if (URLProcessing.getInstance().isAppropriateUrl(jsonUrl)) {

				values = WebOfTrustManager.getInstance().getWotValues(jsonUrl);

				if (values[0] != 0 && values[1] != 0) {
					wotTrustUser = values[0];
					wotSafeUser = values[1];
				}
				System.out.println("For user's url: " + jsonUrl);
				System.out.println("WOT trust value: " + wotTrustUser);
				
				//preprocess
				jsonUrl = URLProcessing.getInstance().processUrlForRunnable(jsonUrl);

				/*indegree = organizeRunRank("indegree-" + currentTweetId, jsonUrl,Vars.INDEGREE_FILE);
				if (indegree != null)
					harmonic = organizeRunRank("harmonic-" + currentTweetId, jsonUrl,Vars.HARMONIC_FILE);

				System.out.println("In-degree centrality value: " + indegree);
				System.out.println("Harmonic centrality value: " + harmonic);*/
				
				AlexaRankingManager arm = new AlexaRankingManager();
				alexaRankings = arm.getAlexaRanking(jsonUrl);
				
				System.out.println("Alexa Popularity: "		+ alexaRankings[0]);
				System.out.println("Alexa Reach Rank: " 	+ alexaRankings[1]);
				System.out.println("Alexa Delta Rank: " 	+ alexaRankings[2]);
				System.out.println("Alexa Country Rank: " 	+ alexaRankings[3]);
			}
		}

		Boolean hasExistingLocation = null;
		if (!hasLocation) {
			hasExistingLocation = false;
		} else {
			hasExistingLocation = hasExistingLocation(location);
		}
		System.out.println("Has existing location: " + hasExistingLocation);

		Boolean hasProfileImg = hasProfileImg(json);
		System.out.println("Has profile Image in the profile: " + hasProfileImg);
		Boolean hasHeaderImg = hasHeaderImg(json);
		System.out.println("Has header Image in the profile: " + hasHeaderImg);
		
		
		String username = json.getJSONObject("user").getString("screen_name");

		System.out.println("-");
	
		uf = new UserFeatures.Builder(currentTweetId, username).numFriends(numFriends)
				.numFollowers(numFollowers).FolFrieRatio(FolFrieRatio)
				.timesListed(timesListed).hasURL(hasURL).hasBio(hasBio)
				.isVerified(isVerified).numTweets(numTweets)
				.numMediaContent(numMediaContent).numFavorites(numFavorites)
				.hasLocation(hasLocation)
				.hasExistingLocation(hasExistingLocation)
				.wotTrustUser(wotTrustUser).accountAge(accountAge)
				.indegree(indegree).harmonic(harmonic)
				.hasProfileImg(hasProfileImg).hasHeaderImg(hasHeaderImg)
				.wotSafeUser(wotSafeUser).tweetRatio(tweetRatio)
				.indegree(indegree).harmonic(harmonic)
				.alexaPopularity(alexaRankings[0])
				.alexaReachRank(alexaRankings[1])
				.alexaDeltaRank(alexaRankings[2])
				.alexaCountryRank(alexaRankings[3]).build();

		return uf;
		
	}

	public static void initializeFiles() {

		citiesFile = AgreementBasedClassification.prop.getProperty("CITIES_PATH");
		countryInfoFile = AgreementBasedClassification.prop.getProperty("COUNTRY_INFO_PATH");
		adminNamesFile = AgreementBasedClassification.prop.getProperty("ADMIN_NAMES_PATH");

	}

	public static Float organizeRunRank(String caseRank, String url,
			String filePath) throws InterruptedException, ExecutionException {
		Float result = null;
		long start = System.currentTimeMillis();

		int numberThreads = 114;

		// Set<Future<Float>> results = new HashSet<Future<Float>>();

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
				System.out.print("RESULT FOUND: " + val);
				System.out.println();
				if (val != null) {
					result = val;
					service.shutdownNow();
					System.out.println("break!");
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

		long end = System.currentTimeMillis();

		System.out.println("TOTAL TIME: " + (double) (end - start) / 1000);

		return result;
	}

	public static List<String> getRankArray(String filePath) {

		List<String> lines = new ArrayList<String>();
		BufferedReader in = null;
		String line = "";

		try {
			in = new BufferedReader(new FileReader(filePath));
			while ((line = in.readLine()) != null) {
				lines.add(line);
			}

			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lines;
	}

	public static boolean hasExistingLocation(String locationName) {

		Countrycoder countrycodingService = new Countrycoder(citiesFile,
				countryInfoFile, adminNamesFile);
		String[] locParts = null;
		boolean hasExistingLocation = false;

		System.out.println("Location name " + locationName);
		// System.out.println(StringUtils.isAlphanumeric(locationName)+" "+!locationName.contains("."));

		locParts = locationName.split(",");

		int i = 0;
		String[] countries = new String[locParts.length];
		for (String locPart : locParts) {
			String newlocPart = TextProcessing.getInstance()
					.eraseAllCharacters(locPart);

			countries[i] = countrycodingService
					.getCountryByLocationName(newlocPart);
			System.out.println("For " + locPart + " found: " + countries[i]);
			i++;
		}

		for (String country : countries) {
			if (country != "unknown") {
				System.out.println("Country for location " + locationName
						+ ": " + country);
				hasExistingLocation = true;
			}
		}

		return hasExistingLocation;

	}



	static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
			.create();

	public static synchronized UserFeatures create(String json) {
		synchronized (gson) {
			UserFeatures item = gson.fromJson(json, UserFeatures.class);
			return item;
		}
	}


}
