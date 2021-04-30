package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple brute force implementation
 *
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filepath;

	/**
	 * 
	 * @param filepath a full or partial path to file with symptom strings in it,
	 *                 one per line
	 */
	public ReadSymptomDataFromFile(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * 
	 * @return an ArrayList of all the symptoms from the DataBase given.
	 */
	@Override
	public List<String> GetSymptoms() {
		ArrayList<String> result = new ArrayList<String>();

		if (this.filepath != null) {

			if (!Paths.get("Project02Eclipse\\").toAbsolutePath().toString().equals(filepath)) { // Manage if empty
																									// String

				try {

					BufferedReader reader = new BufferedReader(new FileReader(this.filepath));

					try {

						String line = reader.readLine();

						while (line != null) {

							if (!line.equals("")) { //Manage if blank line in the file

								result.add(line);
							}

							line = reader.readLine();
						}

					} catch (Exception E) {

						System.out.println("Exception found in the database file, couldn't write to the List.");

					} finally {

						reader.close();
					}
				} catch (IOException e) {

					System.out.println(
							"Couldn't load properly the file, please put the file database it in the Project02Eclipse directory.");
				}
			} else {

				System.out.println("Please enter a name for the database. You set an empty string.");

			}

		} else {

			System.out.println("The given path for the database is null");
		}

		return result;

	}
}
