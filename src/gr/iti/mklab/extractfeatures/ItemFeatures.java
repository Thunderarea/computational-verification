package gr.iti.mklab.extractfeatures;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ItemFeatures {
	
	@Expose(serialize=false)
    @SerializedName(value = "id")
	private String id;
	
	@Expose
    @SerializedName(value = "item_length")
	private Integer itemLength;
	
	@Expose
    @SerializedName(value = "num_words")
	private Integer numWords;
	
	@Expose
    @SerializedName(value = "contains_questionmark")
	private Boolean containsQuestionMark;
	
	@Expose
    @SerializedName(value = "contains_exclamationmark")
	private Boolean containsExclamationMark;
	
	@Expose
    @SerializedName(value = "num_questionmark")
	private Integer numQuestionMark;
	
	@Expose
    @SerializedName(value = "num_exclamationmark")
	private Integer numExclamationMark;
	
	@Expose
    @SerializedName(value = "contains_happy_emoticon")
	private Boolean containsHappyEmo;
	
	@Expose
    @SerializedName(value = "contains_sad_emoticon")
	private Boolean containsSadEmo;
	
	@Expose
    @SerializedName(value = "contains_first_order_pronoun")
	private Boolean containsFirstOrderPron;
	
	@Expose
    @SerializedName(value = "contains_second_order_pronoun")
	private Boolean containsSecondOrderPron;
	
	@Expose
    @SerializedName(value = "contains_third_order_pronoun")
	private Boolean containsThirdOrderPron;
	
	@Expose
    @SerializedName(value = "num_uppercasechars")
	private Integer numUppercaseChars;
	
	@Expose
    @SerializedName(value = "num_neg_sentiment_words")
	private Integer numNegSentiWords;
	
	@Expose
    @SerializedName(value = "num_pos_sentiment_words")
	private Integer numPosSentiWords;
	
	@Expose
    @SerializedName(value = "num_mentions")
	private Integer numMentions;
	
	@Expose
    @SerializedName(value = "num_hashtags")
	private Integer numHashtags;
	
	@Expose
    @SerializedName(value = "num_URLs")
	private Integer numURLs;
	
	@Expose
    @SerializedName(value = "retweet_count")
	private Long retweetCount;
	
	@Expose
    @SerializedName(value = "has_colon")
	private Boolean hasColon;
	
	@Expose
    @SerializedName(value = "has_please")
	private Boolean hasPlease;
	
	@Expose
    @SerializedName(value = "num_nouns")
	private Integer numNouns;
	
	@Expose
    @SerializedName(value = "has_external_link")
	private Boolean hasExternalLink;
	
	@Expose
    @SerializedName(value = "wot_Trust")
	private Integer wotTrust;
	
	@Expose(serialize=false)
    @SerializedName(value = "wotSafe")
	private Integer wotSafe;
	
	@Expose
    @SerializedName(value = "num_slangs")
	private Integer numSlangs;
	
	@Expose
    @SerializedName(value = "readability")
	private Double readability;
	
	@Expose
    @SerializedName(value = "indegree_centrality")
	private Float urlIndegree;
	
	@Expose
    @SerializedName(value = "harmonic_centrality")
	private Float urlHarmonic;
	
	@Expose
    @SerializedName(value = "alexa_popularity")
	private Integer alexaPopularity;
	
	@Expose
    @SerializedName(value = "alexa_reach_rank")
	private Integer alexaReachRank;
	
	@Expose
    @SerializedName(value = "alexa_delta_rank")
	private Integer alexaDeltaRank;
	
	@Expose
    @SerializedName(value = "alexa_country_rank")
	private Integer alexaCountryRank;
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}
	
	public void setItemLength(Integer itemLength){
		this.itemLength = itemLength;
	}
	
	public Integer getItemLength(){
		return itemLength;
	}
	
	public void setNumWords(Integer numWords){
		this.numWords = numWords;
	}
	
	public Integer getNumWords(){
		return numWords;
	}
	
	public void setContainsExclamationMark(Boolean containsExclamationMark){
		this.containsExclamationMark = containsExclamationMark;
	}
	
	public boolean getContainsExclamationMark(){
		return containsExclamationMark;
	}
	
	public void setContainsQuestionMark(Boolean containsQuestionMark){
		this.containsQuestionMark = containsQuestionMark;
	}

	public boolean getContainsQuestionMark(){
		return containsQuestionMark;
	}
	
	public void setNumExclamationMark(Integer numExclamationMark){
		this.numExclamationMark = numExclamationMark;
	}
	
	public Integer getNumExclamationMark(){
		return numExclamationMark;
	}
	
	public void setNumQuestionMark(Integer numQuestionMark){
		this.numQuestionMark = numQuestionMark;
	}
	
	public Integer getNumQuestionMark(){
		return numQuestionMark;
	}
	
	public void setContainsHappyEmo(boolean containsHappyEmo){
		this.containsHappyEmo = containsHappyEmo;
	}
	
	public boolean getContainsHappyEmo(){
		return containsHappyEmo;
	}
	
	public void setContainsSadEmo(boolean containsSadEmo){
		this.containsSadEmo = containsSadEmo;
	}
	
	public boolean getContainsSadEmo(){
		return containsSadEmo;
	}
	
	public void setContainsFirstOrderPron(boolean containsFirstOrderPron){
		this.containsFirstOrderPron = containsFirstOrderPron;
	}
	
	public Boolean getContainsFirstOrderPron(){
		return containsFirstOrderPron;
	}
		
	public void setContainsSecondOrderPron(boolean containsSecondOrderPron){
		this.containsSecondOrderPron = containsSecondOrderPron;
	}
	
	public Boolean getContainsSecondOrderPron(){
		return containsSecondOrderPron;
	}
	
	public void setContainsThirdOrderPron(boolean containsThirdOrderPron){
		this.containsThirdOrderPron = containsThirdOrderPron;
	}
	 
	public Boolean getContainsThirdOrderPron(){
		return containsThirdOrderPron;
	}
	
	public void setNumUppercaseChars(Integer numUppercaseChars){
		this.numUppercaseChars = numUppercaseChars;
	}
	
	public Integer getNumUppercaseChars(){
		return numUppercaseChars;
	}
	
	public void setNumNegSentiWords(Integer numNegSentiWords){
		this.numNegSentiWords = numNegSentiWords;
	}
	
	public Integer getNumNegSentiWords(){
		return numNegSentiWords;
	}
	
	public void setNumPosSentiWords(Integer numPosSentiWords){
		this.numPosSentiWords = numPosSentiWords;
	}
	
	public Integer getNumPosSentiWords(){
		return numPosSentiWords;
	}
	
	public void setNumMentions(Integer numMentions){
		this.numMentions = numMentions;
	}
	
	public Integer getNumMentions(){
		return numMentions;
	}
	
	public void setNumHashtags(Integer numHashtags){
		this.numHashtags = numHashtags;
	}
	
	public Integer getNumHashtags(){
		return numHashtags;
	}
	
	public void setNumURLs(Integer numURLs){
		this.numURLs = numURLs;
	}
	
	public Integer getNumURLs(){
		return numURLs;
	}
	
	public void setRetweetCount(Long retweetCount){
		this.retweetCount = retweetCount;
	}
	
	public Long getRetweetCount(){
		return retweetCount;
	}
		
	public void setNumSlangs(Integer numSlangs){
		this.numSlangs = numSlangs;
	}
	
	public Integer getNumSlangs() {
		return numSlangs;
	}
	
	public void setHasColon(boolean hasColon) {
		this.hasColon = hasColon;
	}
	
	public boolean getHasColon() {
		return hasColon;
	}
	
	public void setHasPlease(boolean hasPlease) {
		this.hasPlease = hasPlease;
	}
	
	public boolean getHasPlease() {
		return hasPlease;
	}
	
	public void setHasExternalLink(boolean hasExternalLink) {
		this.hasExternalLink = hasExternalLink;
	}
	
	public boolean getHasExternalLink() {
		return hasExternalLink;
	}
	
	public void setWotTrust(Integer wotTrust){
		this.wotTrust = wotTrust;
	}
	
	public Integer getWotTrust() {
		return wotTrust;
	}
	
	public void setWotSafe(Integer wotSafe){
		this.wotSafe = wotSafe;
	}
	
	public Integer getWotSafe() {
		return wotSafe;
	}
	
	public void setReadability(Double readability){
		this.readability = readability;
	}
	
	public Double getReadability() {
		return readability;
	}
	
	public void setUrlIndegree(Float urlIndegree){
		this.urlIndegree = urlIndegree;
	}
	
	public Float getUrlIndegree() {
		return urlIndegree;
	}
	
	public void setUrlHarmonic(Float urlHarmonic){
		this.urlHarmonic = urlHarmonic;
	}
	
	public Float getUrlHarmonic() {
		return urlHarmonic;
	}
	
	public void setNumNouns(Integer numNouns){
		this.numNouns = numNouns;
	}
	
	public Integer getNumNouns() {
		return numNouns;
	}
	
	public void setAlexaPopularity(Integer alexaPopularity) {
		this.alexaPopularity = alexaPopularity;
	}
	
	public Integer getAlexaPopularity() {
		return alexaPopularity;
	}
	
	public void setAlexaCountryRank(Integer alexaCountryRank) {
		this.alexaCountryRank = alexaCountryRank;
	}
	
	public Integer getAlexaCountryRank() {
		return alexaCountryRank;
	}
	
	public void setAlexaDeltaRank(Integer alexaDeltaRank) {
		this.alexaDeltaRank = alexaDeltaRank;
	}
	
	public Integer getAlexaDeltaRank() {
		return alexaDeltaRank;
	}
	
	public void setAlexaReachRank(Integer alexaReachRank) {
		this.alexaReachRank = alexaReachRank;
	}
	
	public Integer getAlexaReachRank() {
		return alexaReachRank;
	}
	
	public String toJSONString() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(this);
	}
	
	 
}
