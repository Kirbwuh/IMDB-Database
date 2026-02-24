package src;
import java.util.ArrayList;

public class CPSC219W26Project {
    public static void main(String[] args) {

        ArrayList<Movie> movies = new ArrayList();
        movies.add(new Movie("Forrest Gump",1994,"The history of the United States from the 1950s to the '70s unfolds from the perspective of an Alabama man with an IQ of 75, who yearns to be reunited with his childhood sweetheart.", 8.8));

        System.out.print(movies.toString());
    }

    // Movie object
    public static class Movie {
        // class structure
        String title;
        int date;
        String description;
        double rating;

        // constructor to build new movie objects
        public Movie(String title, int date, String description, double rating) {
            this.title = title;
            this.date = date;
            this.description = description;
            this.rating = rating;
        }

        // toString method to print out movie arraylist and not memory
        @Override
        public String toString() {
            return "Movie{" + "title: " + title + ", date: " + date + ", description: " + description + ", rating: " + rating + "}";
        }
    }

//    public static void addNewMovie(String title, int date, String description, int rating) {
//
//    }
}

