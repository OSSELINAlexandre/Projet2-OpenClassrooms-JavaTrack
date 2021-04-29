package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * <b>A solution</b>
 * <p>
 * What changed ?
 * <ul>
 * <li>The symptoms are into a Dictionary</li>
 * <li>To obtain the result you want in the output file, you just have to call
 * the method in ReadSymptomDataFromFile</li>
 * </ul>
 * </p>
 * 
 * @see ReadSymptomDataFromFile
 * 
 * @author Alexandre OSSELIN
 * @version 1.1
 */

public class AnalyticsCounter {

	/**
	 * Dictionary. Cannot be modified with a Setter in order to protect the data
	 * from the Database.
	 */
	public Map<String, Integer> dicoOfSymptoms;
	public Path path;
	public ReadSymptomDataFromFile reader;
	public WritingInFile writer;

	/**
	 * Constructor of the AnalyticsCounter class.
	 * 
	 */
	public AnalyticsCounter() {
		dicoOfSymptoms = new TreeMap<String, Integer>(new Comparator<String>() {
		    public int compare(String o1, String o2) {
		        return o1.toLowerCase().compareTo(o2.toLowerCase());
		    }
		});
		
		path = null;
		reader = null;
		writer = null;
		
	}

	public Map<String, Integer> getDicoOfSymptoms() {
		return dicoOfSymptoms;
	}

	/**
	 * Constructor of the AnalyticsCounter class.
	 * 
	 * @param ArrayList<String> a Java List with all the data.
	 * @see ReadSymptomDataFromFile
	 * @return the ordered dictionary with the symptom name and it's occurrences.
	 */
	public Map<String, Integer> sortFile() {
		ArrayList<String> theData = (ArrayList<String>) reader.GetSymptoms();
		for (String s : theData) {
			if (dicoOfSymptoms.containsKey(s)) {
				Integer newCount = dicoOfSymptoms.get(s);
				newCount++;
				dicoOfSymptoms.put(s, newCount);
			} else {
				dicoOfSymptoms.put(s, 1);
			}
		}
		return dicoOfSymptoms;
	}

	public void loadFile(String path) {
		Path pathway = Paths.get("Project02Eclipse\\" + path);
		reader = new ReadSymptomDataFromFile(pathway.toAbsolutePath().toString());

	}

	
	public void writeData(String nameOfFile, String nameOfExpectedDestination)  {
		
		try {
			writer = new WritingInFile(nameOfFile, nameOfExpectedDestination);

			for (Map.Entry<String, Integer> kv : this.getDicoOfSymptoms().entrySet()) {
				
				writer.Write(kv.getKey() + "=" + kv.getValue() + "\n");
			}
			writer.close();
			
		} catch (IOException e1) {
			System.out.println("Issue with the writing etc of file !");
			e1.printStackTrace();
		}
		


	}



}
