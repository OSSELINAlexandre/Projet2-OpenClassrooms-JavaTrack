package com.hemebiotech.analytics;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main class to launch the application.
 * 
 * Set the name of the DB in loadFile() Then, call the sortDataFromFile()
 * Finally, choose a name of output and a destination Path in writeDataToFile();
 * 
 * @see ReadSymptomDataFromFile
 * @see AnalyticsCounter
 * 
 * @author Alexandre OSSELIN
 * @version 1.4.4
 */

public class Lauch {

	public static void main(String args[]) throws Exception {

		AnalyticsCounter analyticsCounter = new AnalyticsCounter();
		analyticsCounter.start("symptoms.txt", "results.out");

	}

}
