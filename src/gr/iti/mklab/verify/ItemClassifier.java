package gr.iti.mklab.verify;
import gr.iti.mklab.extractfeatures.ItemFeatures;
import gr.iti.mklab.extractfeatures.ItemFeaturesAnnotation;
import gr.iti.mklab.extractfeatures.ItemFeaturesExtractorJSON;
import gr.iti.mklab.utils.Vars;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import weka.classifiers.misc.SerializedClassifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Class to organize the Item classification using Item features 
 * by declaring the attributes,creating the testing set 
 * and perform the classification.
 * @author boididou
 * boididou@iti.gr
 */
public class ItemClassifier {
	
	static ArrayList<Attribute> fvAttributes = new ArrayList<Attribute>();
	private static ClassifierAccuracy clAcc = new ClassifierAccuracy();
	
	public static ArrayList<Attribute> getFvAttributes(){
		return fvAttributes;
	}
	
	/**
	 * @return List of attributes needed for the classification
	 */
	public static List<Attribute> declareAttributes(){
		
		Attribute ItemLength = new Attribute("itemLength");
		Attribute numWords = new Attribute("numWords");
		Attribute numQuestionMark = new Attribute("numQuestionMark");
		Attribute numExclamationMark = new Attribute("numExclamationMark");
		Attribute numUppercaseChars = new Attribute("numUppercaseChars");
		Attribute numPosSentiWords = new Attribute("numPosSentiWords");
		Attribute numNegSentiWords = new Attribute("numNegSentiWords");
		Attribute numMentions = new Attribute("numMentions");
		Attribute numHashtags = new Attribute("numHashtags");
		Attribute numURLs = new Attribute("numURLs");
		Attribute retweetCount = new Attribute("retweetCount");
		Attribute numSlangs = new Attribute("numSlangs");
		Attribute numNouns = new Attribute("numNouns");
		Attribute wotTrust = new Attribute("wotTrust");
		Attribute urlIndegree = new Attribute("urlIndegree");
		Attribute readability = new Attribute("readability");
		Attribute urlHarmonic = new Attribute("urlHarmonic");
		Attribute alexaCountryRank = new Attribute("alexaCountryRank");
		Attribute alexaDeltaRank = new Attribute("alexaDeltaRank");
		Attribute alexaPopularity = new Attribute("alexaPopularity");
		Attribute alexaReachRank = new Attribute("alexaReachRank");

		// declare nominal attributes
		List<String> fvnominal1 = new ArrayList<String>(2);
		fvnominal1.add("true");
		fvnominal1.add("false");
		Attribute containsQuestionMark = new Attribute("containsQuestionMark",
				fvnominal1);
		// Attribute containsQuestionMark = new
		// Attribute("containsQuestionMark");

		List<String> fvnominal2 = new ArrayList<String>(2);
		fvnominal2.add("true");
		fvnominal2.add("false");
		Attribute containsExclamationMark = new Attribute(
				"containsExclamationMark", fvnominal2);
		// Attribute containsExclamationMark = new
		// Attribute("containsExclamationMark");

		List<String> fvnominal3 = new ArrayList<String>(2);
		fvnominal3.add("true");
		fvnominal3.add("false");
		Attribute containsHappyEmo = new Attribute("containsHappyEmo",
				fvnominal3);
		// Attribute containsHappyEmo = new Attribute("containsHappyEmo");

		List<String> fvnominal4 = new ArrayList<String>(2);
		fvnominal4.add("true");
		fvnominal4.add("false");
		Attribute containsSadEmo = new Attribute("containsSadEmo", fvnominal4);
		// Attribute containsSadEmo = new Attribute("containsSadEmo");

		List<String> fvnominal5 = new ArrayList<String>(2);
		fvnominal5.add("true");
		fvnominal5.add("false");
		Attribute containsFirstOrderPron = new Attribute(
				"containsFirstOrderPron", fvnominal5);
		// Attribute containsFirstOrderPron = new
		// Attribute("containsFirstOrderPron");

		List<String> fvnominal6 = new ArrayList<String>(2);
		fvnominal6.add("true");
		fvnominal6.add("false");
		Attribute containsSecondOrderPron = new Attribute(
				"containsSecondOrderPron", fvnominal6);
		// Attribute containsSecondOrderPron = new
		// Attribute("containsSecondOrderPron");

		List<String> fvnominal7 = new ArrayList<String>(2);
		fvnominal7.add("true");
		fvnominal7.add("false");
		Attribute containsThirdOrderPron = new Attribute(
				"containsThirdOrderPron", fvnominal7);
		// Attribute containsThirdOrderPron = new
		// Attribute("containsThirdOrderPron");

		List<String> fvnominal8 = new ArrayList<String>(2);
		fvnominal8.add("true");
		fvnominal8.add("false");
		Attribute hasColon = new Attribute("hasColon", fvnominal8);
		// Attribute hasColon = new Attribute("hasColon");

		List<String> fvnominal9 = new ArrayList<String>(2);
		fvnominal9.add("true");
		fvnominal9.add("false");
		Attribute hasPlease = new Attribute("hasPlease", fvnominal9);
		// Attribute hasPlease = new Attribute("hasPlease");

		List<String> fvnominal10 = new ArrayList<String>(2);
		fvnominal10.add("true");
		fvnominal10.add("false");
		Attribute hasExternalLink = new Attribute("hasExternalLink",
				fvnominal10);
		// Attribute hasExternalLink = new Attribute("hasExternalLink");

		List<String> fvnominal11 = null;
		Attribute id = new Attribute("id", fvnominal11);

		List<String> fvClass = new ArrayList<String>(2);
		fvClass.add("real");
		fvClass.add("fake");
		Attribute ClassAttribute = new Attribute("theClass", fvClass);

		// declare the feature vector
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
		fvAttributes.add(alexaDeltaRank);
		fvAttributes.add(alexaPopularity);
		fvAttributes.add(alexaReachRank);
		
		/*fvAttributes.add(numPosSentiWords);
		fvAttributes.add(numNegSentiWords);
		fvAttributes.add(ItemLength);
		fvAttributes.add(wotTrust);
		fvAttributes.add(hasExternalLink);
		fvAttributes.add(readability);*/

		fvAttributes.add(ClassAttribute);

		return fvAttributes;
	}
	
