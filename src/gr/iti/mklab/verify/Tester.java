package gr.iti.mklab.verify;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

public class Tester {

	public static void main(String[] args) {
		
		//create an object of the AgreementBasedClassification class to start the verification process 
		//give as parameter the file path of the configuration file
		AgreementBasedClassification tweetVerifier = new AgreementBasedClassification("conf/config.properties");
				
		//(optional) define the buffered reader to read the file that holds the tweets to be tested
		BufferedReader br = null;
		//(optional) define the buffered writer to write the verification results
		BufferedWriter bw = null;
		
		
		String outputFilePath = "resources/test_data/sample_output.json";
		
		String line;
		try {
			
			br = new BufferedReader (new FileReader( AgreementBasedClassification.getProperties().getProperty("TESTINGDATA_JSON")));
			
			//read each line of the file (each tweet in a single line)
			while ((line=br.readLine())!=null) {

				bw = new BufferedWriter(new FileWriter(outputFilePath,true));
				
				//get a tweet in JSON format from the file				
				JSONObject tweet = new JSONObject(line);

				//call the verifyTweet method
				JSONObject verificationResult = tweetVerifier.verifyTweet(tweet);
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
