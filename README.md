computational-verification
==========================

A framework for "learning" how to classify social content as truthful/reliable or not.

Getting started
---------------

Class TweetVerificationMain in gr.iti.mklab package controls all functionalities. 

1. Feature extraction

Input arguments:
tweetsFile: path of a text file containing the tweet objects as they are returned by the Twitter API in JSON format.
feature_folder: (optional) Path of a folder where the extracted features will be stored. Two files are created into the folder:
    	1. tweetsFeats.txt: containing the extracted tweet-based features. One line per tweet.
    	2. userFeats.txt: containing the extracted user-based features. One line per tweet.
    	Default value of feature_folder current_directory + /Features/"

2. Training and Classification

Input arguments:
-v: whether to print detailed results or just the average
trainMethod: (deafaut 5)
	1 -- Classify disagreed on agreed without bagging
	2 -- Classify disagreed on agreed with bagging
	3 -- Classify disagreed on Updated existing model (initial training set + agreed) with bagging 
    4 -- Classify disagreed on Updated existing model (initial training set + agreed) without bagging 
    5 -- All above 	
	trainLabels: A text file containing the labels of the training items. Read the instructions for the text file format.
    testLabels: A text file containing the labels of the testing items. Read the instructions for the text file format.
    feature_folder: (optional) Path of the folder where the extracted features are stored. Two files are stored into the folder:
		1. tweetsFeats.txt: containing the extracted tweet-based features. One line per tweet.
		2. userFeats.txt: containing the extracted user-based features. One line per tweet.
    Default value of feature_folder current_directory + /Features/
    outputFolder: (optional) Path of the folder where the final evaluation results will be stored 
    Default value of outputFolder current_directory + /Run/