package com.hemebiotech.analytics;

/**
 * Main class to launch the application.
 * 
 * First, create an instance of AnalyticsCounter Then, call start([A],[B]) with
 * String A the name of your database file with the '.txt' extension and String
 * B the name of the output file you desire.
 * 
 * 
 * @see ReadSymptomDataFromFile
 * @see AnalyticsCounter
 * 
 * @author Alexandre OSSELIN
 * @version 1.4.7
 */

public class Launch {

	public static void main(String args[]) throws Exception {

		AnalyticsCounter analyticsCounter = new AnalyticsCounter();
		analyticsCounter.start("symptoms.txt", "results.out");

	}

}
