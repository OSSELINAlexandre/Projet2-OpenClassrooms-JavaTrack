package com.hemebiotech.analytics;

import java.util.Map;

/**
 * Anything that will write the symptom to an output. The important part is, the
 * argument from the operation, which is a Map, that may contain the symptoms
 * name, and their occurrences.
 * 
 * The implementation is already sorted
 * 
 */

public interface ISymptomWriter {

	/**
	 * Write the symptom to a output file, given in the main class.
	 * 
	 * Composed of the nature of symptom (name) and its occurrences in the Database.
	 * 
	 */
	void writeSymptom(Map<String, Integer> map);
}
