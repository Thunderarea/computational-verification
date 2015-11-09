package gr.iti.mklab.extractfeatures;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ItemFeatures {
	
	@Expose
    @SerializedName(value = "id")
	private String id;
	
	@Expose
    @SerializedName(value = "itemLength")
	private Integer itemLength;
	
	@Expose
    @SerializedName(value = "numWords")
	private Integer numWords;
	
	@Expose
    @SerializedName(value = "containsQuestionMark")
	private Boolean containsQuestionMark;
	
	@Expose
    @SerializedName(value = "containsExclamationMark")
	private Boolean containsExclamationMark;
	
	@Expose
    @SerializedName(value = "numQuestionMark")
	private Integer numQuestionMark;
	
	@Expose
    @SerializedName(value = "numExclamationMark")
	private Integer numExclamationMark;
	
	@Expose
    @SerializedName(value = "containsHappyEmo")
	private Boolean containsHappyEmo;
	
	@Expose
    @SerializedName(value = "containsSadEmo")
	private Boolean containsSadEmo;
	
	@Expose
    @SerializedName(value = "containsFirstOrderPron")
	private Boolean containsFirstOrderPron;
	
	@Expose
    @SerializedName(value = "containsSecondOrderPron")
	private Boolean containsSecondOrderPron;
	
	@Expose
    @SerializedName(value = "containsThirdOrderPron")
	private Boolean containsThirdOrderPron;
	
	@Expose
    @SerializedName(value = "numUppercaseChars")
	private Integer numUppercaseChars;
	
	@Expose
    @SerializedName(value = "numNegSentiWords")
	private Integer numNegSentiWords;
	
	@Expose
    @SerializedName(value = "numPosSentiWords")
	private Integer numPosSentiWords;
	
	@Expose
    @SerializedName(value = "numMentions")
	private Integer numMentions;
	
	@Expose
    @SerializedName(value = "numHashtags")
	private Integer numHashtags;
	
	@Expose
    @SerializedName(value = "numURLs")
	private Integer numURLs;
	
	@Expose
    @SerializedName(value = "retweetCount")
	private Long retweetCount;
	
	@Expose
    @SerializedName(value = "hasColon")
	private Boolean hasColon;
	
	@Expose
    @SerializedName(value = "hasPlease")
	private Boolean hasPlease;
	
	@Expose
    @SerializedName(value = "numNouns")
	private Integer numNouns;
	
	@Expose
    @SerializedName(value = "hasExternalLink")
	private Boolean hasExternalLink;
	
	@Expose
    @SerializedName(value = "wotTrust")
	private Integer wotTrust;
	
	@Expose
    @SerializedName(value = "wotSafe")
	private Integer wotSafe;
	
	@Expose
    @SerializedName(value = "numSlangs")
	private Integer numSlangs;
	
	@Expose
    @SerializedName(value = "readability")
	private Double readability;
	
	@Expose
    @SerializedName(value = "urlIndegree")
	private Float urlIndegree;
	
	@Expose
    @SerializedName(value = "urlHarmonic")
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
    @SerializedName(value = "alexaPopularity")
	private Integer alexaPopularity;
	
	@Expose
    @SerializedName(value = "alexaReachRank")
	private Integer alexaReachRank;
	
	@Expose
    @SerializedName(value = "alexaDeltaRank")
	private Integer alexaDeltaRank;
	
	@Expose
    @SerializedName(value = "alexaCountryRank")
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
	
	public void setReliability(String reliability){
		this.reliability = reliability;
	}
	
	public String getReliability(){
		return reliability;
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
