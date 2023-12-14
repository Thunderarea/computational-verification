package gr.iti.mklab.extractfeatures;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import eu.socialsensor.geo.Countrycoder;
import gr.iti.mklab.util.AlexaRankingManager;
import gr.iti.mklab.util.URLProcessing;
import gr.iti.mklab.util.WebOfTrustManager;
import gr.iti.mklab.util.TextProcessing;
import gr.iti.mklab.util.RunnableRank;
import gr.iti.mklab.util.StringProcessing;

public class UserFeaturesExtractorJSON {
	
	private static final Logger LOGGER = Logger.getLogger( UserFeaturesExtractorJSON.class.getName() );
	
	public static UserFeatures extractFeatures(JSONObject json, String resourcesPath)
			throws Exception, JSONException, IOException {
		LOGGER.setLevel(Level.OFF);
		String currentTweetId = json.getString("id_str");
		//info
		LOGGER.info("Extracting User features for " + currentTweetId + "...");
		
		UserFeatures feats = new UserFeatures();

		String permalink = "http://twitter.com/"
				+ json.getJSONObject("user").getString("screen_name");
		Document doc = null;
		try {
			doc = Jsoup.connect(permalink).get();
		} catch (IOException e) {
			doc = null;

		}

		// Declare the values of the features
		feats.setId(currentTweetId);
		
		feats.setNumFriends(getNumFriends(json));
		LOGGER.info("Number of friends: " + feats.getNumFriends());
		
		feats.setNumFollowers( getNumFollowers(json));
		LOGGER.info("Number of followers: " + feats.getNumFollowers());
		
		feats.setFolFrieRatio(getFollowerFriendRatio(feats.getNumFriends(), feats.getNumFollowers()));
		LOGGER.info("Follower/Friend ratio: " + feats.getFolFrieRatio());
		
		feats.setTimesListed(getTimesListed(json));
		LOGGER.info("Times listed: " + feats.getTimesListed());
		
		feats.setHasURL(hasUrl(json));
		LOGGER.info("Has url in his profile description: " + feats.getHasURL());
		
		feats.setHasBio(hasBio(json));
		LOGGER.info("Has bio description: " + feats.getHasBio());
		
		feats.setIsVerified(isVerifiedUser(json));
		LOGGER.info("Is verified by Twitter: " + feats.getIsVerified());
		
		feats.setNumTweets(getNumTweets(json));
		LOGGER.info("Number of tweets shared: " + feats.getNumTweets());

		if (doc != null) {
			feats.setNumMediaContent(getNumMediaContent(doc));
			LOGGER.info("Number of media content shared: " + feats.getNumMediaContent());
		}else{
			feats.setNumMediaContent(0L);
		}
		
		feats.setNumFavorites(getNumFavorites(json));
		LOGGER.info("Number of favorites: " + feats.getNumFavorites());
		
		String location = getLocation(json);
		feats.setHasLocation(hasLocation(location));
		LOGGER.info("Has location the profile: " + feats.getHasLocation());
		
		feats.setAccountAge(getAccountAge(json));
		LOGGER.info("Age of the account (timestamp): " + feats.getAccountAge());
		
		Float tweetRatio;
		long epoch = System.currentTimeMillis() / 1000;
		if (feats.getAccountAge() != null) {
			tweetRatio = (((float) feats.getNumTweets() / (float) (epoch - feats.getAccountAge())) * 86400L);
			LOGGER.info("Tweet ratio (number of tweets/accountAge): " + tweetRatio);
		} else
			tweetRatio = (float) 0;
		
		feats.setTweetRatio(tweetRatio);

		String jsonUrl = getUserUrl(json);

		Integer wotTrustUser = null;
		Integer wotSafeUser = null;
		Integer[] values = { 0, 0 };

		
		Integer[] alexaRankings = new Integer[4];
		if (feats.getHasURL()) {
			if (URLProcessing.getInstance().isAppropriateUrl(jsonUrl)) {

				values = WebOfTrustManager.getInstance().getWotValues(jsonUrl);

				if (values[0] != 0 && values[1] != 0) {
					wotTrustUser = values[0];
					wotSafeUser = values[1];
				}
				
				feats.setWotSafeUser(wotSafeUser);
				feats.setWotTrustUser(wotTrustUser);
				LOGGER.info("For user's url: " + jsonUrl);
				LOGGER.info("WOT trust value: " + wotTrustUser);
				
				//preprocess
				jsonUrl = URLProcessing.getInstance().processUrlForRunnable(jsonUrl);

				feats.setIndegree(organizeRunRank("indegree-" + currentTweetId, jsonUrl, resourcesPath + "/centralities/hostgraph-indegree_split_1/hostgraph-indegree"));
				if (feats.getIndegree() != null)
					feats.setHarmonic(organizeRunRank("harmonic-" + currentTweetId, jsonUrl, resourcesPath + "/centralities/hostgraph-h_split_2/hostgraph-h"));

				LOGGER.info("In-degree centrality value: " + feats.getIndegree());
				LOGGER.info("Harmonic centrality value: " + feats.getHarmonic());
				
				AlexaRankingManager arm = new AlexaRankingManager();
				alexaRankings = arm.getAlexaRanking(jsonUrl);
				
				feats.setAlexaPopularity(alexaRankings[0]);
				feats.setAlexaReachRank(alexaRankings[1]);
				feats.setAlexaDeltaRank(alexaRankings[2]);
				feats.setAlexaCountryRank(alexaRankings[3]);
				
				LOGGER.info("Alexa Popularity: "		+ alexaRankings[0]);
				LOGGER.info("Alexa Reach Rank: " 	+ alexaRankings[1]);
				LOGGER.info("Alexa Delta Rank: " 	+ alexaRankings[2]);
				LOGGER.info("Alexa Country Rank: " 	+ alexaRankings[3]);
			}
		}

		Boolean hasExistingLocation = null;
		if (!feats.getHasLocation()) {
			feats.setHasExistingLocation(false);
		} else {
			
			try {
				feats.setHasExistingLocation(hasExistingLocation(location, resourcesPath));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				feats.setHasExistingLocation(false);
			}
		}
		LOGGER.info("Has existing location: " + hasExistingLocation);

		feats.setHasProfileImg(hasProfileImg(json));
		LOGGER.info("Has profile Image in the profile: " + feats.getHasProfileImg());
		feats.setHasHeaderImg(hasHeaderImg(json));
		LOGGER.info("Has header Image in the profile: " + feats.getHasHeaderImg());
				
		feats.setUsername(json.getJSONObject("user").getString("screen_name"));
		LOGGER.info("Username " + feats.getUsername());
		
		return feats;
		
	}
	
	
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

