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
 * @version 1.4.7
 */

public class WritingInFile implements ISymptomWriter {

	private String wantedDestination;
	private String currentFileName;

	/**
	 * Constructor of the class.
	 * 
	 * We instantiated the FileWriter class here.
	 * 
	 */
	public WritingInFile(String nameOfFile, String expectedDestination) {
		this.currentFileName = nameOfFile;
		this.wantedDestination = expectedDestination;

	}

	/**
	 * Start the Writer
	 * 
	 * We instantiated the FileWriter class here.
	 * 
	 * @throws IOException
	 * 
	 */
	@Override
	public void writeSymptom(Map<String, Integer> map) {

		FileWriter writer = null;
		try {
			writer = new FileWriter(this.wantedDestination + this.currentFileName);

			for (Map.Entry<String, Integer> kv : map.entrySet()) {

				writer.write(kv.getKey() + "=" + kv.getValue() + "\n");
			}

		} catch (Exception e) {

			System.out.println(
					"Couldn't write an entry of the dictionary into the file results in class in the WRITER method");
		} finally {

			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
