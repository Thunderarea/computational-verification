# computational-verification #

A framework for "learning" how to classify social content as truthful/reliable or not. 
Features are extracted from the tweet text (Tweet-based features TF) and the user who published it (User-based features UB). 
A two level classification model is trained. 

## Getting Started ##


### Dataset ###
---------
Evaluations are conducted on a publicly available verification corpus (VC) of fake and real tweets collected for the needs of the MediaEval 2015 Verifying Multimedia Use (VMU) and MediaEval 2016 Verifying Multimedia Use (VMU) task. 
The VMU 2015 consist of tweets related to 17 events that comprise in total 193 cases of real images, 218 cases of misused (fake) images and two cases of misused videos, and are associated with 6,225 real
and 9,404 fake tweets posted by 5,895 and 9,025 unique users respectively. For dataset details refer to https://github.com/MKLab-ITI/image-verification-corpus

### Features ###
---------
| **Tweet-based features** | **User-based features** |
| :---: | :---:|
| #words |  #friends |
| length of text | #followers |
| #question marks | follower-friend ratio |
| #exclamation marks | #tweets|
| contains question mark | #media content|
| contains exclamation mark | has profile image |
| has ``please'' | has header image |
| has colon | has a URL |
| contains happy emoticon | has location|
| contains sad emoticon | has existing location|
| #uppercase chars | has bio description |
| #pos senti words |  tweet ratio |
| #neg senti words | account age |
| #slangs | is verified |
| #nouns | #times listed | WOT score |
| contains 1st pers.pron. | in-degree centrality|
| contains 2nd pers.pron. | harmonic centralit |
| contains 3rd pers.pron. | alexa country rank |
| readability | alexa delta rank | 
| #retweets | alexa popularity |
| #hashtags | alexa reach rank |
| has external link |
| #mentions |
| #URLs |
| WOT score |
| in-degree centrality |
| harmonic centralit |
| alexa country rank |
| alexa delta rank |
| alexa popularity |

### Agreement-based retraining technique ###
------------------------------------

**CL1:** classifier build with tweet-based features. <br />
**CL2:** classifier build with user-based features. <br />
**CL_ag:** Agreement-based model build with the agreed samples resulted using CL1 and CL2. <br />
**CL_tot:**  Agreement-based model build with the entire (total) set of initial training samples extending it with the set of agreed samples resulted using CL1 and CL2.


## Input data ##
-----------
*Features files:* <br />
	- tweetsFeatsVMU2015.txt: the tweet-based features in JSON format of the VMU 2015. <br />
	- userFeatsVMU2015.txt: the user-based features in JSON format of the VMU 2015. <br />
	- tweetsFeatsVMU2016.txt: the tweet-based features in JSON format of the VMU 2016. <br />
	- userFeatsVMU2016.txt: the user-based features in JSON format of the VMU 2016. <br /> 
	
*Annotation files:* <br />
	- VMU2015Train.txt: the annotations of the VMU 2015 dataset used for training.<br />
	- VMU2015Test.txt: the annotations of the VMU 2015 dataset used for testing.<br />
	- VMU2016Train.txt: the annotations of the VMU 2016 dataset used for training.<br />
	- VMU2016Test.txt: the annotations of the VMU 2016 dataset used for testing.<br />
	
	 Annotation files are in JSON format: {"id":"","label":"","event":""}


## Usage
------------------
The framework performs feature extraction on a new set of tweets and then classify each of them as truthful/reliable or not. 

**Initialize parameters** <br />

	DoubleVerifyBagging dvb = new DoubleVerifyBagging(); 
	setVerbose(boolean): false or real. Print just average results or also intermediate detailed results.
	Agreement-based retraining technique: (default 5)
		1 - Classify disagreed on agreed samples without bagging 
		2 - Classify disagreed on agreed samples with bagging
		3 - Classify disagreed on the entire (total) set of initial training samples extending it with the set of agreed samples with bagging 
		4 - Classify disagreed on the entire (total) set of initial training samples extending it with the set of agreed samples without bagging 
		5 - All above 	
	setRunConcatenated(boolean): false or real. Perform classification using simple concatenation of the user-based and tweet-based feature vectors into a single-level classifier.
	setClassifier(String): RF for Random Forest or LG for Logistic regression.

Main class *TweetVerificationMain* in *gr.iti.mklab* package. Provide command line arguments:

**1. Feature extraction**

	Input arguments:

		- tweetsFile: path of a text file containing the tweet objects as they are returned by the Twitter API in JSON format.

	 	- feature_folder: (optional) Path of a folder where the extracted features will be stored. Default value: [current_directory + /Features/"].			
		
	Output: (The feature files are created into the output folder:)
    	- tweetsFeats.txt: containing the extracted tweet-based features. One line per tweet. 
		- userFeats.txt: containing the extracted user-based features. One line per tweet.

**2. Training and Classification**

	Input arguments:
	
		- trainLabels: A text file containing the labels of the training items. Annotation files should be in JSON format: {"id":"","label":"","event":""}. 
	 	- testLabels: A text file containing the labels of the testing items. Annotation files should be in JSON format: {"id":"","label":"","event":""}.  
	
		- feature_folder: (optional) Path of the folder where the extracted features are stored. Two files are stored into the folder: 
			1. tweetsFeats.txt: containing the extracted tweet-based features. One line per tweet.  
			2. userFeats.txt: containing the extracted user-based features. One line per tweet.  
    		Default value of feature_folder current_directory + /Features/ 
	
		- outputFolder: (optional) Path of the folder where the final evaluation results will be stored. Default value: [current_directory + /Run/]

	Output:
		1. AverageResults.txt: Average accuracy (ratio of correctly classified samples over total number of test samples) of 
			CL: the selected classifier among CL1 and CL2.
			CL_ag : the overall accuracy using CL_ag agreement-based retraining model.
			CL_tot : the overall accuracy using CL_tot agreement-based retraining model.
	
		2. CL_ag_predictions.txt: tweet ID and fake/real label using CL_ag agreement-based retraining model.
		3. CL_tot_predictions.txt: tweet ID and fake/real label using CL_tot agreement-based retraining model.
		
## Citations

Please cite the following paper in your publications if you use the implementation:

	@inproceedings{boididou2017learning,
		title={Learning to Detect Misleading Content on Twitter},
		author={Boididou, Christina and Papadopoulos, Symeon and Apostolidis, Lazaros and Kompatsiaris, Yiannis},
		booktitle={Proceedings of the 2017 ACM on International Conference on Multimedia Retrieval},
		pages={278--286},
		year={2017},
		organization={ACM}
	}
		
## Contact for further details
------------------
Olga Papadopoulou (<olgapapa@iti.gr>) <br />
Symeon Papadopoulos (<papadop@iti.gr>)

	

