package gr.iti.mklab.extractfeatures;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TweetFeatures {
	
	@Expose
    @SerializedName(value = "id")
	private String id;
	
	@Expose
    @SerializedName(value = "itemLength",  alternate={"item_length"})
	private Integer itemLength;
	
	@Expose
    @SerializedName(value = "numWords", alternate= {"num_words"})
	private Integer numWords;
	
	@Expose
    @SerializedName(value = "containsQuestionMark", alternate= {"contains_questionmark"})
	private Boolean containsQuestionMark;
	
	@Expose
    @SerializedName(value = "containsExclamationMark", alternate= {"contains_exclamationmark"})
	private Boolean containsExclamationMark;
	
	@Expose
    @SerializedName(value = "numQuestionMark", alternate= {"num_questionmark"})
	private Integer numQuestionMark;
	
	@Expose
    @SerializedName(value = "numExclamationMark", alternate= {"num_exclamationmark"})
	private Integer numExclamationMark;
	
	@Expose
    @SerializedName(value = "containsHappyEmo", alternate= {"contains_happy_emoticon"})
	private Boolean containsHappyEmo;
	
	@Expose
    @SerializedName(value = "containsSadEmo", alternate= {"contains_sad_emoticon"})
	private Boolean containsSadEmo;
	
	@Expose
    @SerializedName(value = "containsFirstOrderPron", alternate= {"contains_first_order_pronoun"})
	private Boolean containsFirstOrderPron;
	
	@Expose
    @SerializedName(value = "containsSecondOrderPron", alternate= {"contains_second_order_pronoun"})
	private Boolean containsSecondOrderPron;
	
	@Expose
    @SerializedName(value = "containsThirdOrderPron", alternate= {"contains_third_order_pronoun"})
	private Boolean containsThirdOrderPron;
	
	@Expose
    @SerializedName(value = "numUppercaseChars", alternate= {"num_uppercasechars"})
	private Integer numUppercaseChars;
	
	@Expose
    @SerializedName(value = "numNegSentiWords", alternate= {"num_neg_sentiment_words"})
	private Integer numNegSentiWords;
	
	@Expose
    @SerializedName(value = "numPosSentiWords", alternate= {"num_pos_sentiment_words"})
	private Integer numPosSentiWords;
	
	@Expose
    @SerializedName(value = "numMentions", alternate= {"num_mentions"})
	private Integer numMentions;
	
	@Expose
    @SerializedName(value = "numHashtags", alternate= {"num_hashtags"})
	private Integer numHashtags;
	
	@Expose
    @SerializedName(value = "numURLs", alternate= {"num_URLs"})
	private Integer numURLs;
	
	@Expose
    @SerializedName(value = "retweetCount", alternate= {"retweet_count"} )
	private Long retweetCount;
	
	@Expose
    @SerializedName(value = "hasColon", alternate= {"has_colon"})
	private Boolean hasColon;
	
	@Expose
    @SerializedName(value = "hasPlease", alternate= {"has_please"})
	private Boolean hasPlease;
	
	@Expose
    @SerializedName(value = "numNouns", alternate= {"num_nouns"})
	private Integer numNouns;
	
	@Expose
    @SerializedName(value = "hasExternalLink", alternate= {"has_external_link"})
	private Boolean hasExternalLink;
	
	@Expose
    @SerializedName(value = "wotTrust", alternate= {"wot_Trust"})
	private Integer wotTrust;
	
	@Expose
    @SerializedName(value = "wotSafe")
	private Integer wotSafe;
	
	@Expose
    @SerializedName(value = "numSlangs", alternate= {"num_slangs"})
	private Integer numSlangs;
	
	@Expose
    @SerializedName(value = "readability")
	private Double readability;
	
	@Expose
    @SerializedName(value = "urlIndegree", alternate= {"indegree_centrality"})
	private Float urlIndegree;
	
	@Expose
    @SerializedName(value = "urlHarmonic", alternate= {"harmonic_centrality"})
	private Float urlHarmonic;
	
	@Expose
    @SerializedName(value = "containsWordFake")
	private Boolean containsWordFake;
	
	@Expose
    @SerializedName(value = "numFakeWords")
	private Integer numFakeWords;
	
	@Expose
    @SerializedName(value = "numComments")
	private Integer numComments;
		
	@Expose
    @SerializedName(value = "timeFromStart")
	private Long timeFromStart;
	
	@Expose
    @SerializedName(value = "reliability")
	private String reliability;
	
	@Expose
    @SerializedName(value = "alexaPopularity", alternate= {"alexa_popularity"})
	private Integer alexaPopularity;
	
	@Expose
    @SerializedName(value = "alexaReachRank", alternate= {"alexa_reach_rank"})
	private Integer alexaReachRank;
	
	@Expose
    @SerializedName(value = "alexaDeltaRank", alternate= {"alexa_delta_rank"})
	private Integer alexaDeltaRank;
	
	@Expose
    @SerializedName(value = "alexaCountryRank", alternate= {"alexa_country_rank"})
	private Integer alexaCountryRank;
	
	@Expose
    @SerializedName(value = "annotation")
	private String annotation;
	
		
	
	
	
	
	public String getId() {
		return id;
	}






	public void setId(String id) {
		this.id = id;
	}






	public Integer getItemLength() {
		return itemLength;
	}






	public void setItemLength(Integer itemLength) {
		this.itemLength = itemLength;
	}






	public Integer getNumWords() {
		return numWords;
	}






	public void setNumWords(Integer numWords) {
		this.numWords = numWords;
	}






	public Boolean getContainsQuestionMark() {
		return containsQuestionMark;
	}






	public void setContainsQuestionMark(Boolean containsQuestionMark) {
		this.containsQuestionMark = containsQuestionMark;
	}






	public Boolean getContainsExclamationMark() {
		return containsExclamationMark;
	}






	public void setContainsExclamationMark(Boolean containsExclamationMark) {
		this.containsExclamationMark = containsExclamationMark;
	}






	public Integer getNumQuestionMark() {
		return numQuestionMark;
	}






	public void setNumQuestionMark(Integer numQuestionMark) {
		this.numQuestionMark = numQuestionMark;
	}






	public Integer getNumExclamationMark() {
		return numExclamationMark;
	}






	public void setNumExclamationMark(Integer numExclamationMark) {
		this.numExclamationMark = numExclamationMark;
	}






	public Boolean getContainsHappyEmo() {
		return containsHappyEmo;
	}






	public void setContainsHappyEmo(Boolean containsHappyEmo) {
		this.containsHappyEmo = containsHappyEmo;
	}






	public Boolean getContainsSadEmo() {
		return containsSadEmo;
	}






	public void setContainsSadEmo(Boolean containsSadEmo) {
		this.containsSadEmo = containsSadEmo;
	}






	public Boolean getContainsFirstOrderPron() {
		return containsFirstOrderPron;
	}






	public void setContainsFirstOrderPron(Boolean containsFirstOrderPron) {
		this.containsFirstOrderPron = containsFirstOrderPron;
	}






	public Boolean getContainsSecondOrderPron() {
		return containsSecondOrderPron;
	}






	public void setContainsSecondOrderPron(Boolean containsSecondOrderPron) {
		this.containsSecondOrderPron = containsSecondOrderPron;
	}






	public Boolean getContainsThirdOrderPron() {
		return containsThirdOrderPron;
	}






	public void setContainsThirdOrderPron(Boolean containsThirdOrderPron) {
		this.containsThirdOrderPron = containsThirdOrderPron;
	}






	public Integer getNumUppercaseChars() {
		return numUppercaseChars;
	}






	public void setNumUppercaseChars(Integer numUppercaseChars) {
		this.numUppercaseChars = numUppercaseChars;
	}






	public Integer getNumNegSentiWords() {
		return numNegSentiWords;
	}






	public void setNumNegSentiWords(Integer numNegSentiWords) {
		this.numNegSentiWords = numNegSentiWords;
	}






	public Integer getNumPosSentiWords() {
		return numPosSentiWords;
	}






	public void setNumPosSentiWords(Integer numPosSentiWords) {
		this.numPosSentiWords = numPosSentiWords;
	}






	public Integer getNumMentions() {
		return numMentions;
	}






	public void setNumMentions(Integer numMentions) {
		this.numMentions = numMentions;
	}






	public Integer getNumHashtags() {
		return numHashtags;
	}






	public void setNumHashtags(Integer numHashtags) {
		this.numHashtags = numHashtags;
	}






	public Integer getNumURLs() {
		return numURLs;
	}






	public void setNumURLs(Integer numURLs) {
		this.numURLs = numURLs;
	}






	public Long getRetweetCount() {
		return retweetCount;
	}






	public void setRetweetCount(Long retweetCount) {
		this.retweetCount = retweetCount;
	}






	public Boolean getHasColon() {
		return hasColon;
	}






	public void setHasColon(Boolean hasColon) {
		this.hasColon = hasColon;
	}






	public Boolean getHasPlease() {
		return hasPlease;
	}






	public void setHasPlease(Boolean hasPlease) {
		this.hasPlease = hasPlease;
	}






	public Integer getNumNouns() {
		return numNouns;
	}






	public void setNumNouns(Integer numNouns) {
		this.numNouns = numNouns;
	}






	public Boolean getHasExternalLink() {
		return hasExternalLink;
	}






	public void setHasExternalLink(Boolean hasExternalLink) {
		this.hasExternalLink = hasExternalLink;
	}






	public Integer getWotTrust() {
		return wotTrust;
	}






	public void setWotTrust(Integer wotTrust) {
		this.wotTrust = wotTrust;
	}






	public Integer getWotSafe() {
		return wotSafe;
	}






	public void setWotSafe(Integer wotSafe) {
		this.wotSafe = wotSafe;
	}






	public Integer getNumSlangs() {
		return numSlangs;
	}






	public void setNumSlangs(Integer numSlangs) {
		this.numSlangs = numSlangs;
	}






	public Double getReadability() {
		return readability;
	}






	public void setReadability(Double readability) {
		this.readability = readability;
	}






	public Float getUrlIndegree() {
		return urlIndegree;
	}






	public void setUrlIndegree(Float urlIndegree) {
		this.urlIndegree = urlIndegree;
	}






	public Float getUrlHarmonic() {
		return urlHarmonic;
	}






	public void setUrlHarmonic(Float urlHarmonic) {
		this.urlHarmonic = urlHarmonic;
	}






	public Boolean getContainsWordFake() {
		return containsWordFake;
	}






	public void setContainsWordFake(Boolean containsWordFake) {
		this.containsWordFake = containsWordFake;
	}






	public Integer getNumFakeWords() {
		return numFakeWords;
	}






	public void setNumFakeWords(Integer numFakeWords) {
		this.numFakeWords = numFakeWords;
	}






	public Integer getNumComments() {
		return numComments;
	}






	public void setNumComments(Integer numComments) {
		this.numComments = numComments;
	}






	public Long getTimeFromStart() {
		return timeFromStart;
	}






	public void setTimeFromStart(Long timeFromStart) {
		this.timeFromStart = timeFromStart;
	}






	public String getReliability() {
		return reliability;
	}






	public void setReliability(String reliability) {
		this.reliability = reliability;
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
