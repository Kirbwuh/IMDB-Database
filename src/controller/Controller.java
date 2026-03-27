package controller;

import model.MovieDatabase;
import model.SeriesDatabase;
import model.Movie;
import util.HelperMethods;
import util.CsvFileHandler;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


/**
 * Main Controller Method
 * Arraf Hoque T10
 */
public class Controller {


    public static Scanner scanner; //init Scanner
    private static final MovieDatabase MDB = new MovieDatabase();
    private static final SeriesDatabase SBD = new SeriesDatabase();
    private static final CsvFileHandler fileHandler = new CsvFileHandler("src/util/Movies.csv");
    private static boolean csvLoaded = false;
    private static final String CSV_PATH = "src/util/Movies.csv";

    public static void loadMoviesFromCsv(){// Load the CSV file once at startup // at least 8 elements.
        try {
            System.out.println("Loading movies from csv");
            if (Files.exists(Paths.get(CSV_PATH))) {
                List<String> lines = Files.readAllLines(Paths.get(CSV_PATH));

                for (String line : lines) {
                    if (line == null || line.trim().isEmpty())
                        continue; // skip blank lines

                    String[] parts = HelperMethods.separateCommaValues(line);
                    if (parts.length < 8)
                        continue; // skip less than 8 elements

                    ArrayList<String> entries = new ArrayList<>();
                    // copy up to t8 elements
                    for (int i = 0; i < parts.length && i < 8; i++) {
                        String value = parts[i];
                        value = value.trim();
                        entries.add(value);
                    }

                    // convert to movie object
                    Movie movie = stringToMovie(entries);
                    MDB.addMovie(movie);

                }
            }
            csvLoaded = true;
        } catch (Exception e) {

            System.out.println("Error loading CSV");
        }

    }

    /**
     * HL - 25/03/2026 - T10
     * Saves movies to CSV file at 'MOVIE_CSV_PATH'
     * @param movieEntriesData
     */
    public static void saveMoviesToCSV(List<String> movieEntriesData) {
        Movie movie = new Movie(
                movieEntriesData.get(0),                         // title
                Integer.parseInt(movieEntriesData.get(1)),       // year
                Boolean.parseBoolean(movieEntriesData.get(2)),   // certification
                movieEntriesData.get(3),                         // genre
                Double.parseDouble(movieEntriesData.get(4)),     // IMDB RATING
                movieEntriesData.get(5),                         // description
                movieEntriesData.get(6),                         // director
                util.HelperMethods.parseLongSafe(movieEntriesData.get(7)));

        CsvFileHandler movieSaver = new CsvFileHandler(CSV_PATH);
        movieSaver.saveToCSV(movie);
    }

    /**
     * turns the string of attributes into a movie object
     * Arraf Hoque T10
     * @param movieEntriesData -an ArrayList of Strings-
     * @return movie object
     */
    public static Movie stringToMovie(List<String> movieEntriesData){
        if (movieEntriesData == null || movieEntriesData.size() < 8) {
            System.out.println("Insufficient data to create a Movie. Expected 8 fields.");
            return null;
        }

        Movie movie = new Movie(
            movieEntriesData.get(0),                         // title
            Integer.parseInt(movieEntriesData.get(1)),       // year
            Boolean.parseBoolean(movieEntriesData.get(2)),   // certification
            movieEntriesData.get(3),                         // genre
            Double.parseDouble(movieEntriesData.get(4)),     // IMDB RATING
            movieEntriesData.get(5),                         // description
            movieEntriesData.get(6),                         // director
            util.HelperMethods.parseLongSafe(movieEntriesData.get(7)));        // gross profit

        return movie;
    }

    public static Movie stringToSeries(List<String> movieEntriesData){
        Movie movie = new Movie(
                movieEntriesData.get(0),                         // title
                Integer.parseInt(movieEntriesData.get(1)),       // year
                Boolean.parseBoolean(movieEntriesData.get(2)),   // certification
                movieEntriesData.get(3),                         // genre
                Double.parseDouble(movieEntriesData.get(4)),     // IMDB RATING
                movieEntriesData.get(5),                         // description
                movieEntriesData.get(6),                         // director
                util.HelperMethods.parseLongSafe(movieEntriesData.get(7)));        // gross profit
        return movie;
    }

    /**
     * Performs MovieDatabase addMovie
     * Arraf Hoque T10
     * @param movieEntries
     */
        public static void handleAddMovie(ArrayList<String> movieEntries) {
                Movie movie = stringToMovie(movieEntries);
                if (movie == null) {
                    System.out.println("Movie not added: invalid or incomplete input.");
                    return;
                }
                fileHandler.saveToCSV(movie);
                MDB.addMovie(movie);
        }


