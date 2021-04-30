package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * 
 * This class write in the file. It is possible to get or set any variables (the
 * name of the file in which you write, or the path of it). It is a way to
 * centralized all exception possible and therefore to see exactly where the bug
 * can be.
 * 
 * @author Alexandre OSSELIN
 * @version 1.4.5
 */

public class WritingInFile {

	private String wantedDestination;
	private String currentFileName;

	/**
	 * Constructor of the class.
	 * 
	 * We instantiated the FileWriter class here.
	 * 
	 */
	public WritingInFile(String nameOfFile, String expectedDestination) {
		super();

		this.currentFileName = nameOfFile;
		this.wantedDestination = expectedDestination;

	}

	/**
	 * Start the Writer
	 * 
	 * We instantiated the FileWriter class here.
	 * 
	 */
	public void start(Map<String, Integer> map) {

		try {
			FileWriter writer = new FileWriter(this.wantedDestination + this.currentFileName);

			try {
				for (Map.Entry<String, Integer> kv : map.entrySet()) {

					writer.write(kv.getKey() + "=" + kv.getValue() + "\n");

				}

			} catch (Exception e) {
				System.out.println(
						"Couldn't write an entry of the dictionary into the file results in class in the WRITER method");
			} finally {

				writer.close();
			}

		} catch (Exception E) {
			System.out.println(
					"Couldn't instantiate the FileWriter class in the WRITER method, there is an issue with the given path, or the name for the output file.");

		}

	}

}
