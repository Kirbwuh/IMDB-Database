package src;
import java.util.ArrayList;

public class CPSC219W26Project {

    // Movie array
    private static final ArrayList<Movie> movies = new ArrayList<>();

    public static void main(String[] args) {

        //tests
        movies.add(new Movie("Forrest Gump",1994,"...", 8.8));
        movies.add(new Movie("Inception",2010,"...",8.8));
        addMovie("Interstellar",2014,"...",8.7);
        removeMovie("Forrest Gump");
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
            return "Movie {" + "\n" + "title: " + title +  "\n" + "date: " + date + "\n" + "description: " + description + "\n" + "rating: " + rating + "\n" + "}";
        }
    }

    /**
     * adds new movie to movies list
     * @param title
     * @param date
     * @param description
     * @param rating
     */
    public static void addMovie(String title, int date, String description, double rating) {
        CPSC219W26Project.movies.add(new Movie(title,date,description,rating));
    }


    public static void removeMovie(String title) {
        movies.removeIf(m -> m.title.equals(title));
    }
}