    /**
     *  Performs MovieDatabase removeMovie
     *  Prints if there is an error with input
     *  Arraf Hoque T10
     * @param id
     * @param title
     */
        public static boolean handleRemoveMovie(int id, String title){
        Movie target = null;

        if (title == null || title.isBlank()) {
            target = MDB.getMovie(id);
            if (target != null) {
                // Remove from disk before deleting from memory so we still have the original
                // object available to rebuild the exact CSV row that was written earlier.
                fileHandler.removeFromCSV(target);
                MDB.removeMovie(id);
                return true;
            }
        } else if (id == 0) {
            target = MDB.getMovie(title);
            if (target != null) {
                // Title-based removal follows the same pattern as ID removal for CSV sync.
                fileHandler.removeFromCSV(target);
                MDB.removeMovie(title);
                return true;
            }
        } else {
            System.out.println("Please enter a valid movie ID or title."); // print if all else fails
            return false;
        }

        System.out.println("Movie not found.");
        return false;
    }

    /**
     * Performs getMovie
     * Prints error message if it fails
     * Arraf Hoque T10
     * @param title
     */
        public static void handleGetMovie(String title){
        if (title != null){ //if there is no title, use the movie ID
            Movie target =  MDB.getMovie(title);
            System.out.println(target.toString());
        } else{
            System.out.println("Please enter a valid movie title.");
        }
    }

    /**
     * handles the updatemovie method from MovieDatabase
     * @param field
     * @param value
     * @param title
     */
        public static void handleUpdateMovie(int field, String value, String title){
        if (title != null){ //if there is no title, use the movie ID
            MDB.updateMovie(title, field, value);
        } else {
            System.out.println("Please enter a valid movie title.");
        }
    }

    /**
     * Prints all movies in the database
     * Arraf Hoque T10
     */
    public static ArrayList<String> handlePrintAllMovies() {
        Map<Integer, Movie> all = MDB.getAllMovies();
        ArrayList<String> out = new ArrayList<>();
        for (Map.Entry<Integer, Movie> e : all.entrySet()) {
            out.add(e.getKey() + ": " + e.getValue().toString());
        }
        return out;
    }

    /**
     * Writes all movies currently in the in-memory database to the CSV file,
     * overwriting any existing contents.
     */
    public static void saveAllMoviesToCsv() {
        // If CSV hasn't been loaded into memory yet, load existing CSV first
        if (!csvLoaded) {
            loadMoviesFromCsv();
        }

        try {
            var movies = MDB.getAllMovies();
            ArrayList<String> lines = new ArrayList<>();
            for (Movie m : movies.values()) {
                lines.add(m.toCSVStringRow());
            }
            java.nio.file.Files.write(java.nio.file.Paths.get(CSV_PATH), lines);
            System.out.println("Movies saved to CSV.");
        } catch (Exception e) {
            System.out.println("Error saving CSV");
        }
    }

    /**
     * prints the top 5 movies ranked
     * Collection of Movies -> get from the list of movies two movies -> compared movie 1 to movie 2 -> sort the objects on imbdrating
     * Arraf Hoque T10
     */
    public static ArrayList<Movie> getTop5(){
        ArrayList<Movie> movies = new ArrayList<>(MDB.getAllMovies().values());
        movies.sort(Comparator.comparingDouble(Movie::getImdbRating).reversed());
        ArrayList<Movie> top5 = new ArrayList<>();
        for (int i = 0; i < 5 && i < movies.size(); i++){
            top5.add(movies.get(i));
        }
        return top5;

    }

    /**
     * gets the highest rated movie in the database
     * Arraf Hoque T10
     */
    public static Movie handleHighestRating(){
        Movie highestRated = null;

        for (Movie value : MDB.getAllMovies().values()) {
            if (highestRated == null || value.getImdbRating() > highestRated.getImdbRating()) {
                highestRated = value;
            }
        }
        return highestRated;
    }

    /**
     * gets lowest rated movie in the database
     * Arraf Hoque T10
     */
    public static Movie handleLowestRating(){
        Movie lowestRated = null;

        for (Movie value : MDB.getAllMovies().values()) {
            if (lowestRated == null || value.getImdbRating() < lowestRated.getImdbRating()) {
                lowestRated = value;
            }
        }
        return lowestRated;
    }
}
