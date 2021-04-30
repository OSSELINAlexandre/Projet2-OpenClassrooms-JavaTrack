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
 * </p>
 * <p>
 * Autonomous class that can read data from a file in the directory, and produce
 * a result file</li>
 * </p>
 * 
 * 
 * @see ReadSymptomDataFromFile
 * 
 * @author Alexandre OSSELIN
 * @version 1.4.4
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
	 * The ArrayList that save all the data from the given Database.
	 */
	public ArrayList<String> theDataFromFile;

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

		this.pathOfDatabase = null;
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
		return dicoOfSymptoms;
	}

	/**
	 * Getter of the path of the database where the information is saved
	 * 
	 * @return pathOfDatabase
	 * 
	 */
	public Path getPathOfDatabase() {
		return pathOfDatabase;
	}

	/**
	 * Setter of the path of the database where the information is saved
	 * 
	 * @return pathOfDatabase
	 * 
	 */
	public void setPathOfDatabase(Path pathOfDatabase) {
		this.pathOfDatabase = pathOfDatabase;
	}

	/**
	 * Getter of the List that saved all data from the database
	 * 
	 * @return theDataFromFile
	 * 
	 */
	public ArrayList<String> getTheDataFromFile() {
		return theDataFromFile;
	}

	/**
	 * Setter of the List that saved all data from the database
	 * 
	 * @return pathOfDatabase
	 * 
	 */
	public void setTheDataFromFile(ArrayList<String> theDataFromFile) {
		this.theDataFromFile = theDataFromFile;
	}

	/**
	 * Setter of the dictionary that categorized and count occurrences all the data
	 * from file
	 * 
	 * @return dicoOfSymptoms
	 * 
	 */
	public void setDicoOfSymptoms(Map<String, Integer> dicoOfSymptoms) {
		this.dicoOfSymptoms = dicoOfSymptoms;
	}

	/**
	 * Method called to make the whole system autonomous.
	 * 
	 * First the READER | Second the SORTER | Third the WRITER
	 * 
	 * 
	 * @see loadFile()
	 * @see sortDataFromFile()
	 * @see writeDataToFile();
	 * 
	 * 
	 * @param the name of the database where the information is saved
	 * @param the wanted name for the file output.
	 * 
	 */
	public void start(String dataBaseNameFile, String resultFileName) {
		this.loadFile_READER(dataBaseNameFile);
		this.sortDataFromFile_SORTER();
		try {
			this.writeDataToFile_WRITER(resultFileName, "Project02Eclipse\\src\\com\\hemebiotech\\analytics\\");
		} catch (IOException e) {
			System.out.println("Issue while trying to write in the file.");
		}

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
	public void loadFile_READER(String nameOfDb) {

		this.pathOfDatabase = Paths.get("Project02Eclipse\\" + nameOfDb);
		ReadSymptomDataFromFile reader = new ReadSymptomDataFromFile(pathOfDatabase.toAbsolutePath().toString());
		theDataFromFile = (ArrayList<String>) reader.GetSymptoms();

	}

	/**
	 * SORTER : this method transform the result of the reader (a List) into a
	 * dictionary (SORTER).
	 * 
	 * @return The Map representing the data of the file given.
	 * 
	 */
	public Map<String, Integer> sortDataFromFile_SORTER() {

		for (String s : theDataFromFile) {
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
	 * WRITER : this method write the results of the dictionary (result of SORTER)
	 * into a result file.
	 * 
	 * @param nameOfFile                is the name that the clients wants to give
	 *                                  to the output
	 * @param pathOfExpectedDestination is the destination path that the client want
	 *                                  to store the result in
	 * @throws IOException
	 * 
	 * @see ReadSymptomDataFromFile()
	 * 
	 */
	public void writeDataToFile_WRITER(String nameOfFile, String pathOfExpectedDestination) throws IOException {

		WritingInFile writer = new WritingInFile(nameOfFile, pathOfExpectedDestination);
		writer.start(this.dicoOfSymptoms);

	}

}