	/**
	 * @param listItemFeatures the ItemFeatures of the Item
	 * @return the Instance created by the features
	 */
	public static Instance createInstance(ItemFeatures listItemFeatures){
		
		Instance inst = new DenseInstance(fvAttributes.size());
		String id = listItemFeatures.getId().replaceAll("[^\\d.]", "");
		inst.setValue((Attribute) fvAttributes.get(0), id);
		
		inst.setValue((Attribute) fvAttributes.get(1),
				listItemFeatures.getItemLength());
		inst.setValue((Attribute) fvAttributes.get(2),
				listItemFeatures.getNumWords());
		inst.setValue((Attribute) fvAttributes.get(3),
				String.valueOf(listItemFeatures.getContainsQuestionMark()));
		
		inst.setValue((Attribute) fvAttributes.get(4),
				String.valueOf(listItemFeatures.getContainsExclamationMark()));
		inst.setValue((Attribute) fvAttributes.get(5),
				String.valueOf(listItemFeatures.getHasExternalLink()));
		if (listItemFeatures.getNumNouns() != null) {
			inst.setValue((Attribute) fvAttributes.get(6),
					listItemFeatures.getNumNouns());
		}
		inst.setValue((Attribute) fvAttributes.get(7),
				String.valueOf(listItemFeatures.getContainsHappyEmo()));
		inst.setValue((Attribute) fvAttributes.get(8),
				String.valueOf(listItemFeatures.getContainsSadEmo()));

		if (listItemFeatures.getContainsFirstOrderPron() != null) {
			inst.setValue((Attribute) fvAttributes.get(9), String
					.valueOf(listItemFeatures.getContainsFirstOrderPron()));
		}
		if (listItemFeatures.getContainsSecondOrderPron() != null) {
			inst.setValue((Attribute) fvAttributes.get(10), String
					.valueOf(listItemFeatures.getContainsSecondOrderPron()));
		}
		if (listItemFeatures.getContainsThirdOrderPron() != null) {
			inst.setValue((Attribute) fvAttributes.get(11), String
					.valueOf(listItemFeatures.getContainsThirdOrderPron()));
		}

		inst.setValue((Attribute) fvAttributes.get(12),
				listItemFeatures.getNumUppercaseChars());

		if (listItemFeatures.getNumPosSentiWords() != null) {
			inst.setValue((Attribute) fvAttributes.get(13),
					listItemFeatures.getNumPosSentiWords());
		}
		if (listItemFeatures.getNumNegSentiWords() != null) {
			inst.setValue((Attribute) fvAttributes.get(14),
					listItemFeatures.getNumNegSentiWords());
		}
		inst.setValue((Attribute) fvAttributes.get(15),
				listItemFeatures.getNumMentions());
		inst.setValue((Attribute) fvAttributes.get(16),
				listItemFeatures.getNumHashtags());
		inst.setValue((Attribute) fvAttributes.get(17),
				listItemFeatures.getNumURLs());
		inst.setValue((Attribute) fvAttributes.get(18),
				listItemFeatures.getRetweetCount());

		if (listItemFeatures.getNumSlangs() != null) {
			inst.setValue((Attribute) fvAttributes.get(19),
					listItemFeatures.getNumSlangs());
		}
		inst.setValue((Attribute) fvAttributes.get(20),
				String.valueOf(listItemFeatures.getHasColon()));
		inst.setValue((Attribute) fvAttributes.get(21),
				String.valueOf(listItemFeatures.getHasPlease()));

		if (listItemFeatures.getWotTrust() != null) {
			inst.setValue((Attribute) fvAttributes.get(22),
					listItemFeatures.getWotTrust());
			
		}
		inst.setValue((Attribute) fvAttributes.get(23),
				listItemFeatures.getNumQuestionMark());
		inst.setValue((Attribute) fvAttributes.get(24),
				listItemFeatures.getNumExclamationMark());

		if (listItemFeatures.getReadability() != null) {
			inst.setValue((Attribute) fvAttributes.get(25),
					listItemFeatures.getReadability());
		}
		if (listItemFeatures.getUrlIndegree() != null) {
			inst.setValue((Attribute) fvAttributes.get(26),
					listItemFeatures.getUrlIndegree());
		}
		if (listItemFeatures.getUrlHarmonic() != null) {
			inst.setValue((Attribute) fvAttributes.get(27),
					listItemFeatures.getUrlHarmonic());
		}
		if (listItemFeatures.getAlexaCountryRank() != null) {
			inst.setValue((Attribute) fvAttributes.get(28),
					listItemFeatures.getAlexaCountryRank());
		}
		if (listItemFeatures.getAlexaDeltaRank() != null) {
			inst.setValue((Attribute) fvAttributes.get(29),
					listItemFeatures.getAlexaDeltaRank());
		}
		if (listItemFeatures.getAlexaPopularity() != null) {
			inst.setValue((Attribute) fvAttributes.get(30),
					listItemFeatures.getAlexaPopularity());
		}
		
		if (listItemFeatures.getAlexaReachRank() != null) {
			
			inst.setValue((Attribute) fvAttributes.get(31),
					listItemFeatures.getAlexaReachRank());
		}
		

		return inst;
	}
	
