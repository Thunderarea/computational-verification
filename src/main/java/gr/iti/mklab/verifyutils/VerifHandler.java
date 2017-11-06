package gr.iti.mklab.verifyutils;

import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.output.prediction.PlainText;
import weka.classifiers.meta.CVParameterSelection;
import weka.core.Debug;
import weka.core.Instances;
import weka.core.Utils;

public class VerifHandler {

	private static VerifHandler mInstance = new VerifHandler();

	public static VerifHandler getInstance() {
		
		
		if (mInstance == null) {			
			mInstance = new VerifHandler();
		}
		return mInstance;

	}
	
	/**
	 * Calculates and prints the best parameters for the cross-validation
	 * procedure for the specific training set using the J48 decision tree as a
	 * classifier.
	 * 
	 * @param tree
	 *            J48 decision tree classifier
	 */
	public String[] selectBestParameters(Classifier tree, Instances isTrainingSet) {

		System.out.println("PARAMETER SELECTION INFORMATION");
		CVParameterSelection cls = new CVParameterSelection();
		String[] valueC = new String[2];
		String par = "C 0.1 0.5 5";
		try {
			cls.addCVParameter(par);
			cls.setClassifier(tree);
			cls.setNumFolds(10);
			// build and output best options
			cls.buildClassifier(isTrainingSet);
			System.out.println("J48 tree Optimized parameters: "+ Utils.joinOptions(cls.getBestClassifierOptions()));
			System.out.println();
			
			valueC[0] = "-C";
			valueC[1] = cls.getBestClassifierOptions()[1];
			
		} catch (Exception e) {
			System.out.println("Parameter Selection for J48 decision tree cannot be performed!");
			e.printStackTrace();
		}

		
		return valueC;
	}
	
	public String[] selectBestSVMParameters(Classifier tree, Instances isTrainingSet) {

		System.out.println("PARAMETER SELECTION INFORMATION");
		CVParameterSelection cls = new CVParameterSelection();
		String[] valueC = new String[2];
		String par = "G 0.01 0.1 10";
		try {
			cls.addCVParameter(par);
			cls.setClassifier(tree);
			cls.setNumFolds(10);
			// build and output best options
			cls.buildClassifier(isTrainingSet);
			System.out.println("SVM Optimized parameters: "+ Utils.joinOptions(cls.getBestClassifierOptions()));
			System.out.println();
			
			valueC[0] = "-G";
			valueC[1] = cls.getBestClassifierOptions()[1];
			
		} catch (Exception e) {
			System.out.println("Parameter Selection for SVM cannot be performed!");
			e.printStackTrace();
		}

		
		return valueC;
	}
	
	public void createClassifier(Classifier cls, Instances isTrainingSet, String model) throws Exception {

		// create the classifier
		Classifier fc = (Classifier) cls;
		fc.buildClassifier(isTrainingSet);
		Debug.saveToFile(model, fc);
		
		System.out.println("Model saved to "+model);

	}
	
		
	public double crossValidate(Classifier cls, Instances data)	throws Exception {

		double pctCorrect;
		
		Evaluation eval = new Evaluation(data);
		StringBuffer forPredictionsPrinting = new StringBuffer();
		PlainText classifierOutput = new PlainText();

		classifierOutput.setBuffer(forPredictionsPrinting);
		weka.core.Range attsToOutput = null;
		Boolean outputDistribution = new Boolean(true);
		classifierOutput.setOutputDistribution(true);

		eval.crossValidateModel(cls, data, 10, new Random(1), classifierOutput,
				attsToOutput, outputDistribution);

		System.out.println(eval.toClassDetailsString());
		System.out.println(eval.toMatrixString());

		System.out.println(eval.toSummaryString());
		pctCorrect = eval.pctCorrect()/100;
		
		return pctCorrect;
	}
	
}
