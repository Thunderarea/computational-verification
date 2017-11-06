package gr.iti.mklab.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.imageio.ImageIO;
import org.json.JSONObject;
import weka.core.Instances;
import gr.iti.mklab.extractfeatures.TweetFeatures;
import gr.iti.mklab.extractfeatures.UserFeatures;
import gr.iti.mklab.extractfeatures.UserFeaturesAnnotation;
import gr.iti.mklab.verify.FeaturesAnnotationItem;

public class FileManager {

	private static FileManager sInstance = new FileManager();

	public static FileManager getInstance() {

		if (sInstance == null) {
			sInstance = new FileManager();
		}
		return sInstance;

	}

	/**
	 * Downloads the file from a given url.
	 * 
	 * @param url
	 *            URL source from where the file is downloaded.
	 * @param name
	 *            String the name of the file to be stored.
	 * @param directory
	 *            String the name of the directory.
	 * @return the File that has been downloaded.
	 * @throws MalformedURLException
	 */
	public File downloadFile(URL url, String name, String directory)
			throws MalformedURLException {

		String parts[] = url.toString().split("\\.");

		String prefix = parts[parts.length - 1];
		if (prefix.contains("&"))
			prefix = prefix.substring(0, prefix.indexOf("&"));
		System.out.println("prefix "+prefix);
		
		String[] prefixArray = new String[] {"jpg","png"};
		List<String> prefixes = java.util.Arrays.asList(prefixArray);
		
		File f2 ;
		if (prefixes.contains(prefix)) {
			f2 = new File(directory + name + "." + prefix);
		}
		else {
			f2 = new File(directory + name + ".jpg");
		}
		

		File parentFile = new File(directory).getParentFile();
		boolean existence = false;
		File[] files = new File[parentFile.listFiles().length];

		for (int i = 0; i < files.length; i++) {

			files[i] = new File(parentFile.listFiles()[i].getAbsolutePath()
					+ "\\" + name + "." + "jpg");
			System.out.println(files[i]);
			System.out.println(files[i].exists() + " " + existence);
			existence = files[i].exists() || existence;
			System.out.println(existence);
		}

		boolean decide = false;
		System.out.println(f2.exists());
		if (f2.exists() || existence) {
			System.out.println("File existed");
			decide = false;
		} else {
			System.out.println("File not existed");
			decide = true;
		}

		System.out.println("decide " + decide);

		if (decide) {

			// f2 = new File("D:\\dataset\\images_Eclipse\\test\\" + name +
			// "."+prefix);
			String newurl = url.toString();
			System.out.println("new url before: "+ newurl);
			if (!url.toString().contains("media")
					&& url.toString().contains("p.twimg")) {
				try {
					newurl = url.toString().split("https://p.twimg.com/")[1];
				}catch(Exception e) {
					newurl = url.toString().split("http://p.twimg.com/")[1];
				}
				
				newurl = "https://p.twimg.com/media/" + newurl;
			}

			System.out.println("new url " + newurl);
			URL url2 = new URL(newurl);

			try {
				BufferedInputStream fr = new BufferedInputStream(url2.openStream());
				FileOutputStream fw = new FileOutputStream(f2);

				int c;

				while ((c = fr.read()) != -1) {
					fw.write(c);
				}

				fr.close();
				fw.close();

			} catch (IOException e) {
				System.out.println(name);
				System.out.println(e);

			}
		}

		return f2;
	}

