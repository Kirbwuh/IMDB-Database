package src;
import java.util.ArrayList;

public class CPSC219W26Project {
    public static void main(String[] args) {

        ArrayList<ArrayList<Movie>> movies = new ArrayList();
    }

    // Movie object
    public static class Movie {
        // class structure
        String title;
        int date;
        String description;
        int rating;

        // constructor to build new movie objects
        public Movie(String title, int date, String description, int rating) {
            this.title = title;
            this.date = date;
            this.description = description;
            this.rating = rating;
        }
    }

    public static void addNewMovie(String title, int date, String description, int rating) {

    }
}

