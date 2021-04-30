package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * 
 * This class write in the file. It is possible to get or set any variables (the
 * name of the file in which you write, or the path of it). It is a way to
 * centralized all exception possible and therefore to see exactly where the bug
 * can be. F
 * 
 * @author Alexandre OSSELIN
 * @version 1.4.4
 */

public class WritingInFile {

	public FileWriter writer;
	public String wantedDestination;
	public String currentFileName;

	/**
	 * Constructor of the class.
	 * 
	 * We instantiated the FileWriter class here.
	 * 
	 * @throws IOException
	 */
	public WritingInFile(String nameOfFile, String expectedDestination) throws IOException {
		super();

		this.currentFileName = nameOfFile;
		this.wantedDestination = expectedDestination;
		this.writer = new FileWriter(this.wantedDestination + this.currentFileName);

	}

	/**
	 * Getter of Writer
	 * 
	 * @return the Object that Write
	 */
	public FileWriter getWriter() {
		return this.writer;
	}

	/**
	 * Setter of Writer
	 */
	public void setWriter(FileWriter writer) {
		this.writer = writer;
	}

	/**
	 * Getter of the Path
	 * 
	 * @return the String showing the Path of the result file
	 */
	public String getWantedDestination() {
		return this.wantedDestination;
	}

	/**
	 * Setter of the destination for the result.
	 */
	public void setWantedDestination(String wantedDestination) {
		this.wantedDestination = wantedDestination;
	}

	/**
	 * Getter of the result file name
	 * 
	 * @return the name of the result file.
	 */
	public String getCurrentFileName() {
		return this.currentFileName;
	}

	/**
	 * Setter of the result file name.
	 */
	public void setCurrentFileName(String currentFileName) {
		this.currentFileName = currentFileName;
	}

	public void start(Map<String, Integer> map) throws IOException {

		for (Map.Entry<String, Integer> kv : map.entrySet()) {

			this.writer.write(kv.getKey() + "=" + kv.getValue() + "\n");
		}

		this.writer.close();

	}

}
