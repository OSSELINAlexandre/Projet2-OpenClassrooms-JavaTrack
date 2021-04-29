package com.hemebiotech.analytics;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Lauch {

	public static void main(String args[]) throws Exception {

		AnalyticsCounter analyticsCounter = new AnalyticsCounter();

		analyticsCounter.loadFile("symptoms.txt");
		analyticsCounter.sortFile();
		analyticsCounter.writeData("results.out" , "Project02Eclipse\\src\\com\\hemebiotech\\analytics\\");

	}

}
