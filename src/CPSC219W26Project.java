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
//        removeMovie("Forrest Gump");
//        System.out.println(getMovie("Interstellar"));
        System.out.print(movies.toString());
    }

    // Movie object
    public static class Movie {
        // class structure
        static int nextId = 1;  // ID updater
        int id;
        String title;
        int date;
        String description;
        double rating;

        // constructor to build new movie objects
        public Movie(String title, int date, String description, double rating) {
            this.id = nextId++;
            this.title = title;
            this.date = date;
            this.description = description;
            this.rating = rating;
        }

        // toString method to print out movie arraylist and not memory
        @Override
        public String toString() {
            return "Movie {" + "\n" + "ID: " + id  + "\n" + "title: " + title +  "\n" + "date: " + date + "\n" + "description: " + description + "\n" + "rating: " + rating + "\n" + "}";
        }
    }

    /**
     * adds new movie to movies list
     * @param title title of movie
     * @param date release date
     * @param description brief description
     * @param rating IMDB rating
     */
    public static void addMovie(String title, int date, String description, double rating) {
        CPSC219W26Project.movies.add(new Movie(title,date,description,rating));
    }

    /**
     * remove movie by title from movies list
     * @param title title of movie
     */
    public static void removeMovieByTitle(String title) {
        movies.removeIf(m -> m.title.equals(title));
    }

    /**
     * remove movie by ID from movies list
     * @param id auto assigned movie ID
     */
    public static void removeMovieById(int id) {
        movies.removeIf(m -> m.id == id);
    }

    /**
     * get movie by title
     * @param title title of movie
     * @return movie object or print error message
     */
    public static Movie getMovieByTitle(String title) {
        for (Movie m: movies) {
            if (m.title.equals(title)) {
                return m;
            } else {
                System.out.println("Movie does not exist");
            }
        }
        return null;
    }

    /**
     * get movie by ID
      * @param id auto-assigned id.
     * @return movie object or print error message
     */
   public static Movie getMovieById(int id) {
        for (Movie m: movies) {
            if (m.id == id) {
                return m;
            } else {
                System.out.println("Movie does not exist");
            }
        }
        return null;
   }
}

