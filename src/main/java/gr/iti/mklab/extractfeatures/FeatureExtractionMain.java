package gr.iti.mklab.extractfeatures;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import gr.iti.mklab.util.FileManager;

public class FeatureExtractionMain {
	
	public static String root_path = "D:/TweetVerification/Reproducibility/Features/";
	public static String workingDir = System.getProperty("user.dir");
	private static final Logger LOGGER = Logger.getLogger("gr.iti.mklab.extractfeatures.FeatureExtractionMain");
	public static String fileName_temp;
	
	
	public void extractTweetFeatures(String tweetsFile, String userFeatFile, String tweetFeatFile, String resourcesPath) throws Exception{
		long start_time = System.currentTimeMillis();

	
		List<JSONObject> tweet_posts = new ArrayList<JSONObject>();
	
		BufferedReader in = new BufferedReader(
				   new InputStreamReader(
		                      new FileInputStream(tweetsFile), "UTF-8"));		
		String str;
		int cnt =0;
		while ((str = in.readLine()) != null) {
		    cnt = cnt +1;		
		    JSONObject json = new JSONObject(str);			
			tweet_posts.add(json);
		}
		in.close();
		
		Gson gson = new Gson();
		JsonElement element;
		for (int i =0; i < tweet_posts.size(); i ++){
			TweetFeatures tempItem = new TweetFeatures();
			tempItem = TweetFeaturesExtractor.extractFeatures(tweet_posts.get(i), resourcesPath);
			element = gson.fromJson(tempItem.toJSONString(), JsonElement.class);
			JsonObject item_features = element.getAsJsonObject();	
			FileManager.getInstance().writePlainDataToFile(item_features.toString(), tweetFeatFile);
	
			UserFeatures tempUser = new UserFeatures();
			tempUser = UserFeaturesExtractorJSON.extractFeatures(tweet_posts.get(i), resourcesPath);
			//
			element = gson.fromJson(tempUser.toJSONString(), JsonElement.class);
			JsonObject user_features = element.getAsJsonObject();	
			FileManager.getInstance().writePlainDataToFile(user_features.toString(), userFeatFile);		
		}
		
		LOGGER.info(" Feature Extraction Execution ended in --- " + (System.currentTimeMillis() - start_time));
		
	}	
	
}
