package gr.iti.mklab.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import weka.core.Instances;

public class FileManager {

	private static FileManager sInstance = new FileManager();

	public static FileManager getInstance() {
		
		if (sInstance == null) {	
			sInstance = new FileManager();
		}
		return sInstance;

	}
	
	/**
	 * Sets the file's content to a HashSet<String>.
	 * @param file String the name of the file.
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
	
	
	
	
}
