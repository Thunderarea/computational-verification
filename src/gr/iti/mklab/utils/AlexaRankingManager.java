package gr.iti.mklab.utils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class AlexaRankingManager {

	private static AlexaRankingManager mInstance = new AlexaRankingManager();

	public static AlexaRankingManager getInstance() {
		
		
		if (mInstance == null) {			
			mInstance = new AlexaRankingManager();
		}
		return mInstance;

	}
	
	private int getAlexaPopularity(Element element) {
		
		int result = 0;
		
		NodeList nodeList = element.getElementsByTagName("POPULARITY");
		if (nodeList.getLength() > 0) {
			Element elementAttribute = (Element) nodeList.item(0);
			String ranking = elementAttribute.getAttribute("TEXT");
			if(!"".equals(ranking)){
				result = Integer.valueOf(ranking);
			}
		}
		
		return result;
	}
	
	private int getAlexaReachRank(Element element) {
		
		int result = 0;
		
		NodeList nodeList = element.getElementsByTagName("REACH");
		if (nodeList.getLength() > 0) {
			Element elementAttribute = (Element) nodeList.item(0);
			String ranking = elementAttribute.getAttribute("RANK");
			if(!"".equals(ranking)){
				result = Integer.valueOf(ranking);
			}			
		}
		
		return result;
	}
	
	private int getAlexaDeltaRank(Element element) {
		
		int result = 0;
		
		NodeList nodeList = element.getElementsByTagName("RANK");
		if (nodeList.getLength() > 0) {
			Element elementAttribute = (Element) nodeList.item(0);
			String ranking = elementAttribute.getAttribute("DELTA");
			if(!"".equals(ranking)){
				result = Integer.valueOf(ranking);
			}			
		}
		
		return result;
	}
	
	private int getAlexaCountryRank(Element element) {
		
		int result = 0;
		
		NodeList nodeList = element.getElementsByTagName("COUNTRY");
		if (nodeList.getLength() > 0) {
			Element elementAttribute = (Element) nodeList.item(0);
			String ranking = elementAttribute.getAttribute("RANK");
			if(!"".equals(ranking)){
				result = Integer.valueOf(ranking);
			}			
		}
		
		return result;
	}
	
	public int[] getAlexaRanking(String domain) {
		  
		int [] alexaRanks = new int[4];
		String url = "http://data.alexa.com/data?cli=10&url=" + domain;
 
		try {
 
			URLConnection conn = new URL(url).openConnection();
			InputStream is = conn.getInputStream();
 
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(is);
 
			Element element = doc.getDocumentElement();
 
			alexaRanks[0] = getAlexaPopularity(element);
			alexaRanks[1] = getAlexaReachRank(element);
			alexaRanks[2] = getAlexaDeltaRank(element);
			alexaRanks[3] = getAlexaCountryRank(element);
 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
 
		return alexaRanks;
		
	}
}