		if (json.getJSONObject("user").has("url")){
			String url = json.getJSONObject("user").get("url").toString();
	
			if (url != null && !url.equals("null"))
				return true;
		
		}

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

		if (json.getJSONObject("user").has("url")){
			Object urlObj = json.getJSONObject("user").get("url");
	
			if (urlObj != null) {
				return urlObj.toString();
			} else {
				String nothing = "";
				return nothing;
			}
		}
		String nothing = "";
		return nothing;
		 
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

		if (user.has("description")){
				if (user.get("description") != null
						&& !user.get("description").equals("null")) {
					return true;
				}
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

			// location in case of using location as String only
			if (!location.equals("Worldwide trends") && (location.length() != 0)) {
				hasLoc = true;
			}

			return hasLoc;
		}
		
		public static Long getAccountAge(JSONObject json) throws ParseException {

			String age = json.getJSONObject("user").getString("created_at");

			DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
			Date date = dateFormat.parse(age);
			Long accountAge = date.getTime() / 1000;
			
			return accountAge;
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
		
		public static boolean hasExistingLocation(String locationName, String resourcesPath) {

			Countrycoder countrycodingService = new Countrycoder(resourcesPath + "/location/cities1000_mod.txt",
					resourcesPath + "/location/countryInfo.txt", resourcesPath + "/location/admin1CodesASCII_mod.txt");
			String[] locParts = null;
			boolean hasExistingLocation = false;
			locParts = locationName.split(",");
			int i = 0;
			String[] countries = new String[locParts.length];
			for (String locPart : locParts) {
				String newlocPart = TextProcessing.getInstance()
						.eraseAllCharacters(locPart);
					countries[i] = countrycodingService
							.getCountryByLocationName(newlocPart);
				i++;
			}

			for (String country : countries) {
				if (country != "unknown") {
					//System.out.println("Country for location " + locationName
						//	+ ": " + country);
					hasExistingLocation = true;
				}
			}

			return hasExistingLocation;

		}
		
		public static Boolean hasProfileImg(JSONObject json) {

			Boolean profileImg = (Boolean) json.getJSONObject("user").get(
					"default_profile_image");

			return !profileImg;
		}

		public static Boolean hasHeaderImg(JSONObject json) {

			Boolean headerImg = (Boolean) json.getJSONObject("user").get(
					"profile_use_background_image");

			return !headerImg;
		}

}
