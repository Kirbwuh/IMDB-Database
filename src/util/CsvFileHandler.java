package src.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles saving and loading of comma separated value files.
 * @author Haitam Lembaid 18/03/2026 T10
 */
public class CsvFileHandler {
    // Instance Variables
    private final String filepath;
    private final File movies;

    /**
     * The defined constructor for CSVFileHandler.
     * @param filepath - The relative filepath of the csv file.
     * @param movies - The csv file name.
     */
    public CsvFileHandler(String filepath, File movies) {
        this.filepath = filepath;
        this.movies = movies;
    }

    /**
     * Method to save added movies to CSV file. Catches IOException.
     * @param filepath - The relative filepath of the csv file.
     */
    public void saveToCSV(String filepath) {
        try {
            FileWriter save = new FileWriter(filepath);
            save.append();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The file is not in the same directory (either doesn't exist or was moved.)");
        }
    }

    /**
     * Loads the csv file into memory to read contents.
     * @param filepath - The relative filepath of the csv file.
     */
    public void loadCSV(String filepath) {
        try {
            FileReader load = new FileReader(filepath);
            load.readAllLines();
            load.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The file is not in the same directory (either doesn't exist or was moved.");
        }
    }
}