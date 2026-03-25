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
        return getEntry(id);
    }

    public Movie getMovie(String title) {
        return getEntry(title);
    }

    public HashMap<Integer, Movie> getAllMovies() {
        return getAllEntries();
    }

    public void updateMovie(int id, int field, String value) {
        if (field < 6) {
            updateEntry(id, field, value);
        } else {
            Movie target = getMovie(id);
            switch(field) {
                case 6:
                target.setDirector(value);
                break;
            case 7:
                target.setGross(Long.parseLong(value));
                break;
            }
        }
    }

    public void updateMovie(String title, int field, String value) {
        if (field < 6) {
            updateEntry(title, field, value);
        } else {
            Movie target = getMovie(title);
            switch(field) {
                case 6:
                    target.setDirector(value);
                    break;
                case 7:
                    target.setGross(Long.parseLong(value));
                    break;
            }
        }
    }

    public void removeMovie(int id) {
        removeEntry(id);
    }

    public void removeMovie(String title) {
        removeEntry(title);
    }
}
