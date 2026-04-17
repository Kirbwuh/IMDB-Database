package controller;

import model.MovieDatabase;
import model.SeriesDatabase;
import model.Movie;
import model.Series;
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


    public Scanner scanner; //init Scanner
    private final MovieDatabase MDB = new MovieDatabase();
    private final SeriesDatabase SBD = new SeriesDatabase();
    private final CsvFileHandler fileHandler = new CsvFileHandler("src/main/resources/util/Movies.csv");
    private final CsvFileHandler seriesFileHandler = new CsvFileHandler("src/main/resources/util/Series.csv");
    private boolean csvLoaded = false;
    private boolean seriesCsvLoaded = false;
    private static final String CSV_PATH = "src/main/resources/util/Movies.csv";
    private static final String SERIES_CSV_PATH = "src/main/resources/util/Series.csv";

    public void loadMoviesFromCsv(){// Load the CSV file once at startup // at least 8 elements.
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

    public void loadSeriesFromCsv(){// Load the CSV file once at startup // at least 8 elements.
        try {
            System.out.println("Loading series from csv");
            if (Files.exists(Paths.get(SERIES_CSV_PATH))) {
                List<String> lines = Files.readAllLines(Paths.get(SERIES_CSV_PATH));

                for (String line : lines) {
                    if (line == null || line.trim().isEmpty())
                        continue; // skip blank lines

                    String[] parts = HelperMethods.separateCommaValues(line);
                    if (parts.length < 8)
                        continue; // skip less than 8 elements

                    ArrayList<String> entries = new ArrayList<>();
                    // copy up to 8 elements
                    for (int i = 0; i < parts.length && i < 8; i++) {
                        String value = parts[i];
                        value = value.trim();
                        entries.add(value);
                    }

                    // convert to series object
                    Series series = stringToSeries(entries);
                    SBD.addSeries(series);

                }
            }
            seriesCsvLoaded = true;
        } catch (Exception e) {

            System.out.println("Error loading Series CSV");
        }

    }

    /**
     * HL - 25/03/2026 - T10
     * Saves movies to CSV file at 'MOVIE_CSV_PATH'
     * @param movieEntriesData
     */
    public void saveMoviesToCSV(List<String> movieEntriesData) {
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
     * Saves series to CSV file at 'SERIES_CSV_PATH'
     * @param seriesEntriesData
     */
    public void saveSeriesToCSV(List<String> seriesEntriesData) {
        Series series = new Series(
                seriesEntriesData.get(0),                         // title
                Integer.parseInt(seriesEntriesData.get(1)),       // year
                seriesEntriesData.get(2),                         // genre
                Double.parseDouble(seriesEntriesData.get(3)),     // IMDB RATING
                seriesEntriesData.get(4),                         // description
                Integer.parseInt(seriesEntriesData.get(5)),       // number of seasons
                Integer.parseInt(seriesEntriesData.get(6)),       // number of episodes
                seriesEntriesData.get(7));                        // creator

        CsvFileHandler seriesSaver = new CsvFileHandler(SERIES_CSV_PATH);
        seriesSaver.saveToCSV(series);
    }

    /**
     * turns the string of attributes into a movie object
     * Arraf Hoque T10
     * @param movieEntriesData -an ArrayList of Strings-
     * @return movie object
     */
    public Movie stringToMovie(List<String> movieEntriesData){
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

    /**
     * turns the string of attributes into a series object
     * @param seriesEntriesData -an ArrayList of Strings-
     * @return series object
     */
    public Series stringToSeries(List<String> seriesEntriesData){
        if (seriesEntriesData == null || seriesEntriesData.size() < 8) {
            System.out.println("Insufficient data to create a Series. Expected 8 fields.");
            return null;
        }

        Series series = new Series(
                seriesEntriesData.get(0),                         // title
                Integer.parseInt(seriesEntriesData.get(1)),       // year
                seriesEntriesData.get(2),                         // genre
                Double.parseDouble(seriesEntriesData.get(3)),     // IMDB RATING
                seriesEntriesData.get(4),                         // description
                Integer.parseInt(seriesEntriesData.get(5)),       // number of seasons
                Integer.parseInt(seriesEntriesData.get(6)),       // number of episodes
                seriesEntriesData.get(7));                        // creator
        return series;
    }

    /**
     * Performs MovieDatabase addMovie
     * Arraf Hoque T10
     * @param movieEntries
     */
        public void handleAddMovie(ArrayList<String> movieEntries) {
                Movie movie = stringToMovie(movieEntries);
                if (movie == null) {
                    System.out.println("Movie not added: invalid or incomplete input.");
                    return;
                }

                // Check for duplicates using Movie.equals()
                for (Movie existing : MDB.getAllMovies().values()) {
                    if (movie.equals(existing)) {
                        System.out.println("Movie not added: a matching movie already exists.");
                        return;
                    }
                }

                fileHandler.saveToCSV(movie);
                MDB.addMovie(movie);
        }


    /**
     * Performs SeriesDatabase addSeries
     * @param seriesEntries
     */
        public void handleAddSeries(ArrayList<String> seriesEntries) {
                Series series = stringToSeries(seriesEntries);
                if (series == null) {
                    System.out.println("Series not added: invalid or incomplete input.");
                    return;
                }

                // Check for duplicates using Series.equals()
                for (Series existing : SBD.getAllSeries().values()) {
                    if (series.equals(existing)) {
                        System.out.println("Series not added: a matching series already exists.");
                        return;
                    }
                }

                seriesFileHandler.saveToCSV(series);
                SBD.addSeries(series);
        }


    /**
     *  Performs MovieDatabase removeMovie
     *  Prints if there is an error with input
     *  Arraf Hoque T10
     * @param id
     * @param title
     */
        public boolean handleRemoveMovie(int id, String title){
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
     *  Performs SeriesDatabase removeSeries
     *  Prints if there is an error with input
     * @param id
     * @param title
     */
        public boolean handleRemoveSeries(int id, String title){
        Series target = null;

        if (title == null || title.isBlank()) {
            target = SBD.getSeries(id);
            if (target != null) {
                // Remove from disk before deleting from memory so we still have the original
                // object available to rebuild the exact CSV row that was written earlier.
                seriesFileHandler.removeFromCSV(target);
                SBD.removeSeries(id);
                return true;
            }
        } else if (id == 0) {
            target = SBD.getSeries(title);
            if (target != null) {
                // Title-based removal follows the same pattern as ID removal for CSV sync.
                seriesFileHandler.removeFromCSV(target);
                SBD.removeSeries(title);
                return true;
            }
        } else {
            System.out.println("Please enter a valid series ID or title."); // print if all else fails
            return false;
        }

        System.out.println("Series not found.");
        return false;
    }

    /**
     * Performs getMovie
     * Prints error message if it fails
     * Arraf Hoque T10
     * @param title
     */
        public Movie handleGetMovie(String title){
        if (title != null){ //if there is no title, use the movie ID
            Movie target =  MDB.getMovie(title);
            if (target != null) {
                System.out.println(target.toString());
            }
            return target;
        } else{
            System.out.println("Please enter a valid movie title.");
            return null;
        }
    }

    /**
     * Performs getSeries
     * Prints error message if it fails
     * @param title
     */
        public Series handleGetSeries(String title){
        if (title != null){ //if there is no title, use the series ID
            Series target =  SBD.getSeries(title);
            if (target != null) {
                System.out.println(target.toString());
            }
            return target;
        } else{
            System.out.println("Please enter a valid series title.");
            return null;
        }
    }

    /**
     * handles the updatemovie method from MovieDatabase
     * @param field
     * @param value
     * @param title
     */
        public void handleUpdateMovie(int field, String value, String title){
        if (title != null){ //if there is no title, use the movie ID
            MDB.updateMovie(title, field, value);
        } else {
            System.out.println("Please enter a valid movie title.");
        }
    }

    /**
     * handles the updateSeries method from SeriesDatabase
     * @param field
     * @param value
     * @param title
     */
        public void handleUpdateSeries(int field, String value, String title){
        if (title != null){ //if there is no title, use the series ID
            SBD.updateSeries(title, field, value);
        } else {
            System.out.println("Please enter a valid series title.");
        }
    }

    /**
     * Prints all movies in the database
     * Arraf Hoque T10
     */
    public ArrayList<String> handlePrintAllMovies() {
        Map<Integer, Movie> all = MDB.getAllMovies();
        ArrayList<String> out = new ArrayList<>();
        for (Map.Entry<Integer, Movie> e : all.entrySet()) {
            out.add(e.getKey() + ": " + e.getValue().toString());
        }
        return out;
    }

    /**
     * Prints all series in the database
     */
    public ArrayList<String> handlePrintAllSeries() {
        Map<Integer, Series> all = SBD.getAllSeries();
        ArrayList<String> out = new ArrayList<>();
        for (Map.Entry<Integer, Series> e : all.entrySet()) {
            out.add(e.getKey() + ": " + e.getValue().toString());
        }
        return out;
    }

    public Collection<Movie> getAllMovies() {
        return MDB.getAllMovies().values();
    }

    public Collection<Series> getAllSeries() {
        return SBD.getAllSeries().values();
    }

    /**
     * Writes all movies currently in the in-memory database to the CSV file,
     * overwriting any existing contents.
     */
    public void saveAllMoviesToCsv() {
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
     * Writes all series currently in the in-memory database to the CSV file,
     * overwriting any existing contents.
     */
    public void saveAllSeriesToCsv() {
        // If CSV hasn't been loaded into memory yet, load existing CSV first
        if (!seriesCsvLoaded) {
            loadSeriesFromCsv();
        }

        try {
            var series = SBD.getAllSeries();
            ArrayList<String> lines = new ArrayList<>();
            for (Series s : series.values()) {
                lines.add(s.toCSVStringRow());
            }
            java.nio.file.Files.write(java.nio.file.Paths.get(SERIES_CSV_PATH), lines);
            System.out.println("Series saved to CSV.");
        } catch (Exception e) {
            System.out.println("Error saving Series CSV");
        }
    }

    /**
     * prints the top 5 movies ranked
     * Collection of Movies -> get from the list of movies two movies -> compared movie 1 to movie 2 -> sort the objects on imbdrating
     * Arraf Hoque T10
     */
    public ArrayList<Movie> getTop5(){
        ArrayList<Movie> movies = new ArrayList<>(MDB.getAllMovies().values());
        movies.sort(Comparator.comparingDouble(Movie::getImdbRating).reversed());
        ArrayList<Movie> top5 = new ArrayList<>();
        for (int i = 0; i < 5 && i < movies.size(); i++){
            top5.add(movies.get(i));
        }
        return top5;

    }

    /**
     * prints the top 5 series ranked
     */
    public ArrayList<Series> getTop5Series(){
        ArrayList<Series> series = new ArrayList<>(SBD.getAllSeries().values());
        series.sort(Comparator.comparingDouble(Series::getImdbRating).reversed());
        ArrayList<Series> top5 = new ArrayList<>();
        for (int i = 0; i < 5 && i < series.size(); i++){
            top5.add(series.get(i));
        }
        return top5;

    }

    /**
     * gets the highest rated movie in the database
     * Arraf Hoque T10
     */
    public Movie handleHighestRating(){
        Movie highestRated = null;

        for (Movie value : MDB.getAllMovies().values()) {
            if (highestRated == null || value.getImdbRating() > highestRated.getImdbRating()) {
                highestRated = value;
            }
        }
        return highestRated;
    }

    /**
     * gets the highest rated series in the database
     */
    public Series handleHighestSeriesRating(){
        Series highestRated = null;

        for (Series value : SBD.getAllSeries().values()) {
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
    public Movie handleLowestRating(){
        Movie lowestRated = null;

        for (Movie value : MDB.getAllMovies().values()) {
            if (lowestRated == null || value.getImdbRating() < lowestRated.getImdbRating()) {
                lowestRated = value;
            }
        }
        return lowestRated;
    }

    /**
     * gets lowest rated series in the database
     */
    public Series handleLowestSeriesRating(){
        Series lowestRated = null;

        for (Series value : SBD.getAllSeries().values()) {
            if (lowestRated == null || value.getImdbRating() < lowestRated.getImdbRating()) {
                lowestRated = value;
            }
        }
        return lowestRated;
    }

    public double handleAverageRating() {
        Collection<Movie> movies = MDB.getAllMovies().values();
        if (movies.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (Movie movie : movies) {
            total += movie.getImdbRating();
        }
        return total / movies.size();
    }
}
