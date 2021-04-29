package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

	/**
	 * Constructor of the AnalyticsCounter class.
	 * 
	 */
	public AnalyticsCounter() {
		dicoOfSymptoms = new TreeMap<String, Integer>();
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
	public Map<String, Integer> generateResult(ArrayList<String> theData) {
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

	public static void main(String args[]) throws Exception {

		Path path = Paths.get("Project02Eclipse\\symptoms.txt");
		AnalyticsCounter analyticsCounter = new AnalyticsCounter();
		ReadSymptomDataFromFile readData = new ReadSymptomDataFromFile(path.toAbsolutePath().toString());

		ArrayList<String> theData = (ArrayList<String>) readData.GetSymptoms();

		analyticsCounter.generateResult(theData);

		FileWriter writer = new FileWriter("result.out");

		for (Map.Entry<String, Integer> kv : analyticsCounter.getDicoOfSymptoms().entrySet()) {

			writer.write(kv.getKey() + "=" + kv.getValue() + "\n");
		}

		writer.close();
	}
}