	/**
	 * Writes the content of a HashMap<String,String> to a file.
	 * 
	 * @param hashmap
	 *            the HashMap<String,String> to be written in the file.
	 */
	public void writeHashmapToFile(HashMap<String, Long> hashmap,
			String filePath) {

		File file = new File(filePath);// your file
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));

			for (String p : hashmap.keySet()) {
				bw.write(p + "\t" + hashmap.get(p));
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes the content of a HashMap<String,Integer> to a file.
	 * 
	 * @param hashmap
	 *            the HashMap<String,String> to be written in the file.
	 */
	public void writeIntegerHashmapToFile(HashMap<String, Integer> hashmap,
			String filePath) {

		File file = new File(filePath);// your file
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));

			for (String p : hashmap.keySet()) {
				bw.write(p + "\t" + hashmap.get(p));
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes the content of a HashMap<String,String> to a file.
	 * 
	 * @param hashmap
	 *            the HashMap<String,String> to be written in the file.
	 */
	public void writeStringHashmapToFile(HashMap<String, String> hashmap,
			String filePath) {

		File file = new File(filePath);// your file
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));

			for (String p : hashmap.keySet()) {
				bw.write(p + "\t" + hashmap.get(p));
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes the content of a HashMap<String,String> to a file.
	 * 
	 * @param hashmap
	 *            the HashMap<String,String> to be written in the file.
	 */
	public void writeDoubleHashmapToFile(HashMap<String, Double> hashmap,
			String filePath) {

		File file = new File(filePath);// your file
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));

			for (String p : hashmap.keySet()) {
				bw.write(p + "\t" + hashmap.get(p));
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void writePlainDataToFile(String data, String filePath) {

		File file = new File(filePath);// your file
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

			bw.write(data);
			bw.newLine();

			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeDoubleDataToFile(double data, String filePath) {

		File file = new File(filePath);// your file
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

			bw.write(String.valueOf(data));
			bw.newLine();

			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeListofArrayToFile(HashMap<String, String[]> data,
			String filePath) {

		File file = new File(filePath);// your file
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));

			bw.write("@RELATION texts");
			bw.newLine();
			bw.write("@ATTRIBUTE tweetId string");
			bw.newLine();
			bw.write("@ATTRIBUTE tweetText string");
			bw.newLine();
			bw.newLine();

			bw.write("@data");
			bw.newLine();

			int counter = 0;
			for (String p : data.keySet()) {

				bw.write("\""); // quotation marks
				bw.write(p); // tweet id
				bw.write("\"");// quotation marks
				bw.write(","); // comma
				bw.write("\""); // quotation marks

				for (String str : data.get(p)) {
					counter++;

					bw.write(str);
					if (counter != data.get(p).length)
						bw.write(" ");
				}

				bw.write("\"");
				bw.newLine();

			}

			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addElements(String filePath, String filePathWrite) {

		File fileRead = new File(filePath);// your file
		File fileWrite = new File(filePathWrite);// your file

		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader(fileRead));
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileWrite));

			String line = "";
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				String[] parts = line.split("\\s", 2);

				System.out.println(parts[0] + " " + parts[1]);
				parts[1] = TextProcessing.getInstance().eraseCharacters(
						parts[1]);
				bw.write("'" + parts[0] + "','" + parts[1] + "'");
				bw.newLine();
			}

			bw.flush();
			bw.close();

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeSetToFile(Set<String> list, String filePath) {
		File file = new File(filePath);// your file
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));

			for (String l : list) {
				bw.append(l);
				bw.newLine();
			}

			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeListToFile(List<String> list, String filePath) {
		File file = new File(filePath);// your file
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));

			for (int i = 0; i < list.size(); i++) {
				bw.append(list.get(i));
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the file's content to a HashMap<String,String>.
	 * 
	 * @param file
	 *            String the name of the file.
	 * @return the HashMap<String,String> which contains the file's data in a
	 *         comma-separated format.
	 * @throws IOException
	 */
	public HashMap<String, String> loadHashmapfromFile(String file)
			throws IOException {
		// load hashmap from file
		HashMap<String, String> hashmap = new HashMap<String, String>();
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = in.readLine()) != null) {
				String parts[] = line.split("\t");
				hashmap.put(parts[0], parts[1]);
			}
			in.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return hashmap;
	}


	/**
	 * Sets the file's content to a HashMap<String,String>.
	 * 
	 * @param file
	 *            String the name of the file.
	 * @return the HashMap<String,String> which contains the file's data in a
	 *         comma-separated format.
	 * @throws IOException
	 */
	public List<String> loadDatafromFile(String file) throws IOException {
		// load hashmap from file
		List<String> list = new ArrayList<String>();
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = in.readLine()) != null) {
				list.add(line);
			}
			in.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Loads a list of JSONObject from a given file
	 * @param file String path
	 * @return List<JSONObject> list of jsons loaded from the file
	 * @throws IOException
	 */
	public HashMap<JSONObject,String> loadJsonsfromFile(String file) throws IOException {
		
		HashMap<JSONObject,String> map = new HashMap<JSONObject,String>();
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = in.readLine()) != null) {
				String parts[] = line.split("\t");
				JSONObject json = new JSONObject(parts[0]);
				String label = parts[1];
				map.put(json, label);
			}
			in.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return map;
	}

	public List<String> loadIdsfromFileWithCondition(String file,
			String regexToSplit) throws IOException {

		List<String> list = new ArrayList<String>();
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = in.readLine()) != null) {
				if (line.contains("pigFish_")
						/*|| line.contains("sandyB_real")*/) {
					list.add(line.split(regexToSplit)[0]);
				}
			}
			in.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	public List<String> loadIdsfromFile(String file, String regexToSplit)
			throws IOException {

		List<String> list = new ArrayList<String>();
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = in.readLine()) != null) {
				list.add(line.split(regexToSplit)[0]);
			}
			in.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	/**
	 * Sets the file's content to a HashSet<String>.
	 * 
	 * @param file
	 *            String the name of the file.
	 * @return the HashSet<String> which contains the file's data.
	 * @throws IOException
	 */
	public HashSet<String> loadSetfromFile(String file) throws IOException {
		// load hashmap from file
		HashSet<String> set = new HashSet<String>();
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = in.readLine()) != null) {
				set.add(line);
			}
			in.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return set;
	}

	/**
	 * Sets the file's content to a HashSet<String>.
	 * 
	 * @param file
	 *            String the name of the file.
	 * @return the HashSet<String> which contains the file's data.
	 * @throws IOException
	 */
	public TreeSet<String> loadTreeSetfromFile(String file) throws IOException {

		// load set from file
		TreeSet<String> set = new TreeSet<String>();
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = in.readLine()) != null) {
				set.add(line);
			}
			in.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return set;
	}

	public void writeDataToFile(String filePath, Instances dataset)
			throws IOException {

		BufferedWriter bw = null;

		for (int i = 0; i < dataset.size(); i++) {
			try {

				bw = new BufferedWriter(new FileWriter(filePath, true));
				// if file doesn't exist, then create it
				// System.out.println(dataset.get(i).toString());
				bw.append(dataset.get(i).toString());
				bw.newLine();
				bw.flush();
				bw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void writeInstancesToFile(String filePath, Instances dataset)
			throws IOException {

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(filePath, true));
			bw.write("@RELATION data ");
			bw.newLine();
			bw.write("@ATTRIBUTE id string");
			bw.newLine();
			bw.write("@ATTRIBUTE numFriends numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numFollowers numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE FolFrieRatio numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE timesListed numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE hasUrl {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE isVerified {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE numTweets numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE hasBio {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE hasLocation {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE hasExistingLocation {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE wotTrustUser numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numMediaContent numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE accountAge numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE hasProfileImg {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE hasHeaderImg {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE tweetRatio numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE indegree numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE harmonic numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaCountryRank numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaDeltaRank numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaPopularity numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaReachRank numeric");
			bw.newLine();

			bw.write("@ATTRIBUTE class {fake,real}");
			bw.newLine();
			bw.write("@DATA");
			bw.newLine();

			for (int i = 0; i < dataset.size(); i++) {
				// if file doesn't exist, then create it
				// System.out.println(dataset.get(i).toString());
				bw.append(dataset.get(i).toString());
				bw.newLine();

			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeSelectedToFile(String filePath, String filePath2,
			String fileOutput) {

		BufferedWriter bw = null;
		BufferedReader in_ids, in_texts;

		try {
			bw = new BufferedWriter(new FileWriter(fileOutput, true));

			in_ids = new BufferedReader(new FileReader(filePath));

			String line = "", line2 = "";

			// read in_ids file
			while ((line = in_ids.readLine()) != null) {

				in_texts = new BufferedReader(new FileReader(filePath2));
				// read in_texts file
				while ((line2 = in_texts.readLine()) != null) {
					// define each id of the in_texts file
					String id = line2.split(",")[0];
					// check if equals the line of the in_ids file
					if (id.equals(line)) {
						String text = line2.split(",")[1];
						bw.append(id + "," + text + ",real");
						bw.newLine();
					}
				}
			}
			bw.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*public void writeDatatoFile(String db, String collection, String path,
			List<String> ids) throws Exception {

		// declare the class to use
		Class<?> stemClass = Class
				.forName("org.tartarus.snowball.ext.spanishStemmer");
		SnowballStemmer stemmer = (SnowballStemmer) stemClass.newInstance();

		
		 * TotalFeaturesExtractor tfe = new TotalFeaturesExtractor();
		 * List<TotalFeatures> list = tfe.getLatestItems("FeaturesTotal",
		 * "Bring_reals", 0); //Collections.shuffle(list,new Random(24));
		 
		// List<String> ids = new ArrayList<String>();

		
		 * List<Item> items = new ArrayList<Item>(); for (TotalFeatures
		 * feat:list) { ItemDAOImpl dao = new ItemDAOImpl(Vars.LOCALHOST_IP, db,
		 * collection); Item item = dao.getItem(feat.getId()); items.add(item);
		 * }
		 

		ItemDAOImpl dao = new ItemDAOImpl(Vars.LOCALHOST_IP, db, collection);
		List<Item> itemspro = dao.getLatestItems(0);

		List<Item> items = new ArrayList<Item>();
		for (Item item : itemspro) {
			if (ids.contains(item.getId())) {
				items.add(item);
			} else {
				System.out.println("not found");
			}
		}

		int size = items.size();
		int counter = 0;

		try {
			File file = new File(path);
			// if file doesn't exist, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);

			Morphology m = new Morphology();
			System.out.println("Total items: " + items.size());

			for (int i = 0; i < size; i++) {

				String text = items.get(i).getTitle();
				String id = items.get(i).getId().replaceAll("[^0-9.]", "");
				ids.add(id);
				// System.out.println(text);
				UberLanguageDetector detector = UberLanguageDetector
						.getInstance();
				String language = detector.detectLang(text);

				// if (Vars.SUPPORTED_LANGS.contains(language)) {
				String texts[] = TextProcessing.getInstance()
						.tokenizeText(text);
				String textAll = "";

				for (String t : texts) {
					if (StringProcessing.getInstance().isAppropriateWord(t)) {
						// System.out.println("-"+t+"--");
						String word = "";
						if (language.equals("es")) {
							stemmer.setCurrent(t);
							stemmer.stem();
							word = stemmer.getCurrent();
						} else {
							Word wrd = new Word(t);
							word = m.stem(wrd).toString();
						}
						if (StringProcessing.getInstance().isAppropriateWord(
								word)) {
							word = word.replace("'", "");
							if (StringProcessing.getInstance()
									.isAppropriateWord(word)) {
								textAll = textAll.concat(word + " ");
							}
						}
					}
				}
				textAll = textAll.trim();

				// if (textAll.length()>0) {
				counter++;
				bw.append(id + ",'" + textAll + "',fake");
				bw.newLine();
				// }
				// }

			}

			bw.close();
			// writeSetToFile(ids);
			System.out.println("Done! " + counter
					+ " Tweets' texts were written in " + path);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
*/
	/*public void manageRequestedItems(List<ItemFeatures> itemFeats, String db,
			String collection, String collection2) throws Exception {

		List<String> ids = new ArrayList<String>();

		for (ItemFeatures feat : itemFeats) {
			ids.add(feat.getId());
		}

		MongoHandler mh = null;
		try {
			mh = new MongoHandler(Vars.LOCALHOST_IP, "Experiments");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		ItemDAOImpl dao = new ItemDAOImpl(Vars.LOCALHOST_IP, db, collection);

		for (String id : ids) {
			// id = id.replaceAll("Twitter::", "");
			Item item = dao.getItem(id);
			mh.insert(item, collection2);
		}

	}*/

	public HashMap<String, Long> sortByComparator(Map<String, Long> unsortMap) {

		// Convert Map to List
		List<Map.Entry<String, Long>> list = new LinkedList<Map.Entry<String, Long>>(
				unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
			public int compare(Map.Entry<String, Long> o1,
					Map.Entry<String, Long> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// Convert sorted map back to a Map
		HashMap<String, Long> sortedMap = new LinkedHashMap<String, Long>();
		for (Iterator<Map.Entry<String, Long>> it = list.iterator(); it
				.hasNext();) {
			Map.Entry<String, Long> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public HashMap<String, Double> sortByComparatorDouble(
			Map<String, Double> unsortMap) {

		// Convert Map to List
		List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(
				unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
					Map.Entry<String, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// Convert sorted map back to a Map
		HashMap<String, Double> sortedMap = new LinkedHashMap<String, Double>();
		for (Iterator<Map.Entry<String, Double>> it = list.iterator(); it
				.hasNext();) {
			Map.Entry<String, Double> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public HashMap<ArrayList<String>, Double> sortByDoubleComparator(
			Map<ArrayList<String>, Double> unsortMap) {

		// Convert Map to List
		List<Map.Entry<ArrayList<String>, Double>> list = new LinkedList<Map.Entry<ArrayList<String>, Double>>(
				unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list,
				new Comparator<Map.Entry<ArrayList<String>, Double>>() {
					public int compare(Map.Entry<ArrayList<String>, Double> o1,
							Map.Entry<ArrayList<String>, Double> o2) {
						return (o2.getValue()).compareTo(o1.getValue());
					}
				});

		// Convert sorted map back to a Map
		HashMap<ArrayList<String>, Double> sortedMap = new LinkedHashMap<ArrayList<String>, Double>();
		for (Iterator<Map.Entry<ArrayList<String>, Double>> it = list
				.iterator(); it.hasNext();) {
			Map.Entry<ArrayList<String>, Double> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	/**
	 * Function that sorts the given map
	 * 
	 * @param unsortMap
	 *            Map<Double, Double> the map to be sorted
	 * @return HashMap<Double, Double> the sorted map
	 */
	public HashMap<Double, Double> sortByDoubleComparator2(
			Map<Double, Double> unsortMap) {

		// Convert Map to List
		List<Map.Entry<Double, Double>> list = new LinkedList<Map.Entry<Double, Double>>(
				unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<Double, Double>>() {
			public int compare(Map.Entry<Double, Double> o1,
					Map.Entry<Double, Double> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		// Convert sorted map back to a Map
		HashMap<Double, Double> sortedMap = new LinkedHashMap<Double, Double>();
		for (Iterator<Map.Entry<Double, Double>> it = list.iterator(); it
				.hasNext();) {
			Map.Entry<Double, Double> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public void writeJsonsToFile(List<JSONObject> jsons, String filePath, String label) {

		File file = new File(filePath);// your file
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));

			for (JSONObject json : jsons) {
				bw.write(json.toString());
				bw.newLine();
			}

			bw.flush();
			bw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void writeJsonIdsAndTextToFile(List<JSONObject> jsons,
			String filePath) {

		File file = new File(filePath);// your file
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));

			for (JSONObject json : jsons) {
				String id = json.getString("id_str");
				String text = json.getString("text").replaceAll("\n", "");
				bw.write(id + "\t" + text);
				bw.newLine();
			}

			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String escapeChars(String str) {
		char[][] escapes = { { '\n', 'n' }, { '\r', 'r' }, { '\f', 'f' },
				{ '\b', 'b' }, { '\t', 't' } };
		for (char[] escape : escapes) {
			str = str.replaceAll(Character.toString(escape[0]), "\\\\"
					+ escape[1]);
		}
		return str;
	}

	public void writeJsonIdsAndTextToFile2(List<JSONObject> jsons, String filePath, String str) {

		File file = new File(filePath);// your file
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

			for (JSONObject json : jsons) {
				String id = json.getString("id_str");
				String text = json.getString("text");
				System.out.println(text);
				// text = StringEscapeUtils.escapeJava(text);
				text = escapeChars(text);
				System.out.println(text);
				String userid = json.getJSONObject("user").get("id").toString();
				userid = userid.replaceAll("[^\\d.]", "");
				System.out.println(userid);

				String username = json.getJSONObject("user").getString(
						"screen_name");
				String timestamp = json.getString("created_at");
				bw.write(id + "\t" + text + "\t" + userid + "\t" + username
						+ "\t" + timestamp + "\t" + str);

				bw.newLine();
			}

			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void writeJsonIdsAndTextToFile3(List<JSONObject> jsons,
			String filePath, String str, HashMap<String, String> map) {

		File file = new File(filePath);// your file
		try {
			PrintWriter bw = new PrintWriter(new FileWriter(file,true));

			for (JSONObject json : jsons) {
				String id = json.getString("id_str");
				System.out.println(json.toString());
				String text = "";

				try {
					text = json.getJSONObject("retweeted_status").getString(
							"text");
				} catch (Exception e) {
					text = json.getString("text");
				}

				System.out.println(text);
				// text = StringEscapeUtils.escapeJava(text);
				text = escapeChars(text);
				System.out.println(text);
				String userid = json.getJSONObject("user").get("id").toString();
				userid = userid.replaceAll("[^\\d.]", "");
				System.out.println(userid);

				String username = json.getJSONObject("user").getString(
						"screen_name");
				String timestamp = json.getString("created_at");
				bw.println(id + "\t" + text + "\t" + userid + "\t"
						+ /*map.get(id)*/"varoufakis_1" + "\t" + username + "\t" + timestamp
						+ "\t" + str);

				// bw.newLine();
			}

			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Writes the Item Features been on the filePath retrieved from the
	 * db/collection.
	 * 
	 * @param db
	 *            String the database name to retrieve the features from
	 * @param collection
	 *            the collection name to retrieve the features from
	 * @param filePathToWrite
	 *            String the file to write the features to
	 * @param filePath
	 *            String the file from which the ids to get the features are
	 *            specified
	 * @throws IOException
	 *//*
	public void writeItemFeaturesToFile(String db, String collection,
			String filePathToWrite) throws IOException {

		ItemFeaturesExtractor ife = new ItemFeaturesExtractor();

		//List<String> idsFromWhichToGetFeats = loadIdsfromFileWithCondition(
				//filePath, Vars.COMMA);
		//System.out.println(collection);
		List<String> idsFromWhichToGetFeats = DBHandler.getInstance().getExistingTweetsIds
				("TestSet2016_v2", collection, "id_str");
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(filePathToWrite,true));

		for (String id : idsFromWhichToGetFeats) {

			System.out.println(id);
			ItemFeatures ifeat = ife.getItemFeaturesById(db, collection, id);
			if (ifeat != null) {

				bw.write(ifeat.getId());
				bw.write(Vars.COMMA);
				bw.write(ifeat.getNumWords().toString());
				bw.write(Vars.COMMA);
				bw.write(ifeat.getItemLength().toString());
				bw.write(Vars.COMMA);
				bw.write(String.valueOf(ifeat.getContainsQuestionMark()));
				bw.write(Vars.COMMA);
				bw.write(ifeat.getNumQuestionMark().toString());
				bw.write(Vars.COMMA);
				bw.write(String.valueOf(ifeat.getContainsExclamationMark()));
				bw.write(Vars.COMMA);
				bw.write(ifeat.getNumExclamationMark().toString());
				bw.write(Vars.COMMA);
				bw.write(String.valueOf(ifeat.getContainsHappyEmo()));
				bw.write(Vars.COMMA);
				bw.write(String.valueOf(ifeat.getContainsSadEmo()));
				bw.write(Vars.COMMA);
				bw.write(String.valueOf(ifeat.getContainsFirstOrderPron()));
				bw.write(Vars.COMMA);
				bw.write(String.valueOf(ifeat.getContainsSecondOrderPron()));
				bw.write(Vars.COMMA);
				bw.write(String.valueOf(ifeat.getContainsThirdOrderPron()));
				bw.write(Vars.COMMA);
				bw.write(ifeat.getNumUppercaseChars().toString());
				bw.write(Vars.COMMA);
				if (ifeat.getNumPosSentiWords() != null)
					bw.write(ifeat.getNumPosSentiWords().toString());
				else
					bw.write("null");
				bw.write(Vars.COMMA);
				if (ifeat.getNumNegSentiWords() != null)
					bw.write(ifeat.getNumNegSentiWords().toString());
				else
					bw.write("null");
				bw.write(Vars.COMMA);
				bw.write(ifeat.getNumMentions().toString());
				bw.write(Vars.COMMA);
				bw.write(ifeat.getNumHashtags().toString());
				bw.write(Vars.COMMA);
				bw.write(ifeat.getNumURLs().toString());
				bw.write(Vars.COMMA);
				bw.write(ifeat.getRetweetCount().toString());
				bw.newLine();
				bw.flush();
			} else {
				bw.write(id);
				bw.newLine();
				bw.flush();
			}
		}

		bw.close();

	}*/

	
	public void writeItemFeaturesListToFile(String filePath, List<TweetFeatures> ifeats, String label)
			throws IOException {

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(filePath, false));
			bw.write("@RELATION data ");
			bw.newLine();
			bw.write("@ATTRIBUTE id string");
			bw.newLine();
			bw.write("@ATTRIBUTE ItemLength numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numWords numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE containsQuestionMark {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE containsExclamationMark {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE hasExternalLink {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE numNouns numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE containsHappyEmo {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE containsSadEmo {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE containsFirstOrderPron {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE containsSecondOrderPron {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE containsThirdOrderPron {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE numUppercaseChars numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numPosSentiWords numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numNegSentiWords numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numMentions numeric}");
			bw.newLine();
			bw.write("@ATTRIBUTE numHashtags numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numURLs numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE retweetCount numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numSlangs numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE hasColon {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE hasPlease {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE wotTrust numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numQuestionMark numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numExclamationMark numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE readability numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE urlIndegree numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE urlHarmonic numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaCountryRank numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaDeltaRank numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaPopularity numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaReachRank numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE theClass {fake,real}");
			bw.newLine();
			bw.write("@DATA");
			bw.newLine();

			for (int i = 0; i < ifeats.size(); i++) {
				// if file doesn't exist, then create it
				// System.out.println(dataset.get(i).toString());
				
				TweetFeatures currFeat = ifeats.get(i);
				bw.append("'"+currFeat.getId()+"'"); bw.append(",");
				bw.append(currFeat.getItemLength().toString()); bw.append(",");
				bw.append(currFeat.getNumWords().toString()); bw.append(",");
				bw.append(((Boolean)currFeat.getContainsQuestionMark()).toString()); bw.append(",");
				bw.append(((Boolean)currFeat.getContainsExclamationMark()).toString()); bw.append(",");
				bw.append(((Boolean)currFeat.getHasExternalLink()).toString()); bw.append(",");
				if (currFeat.getNumNouns() != null)
					bw.append(currFeat.getNumNouns().toString());
				else
					bw.append("?");
				bw.append(",");
				bw.append(((Boolean)currFeat.getContainsHappyEmo()).toString()); bw.append(",");
				bw.append(((Boolean)currFeat.getContainsSadEmo()).toString()); bw.append(",");
				
				if (currFeat.getContainsFirstOrderPron()!=null)
					bw.append(((Boolean)currFeat.getContainsFirstOrderPron()).toString()); 
				else
					bw.append("?");
				bw.append(",");
				
				if (currFeat.getContainsSecondOrderPron() != null)
					bw.append(((Boolean)currFeat.getContainsSecondOrderPron()).toString()); 
				else
					bw.append("?");
				bw.append(",");
				
				if (currFeat.getContainsThirdOrderPron() != null)
					bw.append(((Boolean)currFeat.getContainsThirdOrderPron()).toString());
				else
					bw.append("?");
				bw.append(",");
				
				bw.append(currFeat.getNumUppercaseChars().toString()); bw.append(",");
				
				if (currFeat.getNumPosSentiWords() != null)
					bw.append(currFeat.getNumPosSentiWords().toString()); 
				else
					bw.append("?");
				bw.append(",");
				
				if (currFeat.getNumNegSentiWords() != null)
					bw.append(currFeat.getNumNegSentiWords().toString());
				else
					bw.append("?");
				bw.append(",");
				
				bw.append(currFeat.getNumMentions().toString()); bw.append(",");
				bw.append(currFeat.getNumHashtags().toString()); bw.append(",");
				bw.append(currFeat.getNumURLs().toString()); bw.append(",");
				bw.append(currFeat.getRetweetCount().toString()); bw.append(",");
				
				if (currFeat.getNumSlangs() != null)
					bw.append(currFeat.getNumSlangs().toString());
				else
					bw.append("?");
				bw.append(",");
				
				bw.append(((Boolean)currFeat.getHasColon()).toString()); bw.append(",");
				bw.append(((Boolean)currFeat.getHasPlease()).toString()); bw.append(",");
				
				if (currFeat.getWotTrust()!=null)
					bw.append(currFeat.getWotTrust().toString());
				else
					bw.append("?");
				bw.append(",");
				bw.append(currFeat.getNumQuestionMark().toString()); bw.append(",");
				bw.append(currFeat.getNumExclamationMark().toString()); bw.append(",");
				
				if (currFeat.getReadability() != null)
					bw.append(currFeat.getReadability().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (currFeat.getUrlIndegree()!=null)
					bw.append(currFeat.getAlexaCountryRank().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (currFeat.getUrlHarmonic()!=null)
					bw.append(currFeat.getAlexaCountryRank().toString());
				else
					bw.append("?");
				bw.append(",");

				if (currFeat.getAlexaCountryRank()!=null)
					bw.append(currFeat.getAlexaCountryRank().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (currFeat.getAlexaDeltaRank()!=null)
					bw.append(currFeat.getAlexaCountryRank().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (currFeat.getAlexaPopularity()!=null)
					bw.append(currFeat.getAlexaCountryRank().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (currFeat.getAlexaReachRank()!=null)
					bw.append(currFeat.getAlexaCountryRank().toString());
				else
					bw.append("?");
				bw.append(",");
				
				bw.append(label);
				/*for (int j=0; j<itemAnnots.size(); j++) {
					if (itemAnnots.get(j).getId().equals(currFeat.getId())) {
						bw.append(itemAnnots.get(j).getReliability());
						break;
					}
				}*/
				
				if (i<ifeats.size()-1)
					bw.newLine();

			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void writeItemFeaturesListToFile(String filePath, List<TweetFeatures> ifeats, List<FeaturesAnnotationItem> itemAnnots)
			throws IOException {

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(filePath, false));
			bw.write("@RELATION data ");
			bw.newLine();
			bw.write("@ATTRIBUTE id string");
			bw.newLine();
			bw.write("@ATTRIBUTE ItemLength numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numWords numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE containsQuestionMark {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE containsExclamationMark {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE hasExternalLink {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE numNouns numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE containsHappyEmo {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE containsSadEmo {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE containsFirstOrderPron {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE containsSecondOrderPron {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE containsThirdOrderPron {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE numUppercaseChars numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numPosSentiWords numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numNegSentiWords numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numMentions numeric}");
			bw.newLine();
			bw.write("@ATTRIBUTE numHashtags numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numURLs numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE retweetCount numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numSlangs numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE hasColon {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE hasPlease {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE wotTrust numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numQuestionMark numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numExclamationMark numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE readability numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE urlIndegree numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE urlHarmonic numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaCountryRank numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaDeltaRank numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaPopularity numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaReachRank numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE theClass {fake,real}");
			bw.newLine();
			bw.write("@DATA");
			bw.newLine();

			for (int i = 0; i < ifeats.size(); i++) {
				// if file doesn't exist, then create it
				// System.out.println(dataset.get(i).toString());
				
				TweetFeatures currFeat = ifeats.get(i);
				bw.append("'"+currFeat.getId()+"'"); bw.append(",");
				bw.append(currFeat.getItemLength().toString()); bw.append(",");
				bw.append(currFeat.getNumWords().toString()); bw.append(",");
				bw.append(((Boolean)currFeat.getContainsQuestionMark()).toString()); bw.append(",");
				bw.append(((Boolean)currFeat.getContainsExclamationMark()).toString()); bw.append(",");
				bw.append(((Boolean)currFeat.getHasExternalLink()).toString()); bw.append(",");
				if (currFeat.getNumNouns() != null)
					bw.append(currFeat.getNumNouns().toString());
				else
					bw.append("?");
				bw.append(",");
				bw.append(((Boolean)currFeat.getContainsHappyEmo()).toString()); bw.append(",");
				bw.append(((Boolean)currFeat.getContainsSadEmo()).toString()); bw.append(",");
				
				if (currFeat.getContainsFirstOrderPron()!=null)
					bw.append(((Boolean)currFeat.getContainsFirstOrderPron()).toString()); 
				else
					bw.append("?");
				bw.append(",");
				
				if (currFeat.getContainsSecondOrderPron() != null)
					bw.append(((Boolean)currFeat.getContainsSecondOrderPron()).toString()); 
				else
					bw.append("?");
				bw.append(",");
				
				if (currFeat.getContainsThirdOrderPron() != null)
					bw.append(((Boolean)currFeat.getContainsThirdOrderPron()).toString());
				else
					bw.append("?");
				bw.append(",");
				
				bw.append(currFeat.getNumUppercaseChars().toString()); bw.append(",");
				
				if (currFeat.getNumPosSentiWords() != null)
					bw.append(currFeat.getNumPosSentiWords().toString()); 
				else
					bw.append("?");
				bw.append(",");
				
				if (currFeat.getNumNegSentiWords() != null)
					bw.append(currFeat.getNumNegSentiWords().toString());
				else
					bw.append("?");
				bw.append(",");
				
				bw.append(currFeat.getNumMentions().toString()); bw.append(",");
				bw.append(currFeat.getNumHashtags().toString()); bw.append(",");
				bw.append(currFeat.getNumURLs().toString()); bw.append(",");
				bw.append(currFeat.getRetweetCount().toString()); bw.append(",");
				
				if (currFeat.getNumSlangs() != null)
					bw.append(currFeat.getNumSlangs().toString());
				else
					bw.append("?");
				bw.append(",");
				
				bw.append(((Boolean)currFeat.getHasColon()).toString()); bw.append(",");
				bw.append(((Boolean)currFeat.getHasPlease()).toString()); bw.append(",");
				
				if (currFeat.getWotTrust()!=null)
					bw.append(currFeat.getWotTrust().toString());
				else
					bw.append("?");
				bw.append(",");
				bw.append(currFeat.getNumQuestionMark().toString()); bw.append(",");
				bw.append(currFeat.getNumExclamationMark().toString()); bw.append(",");
				
				if (currFeat.getReadability() != null)
					bw.append(currFeat.getReadability().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (currFeat.getUrlIndegree()!=null)
					bw.append(currFeat.getAlexaCountryRank().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (currFeat.getUrlHarmonic()!=null)
					bw.append(currFeat.getAlexaCountryRank().toString());
				else
					bw.append("?");
				bw.append(",");

				if (currFeat.getAlexaCountryRank()!=null)
					bw.append(currFeat.getAlexaCountryRank().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (currFeat.getAlexaDeltaRank()!=null)
					bw.append(currFeat.getAlexaCountryRank().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (currFeat.getAlexaPopularity()!=null)
					bw.append(currFeat.getAlexaCountryRank().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (currFeat.getAlexaReachRank()!=null)
					bw.append(currFeat.getAlexaCountryRank().toString());
				else
					bw.append("?");
				bw.append(",");
				
			
				for (int j=0; j<itemAnnots.size(); j++) {
					if (itemAnnots.get(j).getId().equals(currFeat.getId())) {
						bw.append(itemAnnots.get(j).getLabel());
						break;
					}
				}
				
				if (i<ifeats.size()-1)
					bw.newLine();

			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void writeUserFeaturesListToFile(String filePath, List<UserFeatures> ufeats, List<UserFeaturesAnnotation> listUserAnnots)
			throws IOException {

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(filePath, false));
			bw.write("@RELATION data ");
			bw.newLine();
			bw.write("@ATTRIBUTE id string");
			bw.newLine();
			bw.write("@ATTRIBUTE numFriends numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numFollowers numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE FolFrieRatio numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE timesListed numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE hasUrl {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE isVerified {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE numTweets numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE hasBio {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE hasLocation {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE hasExistingLocation {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE wotTrustUser numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numMediaContent numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE accountAge numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE hasProfileImg {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE hasHeaderImg {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE tweetRatio numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE indegree numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE harmonic numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaCountryRank numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaDeltaRank numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaPopularity numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaReachRank numeric");
			bw.newLine();

			bw.write("@ATTRIBUTE class {fake,real}");
			bw.newLine();
			bw.write("@DATA");
			bw.newLine();

			for (int i = 0; i < ufeats.size(); i++) {
				// if file doesn't exist, then create it
				UserFeatures ufeat = ufeats.get(i);
				
				bw.append("'"+ufeat.getId()+"'");
				bw.append(",");
				bw.append(ufeat.getNumFriends().toString());
				bw.append(",");
				bw.append(ufeat.getNumFollowers().toString());
				bw.append(",");
				bw.append(ufeat.getFolFrieRatio().toString());
				bw.append(",");
				bw.append(ufeat.getTimesListed().toString());
				bw.append(",");
				bw.append(ufeat.getHasURL().toString());
				bw.append(",");
				bw.append(ufeat.getIsVerified().toString());
				bw.append(",");
				bw.append(ufeat.getNumTweets().toString());
				bw.append(",");
				bw.append(ufeat.getHasBio().toString());
				bw.append(",");
				bw.append(ufeat.getHasLocation().toString());
				bw.append(",");
				bw.append(ufeat.getHasExistingLocation().toString());
				bw.append(",");
				
				if (ufeat.getWotTrustUser() != null)
					bw.append(ufeat.getWotTrustUser().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (ufeat.getNumMediaContent() != null)
					bw.append(ufeat.getNumMediaContent().toString());
				else
					bw.append("?");
				bw.append(",");
				if (ufeat.getAccountAge()!=null)
					bw.append(ufeat.getAccountAge().toString());
				else
					bw.append("?");
				bw.append(",");
				bw.append(ufeat.getHasProfileImg().toString());
				bw.append(",");
				bw.append(ufeat.getHasHeaderImg().toString());
				bw.append(",");
				bw.append(ufeat.getTweetRatio().toString());
				bw.append(",");
				
				if (ufeat.getIndegree()!=null)
					bw.append(ufeat.getIndegree().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (ufeat.getHarmonic()!=null)
					bw.append(ufeat.getIndegree().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (ufeat.getAlexaCountryRank()!=null)
					bw.append(ufeat.getAlexaCountryRank().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (ufeat.getAlexaDeltaRank()!=null)
					bw.append(ufeat.getAlexaDeltaRank().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (ufeat.getAlexaPopularity()!=null)
					bw.append(ufeat.getAlexaPopularity().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (ufeat.getAlexaReachRank()!=null)
					bw.append(ufeat.getAlexaReachRank().toString());
				else
					bw.append("?");
				bw.append(",");

				for (int j=0; j<listUserAnnots.size(); j++) {
					if (listUserAnnots.get(j).getId().equals(ufeat.getId())) {
						bw.append(listUserAnnots.get(j).getReliability());
						break;
					}
				}
				
				if (i<ufeats.size()-1)
					bw.newLine();

			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public void writeUserFeaturesListToFile(String filePath, List<UserFeatures> ufeats, String label)
			throws IOException {

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(filePath, false));
			bw.write("@RELATION data ");
			bw.newLine();
			bw.write("@ATTRIBUTE id string");
			bw.newLine();
			bw.write("@ATTRIBUTE numFriends numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numFollowers numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE FolFrieRatio numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE timesListed numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE hasUrl {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE isVerified {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE numTweets numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE hasBio {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE hasLocation {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE hasExistingLocation {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE wotTrustUser numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE numMediaContent numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE accountAge numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE hasProfileImg {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE hasHeaderImg {true,false}");
			bw.newLine();
			bw.write("@ATTRIBUTE tweetRatio numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE indegree numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE harmonic numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaCountryRank numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaDeltaRank numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaPopularity numeric");
			bw.newLine();
			bw.write("@ATTRIBUTE alexaReachRank numeric");
			bw.newLine();

			bw.write("@ATTRIBUTE class {fake,real}");
			bw.newLine();
			bw.write("@DATA");
			bw.newLine();

			for (int i = 0; i < ufeats.size(); i++) {
				// if file doesn't exist, then create it
				UserFeatures ufeat = ufeats.get(i);
				
				bw.append("'"+ufeat.getId()+"'");
				bw.append(",");
				bw.append(ufeat.getNumFriends().toString());
				bw.append(",");
				bw.append(ufeat.getNumFollowers().toString());
				bw.append(",");
				bw.append(ufeat.getFolFrieRatio().toString());
				bw.append(",");
				bw.append(ufeat.getTimesListed().toString());
				bw.append(",");
				bw.append(ufeat.getHasURL().toString());
				bw.append(",");
				bw.append(ufeat.getIsVerified().toString());
				bw.append(",");
				bw.append(ufeat.getNumTweets().toString());
				bw.append(",");
				bw.append(ufeat.getHasBio().toString());
				bw.append(",");
				bw.append(ufeat.getHasLocation().toString());
				bw.append(",");
				bw.append(ufeat.getHasExistingLocation().toString());
				bw.append(",");
				
				if (ufeat.getWotTrustUser() != null)
					bw.append(ufeat.getWotTrustUser().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (ufeat.getNumMediaContent() != null)
					bw.append(ufeat.getNumMediaContent().toString());
				else
					bw.append("?");
				bw.append(",");
				if (ufeat.getAccountAge()!=null)
					bw.append(ufeat.getAccountAge().toString());
				else
					bw.append("?");
				bw.append(",");
				bw.append(ufeat.getHasProfileImg().toString());
				bw.append(",");
				bw.append(ufeat.getHasHeaderImg().toString());
				bw.append(",");
				bw.append(ufeat.getTweetRatio().toString());
				bw.append(",");
				
				if (ufeat.getIndegree()!=null)
					bw.append(ufeat.getIndegree().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (ufeat.getHarmonic()!=null)
					bw.append(ufeat.getIndegree().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (ufeat.getAlexaCountryRank()!=null)
					bw.append(ufeat.getAlexaCountryRank().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (ufeat.getAlexaDeltaRank()!=null)
					bw.append(ufeat.getAlexaDeltaRank().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (ufeat.getAlexaPopularity()!=null)
					bw.append(ufeat.getAlexaPopularity().toString());
				else
					bw.append("?");
				bw.append(",");
				
				if (ufeat.getAlexaReachRank()!=null)
					bw.append(ufeat.getAlexaReachRank().toString());
				else
					bw.append("?");
				bw.append(",");

				bw.append(label);
				
				if (i<ufeats.size()-1)
					bw.newLine();

			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/*
	public void writeUserFeaturesToFile(String db, String collection,
			String filePathToWrite) throws IOException {

		UserFeaturesExtractor ife = new UserFeaturesExtractor();

		//List<String> idsFromWhichToGetFeats = loadIdsfromFileWithCondition(
			//	filePath, Vars.COMMA);
		List<String> idsFromWhichToGetFeats = DBHandler.getInstance().getExistingTweetsIds
				("TestSet2016_v2", collection, "id_str");
		BufferedWriter bw = new BufferedWriter(new FileWriter(filePathToWrite,true));

		for (String id : idsFromWhichToGetFeats) {

			System.out.println(id);
			UserFeatures ufeat = ife.getUserFeaturesById(db, collection+"_user", id);
			if (ufeat != null) {

				bw.write(ufeat.getId());
				bw.write(Vars.COMMA);
				bw.write(ufeat.getNumFriends().toString());
				bw.write(Vars.COMMA);
				bw.write(ufeat.getNumFollowers().toString());
				bw.write(Vars.COMMA);
				bw.write(ufeat.getFolFrieRatio().toString());
				bw.write(Vars.COMMA);
				bw.write(ufeat.getTimesListed().toString());
				bw.write(Vars.COMMA);
				bw.write(ufeat.gethasURL().toString());
				bw.write(Vars.COMMA);
				bw.write(ufeat.getisVerified().toString());
				bw.write(Vars.COMMA);
				bw.write(ufeat.getNumTweets().toString());
				bw.newLine();
				bw.flush();
			} else {
				bw.write(id);
				bw.newLine();
				bw.flush();
			}
		}

		bw.close();

	}
*/
	public boolean isAppropriateforDownload(String url) {

		int height = 0, width = 0;
		URL testit;
		try {
			testit = new URL(url);
			BufferedImage image;
			try {
				image = ImageIO.read(testit);
				if (image != null) {
					height = image.getHeight();
					width = image.getWidth();
				}
			} catch (IOException e) {
				// e.printStackTrace();
				return false;
			}
		} catch (Exception e1) {
			return false;
		}

		return (height > 200 && width > 200);
	}

	public void convertFilesToJPG(String folderPath) {

		int countConversions = 0;

		BufferedImage bufferedImage;

		File folder = new File(folderPath);

		int countPngs = 0;
		for (File file : folder.listFiles()) {
			if (file.getAbsolutePath().endsWith("png")
					|| file.getAbsolutePath().endsWith("PNG")) {
				countPngs++;
				try {
					System.out.println("Converting: " + file.getAbsolutePath());
					// read image file
					bufferedImage = ImageIO.read(file);

					// create a blank, RGB, same width and height, and a white
					// background
					BufferedImage newBufferedImage = new BufferedImage(
							bufferedImage.getWidth(),
							bufferedImage.getHeight(),
							BufferedImage.TYPE_INT_RGB);
					newBufferedImage.createGraphics().drawImage(bufferedImage,
							0, 0, Color.WHITE, null);

					// write to jpeg file

					if (ImageIO.write(newBufferedImage, "jpg", new File(file
							.getAbsolutePath().replaceAll(".png", ".jpg")))) {

						System.out.println("File converted to: "
								+ file.getAbsolutePath().replaceAll("png",
										"jpg"));
						countConversions++;
						System.out.println("Done");
					} else {
						System.out.println("Conversion cannot be done!");
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("PNGs found " + countPngs);
		System.out.println(countConversions + " performed.");
	}

	public void splitFilesToFolders(String folderPath) {

		// define how many files per folder
		int numFilesperFolder = 5000;

		// which is the folder where the files are?
		File folder = new File(folderPath);
		// calculate the num of folders you need to split equally the files
		int intNumFolders = folder.listFiles().length / numFilesperFolder;
		int restFiles = folder.listFiles().length % numFilesperFolder;
		// if there are more files use one folder more
		if (restFiles != 0)
			intNumFolders += 1;

		System.out.println("Number of folders to split the files: "
				+ intNumFolders);

		File[] folders = new File[intNumFolders];
		for (int i = 0; i < intNumFolders; i++) {
			folders[i] = new File(folder.getParent() + "\\" + folder.getName() + (i));
			if (!folders[i].exists()) {
				folders[i].mkdir();
			}
			System.out.println(folders[i].getAbsolutePath());
		}

		int counter = 0, j = 0;
		for (File file : folder.listFiles()) {

			if (counter < numFilesperFolder) {
				File filenew = new File(folders[j] + "\\" + file.getName());
				file.renameTo(filenew);
				counter++;
				System.out.println(filenew.getAbsolutePath());
			} else {
				j++;
				counter = 0;
			}
		}

	}
	
	/**
	 * Get the ids of the image files from a specified folder.
	 * @param folderPath the path which the folder is
	 * @return List<String> a list of ids found
	 */
	public List<String> getListIdsFromFileNames(String folderPath) {
		
		//which is the folder where the files are?
		File folder = new File(folderPath);
		
		//which list the ids will be saved?
		List<String> ids = new ArrayList<String>();
		
		for (File f:folder.listFiles()) {
			
			String name = f.getName();
			
			String id = name.split("-")[0].replaceAll("[^\\d]", "");
			ids.add(id);
			//System.out.println(id);
		}
		System.out.println("ids size: " + ids.size());
		Set<String> uniqueIds = new HashSet<String>();
		uniqueIds.addAll(ids);
		System.out.println("ids unique size: "+uniqueIds.size());
		return ids;
		
	}
	
	
	/* METHODS FOR FORENSICS */
	
	public String getImageId (String tweetId, String filePath, int positionInFile) {
		String imageid = "";
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line = "";
			while ( (line=br.readLine()) != null ) {
				
				if (line.startsWith(tweetId)) {
					imageid = line.split("\t")[positionInFile];
					if (imageid.contains(",")) {
						imageid = imageid.split(",")[0];
					}
					imageid = imageid.trim();
					
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println("For tweetId: "+tweetId +" the image id is: "+ imageid);
		
		return imageid;
	}
	
	public Double[] getBfArray(String imageId, String filePath) {
		
		Double[] bfArray = new Double[22];
		//System.out.println("Finding bf values for "+imageId+"...");
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line = "";
			while ( (line=br.readLine()) != null ) {
				if (line.startsWith(imageId)) {

					String lineWithoutId = line.substring(line.indexOf(",")+1);
					String parts[] = lineWithoutId.split(",");
					
					for (int i=0; i<bfArray.length; i++) {
						bfArray[i] = Double.parseDouble(parts[i]);
					}
					
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println("Returning bf values: "+ Arrays.arrayToString(bfArray));
		
		return bfArray;
		
	}
	
	public Double[] getQuantSteps(String imageId, String filePath) {
		
		Double[] sadArray = new Double[8];
		
		//System.out.println("Finding sad/snad values for "+imageId+"...");
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line = "";
			while ( (line=br.readLine()) != null ) {
				if (line.startsWith(imageId)) {

					String lineWithoutId = line.substring(line.indexOf(",")+1);
					String parts[] = lineWithoutId.split(",");
					
					
					for (int i=0; i<sadArray.length; i++) {
						sadArray[i] = Double.parseDouble(parts[i]);
					}
					
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println("Returning sad/snad values: "+ Arrays.arrayToString(sadArray));
		
		return sadArray;
	}
	
	public Map<String, Double[]> loadForensics(String filePath) {
		
		//System.out.println("Finding sad/snad values for "+imageId+"...");
		Map<String, Double[]> map = new HashMap<String, Double[]>();
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line = "";
			while ( (line = br.readLine()) != null ) {
				//if (line.startsWith(imageId)) {

				String imageID = line.split(",")[0];
				
				String lineWithoutId = line.substring(line.indexOf(",")+1);
				String parts[] = lineWithoutId.split(",");
				
				Double[] array = new Double[parts.length];
				
				for (int i=0; i<array.length; i++) {
					array[i] = Double.parseDouble(parts[i].trim());
				}
				
				map.put(imageID, array);
				
				//System.out.println(imageID+" "+Arrays.arrayToString(array));
				
				//}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}
	
	/*public Double[] getJpgbag(String imageId, String filePath) {
		
		Double[] sadArray = new Double[16];
		
		System.out.println("Finding ajpgbag/najpgbag values for "+imageId+"...");
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line = "";
			while ( (line=br.readLine()) != null ) {
				if (line.startsWith(imageId)) {

					String lineWithoutId = line.substring(line.indexOf(",")+1);
					String parts[] = lineWithoutId.split(",");
					
					
					for (int i=0; i<sadArray.length; i++) {
						sadArray[i] = Double.parseDouble(parts[i]);
					}
					
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Returning ajpgbag/najpgbag values: "+ Arrays.arrayToString(sadArray));
		
		return sadArray;
	}*/
	
	public void writeDoubleArrayToFile(double[][] array, String filePath) {
		
		File file = new File(filePath);// your file
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));

			for (int i=0; i<array.length; i++) {
				for (int j=0; j<array[i].length; j++) {
					bw.write(Double.toString(array[i][j])+"\t");
				}
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
