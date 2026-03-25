package src.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles saving and loading of comma separated value files. Extends from Movie.java to use the toCSVStringRow method.
 * @author Haitam Lembaid 18/03/2026 T10
 */
public class CsvFileHandler extends model.Movie {
    // Instance Variables
    private final String filepath;
    private final String movies;

    /**
     * Constructor built from extending Movie.java, with filepath and movies instance variables added in.
     * @param title         movie title
     * @param year          release year
     * @param certification true if rated PG-13
     * @param genre         genre(s) of the movie
     * @param imdbRating    IMDB rating out of 10
     * @param description   short description of the movie
     * @param director      name of the director
     * @param gross         total box office gross earnings
     */
    public CsvFileHandler(String title, int year, boolean certification, String genre, double imdbRating,
                          String description, String director, long gross, String filepath, String movies) {
        super(title, year, certification, genre, imdbRating, description, director, gross);
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
            save.append(toCSVStringRow());
            save.close();
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