	/**
	 * @param listItemFeatures list of the ItemFeatures computed
	 * @param itemFeaturesAnnot list of the items' annotation details
	 * @return Instances that form the testing set
	 */
	public static Instances createTestingSet(List<ItemFeatures> listItemFeatures,List<ItemFeaturesAnnotation> listFeaturesAnnot){
		
		//auxiliary variable
		Integer index=0;
		
		// Create an empty training set
		Instances isTestSet = new Instances("Rel", fvAttributes, listItemFeatures.size());           
		
		// Set class index
		isTestSet.setClassIndex(fvAttributes.size()-1);
		
		for (int i=0;i<listItemFeatures.size();i++){
			Instance inst = createInstance(listItemFeatures.get(i));
						
			for (int j=0;j<listFeaturesAnnot.size();j++){
				if (listItemFeatures.get(i).getId().equals(listFeaturesAnnot.get(j).getId())){
					index = j;
				}
			}
			inst.setValue((Attribute)fvAttributes.get(fvAttributes.size()-1), listFeaturesAnnot.get(index).getReliability());
			
			isTestSet.add(inst);
		}
		return isTestSet;	
		
	}
	
	/**
	 * @param listItemFeatures the ItemFeatures computed for the MediaItem
	 * @param listFeaturesAnnot the MediaItem's annotation details
	 * @return Instances that form the testing set
	 */
	public static Instances createTestingSet(ItemFeatures listItemFeatures,ItemFeaturesAnnotation listFeaturesAnnot){
		
		// Create an empty training set
		Instances isTestSet = new Instances("ItemFeatureClassification", fvAttributes, 1);           
		// Set class index
		isTestSet.setClassIndex(ItemClassifier.getFvAttributes().size()-1);
	
		Instance inst = createInstance(listItemFeatures);
			
		inst.setValue((Attribute)fvAttributes.get(ItemClassifier.getFvAttributes().size()-1), listFeaturesAnnot.getReliability());
		
		isTestSet.add(inst);
		
		return isTestSet;	
		
	}
	
