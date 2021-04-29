package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;

public class WritingInFile {

	public FileWriter writer;
	public String wantedDestination;
	public String currentFileName;

	public WritingInFile(String nameOfFile, String expectedDestination) throws IOException {
		super();

		currentFileName = nameOfFile;
		wantedDestination = expectedDestination;
		writer = new FileWriter(wantedDestination + currentFileName);
	}

	public void Write(String Message) throws IOException {

		writer.write(Message);

	}

	public void close() throws IOException {

		writer.close();

	}

	public FileWriter getWriter() {
		return writer;
	}

	public void setWriter(FileWriter writer) {
		this.writer = writer;
	}

	public String getWantedDestination() {
		return wantedDestination;
	}

	public void setWantedDestination(String wantedDestination) {
		this.wantedDestination = wantedDestination;
	}

	public String getCurrentFileName() {
		return currentFileName;
	}

	public void setCurrentFileName(String currentFileName) {
		this.currentFileName = currentFileName;
	}

}
