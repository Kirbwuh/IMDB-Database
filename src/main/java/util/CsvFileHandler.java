package util;

import model.Movie;
import model.Series;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

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
            FileWriter fileWriter = new FileWriter(filepath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("");
            bufferedWriter.write(movie.toCSVStringRow());

            bufferedWriter.close();
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
            FileWriter fileWriter = new FileWriter(filepath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("");
            bufferedWriter.write(series.toCSVStringRow());

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The file is not in the same directory (either doesn't exist or was moved.)");
        }
    }

    public void removeFromCSV(Movie movie) {
        try {
            removeRow(movie.toCSVStringRow());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The file is not in the same directory (either doesn't exist or was moved.)");
        }
    }

    private void removeRow(String row) throws IOException {
        Path path = Path.of(filepath);
        if (!Files.exists(path)) {
            return;
        }

        var lines = Files.readAllLines(path);
        var updatedLines = new ArrayList<String>();
        boolean removed = false;

        for (String line : lines) {
            // Remove only the first exact row match so one delete action does not wipe
            // every duplicate-looking line in the CSV.
            if (!removed && line.equals(row)) {
                removed = true;
                continue;
            }
            updatedLines.add(line);
        }

        Files.write(path, updatedLines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Loads the csv file into memory to read contents. Catches IOException.
     */
    public void loadCSV() {
        try {
            Path path = Path.of(filepath);
            var lines = Files.readAllLines(path);
            for (String line : lines) {
                if (line == null || line.trim().isEmpty())
                    continue; // skip blank lines

                String[] parts = HelperMethods.separateCommaValues(line);
                if (parts.length < 8)
                    continue; // skip less than 8 elements
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The file is not in the same directory (either doesn't exist or was moved.");
        }
    }
}
