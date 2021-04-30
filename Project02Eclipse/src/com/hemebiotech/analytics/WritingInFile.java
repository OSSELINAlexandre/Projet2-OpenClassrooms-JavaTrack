package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * This class write in the file. It is possible to get or set any variables (the
 * name of the file in which you write, or the path of it). It is a way to
 * centralized all exception possible and therefore to see exactly where the bug
 * can be. F
 * 
 * @author Alexandre OSSELIN
 * @version 1.4.3
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

		currentFileName = nameOfFile;
		wantedDestination = expectedDestination;
		writer = new FileWriter(wantedDestination + currentFileName);

	}

	/**
	 * Write in the file.
	 * 
	 * @throws IOException
	 * 
	 */
	public void Write(String Message) throws IOException {

		writer.write(Message);

	}

	/**
	 * Close the file.
	 * 
	 * @throws IOException
	 * 
	 */
	public void close() throws IOException {

		writer.close();

	}

	/**
	 * Getter of Writer
	 * 
	 * @return the Object that Write
	 */
	public FileWriter getWriter() {
		return writer;
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
		return wantedDestination;
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
		return currentFileName;
	}

	/**
	 * Setter of the result file name.
	 */
	public void setCurrentFileName(String currentFileName) {
		this.currentFileName = currentFileName;
	}

}
