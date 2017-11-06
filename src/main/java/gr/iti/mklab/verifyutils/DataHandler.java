package gr.iti.mklab.verifyutils;

import gr.iti.mklab.util.FileManager;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
import weka.filters.unsupervised.attribute.Standardize;

/**
 * The DataHandler class provides solutions for handling the data used on the
 * training and testing procedure. It provides functions for normalizing data,
 * stardizing and handling the missing values on the dataset.
 * 
 * @author Boididou Christina
 * @date 11.07.14
 */
public class DataHandler {

	private static DataHandler dhInstance;

	public static DataHandler getInstance() {
		if (dhInstance == null) {
			dhInstance = new DataHandler();
		}
		return dhInstance;
	}

	static Normalize normFilter = new Normalize();
	static Normalize normFilterUser = new Normalize();
	

	static FilteredClassifier modelTweet = new FilteredClassifier();
	static FilteredClassifier modelUser = new FilteredClassifier();
	
	
	public static void initializeTweetModels() {

		normFilter = new Normalize();	
		modelTweet = new FilteredClassifier();
		
	}
	
	public static void initializeUserModels() {

		normFilterUser = new Normalize();	
		modelUser = new FilteredClassifier();
		
	}
	
	
	public double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    if (((Double)value).toString() != "NaN") {
	    	BigDecimal bd = new BigDecimal(value);
	    	bd = bd.setScale(places, RoundingMode.HALF_UP);
	    	return bd.doubleValue();
	    }
	    else {
	    	return value;
	    }
	}

	/**
	 * Normalizes the given data
	 * 
	 * @param isTrainingSet
	 *            the Instances to be normalized
	 * @param fvAttributes
	 *            List<Attribute> the list of attributes of the current dataset
	 * @return the normalized Instances
	 */
	public Normalize createNormalizationFilter(Instances isTrainingSet) {

		// set the Normalize object
		Normalize norm = new Normalize();
		try {

			// set the parameters for norm object
			norm.setInputFormat(isTrainingSet);

			// set and print the normalization options
			//System.out.println();
			String[] options = { "-S", "1.0", "-T", "0.0" };
			norm.setOptions(options);
			// System.out.print("Normalization options:\t");

		} catch (Exception e) {
			System.out.println("Data Normalization filter cannot be created!");
			e.printStackTrace();
		}
		return norm;
	}

	public Instances normalizeData(Instances dataset, int classIndex,
			Normalize normFilter) {
		//System.out.println(" Normalization Begins .... ");
		Instances dataset_norm = null;
		try {
		//	System.out.println(" Normalization begins 2");
			dataset_norm = Filter.useFilter(dataset, normFilter);
			dataset_norm.setClassIndex(classIndex);
		} catch (Exception e) {
			System.out.println("Data Normalization cannot be performed! Please check your data!");
			e.printStackTrace();
		}

		return dataset_norm;
	}

	/**
	 * Standardizes the given data
	 * 
	 * @param isTrainingSet
	 *            the Instances to be standardized
	 * @return the standardized Instances
	 */
	public Standardize createStandardizationFilter(Instances isTrainingSet) {

		// Instances isTrainingSet_stand = null;

		Standardize filter = new Standardize();
		try {
			filter.setInputFormat(isTrainingSet); // initializing the filter
													// once with training set
			// isTrainingSet_stand = Filter.useFilter(isTrainingSet, filter);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return filter;
	}

	public Instances standardizeData(Instances isTrainingSet, int classIndex,
			Standardize filter) {
		Instances isTrainingSet_stand = null;

		try {
			isTrainingSet_stand = Filter.useFilter(isTrainingSet, filter);
			isTrainingSet_stand.setClassIndex(classIndex);
		} catch (Exception e) {
			System.out
					.println("Data Standardization cannot be performed! Please check your data!");
			e.printStackTrace();
		}

		return isTrainingSet_stand;
	}

	public Instances replaceMissingValues(Instances isTrainingSet,
			ReplaceMissingValues replace) {

		Instances training_data_filter = null;

		try {
			// replace.setInputFormat(isTrainingSet);
			training_data_filter = Filter.useFilter(isTrainingSet, replace);
		} catch (Exception e) {
			System.out
					.println("Cannot replace the missing values! Please check your data!");
			e.printStackTrace();
		}

		// write training set to file
		/*
		 * BufferedWriter bw = null; String filePath =
		 * "trainingset_replaced.txt"; for (int i = 0; i <
		 * training_data_filter.size(); i++) { try {
		 * 
		 * bw = new BufferedWriter(new FileWriter(filePath, true)); // if file
		 * doesn't exist, then create it
		 * bw.append(training_data_filter.get(i).toString()); bw.newLine();
		 * bw.flush(); bw.close();
		 * 
		 * } catch (IOException e) { e.printStackTrace(); } }
		 */

		return training_data_filter;

	}

	public Instances applyRegressionModel(Instances data, Classifier model)
			throws Exception {

		// System.out.println("======Apply regression model======");

		// declare the new training set
		Instances newData = new Instances(data,0);

		// change the class index
		// isTrainingSet.setClassIndex(index);
		int index = data.classIndex();

		// System.out.println(model);
		int attSize = data.get(0).numAttributes();
		
		// classify the instances
		double value = 0.0;
		DenseInstance inst = new DenseInstance(attSize);
		//DenseInstance newinst = new DenseInstance(attSize);
		
		for (int i = 0; i < data.numInstances(); i++) {
			//System.out.println(i);
			inst = (DenseInstance) data.get(i);
			//System.out.println(inst);
			if (inst.isMissing(index)) {
				value = 0.0;
				try {
					
					//System.out.println(inst);
					//System.out.println(inst.classIndex());
					value = model.classifyInstance(inst);
					
					DenseInstance newinst = new DenseInstance(attSize);
					for (int j = 0; j < inst.numAttributes(); j++) {
						newinst.setValue(j, inst.value(j));
					}
					newinst.setValue(index, value);

					newData.add(newinst);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			} else {
				newData.add(inst);
			}
		}
		
		//System.out.println("Exception caught "+counter);
		// System.out.println("counter "+counter);
		newData.setClassIndex(data.numAttributes() - 1);
		data.setClassIndex(data.numAttributes() - 1);
		// System.out.println(isTrainingSet);

		
		return newData;
	}
	
	public Instances getTransformedTrainingUser(Instances trainingSet, String className) throws IOException{
		
		initializeUserModels();
		Remove rm = new Remove();
		rm.setAttributeIndices("1");
		
		// REGRESSION
				// wotTrust
			//	trainingSet.setClassIndex(22);
				trainingSet.setClass(trainingSet.attribute(className));
				//System.out.println(trainingSet.attribute(className));
				//System.out.println(" ---- Actual " + className + " ---- defined " + trainingSet.classIndex() + "   " +trainingSet.classAttribute());
				LinearRegression lr = new LinearRegression();
				Instances training_regr = null;
				modelUser.setFilter(rm);
				modelUser.setClassifier(lr);
				
				//FileManager.getInstance().writeDataToFile("D:/TweetVerification/Reproducibility/Example/trainingSet" + className + ".arff", trainingSet);

				try {
					modelUser.buildClassifier(trainingSet);
					training_regr = DataHandler.getInstance().applyRegressionModel(
							trainingSet, modelUser);
				} catch (Exception e) {
					training_regr = trainingSet;
					//System.out.println("not enough training instances. Linear Regression not performed!");
				}
		
				//FileManager.getInstance().writeDataToFile("D:/TweetVerification/Reproducibility/Example/training_regr" + className + ".arff", training_regr);

		
		return training_regr;
	}
	
	/**
	 * Applies Linear Regression and normalization to the testing set according
	 * to the models and filters created before by the training set. It is the
	 * case of the Item type Instances.
	 * 
	 * @param testing
	 *            the Instances to be transformed
	 * @return the transformed testing set
	 * @throws Exception
	 */
	public Instances getTransformedTestingUser(Instances testing, String className) throws Exception {

		Instances testing_regr = null;
		
		// regression
		if (!modelUser.toString().contains("No model built yet.")) {
			//testing.setClassIndex(22);
			testing.setClass(testing.attribute(className));
			//System.out.println("testing " + testing.attribute(className));
			testing_regr = DataHandler.getInstance().applyRegressionModel(
					testing, modelUser);
		} else {
			//System.out.println("MOdel NO ******************************************************************");
			testing_regr = testing;
		}		
		
		return testing_regr;
	}
	
	public Instances normalizationUser(Instances training) throws Exception{
		
		String[] options = { "-S", "1.0", "-T", "0.0" };
		normFilterUser.setOptions(options);
		normFilterUser.setInputFormat(training);
		//System.out.println(normFilter.getOptions());

		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(training, training.numAttributes() - 1, normFilterUser);
		trainingSet_normed.setClassIndex(training.numAttributes()-1);
		
		for (int i=0; i<trainingSet_normed.size(); i++) {
			for (int j=0; j<trainingSet_normed.get(i).numAttributes();j++) {
				if (!trainingSet_normed.get(i).attribute(j).isNominal())
				trainingSet_normed.get(i).setValue(j, round(trainingSet_normed.get(i).value(j), 6) );
			}
		}
		
		trainingSet_normed = getTrimmedInstances(trainingSet_normed);
		
		return trainingSet_normed;
	}
	
	public Instances normalizationTestingUser(Instances testing) throws Exception{
		
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(testing, testing.numAttributes() - 1, normFilterUser);
		testingSet_normed.setClassIndex(testing.numAttributes()-1);
		
		// testingSet_normed = getTrimmedInstances(testingSet_normed);
		for (int i=0; i<testingSet_normed.size(); i++) {
			for (int j=0; j<testingSet_normed.get(i).numAttributes();j++) {
				if (!testingSet_normed.get(i).attribute(j).isNominal())
				testingSet_normed.get(i).setValue(j, round(testingSet_normed.get(i).value(j), 6) );
			}
		}
		
		testingSet_normed = getTrimmedInstances(testingSet_normed);
		
		return testingSet_normed;
		
	}


	
	public Instances getTransformedTrainingTweet(Instances trainingSet, String className) throws IOException{
		
		initializeTweetModels();
		Remove rm = new Remove();
		rm.setAttributeIndices("1");
		
				trainingSet.setClass(trainingSet.attribute(className));
				//System.out.println(trainingSet.attribute(className));
				//System.out.println(" ---- Actual " + className + " ---- defined " + trainingSet.classIndex() + "   " +trainingSet.classAttribute());
				LinearRegression lr = new LinearRegression();
				Instances training_regr = null;
				modelTweet.setFilter(rm);
				modelTweet.setClassifier(lr);
				
				//FileManager.getInstance().writeDataToFile("D:/TweetVerification/Reproducibility/Example/trainingSet" + className + ".arff", trainingSet);

				try {
					modelTweet.buildClassifier(trainingSet);
					training_regr = DataHandler.getInstance().applyRegressionModel(
							trainingSet, modelTweet);
				} catch (Exception e) {
					training_regr = trainingSet;
					//System.out.println("not enough training instances. Linear Regression not performed!");
				}
		
				//FileManager.getInstance().writeDataToFile("D:/TweetVerification/Reproducibility/Example/training_regr" + className + ".arff", training_regr);

		
		return training_regr;
	}
	
	/**
	 * Applies Linear Regression and normalization to the testing set according
	 * to the models and filters created before by the training set. It is the
	 * case of the Item type Instances.
	 * 
	 * @param testing
	 *            the Instances to be transformed
	 * @return the transformed testing set
	 * @throws Exception
	 */
	public Instances getTransformedTestingTweet(Instances testing, String className) throws Exception {

		//ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();
		Instances testing_regr = null;
		//System.out.println(modelTweet.getOptions());
		//System.out.println(modelTweet.toString());
		// regression
		if (!modelTweet.toString().contains("No model built yet.")) {
			//testing.setClassIndex(22);
			testing.setClass(testing.attribute(className));
			//System.out.println("testing " + testing.attribute(className));
			testing_regr = DataHandler.getInstance().applyRegressionModel(
					testing, modelTweet);
		} else {
			testing_regr = testing;
		}		
		
		return testing_regr;
	}
	
	public Instances normalizationTweet(Instances training) throws Exception{
		
		String[] options = { "-S", "1.0", "-T", "0.0" };
		normFilter.setOptions(options);
		normFilter.setInputFormat(training);
		//System.out.println(normFilter.getOptions());

		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(training, training.numAttributes() - 1, normFilter);
		trainingSet_normed.setClassIndex(training.numAttributes()-1);
		
		for (int i=0; i<trainingSet_normed.size(); i++) {
			for (int j=0; j<trainingSet_normed.get(i).numAttributes();j++) {
				if (!trainingSet_normed.get(i).attribute(j).isNominal())
				trainingSet_normed.get(i).setValue(j, round(trainingSet_normed.get(i).value(j), 6) );
			}
		}
		
		trainingSet_normed = getTrimmedInstances(trainingSet_normed);
		
		return trainingSet_normed;
	}

	
	public Instances normalizationTestingTweet(Instances testing) throws Exception{
		
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(testing, testing.numAttributes() - 1, normFilter);
		testingSet_normed.setClassIndex(testing.numAttributes()-1);
		
		// testingSet_normed = getTrimmedInstances(testingSet_normed);
		for (int i=0; i<testingSet_normed.size(); i++) {
			for (int j=0; j<testingSet_normed.get(i).numAttributes();j++) {
				if (!testingSet_normed.get(i).attribute(j).isNominal())
				testingSet_normed.get(i).setValue(j, round(testingSet_normed.get(i).value(j), 6) );
			}
		}
		
		testingSet_normed = getTrimmedInstances(testingSet_normed);
		
		return testingSet_normed;
		
	}


	/**
	 * Function that given the current ranks, adds them in the map
	 * @param ranks double[][] the current ranks
	 * @param map HashMap<Double, List<Double>> the map currently edited
	 * @return map HashMap<Double, List<Double>> the updated map
	 */
	public HashMap<Double, List<Double>> editRankingMap(double[][] ranks, HashMap<Double, List<Double>> map) {

		for (int i = 0; i < ranks.length; i++) {
			for (int j = 0; j < ranks[i].length; j = j + 2) {
				// add
				if (map.get(ranks[i][j]) != null) {
					map.get(ranks[i][j]).add(ranks[i][j + 1]);
				} else {
					List<Double> tempList = new ArrayList<Double>();
					tempList.add(ranks[i][j + 1]);
					map.put(ranks[i][j], tempList);
				}
				//System.out.println(ranks[i][j] + " " + ranks[i][j + 1]);
				
			}
		}
		return map;
	}
	
	public HashMap<String,Double> calculateTotalAttributeRanking() throws IOException{
		
		HashMap<String,Double> ranking = new HashMap<String, Double>();
		
		String filePath = "C:\\Users\\boididou\\Dropbox\\tomm_journal\\experiments\\selected_set_features";
		File folder = new File(filePath);
		for (File f:folder.listFiles()) {
			
			for (File f2:f.listFiles()) {
				
				if (f2.getName().contains("userFeaturesSelected")) {
					HashMap<String,String> current = FileManager.getInstance().loadHashmapfromFile(f2.getAbsolutePath());
					//System.out.println(current);
					for (Map.Entry<String, String> entry : current.entrySet()) {
												
						String currentKey   =  entry.getKey();
						Double currentValue =  Double.parseDouble(entry.getValue());
						
						if (ranking.get(currentKey) != null) {
							ranking.put(entry.getKey(), ranking.get(entry.getKey()) + currentValue);
						}
						else {
							ranking.put(entry.getKey(), currentValue);
						}
					}//for
					
				}
			}
			
		}
		
		for (Map.Entry<String, Double> entry : ranking.entrySet()) {
			Double newValue = (double) entry.getValue()/10;
			ranking.put(entry.getKey(), newValue);
		}
		
		ranking = FileManager.getInstance().sortByComparatorDouble(ranking);
		System.out.println(ranking);
		
		return ranking;
	}
	
	public Instances getTrimmedInstances(Instances data) {
		
		for (int i=0;i<data.size();i++) {
			for (int j=0; j<data.instance(i).numValues(); j++) {
				if (!data.instance(i).attribute(j).isString()) {
					if (data.instance(i).value(j) < 0) {
						data.instance(i).setValue(j, 0);
					}
					else if (data.instance(i).value(j) > 1) {
						data.instance(i).setValue(j, 1);
					}
				}
			}
		}
		return data;
		}



}
