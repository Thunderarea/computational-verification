package gr.iti.mklab.verify;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

public class Tester {

	public static void main(String[] args) {
		
		//set the configuration file's path and initialize the parameters
		try {
			AgreementBasedClassification.initializeParameters("conf/config.properties");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//(optional) define the buffered reader to read the file that holds the tweets to be tested
		BufferedReader br = null;
		//(optional) define the buffered writer to write the verification results
		BufferedWriter bw = null;
		
		String line;
		try {
			
			br = new BufferedReader (new FileReader( AgreementBasedClassification.getProperties().getProperty("TESTINGDATA_JSON")));
			
			//read each line of the file (each tweet in a single line)
			while ((line=br.readLine())!=null) {

				bw = new BufferedWriter(new FileWriter("resources/test_data/sample_output.json",true));
				
				//get a tweet in JSON format from the file				
				JSONObject tweet = new JSONObject(line);

				//create object for AgreementBasedClassification class to start the verification process
				AgreementBasedClassification agreemBasedClassificationObj = new AgreementBasedClassification();
				//call the verifyTweet method
				JSONObject verificationResult = agreemBasedClassificationObj.verifyTweet(tweet);
				//print result
				System.out.println("Result: ");
				System.out.println(verificationResult);
				System.out.println();
				//(optional) write the result to a file
				bw.write(verificationResult.toString());
				bw.newLine();
				bw.flush();
			}
			
			bw.close();
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
		

	}
}
