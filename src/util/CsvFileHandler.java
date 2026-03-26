package src.util;

import src.model.Movie;
import src.model.Series;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Handles saving and loading of comma separated value files. Extends from Movie.java to use the toCSVStringRow method.
 * @author Haitam Lembaid 18/03/2026 T10
 */
public class CsvFileHandler {
    // Instance Variables
    private final String filepath;

    /**
     * Constructor for CSVFileHandler
     * @param filepath - The filepath to be passed in.
     */
    public CsvFileHandler (String filepath) {
        this.filepath = filepath;
    }

    /**
     * Method to save added movies to CSV file. Catches IOException.
     * @param movie - A movie object
     */
    public void saveToCSV(Movie movie) {
        try {
            FileWriter save = new FileWriter(filepath);
            save.append(movie.toCSVStringRow());
            save.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The file is not in the same directory (either doesn't exist or was moved.)");
        }
    }

    /**
     * Method to save added series to CSV file. Catches IOException.
     * @param series
     */
    public void saveToCSV(Series series) {
        try {
            FileWriter save = new FileWriter(filepath);
            save.append(series.toCSVStringRow());
            save.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The file is not in the same directory (either doesn't exist or was moved.)");
        }
    }

    /**
     * Loads the csv file into memory to read contents. Catches IOException.
     */
    public void loadCSV() {
        try {
            FileReader load = new FileReader(filepath);
            List<String> lines = load.readAllLines();
            for (String line : lines) {
                if (line == null || line.trim().isEmpty())
                    continue; // skip blank lines

                String[] parts = HelperMethods.separateCommaValues(line);
                if (parts.length < 8)
                    continue; // skip less than 8 elements
            }
            load.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The file is not in the same directory (either doesn't exist or was moved.");
        }
    }
}