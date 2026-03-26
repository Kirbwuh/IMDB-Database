package src.controller;

import src.model.MovieDatabase;
import src.model.Movie;
import src.util.HelperMethods;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;


/**
 * Main Controller Method
 * Arraf Hoque T10
 */
public class  MovieController{


    private static Scanner scanner; //init Scanner
    private static final MovieDatabase DB = new MovieDatabase();
    private static boolean csvLoaded = false;
    private static final String CSV_PATH = "src/util/Movies.csv";

    /** JJ - 2026/09/14 - T10
     * Loads movies from the CSV file located at `CSV_PATH` into MOvie Database
     
     */
    public static void loadMoviesFromCsv(){
        // Load the CSV file once at startup 
        // at least 8 elements.
        try {
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
                    DB.addMovie(movie);
                }
            }
            csvLoaded = true;
        } catch (Exception e) {
           
            System.out.println("Error loading CSV");
        }

    }


    /**
     * turns the string of attributes into a movie object
     * Arraf Hoque T10
     * @param movieEntriesData -an ArrayList of Strings-
     * @return movie object
     */
        private static Movie stringToMovie(ArrayList<String> movieEntriesData){
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
            DB.addMovie(movie);
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
           DB.removeMovie(id);
        } else if (id == 0) { // if no int, pass the title use case
            DB.removeMovie(title);
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
            System.out.println(DB.getMovie(id));
        } else if (id == 0) {
            System.out.println(DB.getMovie(title));// if no int, pass the title use case
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
            DB.updateMovie(id, field, value);
        } else if (id == 0) {
            DB.updateMovie(title, field, value);// if no int, pass the title use case
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
        Map<Integer, Movie> all = DB.getAllMovies();
        ArrayList<String> out = new ArrayList<>();
        for (Map.Entry<Integer, Movie> e : all.entrySet()) {
            out.add(e.getKey() + ": " + e.getValue().toString());
        }
        return out;
    }


    /**
     * prints the top 5 movies ranked
     * Arraf Hoque T10
     */

//    public void handleTop5(){
//        MovieDatabase MD = new MovieDatabase();
//        System.out.println(MD.getTop5());
//    }

    /**
     * gets the highest rated movie in the database
     * Arraf Hoque T10
     */
//    public void handleHighestRating(){
//        MovieDatabase MD = new MovieDatabase();
//        System.out.println(MD.getHighestRated());
//    }

    /**
     * gets lowest rated movie oin the database
     * Arraf Hoque T10
     */
//    public void handleLowestRating(){
//        MovieDatabase MD = new MovieDatabase();
//        System.out.println(MD.getLowestRated());
//    }







}