	/**
	 * @param isTestSet Instances of the test set
	 * @return Boolean table of reliability values of the test set instances 
	 * @throws Exception file
	 */
	public static boolean[] classifyItems(Instances isTestSet) throws Exception{
		
		//flags variable for the values of the verification
		boolean[] flags = new boolean[isTestSet.size()];
		int count = 0;
		//define the classifier and set the appropriate model file 
		SerializedClassifier classifier = new SerializedClassifier();
		classifier.setModelFile(new File(Vars.MODEL_PATH_ITEM_sample));

		for (int i = 0; i < isTestSet.numInstances(); i++) {
			
			double pred = classifier.classifyInstance(isTestSet.instance(i));
			
			String actual = isTestSet.classAttribute().value((int)isTestSet.instance(i).classValue());
			String predicted = isTestSet.classAttribute().value((int) pred);
			if (actual.equals(predicted)){
				count++;
			}
			
			//assign appropriate value to the flag
			if (predicted=="fake"){
				flags[i]=true;
			}
			else{
				flags[i]=false;
			}
			
		}
		
		//print info
		/*System.out.println();
		System.out.println("=== Results ===");
		System.out.println("Total items "+isTestSet.numInstances());
		System.out.println("Items classified correctly:"+count);
		System.out.println("Percentage "+((double)count/isTestSet.numInstances())*100);
		System.out.println();*/
		return flags;
	}
	
