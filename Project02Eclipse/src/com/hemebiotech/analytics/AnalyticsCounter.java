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
 * @version 1.4.2
 */

public class AnalyticsCounter {

	/**
	 * Dictionary. Cannot be modified with a Setter in order to protect the data
	 * from the Database.
	 */
	public Map<String, Integer> dicoOfSymptoms;
	/**
	 * The Path to the database.
	 */
	public Path pathOfDatabase;
	/**
	 * The class in charge of the service.
	 */
	/**
	 * 
	 */
	public ReadSymptomDataFromFile reader;
	/**
	 * my reader class to centralize all possible exception
	 */
	public WritingInFile writer;

	/**
	 * Constructor of the AnalyticsCounter class.
	 * 
	 * 
	 * We set the comparator of the TreeMap to sort in alphabetical order the keys.
	 * 
	 */
	public AnalyticsCounter() {
		dicoOfSymptoms = new TreeMap<String, Integer>(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.toLowerCase().compareTo(o2.toLowerCase());
			}
		});

		pathOfDatabase = null;
		reader = null;
		writer = null;

	}

	/**
	 * A Map is used, as a Dictionary, in order to adapt to any new symptoms and any
	 * occurrences.
	 * 
	 * @return The class Map
	 * 
	 */
	public Map<String, Integer> getDicoOfSymptoms() {
		return dicoOfSymptoms;
	}

	/**
	 * This method instantiate the ReadSymptomDataFromFile() class and find the path
	 * of the DataBase
	 * 
	 * 
	 * @param path is the name of the database file.
	 * 
	 * @see ReadSymptomDataFromFile()
	 * 
	 */
	public void loadFile(String path) {
		this.pathOfDatabase = Paths.get("Project02Eclipse\\" + path);
		reader = new ReadSymptomDataFromFile(pathOfDatabase.toAbsolutePath().toString());

	}

	/**
	 * This method, given a list of symptoms, counts the occurrences of every
	 * symptoms. The comparator in the constructor class is set to guarantee
	 * alphabetical order in the Map.
	 * 
	 * @return The Map representing the data of the file given.
	 * 
	 */
	public Map<String, Integer> sortDataFromFile() {
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

	/**
	 * This method will create a file given a name, to a given destination.
	 * 
	 * @param nameOfFile                is the name that the clients wants to give
	 *                                  to the output
	 * @param nameOfExpectedDestination is the destination path that the client want
	 *                                  to store the result in
	 * 
	 * @see ReadSymptomDataFromFile()
	 * 
	 */
	public void writeDataToFile(String nameOfFile, String nameOfExpectedDestination) {

		try {
			writer = new WritingInFile(nameOfFile, nameOfExpectedDestination);

			for (Map.Entry<String, Integer> kv : this.getDicoOfSymptoms().entrySet()) {

				writer.Write(kv.getKey() + "=" + kv.getValue() + "\n");
			}
			writer.close();

		} catch (IOException e1) {
			System.out.println("Issue with the writing of the file !");
			e1.printStackTrace();
		}

	}

}
