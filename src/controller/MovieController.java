package src.controller;
import src.model.MovieDatabase;
import src.view.InputView;
import src.model.Movie;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Main Controller Method
 * Arraf Hoque T10
 */
public class  MovieController{


    private static Scanner scanner; //init Scanner


    /**
     * turns the string of attributes into a movie object
     * Arraf Hoque T10
     * @param data -an ArrayList of Strings-
     * @return movie object
     */
    private static Object stringToMovie(ArrayList<String> data){
            Movie movie = new Movie( // create new movie object
                    data.get(0),                         // title
                    Integer.parseInt(data.get(1)),       // year
                    Boolean.parseBoolean(data.get(2)),   // certification
                    data.get(3),                         // genre
                    Double.parseDouble(data.get(4)),     // IMDB RATING
                    data.get(5),                         // description
                    data.get(6),                         // director
                    Long.parseLong(data.get(7)));        // gross profit
            return movie;
    }

    /**
     * Performs MovieDatabase addMovie
     * Arraf Hoque T10
     * @param movie
     */
    private void handleAddMovie(Movie movie){
        MovieDatabase MD = new MovieDatabase();
        MD.addMovie(movie); // call addMovie in MD
    }

    /**
     *  Performs MovieDatabase removeMovie
     *  Prints if there is an error with input
     *  Arraf Hoque T10
     * @param id
     * @param title
     */
    private void handleRemoveMovie(int id, String title){
        MovieDatabase MD = new MovieDatabase();
        if (title == null){ //if there is no title, use the movie ID
           MD.removeMovie(id);
        } else if (id == 0) { // if no int, pass the title use case
            MD.removeMovie(title);
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
        MovieDatabase MD = new MovieDatabase();
        if (title == null){ //if there is no title, use the movie ID
            System.out.println(MD.getMovie(id));
        } else if (id == 0) {
            System.out.println(MD.getMovie(title));// if no int, pass the title use case
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
        MovieDatabase MD = new MovieDatabase();
        if (title == null){ //if there is no title, use the movie ID
            MD.updateMovie(id, field, value);
        } else if (id == 0) {
            MD.updateMovie(title, field, value);// if no int, pass the title use case
        }
        else{
            System.out.println("Please enter a valid movie ID or title.");
        }
    }

    /**
     * Prints all movies in the database
     * Arraf Hoque T10
     */
    private void handlePrintAllMovie(){
        MovieDatabase MD = new MovieDatabase();
        System.out.println(MD.getAllMovies());
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
