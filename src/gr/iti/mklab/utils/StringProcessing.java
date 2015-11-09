package gr.iti.mklab.utils;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.select.Elements;

public class StringProcessing {

	private static StringProcessing sInstance = new StringProcessing();

	public static StringProcessing getInstance() {
		
		if (sInstance == null) {	
			sInstance = new StringProcessing();
		}
		return sInstance;

	}
	
	public boolean isAppropriate(String str){
		
		if (str == null || str.isEmpty() || str.equals("null")){
			return false;
		}
		
		return true;
	}
	
	public boolean isAppropriateWord(String str) {
		
		HashSet<String> stop_eng 	= new HashSet<String>();
		HashSet<String> stop_es  	= new HashSet<String>();
		HashSet<String> stop_tweets = new HashSet<String>();
		try {
			stop_eng	= FileManager.getInstance().loadSetfromFile(Vars.STOP_WORDS_ENG);
			stop_es 	= FileManager.getInstance().loadSetfromFile(Vars.STOP_WORDS_ES);
			stop_tweets = FileManager.getInstance().loadSetfromFile(Vars.STOP_WORDS_TWEET);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		if (str.length()>2 && !str.contains("@") && !str.contains("#") && !stop_tweets.contains(str) 
				&& !stop_eng.contains(str) && !stop_es.contains(str)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Function that checks if an element exists
	 * @param elements Elements to be checked
	 * @return boolean exists or not
	 */
	public boolean existsAsElement(Elements elements) {
		
		if (elements != null && !elements.equals("") && !elements.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Function that removes the unit (K or M) from the String given
	 * @param str String 
	 * @return String processed
	 */
	public String removeUnit(String str) {
		
		if (str.contains("K")) {
			str = str.replace("K", "").replace(".", "").concat("00");
		} else if (str.contains("M")) {
			str = str.replace("M", "").replace(".", "").concat("000");
		} else if (str.contains(",")) {
			str = str.replace(",", "");
		}
		
		return str;
	}
	
	
	
}
