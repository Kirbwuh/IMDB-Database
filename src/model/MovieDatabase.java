package Model;
import java.util.HashMap;
import java.util.Map;
import model.Movie;

// Replace the static HashMap with a proper OO class. Store List. Manage auto-increment ID.
//
//Create MovieDatabase class with private ArrayList and nextId counter
//
//addMovie(Movie m) assigns and stores ID
//
//removeById(int id)
//
//updateById(int id, int field, String val) — keep field index constants
//
//getById(int id) returns Movie or null
//
//getByTitle(String t) returns Movie or null
//
//getAllMovies() returns unmodifiable list
//
//Javadoc on all public methods

public class MovieDatabase {

    private HashMap<Integer, Movie> movieDatabase;
    private static int nextId = 1;

    public MovieDatabase() {
    }

    public void addMovie(Movie movie) {
        movieDatabase.put(nextId, movie);
        nextId ++;
    }

    public void removeMovie(int id) {
        movieDatabase.remove(id);
    }

    public void removeMovie(String title) {
        for (Map.Entry<Integer, Movie> entry : movieDatabase.entrySet()) {
            int id = entry.getKey();
            Movie movie = entry.getValue();
            if (movie.getTitle().equals(title)) {
                movieDatabase.remove(id);
            }
        }
    }
}