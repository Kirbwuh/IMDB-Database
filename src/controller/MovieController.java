package src.controller;
import Model.MovieDatabase;
import src.view.InputView;
import model.Movie;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class  MovieController{

    private static final Model.MovieDatabase MD = new MovieDatabase();
    private static Scanner scanner;




    private static Object stringToMovie(ArrayList<String> data){
            Movie movie = new Movie(
                    data.get(0),                         // title
                    Integer.parseInt(data.get(1)),       // year
                    Boolean.parseBoolean(data.get(2)),                         // genre
                    data.get(3),     // rating
                    Double.parseDouble(data.get(4)),                         // description
                    data.get(5),                         // director
                    data.get(6),
                    Long.parseLong(data.get(7)));

            return movie;
    }

    // calling MovieDatabase
    private void handleAddMovie(Movie movie){
        MovieDatabase MD = new MovieDatabase();
        MD.addMovie(movie);
    }
    private void handleRemoveMovie(int id, String title){
        MovieDatabase MD = new MovieDatabase();
        if (title == null){
           MD.removeMovie(id);
        } else if (id == 0) {
            MD.removeMovie(title);
        }
        else{
            System.out.println("Please enter a valid movie ID or title.");
        }
    }
    private void handleGetMovie(int id, String title){
        MovieDatabase MD = new MovieDatabase();
        if (title == null){
            System.out.println(MD.getMovie(id));
        } else if (id == 0) {
            System.out.println(MD.getMovie(title));
        }
        else{
            System.out.println("Please enter a valid movie ID or title.");
        }
    }
    private void handlePrintAllMovie(){
        MovieDatabase MD = new MovieDatabase();
        System.out.println(MD.getAllMovies());
    }
    private



}
