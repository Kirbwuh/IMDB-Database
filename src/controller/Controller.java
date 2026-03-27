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

    private static boolean csvLoaded = false;
    private static final String MOVIE_CSV_PATH = "src/util/Movies.csv";

    /** JJ - 2026/09/14 - T10
     *  HL - 25/03/2026 - T10
     * Loads movies from the CSV file located at `MOVIE_CSV_PATH`
     */
    public static void loadMoviesFromCsv(){
        CsvFileHandler movieLoader = new CsvFileHandler(MOVIE_CSV_PATH);
        movieLoader.loadCSV();
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
                Long.parseLong(movieEntriesData.get(7)));        // gross profit

        CsvFileHandler movieSaver = new CsvFileHandler(MOVIE_CSV_PATH);
        movieSaver.saveToCSV(movie);
    }

    /**
     * turns the string of attributes into a movie object
     * Arraf Hoque T10
     * @param movieEntriesData -an ArrayList of Strings-
     * @return movie object
     */
    private static Movie stringToMovie(List<String> movieEntriesData){
        Movie movie = new Movie(
            movieEntriesData.get(0),                         // title
            Integer.parseInt(movieEntriesData.get(1)),       // year
            Boolean.parseBoolean(movieEntriesData.get(2)),   // certification
            movieEntriesData.get(3),                         // genre
            Double.parseDouble(movieEntriesData.get(4)),     // IMDB RATING
            movieEntriesData.get(5),                         // description
            movieEntriesData.get(6),                         // director
            Long.parseLong(movieEntriesData.get(7)));        // gross profit
        return movie;
    }

    private static Movie stringToSeries(List<String> movieEntriesData){
        Movie movie = new Movie(
                movieEntriesData.get(0),                         // title
                Integer.parseInt(movieEntriesData.get(1)),       // year
                Boolean.parseBoolean(movieEntriesData.get(2)),   // certification
                movieEntriesData.get(3),                         // genre
                Double.parseDouble(movieEntriesData.get(4)),     // IMDB RATING
                movieEntriesData.get(5),                         // description
                movieEntriesData.get(6),                         // director
                Long.parseLong(movieEntriesData.get(7)));        // gross profit
        return movie;
    }

    /**
     * Performs MovieDatabase addMovie
     * Arraf Hoque T10
     * @param movieEntries
     */
        public static void handleAddMovie(ArrayList<String> movieEntries) {
            Movie movie = stringToMovie(movieEntries);
            MDB.addMovie(movie);
        }




    /**
     *  Performs MovieDatabase removeMovie
     *  Prints if there is an error with input
     *  Arraf Hoque T10
     * @param id
     * @param title
     */
        private void handleRemoveMovie(int id, String title){
        if (title == null){ //if there is no title, use the movie ID
            MDB.removeMovie(id);
        } else if (id == 0) { // if no int, pass the title use case
            MDB.removeMovie(title);
        }
        else{
            System.out.println("Please enter a valid movie ID or title."); // print if all else fails
        }
    }

    /**
     * Performs getMovie
     * Prints error message if it fails
     * Arraf Hoque T10
     * @param id
     * @param title
     */
        private void handleGetMovie(int id, String title){
        if (title == null){ //if there is no title, use the movie ID
            System.out.println(MDB.getMovie(id));
        } else if (id == 0) {
            System.out.println(MDB.getMovie(title));// if no int, pass the title use case
        }
        else{
            System.out.println("Please enter a valid movie ID or title.");
        }
    }

    /**
     * handles the updatemovie method from MovieDatabase
     * @param id
     * @param field
     * @param value
     * @param title
     */
        private void handleUpdateMovie(int id, int field, String value, String title){
        if (title == null){ //if there is no title, use the movie ID
            MDB.updateMovie(id, field, value);
        } else if (id == 0) {
            MDB.updateMovie(title, field, value);// if no int, pass the title use case
        }
        else{
            System.out.println("Please enter a valid movie ID or title.");
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

        Movie highestRated = null; // init highestRated

        for(Map.Entry<Integer, Movie> entry: MDB.getAllEntries().entrySet()){ // for each entry in MDB
            Movie value = entry.getValue(); //obtain entry
            if (value.getImdbRating() >= highestRated.getImdbRating()){ // Compare ratings, if new value is higher
                highestRated = value; // make it the new highest
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

        for(Map.Entry<Integer, Movie> entry: MDB.getAllEntries().entrySet()){
            Movie value = entry.getValue();
            int key = entry.getKey();
            if (value.getImdbRating() <= lowestRated.getImdbRating()){
                lowestRated = value;
            }

        }
        return lowestRated;
    }
}
