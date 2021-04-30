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
 * Class that centralized all needed methods.
 * 
 * @see ReadSymptomDataFromFile
 * 
 * @author Alexandre OSSELIN
 * @version 1.4.5
 */

public class AnalyticsCounter {

	/**
	 * Dictionary. Cannot be modified with a Setter in order to protect the data
	 * from the Database.
	 */
	private Map<String, Integer> dicoOfSymptoms;

	/**
	 * The ArrayList that save all the data from the given Database.
	 */
	private ArrayList<String> theDataFromFile;

	/**
	 * Constructor of the AnalyticsCounter class.
	 * 
	 * 
	 * We set the comparator of the TreeMap to sort in alphabetical order the keys.
	 * 
	 */
	public AnalyticsCounter() {
		this.dicoOfSymptoms = new TreeMap<String, Integer>(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.toLowerCase().compareTo(o2.toLowerCase());
			}
		});

		this.theDataFromFile = new ArrayList<String>();
	}

	/**
	 * Getter of the dictionary that categorized and count occurrences all the data
	 * from file A Map is used, as a Dictionary, in order to adapt to any new
	 * symptoms and any occurrences.
	 * 
	 * @return The class Map
	 * 
	 */
	public Map<String, Integer> getDicoOfSymptoms() {
		return this.dicoOfSymptoms;
	}

	/**
	 * Getter of the List that saved all data from the database
	 * 
	 * @return theDataFromFile
	 * 
	 */
	public ArrayList<String> getTheDataFromFile() {
		return this.theDataFromFile;
	}

	/**
	 * Method called to make the whole system autonomous.
	 * 
	 * First the READER | Second the SORTER | Third the WRITER
	 * 
	 * 
	 * @see loadFile_READER()
	 * @see sortDataFromFile_SORTER()
	 * @see writeDataToFile_WRITER();
	 * 
	 * 
	 * @param the name of the database where the information is saved
	 * @param the wanted name for the file output.
	 * 
	 */
	public void start(String dataBaseNameFile, String resultFileName) {
		this.loadFile_READER(dataBaseNameFile);
		this.sortDataFromFile_SORTER();
		this.writeDataToFile_WRITER(resultFileName, "Project02Eclipse\\src\\com\\hemebiotech\\analytics\\");

	}

	/**
	 * READER : this method load the file and transform it into a Java List.
	 * 
	 * 
	 * @param nameOfDb is the name of the database file.
	 * 
	 * @see ReadSymptomDataFromFile()
	 * 
	 */
	private void loadFile_READER(String nameOfDb) {

		Path pathOfDatabase = Paths.get("Project02Eclipse\\" + nameOfDb);
		ReadSymptomDataFromFile reader = new ReadSymptomDataFromFile(pathOfDatabase.toAbsolutePath().toString());
		this.theDataFromFile = (ArrayList<String>) reader.GetSymptoms();

	}

	/**
	 * SORTER : this method transform the result of the reader (a List) into a
	 * dictionary (SORTER).
	 * 
	 * @return The Map representing the data of the file given.
	 * 
	 */
	private Map<String, Integer> sortDataFromFile_SORTER() {

		for (String s : this.theDataFromFile) {
			if (this.dicoOfSymptoms.containsKey(s)) {
				Integer newCount = this.dicoOfSymptoms.get(s);
				newCount++;
				this.dicoOfSymptoms.put(s, newCount);
			} else {
				this.dicoOfSymptoms.put(s, 1);
			}
		}
		return this.dicoOfSymptoms;
	}

	/**
	 * WRITER : this method write the results of the dictionary (result of SORTER)
	 * into a result file.
	 * 
	 * @param nameOfFile                is the name that the clients wants to give
	 *                                  to the output
	 * @param pathOfExpectedDestination is the destination path that the client want
	 *                                  to store the result in
	 * 
	 * @see ReadSymptomDataFromFile()
	 * 
	 */
	private void writeDataToFile_WRITER(String nameOfFile, String pathOfExpectedDestination) {

		WritingInFile writer = new WritingInFile(nameOfFile, pathOfExpectedDestination);
		writer.start(this.dicoOfSymptoms);

	}

}
