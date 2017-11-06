package gr.iti.mklab.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class RunnableRank implements Callable<Float> {

	private Thread t;
	private String threadName;
	private String urlName;
	private String filePathName;

	private String getUrl() {
		return urlName;
	}

	public RunnableRank(String name, String url, String filePath) {
		threadName = name;
		urlName = url;
		filePathName = filePath;
		//System.out.println("Creating " + threadName + " for " + url);
	}

	public Float call() throws Exception {

		long start = System.currentTimeMillis();

		Float rank = null;
		BufferedReader in;

		String url = getUrl();

		Map<String, String> hashmap = new HashMap<String, String>();

		//System.out.println("map defined!");

		BufferedReader in2;
		try {
			in2 = new BufferedReader(new FileReader(filePathName));
			String line = "";
			while ((line = in2.readLine()) != null) {
				String parts[] = line.split("\t");
				if (parts.length == 2) {
					hashmap.put(parts[0], parts[1]);
				} else {
					System.out.println(filePathName + " " + parts[0]);
				}

			}
			in2.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		//System.out.println("map loaded!");

		String strRank = hashmap.get(url);

		if (strRank != null)
			rank = Float.parseFloat(strRank);

		hashmap.clear();

		long end = System.currentTimeMillis();
		//System.out.println("thread " + threadName + "  "
				//+ (double) (end - start) / 1000 + " seconds");

		// System.out.println("Thread " + threadName + " Url: " + host +
		// ". Score " + rank);

		return rank;
	}

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
				// System.out.println(parts[0]);
				hashmap.put(parts[0], parts[1]);
			}
			in.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		//System.out.println(hashmap.size() + " objects loaded in the map");
		return hashmap;
	}

}
