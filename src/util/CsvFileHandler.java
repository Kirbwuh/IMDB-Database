package src.util;

import org.w3c.dom.css.CSSValue;

import java.io.File;

/**
 * Handles saving and loading of comma separated value files.
 * @author Haitam Lembaid 18/03/2026 T10
 */
public class CsvFileHandler {
    // Instance Variables
    private final String filepath;
    private Model.MovieDatabase movieEntry;
    private File movies;

    public CsvFileHandler(String filepath, Model.MovieDatabase movieEntry, File movies) {
        this.filepath = "src/util/Movies.csv";
        this.movieEntry = new Model.MovieDatabase();
        this.movies = new File("Movies.csv");
    }

    /**
     *
     * @param filepath
     * @param db
     */
    public void saveToCSV(String filepath, Model.MovieDatabase db) {

    }

    /**
     *
     * @return
     */
    public File getMovies() {
        return movies;
    }
}