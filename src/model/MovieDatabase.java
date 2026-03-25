package Model;
import src.model.Movie;

import java.util.HashMap;

public class MovieDatabase extends Database<Movie> {

    public MovieDatabase() {
        super(Movie.class);
    }

    public void addMovie(Movie movie) {
        addEntry(movie);
    }

    public Movie getMovie(int id) {
        getEntry(id);
    }

    public Movie getMovie(String title) {
        getEntry(title);
    }

    public HashMap<Integer, Movie> getAllMovies() {
        getAllEntries();
    }

    public void updateMovie(int id, int field, String value) {
        updateEntry(id, field, value);
    }

    public void updateMovie(String title, int field, String value) {
        updateEntry(title, field, value);
    }

    public void removeMovie(int id) {
        removeEntry(id);
    }

    public void removeMovie(String title) {
        removeEntry(title);
    }
}
