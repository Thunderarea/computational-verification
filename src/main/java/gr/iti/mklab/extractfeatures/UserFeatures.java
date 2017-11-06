package gr.iti.mklab.extractfeatures;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserFeatures{
	
	@Expose
    @SerializedName(value = "id")
	protected String id;
	
	@Expose
    @SerializedName(value = "username")
	protected String username;
	
	@Expose
    @SerializedName(value = "numFriends", alternate = {"num_friends"})
	protected Long numFriends;
	
	@Expose
    @SerializedName(value = "numFollowers", alternate = {"num_followers"})
	protected Long numFollowers;
	
	@Expose
    @SerializedName(value = "FolFrieRatio", alternate = {"follower_friend_ratio"})
	protected Float FolFrieRatio;
	
	@Expose
    @SerializedName(value = "timesListed", alternate = {"times_listed"})
	protected Long timesListed;
	
	@Expose
    @SerializedName(value = "hasURL", alternate = {"has_URL"})
	protected Boolean hasURL;
	
	@Expose
    @SerializedName(value = "hasBio", alternate = {"has_bio_desc"})
	protected Boolean hasBio;
	
	@Expose
    @SerializedName(value = "isVerified", alternate = {"is_verified"})
	protected Boolean isVerified;
	
	@Expose
    @SerializedName(value = "numTweets", alternate = {"num_tweets"})
	protected Long numTweets;
	
	@Expose
    @SerializedName(value = "numMediaContent", alternate = {"num_media_content"})
	protected Long numMediaContent;
	
	@Expose
    @SerializedName(value = "numFavorites", alternate = {"num_favorites"})
	protected Long numFavorites;
	
	/*@Expose
    @SerializedName(value = "distance")
	protected double distance;*/
	
	@Expose
    @SerializedName(value = "hasLocation", alternate = {"has_location"})
	protected Boolean hasLocation;
	
	@Expose
    @SerializedName(value = "hasExistingLocation", alternate = {"has_existing_location"})
	protected Boolean hasExistingLocation;
	
	@Expose
    @SerializedName(value = "accountAge", alternate = {"account_age"})
	protected Long accountAge;
	
	@Expose
    @SerializedName(value = "wotTrustUser", alternate = {"wot_Trust"})
	protected Integer wotTrustUser;
	
	@Expose
    @SerializedName(value = "wotSafeUser")
	protected Integer wotSafeUser;
	
	@Expose
    @SerializedName(value = "indegree", alternate = {"indegree_centrality"})
	protected Float indegree;
	
	@Expose
    @SerializedName(value = "harmonic", alternate = {"harmonic_centrality"})
	protected Float harmonic;
	
	@Expose
    @SerializedName(value = "hasProfileImg", alternate = {"has_profile_img"})
	protected Boolean hasProfileImg;
	
	@Expose
    @SerializedName(value = "hasHeaderImg", alternate = {"has_header_img"})
	protected Boolean hasHeaderImg;
	
	@Expose
    @SerializedName(value = "tweetRatio", alternate = {"tweet_ratio"})
	protected Float tweetRatio;
	
	@Expose
    @SerializedName(value = "alexaPopularity", alternate = {"alexa_popularity"})
	protected Integer alexaPopularity;
	
	@Expose
    @SerializedName(value = "alexaReachRank", alternate = {"alexa_reach_rank"})
	protected Integer alexaReachRank;
	
	@Expose
    @SerializedName(value = "alexaDeltaRank", alternate = {"alexa_delta_rank"})
	protected Integer alexaDeltaRank;
	
	@Expose
    @SerializedName(value = "alexaCountryRank", alternate = {"alexa_country_rank"})
	protected Integer alexaCountryRank;
	

	@Expose
    @SerializedName(value = "annotation")
	private String annotation;
	
	
	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public Long getNumFriends() {
		return numFriends;
	}




	public void setNumFriends(Long numFriends) {
		this.numFriends = numFriends;
	}




	public Long getNumFollowers() {
		return numFollowers;
	}




	public void setNumFollowers(Long numFollowers) {
		this.numFollowers = numFollowers;
	}




	public Float getFolFrieRatio() {
		return FolFrieRatio;
	}




	public void setFolFrieRatio(Float folFrieRatio) {
		FolFrieRatio = folFrieRatio;
	}




	public Long getTimesListed() {
		return timesListed;
	}




	public void setTimesListed(Long timesListed) {
		this.timesListed = timesListed;
	}




	public Boolean getHasURL() {
		return hasURL;
	}




	public void setHasURL(Boolean hasURL) {
		this.hasURL = hasURL;
	}




	public Boolean getHasBio() {
		return hasBio;
	}




	public void setHasBio(Boolean hasBio) {
		this.hasBio = hasBio;
	}




	public Boolean getIsVerified() {
		return isVerified;
	}




	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}




	public Long getNumTweets() {
		return numTweets;
	}




	public void setNumTweets(Long numTweets) {
		this.numTweets = numTweets;
	}




	public Long getNumMediaContent() {
		return numMediaContent;
	}




	public void setNumMediaContent(Long numMediaContent) {
		this.numMediaContent = numMediaContent;
	}




	public Long getNumFavorites() {
		return numFavorites;
	}




	public void setNumFavorites(Long numFavorites) {
		this.numFavorites = numFavorites;
	}




	public Boolean getHasLocation() {
		return hasLocation;
	}




	public void setHasLocation(Boolean hasLocation) {
		this.hasLocation = hasLocation;
	}




	public Boolean getHasExistingLocation() {
		return hasExistingLocation;
	}




	public void setHasExistingLocation(Boolean hasExistingLocation) {
		this.hasExistingLocation = hasExistingLocation;
	}




	public Long getAccountAge() {
		return accountAge;
	}




	public void setAccountAge(Long accountAge) {
		this.accountAge = accountAge;
	}




	public Integer getWotTrustUser() {
		return wotTrustUser;
	}




	public void setWotTrustUser(Integer wotTrustUser) {
		this.wotTrustUser = wotTrustUser;
	}




	public Integer getWotSafeUser() {
		return wotSafeUser;
	}




	public void setWotSafeUser(Integer wotSafeUser) {
		this.wotSafeUser = wotSafeUser;
	}




	public Float getIndegree() {
		return indegree;
	}




	public void setIndegree(Float indegree) {
		this.indegree = indegree;
	}




	public Float getHarmonic() {
		return harmonic;
	}




	public void setHarmonic(Float harmonic) {
		this.harmonic = harmonic;
	}




	public Boolean getHasProfileImg() {
		return hasProfileImg;
	}




	public void setHasProfileImg(Boolean hasProfileImg) {
		this.hasProfileImg = hasProfileImg;
	}




	public Boolean getHasHeaderImg() {
		return hasHeaderImg;
	}




	public void setHasHeaderImg(Boolean hasHeaderImg) {
		this.hasHeaderImg = hasHeaderImg;
	}




	public Float getTweetRatio() {
		return tweetRatio;
	}




	public void setTweetRatio(Float tweetRatio) {
		this.tweetRatio = tweetRatio;
	}




	public Integer getAlexaPopularity() {
		return alexaPopularity;
	}




	public void setAlexaPopularity(Integer alexaPopularity) {
		this.alexaPopularity = alexaPopularity;
	}




	public Integer getAlexaReachRank() {
		return alexaReachRank;
	}




	public void setAlexaReachRank(Integer alexaReachRank) {
		this.alexaReachRank = alexaReachRank;
	}




	public Integer getAlexaDeltaRank() {
		return alexaDeltaRank;
	}




	public void setAlexaDeltaRank(Integer alexaDeltaRank) {
		this.alexaDeltaRank = alexaDeltaRank;
	}




	public Integer getAlexaCountryRank() {
		return alexaCountryRank;
	}




	public void setAlexaCountryRank(Integer alexaCountryRank) {
		this.alexaCountryRank = alexaCountryRank;
	}




	public String getAnnotation() {
		return annotation;
	}




	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}




	public String toJSONString() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(this);
	}
	
}