	/**
	 * Function that creates the training set given the features calculated before
	 * @param listItemFeatures the Item features of the MediaItem list
	 * @param itemFeaturesAnnot the MediaItem list's annotation details
	 * @return the Instances formed
	 */
	public static Instances createTrainingSet(List<ItemFeatures> listItemFeatures, List<ItemFeaturesAnnotation> itemFeaturesAnnot){
		
		//auxiliary variable
		Integer index=0;
		
		if (ItemClassifier.getFvAttributes().size()==0){
			fvAttributes = (ArrayList<Attribute>) declareAttributes();
		}
		
		// Create an empty training set
		Instances isTrainingSet = new Instances("TrainingContentFeatures",  ItemClassifier.getFvAttributes(), listItemFeatures.size());           
		
		// Set class index
		isTrainingSet.setClassIndex(fvAttributes.size()-1);
		
		for (int i=0;i<listItemFeatures.size();i++){
			
			Instance inst  = createInstance(listItemFeatures.get(i));
			
			for (int j=0;j<itemFeaturesAnnot.size();j++){
				if ((listItemFeatures.get(i).getId()).equals(itemFeaturesAnnot.get(j).getId())){
					index = j;
				}
			}
			
			inst.setValue((Attribute)fvAttributes.get(fvAttributes.size()-1), itemFeaturesAnnot.get(index).getReliability());
			isTrainingSet.add(inst);
		}
		
		//System.out.println("-----TRAINING SET-------");
		//System.out.println(isTrainingSet);
		
		return isTrainingSet;
	}
	
	

	
	public static Instances reformatInstances(Instances instances) {
		
		ItemClassifier.declareAttributes();
		
		Instances newInstances = new Instances("data", ItemClassifier.getFvAttributes(), instances.size());
		
		for (int i=0; i<instances.numInstances(); i++) {
			
			Instance inst = new DenseInstance(ItemClassifier.getFvAttributes().size());
	
			inst.setValue((Attribute) fvAttributes.get(0), instances.instance(i).stringValue(0));
			inst.setValue((Attribute) fvAttributes.get(1), instances.instance(i).value(1));
			inst.setValue((Attribute) fvAttributes.get(2), instances.instance(i).value(2));
			inst.setValue((Attribute) fvAttributes.get(3), instances.instance(i).value(3));
			inst.setValue((Attribute) fvAttributes.get(4), instances.instance(i).value(4));
			inst.setValue((Attribute) fvAttributes.get(5), instances.instance(i).value(5));
			inst.setValue((Attribute) fvAttributes.get(6), instances.instance(i).value(6));
			inst.setValue((Attribute) fvAttributes.get(7), instances.instance(i).value(7));
			inst.setValue((Attribute) fvAttributes.get(8), instances.instance(i).value(8));
			inst.setValue((Attribute) fvAttributes.get(9), instances.instance(i).value(9));
			inst.setValue((Attribute) fvAttributes.get(10), instances.instance(i).value(10));
			inst.setValue((Attribute) fvAttributes.get(11), instances.instance(i).value(11));
			inst.setValue((Attribute) fvAttributes.get(12),	instances.instance(i).value(12));
			inst.setValue((Attribute) fvAttributes.get(13),	instances.instance(i).value(13));
			inst.setValue((Attribute) fvAttributes.get(14),	instances.instance(i).value(14));
			inst.setValue((Attribute) fvAttributes.get(15),	instances.instance(i).value(15));
			inst.setValue((Attribute) fvAttributes.get(16),	instances.instance(i).value(16));
			inst.setValue((Attribute) fvAttributes.get(17),	instances.instance(i).value(17));
			inst.setValue((Attribute) fvAttributes.get(18),	instances.instance(i).value(18));
			inst.setValue((Attribute) fvAttributes.get(19), instances.instance(i).value(19));
			inst.setValue((Attribute) fvAttributes.get(20), instances.instance(i).value(20));
			inst.setValue((Attribute) fvAttributes.get(21),	instances.instance(i).value(21));
			inst.setValue((Attribute) fvAttributes.get(22),	instances.instance(i).value(22));
			inst.setValue((Attribute) fvAttributes.get(23),	instances.instance(i).value(23));
			inst.setValue((Attribute) fvAttributes.get(24),	instances.instance(i).value(24));
			inst.setValue((Attribute) fvAttributes.get(25),	instances.instance(i).value(25));
			inst.setValue((Attribute) fvAttributes.get(26),	instances.instance(i).value(26));
			inst.setValue((Attribute) fvAttributes.get(27),	instances.instance(i).value(27));
			inst.setValue((Attribute) fvAttributes.get(28),	instances.instance(i).value(28));
			inst.setValue((Attribute) fvAttributes.get(29),	instances.instance(i).value(29));
			inst.setValue((Attribute) fvAttributes.get(30),	instances.instance(i).value(30));
			inst.setValue((Attribute) fvAttributes.get(31), instances.instance(i).value(31));
			inst.setValue((Attribute) fvAttributes.get(32), instances.instance(i).stringValue(32));
				
		
			inst.setDataset(newInstances);
		
			newInstances.add(inst);
			
		}
		
	
		return newInstances;
	}
	
	
	public static Instances formTestingSet(ItemFeatures itemFeats){

		if (ItemClassifier.getFvAttributes().size()==0){
			fvAttributes = (ArrayList<Attribute>) declareAttributes();
		}
		
		Instances testingSet = null;
		//ItemFeatures itemFeats;
		
		try {
			//compute and get the Item features of the current JSON object
			//itemFeats = ItemFeaturesExtractorJSON.extractFeatures(json);
			//create the Item annotation instance
			ItemFeaturesAnnotation itemAnnot = new ItemFeaturesAnnotation();
			itemAnnot.setId(itemFeats.getId());
			itemAnnot.setReliability("fake");
			//create the testing set using the above data
			testingSet = createTestingSet(itemFeats, itemAnnot);
			
		} catch (Exception e) {
			System.out.println("Error on forming the Item features testing set...!");
			e.printStackTrace();
		}
		
		return testingSet;
	}
	
	public static ClassifierAccuracy getClsAccuracy() {
		return clAcc;
	}

}
