package gr.iti.mklab;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;

import gr.iti.mklab.extractfeatures.FeatureExtractionMain;
import gr.iti.mklab.verify.DoubleVerifyBagging;

public class TweetVerificationMain {
	
	public static String workingDir = System.getProperty("user.dir");
	private static final Logger LOGGER = Logger.getLogger("gr.iti.mklab.TweetVerificationMain");
	public static String tweetsFile;
	public static String featureFolder;
	public static String tweetsFeatureFile;
	public static String userFeatureFile;
	public static String method;
	public static String outputFolder;
	public static String trainLabels;
	public static String testLabels;
	
	public static List<String> acceptedMethodValues = new ArrayList<String>(Arrays.asList("1","2","3","4","5"));
	
	
public static void main(String[] args) throws Exception {
		
		/**
		 * Input parameters
		 */

        if (args.length > 0){
       	 if (args[0].equals("-featExt")){
       		 
       		 method = "featExt";
       		 if (args.length == 2){
       		
       			 tweetsFile = args[1];
       			 featureFolder = workingDir + "/Features/";
       			 
       		 }else if (args.length == 3){
       			 
       			tweetsFile = args[1];
       			featureFolder = args[2];
       		 }else{
       			 System.out.println("Wrong number of arguments for -featExt");
       			 return;
       		 }       		       		
       	
       	 }else if (args[0].equals("-train")){
       		 
       		method = "train";
       		
       			if (args.length == 3){
           			
           			trainLabels = args[1];
           			testLabels = args[2];
           			featureFolder =  workingDir + "/Features/";
           			outputFolder = workingDir + "/Run/";
           			
           		}else if (args.length == 5){
           	
           			trainLabels = args[1];
           			testLabels = args[2];
           	    	featureFolder =  args[3];
           			outputFolder = args[4];
           			
           		}else{
           			System.out.println("Wrong number of arguments -train");
           			return;
           		}  		
       	 
       	 }else{
       		 System.out.println("Wrong method. Available values:\n");
       		 System.out.println("-featExt for Feature Extraction \n");
       		 System.out.println("-train for Training and Evaluation \n");
       	 }
        }else if (args[0].equals("-help")){
        	help();
        	return;
        }else{
        	help();
        	return;
        }
        
		
        
        File featureFolderF = new File(featureFolder);
        /**
         * If Feature Extraction
         */
        if (method.equals("featExt")){
        	
	        if (!featureFolderF.exists()){
	        	 LOGGER.info("Create folder for storing the Features "+ outputFolder);
	        	 featureFolderF.mkdirs();
	        }else{
	        	LOGGER.info("The folder already exist. Delete or rename it in order not to owerwrite data");
	        	return;
	        }
	        
	        if ((new File(tweetsFile).exists())){
	        	LOGGER.info("FEATURE EXTRACTION EXECUTION");
	        	LOGGER.info(" --- Tweet file " + tweetsFile);

	        	tweetsFeatureFile = featureFolderF + "/tweetFeats.txt";
	        	userFeatureFile = featureFolderF + "/userFeats.txt";
	        	
	        	LOGGER.info(" --- tweetsFeatureFile " + tweetsFeatureFile);
	        	LOGGER.info(" --- userFeatureFile " + userFeatureFile);

	        	long start_time = System.currentTimeMillis();
	        	FeatureExtractionMain featEx = new FeatureExtractionMain();
	        	featEx.extractTweetFeatures(tweetsFile, userFeatureFile, tweetsFeatureFile, getResourcesPath());
		        LOGGER.info("END FEATURE EXTRACTION EXECUTION IN " + (System.currentTimeMillis() - start_time));
	        }else{
	        	LOGGER.info("Tweets file does not exist. Please provide a text file containg the tweets in JSON format - one tweet per line");
	        	return;
	        }
	        
        }
 
        
        /**
         * If Training
         */
        
        if (method.equals("train")){
        	
        	LOGGER.info("TRAINING AND EVALUATION");               
             File outputFolderF = new File(outputFolder);
             
        	if (!featureFolderF.exists()){
	        	 LOGGER.info("Feature folder does not exist. Extract features and run again");
	        	 return;
	        }else{
	        	outputFolderF.mkdirs();
	        	tweetsFeatureFile = featureFolderF + "/tweetFeats.txt";
	        	if (!(new File(tweetsFeatureFile).exists())){
	        		LOGGER.info("Tweets Feature file does not exist. A file named tweetsFeats.txt should be stored into " + 
	        				featureFolderF + " and contains the tweet features" );
	        		return;
	        	}
	        	
	        	userFeatureFile = featureFolderF + "/userFeats.txt";
	        	if (!(new File(userFeatureFile).exists())){
	        		LOGGER.info("User Feature file does not exist. A file named userFeats.txt should be stored into " + 
	        				featureFolderF + " and contains the user features" );
	        		return;
	        	}	   
	        	
	        	if (!(new File(trainLabels).exists())){
	        		LOGGER.info("A text file containing the ids and annotations of the training samples is missing" );
	        		return;
	        	}
	        	
	        	if (!(new File(testLabels).exists())){
	        		LOGGER.info("A text file containing the ids and annotations of the testing samples is missing" );
	        		return;
	        	}	
	        	
	        	long start_time = System.currentTimeMillis();
	        	DoubleVerifyBagging.startVerification(userFeatureFile, tweetsFeatureFile, trainLabels, testLabels, outputFolder);
	        	LOGGER.info("END TRAINING AND EVALUATION EXECUTION IN " + (System.currentTimeMillis() - start_time));
	        	
	        }         	
        	
        }
		
	   	
	}

