package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AnalyticsCounter {
	
	private static int headacheCount = 0;	// initialize to 0 First mistake : The variable used in the code is different from the
	// class variable written here. 
	private static int rashCount = 0;		// initialize to 0
	private static int pupilCount = 0;		// initialize to 0
	
	public static void main(String args[]) throws Exception {
		
		String currentPath = System.getProperty("user.dir");
		String symptomPath = currentPath + "\\Project02Eclipse\\symptoms.txt" ;
		FileReader file = new FileReader(symptomPath);
		BufferedReader reader = new BufferedReader(file);
		String line = reader.readLine();
		
		int i = 0;	// set i to 0
		//  headaches = 0; counts headaches !! Yes, but you do have a global variable for that Purpose
		while (line != null) {
			i++;	// increment i
			System.out.println("symptom n°" + i + " from file: " + line);
			if (line.equals("headache")) {
				headacheCount++;
				System.out.println("number of headaches: " + headacheCount);
			}
			else if (line.equals("rush")) { 
				rashCount++;
				System.out.println("number of rush: " + rashCount);
			}
			else if (line.contains("pupils")) {
				pupilCount++;
				System.out.println("number of pupilsache: " + pupilCount);
			}

			line = reader.readLine();	// get another symptom
		}
				
		// next generate output
		FileWriter writer = new FileWriter ("result.out");
		writer.write("headache: " + headacheCount + "\n");
		writer.write("rash: " + rashCount + "\n");
		writer.write("dialated pupils: " + pupilCount + "\n");
		writer.close();
	}
}
