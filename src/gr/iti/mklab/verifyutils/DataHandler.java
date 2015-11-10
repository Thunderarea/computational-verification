package gr.iti.mklab.verifyutils;

import gr.iti.mklab.verify.ItemClassifier;
import gr.iti.mklab.verify.UserClassifier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
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

	static FilteredClassifier model  = new FilteredClassifier();
	static FilteredClassifier model2 = new FilteredClassifier();
	static FilteredClassifier model3 = new FilteredClassifier();
	static FilteredClassifier model4 = new FilteredClassifier();
	static FilteredClassifier model5 = new FilteredClassifier();
	static FilteredClassifier model6 = new FilteredClassifier();
	static FilteredClassifier model7 = new FilteredClassifier();
	static FilteredClassifier model8 = new FilteredClassifier();
	static FilteredClassifier model9 = new FilteredClassifier();
	static FilteredClassifier model10 = new FilteredClassifier();
	static FilteredClassifier model11 = new FilteredClassifier();
	static FilteredClassifier model12 = new FilteredClassifier();
	static FilteredClassifier model13 = new FilteredClassifier();
	static FilteredClassifier model14 = new FilteredClassifier();
	static FilteredClassifier model15 = new FilteredClassifier();
	
	static FilteredClassifier modelbf_01 = new FilteredClassifier();
	static FilteredClassifier modelbf_02 = new FilteredClassifier();
	static FilteredClassifier modelbf_03 = new FilteredClassifier();
	static FilteredClassifier modelbf_04 = new FilteredClassifier();
	static FilteredClassifier modelbf_05 = new FilteredClassifier();
	static FilteredClassifier modelbf_06 = new FilteredClassifier();
	static FilteredClassifier modelbf_07 = new FilteredClassifier();
	static FilteredClassifier modelbf_08 = new FilteredClassifier();
	static FilteredClassifier modelbf_09 = new FilteredClassifier();
	static FilteredClassifier modelbf_10 = new FilteredClassifier();
	static FilteredClassifier modelbf_11 = new FilteredClassifier();
	static FilteredClassifier modelbf_12 = new FilteredClassifier();
	static FilteredClassifier modelbf_13 = new FilteredClassifier();
	static FilteredClassifier modelbf_14 = new FilteredClassifier();
	static FilteredClassifier modelbf_15 = new FilteredClassifier();
	static FilteredClassifier modelbf_16 = new FilteredClassifier();
	static FilteredClassifier modelbf_17 = new FilteredClassifier();
	static FilteredClassifier modelbf_18 = new FilteredClassifier();
	static FilteredClassifier modelbf_19 = new FilteredClassifier();
	static FilteredClassifier modelbf_20 = new FilteredClassifier();
	static FilteredClassifier modelbf_21 = new FilteredClassifier();
	static FilteredClassifier modelbf_22 = new FilteredClassifier();

	static FilteredClassifier modelsad_01 = new FilteredClassifier();
	static FilteredClassifier modelsad_02 = new FilteredClassifier();
	static FilteredClassifier modelsad_03 = new FilteredClassifier();
	static FilteredClassifier modelsad_04 = new FilteredClassifier();
	static FilteredClassifier modelsad_05 = new FilteredClassifier();
	static FilteredClassifier modelsad_06 = new FilteredClassifier();
	static FilteredClassifier modelsad_07 = new FilteredClassifier();
	static FilteredClassifier modelsad_08 = new FilteredClassifier();
	
	static FilteredClassifier modelsnad_01 = new FilteredClassifier();
	static FilteredClassifier modelsnad_02 = new FilteredClassifier();
	static FilteredClassifier modelsnad_03 = new FilteredClassifier();
	static FilteredClassifier modelsnad_04 = new FilteredClassifier();
	static FilteredClassifier modelsnad_05 = new FilteredClassifier();
	static FilteredClassifier modelsnad_06 = new FilteredClassifier();
	static FilteredClassifier modelsnad_07 = new FilteredClassifier();
	static FilteredClassifier modelsnad_08 = new FilteredClassifier();
	
	static FilteredClassifier modelajpgbag_01 = new FilteredClassifier();
	static FilteredClassifier modelajpgbag_02 = new FilteredClassifier();
	static FilteredClassifier modelajpgbag_03 = new FilteredClassifier();
	static FilteredClassifier modelajpgbag_04 = new FilteredClassifier();
	static FilteredClassifier modelajpgbag_05 = new FilteredClassifier();
	static FilteredClassifier modelajpgbag_06 = new FilteredClassifier();
	static FilteredClassifier modelajpgbag_07 = new FilteredClassifier();
	static FilteredClassifier modelajpgbag_08 = new FilteredClassifier();
	static FilteredClassifier modelajpgbag_09 = new FilteredClassifier();
	static FilteredClassifier modelajpgbag_10 = new FilteredClassifier();
	static FilteredClassifier modelajpgbag_11 = new FilteredClassifier();
	static FilteredClassifier modelajpgbag_12 = new FilteredClassifier();
	static FilteredClassifier modelajpgbag_13 = new FilteredClassifier();
	static FilteredClassifier modelajpgbag_14 = new FilteredClassifier();
	static FilteredClassifier modelajpgbag_15 = new FilteredClassifier();
	static FilteredClassifier modelajpgbag_16 = new FilteredClassifier();
	
	static FilteredClassifier modelnajpgbag_01 = new FilteredClassifier();
	static FilteredClassifier modelnajpgbag_02 = new FilteredClassifier();
	static FilteredClassifier modelnajpgbag_03 = new FilteredClassifier();
	static FilteredClassifier modelnajpgbag_04 = new FilteredClassifier();
	static FilteredClassifier modelnajpgbag_05 = new FilteredClassifier();
	static FilteredClassifier modelnajpgbag_06 = new FilteredClassifier();
	static FilteredClassifier modelnajpgbag_07 = new FilteredClassifier();
	static FilteredClassifier modelnajpgbag_08 = new FilteredClassifier();
	static FilteredClassifier modelnajpgbag_09 = new FilteredClassifier();
	static FilteredClassifier modelnajpgbag_10 = new FilteredClassifier();
	static FilteredClassifier modelnajpgbag_11 = new FilteredClassifier();
	static FilteredClassifier modelnajpgbag_12 = new FilteredClassifier();
	static FilteredClassifier modelnajpgbag_13 = new FilteredClassifier();
	static FilteredClassifier modelnajpgbag_14 = new FilteredClassifier();
	static FilteredClassifier modelnajpgbag_15 = new FilteredClassifier();
	static FilteredClassifier modelnajpgbag_16 = new FilteredClassifier();

	static FilteredClassifier usermodel = new FilteredClassifier();
	static FilteredClassifier usermodel2 = new FilteredClassifier();
	static FilteredClassifier usermodel3 = new FilteredClassifier();
	static FilteredClassifier usermodel4 = new FilteredClassifier();
	static FilteredClassifier usermodel5 = new FilteredClassifier();
	static FilteredClassifier usermodel6 = new FilteredClassifier();
	static FilteredClassifier usermodel7 = new FilteredClassifier();
	static FilteredClassifier usermodel8 = new FilteredClassifier();
	static FilteredClassifier usermodel9 = new FilteredClassifier();
	
	
	public static void initializeModels() {

		model = new FilteredClassifier();
		model2 = new FilteredClassifier();
		model3 = new FilteredClassifier();
		model4 = new FilteredClassifier();
		model5 = new FilteredClassifier();
		model6 = new FilteredClassifier();
		model7 = new FilteredClassifier();
		model8 = new FilteredClassifier();
		usermodel = new FilteredClassifier();
		usermodel2 = new FilteredClassifier();
		usermodel3 = new FilteredClassifier();
		usermodel4 = new FilteredClassifier();
		usermodel5 = new FilteredClassifier();
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
			System.out.println();
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

		Instances dataset_norm = null;
		try {
			
			dataset_norm = Filter.useFilter(dataset, normFilter);
			dataset_norm.setClassIndex(classIndex);
			
		} catch (Exception e) {
			System.out
					.println("Data Normalization cannot be performed! Please check your data!");
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

	public Instances applyRegressionModel(Instances data, ArrayList<Attribute> fvAttributes, Classifier model)
			throws Exception {

		// System.out.println("======Apply regression model======");

		// declare the new training set
		int size = data.size();

		Instances newData = new Instances("Rel", fvAttributes, size);

		//System.out.println(data);
		// change the class index
		// isTrainingSet.setClassIndex(index);
		int index = data.classIndex();

		// System.out.println(model);

		int counter = 0;
		// classify the instances

		for (int i = 0; i < data.numInstances(); i++) {

			DenseInstance inst = (DenseInstance) data.get(i);
			
			if (inst.isMissing(index)) {
				double value = 0.0;
				try {
					
					//System.out.println(inst);
					//System.out.println(inst.classIndex());
					value = model.classifyInstance((DenseInstance)data.get(i));
					
					Instance newinst = new DenseInstance(fvAttributes.size());
					for (int j = 0; j < inst.numAttributes(); j++) {
						newinst.setValue(j, inst.value(j));
					}
					newinst.setValue(index, value);

					newData.add(newinst);
					
				}catch(Exception e) {
					//System.out.println(model);
					//System.out.println("exception for " +inst );
					e.printStackTrace();
					counter++;
				}
				
			} else {
				newData.add(inst);
			}
		}
		
		//System.out.println("Exception caught "+counter);
		// System.out.println("counter "+counter);
		newData.setClassIndex(fvAttributes.size() - 1);
		data.setClassIndex(fvAttributes.size() - 1);
		// System.out.println(isTrainingSet);

		
		
		return newData;
	}

	public Instances getTransformedTrainingOld(Instances trainingSet)
			throws Exception {

		initializeModels();

		Remove rm = new Remove();
		rm.setAttributeIndices("1");
		Remove rm2 = new Remove();
		rm2.setAttributeIndices("1");
		Remove rm3 = new Remove();
		rm3.setAttributeIndices("1");
		Remove rm4 = new Remove();
		rm4.setAttributeIndices("1");
		Remove rm5 = new Remove();
		rm5.setAttributeIndices("1");

		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();

		// REGRESSION
		// num pos senti
		trainingSet.setClass(fvAttributes.get(11));
		LinearRegression lr = new LinearRegression();
		Instances training_regr = null;
		model.setFilter(rm);
		model.setClassifier(lr);

		try {
			model.buildClassifier(trainingSet);
			training_regr = DataHandler.getInstance().applyRegressionModel(
					trainingSet, fvAttributes, model);
		} catch (Exception e) {
			training_regr = trainingSet;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// num neg senti
		training_regr.setClass(fvAttributes.get(12));
		LinearRegression lr2 = new LinearRegression();
		Instances training_regr2 = null;

		model2.setFilter(rm2);
		model2.setClassifier(lr2);
		try {
			model2.buildClassifier(training_regr);
			training_regr2 = DataHandler.getInstance().applyRegressionModel(
					training_regr, fvAttributes, model2);
		} catch (Exception e) {
			training_regr2 = training_regr;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		//first pron
		training_regr2.setClass(fvAttributes.get(7));
		LinearRegression lr3 = new LinearRegression();
		Instances training_regr3 = null;

		model3.setFilter(rm3);
		model3.setClassifier(lr3);
		try {
			model3.buildClassifier(training_regr2);
			training_regr3 = DataHandler.getInstance().applyRegressionModel(
					training_regr2, fvAttributes, model3);
		} catch (Exception e) {
			training_regr3 = training_regr2;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		//second pron
		training_regr3.setClass(fvAttributes.get(8));
		LinearRegression lr4 = new LinearRegression();
		Instances training_regr4 = null;

		model4.setFilter(rm4);
		model4.setClassifier(lr4);
		try {
			model4.buildClassifier(training_regr3);
			training_regr4 = DataHandler.getInstance().applyRegressionModel(
					training_regr3, fvAttributes, model4);
		} catch (Exception e) {
			training_regr4 = training_regr3;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		//third pron
		training_regr4.setClass(fvAttributes.get(9));
		LinearRegression lr5 = new LinearRegression();
		Instances training_regr5 = null;

		model5.setFilter(rm5);
		model5.setClassifier(lr5);
		try {
			model5.buildClassifier(training_regr4);
			training_regr5 = DataHandler.getInstance().applyRegressionModel(
					training_regr4, fvAttributes, model5);
		} catch (Exception e) {
			training_regr5 = training_regr4;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// normalization part
		String[] options = { "-S", "1.0", "-T", "0.0" };
		normFilter.setOptions(options);
		normFilter.setInputFormat(training_regr5);

		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(
				training_regr5, fvAttributes.size() - 1, normFilter);

		
		trainingSet_normed = getTrimmedInstances(trainingSet_normed);
		
		return trainingSet_normed;

	}

	/**
	 * Applies linear regression to the missing values of the training set in
	 * order to predict their values and normalization to make the values fall
	 * into the range [0,1]. It is the case of the Item type Instances.
	 * 
	 * @param trainingSet
	 * @return Instances the transformed training set
	 * @throws Exception
	 */
	public Instances getTransformedTraining(Instances trainingSet)
			throws Exception {

		initializeModels();

		Remove rm = new Remove();
		rm.setAttributeIndices("1");

		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();

		// REGRESSION
		// wotTrust
		trainingSet.setClass(fvAttributes.get(22));
		LinearRegression lr = new LinearRegression();
		Instances training_regr = null;
		model.setFilter(rm);
		model.setClassifier(lr);

		try {
			model.buildClassifier(trainingSet);
			training_regr = DataHandler.getInstance().applyRegressionModel(
					trainingSet, fvAttributes, model);
		} catch (Exception e) {
			training_regr = trainingSet;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// readability
		training_regr.setClass(fvAttributes.get(25));
		LinearRegression lr2 = new LinearRegression();
		Instances training_regr2 = null;

		model2.setFilter(rm);
		model2.setClassifier(lr2);
		try {
			model2.buildClassifier(training_regr);
			training_regr2 = DataHandler.getInstance().applyRegressionModel(
					training_regr, fvAttributes, model2);
		} catch (Exception e) {
			training_regr2 = training_regr;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// num slangs
		training_regr2.setClass(fvAttributes.get(19));
		LinearRegression lr3 = new LinearRegression();
		Instances training_regr3 = null;

		model3.setFilter(rm);
		model3.setClassifier(lr3);
		try {
			model3.buildClassifier(training_regr2);
			training_regr3 = DataHandler.getInstance().applyRegressionModel(
					training_regr2, fvAttributes, model3);
		} catch (Exception e) {
			training_regr3 = training_regr2;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// getNumPosSentiWords
		training_regr3.setClass(fvAttributes.get(13));
		LinearRegression lr4 = new LinearRegression();
		Instances training_regr4 = null;
		model4.setFilter(rm);
		model4.setClassifier(lr4);
		try {
			model4.buildClassifier(training_regr3);
			training_regr4 = DataHandler.getInstance().applyRegressionModel(
					training_regr3, fvAttributes, model4);
		} catch (Exception e) {
			training_regr4 = training_regr3;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// getNumNegSentiWords
		training_regr4.setClass(fvAttributes.get(14));
		LinearRegression lr5 = new LinearRegression();
		Instances training_regr5 = null;
		model5.setFilter(rm);
		model5.setClassifier(lr5);

		try {
			model5.buildClassifier(training_regr4);
			training_regr5 = DataHandler.getInstance().applyRegressionModel(
					training_regr4, fvAttributes, model5);
		} catch (Exception e) {
			training_regr5 = training_regr4;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// getNumNouns
		training_regr5.setClass(fvAttributes.get(6));
		LinearRegression lr6 = new LinearRegression();
		Instances training_regr6 = null;
		model6.setFilter(rm);
		model6.setClassifier(lr6);
		try {
			model6.buildClassifier(training_regr5);
			training_regr6 = DataHandler.getInstance().applyRegressionModel(
					training_regr5, fvAttributes, model6);
		} catch (Exception e) {
			training_regr6 = training_regr5;
		}

		// indegree
		training_regr6.setClass(fvAttributes.get(26));
		LinearRegression lr7 = new LinearRegression();
		Instances training_regr7 = null;
		model7.setFilter(rm);
		model7.setClassifier(lr7);
		try {
			model7.buildClassifier(training_regr6);
			training_regr7 = DataHandler.getInstance().applyRegressionModel(
					training_regr6, fvAttributes, model7);
		} catch (Exception e) {
			training_regr7 = training_regr6;
		}

		// System.out.println(model7);

		// harmonic
		training_regr7.setClass(fvAttributes.get(27));
		LinearRegression lr8 = new LinearRegression();
		Instances training_regr8 = null;
		model8.setFilter(rm);
		model8.setClassifier(lr8);
		try {
			model8.buildClassifier(training_regr7);
			training_regr8 = DataHandler.getInstance().applyRegressionModel(
					training_regr7, fvAttributes, model8);
		} catch (Exception e) {
			training_regr8 = training_regr7;
		}

		// System.out.println(model8);

		// normalization part
		String[] options = { "-S", "1.0", "-T", "0.0" };
		normFilter.setOptions(options);
		normFilter.setInputFormat(training_regr8);

		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(
				training_regr8, fvAttributes.size() - 1, normFilter);

		return trainingSet_normed;

	}
	
	/**
	 * Applies linear regression to the missing values of the training set in
	 * order to predict their values and normalization to make the values fall
	 * into the range [0,1]. It is the case of the Item type Instances.
	 * 
	 * @param trainingSet
	 * @return Instances the transformed training set
	 * @throws Exception
	 */
	public Instances getTransformedTrainingOverall(Instances trainingSet)
			throws Exception {

		initializeModels();
		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();
		
		
		Remove rm = new Remove();
		rm.setAttributeIndices("1");
		Remove rm1 = new Remove();
		rm1.setAttributeIndices("1");
		Remove rm2 = new Remove();
		rm2.setAttributeIndices("1");
		Remove rm3 = new Remove();
		rm3.setAttributeIndices("1");
		Remove rm4 = new Remove();
		rm4.setAttributeIndices("1");
		Remove rm5 = new Remove();
		rm5.setAttributeIndices("1");
		Remove rm6 = new Remove();
		rm6.setAttributeIndices("1");
		Remove rm7 = new Remove();
		rm7.setAttributeIndices("1");
		Remove rm8 = new Remove();
		rm8.setAttributeIndices("1");
		Remove rm9 = new Remove();
		rm9.setAttributeIndices("1");
		Remove rm10 = new Remove();
		rm10.setAttributeIndices("1");
		Remove rm11 = new Remove();
		rm11.setAttributeIndices("1");
		Remove rm12 = new Remove();
		rm12.setAttributeIndices("1");
		Remove rm13 = new Remove();
		rm13.setAttributeIndices("1");
		Remove rm14 = new Remove();
		rm14.setAttributeIndices("1");
		
		
		// REGRESSION
		// wotTrust
		trainingSet.setClass(fvAttributes.get(22));
				
		LinearRegression lr = new LinearRegression();
		Instances training_regr = null;
		model.setFilter(rm);
		model.setClassifier(lr);

		try {
			model.buildClassifier(trainingSet);
			training_regr = DataHandler.getInstance().applyRegressionModel(trainingSet, fvAttributes, model);
		} catch (Exception e) {
			training_regr = trainingSet;
		}
		
		
		
		// readability
		training_regr.setClass(fvAttributes.get(25));
		LinearRegression lr2 = new LinearRegression();
		Instances training_regr2 = null;

		model2.setFilter(rm1);
		model2.setClassifier(lr2);
		try {
			model2.buildClassifier(training_regr);
			training_regr2 = DataHandler.getInstance().applyRegressionModel(
					training_regr, fvAttributes, model2);
		} catch (Exception e) {
			training_regr2 = training_regr;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// alexa popularity
		training_regr2.setClass(fvAttributes.get(30));
		LinearRegression lr3 = new LinearRegression();
		Instances training_regr3 = null;

		model3.setFilter(rm2);
		model3.setClassifier(lr3);
		try {
			model3.buildClassifier(training_regr2);
			training_regr3 = DataHandler.getInstance().applyRegressionModel(
					training_regr2, fvAttributes, model3);
		} catch (Exception e) {
			training_regr3 = training_regr2;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// alexa delta rank
		training_regr3.setClass(fvAttributes.get(29));
		LinearRegression lr4 = new LinearRegression();
		Instances training_regr4 = null;
		model4.setFilter(rm3);
		model4.setClassifier(lr4);
		try {
			model4.buildClassifier(training_regr3);
			training_regr4 = DataHandler.getInstance().applyRegressionModel(
					training_regr3, fvAttributes, model4);
		} catch (Exception e) {
			training_regr4 = training_regr3;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// getNumNegSentiWords
		training_regr4.setClass(fvAttributes.get(14));
		LinearRegression lr5 = new LinearRegression();
		Instances training_regr5 = null;
		model5.setFilter(rm4);
		model5.setClassifier(lr5);

		try {
			model5.buildClassifier(training_regr4);
			training_regr5 = DataHandler.getInstance().applyRegressionModel(
					training_regr4, fvAttributes, model5);
		} catch (Exception e) {
			training_regr5 = training_regr4;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// num slangs
		training_regr5.setClass(fvAttributes.get(19));
		LinearRegression lr6 = new LinearRegression();
		Instances training_regr6 = null;
		model6.setFilter(rm5);
		model6.setClassifier(lr6);
		try {
			model6.buildClassifier(training_regr5);
			training_regr6 = DataHandler.getInstance().applyRegressionModel(
					training_regr5, fvAttributes, model6);
		} catch (Exception e) {
			training_regr6 = training_regr5;
		}

		// indegree
		training_regr6.setClass(fvAttributes.get(26));
		LinearRegression lr7 = new LinearRegression();
		Instances training_regr7 = null;
		model7.setFilter(rm6);
		model7.setClassifier(lr7);
		try {
			model7.buildClassifier(training_regr6);
			training_regr7 = DataHandler.getInstance().applyRegressionModel(
					training_regr6, fvAttributes, model7);
		} catch (Exception e) {
			training_regr7 = training_regr6;
		}

		// System.out.println(model7);

		//alexa country rank 
		training_regr7.setClass(fvAttributes.get(28));
		LinearRegression lr8 = new LinearRegression();
		Instances training_regr8 = null;
		model8.setFilter(rm7);
		model8.setClassifier(lr8);
		try {
			model8.buildClassifier(training_regr7);
			training_regr8 = DataHandler.getInstance().applyRegressionModel(
					training_regr7, fvAttributes, model8);
		} catch (Exception e) {
			training_regr8 = training_regr7;
		}

		// harmonic
		training_regr8.setClass(fvAttributes.get(27));
		LinearRegression lr9 = new LinearRegression();
		Instances training_regr9 = null;
		model9.setFilter(rm8);
		model9.setClassifier(lr9);
		try {
			model9.buildClassifier(training_regr8);
			training_regr9 = DataHandler.getInstance().applyRegressionModel(
					training_regr8, fvAttributes, model9);
		} catch (Exception e) {
			training_regr9 = training_regr8;
		}

		// getNumPosSentiWords
		training_regr9.setClass(fvAttributes.get(13));
		LinearRegression lr10 = new LinearRegression();
		Instances training_regr10 = null;
		model10.setFilter(rm9);
		model10.setClassifier(lr10);
		try {
			model10.buildClassifier(training_regr9);
			training_regr10 = DataHandler.getInstance().applyRegressionModel(
					training_regr9, fvAttributes, model10);
		} catch (Exception e) {
			training_regr10 = training_regr9;
		}
		
		//alexa reach rank
		training_regr10.setClass(fvAttributes.get(31));
		LinearRegression lr11 = new LinearRegression();
		Instances training_regr11 = null;
		model11.setFilter(rm10);
		model11.setClassifier(lr11);
		try {
			model11.buildClassifier(training_regr10);
			training_regr11 = DataHandler.getInstance().applyRegressionModel(
					training_regr10, fvAttributes, model11);
		} catch (Exception e) {
			training_regr11 = training_regr10;
		}
		
		//getNumNouns
		training_regr11.setClass(fvAttributes.get(6));
		LinearRegression lr12 = new LinearRegression();
		Instances training_regr12 = null;
		model12.setFilter(rm11);
		model12.setClassifier(lr12);
		try {
			model12.buildClassifier(training_regr11);
			training_regr12 = DataHandler.getInstance().applyRegressionModel(
					training_regr11, fvAttributes, model12);
		} catch (Exception e) {
			training_regr12 = training_regr11;
		}
		
		
		//1st pron
		training_regr12.setClass(fvAttributes.get(9));
		LinearRegression lr13 = new LinearRegression();
		Instances training_regr13 = null;
		model13.setFilter(rm12);
		model13.setClassifier(lr13);
		try {
			model13.buildClassifier(training_regr12);
			training_regr13 = DataHandler.getInstance().applyRegressionModel(training_regr12, fvAttributes, model13);			
		} catch (Exception e) {
			training_regr13 = training_regr12;
		}
		
		
		//2nd pron
		training_regr13.setClass(fvAttributes.get(10));
		LinearRegression lr14 = new LinearRegression();
		Instances training_regr14 = null;
		model14.setFilter(rm13);
		model14.setClassifier(lr14);
		try {
			model14.buildClassifier(training_regr13);
			training_regr14 = DataHandler.getInstance().applyRegressionModel(
					training_regr13, fvAttributes, model14);
		} catch (Exception e) {
			training_regr14 = training_regr13;
		}
		
		//3rd pron
		training_regr14.setClass(fvAttributes.get(11));
		LinearRegression lr15 = new LinearRegression();
		Instances training_regr15 = null;
		model15.setFilter(rm14);
		model15.setClassifier(lr15);
		try {
			model15.buildClassifier(training_regr14);
			training_regr15 = DataHandler.getInstance().applyRegressionModel(
					training_regr14, fvAttributes, model15);
		} catch (Exception e) {
			training_regr15 = training_regr14;
		}

		
		// normalization part
		String[] options = { "-S", "1.0", "-T", "0.0" };
		normFilter.setOptions(options);
		normFilter.setInputFormat(training_regr15);

		//System.out.println("SIZE OF DATA "+training_regr15.size());
		//System.out.println(training_regr15.toSummaryString());
		
		//Instances trainingSet_normed = training_regr15;
		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(training_regr15, fvAttributes.size() - 1, normFilter);
		trainingSet_normed.setClassIndex(fvAttributes.size()-1);
		
		for (int i=0; i<trainingSet_normed.size(); i++) {
			//System.out.println(trainingSet_normed.get(i));
			for (int j=0; j<trainingSet_normed.get(i).numAttributes();j++) {
				if (!trainingSet_normed.get(i).attribute(j).isNominal())
				trainingSet_normed.get(i).setValue(j, round(trainingSet_normed.get(i).value(j), 6) );
			}
			//System.out.println(trainingSet_normed.get(i));
		}
		
		trainingSet_normed = getTrimmedInstances(trainingSet_normed);
		
		return trainingSet_normed;

	}
	
	/**
	 * Applies linear regression to the missing values of the training set in
	 * order to predict their values and normalization to make the values fall
	 * into the range [0,1]. It is the case of the Item type Instances.
	 * 
	 * @param trainingSet
	 * @return Instances the transformed training set
	 * @throws Exception
	 */
	public Instances getTransformedTrainingEnriched(Instances trainingSet)
			throws Exception {

		initializeModels();
		System.out.println(trainingSet.get(0));
		Remove rm = new Remove();
		rm.setAttributeIndices("1");

		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();
		
		// REGRESSION
		// wotTrust
		trainingSet.setClass(fvAttributes.get(22));
		LinearRegression lr = new LinearRegression();
		Instances training_regr = null;
		model.setFilter(rm);
		model.setClassifier(lr);

		try {
			model.buildClassifier(trainingSet);
			training_regr = DataHandler.getInstance().applyRegressionModel(
					trainingSet, fvAttributes, model);
		} catch (Exception e) {
			training_regr = trainingSet;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// readability
		training_regr.setClass(fvAttributes.get(25));
		LinearRegression lr2 = new LinearRegression();
		Instances training_regr2 = null;

		model2.setFilter(rm);
		model2.setClassifier(lr2);
		try {
			model2.buildClassifier(training_regr);
			training_regr2 = DataHandler.getInstance().applyRegressionModel(
					training_regr, fvAttributes, model2);
		} catch (Exception e) {
			training_regr2 = training_regr;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// alexa popularity
		training_regr2.setClass(fvAttributes.get(30));
		LinearRegression lr3 = new LinearRegression();
		Instances training_regr3 = null;

		model3.setFilter(rm);
		model3.setClassifier(lr3);
		try {
			model3.buildClassifier(training_regr2);
			training_regr3 = DataHandler.getInstance().applyRegressionModel(
					training_regr2, fvAttributes, model3);
		} catch (Exception e) {
			training_regr3 = training_regr2;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// alexa delta rank
		training_regr3.setClass(fvAttributes.get(29));
		LinearRegression lr4 = new LinearRegression();
		Instances training_regr4 = null;
		model4.setFilter(rm);
		model4.setClassifier(lr4);
		try {
			model4.buildClassifier(training_regr3);
			training_regr4 = DataHandler.getInstance().applyRegressionModel(
					training_regr3, fvAttributes, model4);
		} catch (Exception e) {
			training_regr4 = training_regr3;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// getNumNegSentiWords
		training_regr4.setClass(fvAttributes.get(14));
		LinearRegression lr5 = new LinearRegression();
		Instances training_regr5 = null;
		model5.setFilter(rm);
		model5.setClassifier(lr5);

		try {
			model5.buildClassifier(training_regr4);
			training_regr5 = DataHandler.getInstance().applyRegressionModel(
					training_regr4, fvAttributes, model5);
		} catch (Exception e) {
			training_regr5 = training_regr4;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		
		// num slangs
		training_regr5.setClass(fvAttributes.get(19));
		LinearRegression lr6 = new LinearRegression();
		Instances training_regr6 = null;
		model6.setFilter(rm);
		model6.setClassifier(lr6);
		try {
			model6.buildClassifier(training_regr5);
			training_regr6 = DataHandler.getInstance().applyRegressionModel(
					training_regr5, fvAttributes, model6);
		} catch (Exception e) {
			training_regr6 = training_regr5;
		}

		// indegree
		training_regr6.setClass(fvAttributes.get(26));
		LinearRegression lr7 = new LinearRegression();
		Instances training_regr7 = null;
		model7.setFilter(rm);
		model7.setClassifier(lr7);
		try {
			model7.buildClassifier(training_regr6);
			training_regr7 = DataHandler.getInstance().applyRegressionModel(
					training_regr6, fvAttributes, model7);
		} catch (Exception e) {
			training_regr7 = training_regr6;
		}

		

		//alexa country rank 
		training_regr7.setClass(fvAttributes.get(28));
		LinearRegression lr8 = new LinearRegression();
		Instances training_regr8 = null;
		model8.setFilter(rm);
		model8.setClassifier(lr8);
		try {
			model8.buildClassifier(training_regr7);
			training_regr8 = DataHandler.getInstance().applyRegressionModel(
					training_regr7, fvAttributes, model8);
		} catch (Exception e) {
			training_regr8 = training_regr7;
		}

		// harmonic
		training_regr8.setClass(fvAttributes.get(27));
		LinearRegression lr9 = new LinearRegression();
		Instances training_regr9 = null;
		model9.setFilter(rm);
		model9.setClassifier(lr9);
		try {
			model9.buildClassifier(training_regr8);
			training_regr9 = DataHandler.getInstance().applyRegressionModel(
					training_regr8, fvAttributes, model9);
		} catch (Exception e) {
			training_regr9 = training_regr8;
		}

		// getNumPosSentiWords
		training_regr9.setClass(fvAttributes.get(13));
		LinearRegression lr10 = new LinearRegression();
		Instances training_regr10 = null;
		model10.setFilter(rm);
		model10.setClassifier(lr10);
		try {
			model10.buildClassifier(training_regr9);
			training_regr10 = DataHandler.getInstance().applyRegressionModel(
					training_regr9, fvAttributes, model10);
		} catch (Exception e) {
			training_regr10 = training_regr9;
		}
		
		//alexa reach rank
		training_regr10.setClass(fvAttributes.get(31));
		LinearRegression lr11 = new LinearRegression();
		Instances training_regr11 = null;
		model11.setFilter(rm);
		model11.setClassifier(lr11);
		try {
			model11.buildClassifier(training_regr10);
			training_regr11 = DataHandler.getInstance().applyRegressionModel(
					training_regr10, fvAttributes, model11);
		} catch (Exception e) {
			training_regr11 = training_regr10;
		}
		
		//getNumNouns
		training_regr11.setClass(fvAttributes.get(6));
		LinearRegression lr12 = new LinearRegression();
		Instances training_regr12 = null;
		model12.setFilter(rm);
		model12.setClassifier(lr12);
		try {
			model12.buildClassifier(training_regr11);
			training_regr12 = DataHandler.getInstance().applyRegressionModel(
					training_regr11, fvAttributes, model12);
		} catch (Exception e) {
			training_regr12 = training_regr11;
		}
		
		
		//first pron
		training_regr12.setClass(fvAttributes.get(7));
		LinearRegression lr13 = new LinearRegression();
		Instances training_regr13 = null;

		model13.setFilter(rm);
		model13.setClassifier(lr13);
		try {
			model13.buildClassifier(training_regr12);
			training_regr13 = DataHandler.getInstance().applyRegressionModel(
					training_regr12, fvAttributes, model13);
		} catch (Exception e) {
			training_regr13 = training_regr12;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		//second pron
		training_regr13.setClass(fvAttributes.get(8));
		LinearRegression lr14 = new LinearRegression();
		Instances training_regr14 = null;

		model14.setFilter(rm);
		model14.setClassifier(lr14);
		try {
			model14.buildClassifier(training_regr13);
			training_regr14 = DataHandler.getInstance().applyRegressionModel(
					training_regr13, fvAttributes, model14);
		} catch (Exception e) {
			training_regr14 = training_regr13;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		//third pron
		training_regr14.setClass(fvAttributes.get(9));
		LinearRegression lr15 = new LinearRegression();
		Instances training_regr15 = null;

		model15.setFilter(rm);
		model15.setClassifier(lr15);
		try {
			model15.buildClassifier(training_regr14);
			training_regr15 = DataHandler.getInstance().applyRegressionModel(
					training_regr14, fvAttributes, model15);
		} catch (Exception e) {
			training_regr15 = training_regr14;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		
		//sad 
		training_regr15.setClass(fvAttributes.get(54));
		LinearRegression lr15a = new LinearRegression();
		Instances training_regr15a = null;
		modelsad_01.setFilter(rm);
		modelsad_01.setClassifier(lr15a);
		try {
			modelsad_01.buildClassifier(training_regr15);
			training_regr15a = DataHandler.getInstance().applyRegressionModel(
					training_regr15, fvAttributes, modelsad_01);
		} catch (Exception e) {
			training_regr15a = training_regr15;
		}
		
		training_regr15a.setClass(fvAttributes.get(55));
		LinearRegression lr15b = new LinearRegression();
		Instances training_regr15b = null;
		modelsad_02.setFilter(rm);
		modelsad_02.setClassifier(lr15b);
		try {
			modelsad_02.buildClassifier(training_regr15a);
			training_regr15b = DataHandler.getInstance().applyRegressionModel(
					training_regr15a, fvAttributes, modelsad_02);
		} catch (Exception e) {
			training_regr15b = training_regr15a;
		}
		
		training_regr15b.setClass(fvAttributes.get(56));
		LinearRegression lr15c = new LinearRegression();
		Instances training_regr15c = null;
		modelsad_03.setFilter(rm);
		modelsad_03.setClassifier(lr15c);
		try {
			modelsad_03.buildClassifier(training_regr15b);
			training_regr15c = DataHandler.getInstance().applyRegressionModel(
					training_regr15b, fvAttributes, modelsad_03);
		} catch (Exception e) {
			training_regr15c = training_regr15b;
		}
		
		training_regr15c.setClass(fvAttributes.get(57));
		LinearRegression lr16 = new LinearRegression();
		Instances training_regr16 = null;
		modelsad_04.setFilter(rm);
		modelsad_04.setClassifier(lr16);
		try {
			modelsad_04.buildClassifier(training_regr15c);
			training_regr16 = DataHandler.getInstance().applyRegressionModel(
					training_regr15c, fvAttributes, modelsad_04);
		} catch (Exception e) {
			training_regr16 = training_regr15c;
		}
		
		training_regr16.setClass(fvAttributes.get(58));
		LinearRegression lr17 = new LinearRegression();
		Instances training_regr17 = null;
		modelsad_05.setFilter(rm);
		modelsad_05.setClassifier(lr17);
		try {
			modelsad_05.buildClassifier(training_regr16);
			training_regr17 = DataHandler.getInstance().applyRegressionModel(
					training_regr16, fvAttributes, modelsad_05);
		} catch (Exception e) {
			training_regr17 = training_regr16;
		}
		
		training_regr17.setClass(fvAttributes.get(59));
		LinearRegression lr18 = new LinearRegression();
		Instances training_regr18 = null;
		modelsad_06.setFilter(rm);
		modelsad_06.setClassifier(lr18);
		try {
			modelsad_06.buildClassifier(training_regr17);
			training_regr18 = DataHandler.getInstance().applyRegressionModel(
					training_regr17, fvAttributes, modelsad_06);
		} catch (Exception e) {
			training_regr18 = training_regr17;
		}
		
		training_regr18.setClass(fvAttributes.get(60));
		LinearRegression lr19 = new LinearRegression();
		Instances training_regr19 = null;
		modelsad_07.setFilter(rm);
		modelsad_07.setClassifier(lr19);
		try {
			modelsad_07.buildClassifier(training_regr18);
			training_regr19 = DataHandler.getInstance().applyRegressionModel(
					training_regr18, fvAttributes, modelsad_07);
		} catch (Exception e) {
			training_regr19 = training_regr18;
		}
		
		training_regr19.setClass(fvAttributes.get(61));
		LinearRegression lr20 = new LinearRegression();
		Instances training_regr20 = null;
		modelsad_08.setFilter(rm);
		modelsad_08.setClassifier(lr20);
		try {
			modelsad_08.buildClassifier(training_regr19);
			training_regr20 = DataHandler.getInstance().applyRegressionModel(
					training_regr19, fvAttributes, modelsad_08);
		} catch (Exception e) {
			training_regr20 = training_regr19;
		}
		
		training_regr20.setClass(fvAttributes.get(62));
		LinearRegression lr21 = new LinearRegression();
		Instances training_regr21 = null;
		modelsnad_01.setFilter(rm);
		modelsnad_01.setClassifier(lr21);
		try {
			modelsnad_01.buildClassifier(training_regr20);
			training_regr21 = DataHandler.getInstance().applyRegressionModel(
					training_regr20, fvAttributes, modelsnad_01);
		} catch (Exception e) {
			training_regr21 = training_regr20;
		}
		
		training_regr21.setClass(fvAttributes.get(63));
		LinearRegression lr22 = new LinearRegression();
		Instances training_regr22 = null;
		modelsnad_02.setFilter(rm);
		modelsnad_02.setClassifier(lr22);
		try {
			modelsnad_02.buildClassifier(training_regr21);
			training_regr22 = DataHandler.getInstance().applyRegressionModel(
					training_regr21, fvAttributes, modelsnad_02);
		} catch (Exception e) {
			training_regr22 = training_regr21;
		}
		
		training_regr22.setClass(fvAttributes.get(64));
		LinearRegression lr23 = new LinearRegression();
		Instances training_regr23 = null;
		modelsnad_03.setFilter(rm);
		modelsnad_03.setClassifier(lr23);
		try {
			modelsnad_03.buildClassifier(training_regr22);
			training_regr23 = DataHandler.getInstance().applyRegressionModel(
					training_regr22, fvAttributes, modelsnad_03);
		} catch (Exception e) {
			training_regr23 = training_regr22;
		}
		
		training_regr23.setClass(fvAttributes.get(65));
		LinearRegression lr24 = new LinearRegression();
		Instances training_regr24 = null;
		modelsnad_04.setFilter(rm);
		modelsnad_04.setClassifier(lr24);
		try {
			modelsnad_04.buildClassifier(training_regr23);
			training_regr24 = DataHandler.getInstance().applyRegressionModel(
					training_regr23, fvAttributes, modelsnad_04);
		} catch (Exception e) {
			training_regr24 = training_regr23;
		}
		
		training_regr24.setClass(fvAttributes.get(66));
		LinearRegression lr25 = new LinearRegression();
		Instances training_regr25 = null;
		modelsnad_05.setFilter(rm);
		modelsnad_05.setClassifier(lr25);
		try {
			modelsnad_05.buildClassifier(training_regr24);
			training_regr25 = DataHandler.getInstance().applyRegressionModel(
					training_regr24, fvAttributes, modelsnad_05);
		} catch (Exception e) {
			training_regr25 = training_regr24;
		}
		
		training_regr25.setClass(fvAttributes.get(67));
		LinearRegression lr26 = new LinearRegression();
		Instances training_regr26 = null;
		modelsnad_06.setFilter(rm);
		modelsnad_06.setClassifier(lr26);
		try {
			modelsnad_06.buildClassifier(training_regr25);
			training_regr26 = DataHandler.getInstance().applyRegressionModel(
					training_regr25, fvAttributes, modelsnad_06);
		} catch (Exception e) {
			training_regr26 = training_regr25;
		}
		
		training_regr26.setClass(fvAttributes.get(68));
		LinearRegression lr27 = new LinearRegression();
		Instances training_regr27 = null;
		modelsnad_07.setFilter(rm);
		modelsnad_07.setClassifier(lr27);
		try {
			modelsnad_07.buildClassifier(training_regr26);
			training_regr27 = DataHandler.getInstance().applyRegressionModel(
					training_regr26, fvAttributes, modelsnad_07);
		} catch (Exception e) {
			training_regr27 = training_regr26;
		}
		
		training_regr27.setClass(fvAttributes.get(69));
		LinearRegression lr28 = new LinearRegression();
		Instances training_regr28 = null;
		modelsnad_08.setFilter(rm);
		modelsnad_08.setClassifier(lr28);
		try {
			modelsnad_08.buildClassifier(training_regr27);
			training_regr28 = DataHandler.getInstance().applyRegressionModel(
					training_regr27, fvAttributes, modelsnad_08);
		} catch (Exception e) {
			training_regr28 = training_regr27;
		}
		
		training_regr28.setClass(fvAttributes.get(32));
		LinearRegression lr29 = new LinearRegression();
		Instances training_regr29 = null;
		modelbf_01.setFilter(rm);
		modelbf_01.setClassifier(lr29);
		try {
			modelbf_01.buildClassifier(training_regr28);
			training_regr29 = DataHandler.getInstance().applyRegressionModel(
					training_regr28, fvAttributes, modelbf_01);
		} catch (Exception e) {
			training_regr29 = training_regr28;
		}
		
		training_regr29.setClass(fvAttributes.get(33));
		LinearRegression lr30 = new LinearRegression();
		Instances training_regr30 = null;
		modelbf_02.setFilter(rm);
		modelbf_02.setClassifier(lr30);
		try {
			modelbf_02.buildClassifier(training_regr29);
			training_regr30 = DataHandler.getInstance().applyRegressionModel(
					training_regr29, fvAttributes, modelbf_02);
		} catch (Exception e) {
			training_regr30 = training_regr29;
		}
		
		training_regr30.setClass(fvAttributes.get(34));
		LinearRegression lr31 = new LinearRegression();
		Instances training_regr31 = null;
		modelbf_03.setFilter(rm);
		modelbf_03.setClassifier(lr31);
		try {
			modelbf_03.buildClassifier(training_regr30);
			training_regr31 = DataHandler.getInstance().applyRegressionModel(
					training_regr30, fvAttributes, modelbf_03);
		} catch (Exception e) {
			training_regr31 = training_regr30;
		}
		
		training_regr31.setClass(fvAttributes.get(35));
		LinearRegression lr32 = new LinearRegression();
		Instances training_regr32 = null;
		modelbf_04.setFilter(rm);
		modelbf_04.setClassifier(lr32);
		try {
			modelbf_04.buildClassifier(training_regr31);
			training_regr32 = DataHandler.getInstance().applyRegressionModel(
					training_regr31, fvAttributes, modelbf_04);
		} catch (Exception e) {
			training_regr32 = training_regr31;
		}
		
		training_regr32.setClass(fvAttributes.get(36));
		LinearRegression lr33 = new LinearRegression();
		Instances training_regr33 = null;
		modelbf_05.setFilter(rm);
		modelbf_05.setClassifier(lr33);
		try {
			modelbf_05.buildClassifier(training_regr32);
			training_regr33 = DataHandler.getInstance().applyRegressionModel(
					training_regr32, fvAttributes, modelbf_05);
		} catch (Exception e) {
			training_regr33 = training_regr32;
		}
		
		training_regr33.setClass(fvAttributes.get(37));
		LinearRegression lr34 = new LinearRegression();
		Instances training_regr34 = null;
		modelbf_06.setFilter(rm);
		modelbf_06.setClassifier(lr34);
		try {
			modelbf_06.buildClassifier(training_regr33);
			training_regr34 = DataHandler.getInstance().applyRegressionModel(
					training_regr33, fvAttributes, modelbf_06);
		} catch (Exception e) {
			training_regr34 = training_regr33;
		}
		
		training_regr34.setClass(fvAttributes.get(38));
		LinearRegression lr35 = new LinearRegression();
		Instances training_regr35 = null;
		modelbf_07.setFilter(rm);
		modelbf_07.setClassifier(lr35);
		try {
			modelbf_07.buildClassifier(training_regr34);
			training_regr35 = DataHandler.getInstance().applyRegressionModel(
					training_regr34, fvAttributes, modelbf_07);
		} catch (Exception e) {
			training_regr35 = training_regr34;
		}
		
		training_regr35.setClass(fvAttributes.get(39));
		LinearRegression lr36 = new LinearRegression();
		Instances training_regr36 = null;
		modelbf_08.setFilter(rm);
		modelbf_08.setClassifier(lr36);
		try {
			modelbf_08.buildClassifier(training_regr35);
			training_regr36 = DataHandler.getInstance().applyRegressionModel(
					training_regr35, fvAttributes, modelbf_08);
		} catch (Exception e) {
			training_regr36 = training_regr35;
		}
		
		training_regr36.setClass(fvAttributes.get(40));
		LinearRegression lr37 = new LinearRegression();
		Instances training_regr37 = null;
		modelbf_09.setFilter(rm);
		modelbf_09.setClassifier(lr37);
		try {
			modelbf_09.buildClassifier(training_regr36);
			training_regr37 = DataHandler.getInstance().applyRegressionModel(
					training_regr36, fvAttributes, modelbf_09);
		} catch (Exception e) {
			training_regr37 = training_regr36;
		}
		
		training_regr37.setClass(fvAttributes.get(41));
		LinearRegression lr38 = new LinearRegression();
		Instances training_regr38 = null;
		modelbf_10.setFilter(rm);
		modelbf_10.setClassifier(lr38);
		try {
			modelbf_10.buildClassifier(training_regr37);
			training_regr38 = DataHandler.getInstance().applyRegressionModel(
					training_regr37, fvAttributes, modelbf_10);
		} catch (Exception e) {
			training_regr38 = training_regr37;
		}
		
		training_regr38.setClass(fvAttributes.get(42));
		LinearRegression lr39 = new LinearRegression();
		Instances training_regr39 = null;
		modelbf_11.setFilter(rm);
		modelbf_11.setClassifier(lr39);
		try {
			modelbf_11.buildClassifier(training_regr38);
			training_regr39 = DataHandler.getInstance().applyRegressionModel(
					training_regr38, fvAttributes, modelbf_11);
		} catch (Exception e) {
			training_regr39 = training_regr38;
		}
		
		training_regr39.setClass(fvAttributes.get(43));
		LinearRegression lr40 = new LinearRegression();
		Instances training_regr40 = null;
		modelbf_12.setFilter(rm);
		modelbf_12.setClassifier(lr40);
		try {
			modelbf_12.buildClassifier(training_regr39);
			training_regr40 = DataHandler.getInstance().applyRegressionModel(
					training_regr39, fvAttributes, modelbf_12);
		} catch (Exception e) {
			training_regr40 = training_regr39;
		}
		
		training_regr40.setClass(fvAttributes.get(44));
		LinearRegression lr41 = new LinearRegression();
		Instances training_regr41 = null;
		modelbf_13.setFilter(rm);
		modelbf_13.setClassifier(lr41);
		try {
			modelbf_13.buildClassifier(training_regr40);
			training_regr41 = DataHandler.getInstance().applyRegressionModel(
					training_regr40, fvAttributes, modelbf_13);
		} catch (Exception e) {
			training_regr41 = training_regr40;
		}
		
		training_regr41.setClass(fvAttributes.get(45));
		LinearRegression lr42 = new LinearRegression();
		Instances training_regr42 = null;
		modelbf_14.setFilter(rm);
		modelbf_14.setClassifier(lr42);
		try {
			modelbf_14.buildClassifier(training_regr41);
			training_regr42 = DataHandler.getInstance().applyRegressionModel(
					training_regr41, fvAttributes, modelbf_14);
		} catch (Exception e) {
			training_regr42 = training_regr41;
		}
		
		training_regr42.setClass(fvAttributes.get(46));
		LinearRegression lr43 = new LinearRegression();
		Instances training_regr43 = null;
		modelbf_15.setFilter(rm);
		modelbf_15.setClassifier(lr43);
		try {
			modelbf_15.buildClassifier(training_regr42);
			training_regr43 = DataHandler.getInstance().applyRegressionModel(
					training_regr42, fvAttributes, modelbf_15);
		} catch (Exception e) {
			training_regr43 = training_regr42;
		}
		
		training_regr43.setClass(fvAttributes.get(47));
		LinearRegression lr44 = new LinearRegression();
		Instances training_regr44 = null;
		modelbf_16.setFilter(rm);
		modelbf_16.setClassifier(lr44);
		try {
			modelbf_16.buildClassifier(training_regr43);
			training_regr44 = DataHandler.getInstance().applyRegressionModel(
					training_regr43, fvAttributes, modelbf_16);
		} catch (Exception e) {
			training_regr44 = training_regr43;
		}
		
		training_regr44.setClass(fvAttributes.get(48));
		LinearRegression lr45 = new LinearRegression();
		Instances training_regr45 = null;
		modelbf_17.setFilter(rm);
		modelbf_17.setClassifier(lr45);
		try {
			modelbf_17.buildClassifier(training_regr44);
			training_regr45 = DataHandler.getInstance().applyRegressionModel(
					training_regr44, fvAttributes, modelbf_17);
		} catch (Exception e) {
			training_regr45 = training_regr44;
		}
		
		training_regr45.setClass(fvAttributes.get(49));
		LinearRegression lr46 = new LinearRegression();
		Instances training_regr46 = null;
		modelbf_18.setFilter(rm);
		modelbf_18.setClassifier(lr46);
		try {
			modelbf_18.buildClassifier(training_regr45);
			training_regr46 = DataHandler.getInstance().applyRegressionModel(
					training_regr45, fvAttributes, modelbf_18);
		} catch (Exception e) {
			training_regr46 = training_regr45;
		}
		
		training_regr46.setClass(fvAttributes.get(50));
		LinearRegression lr47 = new LinearRegression();
		Instances training_regr47 = null;
		modelbf_19.setFilter(rm);
		modelbf_19.setClassifier(lr47);
		try {
			modelbf_19.buildClassifier(training_regr46);
			training_regr47 = DataHandler.getInstance().applyRegressionModel(
					training_regr46, fvAttributes, modelbf_19);
		} catch (Exception e) {
			training_regr47 = training_regr46;
		}
		
		training_regr47.setClass(fvAttributes.get(51));
		LinearRegression lr48 = new LinearRegression();
		Instances training_regr48 = null;
		modelbf_20.setFilter(rm);
		modelbf_20.setClassifier(lr48);
		try {
			modelbf_20.buildClassifier(training_regr47);
			training_regr48 = DataHandler.getInstance().applyRegressionModel(
					training_regr47, fvAttributes, modelbf_20);
		} catch (Exception e) {
			training_regr48 = training_regr47;
		}
		
		training_regr48.setClass(fvAttributes.get(52));
		LinearRegression lr49 = new LinearRegression();
		Instances training_regr49 = null;
		modelbf_21.setFilter(rm);
		modelbf_21.setClassifier(lr49);
		try {
			modelbf_21.buildClassifier(training_regr48);
			training_regr49 = DataHandler.getInstance().applyRegressionModel(
					training_regr48, fvAttributes, modelbf_21);
		} catch (Exception e) {
			training_regr49 = training_regr48;
		}
		
		training_regr49.setClass(fvAttributes.get(53));
		LinearRegression lr50 = new LinearRegression();
		Instances training_regr50 = null;
		modelbf_22.setFilter(rm);
		modelbf_22.setClassifier(lr50);
		try {
			modelbf_22.buildClassifier(training_regr49);
			training_regr50 = DataHandler.getInstance().applyRegressionModel(
					training_regr49, fvAttributes, modelbf_22);
		} catch (Exception e) {
			training_regr50 = training_regr49;
		}
		
		training_regr50.setClass(fvAttributes.get(70));
		LinearRegression lr51 = new LinearRegression();
		Instances training_regr51 = null;
		modelajpgbag_01.setFilter(rm);
		modelajpgbag_01.setClassifier(lr51);
		try {
			modelajpgbag_01.buildClassifier(training_regr50);
			training_regr51 = DataHandler.getInstance().applyRegressionModel(
					training_regr50, fvAttributes, modelajpgbag_01);
		} catch (Exception e) {
			training_regr51 = training_regr50;
		}
		
		training_regr51.setClass(fvAttributes.get(71));
		LinearRegression lr52 = new LinearRegression();
		Instances training_regr52 = null;
		modelajpgbag_02.setFilter(rm);
		modelajpgbag_02.setClassifier(lr52);
		try {
			modelajpgbag_02.buildClassifier(training_regr51);
			training_regr52 = DataHandler.getInstance().applyRegressionModel(
					training_regr51, fvAttributes, modelajpgbag_02);
		} catch (Exception e) {
			training_regr52 = training_regr51;
		}
		
		training_regr52.setClass(fvAttributes.get(72));
		LinearRegression lr53 = new LinearRegression();
		Instances training_regr53 = null;
		modelajpgbag_03.setFilter(rm);
		modelajpgbag_03.setClassifier(lr53);
		try {
			modelajpgbag_03.buildClassifier(training_regr52);
			training_regr53 = DataHandler.getInstance().applyRegressionModel(
					training_regr52, fvAttributes, modelajpgbag_03);
		} catch (Exception e) {
			training_regr53 = training_regr52;
		}
		
		training_regr53.setClass(fvAttributes.get(73));
		LinearRegression lr54 = new LinearRegression();
		Instances training_regr54 = null;
		modelajpgbag_04.setFilter(rm);
		modelajpgbag_04.setClassifier(lr54);
		try {
			modelajpgbag_04.buildClassifier(training_regr53);
			training_regr54 = DataHandler.getInstance().applyRegressionModel(
					training_regr53, fvAttributes, modelajpgbag_04);
		} catch (Exception e) {
			training_regr54 = training_regr53;
		}
		
		training_regr54.setClass(fvAttributes.get(74));
		LinearRegression lr55 = new LinearRegression();
		Instances training_regr55 = null;
		modelajpgbag_05.setFilter(rm);
		modelajpgbag_05.setClassifier(lr55);
		try {
			modelajpgbag_05.buildClassifier(training_regr54);
			training_regr55 = DataHandler.getInstance().applyRegressionModel(
					training_regr54, fvAttributes, modelajpgbag_05);
		} catch (Exception e) {
			training_regr55 = training_regr54;
		}
		
		training_regr55.setClass(fvAttributes.get(75));
		LinearRegression lr56 = new LinearRegression();
		Instances training_regr56 = null;
		modelajpgbag_06.setFilter(rm);
		modelajpgbag_06.setClassifier(lr56);
		try {
			modelajpgbag_06.buildClassifier(training_regr55);
			training_regr56 = DataHandler.getInstance().applyRegressionModel(
					training_regr55, fvAttributes, modelajpgbag_06);
		} catch (Exception e) {
			training_regr56 = training_regr55;
		}
		
		training_regr56.setClass(fvAttributes.get(76));
		LinearRegression lr57 = new LinearRegression();
		Instances training_regr57 = null;
		modelajpgbag_07.setFilter(rm);
		modelajpgbag_07.setClassifier(lr57);
		try {
			modelajpgbag_07.buildClassifier(training_regr56);
			training_regr57 = DataHandler.getInstance().applyRegressionModel(
					training_regr56, fvAttributes, modelajpgbag_07);
		} catch (Exception e) {
			training_regr57 = training_regr56;
		}
		
		training_regr57.setClass(fvAttributes.get(77));
		LinearRegression lr58 = new LinearRegression();
		Instances training_regr58 = null;
		modelajpgbag_08.setFilter(rm);
		modelajpgbag_08.setClassifier(lr58);
		try {
			modelajpgbag_08.buildClassifier(training_regr57);
			training_regr58 = DataHandler.getInstance().applyRegressionModel(
					training_regr57, fvAttributes, modelajpgbag_08);
		} catch (Exception e) {
			training_regr58 = training_regr57;
		}
		
		training_regr58.setClass(fvAttributes.get(78));
		LinearRegression lr59 = new LinearRegression();
		Instances training_regr59 = null;
		modelajpgbag_09.setFilter(rm);
		modelajpgbag_09.setClassifier(lr59);
		try {
			modelajpgbag_09.buildClassifier(training_regr58);
			training_regr59 = DataHandler.getInstance().applyRegressionModel(
					training_regr58, fvAttributes, modelajpgbag_09);
		} catch (Exception e) {
			training_regr59 = training_regr58;
		}
		
		training_regr59.setClass(fvAttributes.get(79));
		LinearRegression lr60 = new LinearRegression();
		Instances training_regr60 = null;
		modelajpgbag_10.setFilter(rm);
		modelajpgbag_10.setClassifier(lr60);
		try {
			modelajpgbag_10.buildClassifier(training_regr59);
			training_regr60 = DataHandler.getInstance().applyRegressionModel(
					training_regr59, fvAttributes, modelajpgbag_10);
		} catch (Exception e) {
			training_regr60 = training_regr59;
		}
		
		training_regr60.setClass(fvAttributes.get(80));
		LinearRegression lr61 = new LinearRegression();
		Instances training_regr61 = null;
		modelajpgbag_11.setFilter(rm);
		modelajpgbag_11.setClassifier(lr61);
		try {
			modelajpgbag_11.buildClassifier(training_regr60);
			training_regr61 = DataHandler.getInstance().applyRegressionModel(
					training_regr60, fvAttributes, modelajpgbag_11);
		} catch (Exception e) {
			training_regr61 = training_regr60;
		}
		
		training_regr61.setClass(fvAttributes.get(81));
		LinearRegression lr62 = new LinearRegression();
		Instances training_regr62 = null;
		modelajpgbag_12.setFilter(rm);
		modelajpgbag_12.setClassifier(lr62);
		try {
			modelajpgbag_12.buildClassifier(training_regr61);
			training_regr62 = DataHandler.getInstance().applyRegressionModel(
					training_regr61, fvAttributes, modelajpgbag_12);
		} catch (Exception e) {
			training_regr62 = training_regr61;
		}
		
		training_regr62.setClass(fvAttributes.get(82));
		LinearRegression lr63 = new LinearRegression();
		Instances training_regr63 = null;
		modelajpgbag_13.setFilter(rm);
		modelajpgbag_13.setClassifier(lr63);
		try {
			modelajpgbag_13.buildClassifier(training_regr62);
			training_regr63 = DataHandler.getInstance().applyRegressionModel(
					training_regr62, fvAttributes, modelajpgbag_13);
		} catch (Exception e) {
			training_regr63 = training_regr62;
		}
		
		training_regr63.setClass(fvAttributes.get(83));
		LinearRegression lr64 = new LinearRegression();
		Instances training_regr64 = null;
		modelajpgbag_14.setFilter(rm);
		modelajpgbag_14.setClassifier(lr64);
		try {
			modelajpgbag_14.buildClassifier(training_regr63);
			training_regr64 = DataHandler.getInstance().applyRegressionModel(
					training_regr63, fvAttributes, modelajpgbag_14);
		} catch (Exception e) {
			training_regr64 = training_regr63;
		}
		
		training_regr64.setClass(fvAttributes.get(84));
		LinearRegression lr65 = new LinearRegression();
		Instances training_regr65 = null;
		modelajpgbag_15.setFilter(rm);
		modelajpgbag_15.setClassifier(lr65);
		try {
			modelajpgbag_15.buildClassifier(training_regr64);
			training_regr65 = DataHandler.getInstance().applyRegressionModel(
					training_regr64, fvAttributes, modelajpgbag_15);
		} catch (Exception e) {
			training_regr65 = training_regr64;
		}
		
		training_regr65.setClass(fvAttributes.get(85));
		LinearRegression lr66 = new LinearRegression();
		Instances training_regr66 = null;
		modelajpgbag_16.setFilter(rm);
		modelajpgbag_16.setClassifier(lr66);
		try {
			modelajpgbag_16.buildClassifier(training_regr65);
			training_regr66 = DataHandler.getInstance().applyRegressionModel(
					training_regr65, fvAttributes, modelajpgbag_16);
		} catch (Exception e) {
			training_regr66 = training_regr65;
		}
		
		training_regr66.setClass(fvAttributes.get(86));
		LinearRegression lr67 = new LinearRegression();
		Instances training_regr67 = null;
		modelnajpgbag_01.setFilter(rm);
		modelnajpgbag_01.setClassifier(lr67);
		try {
			modelnajpgbag_01.buildClassifier(training_regr66);
			training_regr67 = DataHandler.getInstance().applyRegressionModel(
					training_regr66, fvAttributes, modelnajpgbag_01);
		} catch (Exception e) {
			training_regr67 = training_regr66;
		}
		
		training_regr67.setClass(fvAttributes.get(87));
		LinearRegression lr68 = new LinearRegression();
		Instances training_regr68 = null;
		modelnajpgbag_02.setFilter(rm);
		modelnajpgbag_02.setClassifier(lr68);
		try {
			modelnajpgbag_02.buildClassifier(training_regr67);
			training_regr68 = DataHandler.getInstance().applyRegressionModel(
					training_regr67, fvAttributes, modelnajpgbag_02);
		} catch (Exception e) {
			training_regr68 = training_regr67;
		}
		
		training_regr68.setClass(fvAttributes.get(88));
		LinearRegression lr69 = new LinearRegression();
		Instances training_regr69 = null;
		modelnajpgbag_03.setFilter(rm);
		modelnajpgbag_03.setClassifier(lr69);
		try {
			modelnajpgbag_03.buildClassifier(training_regr68);
			training_regr69 = DataHandler.getInstance().applyRegressionModel(
					training_regr68, fvAttributes, modelnajpgbag_03);
		} catch (Exception e) {
			training_regr69 = training_regr68;
		}
		
		training_regr69.setClass(fvAttributes.get(89));
		LinearRegression lr70 = new LinearRegression();
		Instances training_regr70 = null;
		modelnajpgbag_04.setFilter(rm);
		modelnajpgbag_04.setClassifier(lr70);
		try {
			modelnajpgbag_04.buildClassifier(training_regr69);
			training_regr70 = DataHandler.getInstance().applyRegressionModel(
					training_regr69, fvAttributes, modelnajpgbag_04);
		} catch (Exception e) {
			training_regr70 = training_regr69;
		}
		
		training_regr70.setClass(fvAttributes.get(90));
		LinearRegression lr71 = new LinearRegression();
		Instances training_regr71 = null;
		modelnajpgbag_05.setFilter(rm);
		modelnajpgbag_05.setClassifier(lr71);
		try {
			modelnajpgbag_05.buildClassifier(training_regr70);
			training_regr71 = DataHandler.getInstance().applyRegressionModel(
					training_regr70, fvAttributes, modelnajpgbag_05);
		} catch (Exception e) {
			training_regr71 = training_regr70;
		}
		
		training_regr71.setClass(fvAttributes.get(91));
		LinearRegression lr72 = new LinearRegression();
		Instances training_regr72 = null;
		modelnajpgbag_06.setFilter(rm);
		modelnajpgbag_06.setClassifier(lr72);
		try {
			modelnajpgbag_06.buildClassifier(training_regr71);
			training_regr72 = DataHandler.getInstance().applyRegressionModel(
					training_regr71, fvAttributes, modelnajpgbag_06);
		} catch (Exception e) {
			training_regr72 = training_regr71;
		}
		
		training_regr72.setClass(fvAttributes.get(92));
		LinearRegression lr73 = new LinearRegression();
		Instances training_regr73 = null;
		modelnajpgbag_07.setFilter(rm);
		modelnajpgbag_07.setClassifier(lr73);
		try {
			modelnajpgbag_07.buildClassifier(training_regr72);
			training_regr73 = DataHandler.getInstance().applyRegressionModel(
					training_regr72, fvAttributes, modelnajpgbag_07);
		} catch (Exception e) {
			training_regr73 = training_regr72;
		}
		
		training_regr73.setClass(fvAttributes.get(93));
		LinearRegression lr74 = new LinearRegression();
		Instances training_regr74 = null;
		modelnajpgbag_08.setFilter(rm);
		modelnajpgbag_08.setClassifier(lr74);
		try {
			modelnajpgbag_08.buildClassifier(training_regr73);
			training_regr74 = DataHandler.getInstance().applyRegressionModel(
					training_regr73, fvAttributes, modelnajpgbag_08);
		} catch (Exception e) {
			training_regr74 = training_regr73;
		}
		
		training_regr74.setClass(fvAttributes.get(94));
		LinearRegression lr75 = new LinearRegression();
		Instances training_regr75 = null;
		modelnajpgbag_09.setFilter(rm);
		modelnajpgbag_09.setClassifier(lr75);
		try {
			modelnajpgbag_09.buildClassifier(training_regr74);
			training_regr75 = DataHandler.getInstance().applyRegressionModel(
					training_regr74, fvAttributes, modelnajpgbag_09);
		} catch (Exception e) {
			training_regr75 = training_regr74;
		}
		
		training_regr75.setClass(fvAttributes.get(95));
		LinearRegression lr76 = new LinearRegression();
		Instances training_regr76 = null;
		modelnajpgbag_10.setFilter(rm);
		modelnajpgbag_10.setClassifier(lr76);
		try {
			modelnajpgbag_10.buildClassifier(training_regr75);
			training_regr76 = DataHandler.getInstance().applyRegressionModel(
					training_regr75, fvAttributes, modelnajpgbag_10);
		} catch (Exception e) {
			training_regr76 = training_regr75;
		}
		
		training_regr76.setClass(fvAttributes.get(96));
		LinearRegression lr77 = new LinearRegression();
		Instances training_regr77 = null;
		modelnajpgbag_11.setFilter(rm);
		modelnajpgbag_11.setClassifier(lr77);
		try {
			modelnajpgbag_11.buildClassifier(training_regr76);
			training_regr77 = DataHandler.getInstance().applyRegressionModel(
					training_regr76, fvAttributes, modelnajpgbag_11);
		} catch (Exception e) {
			training_regr77 = training_regr76;
		}
		
		training_regr77.setClass(fvAttributes.get(97));
		LinearRegression lr78 = new LinearRegression();
		Instances training_regr78 = null;
		modelnajpgbag_12.setFilter(rm);
		modelnajpgbag_12.setClassifier(lr78);
		try {
			modelnajpgbag_12.buildClassifier(training_regr77);
			training_regr78 = DataHandler.getInstance().applyRegressionModel(
					training_regr77, fvAttributes, modelnajpgbag_12);
		} catch (Exception e) {
			training_regr78 = training_regr77;
		}
		
		training_regr78.setClass(fvAttributes.get(98));
		LinearRegression lr79 = new LinearRegression();
		Instances training_regr79 = null;
		modelnajpgbag_13.setFilter(rm);
		modelnajpgbag_13.setClassifier(lr79);
		try {
			modelnajpgbag_13.buildClassifier(training_regr78);
			training_regr79 = DataHandler.getInstance().applyRegressionModel(
					training_regr78, fvAttributes, modelnajpgbag_13);
		} catch (Exception e) {
			training_regr79 = training_regr78;
		}
		
		training_regr79.setClass(fvAttributes.get(99));
		LinearRegression lr80 = new LinearRegression();
		Instances training_regr80 = null;
		modelnajpgbag_14.setFilter(rm);
		modelnajpgbag_14.setClassifier(lr80);
		try {
			modelnajpgbag_14.buildClassifier(training_regr79);
			training_regr80 = DataHandler.getInstance().applyRegressionModel(
					training_regr79, fvAttributes, modelnajpgbag_14);
		} catch (Exception e) {
			training_regr80 = training_regr79;
		}
		
		training_regr80.setClass(fvAttributes.get(100));
		LinearRegression lr81 = new LinearRegression();
		Instances training_regr81 = null;
		modelnajpgbag_15.setFilter(rm);
		modelnajpgbag_15.setClassifier(lr81);
		try {
			modelnajpgbag_15.buildClassifier(training_regr80);
			training_regr81 = DataHandler.getInstance().applyRegressionModel(
					training_regr80, fvAttributes, modelnajpgbag_15);
		} catch (Exception e) {
			training_regr81 = training_regr80;
		}
		
		training_regr81.setClass(fvAttributes.get(101));
		LinearRegression lr82 = new LinearRegression();
		Instances training_regr82 = null;
		modelnajpgbag_16.setFilter(rm);
		modelnajpgbag_16.setClassifier(lr82);
		try {
			modelnajpgbag_16.buildClassifier(training_regr81);
			training_regr82 = DataHandler.getInstance().applyRegressionModel(
					training_regr81, fvAttributes, modelnajpgbag_16);
		} catch (Exception e) {
			training_regr82 = training_regr81;
		}
		
		System.out.println(training_regr82.get(0));
		
		// normalization part
		String[] options = { "-S", "1.0", "-T", "0.0" };
		normFilter.setOptions(options);
		normFilter.setInputFormat(training_regr82);

		
		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(
				training_regr82, fvAttributes.size() - 1, normFilter);
		
		for (int i=0; i<trainingSet_normed.size(); i++) {
			//System.out.println(trainingSet_normed.get(i));
			for (int j=0; j<trainingSet_normed.get(i).numAttributes();j++) {
				if (!trainingSet_normed.get(i).attribute(j).isNominal())
				trainingSet_normed.get(i).setValue(j, round(trainingSet_normed.get(i).value(j), 6) );
			}
			//System.out.println(trainingSet_normed.get(i));
		}
		
		trainingSet_normed = getTrimmedInstances(trainingSet_normed);
		
		return trainingSet_normed;

	}
	
	
	/**
	 * Applies linear regression to the missing values of the training set in
	 * order to predict their values and normalization to make the values fall
	 * into the range [0,1]. It is the case of the Item type Instances.
	 * 
	 * @param trainingSet
	 * @return Instances the transformed training set
	 * @throws Exception
	 */
	public Instances getTransformedTrainingBaselineEnriched(Instances trainingSet)
			throws Exception {

		initializeModels();

		Remove rm   = new Remove();	rm.setAttributeIndices("1");
		Remove rm2  = new Remove();	rm2.setAttributeIndices("1");
		Remove rm3  = new Remove();	rm3.setAttributeIndices("1");
		Remove rm4  = new Remove();	rm4.setAttributeIndices("1");
		Remove rm5  = new Remove();	rm5.setAttributeIndices("1");
		Remove rm6  = new Remove();	rm6.setAttributeIndices("1");
		Remove rm7  = new Remove();	rm7.setAttributeIndices("1");
		Remove rm8  = new Remove();	rm8.setAttributeIndices("1");
		Remove rm9  = new Remove();	rm9.setAttributeIndices("1");
		Remove rm10 = new Remove();	rm10.setAttributeIndices("1");
		Remove rm11 = new Remove();	rm11.setAttributeIndices("1");
		Remove rm12 = new Remove();	rm12.setAttributeIndices("1");
		Remove rm13 = new Remove();	rm13.setAttributeIndices("1");
		Remove rm14 = new Remove();	rm14.setAttributeIndices("1");
		Remove rm15 = new Remove();	rm15.setAttributeIndices("1");
		Remove rm16 = new Remove();	rm16.setAttributeIndices("1");
		Remove rm17 = new Remove();	rm17.setAttributeIndices("1");
		Remove rm18 = new Remove();	rm18.setAttributeIndices("1");
		Remove rm19 = new Remove();	rm19.setAttributeIndices("1");
		Remove rm20 = new Remove();	rm20.setAttributeIndices("1");
		Remove rm21 = new Remove();	rm21.setAttributeIndices("1");
		Remove rm22 = new Remove();	rm22.setAttributeIndices("1");
		Remove rm23 = new Remove();	rm23.setAttributeIndices("1");
		Remove rm24 = new Remove();	rm24.setAttributeIndices("1");
		Remove rm25 = new Remove();	rm25.setAttributeIndices("1");
		Remove rm26 = new Remove();	rm26.setAttributeIndices("1");
		Remove rm27 = new Remove();	rm27.setAttributeIndices("1");
		Remove rm28 = new Remove();	rm28.setAttributeIndices("1");
		Remove rm29 = new Remove();	rm29.setAttributeIndices("1");
		Remove rm30 = new Remove();	rm30.setAttributeIndices("1");
		Remove rm31 = new Remove();	rm31.setAttributeIndices("1");
		Remove rm32 = new Remove();	rm32.setAttributeIndices("1");
		Remove rm33 = new Remove();	rm33.setAttributeIndices("1");
		Remove rm34 = new Remove();	rm34.setAttributeIndices("1");
		Remove rm35 = new Remove();	rm35.setAttributeIndices("1");
		Remove rm36 = new Remove();	rm36.setAttributeIndices("1");
		Remove rm37 = new Remove();	rm37.setAttributeIndices("1");
		Remove rm38 = new Remove();	rm38.setAttributeIndices("1");
		Remove rm39 = new Remove();	rm39.setAttributeIndices("1");
		Remove rm40 = new Remove();	rm40.setAttributeIndices("1");
		Remove rm41 = new Remove();	rm41.setAttributeIndices("1");
		Remove rm42 = new Remove();	rm42.setAttributeIndices("1");
		Remove rm43 = new Remove();	rm43.setAttributeIndices("1");
		Remove rm44 = new Remove();	rm44.setAttributeIndices("1");
		Remove rm45 = new Remove();	rm45.setAttributeIndices("1");
		Remove rm46 = new Remove();	rm46.setAttributeIndices("1");
		Remove rm47 = new Remove();	rm47.setAttributeIndices("1");
		Remove rm48 = new Remove();	rm48.setAttributeIndices("1");
		Remove rm49 = new Remove();	rm49.setAttributeIndices("1");
		Remove rm50 = new Remove();	rm50.setAttributeIndices("1");
		Remove rm51 = new Remove();	rm51.setAttributeIndices("1");
		Remove rm52 = new Remove();	rm52.setAttributeIndices("1");
		Remove rm53 = new Remove();	rm53.setAttributeIndices("1");
		Remove rm54 = new Remove();	rm54.setAttributeIndices("1");
		Remove rm55 = new Remove();	rm55.setAttributeIndices("1");
		Remove rm56 = new Remove();	rm56.setAttributeIndices("1");
		Remove rm57 = new Remove();	rm57.setAttributeIndices("1");
		Remove rm58 = new Remove();	rm58.setAttributeIndices("1");
		Remove rm59 = new Remove();	rm59.setAttributeIndices("1");
		Remove rm60 = new Remove();	rm60.setAttributeIndices("1");
		Remove rm61 = new Remove();	rm61.setAttributeIndices("1");
		Remove rm62 = new Remove();	rm62.setAttributeIndices("1");
		Remove rm63 = new Remove();	rm63.setAttributeIndices("1");
		Remove rm64 = new Remove();	rm64.setAttributeIndices("1");
		Remove rm65 = new Remove();	rm65.setAttributeIndices("1");
		Remove rm66 = new Remove();	rm66.setAttributeIndices("1");
		Remove rm67 = new Remove();	rm67.setAttributeIndices("1");
		Remove rm68 = new Remove();	rm68.setAttributeIndices("1");
		Remove rm69 = new Remove();	rm69.setAttributeIndices("1");
		Remove rm70 = new Remove();	rm70.setAttributeIndices("1");
		Remove rm71 = new Remove();	rm71.setAttributeIndices("1");
		Remove rm72 = new Remove();	rm72.setAttributeIndices("1");
		Remove rm73 = new Remove();	rm73.setAttributeIndices("1");
		Remove rm74 = new Remove();	rm74.setAttributeIndices("1");
		Remove rm75 = new Remove();	rm75.setAttributeIndices("1");

		
		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();
		
		
		// REGRESSION
		
		// getNumNegSentiWords
		trainingSet.setClass(fvAttributes.get(12));
		LinearRegression lr = new LinearRegression();
		Instances training_regr = null;
		model.setFilter(rm);
		model.setClassifier(lr);

		try {
			model.buildClassifier(trainingSet);
			training_regr = DataHandler.getInstance().applyRegressionModel(
					trainingSet, fvAttributes, model);
		} catch (Exception e) {
			training_regr = trainingSet;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// getNumPosSentiWords
		training_regr.setClass(fvAttributes.get(11));
		LinearRegression lr2 = new LinearRegression();
		Instances training_regr2 = null;
		model2.setFilter(rm2);
		model2.setClassifier(lr2);
		try {
			model2.buildClassifier(training_regr);
			training_regr2 = DataHandler.getInstance().applyRegressionModel(
					training_regr, fvAttributes, model2);
		} catch (Exception e) {
			training_regr2 = training_regr;
		}
		
		//first pron
		training_regr2.setClass(fvAttributes.get(7));
		LinearRegression lr3 = new LinearRegression();
		Instances training_regr3 = null;

		model3.setFilter(rm3);
		model3.setClassifier(lr3);
		try {
			model3.buildClassifier(training_regr2);
			training_regr3 = DataHandler.getInstance().applyRegressionModel(
					training_regr2, fvAttributes, model3);
		} catch (Exception e) {
			training_regr3 = training_regr2;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		//second pron
		training_regr3.setClass(fvAttributes.get(8));
		LinearRegression lr4 = new LinearRegression();
		Instances training_regr4 = null;

		model4.setFilter(rm4);
		model4.setClassifier(lr4);
		try {
			model4.buildClassifier(training_regr3);
			training_regr4 = DataHandler.getInstance().applyRegressionModel(
					training_regr3, fvAttributes, model4);
		} catch (Exception e) {
			training_regr4 = training_regr3;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		//third pron
		training_regr4.setClass(fvAttributes.get(9));
		LinearRegression lr5 = new LinearRegression();
		Instances training_regr5 = null;

		model5.setFilter(rm5);
		model5.setClassifier(lr5);
		try {
			model5.buildClassifier(training_regr4);
			training_regr5 = DataHandler.getInstance().applyRegressionModel(
					training_regr4, fvAttributes, model5);
		} catch (Exception e) {
			training_regr5 = training_regr4;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		//sad 
		training_regr5.setClass(fvAttributes.get(41));
		LinearRegression lr13 = new LinearRegression();
		Instances training_regr13 = null;
		modelsad_01.setFilter(rm6);
		modelsad_01.setClassifier(lr13);
		try {
			modelsad_01.buildClassifier(training_regr5);
			training_regr13 = DataHandler.getInstance().applyRegressionModel(
					training_regr5, fvAttributes, modelsad_01);
		} catch (Exception e) {
			training_regr13 = training_regr5;
		}
		
		training_regr13.setClass(fvAttributes.get(42));
		LinearRegression lr14 = new LinearRegression();
		Instances training_regr14 = null;
		modelsad_02.setFilter(rm7);
		modelsad_02.setClassifier(lr14);
		try {
			modelsad_02.buildClassifier(training_regr13);
			training_regr14 = DataHandler.getInstance().applyRegressionModel(
					training_regr13, fvAttributes, modelsad_02);
		} catch (Exception e) {
			training_regr14 = training_regr13;
		}
		
		training_regr14.setClass(fvAttributes.get(43));
		LinearRegression lr15 = new LinearRegression();
		Instances training_regr15 = null;
		modelsad_03.setFilter(rm8);
		modelsad_03.setClassifier(lr15);
		try {
			modelsad_03.buildClassifier(training_regr14);
			training_regr15 = DataHandler.getInstance().applyRegressionModel(
					training_regr14, fvAttributes, modelsad_03);
		} catch (Exception e) {
			training_regr15 = training_regr14;
		}
		
		training_regr15.setClass(fvAttributes.get(44));
		LinearRegression lr16 = new LinearRegression();
		Instances training_regr16 = null;
		modelsad_04.setFilter(rm9);
		modelsad_04.setClassifier(lr16);
		try {
			modelsad_04.buildClassifier(training_regr15);
			training_regr16 = DataHandler.getInstance().applyRegressionModel(
					training_regr15, fvAttributes, modelsad_04);
		} catch (Exception e) {
			training_regr16 = training_regr15;
		}
		
		training_regr16.setClass(fvAttributes.get(45));
		LinearRegression lr17 = new LinearRegression();
		Instances training_regr17 = null;
		modelsad_05.setFilter(rm10);
		modelsad_05.setClassifier(lr17);
		try {
			modelsad_05.buildClassifier(training_regr16);
			training_regr17 = DataHandler.getInstance().applyRegressionModel(
					training_regr16, fvAttributes, modelsad_05);
		} catch (Exception e) {
			training_regr17 = training_regr16;
		}
		
		training_regr17.setClass(fvAttributes.get(46));
		LinearRegression lr18 = new LinearRegression();
		Instances training_regr18 = null;
		modelsad_06.setFilter(rm11);
		modelsad_06.setClassifier(lr18);
		try {
			modelsad_06.buildClassifier(training_regr17);
			training_regr18 = DataHandler.getInstance().applyRegressionModel(
					training_regr17, fvAttributes, modelsad_06);
		} catch (Exception e) {
			training_regr18 = training_regr17;
		}
		
		training_regr18.setClass(fvAttributes.get(47));
		LinearRegression lr19 = new LinearRegression();
		Instances training_regr19 = null;
		modelsad_07.setFilter(rm12);
		modelsad_07.setClassifier(lr19);
		try {
			modelsad_07.buildClassifier(training_regr18);
			training_regr19 = DataHandler.getInstance().applyRegressionModel(
					training_regr18, fvAttributes, modelsad_07);
		} catch (Exception e) {
			training_regr19 = training_regr18;
		}
		
		training_regr19.setClass(fvAttributes.get(48));
		LinearRegression lr20 = new LinearRegression();
		Instances training_regr20 = null;
		modelsad_08.setFilter(rm13);
		modelsad_08.setClassifier(lr20);
		try {
			modelsad_08.buildClassifier(training_regr19);
			training_regr20 = DataHandler.getInstance().applyRegressionModel(
					training_regr19, fvAttributes, modelsad_08);
		} catch (Exception e) {
			training_regr20 = training_regr19;
		}
		
		training_regr20.setClass(fvAttributes.get(49));
		LinearRegression lr21 = new LinearRegression();
		Instances training_regr21 = null;
		modelsnad_01.setFilter(rm14);
		modelsnad_01.setClassifier(lr21);
		try {
			modelsnad_01.buildClassifier(training_regr20);
			training_regr21 = DataHandler.getInstance().applyRegressionModel(
					training_regr20, fvAttributes, modelsnad_01);
		} catch (Exception e) {
			training_regr21 = training_regr20;
		}
		
		training_regr21.setClass(fvAttributes.get(50));
		LinearRegression lr22 = new LinearRegression();
		Instances training_regr22 = null;
		modelsnad_02.setFilter(rm15);
		modelsnad_02.setClassifier(lr22);
		try {
			modelsnad_02.buildClassifier(training_regr21);
			training_regr22 = DataHandler.getInstance().applyRegressionModel(
					training_regr21, fvAttributes, modelsnad_02);
		} catch (Exception e) {
			training_regr22 = training_regr21;
		}
		
		training_regr22.setClass(fvAttributes.get(51));
		LinearRegression lr23 = new LinearRegression();
		Instances training_regr23 = null;
		modelsnad_03.setFilter(rm16);
		modelsnad_03.setClassifier(lr23);
		try {
			modelsnad_03.buildClassifier(training_regr22);
			training_regr23 = DataHandler.getInstance().applyRegressionModel(
					training_regr22, fvAttributes, modelsnad_03);
		} catch (Exception e) {
			training_regr23 = training_regr22;
		}
		
		training_regr23.setClass(fvAttributes.get(52));
		LinearRegression lr24 = new LinearRegression();
		Instances training_regr24 = null;
		modelsnad_04.setFilter(rm17);
		modelsnad_04.setClassifier(lr24);
		try {
			modelsnad_04.buildClassifier(training_regr23);
			training_regr24 = DataHandler.getInstance().applyRegressionModel(
					training_regr23, fvAttributes, modelsnad_04);
		} catch (Exception e) {
			training_regr24 = training_regr23;
		}
		
		training_regr24.setClass(fvAttributes.get(53));
		LinearRegression lr25 = new LinearRegression();
		Instances training_regr25 = null;
		modelsnad_05.setFilter(rm18);
		modelsnad_05.setClassifier(lr25);
		try {
			modelsnad_05.buildClassifier(training_regr24);
			training_regr25 = DataHandler.getInstance().applyRegressionModel(
					training_regr24, fvAttributes, modelsnad_05);
		} catch (Exception e) {
			training_regr25 = training_regr24;
		}
		
		training_regr25.setClass(fvAttributes.get(54));
		LinearRegression lr26 = new LinearRegression();
		Instances training_regr26 = null;
		modelsnad_06.setFilter(rm19);
		modelsnad_06.setClassifier(lr26);
		try {
			modelsnad_06.buildClassifier(training_regr25);
			training_regr26 = DataHandler.getInstance().applyRegressionModel(
					training_regr25, fvAttributes, modelsnad_06);
		} catch (Exception e) {
			training_regr26 = training_regr25;
		}
		
		training_regr26.setClass(fvAttributes.get(55));
		LinearRegression lr27 = new LinearRegression();
		Instances training_regr27 = null;
		modelsnad_07.setFilter(rm20);
		modelsnad_07.setClassifier(lr27);
		try {
			modelsnad_07.buildClassifier(training_regr26);
			training_regr27 = DataHandler.getInstance().applyRegressionModel(
					training_regr26, fvAttributes, modelsnad_07);
		} catch (Exception e) {
			training_regr27 = training_regr26;
		}
		
		training_regr27.setClass(fvAttributes.get(56));
		LinearRegression lr28 = new LinearRegression();
		Instances training_regr28 = null;
		modelsnad_08.setFilter(rm21);
		modelsnad_08.setClassifier(lr28);
		try {
			modelsnad_08.buildClassifier(training_regr27);
			training_regr28 = DataHandler.getInstance().applyRegressionModel(
					training_regr27, fvAttributes, modelsnad_08);
		} catch (Exception e) {
			training_regr28 = training_regr27;
		}
		
		training_regr28.setClass(fvAttributes.get(19));
		LinearRegression lr29 = new LinearRegression();
		Instances training_regr29 = null;
		modelbf_01.setFilter(rm22);
		modelbf_01.setClassifier(lr29);
		try {
			modelbf_01.buildClassifier(training_regr28);
			training_regr29 = DataHandler.getInstance().applyRegressionModel(
					training_regr28, fvAttributes, modelbf_01);
		} catch (Exception e) {
			training_regr29 = training_regr28;
		}
		
		training_regr29.setClass(fvAttributes.get(20));
		LinearRegression lr30 = new LinearRegression();
		Instances training_regr30 = null;
		modelbf_02.setFilter(rm23);
		modelbf_02.setClassifier(lr30);
		try {
			modelbf_02.buildClassifier(training_regr29);
			training_regr30 = DataHandler.getInstance().applyRegressionModel(
					training_regr29, fvAttributes, modelbf_02);
		} catch (Exception e) {
			training_regr30 = training_regr29;
		}
		
		training_regr30.setClass(fvAttributes.get(21));
		LinearRegression lr31 = new LinearRegression();
		Instances training_regr31 = null;
		modelbf_03.setFilter(rm24);
		modelbf_03.setClassifier(lr31);
		try {
			modelbf_03.buildClassifier(training_regr30);
			training_regr31 = DataHandler.getInstance().applyRegressionModel(
					training_regr30, fvAttributes, modelbf_03);
		} catch (Exception e) {
			training_regr31 = training_regr30;
		}
		
		training_regr31.setClass(fvAttributes.get(22));
		LinearRegression lr32 = new LinearRegression();
		Instances training_regr32 = null;
		modelbf_04.setFilter(rm25);
		modelbf_04.setClassifier(lr32);
		try {
			modelbf_04.buildClassifier(training_regr31);
			training_regr32 = DataHandler.getInstance().applyRegressionModel(
					training_regr31, fvAttributes, modelbf_04);
		} catch (Exception e) {
			training_regr32 = training_regr31;
		}
		
		training_regr32.setClass(fvAttributes.get(23));
		LinearRegression lr33 = new LinearRegression();
		Instances training_regr33 = null;
		modelbf_05.setFilter(rm26);
		modelbf_05.setClassifier(lr33);
		try {
			modelbf_05.buildClassifier(training_regr32);
			training_regr33 = DataHandler.getInstance().applyRegressionModel(
					training_regr32, fvAttributes, modelbf_05);
		} catch (Exception e) {
			training_regr33 = training_regr32;
		}
		
		training_regr33.setClass(fvAttributes.get(24));
		LinearRegression lr34 = new LinearRegression();
		Instances training_regr34 = null;
		modelbf_06.setFilter(rm27);
		modelbf_06.setClassifier(lr34);
		try {
			modelbf_06.buildClassifier(training_regr33);
			training_regr34 = DataHandler.getInstance().applyRegressionModel(
					training_regr33, fvAttributes, modelbf_06);
		} catch (Exception e) {
			training_regr34 = training_regr33;
		}
		
		training_regr34.setClass(fvAttributes.get(25));
		LinearRegression lr35 = new LinearRegression();
		Instances training_regr35 = null;
		modelbf_07.setFilter(rm28);
		modelbf_07.setClassifier(lr35);
		try {
			modelbf_07.buildClassifier(training_regr34);
			training_regr35 = DataHandler.getInstance().applyRegressionModel(
					training_regr34, fvAttributes, modelbf_07);
		} catch (Exception e) {
			training_regr35 = training_regr34;
		}
		
		training_regr35.setClass(fvAttributes.get(26));
		LinearRegression lr36 = new LinearRegression();
		Instances training_regr36 = null;
		modelbf_08.setFilter(rm29);
		modelbf_08.setClassifier(lr36);
		try {
			modelbf_08.buildClassifier(training_regr35);
			training_regr36 = DataHandler.getInstance().applyRegressionModel(
					training_regr35, fvAttributes, modelbf_08);
		} catch (Exception e) {
			training_regr36 = training_regr35;
		}
		
		training_regr36.setClass(fvAttributes.get(27));
		LinearRegression lr37 = new LinearRegression();
		Instances training_regr37 = null;
		modelbf_09.setFilter(rm30);
		modelbf_09.setClassifier(lr37);
		try {
			modelbf_09.buildClassifier(training_regr36);
			training_regr37 = DataHandler.getInstance().applyRegressionModel(
					training_regr36, fvAttributes, modelbf_09);
		} catch (Exception e) {
			training_regr37 = training_regr36;
		}
		
		training_regr37.setClass(fvAttributes.get(28));
		LinearRegression lr38 = new LinearRegression();
		Instances training_regr38 = null;
		modelbf_10.setFilter(rm31);
		modelbf_10.setClassifier(lr38);
		try {
			modelbf_10.buildClassifier(training_regr37);
			training_regr38 = DataHandler.getInstance().applyRegressionModel(
					training_regr37, fvAttributes, modelbf_10);
		} catch (Exception e) {
			training_regr38 = training_regr37;
		}
		
		training_regr38.setClass(fvAttributes.get(29));
		LinearRegression lr39 = new LinearRegression();
		Instances training_regr39 = null;
		modelbf_11.setFilter(rm32);
		modelbf_11.setClassifier(lr39);
		try {
			modelbf_11.buildClassifier(training_regr38);
			training_regr39 = DataHandler.getInstance().applyRegressionModel(
					training_regr38, fvAttributes, modelbf_11);
		} catch (Exception e) {
			training_regr39 = training_regr38;
		}
		
		training_regr39.setClass(fvAttributes.get(30));
		LinearRegression lr40 = new LinearRegression();
		Instances training_regr40 = null;
		modelbf_12.setFilter(rm33);
		modelbf_12.setClassifier(lr40);
		try {
			modelbf_12.buildClassifier(training_regr39);
			training_regr40 = DataHandler.getInstance().applyRegressionModel(
					training_regr39, fvAttributes, modelbf_12);
		} catch (Exception e) {
			training_regr40 = training_regr39;
		}
		
		training_regr40.setClass(fvAttributes.get(31));
		LinearRegression lr41 = new LinearRegression();
		Instances training_regr41 = null;
		modelbf_13.setFilter(rm34);
		modelbf_13.setClassifier(lr41);
		try {
			modelbf_13.buildClassifier(training_regr40);
			training_regr41 = DataHandler.getInstance().applyRegressionModel(
					training_regr40, fvAttributes, modelbf_13);
		} catch (Exception e) {
			training_regr41 = training_regr40;
		}
		
		training_regr41.setClass(fvAttributes.get(32));
		LinearRegression lr42 = new LinearRegression();
		Instances training_regr42 = null;
		modelbf_14.setFilter(rm35);
		modelbf_14.setClassifier(lr42);
		try {
			modelbf_14.buildClassifier(training_regr41);
			training_regr42 = DataHandler.getInstance().applyRegressionModel(
					training_regr41, fvAttributes, modelbf_14);
		} catch (Exception e) {
			training_regr42 = training_regr41;
		}
		
		training_regr42.setClass(fvAttributes.get(33));
		LinearRegression lr43 = new LinearRegression();
		Instances training_regr43 = null;
		modelbf_15.setFilter(rm36);
		modelbf_15.setClassifier(lr43);
		try {
			modelbf_15.buildClassifier(training_regr42);
			training_regr43 = DataHandler.getInstance().applyRegressionModel(
					training_regr42, fvAttributes, modelbf_15);
		} catch (Exception e) {
			training_regr43 = training_regr42;
		}
		
		training_regr43.setClass(fvAttributes.get(34));
		LinearRegression lr44 = new LinearRegression();
		Instances training_regr44 = null;
		modelbf_16.setFilter(rm37);
		modelbf_16.setClassifier(lr44);
		try {
			modelbf_16.buildClassifier(training_regr43);
			training_regr44 = DataHandler.getInstance().applyRegressionModel(
					training_regr43, fvAttributes, modelbf_16);
		} catch (Exception e) {
			training_regr44 = training_regr43;
		}
		
		training_regr44.setClass(fvAttributes.get(35));
		LinearRegression lr45 = new LinearRegression();
		Instances training_regr45 = null;
		modelbf_17.setFilter(rm38);
		modelbf_17.setClassifier(lr45);
		try {
			modelbf_17.buildClassifier(training_regr44);
			training_regr45 = DataHandler.getInstance().applyRegressionModel(
					training_regr44, fvAttributes, modelbf_17);
		} catch (Exception e) {
			training_regr45 = training_regr44;
		}
		
		training_regr45.setClass(fvAttributes.get(36));
		LinearRegression lr46 = new LinearRegression();
		Instances training_regr46 = null;
		modelbf_18.setFilter(rm39);
		modelbf_18.setClassifier(lr46);
		try {
			modelbf_18.buildClassifier(training_regr45);
			training_regr46 = DataHandler.getInstance().applyRegressionModel(
					training_regr45, fvAttributes, modelbf_18);
		} catch (Exception e) {
			training_regr46 = training_regr45;
		}
		
		training_regr46.setClass(fvAttributes.get(37));
		LinearRegression lr47 = new LinearRegression();
		Instances training_regr47 = null;
		modelbf_19.setFilter(rm40);
		modelbf_19.setClassifier(lr47);
		try {
			modelbf_19.buildClassifier(training_regr46);
			training_regr47 = DataHandler.getInstance().applyRegressionModel(
					training_regr46, fvAttributes, modelbf_19);
		} catch (Exception e) {
			training_regr47 = training_regr46;
		}
		
		training_regr47.setClass(fvAttributes.get(38));
		LinearRegression lr48 = new LinearRegression();
		Instances training_regr48 = null;
		modelbf_20.setFilter(rm41);
		modelbf_20.setClassifier(lr48);
		try {
			modelbf_20.buildClassifier(training_regr47);
			training_regr48 = DataHandler.getInstance().applyRegressionModel(
					training_regr47, fvAttributes, modelbf_20);
		} catch (Exception e) {
			training_regr48 = training_regr47;
		}
		
		training_regr48.setClass(fvAttributes.get(39));
		LinearRegression lr49 = new LinearRegression();
		Instances training_regr49 = null;
		modelbf_21.setFilter(rm42);
		modelbf_21.setClassifier(lr49);
		try {
			modelbf_21.buildClassifier(training_regr48);
			training_regr49 = DataHandler.getInstance().applyRegressionModel(
					training_regr48, fvAttributes, modelbf_21);
		} catch (Exception e) {
			training_regr49 = training_regr48;
		}
		
		training_regr49.setClass(fvAttributes.get(40));
		LinearRegression lr50 = new LinearRegression();
		Instances training_regr50 = null;
		modelbf_22.setFilter(rm43);
		modelbf_22.setClassifier(lr50);
		try {
			modelbf_22.buildClassifier(training_regr49);
			training_regr50 = DataHandler.getInstance().applyRegressionModel(
					training_regr49, fvAttributes, modelbf_22);
		} catch (Exception e) {
			training_regr50 = training_regr49;
		}
		
		training_regr50.setClass(fvAttributes.get(57));
		LinearRegression lr51 = new LinearRegression();
		Instances training_regr51 = null;
		modelajpgbag_01.setFilter(rm44);
		modelajpgbag_01.setClassifier(lr51);
		try {
			modelajpgbag_01.buildClassifier(training_regr50);
			training_regr51 = DataHandler.getInstance().applyRegressionModel(
					training_regr50, fvAttributes, modelajpgbag_01);
		} catch (Exception e) {
			training_regr51 = training_regr50;
		}
		
		training_regr51.setClass(fvAttributes.get(58));
		LinearRegression lr52 = new LinearRegression();
		Instances training_regr52 = null;
		modelajpgbag_02.setFilter(rm45);
		modelajpgbag_02.setClassifier(lr52);
		try {
			modelajpgbag_02.buildClassifier(training_regr51);
			training_regr52 = DataHandler.getInstance().applyRegressionModel(
					training_regr51, fvAttributes, modelajpgbag_02);
		} catch (Exception e) {
			training_regr52 = training_regr51;
		}
		
		training_regr52.setClass(fvAttributes.get(59));
		LinearRegression lr53 = new LinearRegression();
		Instances training_regr53 = null;
		modelajpgbag_03.setFilter(rm46);
		modelajpgbag_03.setClassifier(lr53);
		try {
			modelajpgbag_03.buildClassifier(training_regr52);
			training_regr53 = DataHandler.getInstance().applyRegressionModel(
					training_regr52, fvAttributes, modelajpgbag_03);
		} catch (Exception e) {
			training_regr53 = training_regr52;
		}
		
		training_regr53.setClass(fvAttributes.get(60));
		LinearRegression lr54 = new LinearRegression();
		Instances training_regr54 = null;
		modelajpgbag_04.setFilter(rm47);
		modelajpgbag_04.setClassifier(lr54);
		try {
			modelajpgbag_04.buildClassifier(training_regr53);
			training_regr54 = DataHandler.getInstance().applyRegressionModel(
					training_regr53, fvAttributes, modelajpgbag_04);
		} catch (Exception e) {
			training_regr54 = training_regr53;
		}
		
		training_regr54.setClass(fvAttributes.get(61));
		LinearRegression lr55 = new LinearRegression();
		Instances training_regr55 = null;
		modelajpgbag_05.setFilter(rm48);
		modelajpgbag_05.setClassifier(lr55);
		try {
			modelajpgbag_05.buildClassifier(training_regr54);
			training_regr55 = DataHandler.getInstance().applyRegressionModel(
					training_regr54, fvAttributes, modelajpgbag_05);
		} catch (Exception e) {
			training_regr55 = training_regr54;
		}
		
		training_regr55.setClass(fvAttributes.get(62));
		LinearRegression lr56 = new LinearRegression();
		Instances training_regr56 = null;
		modelajpgbag_06.setFilter(rm49);
		modelajpgbag_06.setClassifier(lr56);
		try {
			modelajpgbag_06.buildClassifier(training_regr55);
			training_regr56 = DataHandler.getInstance().applyRegressionModel(
					training_regr55, fvAttributes, modelajpgbag_06);
		} catch (Exception e) {
			training_regr56 = training_regr55;
		}
		
		training_regr56.setClass(fvAttributes.get(63));
		LinearRegression lr57 = new LinearRegression();
		Instances training_regr57 = null;
		modelajpgbag_07.setFilter(rm50);
		modelajpgbag_07.setClassifier(lr57);
		try {
			modelajpgbag_07.buildClassifier(training_regr56);
			training_regr57 = DataHandler.getInstance().applyRegressionModel(
					training_regr56, fvAttributes, modelajpgbag_07);
		} catch (Exception e) {
			training_regr57 = training_regr56;
		}
		
		training_regr57.setClass(fvAttributes.get(64));
		LinearRegression lr58 = new LinearRegression();
		Instances training_regr58 = null;
		modelajpgbag_08.setFilter(rm51);
		modelajpgbag_08.setClassifier(lr58);
		try {
			modelajpgbag_08.buildClassifier(training_regr57);
			training_regr58 = DataHandler.getInstance().applyRegressionModel(
					training_regr57, fvAttributes, modelajpgbag_08);
		} catch (Exception e) {
			training_regr58 = training_regr57;
		}
		
		training_regr58.setClass(fvAttributes.get(65));
		LinearRegression lr59 = new LinearRegression();
		Instances training_regr59 = null;
		modelajpgbag_09.setFilter(rm52);
		modelajpgbag_09.setClassifier(lr59);
		try {
			modelajpgbag_09.buildClassifier(training_regr58);
			training_regr59 = DataHandler.getInstance().applyRegressionModel(
					training_regr58, fvAttributes, modelajpgbag_09);
		} catch (Exception e) {
			training_regr59 = training_regr58;
		}
		
		training_regr59.setClass(fvAttributes.get(66));
		LinearRegression lr60 = new LinearRegression();
		Instances training_regr60 = null;
		modelajpgbag_10.setFilter(rm53);
		modelajpgbag_10.setClassifier(lr60);
		try {
			modelajpgbag_10.buildClassifier(training_regr59);
			training_regr60 = DataHandler.getInstance().applyRegressionModel(
					training_regr59, fvAttributes, modelajpgbag_10);
		} catch (Exception e) {
			training_regr60 = training_regr59;
		}
		
		training_regr60.setClass(fvAttributes.get(67));
		LinearRegression lr61 = new LinearRegression();
		Instances training_regr61 = null;
		modelajpgbag_11.setFilter(rm54);
		modelajpgbag_11.setClassifier(lr61);
		try {
			modelajpgbag_11.buildClassifier(training_regr60);
			training_regr61 = DataHandler.getInstance().applyRegressionModel(
					training_regr60, fvAttributes, modelajpgbag_11);
		} catch (Exception e) {
			training_regr61 = training_regr60;
		}
		
		training_regr61.setClass(fvAttributes.get(68));
		LinearRegression lr62 = new LinearRegression();
		Instances training_regr62 = null;
		modelajpgbag_12.setFilter(rm55);
		modelajpgbag_12.setClassifier(lr62);
		try {
			modelajpgbag_12.buildClassifier(training_regr61);
			training_regr62 = DataHandler.getInstance().applyRegressionModel(
					training_regr61, fvAttributes, modelajpgbag_12);
		} catch (Exception e) {
			training_regr62 = training_regr61;
		}
		
		training_regr62.setClass(fvAttributes.get(69));
		LinearRegression lr63 = new LinearRegression();
		Instances training_regr63 = null;
		modelajpgbag_13.setFilter(rm56);
		modelajpgbag_13.setClassifier(lr63);
		try {
			modelajpgbag_13.buildClassifier(training_regr62);
			training_regr63 = DataHandler.getInstance().applyRegressionModel(
					training_regr62, fvAttributes, modelajpgbag_13);
		} catch (Exception e) {
			training_regr63 = training_regr62;
		}
		
		training_regr63.setClass(fvAttributes.get(70));
		LinearRegression lr64 = new LinearRegression();
		Instances training_regr64 = null;
		modelajpgbag_14.setFilter(rm57);
		modelajpgbag_14.setClassifier(lr64);
		try {
			modelajpgbag_14.buildClassifier(training_regr63);
			training_regr64 = DataHandler.getInstance().applyRegressionModel(
					training_regr63, fvAttributes, modelajpgbag_14);
		} catch (Exception e) {
			training_regr64 = training_regr63;
		}
		
		training_regr64.setClass(fvAttributes.get(71));
		LinearRegression lr65 = new LinearRegression();
		Instances training_regr65 = null;
		modelajpgbag_15.setFilter(rm58);
		modelajpgbag_15.setClassifier(lr65);
		try {
			modelajpgbag_15.buildClassifier(training_regr64);
			training_regr65 = DataHandler.getInstance().applyRegressionModel(
					training_regr64, fvAttributes, modelajpgbag_15);
		} catch (Exception e) {
			training_regr65 = training_regr64;
		}
		
		training_regr65.setClass(fvAttributes.get(72));
		LinearRegression lr66 = new LinearRegression();
		Instances training_regr66 = null;
		modelajpgbag_16.setFilter(rm59);
		modelajpgbag_16.setClassifier(lr66);
		try {
			modelajpgbag_16.buildClassifier(training_regr65);
			training_regr66 = DataHandler.getInstance().applyRegressionModel(
					training_regr65, fvAttributes, modelajpgbag_16);
		} catch (Exception e) {
			training_regr66 = training_regr65;
		}
		
		training_regr66.setClass(fvAttributes.get(73));
		LinearRegression lr67 = new LinearRegression();
		Instances training_regr67 = null;
		modelnajpgbag_01.setFilter(rm60);
		modelnajpgbag_01.setClassifier(lr67);
		try {
			modelnajpgbag_01.buildClassifier(training_regr66);
			training_regr67 = DataHandler.getInstance().applyRegressionModel(
					training_regr66, fvAttributes, modelnajpgbag_01);
		} catch (Exception e) {
			training_regr67 = training_regr66;
		}
		
		training_regr67.setClass(fvAttributes.get(74));
		LinearRegression lr68 = new LinearRegression();
		Instances training_regr68 = null;
		modelnajpgbag_02.setFilter(rm61);
		modelnajpgbag_02.setClassifier(lr68);
		try {
			modelnajpgbag_02.buildClassifier(training_regr67);
			training_regr68 = DataHandler.getInstance().applyRegressionModel(
					training_regr67, fvAttributes, modelnajpgbag_02);
		} catch (Exception e) {
			training_regr68 = training_regr67;
		}
		
		training_regr68.setClass(fvAttributes.get(75));
		LinearRegression lr69 = new LinearRegression();
		Instances training_regr69 = null;
		modelnajpgbag_03.setFilter(rm62);
		modelnajpgbag_03.setClassifier(lr69);
		try {
			modelnajpgbag_03.buildClassifier(training_regr68);
			training_regr69 = DataHandler.getInstance().applyRegressionModel(
					training_regr68, fvAttributes, modelnajpgbag_03);
		} catch (Exception e) {
			training_regr69 = training_regr68;
		}
		
		training_regr69.setClass(fvAttributes.get(76));
		LinearRegression lr70 = new LinearRegression();
		Instances training_regr70 = null;
		modelnajpgbag_04.setFilter(rm63);
		modelnajpgbag_04.setClassifier(lr70);
		try {
			modelnajpgbag_04.buildClassifier(training_regr69);
			training_regr70 = DataHandler.getInstance().applyRegressionModel(
					training_regr69, fvAttributes, modelnajpgbag_04);
		} catch (Exception e) {
			training_regr70 = training_regr69;
		}
		
		training_regr70.setClass(fvAttributes.get(77));
		LinearRegression lr71 = new LinearRegression();
		Instances training_regr71 = null;
		modelnajpgbag_05.setFilter(rm64);
		modelnajpgbag_05.setClassifier(lr71);
		try {
			modelnajpgbag_05.buildClassifier(training_regr70);
			training_regr71 = DataHandler.getInstance().applyRegressionModel(
					training_regr70, fvAttributes, modelnajpgbag_05);
		} catch (Exception e) {
			training_regr71 = training_regr70;
		}
		
		training_regr71.setClass(fvAttributes.get(78));
		LinearRegression lr72 = new LinearRegression();
		Instances training_regr72 = null;
		modelnajpgbag_06.setFilter(rm65);
		modelnajpgbag_06.setClassifier(lr72);
		try {
			modelnajpgbag_06.buildClassifier(training_regr71);
			training_regr72 = DataHandler.getInstance().applyRegressionModel(
					training_regr71, fvAttributes, modelnajpgbag_06);
		} catch (Exception e) {
			training_regr72 = training_regr71;
		}
		
		training_regr72.setClass(fvAttributes.get(79));
		LinearRegression lr73 = new LinearRegression();
		Instances training_regr73 = null;
		modelnajpgbag_07.setFilter(rm66);
		modelnajpgbag_07.setClassifier(lr73);
		try {
			modelnajpgbag_07.buildClassifier(training_regr72);
			training_regr73 = DataHandler.getInstance().applyRegressionModel(
					training_regr72, fvAttributes, modelnajpgbag_07);
		} catch (Exception e) {
			training_regr73 = training_regr72;
		}
		
		training_regr73.setClass(fvAttributes.get(80));
		LinearRegression lr74 = new LinearRegression();
		Instances training_regr74 = null;
		modelnajpgbag_08.setFilter(rm67);
		modelnajpgbag_08.setClassifier(lr74);
		try {
			modelnajpgbag_08.buildClassifier(training_regr73);
			training_regr74 = DataHandler.getInstance().applyRegressionModel(
					training_regr73, fvAttributes, modelnajpgbag_08);
		} catch (Exception e) {
			training_regr74 = training_regr73;
		}
		
		training_regr74.setClass(fvAttributes.get(81));
		LinearRegression lr75 = new LinearRegression();
		Instances training_regr75 = null;
		modelnajpgbag_09.setFilter(rm68);
		modelnajpgbag_09.setClassifier(lr75);
		try {
			modelnajpgbag_09.buildClassifier(training_regr74);
			training_regr75 = DataHandler.getInstance().applyRegressionModel(
					training_regr74, fvAttributes, modelnajpgbag_09);
		} catch (Exception e) {
			training_regr75 = training_regr74;
		}
		
		training_regr75.setClass(fvAttributes.get(82));
		LinearRegression lr76 = new LinearRegression();
		Instances training_regr76 = null;
		modelnajpgbag_10.setFilter(rm69);
		modelnajpgbag_10.setClassifier(lr76);
		try {
			modelnajpgbag_10.buildClassifier(training_regr75);
			training_regr76 = DataHandler.getInstance().applyRegressionModel(
					training_regr75, fvAttributes, modelnajpgbag_10);
		} catch (Exception e) {
			training_regr76 = training_regr75;
		}
		
		training_regr76.setClass(fvAttributes.get(83));
		LinearRegression lr77 = new LinearRegression();
		Instances training_regr77 = null;
		modelnajpgbag_11.setFilter(rm70);
		modelnajpgbag_11.setClassifier(lr77);
		try {
			modelnajpgbag_11.buildClassifier(training_regr76);
			training_regr77 = DataHandler.getInstance().applyRegressionModel(
					training_regr76, fvAttributes, modelnajpgbag_11);
		} catch (Exception e) {
			training_regr77 = training_regr76;
		}
		
		training_regr77.setClass(fvAttributes.get(84));
		LinearRegression lr78 = new LinearRegression();
		Instances training_regr78 = null;
		modelnajpgbag_12.setFilter(rm71);
		modelnajpgbag_12.setClassifier(lr78);
		try {
			modelnajpgbag_12.buildClassifier(training_regr77);
			training_regr78 = DataHandler.getInstance().applyRegressionModel(
					training_regr77, fvAttributes, modelnajpgbag_12);
		} catch (Exception e) {
			training_regr78 = training_regr77;
		}
		
		training_regr78.setClass(fvAttributes.get(85));
		LinearRegression lr79 = new LinearRegression();
		Instances training_regr79 = null;
		modelnajpgbag_13.setFilter(rm72);
		modelnajpgbag_13.setClassifier(lr79);
		try {
			modelnajpgbag_13.buildClassifier(training_regr78);
			training_regr79 = DataHandler.getInstance().applyRegressionModel(
					training_regr78, fvAttributes, modelnajpgbag_13);
		} catch (Exception e) {
			training_regr79 = training_regr78;
		}
		
		training_regr79.setClass(fvAttributes.get(86));
		LinearRegression lr80 = new LinearRegression();
		Instances training_regr80 = null;
		modelnajpgbag_14.setFilter(rm73);
		modelnajpgbag_14.setClassifier(lr80);
		try {
			modelnajpgbag_14.buildClassifier(training_regr79);
			training_regr80 = DataHandler.getInstance().applyRegressionModel(
					training_regr79, fvAttributes, modelnajpgbag_14);
		} catch (Exception e) {
			training_regr80 = training_regr79;
		}
		
		training_regr80.setClass(fvAttributes.get(87));
		LinearRegression lr81 = new LinearRegression();
		Instances training_regr81 = null;
		modelnajpgbag_15.setFilter(rm74);
		modelnajpgbag_15.setClassifier(lr81);
		try {
			modelnajpgbag_15.buildClassifier(training_regr80);
			training_regr81 = DataHandler.getInstance().applyRegressionModel(
					training_regr80, fvAttributes, modelnajpgbag_15);
		} catch (Exception e) {
			training_regr81 = training_regr80;
		}
		
		training_regr81.setClass(fvAttributes.get(88));
		LinearRegression lr82 = new LinearRegression();
		Instances training_regr82 = null;
		modelnajpgbag_16.setFilter(rm75);
		modelnajpgbag_16.setClassifier(lr82);
		try {
			modelnajpgbag_16.buildClassifier(training_regr81);
			training_regr82 = DataHandler.getInstance().applyRegressionModel(
					training_regr81, fvAttributes, modelnajpgbag_16);
		} catch (Exception e) {
			training_regr82 = training_regr81;
		}
		
		// normalization part
		String[] options = { "-S", "1.0", "-T", "0.0" };
		normFilter.setOptions(options);
		normFilter.setInputFormat(training_regr82);

		
		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(
				training_regr82, fvAttributes.size() - 1, normFilter);
		
		
		for (int i=0; i<trainingSet_normed.size(); i++) {
			//System.out.println(trainingSet_normed.get(i));
			for (int j=0; j<trainingSet_normed.get(i).numAttributes();j++) {
				if (!trainingSet_normed.get(i).attribute(j).isNominal())
				trainingSet_normed.get(i).setValue(j, round(trainingSet_normed.get(i).value(j), 6) );
			}
			//System.out.println(trainingSet_normed.get(i));
		}
		
		trainingSet_normed = getTrimmedInstances(trainingSet_normed);
		
		return trainingSet_normed;

	}
	
	
	/**
	 * Applies linear regression to the missing values of the training set in
	 * order to predict their values and normalization to make the values fall
	 * into the range [0,1]. It is the case of the Item type Instances.
	 * 
	 * @param trainingSet
	 * @return Instances the transformed training set
	 * @throws Exception
	 */
	public Instances getTransformedTrainingTimeFeats(Instances trainingSet)
			throws Exception {

		initializeModels();

		Remove rm = new Remove();
		rm.setAttributeIndices("1");

		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();

		// REGRESSION
		// readability
		trainingSet.setClass(fvAttributes.get(23));
		LinearRegression lr = new LinearRegression();
		Instances training_regr = null;
		model.setFilter(rm);
		model.setClassifier(lr);

		try {
			model.buildClassifier(trainingSet);
			training_regr = DataHandler.getInstance().applyRegressionModel(
					trainingSet, fvAttributes, model);
		} catch (Exception e) {
			training_regr = trainingSet;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// num slangs
		training_regr.setClass(fvAttributes.get(18));
		LinearRegression lr2 = new LinearRegression();
		Instances training_regr2 = null;

		model2.setFilter(rm);
		model2.setClassifier(lr2);
		try {
			model2.buildClassifier(training_regr);
			training_regr2 = DataHandler.getInstance().applyRegressionModel(
					training_regr, fvAttributes, model2);
		} catch (Exception e) {
			training_regr2 = training_regr;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// getNumPosSentiWords
		training_regr2.setClass(fvAttributes.get(13));
		LinearRegression lr3 = new LinearRegression();
		Instances training_regr3 = null;

		model3.setFilter(rm);
		model3.setClassifier(lr3);
		try {
			model3.buildClassifier(training_regr2);
			training_regr3 = DataHandler.getInstance().applyRegressionModel(
					training_regr2, fvAttributes, model3);
		} catch (Exception e) {
			training_regr3 = training_regr2;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// getNumNegSentiWords
		training_regr3.setClass(fvAttributes.get(14));
		LinearRegression lr4 = new LinearRegression();
		Instances training_regr4 = null;
		model4.setFilter(rm);
		model4.setClassifier(lr4);
		try {
			model4.buildClassifier(training_regr3);
			training_regr4 = DataHandler.getInstance().applyRegressionModel(
					training_regr3, fvAttributes, model4);
		} catch (Exception e) {
			training_regr4 = training_regr3;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// getNumNouns
		training_regr4.setClass(fvAttributes.get(6));
		LinearRegression lr5 = new LinearRegression();
		Instances training_regr5 = null;
		model5.setFilter(rm);
		model5.setClassifier(lr5);

		try {
			model5.buildClassifier(training_regr4);
			training_regr5 = DataHandler.getInstance().applyRegressionModel(
					training_regr4, fvAttributes, model5);
		} catch (Exception e) {
			training_regr5 = training_regr4;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// indegree
		training_regr5.setClass(fvAttributes.get(24));
		LinearRegression lr6 = new LinearRegression();
		Instances training_regr6 = null;
		model6.setFilter(rm);
		model6.setClassifier(lr6);
		try {
			model6.buildClassifier(training_regr5);
			training_regr6 = DataHandler.getInstance().applyRegressionModel(
					training_regr5, fvAttributes, model6);
		} catch (Exception e) {
			training_regr6 = training_regr5;
		}

		// harmonic
		training_regr6.setClass(fvAttributes.get(25));
		LinearRegression lr7 = new LinearRegression();
		Instances training_regr7 = null;
		model7.setFilter(rm);
		model7.setClassifier(lr7);
		try {
			model7.buildClassifier(training_regr6);
			training_regr7 = DataHandler.getInstance().applyRegressionModel(
					training_regr6, fvAttributes, model7);
		} catch (Exception e) {
			training_regr7 = training_regr6;
		}

		// System.out.println(model7);

		// normalization part
		String[] options = { "-S", "1.0", "-T", "0.0" };
		normFilter.setOptions(options);
		normFilter.setInputFormat(training_regr7);

		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(
				training_regr7, fvAttributes.size() - 1, normFilter);

		return trainingSet_normed;

	}

	public Instances getTransformedTrainingAttSelection1(Instances trainingSet)
			throws Exception {

		initializeModels();

		Remove rm = new Remove();
		rm.setAttributeIndices("1");

		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();

		// REGRESSION
		// wotScore
		trainingSet.setClass(fvAttributes.get(4));
		LinearRegression lr = new LinearRegression();
		Instances training_regr = null;
		model.setFilter(rm);
		model.setClassifier(lr);

		try {
			model.buildClassifier(trainingSet);
			training_regr = DataHandler.getInstance().applyRegressionModel(
					trainingSet, fvAttributes, model);
		} catch (Exception e) {
			training_regr = trainingSet;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// readability
		training_regr.setClass(fvAttributes.get(6));
		LinearRegression lr2 = new LinearRegression();
		Instances training_regr2 = null;

		model2.setFilter(rm);
		model2.setClassifier(lr2);
		try {
			model2.buildClassifier(training_regr);
			training_regr2 = DataHandler.getInstance().applyRegressionModel(
					training_regr, fvAttributes, model2);
		} catch (Exception e) {
			training_regr2 = training_regr;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// normalization part
		String[] options = { "-S", "1.0", "-T", "0.0" };
		normFilter.setOptions(options);
		normFilter.setInputFormat(training_regr2);

		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(
				training_regr2, fvAttributes.size() - 1, normFilter);

		return trainingSet_normed;

	}

	public Instances getTransformedTrainingAttSelection2(Instances trainingSet)
			throws Exception {

		initializeModels();

		Remove rm = new Remove();
		rm.setAttributeIndices("1");

		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();

		// REGRESSION
		// retweet count
		trainingSet.setClass(fvAttributes.get(5));
		LinearRegression lr = new LinearRegression();
		Instances training_regr = null;
		model.setFilter(rm);
		model.setClassifier(lr);

		try {
			model.buildClassifier(trainingSet);
			training_regr = DataHandler.getInstance().applyRegressionModel(
					trainingSet, fvAttributes, model);
		} catch (Exception e) {
			training_regr = trainingSet;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// harmonic
		training_regr.setClass(fvAttributes.get(6));
		LinearRegression lr2 = new LinearRegression();
		Instances training_regr2 = null;

		model2.setFilter(rm);
		model2.setClassifier(lr2);
		try {
			model2.buildClassifier(training_regr);
			training_regr2 = DataHandler.getInstance().applyRegressionModel(
					training_regr, fvAttributes, model2);
		} catch (Exception e) {
			training_regr2 = training_regr;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// normalization part
		String[] options = { "-S", "2.0", "-T", "-1.0" };
		normFilter.setOptions(options);
		normFilter.setInputFormat(training_regr2);

		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(
				training_regr2, fvAttributes.size() - 1, normFilter);

		return trainingSet_normed;

	}

	/**
	 * Applies linear regression to the missing values of the training set in
	 * order to predict their values and normalization to make the values fall
	 * into the range [0,1]. It is the case of the User type Instances.
	 * 
	 * @param trainingSet
	 * @return Instances the transformed training set
	 * @throws Exception
	 */
	public Instances getTransformedTrainingUserOld(Instances trainingSet) {

		initializeModels();

		ArrayList<Attribute> fvAttributes = UserClassifier.getFvAttributes();

		// normalization
		normFilterUser = DataHandler.getInstance().createNormalizationFilter(trainingSet);
		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(trainingSet, fvAttributes.size() - 1, normFilterUser);
		
		trainingSet_normed = getTrimmedInstances(trainingSet_normed);

		return trainingSet_normed;
	}

	/**
	 * Applies linear regression to the missing values of the training set in
	 * order to predict their values and normalization to make the values fall
	 * into the range [0,1]. It is the case of the User type Instances.
	 * 
	 * @param trainingSet
	 * @return Instances the transformed training set
	 * @throws Exception
	 */
	public Instances getTransformedTrainingUser(Instances trainingSet) {

		initializeModels();

		ArrayList<Attribute> fvAttributes = UserClassifier.getFvAttributes();

		// remove filter in order to remove the id attribute
		Remove rm = new Remove();
		rm.setAttributeIndices("1");

		// regression
		trainingSet.setClass(fvAttributes.get(11));
		LinearRegression lr = new LinearRegression();
		Instances training_regr = null;
		usermodel.setFilter(rm);
		usermodel.setClassifier(lr);

		try {
			usermodel.buildClassifier(trainingSet);
			training_regr = DataHandler.getInstance().applyRegressionModel(
					trainingSet, fvAttributes, usermodel);
		} catch (Exception e) {
			training_regr = trainingSet;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		training_regr.setClass(fvAttributes.get(13));
		LinearRegression lr2 = new LinearRegression();
		Instances training_regr2 = null;
		usermodel2.setFilter(rm);
		usermodel2.setClassifier(lr2);
		try {
			usermodel2.buildClassifier(training_regr);
			training_regr2 = DataHandler.getInstance().applyRegressionModel(
					training_regr, fvAttributes, usermodel2);
		} catch (Exception e) {
			training_regr2 = training_regr;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		training_regr2.setClass(fvAttributes.get(16));
		LinearRegression lr3 = new LinearRegression();
		Instances training_regr3 = null;
		usermodel3.setFilter(rm);
		usermodel3.setClassifier(lr3);
		try {
			usermodel3.buildClassifier(training_regr2);
			training_regr3 = DataHandler.getInstance().applyRegressionModel(
					training_regr2, fvAttributes, usermodel3);
		} catch (Exception e) {
			training_regr3 = training_regr2;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		training_regr3.setClass(fvAttributes.get(17));
		LinearRegression lr4 = new LinearRegression();
		Instances training_regr4 = null;
		usermodel4.setFilter(rm);
		usermodel4.setClassifier(lr4);
		try {
			usermodel4.buildClassifier(training_regr3);
			training_regr4 = DataHandler.getInstance().applyRegressionModel(
					training_regr3, fvAttributes, usermodel4);
		} catch (Exception e) {
			training_regr4 = training_regr3;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		// System.out.println(usermodel4);

		training_regr4.setClass(fvAttributes.get(18));
		LinearRegression lr5 = new LinearRegression();
		Instances training_regr5 = null;
		usermodel5.setFilter(rm);
		usermodel5.setClassifier(lr5);
		try {
			usermodel5.buildClassifier(training_regr4);
			training_regr5 = DataHandler.getInstance().applyRegressionModel(
					training_regr4, fvAttributes, usermodel5);
		} catch (Exception e) {
			training_regr5 = training_regr4;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		// System.out.println(usermodel5);

		// normalization
		normFilterUser = DataHandler.getInstance().createNormalizationFilter(
				training_regr5);
		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(
				training_regr5, fvAttributes.size() - 1, normFilterUser);

		return trainingSet_normed;
	}

	
	/**
	 * Applies linear regression to the missing values of the training set in
	 * order to predict their values and normalization to make the values fall
	 * into the range [0,1]. It is the case of the User type Instances.
	 * 
	 * @param trainingSet
	 * @return Instances the transformed training set
	 * @throws Exception
	 */
	public Instances getTransformedTrainingUserOverall(Instances trainingSet) {

		initializeModels();

		ArrayList<Attribute> fvAttributes = UserClassifier.getFvAttributes();
		
		
		// remove filter in order to remove the id attribute
		Remove rm = new Remove();
		rm.setAttributeIndices("1");
		Remove rm1 = new Remove();
		rm1.setAttributeIndices("1");
		Remove rm2 = new Remove();
		rm2.setAttributeIndices("1");
		Remove rm3 = new Remove();
		rm3.setAttributeIndices("1");
		Remove rm4 = new Remove();
		rm4.setAttributeIndices("1");
		Remove rm5 = new Remove();
		rm5.setAttributeIndices("1");
		Remove rm6 = new Remove();
		rm6.setAttributeIndices("1");
		Remove rm7 = new Remove();
		rm7.setAttributeIndices("1");
		Remove rm8 = new Remove();
		rm8.setAttributeIndices("1");

		// regression
		trainingSet.setClass(fvAttributes.get(11));
		LinearRegression lr = new LinearRegression();
		Instances training_regr = null;
		usermodel.setFilter(rm);
		usermodel.setClassifier(lr);

		try {
			usermodel.buildClassifier(trainingSet);
			training_regr = DataHandler.getInstance().applyRegressionModel(
					trainingSet, fvAttributes, usermodel);
		} catch (Exception e) {
			training_regr = trainingSet;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		training_regr.setClass(fvAttributes.get(13));
		LinearRegression lr2 = new LinearRegression();
		Instances training_regr2 = null;
		usermodel2.setFilter(rm1);
		usermodel2.setClassifier(lr2);
		try {
			usermodel2.buildClassifier(training_regr);
			training_regr2 = DataHandler.getInstance().applyRegressionModel(
					training_regr, fvAttributes, usermodel2);
		} catch (Exception e) {
			training_regr2 = training_regr;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		training_regr2.setClass(fvAttributes.get(16));
		LinearRegression lr3 = new LinearRegression();
		Instances training_regr3 = null;
		usermodel3.setFilter(rm2);
		usermodel3.setClassifier(lr3);
		try {
			usermodel3.buildClassifier(training_regr2);
			training_regr3 = DataHandler.getInstance().applyRegressionModel(
					training_regr2, fvAttributes, usermodel3);
		} catch (Exception e) {
			training_regr3 = training_regr2;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		training_regr3.setClass(fvAttributes.get(17));
		LinearRegression lr4 = new LinearRegression();
		Instances training_regr4 = null;
		usermodel4.setFilter(rm3);
		usermodel4.setClassifier(lr4);
		try {
			usermodel4.buildClassifier(training_regr3);
			training_regr4 = DataHandler.getInstance().applyRegressionModel(
					training_regr3, fvAttributes, usermodel4);
		} catch (Exception e) {
			training_regr4 = training_regr3;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		// System.out.println(usermodel4);

		training_regr4.setClass(fvAttributes.get(18));
		LinearRegression lr5 = new LinearRegression();
		Instances training_regr5 = null;
		usermodel5.setFilter(rm4);
		usermodel5.setClassifier(lr5);
		try {
			usermodel5.buildClassifier(training_regr4);
			training_regr5 = DataHandler.getInstance().applyRegressionModel(
					training_regr4, fvAttributes, usermodel5);
		} catch (Exception e) {
			training_regr5 = training_regr4;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		// alexa country rank
		training_regr5.setClass(fvAttributes.get(19));
		LinearRegression lr6 = new LinearRegression();
		Instances training_regr6 = null;
		usermodel6.setFilter(rm5);
		usermodel6.setClassifier(lr6);
		try {
			usermodel6.buildClassifier(training_regr5);
			training_regr6 = DataHandler.getInstance().applyRegressionModel(
					training_regr5, fvAttributes, usermodel6);
		} catch (Exception e) {
			training_regr6 = training_regr5;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// alexa delta rank
		training_regr6.setClass(fvAttributes.get(20));
		LinearRegression lr7 = new LinearRegression();
		Instances training_regr7 = null;
		usermodel7.setFilter(rm6);
		usermodel7.setClassifier(lr7);
		try {
			usermodel7.buildClassifier(training_regr6);
			training_regr7 = DataHandler.getInstance().applyRegressionModel(
					training_regr6, fvAttributes, usermodel7);
		} catch (Exception e) {
			training_regr7 = training_regr6;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		// alexa popularity
		training_regr7.setClass(fvAttributes.get(21));
		LinearRegression lr8 = new LinearRegression();
		Instances training_regr8 = null;
		usermodel8.setFilter(rm7);
		usermodel8.setClassifier(lr8);
		try {
			usermodel8.buildClassifier(training_regr7);
			training_regr8 = DataHandler.getInstance().applyRegressionModel(
					training_regr7, fvAttributes, usermodel8);
		} catch (Exception e) {
			training_regr8 = training_regr7;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		// alexa reach rank
		training_regr8.setClass(fvAttributes.get(22));
		LinearRegression lr9 = new LinearRegression();
		Instances training_regr9 = null;
		usermodel9.setFilter(rm8);
		usermodel9.setClassifier(lr9);
		try {
			usermodel9.buildClassifier(training_regr8);
			training_regr9 = DataHandler.getInstance().applyRegressionModel(
					training_regr8, fvAttributes, usermodel9);
		} catch (Exception e) {
			training_regr9 = training_regr8;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		//System.out.println(training_regr9.get(0));
		
		// normalization
		normFilterUser = DataHandler.getInstance().createNormalizationFilter(training_regr9);
		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(training_regr9, fvAttributes.size() - 1, normFilterUser);
		trainingSet_normed.setClassIndex(fvAttributes.size()-1);
		
		
		for (int i=0; i<trainingSet_normed.size(); i++) {
			
			for (int j=0; j<trainingSet_normed.get(i).numAttributes();j++) {
				if (!trainingSet_normed.get(i).attribute(j).isNominal())
				trainingSet_normed.get(i).setValue(j, round(trainingSet_normed.get(i).value(j), 6) );
			}
			
		}
		
		trainingSet_normed = getTrimmedInstances(trainingSet_normed);
		
		return trainingSet_normed;
	}
	
	/**
	 * Applies linear regression to the missing values of the training set in
	 * order to predict their values and normalization to make the values fall
	 * into the range [0,1]. It is the case of the User type Instances.
	 * 
	 * @param trainingSet
	 * @return Instances the transformed training set
	 * @throws Exception
	 */
	public Instances getTransformedTrainingUserTimeFeats(Instances trainingSet) {

		initializeModels();

		ArrayList<Attribute> fvAttributes = UserClassifier.getFvAttributes();

		// remove filter in order to remove the id attribute
		Remove rm = new Remove();
		rm.setAttributeIndices("1");

		// regression
		trainingSet.setClass(fvAttributes.get(8));
		LinearRegression lr = new LinearRegression();
		Instances training_regr = null;
		usermodel.setFilter(rm);
		usermodel.setClassifier(lr);

		try {
			usermodel.buildClassifier(trainingSet);
			training_regr = DataHandler.getInstance().applyRegressionModel(
					trainingSet, fvAttributes, usermodel);
		} catch (Exception e) {
			training_regr = trainingSet;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		training_regr.setClass(fvAttributes.get(9));
		LinearRegression lr2 = new LinearRegression();
		Instances training_regr2 = null;
		usermodel2.setFilter(rm);
		usermodel2.setClassifier(lr2);
		try {
			usermodel2.buildClassifier(training_regr);
			training_regr2 = DataHandler.getInstance().applyRegressionModel(
					training_regr, fvAttributes, usermodel2);
		} catch (Exception e) {
			training_regr2 = training_regr;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// normalization
		normFilterUser = DataHandler.getInstance().createNormalizationFilter(
				training_regr2);
		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(
				training_regr2, fvAttributes.size() - 1, normFilterUser);

		return trainingSet_normed;
	}

	public Instances getTransformedTrainingUserAttSelection1(
			Instances trainingSet) {

		initializeModels();

		ArrayList<Attribute> fvAttributes = UserClassifier.getFvAttributes();

		// remove filter in order to remove the id attribute
		Remove rm = new Remove();
		rm.setAttributeIndices("1");

		// wottrustuser
		trainingSet.setClass(fvAttributes.get(1));
		LinearRegression lr = new LinearRegression();
		Instances training_regr = null;
		usermodel.setFilter(rm);
		usermodel.setClassifier(lr);

		try {
			usermodel.buildClassifier(trainingSet);
			training_regr = DataHandler.getInstance().applyRegressionModel(
					trainingSet, fvAttributes, usermodel);
		} catch (Exception e) {
			training_regr = trainingSet;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		training_regr.setClass(fvAttributes.get(4));
		LinearRegression lr2 = new LinearRegression();
		Instances training_regr2 = null;
		usermodel2.setFilter(rm);
		usermodel2.setClassifier(lr2);
		try {
			usermodel2.buildClassifier(training_regr);
			training_regr2 = DataHandler.getInstance().applyRegressionModel(
					training_regr, fvAttributes, usermodel2);
		} catch (Exception e) {
			training_regr2 = training_regr;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// normalization
		normFilterUser = DataHandler.getInstance().createNormalizationFilter(
				training_regr2);
		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(
				training_regr2, fvAttributes.size() - 1, normFilterUser);

		return trainingSet_normed;
	}

	public Instances getTransformedTrainingUserAttSelection2(
			Instances trainingSet) {

		initializeModels();

		ArrayList<Attribute> fvAttributes = UserClassifier.getFvAttributes();

		// remove filter in order to remove the id attribute
		Remove rm = new Remove();
		rm.setAttributeIndices("1");

		// wottrustuser
		trainingSet.setClass(fvAttributes.get(1));
		LinearRegression lr = new LinearRegression();
		Instances training_regr = null;
		usermodel.setFilter(rm);
		usermodel.setClassifier(lr);

		try {
			usermodel.buildClassifier(trainingSet);
			training_regr = DataHandler.getInstance().applyRegressionModel(
					trainingSet, fvAttributes, usermodel);
		} catch (Exception e) {
			training_regr = trainingSet;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		training_regr.setClass(fvAttributes.get(2));
		LinearRegression lr2 = new LinearRegression();
		Instances training_regr2 = null;
		usermodel2.setFilter(rm);
		usermodel2.setClassifier(lr2);
		try {
			usermodel2.buildClassifier(training_regr);
			training_regr2 = DataHandler.getInstance().applyRegressionModel(
					training_regr, fvAttributes, usermodel2);
		} catch (Exception e) {
			training_regr2 = training_regr;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		training_regr2.setClass(fvAttributes.get(3));
		LinearRegression lr3 = new LinearRegression();
		Instances training_regr3 = null;
		usermodel3.setFilter(rm);
		usermodel3.setClassifier(lr3);
		try {
			usermodel3.buildClassifier(training_regr2);
			training_regr3 = DataHandler.getInstance().applyRegressionModel(
					training_regr2, fvAttributes, usermodel3);
		} catch (Exception e) {
			training_regr3 = training_regr2;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// normalization
		normFilterUser = DataHandler.getInstance().createNormalizationFilter(
				training_regr3);
		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(
				training_regr3, fvAttributes.size() - 1, normFilterUser);

		return trainingSet_normed;
	}

	public Instances getTransformedTestingOld(Instances testing)
			throws Exception {

		//FileManager.getInstance().writeInstancesToFile("C:\\Users\\boididou\\Desktop\\hello.txt", testing);
		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();
		Instances testing_regr = null, testing_regr2 = null, testing_regr3 = null, testing_regr4 = null, testing_regr5 = null, testing_regr6 = null, testing_regr7 = null, testing_regr8 = null;

		// regression
		if (!model.toString().contains("No model built yet.")) {
			testing.setClass(fvAttributes.get(11));
			testing_regr = DataHandler.getInstance().applyRegressionModel(
					testing, fvAttributes, model);
		} else {
			testing_regr = testing;
		}
		

		if (!model2.toString().contains("No model built yet.")) {
			testing_regr.setClass(fvAttributes.get(12));
			testing_regr2 = DataHandler.getInstance().applyRegressionModel(
					testing_regr, fvAttributes, model2);
		} else {
			testing_regr2 = testing_regr;
		}
		
		if (!model3.toString().contains("No model built yet.")) {
			testing_regr2.setClass(fvAttributes.get(7));
			testing_regr3 = DataHandler.getInstance().applyRegressionModel(
					testing_regr2, fvAttributes, model3);
		} else {
			testing_regr3 = testing_regr2;
		}
		
		if (!model4.toString().contains("No model built yet.")) {
			testing_regr3.setClass(fvAttributes.get(8));
			testing_regr4 = DataHandler.getInstance().applyRegressionModel(
					testing_regr3, fvAttributes, model4);
		} else {
			testing_regr4 = testing_regr3;
		}
		
		if (!model5.toString().contains("No model built yet.")) {
			testing_regr4.setClass(fvAttributes.get(9));
			testing_regr5 = DataHandler.getInstance().applyRegressionModel(
					testing_regr4, fvAttributes, model5);
		} else {
			testing_regr5 = testing_regr4;
		}

		// normalization
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(
				testing_regr5, fvAttributes.size() - 1, normFilter);
		// testingSet_normed = getTrimmedInstances(testingSet_normed);

		testingSet_normed = getTrimmedInstances(testingSet_normed);
		
		return testingSet_normed;
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
	public Instances getTransformedTesting(Instances testing) throws Exception {

		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();
		Instances testing_regr = null, testing_regr2 = null, testing_regr3 = null, testing_regr4 = null, testing_regr5 = null, testing_regr6 = null, testing_regr7 = null, testing_regr8 = null;

		// regression
		if (!model.toString().contains("No model built yet.")) {
			testing.setClass(fvAttributes.get(22));
			testing_regr = DataHandler.getInstance().applyRegressionModel(
					testing, fvAttributes, model);
		} else {
			testing_regr = testing;
		}

		if (!model2.toString().contains("No model built yet.")) {
			testing_regr.setClass(fvAttributes.get(25));
			testing_regr2 = DataHandler.getInstance().applyRegressionModel(
					testing_regr, fvAttributes, model2);
		} else {
			testing_regr2 = testing_regr;
		}

		if (!model3.toString().contains("No model built yet.")) {
			testing_regr2.setClass(fvAttributes.get(19));
			testing_regr3 = DataHandler.getInstance().applyRegressionModel(
					testing_regr2, fvAttributes, model3);
		} else {
			testing_regr3 = testing_regr2;
		}

		if (!model4.toString().contains("No model built yet.")) {
			testing_regr3.setClass(fvAttributes.get(13));
			testing_regr4 = DataHandler.getInstance().applyRegressionModel(
					testing_regr3, fvAttributes, model4);
		} else {
			testing_regr4 = testing_regr3;
		}

		if (!model5.toString().contains("No model built yet.")) {
			testing_regr4.setClass(fvAttributes.get(14));
			testing_regr5 = DataHandler.getInstance().applyRegressionModel(
					testing_regr4, fvAttributes, model5);
		} else {
			testing_regr5 = testing_regr4;
		}

		if (!model6.toString().contains("No model built yet.")) {
			testing_regr5.setClass(fvAttributes.get(6));
			testing_regr6 = DataHandler.getInstance().applyRegressionModel(
					testing_regr5, fvAttributes, model6);
		} else {
			testing_regr6 = testing_regr5;
		}

		if (!model7.toString().contains("No model built yet.")) {
			testing_regr6.setClass(fvAttributes.get(26));
			testing_regr7 = DataHandler.getInstance().applyRegressionModel(
					testing_regr6, fvAttributes, model7);
		} else {
			testing_regr7 = testing_regr6;
		}

		if (!model8.toString().contains("No model built yet.")) {
			testing_regr7.setClass(fvAttributes.get(27));
			testing_regr8 = DataHandler.getInstance().applyRegressionModel(
					testing_regr7, fvAttributes, model8);
		} else {
			testing_regr8 = testing_regr7;
		}

		// normalization
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(
				testing_regr8, fvAttributes.size() - 1, normFilter);
		// testingSet_normed = getTrimmedInstances(testingSet_normed);

		return testingSet_normed;
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
	public Instances getTransformedTestingOverall(Instances testing) throws Exception {

		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();
		Instances testing_regr = null, testing_regr2 = null, testing_regr3 = null, testing_regr4 = null, testing_regr5 = null, 
				testing_regr6 = null, testing_regr7 = null, testing_regr8 = null, testing_regr9 = null, testing_regr10 = null,
						testing_regr11 = null, testing_regr12 = null, testing_regr13 = null, testing_regr14 = null, testing_regr15 = null;
		
		// regression
		if (!model.toString().contains("No model built yet.")) {
			testing.setClass(fvAttributes.get(22));
			testing_regr = DataHandler.getInstance().applyRegressionModel(
					testing, fvAttributes, model);
		} else {
			testing_regr = testing;
		}

		if (!model2.toString().contains("No model built yet.")) {
			testing_regr.setClass(fvAttributes.get(25));
			testing_regr2 = DataHandler.getInstance().applyRegressionModel(
					testing_regr, fvAttributes, model2);
		} else {
			testing_regr2 = testing_regr;
		}

		if (!model3.toString().contains("No model built yet.")) {
			testing_regr2.setClass(fvAttributes.get(30));
			testing_regr3 = DataHandler.getInstance().applyRegressionModel(
					testing_regr2, fvAttributes, model3);
		} else {
			testing_regr3 = testing_regr2;
		}

		if (!model4.toString().contains("No model built yet.")) {
			testing_regr3.setClass(fvAttributes.get(29));
			testing_regr4 = DataHandler.getInstance().applyRegressionModel(
					testing_regr3, fvAttributes, model4);
		} else {
			testing_regr4 = testing_regr3;
		}

		if (!model5.toString().contains("No model built yet.")) {
			testing_regr4.setClass(fvAttributes.get(14));
			testing_regr5 = DataHandler.getInstance().applyRegressionModel(
					testing_regr4, fvAttributes, model5);
		} else {
			testing_regr5 = testing_regr4;
		}

		if (!model6.toString().contains("No model built yet.")) {
			testing_regr5.setClass(fvAttributes.get(19));
			testing_regr6 = DataHandler.getInstance().applyRegressionModel(
					testing_regr5, fvAttributes, model6);
		} else {
			testing_regr6 = testing_regr5;
		}

		if (!model7.toString().contains("No model built yet.")) {
			testing_regr6.setClass(fvAttributes.get(26));
			testing_regr7 = DataHandler.getInstance().applyRegressionModel(
					testing_regr6, fvAttributes, model7);
		} else {
			testing_regr7 = testing_regr6;
		}

		if (!model8.toString().contains("No model built yet.")) {
			testing_regr7.setClass(fvAttributes.get(28));
			testing_regr8 = DataHandler.getInstance().applyRegressionModel(
					testing_regr7, fvAttributes, model8);
		} else {
			testing_regr8 = testing_regr7;
		}

		if (!model9.toString().contains("No model built yet.")) {
			testing_regr8.setClass(fvAttributes.get(27));
			testing_regr9 = DataHandler.getInstance().applyRegressionModel(
					testing_regr8, fvAttributes, model9);
		} else {
			testing_regr9 = testing_regr8;
		}
		
		if (!model10.toString().contains("No model built yet.")) {
			testing_regr9.setClass(fvAttributes.get(13));
			testing_regr10 = DataHandler.getInstance().applyRegressionModel(
					testing_regr9, fvAttributes, model10);
		} else {
			testing_regr10 = testing_regr9;
		}
		
		if (!model11.toString().contains("No model built yet.")) {
			testing_regr10.setClass(fvAttributes.get(31));
			testing_regr11 = DataHandler.getInstance().applyRegressionModel(
					testing_regr10, fvAttributes, model11);
		} else {
			testing_regr11 = testing_regr10;
		}
		
		if (!model12.toString().contains("No model built yet.")) {
			testing_regr11.setClass(fvAttributes.get(6));
			testing_regr12 = DataHandler.getInstance().applyRegressionModel(
					testing_regr11, fvAttributes, model12);
		} else {
			testing_regr12 = testing_regr11;
		}
		
		if (!model13.toString().contains("No model built yet.")) {
			testing_regr12.setClass(fvAttributes.get(9));
			testing_regr13 = DataHandler.getInstance().applyRegressionModel(
					testing_regr12, fvAttributes, model13);
		} else {
			testing_regr13 = testing_regr12;
		}
		
		if (!model14.toString().contains("No model built yet.")) {
			testing_regr13.setClass(fvAttributes.get(10));
			testing_regr14 = DataHandler.getInstance().applyRegressionModel(
					testing_regr13, fvAttributes, model14);
		} else {
			testing_regr14 = testing_regr13;
		}
		
		if (!model15.toString().contains("No model built yet.")) {
			testing_regr14.setClass(fvAttributes.get(11));
			testing_regr15 = DataHandler.getInstance().applyRegressionModel(
					testing_regr14, fvAttributes, model15);
		} else {
			testing_regr15 = testing_regr14;
		}
		
		
		// normalization
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(testing_regr15, fvAttributes.size() - 1, normFilter);
		testingSet_normed.setClassIndex(fvAttributes.size()-1);
		
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
	
	
	public Instances getTransformedTestingBaselineEnriched(Instances testing) throws Exception {

		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();
		
		Instances testing_regr = null, testing_regr2 = null, testing_regr3 = null, testing_regr4 = null, testing_regr5 = null, 
				testing_regr6 = null, testing_regr7 = null, testing_regr8 = null, testing_regr9 = null, testing_regr10 = null,
						testing_regr11 = null, testing_regr12 = null, testing_regr13 = null, testing_regr14 = null, testing_regr15 = null,
								testing_regr16 = null, testing_regr17 = null, testing_regr18 = null, testing_regr19 = null, testing_regr20 = null,
										 testing_regr21 = null, testing_regr22 = null, testing_regr23 = null, testing_regr24 = null, testing_regr25 = null, testing_regr26 = null,
												 testing_regr27 = null, testing_regr28 = null, testing_regr29 = null, testing_regr30 = null, testing_regr31 = null, testing_regr32 = null
														 , testing_regr33 = null, testing_regr34 = null, testing_regr35 = null, testing_regr36 = null, testing_regr37 = null, testing_regr38 = null
																 , testing_regr39 = null, testing_regr40 = null, testing_regr41 = null, testing_regr42 = null, testing_regr43 = null, testing_regr44 = null
																		 , testing_regr45 = null, testing_regr46 = null, testing_regr47 = null, testing_regr48 = null, testing_regr49 = null, testing_regr50 = null
		 , testing_regr51 = null, testing_regr52 = null, testing_regr53 = null, testing_regr54 = null, testing_regr55 = null, testing_regr56 = null, testing_regr57 = null, testing_regr58 = null, testing_regr59 = null
				 , testing_regr60 = null, testing_regr61 = null, testing_regr62 = null, testing_regr63 = null, testing_regr64 = null, testing_regr65 = null, testing_regr66 = null, testing_regr67 = null, testing_regr68 = null
						 , testing_regr69 = null, testing_regr70 = null, testing_regr71 = null, testing_regr72 = null, testing_regr73 = null, testing_regr74 = null, testing_regr75 = null, testing_regr76 = null, testing_regr77 = null, testing_regr78 = null
								 , testing_regr79 = null, testing_regr80 = null, testing_regr81 = null, testing_regr82 = null;

		
		if (!model.toString().contains("No model built yet.")) {
			testing.setClass(fvAttributes.get(12));
			testing_regr = DataHandler.getInstance().applyRegressionModel(
					testing, fvAttributes, model);
		} else {
			testing_regr = testing;
		}

		if (!model2.toString().contains("No model built yet.")) {
			testing_regr.setClass(fvAttributes.get(11));
			testing_regr2 = DataHandler.getInstance().applyRegressionModel(
					testing_regr, fvAttributes, model2);
		} else {
			testing_regr2 = testing_regr;
		}

		if (!model3.toString().contains("No model built yet.")) {
			testing_regr2.setClass(fvAttributes.get(7));
			testing_regr3 = DataHandler.getInstance().applyRegressionModel(
					testing_regr2, fvAttributes, model3);
		} else {
			testing_regr3 = testing_regr2;
		}

		if (!model4.toString().contains("No model built yet.")) {
			testing_regr3.setClass(fvAttributes.get(8));
			testing_regr4 = DataHandler.getInstance().applyRegressionModel(
					testing_regr3, fvAttributes, model4);
		} else {
			testing_regr4 = testing_regr3;
		}

		if (!model5.toString().contains("No model built yet.")) {
			testing_regr4.setClass(fvAttributes.get(9));
			testing_regr5 = DataHandler.getInstance().applyRegressionModel(
					testing_regr4, fvAttributes, model5);
		} else {
			testing_regr5 = testing_regr4;
		}

		
		if (!modelsad_01.toString().contains("No model built yet.")) {
			testing_regr5.setClass(fvAttributes.get(41));
			testing_regr13 = DataHandler.getInstance().applyRegressionModel(
					testing_regr5, fvAttributes, modelsad_01);
		} else {
			testing_regr13 = testing_regr5;
		}
		
		if (!modelsad_02.toString().contains("No model built yet.")) {
			testing_regr13.setClass(fvAttributes.get(42));
			testing_regr14 = DataHandler.getInstance().applyRegressionModel(
					testing_regr13, fvAttributes, modelsad_02);
		} else {
			testing_regr14 = testing_regr13;
		}
		
		if (!modelsad_03.toString().contains("No model built yet.")) {
			testing_regr14.setClass(fvAttributes.get(43));
			testing_regr15 = DataHandler.getInstance().applyRegressionModel(
					testing_regr14, fvAttributes, modelsad_03);
		} else {
			testing_regr15 = testing_regr14;
		}
		
		if (!modelsad_04.toString().contains("No model built yet.")) {
			testing_regr15.setClass(fvAttributes.get(44));
			testing_regr16 = DataHandler.getInstance().applyRegressionModel(
					testing_regr15, fvAttributes, modelsad_04);
		} else {
			testing_regr16 = testing_regr15;
		}
		
		if (!modelsad_05.toString().contains("No model built yet.")) {
			testing_regr16.setClass(fvAttributes.get(45));
			testing_regr17 = DataHandler.getInstance().applyRegressionModel(
					testing_regr16, fvAttributes, modelsad_05);
		} else {
			testing_regr17 = testing_regr16;
		}
		
		if (!modelsad_06.toString().contains("No model built yet.")) {
			testing_regr17.setClass(fvAttributes.get(46));
			testing_regr18 = DataHandler.getInstance().applyRegressionModel(
					testing_regr17, fvAttributes, modelsad_06);
		} else {
			testing_regr18 = testing_regr17;
		}
		
		if (!modelsad_07.toString().contains("No model built yet.")) {
			testing_regr18.setClass(fvAttributes.get(47));
			testing_regr19 = DataHandler.getInstance().applyRegressionModel(
					testing_regr18, fvAttributes, modelsad_07);
		} else {
			testing_regr19 = testing_regr18;
		}
		
		if (!modelsad_08.toString().contains("No model built yet.")) {
			testing_regr19.setClass(fvAttributes.get(48));
			testing_regr20 = DataHandler.getInstance().applyRegressionModel(
					testing_regr19, fvAttributes, modelsad_08);
		} else {
			testing_regr20 = testing_regr19;
		}
		
		if (!modelsnad_01.toString().contains("No model built yet.")) {
			testing_regr20.setClass(fvAttributes.get(49));
			testing_regr21 = DataHandler.getInstance().applyRegressionModel(
					testing_regr20, fvAttributes, modelsnad_01);
		} else {
			testing_regr21 = testing_regr20;
		}
		
		if (!modelsnad_02.toString().contains("No model built yet.")) {
			testing_regr21.setClass(fvAttributes.get(50));
			testing_regr22 = DataHandler.getInstance().applyRegressionModel(
					testing_regr21, fvAttributes, modelsnad_02);
		} else {
			testing_regr22 = testing_regr21;
		}
		
		if (!modelsnad_03.toString().contains("No model built yet.")) {
			testing_regr22.setClass(fvAttributes.get(51));
			testing_regr23 = DataHandler.getInstance().applyRegressionModel(
					testing_regr22, fvAttributes, modelsnad_03);
		} else {
			testing_regr23 = testing_regr22;
		}
		
		if (!modelsnad_04.toString().contains("No model built yet.")) {
			testing_regr23.setClass(fvAttributes.get(52));
			testing_regr24 = DataHandler.getInstance().applyRegressionModel(
					testing_regr23, fvAttributes, modelsnad_04);
		} else {
			testing_regr24 = testing_regr23;
		}
		
		if (!modelsnad_05.toString().contains("No model built yet.")) {
			testing_regr24.setClass(fvAttributes.get(53));
			testing_regr25 = DataHandler.getInstance().applyRegressionModel(
					testing_regr24, fvAttributes, modelsnad_05);
		} else {
			testing_regr25 = testing_regr24;
		}
		
		if (!modelsnad_06.toString().contains("No model built yet.")) {
			testing_regr25.setClass(fvAttributes.get(54));
			testing_regr26 = DataHandler.getInstance().applyRegressionModel(
					testing_regr25, fvAttributes, modelsnad_06);
		} else {
			testing_regr26 = testing_regr25;
		}
		
		if (!modelsnad_07.toString().contains("No model built yet.")) {
			testing_regr26.setClass(fvAttributes.get(55));
			testing_regr27 = DataHandler.getInstance().applyRegressionModel(
					testing_regr26, fvAttributes, modelsnad_07);
		} else {
			testing_regr27 = testing_regr26;
		}
		
		if (!modelsnad_08.toString().contains("No model built yet.")) {
			testing_regr27.setClass(fvAttributes.get(56));
			testing_regr28 = DataHandler.getInstance().applyRegressionModel(
					testing_regr27, fvAttributes, modelsnad_08);
		} else {
			testing_regr28 = testing_regr27;
		}
		
		if (!modelbf_01.toString().contains("No model built yet.")) {
			testing_regr28.setClass(fvAttributes.get(19));
			testing_regr29 = DataHandler.getInstance().applyRegressionModel(
					testing_regr28, fvAttributes, modelbf_01);
		} else {
			testing_regr29 = testing_regr28;
		}
		
		if (!modelbf_02.toString().contains("No model built yet.")) {
			testing_regr29.setClass(fvAttributes.get(20));
			testing_regr30 = DataHandler.getInstance().applyRegressionModel(
					testing_regr29, fvAttributes, modelbf_02);
		} else {
			testing_regr30 = testing_regr29;
		}
		
		if (!modelbf_03.toString().contains("No model built yet.")) {
			testing_regr30.setClass(fvAttributes.get(21));
			testing_regr31 = DataHandler.getInstance().applyRegressionModel(
					testing_regr30, fvAttributes, modelbf_03);
		} else {
			testing_regr31 = testing_regr30;
		}
		
		if (!modelbf_04.toString().contains("No model built yet.")) {
			testing_regr31.setClass(fvAttributes.get(22));
			testing_regr32 = DataHandler.getInstance().applyRegressionModel(
					testing_regr31, fvAttributes, modelbf_04);
		} else {
			testing_regr32 = testing_regr31;
		}
		
		if (!modelbf_05.toString().contains("No model built yet.")) {
			testing_regr32.setClass(fvAttributes.get(23));
			testing_regr33 = DataHandler.getInstance().applyRegressionModel(
					testing_regr32, fvAttributes, modelbf_05);
		} else {
			testing_regr33 = testing_regr32;
		}
		
		if (!modelbf_06.toString().contains("No model built yet.")) {
			testing_regr33.setClass(fvAttributes.get(24));
			testing_regr34 = DataHandler.getInstance().applyRegressionModel(
					testing_regr33, fvAttributes, modelbf_06);
		} else {
			testing_regr34 = testing_regr33;
		}
		
		if (!modelbf_07.toString().contains("No model built yet.")) {
			testing_regr34.setClass(fvAttributes.get(25));
			testing_regr35 = DataHandler.getInstance().applyRegressionModel(
					testing_regr34, fvAttributes, modelbf_07);
		} else {
			testing_regr35 = testing_regr34;
		}
		
		if (!modelbf_08.toString().contains("No model built yet.")) {
			testing_regr35.setClass(fvAttributes.get(26));
			testing_regr36 = DataHandler.getInstance().applyRegressionModel(
					testing_regr35, fvAttributes, modelbf_08);
		} else {
			testing_regr36 = testing_regr35;
		}
		
		if (!modelbf_09.toString().contains("No model built yet.")) {
			testing_regr36.setClass(fvAttributes.get(27));
			testing_regr37 = DataHandler.getInstance().applyRegressionModel(
					testing_regr36, fvAttributes, modelbf_09);
		} else {
			testing_regr37 = testing_regr36;
		}
		
		if (!modelbf_10.toString().contains("No model built yet.")) {
			testing_regr37.setClass(fvAttributes.get(28));
			testing_regr38 = DataHandler.getInstance().applyRegressionModel(
					testing_regr37, fvAttributes, modelbf_10);
		} else {
			testing_regr38 = testing_regr37;
		}
		
		if (!modelbf_11.toString().contains("No model built yet.")) {
			testing_regr38.setClass(fvAttributes.get(29));
			testing_regr39 = DataHandler.getInstance().applyRegressionModel(
					testing_regr38, fvAttributes, modelbf_11);
		} else {
			testing_regr39 = testing_regr38;
		}
		
		if (!modelbf_12.toString().contains("No model built yet.")) {
			testing_regr39.setClass(fvAttributes.get(30));
			testing_regr40 = DataHandler.getInstance().applyRegressionModel(
					testing_regr39, fvAttributes, modelbf_12);
		} else {
			testing_regr40 = testing_regr39;
		}
		
		if (!modelbf_13.toString().contains("No model built yet.")) {
			testing_regr40.setClass(fvAttributes.get(31));
			testing_regr41 = DataHandler.getInstance().applyRegressionModel(
					testing_regr40, fvAttributes, modelbf_13);
		} else {
			testing_regr41 = testing_regr40;
		}
		
		if (!modelbf_14.toString().contains("No model built yet.")) {
			testing_regr41.setClass(fvAttributes.get(32));
			testing_regr42 = DataHandler.getInstance().applyRegressionModel(
					testing_regr41, fvAttributes, modelbf_14);
		} else {
			testing_regr42 = testing_regr41;
		}
		
		if (!modelbf_15.toString().contains("No model built yet.")) {
			testing_regr42.setClass(fvAttributes.get(33));
			testing_regr43 = DataHandler.getInstance().applyRegressionModel(
					testing_regr42, fvAttributes, modelbf_15);
		} else {
			testing_regr43 = testing_regr42;
		}
		
		if (!modelbf_16.toString().contains("No model built yet.")) {
			testing_regr43.setClass(fvAttributes.get(34));
			testing_regr44 = DataHandler.getInstance().applyRegressionModel(
					testing_regr43, fvAttributes, modelbf_16);
		} else {
			testing_regr44 = testing_regr43;
		}
		
		if (!modelbf_17.toString().contains("No model built yet.")) {
			testing_regr44.setClass(fvAttributes.get(35));
			testing_regr45 = DataHandler.getInstance().applyRegressionModel(
					testing_regr44, fvAttributes, modelbf_17);
		} else {
			testing_regr45 = testing_regr44;
		}
		
		if (!modelbf_18.toString().contains("No model built yet.")) {
			testing_regr45.setClass(fvAttributes.get(36));
			testing_regr46 = DataHandler.getInstance().applyRegressionModel(
					testing_regr45, fvAttributes, modelbf_18);
		} else {
			testing_regr46 = testing_regr45;
		}
		
		if (!modelbf_19.toString().contains("No model built yet.")) {
			testing_regr46.setClass(fvAttributes.get(37));
			testing_regr47 = DataHandler.getInstance().applyRegressionModel(
					testing_regr46, fvAttributes, modelbf_19);
		} else {
			testing_regr47 = testing_regr46;
		}
		
		if (!modelbf_20.toString().contains("No model built yet.")) {
			testing_regr47.setClass(fvAttributes.get(38));
			testing_regr48 = DataHandler.getInstance().applyRegressionModel(
					testing_regr47, fvAttributes, modelbf_20);
		} else {
			testing_regr48 = testing_regr47;
		}
		
		if (!modelbf_21.toString().contains("No model built yet.")) {
			testing_regr48.setClass(fvAttributes.get(39));
			testing_regr49 = DataHandler.getInstance().applyRegressionModel(
					testing_regr48, fvAttributes, modelbf_21);
		} else {
			testing_regr49 = testing_regr48;
		}
		
		if (!modelbf_22.toString().contains("No model built yet.")) {
			testing_regr49.setClass(fvAttributes.get(40));
			testing_regr50 = DataHandler.getInstance().applyRegressionModel(
					testing_regr49, fvAttributes, modelbf_22);
		} else {
			testing_regr50 = testing_regr49;
		}
		
		if (!modelajpgbag_01.toString().contains("No model built yet.")) {
			testing_regr50.setClass(fvAttributes.get(57));
			testing_regr51 = DataHandler.getInstance().applyRegressionModel(
					testing_regr50, fvAttributes, modelajpgbag_01);
		} else {
			testing_regr51 = testing_regr50;
		}
		
		if (!modelajpgbag_02.toString().contains("No model built yet.")) {
			testing_regr51.setClass(fvAttributes.get(58));
			testing_regr52 = DataHandler.getInstance().applyRegressionModel(
					testing_regr51, fvAttributes, modelajpgbag_02);
		} else {
			testing_regr52 = testing_regr51;
		}
		
		if (!modelajpgbag_03.toString().contains("No model built yet.")) {
			testing_regr52.setClass(fvAttributes.get(59));
			testing_regr53 = DataHandler.getInstance().applyRegressionModel(
					testing_regr52, fvAttributes, modelajpgbag_03);
		} else {
			testing_regr53 = testing_regr52;
		}
		
		if (!modelajpgbag_04.toString().contains("No model built yet.")) {
			testing_regr53.setClass(fvAttributes.get(60));
			testing_regr54 = DataHandler.getInstance().applyRegressionModel(
					testing_regr53, fvAttributes, modelajpgbag_04);
		} else {
			testing_regr54 = testing_regr53;
		}
		
		if (!modelajpgbag_05.toString().contains("No model built yet.")) {
			testing_regr54.setClass(fvAttributes.get(61));
			testing_regr55 = DataHandler.getInstance().applyRegressionModel(
					testing_regr54, fvAttributes, modelajpgbag_05);
		} else {
			testing_regr55 = testing_regr54;
		}
		
		if (!modelajpgbag_06.toString().contains("No model built yet.")) {
			testing_regr55.setClass(fvAttributes.get(62));
			testing_regr56 = DataHandler.getInstance().applyRegressionModel(
					testing_regr55, fvAttributes, modelajpgbag_06);
		} else {
			testing_regr56 = testing_regr55;
		}
		
		if (!modelajpgbag_07.toString().contains("No model built yet.")) {
			testing_regr56.setClass(fvAttributes.get(63));
			testing_regr57 = DataHandler.getInstance().applyRegressionModel(
					testing_regr56, fvAttributes, modelajpgbag_07);
		} else {
			testing_regr57 = testing_regr56;
		}
		
		if (!modelajpgbag_08.toString().contains("No model built yet.")) {
			testing_regr57.setClass(fvAttributes.get(64));
			testing_regr58 = DataHandler.getInstance().applyRegressionModel(
					testing_regr57, fvAttributes, modelajpgbag_08);
		} else {
			testing_regr58 = testing_regr57;
		}
		
		if (!modelajpgbag_09.toString().contains("No model built yet.")) {
			testing_regr58.setClass(fvAttributes.get(65));
			testing_regr59 = DataHandler.getInstance().applyRegressionModel(
					testing_regr58, fvAttributes, modelajpgbag_09);
		} else {
			testing_regr59 = testing_regr58;
		}
		
		if (!modelajpgbag_10.toString().contains("No model built yet.")) {
			testing_regr59.setClass(fvAttributes.get(66));
			testing_regr60 = DataHandler.getInstance().applyRegressionModel(
					testing_regr59, fvAttributes, modelajpgbag_10);
		} else {
			testing_regr60 = testing_regr59;
		}
		
		if (!modelajpgbag_11.toString().contains("No model built yet.")) {
			testing_regr60.setClass(fvAttributes.get(67));
			testing_regr61 = DataHandler.getInstance().applyRegressionModel(
					testing_regr60, fvAttributes, modelajpgbag_11);
		} else {
			testing_regr61 = testing_regr60;
		}
		
		if (!modelajpgbag_12.toString().contains("No model built yet.")) {
			testing_regr61.setClass(fvAttributes.get(68));
			testing_regr62 = DataHandler.getInstance().applyRegressionModel(
					testing_regr61, fvAttributes, modelajpgbag_12);
		} else {
			testing_regr62 = testing_regr61;
		}
		
		if (!modelajpgbag_13.toString().contains("No model built yet.")) {
			testing_regr62.setClass(fvAttributes.get(69));
			testing_regr63 = DataHandler.getInstance().applyRegressionModel(
					testing_regr62, fvAttributes, modelajpgbag_13);
		} else {
			testing_regr63 = testing_regr62;
		}
		
		if (!modelajpgbag_14.toString().contains("No model built yet.")) {
			testing_regr63.setClass(fvAttributes.get(70));
			testing_regr64 = DataHandler.getInstance().applyRegressionModel(
					testing_regr63, fvAttributes, modelajpgbag_14);
		} else {
			testing_regr64 = testing_regr63;
		}
		
		if (!modelajpgbag_15.toString().contains("No model built yet.")) {
			testing_regr64.setClass(fvAttributes.get(71));
			testing_regr65 = DataHandler.getInstance().applyRegressionModel(
					testing_regr64, fvAttributes, modelajpgbag_15);
		} else {
			testing_regr65 = testing_regr64;
		}
		
		if (!modelajpgbag_16.toString().contains("No model built yet.")) {
			testing_regr65.setClass(fvAttributes.get(72));
			testing_regr66 = DataHandler.getInstance().applyRegressionModel(
					testing_regr65, fvAttributes, modelajpgbag_16);
		} else {
			testing_regr66 = testing_regr65;
		}
		
		if (!modelnajpgbag_01.toString().contains("No model built yet.")) {
			testing_regr66.setClass(fvAttributes.get(73));
			testing_regr67 = DataHandler.getInstance().applyRegressionModel(
					testing_regr66, fvAttributes, modelnajpgbag_01);
		} else {
			testing_regr67 = testing_regr66;
		}
		
		if (!modelnajpgbag_02.toString().contains("No model built yet.")) {
			testing_regr67.setClass(fvAttributes.get(74));
			testing_regr68 = DataHandler.getInstance().applyRegressionModel(
					testing_regr67, fvAttributes, modelnajpgbag_02);
		} else {
			testing_regr68 = testing_regr67;
		}
		
		if (!modelnajpgbag_03.toString().contains("No model built yet.")) {
			testing_regr68.setClass(fvAttributes.get(75));
			testing_regr69 = DataHandler.getInstance().applyRegressionModel(
					testing_regr68, fvAttributes, modelnajpgbag_03);
		} else {
			testing_regr69 = testing_regr68;
		}
		
		if (!modelnajpgbag_04.toString().contains("No model built yet.")) {
			testing_regr69.setClass(fvAttributes.get(76));
			testing_regr70 = DataHandler.getInstance().applyRegressionModel(
					testing_regr69, fvAttributes, modelnajpgbag_04);
		} else {
			testing_regr70 = testing_regr69;
		}
		
		if (!modelnajpgbag_05.toString().contains("No model built yet.")) {
			testing_regr70.setClass(fvAttributes.get(77));
			testing_regr71 = DataHandler.getInstance().applyRegressionModel(
					testing_regr70, fvAttributes, modelnajpgbag_05);
		} else {
			testing_regr71 = testing_regr70;
		}
		
		if (!modelnajpgbag_06.toString().contains("No model built yet.")) {
			testing_regr71.setClass(fvAttributes.get(78));
			testing_regr72 = DataHandler.getInstance().applyRegressionModel(
					testing_regr71, fvAttributes, modelnajpgbag_06);
		} else {
			testing_regr72 = testing_regr71;
		}
		
		if (!modelnajpgbag_07.toString().contains("No model built yet.")) {
			testing_regr72.setClass(fvAttributes.get(79));
			testing_regr73 = DataHandler.getInstance().applyRegressionModel(
					testing_regr72, fvAttributes, modelnajpgbag_07);
		} else {
			testing_regr73 = testing_regr72;
		}
		
		if (!modelnajpgbag_08.toString().contains("No model built yet.")) {
			testing_regr73.setClass(fvAttributes.get(80));
			testing_regr74 = DataHandler.getInstance().applyRegressionModel(
					testing_regr73, fvAttributes, modelnajpgbag_08);
		} else {
			testing_regr74 = testing_regr73;
		}
		
		if (!modelnajpgbag_09.toString().contains("No model built yet.")) {
			testing_regr74.setClass(fvAttributes.get(81));
			testing_regr75 = DataHandler.getInstance().applyRegressionModel(
					testing_regr74, fvAttributes, modelnajpgbag_09);
		} else {
			testing_regr75 = testing_regr74;
		}
		
		if (!modelnajpgbag_10.toString().contains("No model built yet.")) {
			testing_regr75.setClass(fvAttributes.get(82));
			testing_regr76 = DataHandler.getInstance().applyRegressionModel(
					testing_regr75, fvAttributes, modelnajpgbag_10);
		} else {
			testing_regr76 = testing_regr75;
		}
		
		if (!modelnajpgbag_11.toString().contains("No model built yet.")) {
			testing_regr76.setClass(fvAttributes.get(83));
			testing_regr77 = DataHandler.getInstance().applyRegressionModel(
					testing_regr76, fvAttributes, modelnajpgbag_11);
		} else {
			testing_regr77 = testing_regr76;
		}
		
		if (!modelnajpgbag_12.toString().contains("No model built yet.")) {
			testing_regr77.setClass(fvAttributes.get(84));
			testing_regr78 = DataHandler.getInstance().applyRegressionModel(
					testing_regr77, fvAttributes, modelnajpgbag_12);
		} else {
			testing_regr78 = testing_regr77;
		}
		
		if (!modelnajpgbag_13.toString().contains("No model built yet.")) {
			testing_regr78.setClass(fvAttributes.get(85));
			testing_regr79 = DataHandler.getInstance().applyRegressionModel(
					testing_regr78, fvAttributes, modelnajpgbag_13);
		} else {
			testing_regr79 = testing_regr78;
		}
		
		if (!modelnajpgbag_14.toString().contains("No model built yet.")) {
			testing_regr79.setClass(fvAttributes.get(86));
			testing_regr80 = DataHandler.getInstance().applyRegressionModel(
					testing_regr79, fvAttributes, modelnajpgbag_14);
		} else {
			testing_regr80 = testing_regr79;
		}
		
		if (!modelnajpgbag_15.toString().contains("No model built yet.")) {
			testing_regr80.setClass(fvAttributes.get(87));
			testing_regr81 = DataHandler.getInstance().applyRegressionModel(
					testing_regr80, fvAttributes, modelnajpgbag_15);
		} else {
			testing_regr81 = testing_regr80;
		}
		
		if (!modelnajpgbag_16.toString().contains("No model built yet.")) {
			testing_regr81.setClass(fvAttributes.get(88));
			testing_regr82 = DataHandler.getInstance().applyRegressionModel(
					testing_regr81, fvAttributes, modelnajpgbag_16);
		} else {
			testing_regr82 = testing_regr81;
		}
		// normalization
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(
				testing_regr82, fvAttributes.size() - 1, normFilter);

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
	 * Applies Linear Regression and normalization to the testing set according
	 * to the models and filters created before by the training set. It is the
	 * case of the Item type Instances.
	 * 
	 * @param testing
	 *            the Instances to be transformed
	 * @return the transformed testing set
	 * @throws Exception
	 */
	public Instances getTransformedTestingEnriched(Instances testing) throws Exception {

		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();
		Instances testing_regr = null, testing_regr2 = null, testing_regr3 = null, testing_regr4 = null, testing_regr5 = null, 
				testing_regr6 = null, testing_regr7 = null, testing_regr8 = null, testing_regr9 = null, testing_regr10 = null,
						testing_regr11 = null, testing_regr12 = null, testing_regr13 = null, testing_regr14 = null, testing_regr15 = null,
								testing_regr16 = null, testing_regr17 = null, testing_regr18 = null, testing_regr19 = null, testing_regr20 = null,
										 testing_regr21 = null, testing_regr22 = null, testing_regr23 = null, testing_regr24 = null, testing_regr25 = null, testing_regr26 = null,
												 testing_regr27 = null, testing_regr28 = null, testing_regr29 = null, testing_regr30 = null, testing_regr31 = null, testing_regr32 = null
														 , testing_regr33 = null, testing_regr34 = null, testing_regr35 = null, testing_regr36 = null, testing_regr37 = null, testing_regr38 = null
																 , testing_regr39 = null, testing_regr40 = null, testing_regr41 = null, testing_regr42 = null, testing_regr43 = null, testing_regr44 = null
																		 , testing_regr45 = null, testing_regr46 = null, testing_regr47 = null, testing_regr48 = null, testing_regr49 = null, testing_regr50 = null
		, testing_regr51 = null, testing_regr52 = null, testing_regr53 = null, testing_regr54 = null, testing_regr55 = null, testing_regr56 = null, testing_regr57 = null, testing_regr58 = null, testing_regr59 = null
				, testing_regr60 = null, testing_regr61 = null, testing_regr62 = null, testing_regr63 = null, testing_regr64 = null, testing_regr65 = null, testing_regr66 = null, testing_regr67 = null, testing_regr68 = null, testing_regr69 = null,
		testing_regr70 = null, testing_regr71 = null, testing_regr72 = null, testing_regr73 = null, testing_regr74 = null, testing_regr75 = null, testing_regr76 = null, testing_regr77 = null, testing_regr78 = null,
				 testing_regr79 = null, testing_regr80 = null, testing_regr81 = null, testing_regr82 = null;
		// regression
		if (!model.toString().contains("No model built yet.")) {
			testing.setClass(fvAttributes.get(22));
			testing_regr = DataHandler.getInstance().applyRegressionModel(
					testing, fvAttributes, model);
		} else {
			testing_regr = testing;
		}

		if (!model2.toString().contains("No model built yet.")) {
			testing_regr.setClass(fvAttributes.get(25));
			testing_regr2 = DataHandler.getInstance().applyRegressionModel(
					testing_regr, fvAttributes, model2);
		} else {
			testing_regr2 = testing_regr;
		}

		if (!model3.toString().contains("No model built yet.")) {
			testing_regr2.setClass(fvAttributes.get(30));
			testing_regr3 = DataHandler.getInstance().applyRegressionModel(
					testing_regr2, fvAttributes, model3);
		} else {
			testing_regr3 = testing_regr2;
		}

		if (!model4.toString().contains("No model built yet.")) {
			testing_regr3.setClass(fvAttributes.get(29));
			testing_regr4 = DataHandler.getInstance().applyRegressionModel(
					testing_regr3, fvAttributes, model4);
		} else {
			testing_regr4 = testing_regr3;
		}

		if (!model5.toString().contains("No model built yet.")) {
			testing_regr4.setClass(fvAttributes.get(14));
			testing_regr5 = DataHandler.getInstance().applyRegressionModel(
					testing_regr4, fvAttributes, model5);
		} else {
			testing_regr5 = testing_regr4;
		}

		if (!model6.toString().contains("No model built yet.")) {
			testing_regr5.setClass(fvAttributes.get(19));
			testing_regr6 = DataHandler.getInstance().applyRegressionModel(
					testing_regr5, fvAttributes, model6);
		} else {
			testing_regr6 = testing_regr5;
		}

		if (!model7.toString().contains("No model built yet.")) {
			testing_regr6.setClass(fvAttributes.get(26));
			testing_regr7 = DataHandler.getInstance().applyRegressionModel(
					testing_regr6, fvAttributes, model7);
		} else {
			testing_regr7 = testing_regr6;
		}

		if (!model8.toString().contains("No model built yet.")) {
			testing_regr7.setClass(fvAttributes.get(28));
			testing_regr8 = DataHandler.getInstance().applyRegressionModel(
					testing_regr7, fvAttributes, model8);
		} else {
			testing_regr8 = testing_regr7;
		}

		if (!model9.toString().contains("No model built yet.")) {
			testing_regr8.setClass(fvAttributes.get(27));
			testing_regr9 = DataHandler.getInstance().applyRegressionModel(
					testing_regr8, fvAttributes, model9);
		} else {
			testing_regr9 = testing_regr8;
		}
		
		if (!model10.toString().contains("No model built yet.")) {
			testing_regr9.setClass(fvAttributes.get(13));
			testing_regr10 = DataHandler.getInstance().applyRegressionModel(
					testing_regr9, fvAttributes, model10);
		} else {
			testing_regr10 = testing_regr9;
		}
		
		if (!model11.toString().contains("No model built yet.")) {
			testing_regr10.setClass(fvAttributes.get(31));
			testing_regr11 = DataHandler.getInstance().applyRegressionModel(
					testing_regr10, fvAttributes, model11);
		} else {
			testing_regr11 = testing_regr10;
		}
		
		if (!model12.toString().contains("No model built yet.")) {
			testing_regr11.setClass(fvAttributes.get(6));
			testing_regr12 = DataHandler.getInstance().applyRegressionModel(
					testing_regr11, fvAttributes, model12);
		} else {
			testing_regr12 = testing_regr11;
		}
		
		if (!modelsad_01.toString().contains("No model built yet.")) {
			testing_regr12.setClass(fvAttributes.get(54));
			testing_regr13 = DataHandler.getInstance().applyRegressionModel(
					testing_regr12, fvAttributes, modelsad_01);
		} else {
			testing_regr13 = testing_regr12;
		}
		
		if (!modelsad_02.toString().contains("No model built yet.")) {
			testing_regr13.setClass(fvAttributes.get(55));
			testing_regr14 = DataHandler.getInstance().applyRegressionModel(
					testing_regr13, fvAttributes, modelsad_02);
		} else {
			testing_regr14 = testing_regr13;
		}
		
		if (!modelsad_03.toString().contains("No model built yet.")) {
			testing_regr14.setClass(fvAttributes.get(56));
			testing_regr15 = DataHandler.getInstance().applyRegressionModel(
					testing_regr14, fvAttributes, modelsad_03);
		} else {
			testing_regr15 = testing_regr14;
		}
		
		if (!modelsad_04.toString().contains("No model built yet.")) {
			testing_regr15.setClass(fvAttributes.get(57));
			testing_regr16 = DataHandler.getInstance().applyRegressionModel(
					testing_regr15, fvAttributes, modelsad_04);
		} else {
			testing_regr16 = testing_regr15;
		}
		
		if (!modelsad_05.toString().contains("No model built yet.")) {
			testing_regr16.setClass(fvAttributes.get(58));
			testing_regr17 = DataHandler.getInstance().applyRegressionModel(
					testing_regr16, fvAttributes, modelsad_05);
		} else {
			testing_regr17 = testing_regr16;
		}
		
		if (!modelsad_06.toString().contains("No model built yet.")) {
			testing_regr17.setClass(fvAttributes.get(59));
			testing_regr18 = DataHandler.getInstance().applyRegressionModel(
					testing_regr17, fvAttributes, modelsad_06);
		} else {
			testing_regr18 = testing_regr17;
		}
		
		if (!modelsad_07.toString().contains("No model built yet.")) {
			testing_regr18.setClass(fvAttributes.get(60));
			testing_regr19 = DataHandler.getInstance().applyRegressionModel(
					testing_regr18, fvAttributes, modelsad_07);
		} else {
			testing_regr19 = testing_regr18;
		}
		
		if (!modelsad_08.toString().contains("No model built yet.")) {
			testing_regr19.setClass(fvAttributes.get(61));
			testing_regr20 = DataHandler.getInstance().applyRegressionModel(
					testing_regr19, fvAttributes, modelsad_08);
		} else {
			testing_regr20 = testing_regr19;
		}
		
		if (!modelsnad_01.toString().contains("No model built yet.")) {
			testing_regr20.setClass(fvAttributes.get(62));
			testing_regr21 = DataHandler.getInstance().applyRegressionModel(
					testing_regr20, fvAttributes, modelsnad_01);
		} else {
			testing_regr21 = testing_regr20;
		}
		
		if (!modelsnad_02.toString().contains("No model built yet.")) {
			testing_regr21.setClass(fvAttributes.get(63));
			testing_regr22 = DataHandler.getInstance().applyRegressionModel(
					testing_regr21, fvAttributes, modelsnad_02);
		} else {
			testing_regr22 = testing_regr21;
		}
		
		if (!modelsnad_03.toString().contains("No model built yet.")) {
			testing_regr22.setClass(fvAttributes.get(64));
			testing_regr23 = DataHandler.getInstance().applyRegressionModel(
					testing_regr22, fvAttributes, modelsnad_03);
		} else {
			testing_regr23 = testing_regr22;
		}
		
		if (!modelsnad_04.toString().contains("No model built yet.")) {
			testing_regr23.setClass(fvAttributes.get(65));
			testing_regr24 = DataHandler.getInstance().applyRegressionModel(
					testing_regr23, fvAttributes, modelsnad_04);
		} else {
			testing_regr24 = testing_regr23;
		}
		
		if (!modelsnad_05.toString().contains("No model built yet.")) {
			testing_regr24.setClass(fvAttributes.get(66));
			testing_regr25 = DataHandler.getInstance().applyRegressionModel(
					testing_regr24, fvAttributes, modelsnad_05);
		} else {
			testing_regr25 = testing_regr24;
		}
		
		if (!modelsnad_06.toString().contains("No model built yet.")) {
			testing_regr25.setClass(fvAttributes.get(67));
			testing_regr26 = DataHandler.getInstance().applyRegressionModel(
					testing_regr25, fvAttributes, modelsnad_06);
		} else {
			testing_regr26 = testing_regr25;
		}
		
		if (!modelsnad_07.toString().contains("No model built yet.")) {
			testing_regr26.setClass(fvAttributes.get(68));
			testing_regr27 = DataHandler.getInstance().applyRegressionModel(
					testing_regr26, fvAttributes, modelsnad_07);
		} else {
			testing_regr27 = testing_regr26;
		}
		
		if (!modelsnad_08.toString().contains("No model built yet.")) {
			testing_regr27.setClass(fvAttributes.get(69));
			testing_regr28 = DataHandler.getInstance().applyRegressionModel(
					testing_regr27, fvAttributes, modelsnad_08);
		} else {
			testing_regr28 = testing_regr27;
		}
		
		if (!modelbf_01.toString().contains("No model built yet.")) {
			testing_regr28.setClass(fvAttributes.get(32));
			testing_regr29 = DataHandler.getInstance().applyRegressionModel(
					testing_regr28, fvAttributes, modelbf_01);
		} else {
			testing_regr29 = testing_regr28;
		}
		
		if (!modelbf_02.toString().contains("No model built yet.")) {
			testing_regr29.setClass(fvAttributes.get(33));
			testing_regr30 = DataHandler.getInstance().applyRegressionModel(
					testing_regr29, fvAttributes, modelbf_02);
		} else {
			testing_regr30 = testing_regr29;
		}
		
		if (!modelbf_03.toString().contains("No model built yet.")) {
			testing_regr30.setClass(fvAttributes.get(34));
			testing_regr31 = DataHandler.getInstance().applyRegressionModel(
					testing_regr30, fvAttributes, modelbf_03);
		} else {
			testing_regr31 = testing_regr30;
		}
		
		if (!modelbf_04.toString().contains("No model built yet.")) {
			testing_regr31.setClass(fvAttributes.get(35));
			testing_regr32 = DataHandler.getInstance().applyRegressionModel(
					testing_regr31, fvAttributes, modelbf_04);
		} else {
			testing_regr32 = testing_regr31;
		}
		
		if (!modelbf_05.toString().contains("No model built yet.")) {
			testing_regr32.setClass(fvAttributes.get(36));
			testing_regr33 = DataHandler.getInstance().applyRegressionModel(
					testing_regr32, fvAttributes, modelbf_05);
		} else {
			testing_regr33 = testing_regr32;
		}
		
		if (!modelbf_06.toString().contains("No model built yet.")) {
			testing_regr33.setClass(fvAttributes.get(37));
			testing_regr34 = DataHandler.getInstance().applyRegressionModel(
					testing_regr33, fvAttributes, modelbf_06);
		} else {
			testing_regr34 = testing_regr33;
		}
		
		if (!modelbf_07.toString().contains("No model built yet.")) {
			testing_regr34.setClass(fvAttributes.get(38));
			testing_regr35 = DataHandler.getInstance().applyRegressionModel(
					testing_regr34, fvAttributes, modelbf_07);
		} else {
			testing_regr35 = testing_regr34;
		}
		
		if (!modelbf_08.toString().contains("No model built yet.")) {
			testing_regr35.setClass(fvAttributes.get(39));
			testing_regr36 = DataHandler.getInstance().applyRegressionModel(
					testing_regr35, fvAttributes, modelbf_08);
		} else {
			testing_regr36 = testing_regr35;
		}
		
		if (!modelbf_09.toString().contains("No model built yet.")) {
			testing_regr36.setClass(fvAttributes.get(40));
			testing_regr37 = DataHandler.getInstance().applyRegressionModel(
					testing_regr36, fvAttributes, modelbf_09);
		} else {
			testing_regr37 = testing_regr36;
		}
		
		if (!modelbf_10.toString().contains("No model built yet.")) {
			testing_regr37.setClass(fvAttributes.get(41));
			testing_regr38 = DataHandler.getInstance().applyRegressionModel(
					testing_regr37, fvAttributes, modelbf_10);
		} else {
			testing_regr38 = testing_regr37;
		}
		
		if (!modelbf_11.toString().contains("No model built yet.")) {
			testing_regr38.setClass(fvAttributes.get(42));
			testing_regr39 = DataHandler.getInstance().applyRegressionModel(
					testing_regr38, fvAttributes, modelbf_11);
		} else {
			testing_regr39 = testing_regr38;
		}
		
		if (!modelbf_12.toString().contains("No model built yet.")) {
			testing_regr39.setClass(fvAttributes.get(43));
			testing_regr40 = DataHandler.getInstance().applyRegressionModel(
					testing_regr39, fvAttributes, modelbf_12);
		} else {
			testing_regr40 = testing_regr39;
		}
		
		if (!modelbf_13.toString().contains("No model built yet.")) {
			testing_regr40.setClass(fvAttributes.get(44));
			testing_regr41 = DataHandler.getInstance().applyRegressionModel(
					testing_regr40, fvAttributes, modelbf_13);
		} else {
			testing_regr41 = testing_regr40;
		}
		
		if (!modelbf_14.toString().contains("No model built yet.")) {
			testing_regr41.setClass(fvAttributes.get(45));
			testing_regr42 = DataHandler.getInstance().applyRegressionModel(
					testing_regr41, fvAttributes, modelbf_14);
		} else {
			testing_regr42 = testing_regr41;
		}
		
		if (!modelbf_15.toString().contains("No model built yet.")) {
			testing_regr42.setClass(fvAttributes.get(46));
			testing_regr43 = DataHandler.getInstance().applyRegressionModel(
					testing_regr42, fvAttributes, modelbf_15);
		} else {
			testing_regr43 = testing_regr42;
		}
		
		if (!modelbf_16.toString().contains("No model built yet.")) {
			testing_regr43.setClass(fvAttributes.get(47));
			testing_regr44 = DataHandler.getInstance().applyRegressionModel(
					testing_regr43, fvAttributes, modelbf_16);
		} else {
			testing_regr44 = testing_regr43;
		}
		
		if (!modelbf_17.toString().contains("No model built yet.")) {
			testing_regr44.setClass(fvAttributes.get(48));
			testing_regr45 = DataHandler.getInstance().applyRegressionModel(
					testing_regr44, fvAttributes, modelbf_17);
		} else {
			testing_regr45 = testing_regr44;
		}
		
		if (!modelbf_18.toString().contains("No model built yet.")) {
			testing_regr45.setClass(fvAttributes.get(49));
			testing_regr46 = DataHandler.getInstance().applyRegressionModel(
					testing_regr45, fvAttributes, modelbf_18);
		} else {
			testing_regr46 = testing_regr45;
		}
		
		if (!modelbf_19.toString().contains("No model built yet.")) {
			testing_regr46.setClass(fvAttributes.get(50));
			testing_regr47 = DataHandler.getInstance().applyRegressionModel(
					testing_regr46, fvAttributes, modelbf_19);
		} else {
			testing_regr47 = testing_regr46;
		}
		
		if (!modelbf_20.toString().contains("No model built yet.")) {
			testing_regr47.setClass(fvAttributes.get(51));
			testing_regr48 = DataHandler.getInstance().applyRegressionModel(
					testing_regr47, fvAttributes, modelbf_20);
		} else {
			testing_regr48 = testing_regr47;
		}
		
		if (!modelbf_21.toString().contains("No model built yet.")) {
			testing_regr48.setClass(fvAttributes.get(52));
			testing_regr49 = DataHandler.getInstance().applyRegressionModel(
					testing_regr48, fvAttributes, modelbf_21);
		} else {
			testing_regr49 = testing_regr48;
		}
		
		if (!modelbf_22.toString().contains("No model built yet.")) {
			testing_regr49.setClass(fvAttributes.get(53));
			testing_regr50 = DataHandler.getInstance().applyRegressionModel(
					testing_regr49, fvAttributes, modelbf_22);
		} else {
			testing_regr50 = testing_regr49;
		}
		
		if (!modelajpgbag_01.toString().contains("No model built yet.")) {
			testing_regr50.setClass(fvAttributes.get(70));
			testing_regr51 = DataHandler.getInstance().applyRegressionModel(
					testing_regr50, fvAttributes, modelajpgbag_01);
		} else {
			testing_regr51 = testing_regr50;
		}
		
		if (!modelajpgbag_02.toString().contains("No model built yet.")) {
			testing_regr51.setClass(fvAttributes.get(71));
			testing_regr52 = DataHandler.getInstance().applyRegressionModel(
					testing_regr51, fvAttributes, modelajpgbag_02);
		} else {
			testing_regr52 = testing_regr51;
		}
		
		if (!modelajpgbag_03.toString().contains("No model built yet.")) {
			testing_regr52.setClass(fvAttributes.get(72));
			testing_regr53 = DataHandler.getInstance().applyRegressionModel(
					testing_regr52, fvAttributes, modelajpgbag_03);
		} else {
			testing_regr53 = testing_regr52;
		}
		
		if (!modelajpgbag_04.toString().contains("No model built yet.")) {
			testing_regr53.setClass(fvAttributes.get(73));
			testing_regr54 = DataHandler.getInstance().applyRegressionModel(
					testing_regr53, fvAttributes, modelajpgbag_04);
		} else {
			testing_regr54 = testing_regr53;
		}
		
		if (!modelajpgbag_05.toString().contains("No model built yet.")) {
			testing_regr54.setClass(fvAttributes.get(74));
			testing_regr55 = DataHandler.getInstance().applyRegressionModel(
					testing_regr54, fvAttributes, modelajpgbag_05);
		} else {
			testing_regr55 = testing_regr54;
		}
		
		if (!modelajpgbag_06.toString().contains("No model built yet.")) {
			testing_regr55.setClass(fvAttributes.get(75));
			testing_regr56 = DataHandler.getInstance().applyRegressionModel(
					testing_regr55, fvAttributes, modelajpgbag_06);
		} else {
			testing_regr56 = testing_regr55;
		}
		
		if (!modelajpgbag_07.toString().contains("No model built yet.")) {
			testing_regr56.setClass(fvAttributes.get(76));
			testing_regr57 = DataHandler.getInstance().applyRegressionModel(
					testing_regr56, fvAttributes, modelajpgbag_07);
		} else {
			testing_regr57 = testing_regr56;
		}
		
		if (!modelajpgbag_08.toString().contains("No model built yet.")) {
			testing_regr57.setClass(fvAttributes.get(77));
			testing_regr58 = DataHandler.getInstance().applyRegressionModel(
					testing_regr57, fvAttributes, modelajpgbag_08);
		} else {
			testing_regr58 = testing_regr57;
		}
		
		if (!modelajpgbag_09.toString().contains("No model built yet.")) {
			testing_regr58.setClass(fvAttributes.get(78));
			testing_regr59 = DataHandler.getInstance().applyRegressionModel(
					testing_regr58, fvAttributes, modelajpgbag_09);
		} else {
			testing_regr59 = testing_regr58;
		}
		
		if (!modelajpgbag_10.toString().contains("No model built yet.")) {
			testing_regr59.setClass(fvAttributes.get(79));
			testing_regr60 = DataHandler.getInstance().applyRegressionModel(
					testing_regr59, fvAttributes, modelajpgbag_10);
		} else {
			testing_regr60 = testing_regr59;
		}
		
		if (!modelajpgbag_11.toString().contains("No model built yet.")) {
			testing_regr60.setClass(fvAttributes.get(80));
			testing_regr61 = DataHandler.getInstance().applyRegressionModel(
					testing_regr60, fvAttributes, modelajpgbag_11);
		} else {
			testing_regr61 = testing_regr60;
		}
		
		if (!modelajpgbag_12.toString().contains("No model built yet.")) {
			testing_regr61.setClass(fvAttributes.get(81));
			testing_regr62 = DataHandler.getInstance().applyRegressionModel(
					testing_regr61, fvAttributes, modelajpgbag_12);
		} else {
			testing_regr62 = testing_regr61;
		}
		
		if (!modelajpgbag_13.toString().contains("No model built yet.")) {
			testing_regr62.setClass(fvAttributes.get(82));
			testing_regr63 = DataHandler.getInstance().applyRegressionModel(
					testing_regr62, fvAttributes, modelajpgbag_13);
		} else {
			testing_regr63 = testing_regr62;
		}
		
		if (!modelajpgbag_14.toString().contains("No model built yet.")) {
			testing_regr63.setClass(fvAttributes.get(83));
			testing_regr64 = DataHandler.getInstance().applyRegressionModel(
					testing_regr63, fvAttributes, modelajpgbag_14);
		} else {
			testing_regr64 = testing_regr63;
		}
		
		if (!modelajpgbag_15.toString().contains("No model built yet.")) {
			testing_regr64.setClass(fvAttributes.get(84));
			testing_regr65 = DataHandler.getInstance().applyRegressionModel(
					testing_regr64, fvAttributes, modelajpgbag_15);
		} else {
			testing_regr65 = testing_regr64;
		}
		
		if (!modelajpgbag_16.toString().contains("No model built yet.")) {
			testing_regr65.setClass(fvAttributes.get(85));
			testing_regr66 = DataHandler.getInstance().applyRegressionModel(
					testing_regr65, fvAttributes, modelajpgbag_16);
		} else {
			testing_regr66 = testing_regr65;
		}
		
		if (!modelnajpgbag_01.toString().contains("No model built yet.")) {
			testing_regr66.setClass(fvAttributes.get(86));
			testing_regr67 = DataHandler.getInstance().applyRegressionModel(
					testing_regr66, fvAttributes, modelnajpgbag_01);
		} else {
			testing_regr67 = testing_regr66;
		}
		
		if (!modelnajpgbag_02.toString().contains("No model built yet.")) {
			testing_regr67.setClass(fvAttributes.get(87));
			testing_regr68 = DataHandler.getInstance().applyRegressionModel(
					testing_regr67, fvAttributes, modelnajpgbag_02);
		} else {
			testing_regr68 = testing_regr67;
		}
		
		if (!modelnajpgbag_03.toString().contains("No model built yet.")) {
			testing_regr68.setClass(fvAttributes.get(88));
			testing_regr69 = DataHandler.getInstance().applyRegressionModel(
					testing_regr68, fvAttributes, modelnajpgbag_03);
		} else {
			testing_regr69 = testing_regr68;
		}
		
		if (!modelnajpgbag_04.toString().contains("No model built yet.")) {
			testing_regr69.setClass(fvAttributes.get(89));
			testing_regr70 = DataHandler.getInstance().applyRegressionModel(
					testing_regr69, fvAttributes, modelnajpgbag_04);
		} else {
			testing_regr70 = testing_regr69;
		}
		
		if (!modelnajpgbag_05.toString().contains("No model built yet.")) {
			testing_regr70.setClass(fvAttributes.get(90));
			testing_regr71 = DataHandler.getInstance().applyRegressionModel(
					testing_regr70, fvAttributes, modelnajpgbag_05);
		} else {
			testing_regr71 = testing_regr70;
		}
		
		if (!modelnajpgbag_06.toString().contains("No model built yet.")) {
			testing_regr71.setClass(fvAttributes.get(91));
			testing_regr72 = DataHandler.getInstance().applyRegressionModel(
					testing_regr71, fvAttributes, modelnajpgbag_06);
		} else {
			testing_regr72 = testing_regr71;
		}
		
		if (!modelnajpgbag_07.toString().contains("No model built yet.")) {
			testing_regr72.setClass(fvAttributes.get(92));
			testing_regr73 = DataHandler.getInstance().applyRegressionModel(
					testing_regr72, fvAttributes, modelnajpgbag_07);
		} else {
			testing_regr73 = testing_regr72;
		}
		
		if (!modelnajpgbag_08.toString().contains("No model built yet.")) {
			testing_regr73.setClass(fvAttributes.get(93));
			testing_regr74 = DataHandler.getInstance().applyRegressionModel(
					testing_regr73, fvAttributes, modelnajpgbag_08);
		} else {
			testing_regr74 = testing_regr73;
		}
		
		if (!modelnajpgbag_09.toString().contains("No model built yet.")) {
			testing_regr74.setClass(fvAttributes.get(94));
			testing_regr75 = DataHandler.getInstance().applyRegressionModel(
					testing_regr74, fvAttributes, modelnajpgbag_09);
		} else {
			testing_regr75 = testing_regr74;
		}
		
		if (!modelnajpgbag_10.toString().contains("No model built yet.")) {
			testing_regr75.setClass(fvAttributes.get(95));
			testing_regr76 = DataHandler.getInstance().applyRegressionModel(
					testing_regr75, fvAttributes, modelnajpgbag_10);
		} else {
			testing_regr76 = testing_regr75;
		}
		
		if (!modelnajpgbag_11.toString().contains("No model built yet.")) {
			testing_regr76.setClass(fvAttributes.get(96));
			testing_regr77 = DataHandler.getInstance().applyRegressionModel(
					testing_regr76, fvAttributes, modelnajpgbag_11);
		} else {
			testing_regr77 = testing_regr76;
		}
		
		if (!modelnajpgbag_12.toString().contains("No model built yet.")) {
			testing_regr77.setClass(fvAttributes.get(97));
			testing_regr78 = DataHandler.getInstance().applyRegressionModel(
					testing_regr77, fvAttributes, modelnajpgbag_12);
		} else {
			testing_regr78 = testing_regr77;
		}
		
		if (!modelnajpgbag_13.toString().contains("No model built yet.")) {
			testing_regr78.setClass(fvAttributes.get(98));
			testing_regr79 = DataHandler.getInstance().applyRegressionModel(
					testing_regr78, fvAttributes, modelnajpgbag_13);
		} else {
			testing_regr79 = testing_regr78;
		}
		
		if (!modelnajpgbag_14.toString().contains("No model built yet.")) {
			testing_regr79.setClass(fvAttributes.get(99));
			testing_regr80 = DataHandler.getInstance().applyRegressionModel(
					testing_regr79, fvAttributes, modelnajpgbag_14);
		} else {
			testing_regr80 = testing_regr79;
		}
		
		if (!modelnajpgbag_15.toString().contains("No model built yet.")) {
			testing_regr80.setClass(fvAttributes.get(100));
			testing_regr81 = DataHandler.getInstance().applyRegressionModel(
					testing_regr80, fvAttributes, modelnajpgbag_15);
		} else {
			testing_regr81 = testing_regr80;
		}
		
		if (!modelnajpgbag_16.toString().contains("No model built yet.")) {
			testing_regr81.setClass(fvAttributes.get(101));
			testing_regr82 = DataHandler.getInstance().applyRegressionModel(
					testing_regr81, fvAttributes, modelnajpgbag_16);
		} else {
			testing_regr82 = testing_regr81;
		}
		
		// normalization
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(
				testing_regr82, fvAttributes.size() - 1, normFilter);
		
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
	 * Applies Linear Regression and normalization to the testing set according
	 * to the models and filters created before by the training set. It is the
	 * case of the Item type Instances.
	 * 
	 * @param testing
	 *            the Instances to be transformed
	 * @return the transformed testing set
	 * @throws Exception
	 */
	public Instances getTransformedTestingTimeFeats(Instances testing)
			throws Exception {

		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();
		Instances testing_regr = null, testing_regr2 = null, testing_regr3 = null, testing_regr4 = null, testing_regr5 = null, testing_regr6 = null, testing_regr7 = null, testing_regr8 = null;

		// regression
		if (!model.toString().contains("No model built yet.")) {
			testing.setClass(fvAttributes.get(23));
			testing_regr = DataHandler.getInstance().applyRegressionModel(
					testing, fvAttributes, model);
		} else {
			testing_regr = testing;
		}

		if (!model2.toString().contains("No model built yet.")) {
			testing_regr.setClass(fvAttributes.get(18));
			testing_regr2 = DataHandler.getInstance().applyRegressionModel(
					testing_regr, fvAttributes, model2);
		} else {
			testing_regr2 = testing_regr;
		}

		if (!model3.toString().contains("No model built yet.")) {
			testing_regr2.setClass(fvAttributes.get(13));
			testing_regr3 = DataHandler.getInstance().applyRegressionModel(
					testing_regr2, fvAttributes, model3);
		} else {
			testing_regr3 = testing_regr2;
		}

		if (!model4.toString().contains("No model built yet.")) {
			testing_regr3.setClass(fvAttributes.get(14));
			testing_regr4 = DataHandler.getInstance().applyRegressionModel(
					testing_regr3, fvAttributes, model4);
		} else {
			testing_regr4 = testing_regr3;
		}

		if (!model5.toString().contains("No model built yet.")) {
			testing_regr4.setClass(fvAttributes.get(6));
			testing_regr5 = DataHandler.getInstance().applyRegressionModel(
					testing_regr4, fvAttributes, model5);
		} else {
			testing_regr5 = testing_regr4;
		}

		if (!model6.toString().contains("No model built yet.")) {
			testing_regr5.setClass(fvAttributes.get(24));
			testing_regr6 = DataHandler.getInstance().applyRegressionModel(
					testing_regr5, fvAttributes, model6);
		} else {
			testing_regr6 = testing_regr5;
		}

		if (!model7.toString().contains("No model built yet.")) {
			testing_regr6.setClass(fvAttributes.get(25));
			testing_regr7 = DataHandler.getInstance().applyRegressionModel(
					testing_regr6, fvAttributes, model7);
		} else {
			testing_regr7 = testing_regr6;
		}

		// normalization
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(
				testing_regr7, fvAttributes.size() - 1, normFilter);
		// testingSet_normed = getTrimmedInstances(testingSet_normed);

		return testingSet_normed;
	}

	public Instances getTransformedTestingAttSelection1(Instances testing)
			throws Exception {

		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();
		Instances testing_regr = null, testing_regr2 = null, testing_regr3 = null, testing_regr4 = null, testing_regr5 = null, testing_regr6 = null, testing_regr7 = null, testing_regr8 = null;

		// wottrust
		if (!model.toString().contains("No model built yet.")) {
			testing.setClass(fvAttributes.get(4));
			testing_regr = DataHandler.getInstance().applyRegressionModel(
					testing, fvAttributes, model);
		} else {
			testing_regr = testing;
		}

		// readability
		if (!model2.toString().contains("No model built yet.")) {
			testing_regr.setClass(fvAttributes.get(6));
			testing_regr2 = DataHandler.getInstance().applyRegressionModel(
					testing_regr, fvAttributes, model2);
		} else {
			testing_regr2 = testing_regr;
		}

		// normalization
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(
				testing_regr2, fvAttributes.size() - 1, normFilter);
		// testingSet_normed = getTrimmedInstances(testingSet_normed);

		return testingSet_normed;
	}

	public Instances getTransformedTestingAttSelection2(Instances testing)
			throws Exception {

		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();
		Instances testing_regr = null, testing_regr2 = null, testing_regr3 = null, testing_regr4 = null, testing_regr5 = null, testing_regr6 = null, testing_regr7 = null, testing_regr8 = null;

		// retweet count
		if (!model.toString().contains("No model built yet.")) {
			testing.setClass(fvAttributes.get(5));
			testing_regr = DataHandler.getInstance().applyRegressionModel(
					testing, fvAttributes, model);
		} else {
			testing_regr = testing;
		}

		// harmonic
		if (!model2.toString().contains("No model built yet.")) {
			testing_regr.setClass(fvAttributes.get(6));
			testing_regr2 = DataHandler.getInstance().applyRegressionModel(
					testing_regr, fvAttributes, model2);
		} else {
			testing_regr2 = testing_regr;
		}

		// normalization
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(
				testing_regr2, fvAttributes.size() - 1, normFilter);
		// testingSet_normed = getTrimmedInstances(testingSet_normed);

		return testingSet_normed;
	}

	/**
	 * Applies Linear Regression and normalization to the testing set according
	 * to the models and filters created before by the training set. It is the
	 * case of the User type Instances.
	 * 
	 * @param testing
	 *            the Instances to be transformed
	 * @return the transformed testing set
	 * @throws Exception
	 */
	public Instances getTransformedTestingUser(Instances testing)
			throws Exception {

		ArrayList<Attribute> fvAttributes = UserClassifier.getFvAttributes();
		Instances testing_regr = null, testing_regr2 = null, testing_regr3 = null, testing_regr4 = null, testing_regr5 = null;

		// testing_regr3 = testing;

		if (!usermodel.toString().contains("No model built yet.")) {
			testing.setClass(fvAttributes.get(11));
			testing_regr = DataHandler.getInstance().applyRegressionModel(
					testing, fvAttributes, usermodel);
		} else {
			testing_regr = testing;
		}
		if (!usermodel2.toString().contains("No model built yet.")) {
			testing_regr.setClass(fvAttributes.get(13));
			testing_regr2 = DataHandler.getInstance().applyRegressionModel(
					testing_regr, fvAttributes, usermodel2);
		} else {
			testing_regr2 = testing_regr;
		}
		if (!usermodel3.toString().contains("No model built yet.")) {
			testing_regr2.setClass(fvAttributes.get(16));
			testing_regr3 = DataHandler.getInstance().applyRegressionModel(
					testing_regr2, fvAttributes, usermodel3);
		} else {
			testing_regr3 = testing_regr2;
		}

		if (!usermodel4.toString().contains("No model built yet.")) {
			testing_regr3.setClass(fvAttributes.get(17));
			testing_regr4 = DataHandler.getInstance().applyRegressionModel(
					testing_regr3, fvAttributes, usermodel4);
		} else {
			testing_regr4 = testing_regr3;
		}

		if (!usermodel5.toString().contains("No model built yet.")) {
			testing_regr4.setClass(fvAttributes.get(18));
			testing_regr5 = DataHandler.getInstance().applyRegressionModel(
					testing_regr4, fvAttributes, usermodel5);
		} else {
			testing_regr5 = testing_regr4;
		}

		// normalization
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(
				testing_regr5, fvAttributes.size() - 1, normFilterUser);
		// testingSet_normed = getTrimmedInstances(testingSet_normed);

		return testingSet_normed;
	}

	/**
	 * Applies Linear Regression and normalization to the testing set according
	 * to the models and filters created before by the training set. It is the
	 * case of the User type Instances.
	 * 
	 * @param testing
	 *            the Instances to be transformed
	 * @return the transformed testing set
	 * @throws Exception
	 */
	public Instances getTransformedTestingUserOverall(Instances testing)
			throws Exception {

		ArrayList<Attribute> fvAttributes = UserClassifier.getFvAttributes();
		Instances testing_regr = null, testing_regr2 = null, testing_regr3 = null, testing_regr4 = null, testing_regr5 = null,
				testing_regr6 = null, testing_regr7 = null, testing_regr8 = null, testing_regr9 = null;

		// testing_regr3 = testing;

		if (!usermodel.toString().contains("No model built yet.")) {
			testing.setClass(fvAttributes.get(11));
			testing_regr = DataHandler.getInstance().applyRegressionModel(
					testing, fvAttributes, usermodel);
		} else {
			testing_regr = testing;
		}
		if (!usermodel2.toString().contains("No model built yet.")) {
			testing_regr.setClass(fvAttributes.get(13));
			testing_regr2 = DataHandler.getInstance().applyRegressionModel(
					testing_regr, fvAttributes, usermodel2);
		} else {
			testing_regr2 = testing_regr;
		}
		if (!usermodel3.toString().contains("No model built yet.")) {
			testing_regr2.setClass(fvAttributes.get(16));
			testing_regr3 = DataHandler.getInstance().applyRegressionModel(
					testing_regr2, fvAttributes, usermodel3);
		} else {
			testing_regr3 = testing_regr2;
		}

		if (!usermodel4.toString().contains("No model built yet.")) {
			testing_regr3.setClass(fvAttributes.get(17));
			testing_regr4 = DataHandler.getInstance().applyRegressionModel(
					testing_regr3, fvAttributes, usermodel4);
		} else {
			testing_regr4 = testing_regr3;
		}

		if (!usermodel5.toString().contains("No model built yet.")) {
			testing_regr4.setClass(fvAttributes.get(18));
			testing_regr5 = DataHandler.getInstance().applyRegressionModel(
					testing_regr4, fvAttributes, usermodel5);
		} else {
			testing_regr5 = testing_regr4;
		}
		
		if (!usermodel6.toString().contains("No model built yet.")) {
			testing_regr5.setClass(fvAttributes.get(19));
			testing_regr6 = DataHandler.getInstance().applyRegressionModel(
					testing_regr5, fvAttributes, usermodel6);
		} else {
			testing_regr6 = testing_regr5;
		}

		if (!usermodel7.toString().contains("No model built yet.")) {
			testing_regr6.setClass(fvAttributes.get(20));
			testing_regr7 = DataHandler.getInstance().applyRegressionModel(
					testing_regr6, fvAttributes, usermodel7);
		} else {
			testing_regr7 = testing_regr6;
		}
		
		if (!usermodel8.toString().contains("No model built yet.")) {
			testing_regr7.setClass(fvAttributes.get(21));
			testing_regr8 = DataHandler.getInstance().applyRegressionModel(
					testing_regr7, fvAttributes, usermodel8);
		} else {
			testing_regr8 = testing_regr7;
		}
		
		if (!usermodel9.toString().contains("No model built yet.")) {
			testing_regr8.setClass(fvAttributes.get(22));
			testing_regr9 = DataHandler.getInstance().applyRegressionModel(
					testing_regr8, fvAttributes, usermodel9);
		} else {
			testing_regr9 = testing_regr8;
		}
		
		// normalization
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(
				testing_regr9, fvAttributes.size() - 1, normFilterUser);
		testingSet_normed.setClassIndex(fvAttributes.size()-1);

		for (int i=0; i<testingSet_normed.size(); i++) {
			//System.out.println(testingSet_normed.get(i));
			for (int j=0; j<testingSet_normed.get(i).numAttributes();j++) {
				if (!testingSet_normed.get(i).attribute(j).isNominal())
				testingSet_normed.get(i).setValue(j, round(testingSet_normed.get(i).value(j), 6) );
			}
			//System.out.println(testingSet_normed.get(i));
		}
		
		testingSet_normed = getTrimmedInstances(testingSet_normed);
		
		return testingSet_normed;
	}
	
	/**
	 * Applies Linear Regression and normalization to the testing set according
	 * to the models and filters created before by the training set. It is the
	 * case of the User type Instances.
	 * 
	 * @param testing
	 *            the Instances to be transformed
	 * @return the transformed testing set
	 * @throws Exception
	 */
	public Instances getTransformedTestingUserTimeFeats(Instances testing)
			throws Exception {

		ArrayList<Attribute> fvAttributes = UserClassifier.getFvAttributes();
		Instances testing_regr = null, testing_regr2 = null, testing_regr3 = null, testing_regr4 = null, testing_regr5 = null;

		if (!usermodel.toString().contains("No model built yet.")) {
			testing.setClass(fvAttributes.get(8));
			testing_regr = DataHandler.getInstance().applyRegressionModel(
					testing, fvAttributes, usermodel);
		} else {
			testing_regr = testing;
		}
		if (!usermodel2.toString().contains("No model built yet.")) {
			testing_regr.setClass(fvAttributes.get(9));
			testing_regr2 = DataHandler.getInstance().applyRegressionModel(
					testing_regr, fvAttributes, usermodel2);
		} else {
			testing_regr2 = testing_regr;
		}

		// normalization
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(
				testing_regr2, fvAttributes.size() - 1, normFilterUser);
		// testingSet_normed = getTrimmedInstances(testingSet_normed);

		return testingSet_normed;
	}

	/**
	 * Applies Linear Regression and normalization to the testing set according
	 * to the models and filters created before by the training set. It is the
	 * case of the User type Instances.
	 * 
	 * @param testing
	 *            the Instances to be transformed
	 * @return the transformed testing set
	 * @throws Exception
	 */
	public Instances getTransformedTestingUserOld(Instances testing)
			throws Exception {

		ArrayList<Attribute> fvAttributes = UserClassifier.getFvAttributes();

		// normalization
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(
				testing, fvAttributes.size() - 1, normFilterUser);
		
		testingSet_normed = getTrimmedInstances(testingSet_normed);

		return testingSet_normed;
	}

	public Instances getTransformedTestingUserAttSelection1(Instances testing)
			throws Exception {

		ArrayList<Attribute> fvAttributes = UserClassifier.getFvAttributes();
		Instances testing_regr = null, testing_regr2 = null, testing_regr3 = null, testing_regr4 = null, testing_regr5 = null;

		// testing_regr3 = testing;

		if (!usermodel.toString().contains("No model built yet.")) {
			testing.setClass(fvAttributes.get(1));
			testing_regr = DataHandler.getInstance().applyRegressionModel(
					testing, fvAttributes, usermodel);
		} else {
			testing_regr = testing;
		}
		if (!usermodel2.toString().contains("No model built yet.")) {
			testing_regr.setClass(fvAttributes.get(4));
			testing_regr2 = DataHandler.getInstance().applyRegressionModel(
					testing_regr, fvAttributes, usermodel2);
		} else {
			testing_regr2 = testing_regr;
		}

		// normalization
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(
				testing_regr2, fvAttributes.size() - 1, normFilterUser);
		// testingSet_normed = getTrimmedInstances(testingSet_normed);

		return testingSet_normed;
	}

	public Instances getTransformedTestingUserAttSelection2(Instances testing)
			throws Exception {

		ArrayList<Attribute> fvAttributes = UserClassifier.getFvAttributes();
		Instances testing_regr = null, testing_regr2 = null, testing_regr3 = null, testing_regr4 = null, testing_regr5 = null;

		// testing_regr3 = testing;

		if (!usermodel.toString().contains("No model built yet.")) {
			testing.setClass(fvAttributes.get(1));
			testing_regr = DataHandler.getInstance().applyRegressionModel(
					testing, fvAttributes, usermodel);
		} else {
			testing_regr = testing;
		}

		if (!usermodel2.toString().contains("No model built yet.")) {
			testing_regr.setClass(fvAttributes.get(2));
			testing_regr2 = DataHandler.getInstance().applyRegressionModel(
					testing_regr, fvAttributes, usermodel2);
		} else {
			testing_regr2 = testing_regr;
		}

		if (!usermodel3.toString().contains("No model built yet.")) {
			testing_regr2.setClass(fvAttributes.get(3));
			testing_regr3 = DataHandler.getInstance().applyRegressionModel(
					testing_regr2, fvAttributes, usermodel3);
		} else {
			testing_regr3 = testing_regr2;
		}
		// normalization
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(
				testing_regr3, fvAttributes.size() - 1, normFilterUser);
		// testingSet_normed = getTrimmedInstances(testingSet_normed);

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

	/**
	 * Applies linear regression to the missing values of the training set in
	 * order to predict their values and normalization to make the values fall
	 * into the range [0,1]. It is the case of the Item type Instances.
	 * 
	 * @param trainingSet
	 * @return Instances the transformed training set
	 * @throws Exception
	 */
	public Instances getTransformedTrainingExtended(Instances trainingSet)
			throws Exception {
	
		initializeModels();
	
		Remove rm = new Remove();	rm.setAttributeIndices("1");
		Remove rm1 = new Remove();	rm.setAttributeIndices("1");
		Remove rm2 = new Remove();	rm.setAttributeIndices("1");
		Remove rm3 = new Remove();	rm.setAttributeIndices("1");
		Remove rm4 = new Remove();	rm.setAttributeIndices("1");
		Remove rm5 = new Remove();	rm.setAttributeIndices("1");
		Remove rm6 = new Remove();	rm.setAttributeIndices("1");
		Remove rm7 = new Remove();	rm.setAttributeIndices("1");
		Remove rm8 = new Remove();	rm.setAttributeIndices("1");
		Remove rm9 = new Remove();	rm.setAttributeIndices("1");
		Remove rm10 = new Remove();	rm.setAttributeIndices("1");
		Remove rm11 = new Remove();	rm.setAttributeIndices("1");
		Remove rm12 = new Remove();	rm.setAttributeIndices("1");
		Remove rm13 = new Remove();	rm.setAttributeIndices("1");
		Remove rm14 = new Remove();	rm.setAttributeIndices("1");
		Remove rm15 = new Remove();	rm.setAttributeIndices("1");
		Remove rm16 = new Remove();	rm.setAttributeIndices("1");
		Remove rm17 = new Remove();	rm.setAttributeIndices("1");
		Remove rm18 = new Remove();	rm.setAttributeIndices("1");
		Remove rm19 = new Remove();	rm.setAttributeIndices("1");
		Remove rm20 = new Remove();	rm.setAttributeIndices("1");
		Remove rm21 = new Remove();	rm.setAttributeIndices("1");
		Remove rm22 = new Remove();	rm.setAttributeIndices("1");
		Remove rm23 = new Remove();	rm.setAttributeIndices("1");
		Remove rm24 = new Remove();	rm.setAttributeIndices("1");
		Remove rm25 = new Remove();	rm.setAttributeIndices("1");
		Remove rm26 = new Remove();	rm.setAttributeIndices("1");
		Remove rm27 = new Remove();	rm.setAttributeIndices("1");
		Remove rm28 = new Remove();	rm.setAttributeIndices("1");
		Remove rm29 = new Remove();	rm.setAttributeIndices("1");
		Remove rm30 = new Remove();	rm.setAttributeIndices("1");
		Remove rm31 = new Remove();	rm.setAttributeIndices("1");
		Remove rm32 = new Remove();	rm.setAttributeIndices("1");
		Remove rm33 = new Remove();	rm.setAttributeIndices("1");
		Remove rm34 = new Remove();	rm.setAttributeIndices("1");
		Remove rm35 = new Remove();	rm.setAttributeIndices("1");
		Remove rm36 = new Remove();	rm.setAttributeIndices("1");
		Remove rm37 = new Remove();	rm.setAttributeIndices("1");
		Remove rm38 = new Remove();	rm.setAttributeIndices("1");
		Remove rm39 = new Remove();	rm.setAttributeIndices("1");
		Remove rm40 = new Remove();	rm.setAttributeIndices("1");
		Remove rm41 = new Remove();	rm.setAttributeIndices("1");
		Remove rm42 = new Remove();	rm.setAttributeIndices("1");
		Remove rm43 = new Remove();	rm.setAttributeIndices("1");
		Remove rm44 = new Remove();	rm.setAttributeIndices("1");
		Remove rm45 = new Remove();	rm.setAttributeIndices("1");
		Remove rm46 = new Remove();	rm.setAttributeIndices("1");
		Remove rm47 = new Remove();	rm.setAttributeIndices("1");
		Remove rm48 = new Remove();	rm.setAttributeIndices("1");
		Remove rm49 = new Remove();	rm.setAttributeIndices("1");
		Remove rm50 = new Remove();	rm50.setAttributeIndices("1");
		Remove rm51 = new Remove();	rm51.setAttributeIndices("1");
		Remove rm52 = new Remove();	rm52.setAttributeIndices("1");
		Remove rm53 = new Remove();	rm53.setAttributeIndices("1");
		Remove rm54 = new Remove();	rm54.setAttributeIndices("1");
		Remove rm55 = new Remove();	rm55.setAttributeIndices("1");
		Remove rm56 = new Remove();	rm56.setAttributeIndices("1");
		Remove rm57 = new Remove();	rm57.setAttributeIndices("1");
		Remove rm58 = new Remove();	rm58.setAttributeIndices("1");
		Remove rm59 = new Remove();	rm59.setAttributeIndices("1");
		Remove rm60 = new Remove();	rm60.setAttributeIndices("1");
		Remove rm61 = new Remove();	rm61.setAttributeIndices("1");
		Remove rm62 = new Remove();	rm62.setAttributeIndices("1");
		Remove rm63 = new Remove();	rm63.setAttributeIndices("1");
		Remove rm64 = new Remove();	rm64.setAttributeIndices("1");
		Remove rm65 = new Remove();	rm65.setAttributeIndices("1");
		Remove rm66 = new Remove();	rm66.setAttributeIndices("1");
		Remove rm67 = new Remove();	rm67.setAttributeIndices("1");
		Remove rm68 = new Remove();	rm68.setAttributeIndices("1");
		Remove rm69 = new Remove();	rm69.setAttributeIndices("1");
		Remove rm70 = new Remove();	rm70.setAttributeIndices("1");
		Remove rm71 = new Remove();	rm71.setAttributeIndices("1");
		Remove rm72 = new Remove();	rm72.setAttributeIndices("1");
		Remove rm73 = new Remove();	rm73.setAttributeIndices("1");
		Remove rm74 = new Remove();	rm74.setAttributeIndices("1");
		Remove rm75 = new Remove();	rm75.setAttributeIndices("1");
		Remove rm76 = new Remove();	rm70.setAttributeIndices("1");
		Remove rm77 = new Remove();	rm71.setAttributeIndices("1");
		Remove rm78 = new Remove();	rm72.setAttributeIndices("1");
		Remove rm79 = new Remove();	rm73.setAttributeIndices("1");
		Remove rm80 = new Remove();	rm74.setAttributeIndices("1");
		Remove rm81 = new Remove();	rm75.setAttributeIndices("1");
		Remove rm82 = new Remove();	rm75.setAttributeIndices("1");
		Remove rm83 = new Remove();	rm75.setAttributeIndices("1");
		Remove rm84 = new Remove();	rm72.setAttributeIndices("1");
		Remove rm85 = new Remove();	rm75.setAttributeIndices("1");
		Remove rm86 = new Remove();	rm70.setAttributeIndices("1");
		Remove rm87 = new Remove();	rm71.setAttributeIndices("1");
		Remove rm88 = new Remove();	rm72.setAttributeIndices("1");
		Remove rm89 = new Remove();	rm73.setAttributeIndices("1");
		Remove rm90 = new Remove();	rm74.setAttributeIndices("1");
		Remove rm91 = new Remove();	rm75.setAttributeIndices("1");
		Remove rm92 = new Remove();	rm75.setAttributeIndices("1");
		Remove rm93 = new Remove();	rm75.setAttributeIndices("1");
		
		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();
		
		// REGRESSION
		// wotTrust
		trainingSet.setClass(fvAttributes.get(22));
		LinearRegression lr = new LinearRegression();
		Instances training_regr = null;
		model.setFilter(rm9);
		model.setClassifier(lr);
	
		try {
			model.buildClassifier(trainingSet);
			training_regr = DataHandler.getInstance().applyRegressionModel(
					trainingSet, fvAttributes, model);
		} catch (Exception e) {
			training_regr = trainingSet;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
	
		// readability
		training_regr.setClass(fvAttributes.get(25));
		LinearRegression lr2 = new LinearRegression();
		Instances training_regr2 = null;
	
		model2.setFilter(rm10);
		model2.setClassifier(lr2);
		try {
			model2.buildClassifier(training_regr);
			training_regr2 = DataHandler.getInstance().applyRegressionModel(
					training_regr, fvAttributes, model2);
		} catch (Exception e) {
			training_regr2 = training_regr;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
	
		// alexa popularity
		training_regr2.setClass(fvAttributes.get(30));
		LinearRegression lr3 = new LinearRegression();
		Instances training_regr3 = null;
	
		model3.setFilter(rm11);
		model3.setClassifier(lr3);
		try {
			model3.buildClassifier(training_regr2);
			training_regr3 = DataHandler.getInstance().applyRegressionModel(
					training_regr2, fvAttributes, model3);
		} catch (Exception e) {
			training_regr3 = training_regr2;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
	
		// alexa delta rank
		training_regr3.setClass(fvAttributes.get(29));
		LinearRegression lr4 = new LinearRegression();
		Instances training_regr4 = null;
		model4.setFilter(rm12);
		model4.setClassifier(lr4);
		try {
			model4.buildClassifier(training_regr3);
			training_regr4 = DataHandler.getInstance().applyRegressionModel(
					training_regr3, fvAttributes, model4);
		} catch (Exception e) {
			training_regr4 = training_regr3;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
	
		// getNumNegSentiWords
		training_regr4.setClass(fvAttributes.get(14));
		LinearRegression lr5 = new LinearRegression();
		Instances training_regr5 = null;
		model5.setFilter(rm13);
		model5.setClassifier(lr5);
	
		try {
			model5.buildClassifier(training_regr4);
			training_regr5 = DataHandler.getInstance().applyRegressionModel(
					training_regr4, fvAttributes, model5);
		} catch (Exception e) {
			training_regr5 = training_regr4;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
	
		
		// num slangs
		training_regr5.setClass(fvAttributes.get(19));
		LinearRegression lr6 = new LinearRegression();
		Instances training_regr6 = null;
		model6.setFilter(rm14);
		model6.setClassifier(lr6);
		try {
			model6.buildClassifier(training_regr5);
			training_regr6 = DataHandler.getInstance().applyRegressionModel(
					training_regr5, fvAttributes, model6);
		} catch (Exception e) {
			training_regr6 = training_regr5;
		}
	
		// indegree
		training_regr6.setClass(fvAttributes.get(26));
		LinearRegression lr7 = new LinearRegression();
		Instances training_regr7 = null;
		model7.setFilter(rm15);
		model7.setClassifier(lr7);
		try {
			model7.buildClassifier(training_regr6);
			training_regr7 = DataHandler.getInstance().applyRegressionModel(
					training_regr6, fvAttributes, model7);
		} catch (Exception e) {
			training_regr7 = training_regr6;
		}
	
		
	
		//alexa country rank 
		training_regr7.setClass(fvAttributes.get(28));
		LinearRegression lr8 = new LinearRegression();
		Instances training_regr8 = null;
		model8.setFilter(rm16);
		model8.setClassifier(lr8);
		try {
			model8.buildClassifier(training_regr7);
			training_regr8 = DataHandler.getInstance().applyRegressionModel(
					training_regr7, fvAttributes, model8);
		} catch (Exception e) {
			training_regr8 = training_regr7;
		}
	
		// harmonic
		training_regr8.setClass(fvAttributes.get(27));
		LinearRegression lr9 = new LinearRegression();
		Instances training_regr9 = null;
		model9.setFilter(rm17);
		model9.setClassifier(lr9);
		try {
			model9.buildClassifier(training_regr8);
			training_regr9 = DataHandler.getInstance().applyRegressionModel(
					training_regr8, fvAttributes, model9);
		} catch (Exception e) {
			training_regr9 = training_regr8;
		}
	
		// getNumPosSentiWords
		training_regr9.setClass(fvAttributes.get(13));
		LinearRegression lr10 = new LinearRegression();
		Instances training_regr10 = null;
		model10.setFilter(rm18);
		model10.setClassifier(lr10);
		try {
			model10.buildClassifier(training_regr9);
			training_regr10 = DataHandler.getInstance().applyRegressionModel(
					training_regr9, fvAttributes, model10);
		} catch (Exception e) {
			training_regr10 = training_regr9;
		}
		
		//alexa reach rank
		training_regr10.setClass(fvAttributes.get(31));
		LinearRegression lr11 = new LinearRegression();
		Instances training_regr11 = null;
		model11.setFilter(rm19);
		model11.setClassifier(lr11);
		try {
			model11.buildClassifier(training_regr10);
			training_regr11 = DataHandler.getInstance().applyRegressionModel(
					training_regr10, fvAttributes, model11);
		} catch (Exception e) {
			training_regr11 = training_regr10;
		}
		
		//getNumNouns
		training_regr11.setClass(fvAttributes.get(6));
		LinearRegression lr12 = new LinearRegression();
		Instances training_regr12 = null;
		model12.setFilter(rm20);
		model12.setClassifier(lr12);
		try {
			model12.buildClassifier(training_regr11);
			training_regr12 = DataHandler.getInstance().applyRegressionModel(
					training_regr11, fvAttributes, model12);
		} catch (Exception e) {
			training_regr12 = training_regr11;
		}
		
		
		//first pron
		training_regr12.setClass(fvAttributes.get(7));
		LinearRegression lr13 = new LinearRegression();
		Instances training_regr13 = null;
	
		model13.setFilter(rm21);
		model13.setClassifier(lr13);
		try {
			model13.buildClassifier(training_regr12);
			training_regr13 = DataHandler.getInstance().applyRegressionModel(
					training_regr12, fvAttributes, model13);
		} catch (Exception e) {
			training_regr13 = training_regr12;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		//second pron
		training_regr13.setClass(fvAttributes.get(8));
		LinearRegression lr14 = new LinearRegression();
		Instances training_regr14 = null;
	
		model14.setFilter(rm22);
		model14.setClassifier(lr14);
		try {
			model14.buildClassifier(training_regr13);
			training_regr14 = DataHandler.getInstance().applyRegressionModel(
					training_regr13, fvAttributes, model14);
		} catch (Exception e) {
			training_regr14 = training_regr13;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		//third pron
		training_regr14.setClass(fvAttributes.get(9));
		LinearRegression lr15 = new LinearRegression();
		Instances training_regr15 = null;
	
		model15.setFilter(rm23);
		model15.setClassifier(lr15);
		try {
			model15.buildClassifier(training_regr14);
			training_regr15 = DataHandler.getInstance().applyRegressionModel(
					training_regr14, fvAttributes, model15);
		} catch (Exception e) {
			training_regr15 = training_regr14;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		
		//sad 
		training_regr15.setClass(fvAttributes.get(54));
		LinearRegression lr15a = new LinearRegression();
		Instances training_regr15a = null;
		modelsad_01.setFilter(rm24);
		modelsad_01.setClassifier(lr15a);
		try {
			modelsad_01.buildClassifier(training_regr15);
			training_regr15a = DataHandler.getInstance().applyRegressionModel(
					training_regr15, fvAttributes, modelsad_01);
		} catch (Exception e) {
			training_regr15a = training_regr15;
		}
		
		training_regr15a.setClass(fvAttributes.get(55));
		LinearRegression lr15b = new LinearRegression();
		Instances training_regr15b = null;
		modelsad_02.setFilter(rm25);
		modelsad_02.setClassifier(lr15b);
		try {
			modelsad_02.buildClassifier(training_regr15a);
			training_regr15b = DataHandler.getInstance().applyRegressionModel(
					training_regr15a, fvAttributes, modelsad_02);
		} catch (Exception e) {
			training_regr15b = training_regr15a;
		}
		
		training_regr15b.setClass(fvAttributes.get(56));
		LinearRegression lr15c = new LinearRegression();
		Instances training_regr15c = null;
		modelsad_03.setFilter(rm26);
		modelsad_03.setClassifier(lr15c);
		try {
			modelsad_03.buildClassifier(training_regr15b);
			training_regr15c = DataHandler.getInstance().applyRegressionModel(
					training_regr15b, fvAttributes, modelsad_03);
		} catch (Exception e) {
			training_regr15c = training_regr15b;
		}
		
		training_regr15c.setClass(fvAttributes.get(57));
		LinearRegression lr16 = new LinearRegression();
		Instances training_regr16 = null;
		modelsad_04.setFilter(rm27);
		modelsad_04.setClassifier(lr16);
		try {
			modelsad_04.buildClassifier(training_regr15c);
			training_regr16 = DataHandler.getInstance().applyRegressionModel(
					training_regr15c, fvAttributes, modelsad_04);
		} catch (Exception e) {
			training_regr16 = training_regr15c;
		}
		
		training_regr16.setClass(fvAttributes.get(58));
		LinearRegression lr17 = new LinearRegression();
		Instances training_regr17 = null;
		modelsad_05.setFilter(rm28);
		modelsad_05.setClassifier(lr17);
		try {
			modelsad_05.buildClassifier(training_regr16);
			training_regr17 = DataHandler.getInstance().applyRegressionModel(
					training_regr16, fvAttributes, modelsad_05);
		} catch (Exception e) {
			training_regr17 = training_regr16;
		}
		
		training_regr17.setClass(fvAttributes.get(59));
		LinearRegression lr18 = new LinearRegression();
		Instances training_regr18 = null;
		modelsad_06.setFilter(rm29);
		modelsad_06.setClassifier(lr18);
		try {
			modelsad_06.buildClassifier(training_regr17);
			training_regr18 = DataHandler.getInstance().applyRegressionModel(
					training_regr17, fvAttributes, modelsad_06);
		} catch (Exception e) {
			training_regr18 = training_regr17;
		}
		
		training_regr18.setClass(fvAttributes.get(60));
		LinearRegression lr19 = new LinearRegression();
		Instances training_regr19 = null;
		modelsad_07.setFilter(rm30);
		modelsad_07.setClassifier(lr19);
		try {
			modelsad_07.buildClassifier(training_regr18);
			training_regr19 = DataHandler.getInstance().applyRegressionModel(
					training_regr18, fvAttributes, modelsad_07);
		} catch (Exception e) {
			training_regr19 = training_regr18;
		}
		
		training_regr19.setClass(fvAttributes.get(61));
		LinearRegression lr20 = new LinearRegression();
		Instances training_regr20 = null;
		modelsad_08.setFilter(rm31);
		modelsad_08.setClassifier(lr20);
		try {
			modelsad_08.buildClassifier(training_regr19);
			training_regr20 = DataHandler.getInstance().applyRegressionModel(
					training_regr19, fvAttributes, modelsad_08);
		} catch (Exception e) {
			training_regr20 = training_regr19;
		}
		
		training_regr20.setClass(fvAttributes.get(62));
		LinearRegression lr21 = new LinearRegression();
		Instances training_regr21 = null;
		modelsnad_01.setFilter(rm32);
		modelsnad_01.setClassifier(lr21);
		try {
			modelsnad_01.buildClassifier(training_regr20);
			training_regr21 = DataHandler.getInstance().applyRegressionModel(
					training_regr20, fvAttributes, modelsnad_01);
		} catch (Exception e) {
			training_regr21 = training_regr20;
		}
		
		training_regr21.setClass(fvAttributes.get(63));
		LinearRegression lr22 = new LinearRegression();
		Instances training_regr22 = null;
		modelsnad_02.setFilter(rm33);
		modelsnad_02.setClassifier(lr22);
		try {
			modelsnad_02.buildClassifier(training_regr21);
			training_regr22 = DataHandler.getInstance().applyRegressionModel(
					training_regr21, fvAttributes, modelsnad_02);
		} catch (Exception e) {
			training_regr22 = training_regr21;
		}
		
		training_regr22.setClass(fvAttributes.get(64));
		LinearRegression lr23 = new LinearRegression();
		Instances training_regr23 = null;
		modelsnad_03.setFilter(rm34);
		modelsnad_03.setClassifier(lr23);
		try {
			modelsnad_03.buildClassifier(training_regr22);
			training_regr23 = DataHandler.getInstance().applyRegressionModel(
					training_regr22, fvAttributes, modelsnad_03);
		} catch (Exception e) {
			training_regr23 = training_regr22;
		}
		
		training_regr23.setClass(fvAttributes.get(65));
		LinearRegression lr24 = new LinearRegression();
		Instances training_regr24 = null;
		modelsnad_04.setFilter(rm35);
		modelsnad_04.setClassifier(lr24);
		try {
			modelsnad_04.buildClassifier(training_regr23);
			training_regr24 = DataHandler.getInstance().applyRegressionModel(
					training_regr23, fvAttributes, modelsnad_04);
		} catch (Exception e) {
			training_regr24 = training_regr23;
		}
		
		training_regr24.setClass(fvAttributes.get(66));
		LinearRegression lr25 = new LinearRegression();
		Instances training_regr25 = null;
		modelsnad_05.setFilter(rm36);
		modelsnad_05.setClassifier(lr25);
		try {
			modelsnad_05.buildClassifier(training_regr24);
			training_regr25 = DataHandler.getInstance().applyRegressionModel(
					training_regr24, fvAttributes, modelsnad_05);
		} catch (Exception e) {
			training_regr25 = training_regr24;
		}
		
		training_regr25.setClass(fvAttributes.get(67));
		LinearRegression lr26 = new LinearRegression();
		Instances training_regr26 = null;
		modelsnad_06.setFilter(rm37);
		modelsnad_06.setClassifier(lr26);
		try {
			modelsnad_06.buildClassifier(training_regr25);
			training_regr26 = DataHandler.getInstance().applyRegressionModel(
					training_regr25, fvAttributes, modelsnad_06);
		} catch (Exception e) {
			training_regr26 = training_regr25;
		}
		
		training_regr26.setClass(fvAttributes.get(68));
		LinearRegression lr27 = new LinearRegression();
		Instances training_regr27 = null;
		modelsnad_07.setFilter(rm38);
		modelsnad_07.setClassifier(lr27);
		try {
			modelsnad_07.buildClassifier(training_regr26);
			training_regr27 = DataHandler.getInstance().applyRegressionModel(
					training_regr26, fvAttributes, modelsnad_07);
		} catch (Exception e) {
			training_regr27 = training_regr26;
		}
		
		training_regr27.setClass(fvAttributes.get(69));
		LinearRegression lr28 = new LinearRegression();
		Instances training_regr28 = null;
		modelsnad_08.setFilter(rm39);
		modelsnad_08.setClassifier(lr28);
		try {
			modelsnad_08.buildClassifier(training_regr27);
			training_regr28 = DataHandler.getInstance().applyRegressionModel(
					training_regr27, fvAttributes, modelsnad_08);
		} catch (Exception e) {
			training_regr28 = training_regr27;
		}
		
		training_regr28.setClass(fvAttributes.get(32));
		LinearRegression lr29 = new LinearRegression();
		Instances training_regr29 = null;
		modelbf_01.setFilter(rm40);
		modelbf_01.setClassifier(lr29);
		try {
			modelbf_01.buildClassifier(training_regr28);
			training_regr29 = DataHandler.getInstance().applyRegressionModel(
					training_regr28, fvAttributes, modelbf_01);
		} catch (Exception e) {
			training_regr29 = training_regr28;
		}
		
		training_regr29.setClass(fvAttributes.get(33));
		LinearRegression lr30 = new LinearRegression();
		Instances training_regr30 = null;
		modelbf_02.setFilter(rm41);
		modelbf_02.setClassifier(lr30);
		try {
			modelbf_02.buildClassifier(training_regr29);
			training_regr30 = DataHandler.getInstance().applyRegressionModel(
					training_regr29, fvAttributes, modelbf_02);
		} catch (Exception e) {
			training_regr30 = training_regr29;
		}
		
		training_regr30.setClass(fvAttributes.get(34));
		LinearRegression lr31 = new LinearRegression();
		Instances training_regr31 = null;
		modelbf_03.setFilter(rm42);
		modelbf_03.setClassifier(lr31);
		try {
			modelbf_03.buildClassifier(training_regr30);
			training_regr31 = DataHandler.getInstance().applyRegressionModel(
					training_regr30, fvAttributes, modelbf_03);
		} catch (Exception e) {
			training_regr31 = training_regr30;
		}
		
		training_regr31.setClass(fvAttributes.get(35));
		LinearRegression lr32 = new LinearRegression();
		Instances training_regr32 = null;
		modelbf_04.setFilter(rm43);
		modelbf_04.setClassifier(lr32);
		try {
			modelbf_04.buildClassifier(training_regr31);
			training_regr32 = DataHandler.getInstance().applyRegressionModel(
					training_regr31, fvAttributes, modelbf_04);
		} catch (Exception e) {
			training_regr32 = training_regr31;
		}
		
		training_regr32.setClass(fvAttributes.get(36));
		LinearRegression lr33 = new LinearRegression();
		Instances training_regr33 = null;
		modelbf_05.setFilter(rm44);
		modelbf_05.setClassifier(lr33);
		try {
			modelbf_05.buildClassifier(training_regr32);
			training_regr33 = DataHandler.getInstance().applyRegressionModel(
					training_regr32, fvAttributes, modelbf_05);
		} catch (Exception e) {
			training_regr33 = training_regr32;
		}
		
		training_regr33.setClass(fvAttributes.get(37));
		LinearRegression lr34 = new LinearRegression();
		Instances training_regr34 = null;
		modelbf_06.setFilter(rm45);
		modelbf_06.setClassifier(lr34);
		try {
			modelbf_06.buildClassifier(training_regr33);
			training_regr34 = DataHandler.getInstance().applyRegressionModel(
					training_regr33, fvAttributes, modelbf_06);
		} catch (Exception e) {
			training_regr34 = training_regr33;
		}
		
		training_regr34.setClass(fvAttributes.get(38));
		LinearRegression lr35 = new LinearRegression();
		Instances training_regr35 = null;
		modelbf_07.setFilter(rm46);
		modelbf_07.setClassifier(lr35);
		try {
			modelbf_07.buildClassifier(training_regr34);
			training_regr35 = DataHandler.getInstance().applyRegressionModel(
					training_regr34, fvAttributes, modelbf_07);
		} catch (Exception e) {
			training_regr35 = training_regr34;
		}
		
		training_regr35.setClass(fvAttributes.get(39));
		LinearRegression lr36 = new LinearRegression();
		Instances training_regr36 = null;
		modelbf_08.setFilter(rm47);
		modelbf_08.setClassifier(lr36);
		try {
			modelbf_08.buildClassifier(training_regr35);
			training_regr36 = DataHandler.getInstance().applyRegressionModel(
					training_regr35, fvAttributes, modelbf_08);
		} catch (Exception e) {
			training_regr36 = training_regr35;
		}
		
		training_regr36.setClass(fvAttributes.get(40));
		LinearRegression lr37 = new LinearRegression();
		Instances training_regr37 = null;
		modelbf_09.setFilter(rm48);
		modelbf_09.setClassifier(lr37);
		try {
			modelbf_09.buildClassifier(training_regr36);
			training_regr37 = DataHandler.getInstance().applyRegressionModel(
					training_regr36, fvAttributes, modelbf_09);
		} catch (Exception e) {
			training_regr37 = training_regr36;
		}
		
		training_regr37.setClass(fvAttributes.get(41));
		LinearRegression lr38 = new LinearRegression();
		Instances training_regr38 = null;
		modelbf_10.setFilter(rm49);
		modelbf_10.setClassifier(lr38);
		try {
			modelbf_10.buildClassifier(training_regr37);
			training_regr38 = DataHandler.getInstance().applyRegressionModel(
					training_regr37, fvAttributes, modelbf_10);
		} catch (Exception e) {
			training_regr38 = training_regr37;
		}
		
		training_regr38.setClass(fvAttributes.get(42));
		LinearRegression lr39 = new LinearRegression();
		Instances training_regr39 = null;
		modelbf_11.setFilter(rm50);
		modelbf_11.setClassifier(lr39);
		try {
			modelbf_11.buildClassifier(training_regr38);
			training_regr39 = DataHandler.getInstance().applyRegressionModel(
					training_regr38, fvAttributes, modelbf_11);
		} catch (Exception e) {
			training_regr39 = training_regr38;
		}
		
		training_regr39.setClass(fvAttributes.get(43));
		LinearRegression lr40 = new LinearRegression();
		Instances training_regr40 = null;
		modelbf_12.setFilter(rm51);
		modelbf_12.setClassifier(lr40);
		try {
			modelbf_12.buildClassifier(training_regr39);
			training_regr40 = DataHandler.getInstance().applyRegressionModel(
					training_regr39, fvAttributes, modelbf_12);
		} catch (Exception e) {
			training_regr40 = training_regr39;
		}
		
		training_regr40.setClass(fvAttributes.get(44));
		LinearRegression lr41 = new LinearRegression();
		Instances training_regr41 = null;
		modelbf_13.setFilter(rm52);
		modelbf_13.setClassifier(lr41);
		try {
			modelbf_13.buildClassifier(training_regr40);
			training_regr41 = DataHandler.getInstance().applyRegressionModel(
					training_regr40, fvAttributes, modelbf_13);
		} catch (Exception e) {
			training_regr41 = training_regr40;
		}
		
		training_regr41.setClass(fvAttributes.get(45));
		LinearRegression lr42 = new LinearRegression();
		Instances training_regr42 = null;
		modelbf_14.setFilter(rm53);
		modelbf_14.setClassifier(lr42);
		try {
			modelbf_14.buildClassifier(training_regr41);
			training_regr42 = DataHandler.getInstance().applyRegressionModel(
					training_regr41, fvAttributes, modelbf_14);
		} catch (Exception e) {
			training_regr42 = training_regr41;
		}
		
		training_regr42.setClass(fvAttributes.get(46));
		LinearRegression lr43 = new LinearRegression();
		Instances training_regr43 = null;
		modelbf_15.setFilter(rm54);
		modelbf_15.setClassifier(lr43);
		try {
			modelbf_15.buildClassifier(training_regr42);
			training_regr43 = DataHandler.getInstance().applyRegressionModel(
					training_regr42, fvAttributes, modelbf_15);
		} catch (Exception e) {
			training_regr43 = training_regr42;
		}
		
		training_regr43.setClass(fvAttributes.get(47));
		LinearRegression lr44 = new LinearRegression();
		Instances training_regr44 = null;
		modelbf_16.setFilter(rm55);
		modelbf_16.setClassifier(lr44);
		try {
			modelbf_16.buildClassifier(training_regr43);
			training_regr44 = DataHandler.getInstance().applyRegressionModel(
					training_regr43, fvAttributes, modelbf_16);
		} catch (Exception e) {
			training_regr44 = training_regr43;
		}
		
		training_regr44.setClass(fvAttributes.get(48));
		LinearRegression lr45 = new LinearRegression();
		Instances training_regr45 = null;
		modelbf_17.setFilter(rm56);
		modelbf_17.setClassifier(lr45);
		try {
			modelbf_17.buildClassifier(training_regr44);
			training_regr45 = DataHandler.getInstance().applyRegressionModel(
					training_regr44, fvAttributes, modelbf_17);
		} catch (Exception e) {
			training_regr45 = training_regr44;
		}
		
		training_regr45.setClass(fvAttributes.get(49));
		LinearRegression lr46 = new LinearRegression();
		Instances training_regr46 = null;
		modelbf_18.setFilter(rm57);
		modelbf_18.setClassifier(lr46);
		try {
			modelbf_18.buildClassifier(training_regr45);
			training_regr46 = DataHandler.getInstance().applyRegressionModel(
					training_regr45, fvAttributes, modelbf_18);
		} catch (Exception e) {
			training_regr46 = training_regr45;
		}
		
		training_regr46.setClass(fvAttributes.get(50));
		LinearRegression lr47 = new LinearRegression();
		Instances training_regr47 = null;
		modelbf_19.setFilter(rm58);
		modelbf_19.setClassifier(lr47);
		try {
			modelbf_19.buildClassifier(training_regr46);
			training_regr47 = DataHandler.getInstance().applyRegressionModel(
					training_regr46, fvAttributes, modelbf_19);
		} catch (Exception e) {
			training_regr47 = training_regr46;
		}
		
		training_regr47.setClass(fvAttributes.get(51));
		LinearRegression lr48 = new LinearRegression();
		Instances training_regr48 = null;
		modelbf_20.setFilter(rm59);
		modelbf_20.setClassifier(lr48);
		try {
			modelbf_20.buildClassifier(training_regr47);
			training_regr48 = DataHandler.getInstance().applyRegressionModel(
					training_regr47, fvAttributes, modelbf_20);
		} catch (Exception e) {
			training_regr48 = training_regr47;
		}
		
		training_regr48.setClass(fvAttributes.get(52));
		LinearRegression lr49 = new LinearRegression();
		Instances training_regr49 = null;
		modelbf_21.setFilter(rm60);
		modelbf_21.setClassifier(lr49);
		try {
			modelbf_21.buildClassifier(training_regr48);
			training_regr49 = DataHandler.getInstance().applyRegressionModel(
					training_regr48, fvAttributes, modelbf_21);
		} catch (Exception e) {
			training_regr49 = training_regr48;
		}
		
		training_regr49.setClass(fvAttributes.get(53));
		LinearRegression lr50 = new LinearRegression();
		Instances training_regr50 = null;
		modelbf_22.setFilter(rm61);
		modelbf_22.setClassifier(lr50);
		try {
			modelbf_22.buildClassifier(training_regr49);
			training_regr50 = DataHandler.getInstance().applyRegressionModel(
					training_regr49, fvAttributes, modelbf_22);
		} catch (Exception e) {
			training_regr50 = training_regr49;
		}
		
		training_regr50.setClass(fvAttributes.get(70));
		LinearRegression lr51 = new LinearRegression();
		Instances training_regr51 = null;
		modelajpgbag_01.setFilter(rm62);
		modelajpgbag_01.setClassifier(lr51);
		try {
			modelajpgbag_01.buildClassifier(training_regr50);
			training_regr51 = DataHandler.getInstance().applyRegressionModel(
					training_regr50, fvAttributes, modelajpgbag_01);
		} catch (Exception e) {
			training_regr51 = training_regr50;
		}
		
		training_regr51.setClass(fvAttributes.get(71));
		LinearRegression lr52 = new LinearRegression();
		Instances training_regr52 = null;
		modelajpgbag_02.setFilter(rm63);
		modelajpgbag_02.setClassifier(lr52);
		try {
			modelajpgbag_02.buildClassifier(training_regr51);
			training_regr52 = DataHandler.getInstance().applyRegressionModel(
					training_regr51, fvAttributes, modelajpgbag_02);
		} catch (Exception e) {
			training_regr52 = training_regr51;
		}
		
		training_regr52.setClass(fvAttributes.get(72));
		LinearRegression lr53 = new LinearRegression();
		Instances training_regr53 = null;
		modelajpgbag_03.setFilter(rm64);
		modelajpgbag_03.setClassifier(lr53);
		try {
			modelajpgbag_03.buildClassifier(training_regr52);
			training_regr53 = DataHandler.getInstance().applyRegressionModel(
					training_regr52, fvAttributes, modelajpgbag_03);
		} catch (Exception e) {
			training_regr53 = training_regr52;
		}
		
		training_regr53.setClass(fvAttributes.get(73));
		LinearRegression lr54 = new LinearRegression();
		Instances training_regr54 = null;
		modelajpgbag_04.setFilter(rm65);
		modelajpgbag_04.setClassifier(lr54);
		try {
			modelajpgbag_04.buildClassifier(training_regr53);
			training_regr54 = DataHandler.getInstance().applyRegressionModel(
					training_regr53, fvAttributes, modelajpgbag_04);
		} catch (Exception e) {
			training_regr54 = training_regr53;
		}
		
		training_regr54.setClass(fvAttributes.get(74));
		LinearRegression lr55 = new LinearRegression();
		Instances training_regr55 = null;
		modelajpgbag_05.setFilter(rm66);
		modelajpgbag_05.setClassifier(lr55);
		try {
			modelajpgbag_05.buildClassifier(training_regr54);
			training_regr55 = DataHandler.getInstance().applyRegressionModel(
					training_regr54, fvAttributes, modelajpgbag_05);
		} catch (Exception e) {
			training_regr55 = training_regr54;
		}
		
		training_regr55.setClass(fvAttributes.get(75));
		LinearRegression lr56 = new LinearRegression();
		Instances training_regr56 = null;
		modelajpgbag_06.setFilter(rm67);
		modelajpgbag_06.setClassifier(lr56);
		try {
			modelajpgbag_06.buildClassifier(training_regr55);
			training_regr56 = DataHandler.getInstance().applyRegressionModel(
					training_regr55, fvAttributes, modelajpgbag_06);
		} catch (Exception e) {
			training_regr56 = training_regr55;
		}
		
		training_regr56.setClass(fvAttributes.get(76));
		LinearRegression lr57 = new LinearRegression();
		Instances training_regr57 = null;
		modelajpgbag_07.setFilter(rm68);
		modelajpgbag_07.setClassifier(lr57);
		try {
			modelajpgbag_07.buildClassifier(training_regr56);
			training_regr57 = DataHandler.getInstance().applyRegressionModel(
					training_regr56, fvAttributes, modelajpgbag_07);
		} catch (Exception e) {
			training_regr57 = training_regr56;
		}
		
		training_regr57.setClass(fvAttributes.get(77));
		LinearRegression lr58 = new LinearRegression();
		Instances training_regr58 = null;
		modelajpgbag_08.setFilter(rm69);
		modelajpgbag_08.setClassifier(lr58);
		try {
			modelajpgbag_08.buildClassifier(training_regr57);
			training_regr58 = DataHandler.getInstance().applyRegressionModel(
					training_regr57, fvAttributes, modelajpgbag_08);
		} catch (Exception e) {
			training_regr58 = training_regr57;
		}
		
		training_regr58.setClass(fvAttributes.get(78));
		LinearRegression lr59 = new LinearRegression();
		Instances training_regr59 = null;
		modelajpgbag_09.setFilter(rm70);
		modelajpgbag_09.setClassifier(lr59);
		try {
			modelajpgbag_09.buildClassifier(training_regr58);
			training_regr59 = DataHandler.getInstance().applyRegressionModel(
					training_regr58, fvAttributes, modelajpgbag_09);
		} catch (Exception e) {
			training_regr59 = training_regr58;
		}
		
		training_regr59.setClass(fvAttributes.get(79));
		LinearRegression lr60 = new LinearRegression();
		Instances training_regr60 = null;
		modelajpgbag_10.setFilter(rm71);
		modelajpgbag_10.setClassifier(lr60);
		try {
			modelajpgbag_10.buildClassifier(training_regr59);
			training_regr60 = DataHandler.getInstance().applyRegressionModel(
					training_regr59, fvAttributes, modelajpgbag_10);
		} catch (Exception e) {
			training_regr60 = training_regr59;
		}
		
		training_regr60.setClass(fvAttributes.get(80));
		LinearRegression lr61 = new LinearRegression();
		Instances training_regr61 = null;
		modelajpgbag_11.setFilter(rm72);
		modelajpgbag_11.setClassifier(lr61);
		try {
			modelajpgbag_11.buildClassifier(training_regr60);
			training_regr61 = DataHandler.getInstance().applyRegressionModel(
					training_regr60, fvAttributes, modelajpgbag_11);
		} catch (Exception e) {
			training_regr61 = training_regr60;
		}
		
		training_regr61.setClass(fvAttributes.get(81));
		LinearRegression lr62 = new LinearRegression();
		Instances training_regr62 = null;
		modelajpgbag_12.setFilter(rm73);
		modelajpgbag_12.setClassifier(lr62);
		try {
			modelajpgbag_12.buildClassifier(training_regr61);
			training_regr62 = DataHandler.getInstance().applyRegressionModel(
					training_regr61, fvAttributes, modelajpgbag_12);
		} catch (Exception e) {
			training_regr62 = training_regr61;
		}
		
		training_regr62.setClass(fvAttributes.get(82));
		LinearRegression lr63 = new LinearRegression();
		Instances training_regr63 = null;
		modelajpgbag_13.setFilter(rm74);
		modelajpgbag_13.setClassifier(lr63);
		try {
			modelajpgbag_13.buildClassifier(training_regr62);
			training_regr63 = DataHandler.getInstance().applyRegressionModel(
					training_regr62, fvAttributes, modelajpgbag_13);
		} catch (Exception e) {
			training_regr63 = training_regr62;
		}
		
		training_regr63.setClass(fvAttributes.get(83));
		LinearRegression lr64 = new LinearRegression();
		Instances training_regr64 = null;
		modelajpgbag_14.setFilter(rm75);
		modelajpgbag_14.setClassifier(lr64);
		try {
			modelajpgbag_14.buildClassifier(training_regr63);
			training_regr64 = DataHandler.getInstance().applyRegressionModel(
					training_regr63, fvAttributes, modelajpgbag_14);
		} catch (Exception e) {
			training_regr64 = training_regr63;
		}
		
		training_regr64.setClass(fvAttributes.get(84));
		LinearRegression lr65 = new LinearRegression();
		Instances training_regr65 = null;
		modelajpgbag_15.setFilter(rm76);
		modelajpgbag_15.setClassifier(lr65);
		try {
			modelajpgbag_15.buildClassifier(training_regr64);
			training_regr65 = DataHandler.getInstance().applyRegressionModel(
					training_regr64, fvAttributes, modelajpgbag_15);
		} catch (Exception e) {
			training_regr65 = training_regr64;
		}
		
		training_regr65.setClass(fvAttributes.get(85));
		LinearRegression lr66 = new LinearRegression();
		Instances training_regr66 = null;
		modelajpgbag_16.setFilter(rm77);
		modelajpgbag_16.setClassifier(lr66);
		try {
			modelajpgbag_16.buildClassifier(training_regr65);
			training_regr66 = DataHandler.getInstance().applyRegressionModel(
					training_regr65, fvAttributes, modelajpgbag_16);
		} catch (Exception e) {
			training_regr66 = training_regr65;
		}
		
		training_regr66.setClass(fvAttributes.get(86));
		LinearRegression lr67 = new LinearRegression();
		Instances training_regr67 = null;
		modelnajpgbag_01.setFilter(rm78);
		modelnajpgbag_01.setClassifier(lr67);
		try {
			modelnajpgbag_01.buildClassifier(training_regr66);
			training_regr67 = DataHandler.getInstance().applyRegressionModel(
					training_regr66, fvAttributes, modelnajpgbag_01);
		} catch (Exception e) {
			training_regr67 = training_regr66;
		}
		
		training_regr67.setClass(fvAttributes.get(87));
		LinearRegression lr68 = new LinearRegression();
		Instances training_regr68 = null;
		modelnajpgbag_02.setFilter(rm79);
		modelnajpgbag_02.setClassifier(lr68);
		try {
			modelnajpgbag_02.buildClassifier(training_regr67);
			training_regr68 = DataHandler.getInstance().applyRegressionModel(
					training_regr67, fvAttributes, modelnajpgbag_02);
		} catch (Exception e) {
			training_regr68 = training_regr67;
		}
		
		training_regr68.setClass(fvAttributes.get(88));
		LinearRegression lr69 = new LinearRegression();
		Instances training_regr69 = null;
		modelnajpgbag_03.setFilter(rm80);
		modelnajpgbag_03.setClassifier(lr69);
		try {
			modelnajpgbag_03.buildClassifier(training_regr68);
			training_regr69 = DataHandler.getInstance().applyRegressionModel(
					training_regr68, fvAttributes, modelnajpgbag_03);
		} catch (Exception e) {
			training_regr69 = training_regr68;
		}
		
		training_regr69.setClass(fvAttributes.get(89));
		LinearRegression lr70 = new LinearRegression();
		Instances training_regr70 = null;
		modelnajpgbag_04.setFilter(rm81);
		modelnajpgbag_04.setClassifier(lr70);
		try {
			modelnajpgbag_04.buildClassifier(training_regr69);
			training_regr70 = DataHandler.getInstance().applyRegressionModel(
					training_regr69, fvAttributes, modelnajpgbag_04);
		} catch (Exception e) {
			training_regr70 = training_regr69;
		}
		
		training_regr70.setClass(fvAttributes.get(90));
		LinearRegression lr71 = new LinearRegression();
		Instances training_regr71 = null;
		modelnajpgbag_05.setFilter(rm82);
		modelnajpgbag_05.setClassifier(lr71);
		try {
			modelnajpgbag_05.buildClassifier(training_regr70);
			training_regr71 = DataHandler.getInstance().applyRegressionModel(
					training_regr70, fvAttributes, modelnajpgbag_05);
		} catch (Exception e) {
			training_regr71 = training_regr70;
		}
		
		training_regr71.setClass(fvAttributes.get(91));
		LinearRegression lr72 = new LinearRegression();
		Instances training_regr72 = null;
		modelnajpgbag_06.setFilter(rm83);
		modelnajpgbag_06.setClassifier(lr72);
		try {
			modelnajpgbag_06.buildClassifier(training_regr71);
			training_regr72 = DataHandler.getInstance().applyRegressionModel(
					training_regr71, fvAttributes, modelnajpgbag_06);
		} catch (Exception e) {
			training_regr72 = training_regr71;
		}
		
		training_regr72.setClass(fvAttributes.get(92));
		LinearRegression lr73 = new LinearRegression();
		Instances training_regr73 = null;
		modelnajpgbag_07.setFilter(rm84);
		modelnajpgbag_07.setClassifier(lr73);
		try {
			modelnajpgbag_07.buildClassifier(training_regr72);
			training_regr73 = DataHandler.getInstance().applyRegressionModel(
					training_regr72, fvAttributes, modelnajpgbag_07);
		} catch (Exception e) {
			training_regr73 = training_regr72;
		}
		
		training_regr73.setClass(fvAttributes.get(93));
		LinearRegression lr74 = new LinearRegression();
		Instances training_regr74 = null;
		modelnajpgbag_08.setFilter(rm85);
		modelnajpgbag_08.setClassifier(lr74);
		try {
			modelnajpgbag_08.buildClassifier(training_regr73);
			training_regr74 = DataHandler.getInstance().applyRegressionModel(
					training_regr73, fvAttributes, modelnajpgbag_08);
		} catch (Exception e) {
			training_regr74 = training_regr73;
		}
		
		training_regr74.setClass(fvAttributes.get(94));
		LinearRegression lr75 = new LinearRegression();
		Instances training_regr75 = null;
		modelnajpgbag_09.setFilter(rm86);
		modelnajpgbag_09.setClassifier(lr75);
		try {
			modelnajpgbag_09.buildClassifier(training_regr74);
			training_regr75 = DataHandler.getInstance().applyRegressionModel(
					training_regr74, fvAttributes, modelnajpgbag_09);
		} catch (Exception e) {
			training_regr75 = training_regr74;
		}
		
		training_regr75.setClass(fvAttributes.get(95));
		LinearRegression lr76 = new LinearRegression();
		Instances training_regr76 = null;
		modelnajpgbag_10.setFilter(rm87);
		modelnajpgbag_10.setClassifier(lr76);
		try {
			modelnajpgbag_10.buildClassifier(training_regr75);
			training_regr76 = DataHandler.getInstance().applyRegressionModel(
					training_regr75, fvAttributes, modelnajpgbag_10);
		} catch (Exception e) {
			training_regr76 = training_regr75;
		}
		
		training_regr76.setClass(fvAttributes.get(96));
		LinearRegression lr77 = new LinearRegression();
		Instances training_regr77 = null;
		modelnajpgbag_11.setFilter(rm88);
		modelnajpgbag_11.setClassifier(lr77);
		try {
			modelnajpgbag_11.buildClassifier(training_regr76);
			training_regr77 = DataHandler.getInstance().applyRegressionModel(
					training_regr76, fvAttributes, modelnajpgbag_11);
		} catch (Exception e) {
			training_regr77 = training_regr76;
		}
		
		training_regr77.setClass(fvAttributes.get(97));
		LinearRegression lr78 = new LinearRegression();
		Instances training_regr78 = null;
		modelnajpgbag_12.setFilter(rm89);
		modelnajpgbag_12.setClassifier(lr78);
		try {
			modelnajpgbag_12.buildClassifier(training_regr77);
			training_regr78 = DataHandler.getInstance().applyRegressionModel(
					training_regr77, fvAttributes, modelnajpgbag_12);
		} catch (Exception e) {
			training_regr78 = training_regr77;
		}
		
		training_regr78.setClass(fvAttributes.get(98));
		LinearRegression lr79 = new LinearRegression();
		Instances training_regr79 = null;
		modelnajpgbag_13.setFilter(rm90);
		modelnajpgbag_13.setClassifier(lr79);
		try {
			modelnajpgbag_13.buildClassifier(training_regr78);
			training_regr79 = DataHandler.getInstance().applyRegressionModel(
					training_regr78, fvAttributes, modelnajpgbag_13);
		} catch (Exception e) {
			training_regr79 = training_regr78;
		}
		
		training_regr79.setClass(fvAttributes.get(99));
		LinearRegression lr80 = new LinearRegression();
		Instances training_regr80 = null;
		modelnajpgbag_14.setFilter(rm91);
		modelnajpgbag_14.setClassifier(lr80);
		try {
			modelnajpgbag_14.buildClassifier(training_regr79);
			training_regr80 = DataHandler.getInstance().applyRegressionModel(
					training_regr79, fvAttributes, modelnajpgbag_14);
		} catch (Exception e) {
			training_regr80 = training_regr79;
		}
		
		training_regr80.setClass(fvAttributes.get(100));
		LinearRegression lr81 = new LinearRegression();
		Instances training_regr81 = null;
		modelnajpgbag_15.setFilter(rm92);
		modelnajpgbag_15.setClassifier(lr81);
		try {
			modelnajpgbag_15.buildClassifier(training_regr80);
			training_regr81 = DataHandler.getInstance().applyRegressionModel(
					training_regr80, fvAttributes, modelnajpgbag_15);
		} catch (Exception e) {
			training_regr81 = training_regr80;
		}
		
		training_regr81.setClass(fvAttributes.get(101));
		LinearRegression lr82 = new LinearRegression();
		Instances training_regr82 = null;
		modelnajpgbag_16.setFilter(rm93);
		modelnajpgbag_16.setClassifier(lr82);
		try {
			modelnajpgbag_16.buildClassifier(training_regr81);
			training_regr82 = DataHandler.getInstance().applyRegressionModel(
					training_regr81, fvAttributes, modelnajpgbag_16);
		} catch (Exception e) {
			training_regr82 = training_regr81;
		}
		
		//user models for user features
		
		training_regr82.setClass(fvAttributes.get(112));
		LinearRegression lr83 = new LinearRegression();
		Instances training_regr83 = null;
		usermodel.setFilter(rm);
		usermodel.setClassifier(lr83);

		try {
			usermodel.buildClassifier(training_regr82);
			training_regr83 = DataHandler.getInstance().applyRegressionModel(
					training_regr82, fvAttributes, usermodel);
		} catch (Exception e) {
			training_regr83 = training_regr82;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		training_regr83.setClass(fvAttributes.get(114));
		LinearRegression lr84 = new LinearRegression();
		Instances training_regr84 = null;
		usermodel2.setFilter(rm1);
		usermodel2.setClassifier(lr84);
		try {
			usermodel2.buildClassifier(training_regr83);
			training_regr84 = DataHandler.getInstance().applyRegressionModel(
					training_regr83, fvAttributes, usermodel2);
		} catch (Exception e) {
			training_regr84 = training_regr83;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		training_regr84.setClass(fvAttributes.get(117));
		LinearRegression lr85 = new LinearRegression();
		Instances training_regr85 = null;
		usermodel3.setFilter(rm2);
		usermodel3.setClassifier(lr85);
		try {
			usermodel3.buildClassifier(training_regr84);
			training_regr85 = DataHandler.getInstance().applyRegressionModel(
					training_regr84, fvAttributes, usermodel3);
		} catch (Exception e) {
			training_regr85 = training_regr84;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		training_regr85.setClass(fvAttributes.get(118));
		LinearRegression lr86 = new LinearRegression();
		Instances training_regr86 = null;
		usermodel4.setFilter(rm3);
		usermodel4.setClassifier(lr86);
		try {
			usermodel4.buildClassifier(training_regr85);
			training_regr86 = DataHandler.getInstance().applyRegressionModel(
					training_regr85, fvAttributes, usermodel4);
		} catch (Exception e) {
			training_regr86 = training_regr85;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		// System.out.println(usermodel4);

		training_regr86.setClass(fvAttributes.get(119));
		LinearRegression lr87 = new LinearRegression();
		Instances training_regr87 = null;
		usermodel5.setFilter(rm4);
		usermodel5.setClassifier(lr87);
		try {
			usermodel5.buildClassifier(training_regr86);
			training_regr87 = DataHandler.getInstance().applyRegressionModel(
					training_regr86, fvAttributes, usermodel5);
		} catch (Exception e) {
			training_regr87 = training_regr86;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		// alexa country rank
		training_regr87.setClass(fvAttributes.get(120));
		LinearRegression lr88 = new LinearRegression();
		Instances training_regr88 = null;
		usermodel6.setFilter(rm5);
		usermodel6.setClassifier(lr88);
		try {
			usermodel6.buildClassifier(training_regr87);
			training_regr88 = DataHandler.getInstance().applyRegressionModel(
					training_regr87, fvAttributes, usermodel6);
		} catch (Exception e) {
			training_regr88 = training_regr87;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}

		// alexa delta rank
		training_regr88.setClass(fvAttributes.get(121));
		LinearRegression lr89 = new LinearRegression();
		Instances training_regr89 = null;
		usermodel7.setFilter(rm6);
		usermodel7.setClassifier(lr89);
		try {
			usermodel7.buildClassifier(training_regr88);
			training_regr89 = DataHandler.getInstance().applyRegressionModel(
					training_regr88, fvAttributes, usermodel7);
		} catch (Exception e) {
			training_regr89 = training_regr88;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		// alexa popularity
		training_regr89.setClass(fvAttributes.get(122));
		LinearRegression lr90 = new LinearRegression();
		Instances training_regr90 = null;
		usermodel8.setFilter(rm7);
		usermodel8.setClassifier(lr90);
		try {
			usermodel8.buildClassifier(training_regr89);
			training_regr90 = DataHandler.getInstance().applyRegressionModel(
					training_regr89, fvAttributes, usermodel8);
		} catch (Exception e) {
			training_regr90 = training_regr89;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		// alexa reach rank
		training_regr90.setClass(fvAttributes.get(123));
		LinearRegression lr91 = new LinearRegression();
		Instances training_regr91 = null;
		usermodel9.setFilter(rm8);
		usermodel9.setClassifier(lr91);
		try {
			usermodel9.buildClassifier(training_regr90);
			training_regr91 = DataHandler.getInstance().applyRegressionModel(
					training_regr90, fvAttributes, usermodel9);
		} catch (Exception e) {
			training_regr91 = training_regr90;
			// System.out.println("not enough training instances. Linear Regression not performed!");
		}
		
		
		// normalization part
		String[] options = { "-S", "1.0", "-T", "0.0" };
		normFilter.setOptions(options);
		normFilter.setInputFormat(training_regr91);
	
		
		Instances trainingSet_normed = DataHandler.getInstance().normalizeData(
				training_regr91, fvAttributes.size() - 1, normFilter);
		
		for (int i=0; i<trainingSet_normed.size(); i++) {
			//System.out.println(trainingSet_normed.get(i));
			for (int j=0; j<trainingSet_normed.get(i).numAttributes();j++) {
				if (!trainingSet_normed.get(i).attribute(j).isNominal())
				trainingSet_normed.get(i).setValue(j, round(trainingSet_normed.get(i).value(j), 6) );
			}
			//System.out.println(trainingSet_normed.get(i));
		}
		
		trainingSet_normed = getTrimmedInstances(trainingSet_normed);
		
		return trainingSet_normed;
	
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
	public Instances getTransformedTestingExtended(Instances testing) throws Exception {
	
		ArrayList<Attribute> fvAttributes = ItemClassifier.getFvAttributes();
		Instances testing_regr = null, testing_regr2 = null, testing_regr3 = null, testing_regr4 = null, testing_regr5 = null, 
				testing_regr6 = null, testing_regr7 = null, testing_regr8 = null, testing_regr9 = null, testing_regr10 = null,
						testing_regr11 = null, testing_regr12 = null, testing_regr13 = null, testing_regr14 = null, testing_regr15 = null,
								testing_regr16 = null, testing_regr17 = null, testing_regr18 = null, testing_regr19 = null, testing_regr20 = null,
										 testing_regr21 = null, testing_regr22 = null, testing_regr23 = null, testing_regr24 = null, testing_regr25 = null, testing_regr26 = null,
												 testing_regr27 = null, testing_regr28 = null, testing_regr29 = null, testing_regr30 = null, testing_regr31 = null, testing_regr32 = null
														 , testing_regr33 = null, testing_regr34 = null, testing_regr35 = null, testing_regr36 = null, testing_regr37 = null, testing_regr38 = null
																 , testing_regr39 = null, testing_regr40 = null, testing_regr41 = null, testing_regr42 = null, testing_regr43 = null, testing_regr44 = null
																		 , testing_regr45 = null, testing_regr46 = null, testing_regr47 = null, testing_regr48 = null, testing_regr49 = null, testing_regr50 = null
		, testing_regr51 = null, testing_regr52 = null, testing_regr53 = null, testing_regr54 = null, testing_regr55 = null, testing_regr56 = null, testing_regr57 = null, testing_regr58 = null, testing_regr59 = null
				, testing_regr60 = null, testing_regr61 = null, testing_regr62 = null, testing_regr63 = null, testing_regr64 = null, testing_regr65 = null, testing_regr66 = null, testing_regr67 = null, testing_regr68 = null, testing_regr69 = null,
		testing_regr70 = null, testing_regr71 = null, testing_regr72 = null, testing_regr73 = null, testing_regr74 = null, testing_regr75 = null, testing_regr76 = null, testing_regr77 = null, testing_regr78 = null,
				 testing_regr79 = null, testing_regr80 = null, testing_regr81 = null, testing_regr82 = null, testing_regr83 = null, testing_regr84 = null, testing_regr85 = null, testing_regr86 = null
						 , testing_regr87 = null, testing_regr88 = null, testing_regr89 = null, testing_regr90 = null, testing_regr91 = null;
		// regression
		if (!model.toString().contains("No model built yet.")) {
			testing.setClass(fvAttributes.get(22));
			testing_regr = DataHandler.getInstance().applyRegressionModel(
					testing, fvAttributes, model);
		} else {
			testing_regr = testing;
		}
	
		if (!model2.toString().contains("No model built yet.")) {
			testing_regr.setClass(fvAttributes.get(25));
			testing_regr2 = DataHandler.getInstance().applyRegressionModel(
					testing_regr, fvAttributes, model2);
		} else {
			testing_regr2 = testing_regr;
		}
	
		if (!model3.toString().contains("No model built yet.")) {
			testing_regr2.setClass(fvAttributes.get(30));
			testing_regr3 = DataHandler.getInstance().applyRegressionModel(
					testing_regr2, fvAttributes, model3);
		} else {
			testing_regr3 = testing_regr2;
		}
	
		if (!model4.toString().contains("No model built yet.")) {
			testing_regr3.setClass(fvAttributes.get(29));
			testing_regr4 = DataHandler.getInstance().applyRegressionModel(
					testing_regr3, fvAttributes, model4);
		} else {
			testing_regr4 = testing_regr3;
		}
	
		if (!model5.toString().contains("No model built yet.")) {
			testing_regr4.setClass(fvAttributes.get(14));
			testing_regr5 = DataHandler.getInstance().applyRegressionModel(
					testing_regr4, fvAttributes, model5);
		} else {
			testing_regr5 = testing_regr4;
		}
	
		if (!model6.toString().contains("No model built yet.")) {
			testing_regr5.setClass(fvAttributes.get(19));
			testing_regr6 = DataHandler.getInstance().applyRegressionModel(
					testing_regr5, fvAttributes, model6);
		} else {
			testing_regr6 = testing_regr5;
		}
	
		if (!model7.toString().contains("No model built yet.")) {
			testing_regr6.setClass(fvAttributes.get(26));
			testing_regr7 = DataHandler.getInstance().applyRegressionModel(
					testing_regr6, fvAttributes, model7);
		} else {
			testing_regr7 = testing_regr6;
		}
	
		if (!model8.toString().contains("No model built yet.")) {
			testing_regr7.setClass(fvAttributes.get(28));
			testing_regr8 = DataHandler.getInstance().applyRegressionModel(
					testing_regr7, fvAttributes, model8);
		} else {
			testing_regr8 = testing_regr7;
		}
	
		if (!model9.toString().contains("No model built yet.")) {
			testing_regr8.setClass(fvAttributes.get(27));
			testing_regr9 = DataHandler.getInstance().applyRegressionModel(
					testing_regr8, fvAttributes, model9);
		} else {
			testing_regr9 = testing_regr8;
		}
		
		if (!model10.toString().contains("No model built yet.")) {
			testing_regr9.setClass(fvAttributes.get(13));
			testing_regr10 = DataHandler.getInstance().applyRegressionModel(
					testing_regr9, fvAttributes, model10);
		} else {
			testing_regr10 = testing_regr9;
		}
		
		if (!model11.toString().contains("No model built yet.")) {
			testing_regr10.setClass(fvAttributes.get(31));
			testing_regr11 = DataHandler.getInstance().applyRegressionModel(
					testing_regr10, fvAttributes, model11);
		} else {
			testing_regr11 = testing_regr10;
		}
		
		if (!model12.toString().contains("No model built yet.")) {
			testing_regr11.setClass(fvAttributes.get(6));
			testing_regr12 = DataHandler.getInstance().applyRegressionModel(
					testing_regr11, fvAttributes, model12);
		} else {
			testing_regr12 = testing_regr11;
		}
		
		if (!modelsad_01.toString().contains("No model built yet.")) {
			testing_regr12.setClass(fvAttributes.get(54));
			testing_regr13 = DataHandler.getInstance().applyRegressionModel(
					testing_regr12, fvAttributes, modelsad_01);
		} else {
			testing_regr13 = testing_regr12;
		}
		
		if (!modelsad_02.toString().contains("No model built yet.")) {
			testing_regr13.setClass(fvAttributes.get(55));
			testing_regr14 = DataHandler.getInstance().applyRegressionModel(
					testing_regr13, fvAttributes, modelsad_02);
		} else {
			testing_regr14 = testing_regr13;
		}
		
		if (!modelsad_03.toString().contains("No model built yet.")) {
			testing_regr14.setClass(fvAttributes.get(56));
			testing_regr15 = DataHandler.getInstance().applyRegressionModel(
					testing_regr14, fvAttributes, modelsad_03);
		} else {
			testing_regr15 = testing_regr14;
		}
		
		if (!modelsad_04.toString().contains("No model built yet.")) {
			testing_regr15.setClass(fvAttributes.get(57));
			testing_regr16 = DataHandler.getInstance().applyRegressionModel(
					testing_regr15, fvAttributes, modelsad_04);
		} else {
			testing_regr16 = testing_regr15;
		}
		
		if (!modelsad_05.toString().contains("No model built yet.")) {
			testing_regr16.setClass(fvAttributes.get(58));
			testing_regr17 = DataHandler.getInstance().applyRegressionModel(
					testing_regr16, fvAttributes, modelsad_05);
		} else {
			testing_regr17 = testing_regr16;
		}
		
		if (!modelsad_06.toString().contains("No model built yet.")) {
			testing_regr17.setClass(fvAttributes.get(59));
			testing_regr18 = DataHandler.getInstance().applyRegressionModel(
					testing_regr17, fvAttributes, modelsad_06);
		} else {
			testing_regr18 = testing_regr17;
		}
		
		if (!modelsad_07.toString().contains("No model built yet.")) {
			testing_regr18.setClass(fvAttributes.get(60));
			testing_regr19 = DataHandler.getInstance().applyRegressionModel(
					testing_regr18, fvAttributes, modelsad_07);
		} else {
			testing_regr19 = testing_regr18;
		}
		
		if (!modelsad_08.toString().contains("No model built yet.")) {
			testing_regr19.setClass(fvAttributes.get(61));
			testing_regr20 = DataHandler.getInstance().applyRegressionModel(
					testing_regr19, fvAttributes, modelsad_08);
		} else {
			testing_regr20 = testing_regr19;
		}
		
		if (!modelsnad_01.toString().contains("No model built yet.")) {
			testing_regr20.setClass(fvAttributes.get(62));
			testing_regr21 = DataHandler.getInstance().applyRegressionModel(
					testing_regr20, fvAttributes, modelsnad_01);
		} else {
			testing_regr21 = testing_regr20;
		}
		
		if (!modelsnad_02.toString().contains("No model built yet.")) {
			testing_regr21.setClass(fvAttributes.get(63));
			testing_regr22 = DataHandler.getInstance().applyRegressionModel(
					testing_regr21, fvAttributes, modelsnad_02);
		} else {
			testing_regr22 = testing_regr21;
		}
		
		if (!modelsnad_03.toString().contains("No model built yet.")) {
			testing_regr22.setClass(fvAttributes.get(64));
			testing_regr23 = DataHandler.getInstance().applyRegressionModel(
					testing_regr22, fvAttributes, modelsnad_03);
		} else {
			testing_regr23 = testing_regr22;
		}
		
		if (!modelsnad_04.toString().contains("No model built yet.")) {
			testing_regr23.setClass(fvAttributes.get(65));
			testing_regr24 = DataHandler.getInstance().applyRegressionModel(
					testing_regr23, fvAttributes, modelsnad_04);
		} else {
			testing_regr24 = testing_regr23;
		}
		
		if (!modelsnad_05.toString().contains("No model built yet.")) {
			testing_regr24.setClass(fvAttributes.get(66));
			testing_regr25 = DataHandler.getInstance().applyRegressionModel(
					testing_regr24, fvAttributes, modelsnad_05);
		} else {
			testing_regr25 = testing_regr24;
		}
		
		if (!modelsnad_06.toString().contains("No model built yet.")) {
			testing_regr25.setClass(fvAttributes.get(67));
			testing_regr26 = DataHandler.getInstance().applyRegressionModel(
					testing_regr25, fvAttributes, modelsnad_06);
		} else {
			testing_regr26 = testing_regr25;
		}
		
		if (!modelsnad_07.toString().contains("No model built yet.")) {
			testing_regr26.setClass(fvAttributes.get(68));
			testing_regr27 = DataHandler.getInstance().applyRegressionModel(
					testing_regr26, fvAttributes, modelsnad_07);
		} else {
			testing_regr27 = testing_regr26;
		}
		
		if (!modelsnad_08.toString().contains("No model built yet.")) {
			testing_regr27.setClass(fvAttributes.get(69));
			testing_regr28 = DataHandler.getInstance().applyRegressionModel(
					testing_regr27, fvAttributes, modelsnad_08);
		} else {
			testing_regr28 = testing_regr27;
		}
		
		if (!modelbf_01.toString().contains("No model built yet.")) {
			testing_regr28.setClass(fvAttributes.get(32));
			testing_regr29 = DataHandler.getInstance().applyRegressionModel(
					testing_regr28, fvAttributes, modelbf_01);
		} else {
			testing_regr29 = testing_regr28;
		}
		
		if (!modelbf_02.toString().contains("No model built yet.")) {
			testing_regr29.setClass(fvAttributes.get(33));
			testing_regr30 = DataHandler.getInstance().applyRegressionModel(
					testing_regr29, fvAttributes, modelbf_02);
		} else {
			testing_regr30 = testing_regr29;
		}
		
		if (!modelbf_03.toString().contains("No model built yet.")) {
			testing_regr30.setClass(fvAttributes.get(34));
			testing_regr31 = DataHandler.getInstance().applyRegressionModel(
					testing_regr30, fvAttributes, modelbf_03);
		} else {
			testing_regr31 = testing_regr30;
		}
		
		if (!modelbf_04.toString().contains("No model built yet.")) {
			testing_regr31.setClass(fvAttributes.get(35));
			testing_regr32 = DataHandler.getInstance().applyRegressionModel(
					testing_regr31, fvAttributes, modelbf_04);
		} else {
			testing_regr32 = testing_regr31;
		}
		
		if (!modelbf_05.toString().contains("No model built yet.")) {
			testing_regr32.setClass(fvAttributes.get(36));
			testing_regr33 = DataHandler.getInstance().applyRegressionModel(
					testing_regr32, fvAttributes, modelbf_05);
		} else {
			testing_regr33 = testing_regr32;
		}
		
		if (!modelbf_06.toString().contains("No model built yet.")) {
			testing_regr33.setClass(fvAttributes.get(37));
			testing_regr34 = DataHandler.getInstance().applyRegressionModel(
					testing_regr33, fvAttributes, modelbf_06);
		} else {
			testing_regr34 = testing_regr33;
		}
		
		if (!modelbf_07.toString().contains("No model built yet.")) {
			testing_regr34.setClass(fvAttributes.get(38));
			testing_regr35 = DataHandler.getInstance().applyRegressionModel(
					testing_regr34, fvAttributes, modelbf_07);
		} else {
			testing_regr35 = testing_regr34;
		}
		
		if (!modelbf_08.toString().contains("No model built yet.")) {
			testing_regr35.setClass(fvAttributes.get(39));
			testing_regr36 = DataHandler.getInstance().applyRegressionModel(
					testing_regr35, fvAttributes, modelbf_08);
		} else {
			testing_regr36 = testing_regr35;
		}
		
		if (!modelbf_09.toString().contains("No model built yet.")) {
			testing_regr36.setClass(fvAttributes.get(40));
			testing_regr37 = DataHandler.getInstance().applyRegressionModel(
					testing_regr36, fvAttributes, modelbf_09);
		} else {
			testing_regr37 = testing_regr36;
		}
		
		if (!modelbf_10.toString().contains("No model built yet.")) {
			testing_regr37.setClass(fvAttributes.get(41));
			testing_regr38 = DataHandler.getInstance().applyRegressionModel(
					testing_regr37, fvAttributes, modelbf_10);
		} else {
			testing_regr38 = testing_regr37;
		}
		
		if (!modelbf_11.toString().contains("No model built yet.")) {
			testing_regr38.setClass(fvAttributes.get(42));
			testing_regr39 = DataHandler.getInstance().applyRegressionModel(
					testing_regr38, fvAttributes, modelbf_11);
		} else {
			testing_regr39 = testing_regr38;
		}
		
		if (!modelbf_12.toString().contains("No model built yet.")) {
			testing_regr39.setClass(fvAttributes.get(43));
			testing_regr40 = DataHandler.getInstance().applyRegressionModel(
					testing_regr39, fvAttributes, modelbf_12);
		} else {
			testing_regr40 = testing_regr39;
		}
		
		if (!modelbf_13.toString().contains("No model built yet.")) {
			testing_regr40.setClass(fvAttributes.get(44));
			testing_regr41 = DataHandler.getInstance().applyRegressionModel(
					testing_regr40, fvAttributes, modelbf_13);
		} else {
			testing_regr41 = testing_regr40;
		}
		
		if (!modelbf_14.toString().contains("No model built yet.")) {
			testing_regr41.setClass(fvAttributes.get(45));
			testing_regr42 = DataHandler.getInstance().applyRegressionModel(
					testing_regr41, fvAttributes, modelbf_14);
		} else {
			testing_regr42 = testing_regr41;
		}
		
		if (!modelbf_15.toString().contains("No model built yet.")) {
			testing_regr42.setClass(fvAttributes.get(46));
			testing_regr43 = DataHandler.getInstance().applyRegressionModel(
					testing_regr42, fvAttributes, modelbf_15);
		} else {
			testing_regr43 = testing_regr42;
		}
		
		if (!modelbf_16.toString().contains("No model built yet.")) {
			testing_regr43.setClass(fvAttributes.get(47));
			testing_regr44 = DataHandler.getInstance().applyRegressionModel(
					testing_regr43, fvAttributes, modelbf_16);
		} else {
			testing_regr44 = testing_regr43;
		}
		
		if (!modelbf_17.toString().contains("No model built yet.")) {
			testing_regr44.setClass(fvAttributes.get(48));
			testing_regr45 = DataHandler.getInstance().applyRegressionModel(
					testing_regr44, fvAttributes, modelbf_17);
		} else {
			testing_regr45 = testing_regr44;
		}
		
		if (!modelbf_18.toString().contains("No model built yet.")) {
			testing_regr45.setClass(fvAttributes.get(49));
			testing_regr46 = DataHandler.getInstance().applyRegressionModel(
					testing_regr45, fvAttributes, modelbf_18);
		} else {
			testing_regr46 = testing_regr45;
		}
		
		if (!modelbf_19.toString().contains("No model built yet.")) {
			testing_regr46.setClass(fvAttributes.get(50));
			testing_regr47 = DataHandler.getInstance().applyRegressionModel(
					testing_regr46, fvAttributes, modelbf_19);
		} else {
			testing_regr47 = testing_regr46;
		}
		
		if (!modelbf_20.toString().contains("No model built yet.")) {
			testing_regr47.setClass(fvAttributes.get(51));
			testing_regr48 = DataHandler.getInstance().applyRegressionModel(
					testing_regr47, fvAttributes, modelbf_20);
		} else {
			testing_regr48 = testing_regr47;
		}
		
		if (!modelbf_21.toString().contains("No model built yet.")) {
			testing_regr48.setClass(fvAttributes.get(52));
			testing_regr49 = DataHandler.getInstance().applyRegressionModel(
					testing_regr48, fvAttributes, modelbf_21);
		} else {
			testing_regr49 = testing_regr48;
		}
		
		if (!modelbf_22.toString().contains("No model built yet.")) {
			testing_regr49.setClass(fvAttributes.get(53));
			testing_regr50 = DataHandler.getInstance().applyRegressionModel(
					testing_regr49, fvAttributes, modelbf_22);
		} else {
			testing_regr50 = testing_regr49;
		}
		
		if (!modelajpgbag_01.toString().contains("No model built yet.")) {
			testing_regr50.setClass(fvAttributes.get(70));
			testing_regr51 = DataHandler.getInstance().applyRegressionModel(
					testing_regr50, fvAttributes, modelajpgbag_01);
		} else {
			testing_regr51 = testing_regr50;
		}
		
		if (!modelajpgbag_02.toString().contains("No model built yet.")) {
			testing_regr51.setClass(fvAttributes.get(71));
			testing_regr52 = DataHandler.getInstance().applyRegressionModel(
					testing_regr51, fvAttributes, modelajpgbag_02);
		} else {
			testing_regr52 = testing_regr51;
		}
		
		if (!modelajpgbag_03.toString().contains("No model built yet.")) {
			testing_regr52.setClass(fvAttributes.get(72));
			testing_regr53 = DataHandler.getInstance().applyRegressionModel(
					testing_regr52, fvAttributes, modelajpgbag_03);
		} else {
			testing_regr53 = testing_regr52;
		}
		
		if (!modelajpgbag_04.toString().contains("No model built yet.")) {
			testing_regr53.setClass(fvAttributes.get(73));
			testing_regr54 = DataHandler.getInstance().applyRegressionModel(
					testing_regr53, fvAttributes, modelajpgbag_04);
		} else {
			testing_regr54 = testing_regr53;
		}
		
		if (!modelajpgbag_05.toString().contains("No model built yet.")) {
			testing_regr54.setClass(fvAttributes.get(74));
			testing_regr55 = DataHandler.getInstance().applyRegressionModel(
					testing_regr54, fvAttributes, modelajpgbag_05);
		} else {
			testing_regr55 = testing_regr54;
		}
		
		if (!modelajpgbag_06.toString().contains("No model built yet.")) {
			testing_regr55.setClass(fvAttributes.get(75));
			testing_regr56 = DataHandler.getInstance().applyRegressionModel(
					testing_regr55, fvAttributes, modelajpgbag_06);
		} else {
			testing_regr56 = testing_regr55;
		}
		
		if (!modelajpgbag_07.toString().contains("No model built yet.")) {
			testing_regr56.setClass(fvAttributes.get(76));
			testing_regr57 = DataHandler.getInstance().applyRegressionModel(
					testing_regr56, fvAttributes, modelajpgbag_07);
		} else {
			testing_regr57 = testing_regr56;
		}
		
		if (!modelajpgbag_08.toString().contains("No model built yet.")) {
			testing_regr57.setClass(fvAttributes.get(77));
			testing_regr58 = DataHandler.getInstance().applyRegressionModel(
					testing_regr57, fvAttributes, modelajpgbag_08);
		} else {
			testing_regr58 = testing_regr57;
		}
		
		if (!modelajpgbag_09.toString().contains("No model built yet.")) {
			testing_regr58.setClass(fvAttributes.get(78));
			testing_regr59 = DataHandler.getInstance().applyRegressionModel(
					testing_regr58, fvAttributes, modelajpgbag_09);
		} else {
			testing_regr59 = testing_regr58;
		}
		
		if (!modelajpgbag_10.toString().contains("No model built yet.")) {
			testing_regr59.setClass(fvAttributes.get(79));
			testing_regr60 = DataHandler.getInstance().applyRegressionModel(
					testing_regr59, fvAttributes, modelajpgbag_10);
		} else {
			testing_regr60 = testing_regr59;
		}
		
		if (!modelajpgbag_11.toString().contains("No model built yet.")) {
			testing_regr60.setClass(fvAttributes.get(80));
			testing_regr61 = DataHandler.getInstance().applyRegressionModel(
					testing_regr60, fvAttributes, modelajpgbag_11);
		} else {
			testing_regr61 = testing_regr60;
		}
		
		if (!modelajpgbag_12.toString().contains("No model built yet.")) {
			testing_regr61.setClass(fvAttributes.get(81));
			testing_regr62 = DataHandler.getInstance().applyRegressionModel(
					testing_regr61, fvAttributes, modelajpgbag_12);
		} else {
			testing_regr62 = testing_regr61;
		}
		
		if (!modelajpgbag_13.toString().contains("No model built yet.")) {
			testing_regr62.setClass(fvAttributes.get(82));
			testing_regr63 = DataHandler.getInstance().applyRegressionModel(
					testing_regr62, fvAttributes, modelajpgbag_13);
		} else {
			testing_regr63 = testing_regr62;
		}
		
		if (!modelajpgbag_14.toString().contains("No model built yet.")) {
			testing_regr63.setClass(fvAttributes.get(83));
			testing_regr64 = DataHandler.getInstance().applyRegressionModel(
					testing_regr63, fvAttributes, modelajpgbag_14);
		} else {
			testing_regr64 = testing_regr63;
		}
		
		if (!modelajpgbag_15.toString().contains("No model built yet.")) {
			testing_regr64.setClass(fvAttributes.get(84));
			testing_regr65 = DataHandler.getInstance().applyRegressionModel(
					testing_regr64, fvAttributes, modelajpgbag_15);
		} else {
			testing_regr65 = testing_regr64;
		}
		
		if (!modelajpgbag_16.toString().contains("No model built yet.")) {
			testing_regr65.setClass(fvAttributes.get(85));
			testing_regr66 = DataHandler.getInstance().applyRegressionModel(
					testing_regr65, fvAttributes, modelajpgbag_16);
		} else {
			testing_regr66 = testing_regr65;
		}
		
		if (!modelnajpgbag_01.toString().contains("No model built yet.")) {
			testing_regr66.setClass(fvAttributes.get(86));
			testing_regr67 = DataHandler.getInstance().applyRegressionModel(
					testing_regr66, fvAttributes, modelnajpgbag_01);
		} else {
			testing_regr67 = testing_regr66;
		}
		
		if (!modelnajpgbag_02.toString().contains("No model built yet.")) {
			testing_regr67.setClass(fvAttributes.get(87));
			testing_regr68 = DataHandler.getInstance().applyRegressionModel(
					testing_regr67, fvAttributes, modelnajpgbag_02);
		} else {
			testing_regr68 = testing_regr67;
		}
		
		if (!modelnajpgbag_03.toString().contains("No model built yet.")) {
			testing_regr68.setClass(fvAttributes.get(88));
			testing_regr69 = DataHandler.getInstance().applyRegressionModel(
					testing_regr68, fvAttributes, modelnajpgbag_03);
		} else {
			testing_regr69 = testing_regr68;
		}
		
		if (!modelnajpgbag_04.toString().contains("No model built yet.")) {
			testing_regr69.setClass(fvAttributes.get(89));
			testing_regr70 = DataHandler.getInstance().applyRegressionModel(
					testing_regr69, fvAttributes, modelnajpgbag_04);
		} else {
			testing_regr70 = testing_regr69;
		}
		
		if (!modelnajpgbag_05.toString().contains("No model built yet.")) {
			testing_regr70.setClass(fvAttributes.get(90));
			testing_regr71 = DataHandler.getInstance().applyRegressionModel(
					testing_regr70, fvAttributes, modelnajpgbag_05);
		} else {
			testing_regr71 = testing_regr70;
		}
		
		if (!modelnajpgbag_06.toString().contains("No model built yet.")) {
			testing_regr71.setClass(fvAttributes.get(91));
			testing_regr72 = DataHandler.getInstance().applyRegressionModel(
					testing_regr71, fvAttributes, modelnajpgbag_06);
		} else {
			testing_regr72 = testing_regr71;
		}
		
		if (!modelnajpgbag_07.toString().contains("No model built yet.")) {
			testing_regr72.setClass(fvAttributes.get(92));
			testing_regr73 = DataHandler.getInstance().applyRegressionModel(
					testing_regr72, fvAttributes, modelnajpgbag_07);
		} else {
			testing_regr73 = testing_regr72;
		}
		
		if (!modelnajpgbag_08.toString().contains("No model built yet.")) {
			testing_regr73.setClass(fvAttributes.get(93));
			testing_regr74 = DataHandler.getInstance().applyRegressionModel(
					testing_regr73, fvAttributes, modelnajpgbag_08);
		} else {
			testing_regr74 = testing_regr73;
		}
		
		if (!modelnajpgbag_09.toString().contains("No model built yet.")) {
			testing_regr74.setClass(fvAttributes.get(94));
			testing_regr75 = DataHandler.getInstance().applyRegressionModel(
					testing_regr74, fvAttributes, modelnajpgbag_09);
		} else {
			testing_regr75 = testing_regr74;
		}
		
		if (!modelnajpgbag_10.toString().contains("No model built yet.")) {
			testing_regr75.setClass(fvAttributes.get(95));
			testing_regr76 = DataHandler.getInstance().applyRegressionModel(
					testing_regr75, fvAttributes, modelnajpgbag_10);
		} else {
			testing_regr76 = testing_regr75;
		}
		
		if (!modelnajpgbag_11.toString().contains("No model built yet.")) {
			testing_regr76.setClass(fvAttributes.get(96));
			testing_regr77 = DataHandler.getInstance().applyRegressionModel(
					testing_regr76, fvAttributes, modelnajpgbag_11);
		} else {
			testing_regr77 = testing_regr76;
		}
		
		if (!modelnajpgbag_12.toString().contains("No model built yet.")) {
			testing_regr77.setClass(fvAttributes.get(97));
			testing_regr78 = DataHandler.getInstance().applyRegressionModel(
					testing_regr77, fvAttributes, modelnajpgbag_12);
		} else {
			testing_regr78 = testing_regr77;
		}
		
		if (!modelnajpgbag_13.toString().contains("No model built yet.")) {
			testing_regr78.setClass(fvAttributes.get(98));
			testing_regr79 = DataHandler.getInstance().applyRegressionModel(
					testing_regr78, fvAttributes, modelnajpgbag_13);
		} else {
			testing_regr79 = testing_regr78;
		}
		
		if (!modelnajpgbag_14.toString().contains("No model built yet.")) {
			testing_regr79.setClass(fvAttributes.get(99));
			testing_regr80 = DataHandler.getInstance().applyRegressionModel(
					testing_regr79, fvAttributes, modelnajpgbag_14);
		} else {
			testing_regr80 = testing_regr79;
		}
		
		if (!modelnajpgbag_15.toString().contains("No model built yet.")) {
			testing_regr80.setClass(fvAttributes.get(100));
			testing_regr81 = DataHandler.getInstance().applyRegressionModel(
					testing_regr80, fvAttributes, modelnajpgbag_15);
		} else {
			testing_regr81 = testing_regr80;
		}
		
		if (!modelnajpgbag_16.toString().contains("No model built yet.")) {
			testing_regr81.setClass(fvAttributes.get(101));
			testing_regr82 = DataHandler.getInstance().applyRegressionModel(
					testing_regr81, fvAttributes, modelnajpgbag_16);
		} else {
			testing_regr82 = testing_regr81;
		}
		
		//user models
		
		if (!usermodel.toString().contains("No model built yet.")) {
			testing_regr82.setClass(fvAttributes.get(112));
			testing_regr83 = DataHandler.getInstance().applyRegressionModel(
					testing_regr82, fvAttributes, usermodel);
		} else {
			testing_regr83 = testing_regr82;
		}
		if (!usermodel2.toString().contains("No model built yet.")) {
			testing_regr83.setClass(fvAttributes.get(114));
			testing_regr84 = DataHandler.getInstance().applyRegressionModel(
					testing_regr83, fvAttributes, usermodel2);
		} else {
			testing_regr84 = testing_regr83;
		}
		if (!usermodel3.toString().contains("No model built yet.")) {
			testing_regr84.setClass(fvAttributes.get(117));
			testing_regr85 = DataHandler.getInstance().applyRegressionModel(
					testing_regr84, fvAttributes, usermodel3);
		} else {
			testing_regr85 = testing_regr84;
		}

		if (!usermodel4.toString().contains("No model built yet.")) {
			testing_regr85.setClass(fvAttributes.get(118));
			testing_regr86 = DataHandler.getInstance().applyRegressionModel(
					testing_regr85, fvAttributes, usermodel4);
		} else {
			testing_regr86 = testing_regr85;
		}

		if (!usermodel5.toString().contains("No model built yet.")) {
			testing_regr86.setClass(fvAttributes.get(119));
			testing_regr87 = DataHandler.getInstance().applyRegressionModel(
					testing_regr86, fvAttributes, usermodel5);
		} else {
			testing_regr87 = testing_regr86;
		}
		
		if (!usermodel6.toString().contains("No model built yet.")) {
			testing_regr87.setClass(fvAttributes.get(120));
			testing_regr88 = DataHandler.getInstance().applyRegressionModel(
					testing_regr87, fvAttributes, usermodel6);
		} else {
			testing_regr88 = testing_regr87;
		}

		if (!usermodel7.toString().contains("No model built yet.")) {
			testing_regr88.setClass(fvAttributes.get(121));
			testing_regr89 = DataHandler.getInstance().applyRegressionModel(
					testing_regr88, fvAttributes, usermodel7);
		} else {
			testing_regr89 = testing_regr88;
		}
		
		if (!usermodel8.toString().contains("No model built yet.")) {
			testing_regr89.setClass(fvAttributes.get(122));
			testing_regr90 = DataHandler.getInstance().applyRegressionModel(
					testing_regr89, fvAttributes, usermodel8);
		} else {
			testing_regr90 = testing_regr89;
		}
		
		if (!usermodel9.toString().contains("No model built yet.")) {
			testing_regr90.setClass(fvAttributes.get(123));
			testing_regr91 = DataHandler.getInstance().applyRegressionModel(
					testing_regr90, fvAttributes, usermodel9);
		} else {
			testing_regr91 = testing_regr90;
		}
		
		// normalization
		Instances testingSet_normed = DataHandler.getInstance().normalizeData(
				testing_regr91, fvAttributes.size() - 1, normFilter);
		
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

}