	private static String getResourcesPath() throws ConfigurationException, IOException {
		Properties prop = new Properties();
    	InputStream input = new FileInputStream(TweetVerificationMain.class.getResource("/local.properties").getFile());
		// load a properties file
		prop.load(input);    	
        return prop.getProperty("resources");
	}
	
	
	public static void help(){
		
	   	System.out.println("Wrong number of arguments\n");
    	System.out.println("Usage for Feature Extraction\n");
    	System.out.println(" java -jar tweetVerification.jar -featExt <tweetsFile> <feature_folder>\n");
    	System.out.println("tweetsFile: path of a text file containing the tweet objects as they are returned by the Twitter API in JSON format. \n");
    	System.out.println("feature_folder: (optional) Path of a folder where the extracted features will be stored. Two files are created into the folder:\n");
    	System.out.println("1. tweetsFeats.txt: containing the extracted tweet-based features. One line per tweet.\n");
    	System.out.println("2. userFeats.txt: containing the extracted user-based features. One line per tweet.\n");
    	System.out.println("Default value of feature_folder current_directory + /Features/");
    	System.out.println("\n");
    	System.out.println(" ---------------------------------------------------------------------------------------------- ");
    	System.out.println("\n");
    	System.out.println("Usage for Training and Evaluation\n");
    	System.out.println(" java -jar tweetVerification.jar -train -v <trainMethod> <trainLabels> <testLabels> <featureFolder> <outputFolder> \n");
    	System.out.println("-v: whether to print detailed results or just the average");
    	System.out.println("trainMethod: (deafaut 5)\n");
    	System.out.println("     1 -- Classify disagreed on agreed without bagging \n");
    	System.out.println("     2 -- Classify disagreed on agreed with bagging \n");
    	System.out.println("     3 -- Classify disagreed on Updated existing model (initial training set + agreed) with bagging \n");
    	System.out.println("     4 -- Classify disagreed on Updated existing model (initial training set + agreed) without bagging \n");
    	System.out.println("     5 -- All above \n");
    	System.out.println("trainLabels: A text file containing the labels of the training items. Read the instructions for the text file format.\n");
    	System.out.println("testLabels: A text file containing the labels of the testing items. Read the instructions for the text file format.\n");
    	System.out.println("feature_folder: (optional) Path of the folder where the extracted features are stored. Two files are stored into the folder:\n");
    	System.out.println("1. tweetsFeats.txt: containing the extracted tweet-based features. One line per tweet.\n");
    	System.out.println("2. userFeats.txt: containing the extracted user-based features. One line per tweet.\n");
    	System.out.println("Default value of feature_folder current_directory + /Features/");
    	System.out.println("outputFolder: (optional) Path of the folder where the final evaluation results will be stored \n");
    	System.out.println("Default value of outputFolder current_directory + /Run/");

    	System.out.println(" -------------------------------------------------------------------------------------------------- ");
	}

	
	}
