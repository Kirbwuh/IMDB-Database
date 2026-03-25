package Model;
import src.model.Movie;

import java.util.HashMap;

/**
 * Stores and manages {@link Movie} entries.
 *
 * <p>This class provides movie-specific wrapper methods around the generic
 * functionality defined in {@link Database}.</p>
 */
public class MovieDatabase extends Database<Movie> {

    /**
     * Creates an empty movie database.
     */
    public MovieDatabase() {
        super(Movie.class);
    }

    /**
     * Adds a movie to the database.
     *
     * @param movie the movie to add
     */
    public void addMovie(Movie movie) {
        addEntry(movie);
    }

    /**
     * Retrieves a movie by its database ID.
     *
     * @param id the ID of the movie to retrieve
     * @return the matching movie, or {@code null} if no movie exists with that ID
     */
    public Movie getMovie(int id) {
        return getEntry(id);
    }

    /**
     * Retrieves the first movie with the given title.
     *
     * @param title the title of the movie to retrieve
     * @return the matching movie, or {@code null} if no movie with that title exists
     */
    public Movie getMovie(String title) {
        return getEntry(title);
    }

    /**
     * Returns all movies currently stored in the database.
     *
     * @return a map of movie IDs to movie objects
     */
    public HashMap<Integer, Movie> getAllMovies() {
        return getAllEntries();
    }

    /**
     * Updates a movie field using the movie's ID.
     *
     * <p>Fields {@code 1} to {@code 5} are handled by the parent class.
     * Field {@code 6} updates the director and field {@code 7} updates the gross.</p>
     *
     * @param id the ID of the movie to update
     * @param field the field number to update
     * @param value the new value for the selected field
     */
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

    /**
     * Updates a movie field using the movie's title.
     *
     * <p>Fields {@code 1} to {@code 5} are handled by the parent class.
     * Field {@code 6} updates the director and field {@code 7} updates the gross.</p>
     *
     * @param title the title of the movie to update
     * @param field the field number to update
     * @param value the new value for the selected field
     */
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

    /**
     * Removes a movie by its database ID.
     *
     * @param id the ID of the movie to remove
     */
    public void removeMovie(int id) {
        removeEntry(id);
    }

    /**
     * Removes the first movie with the given title.
     *
     * @param title the title of the movie to remove
     */
    public void removeMovie(String title) {
        removeEntry(title);
    }
}
