package tests;

import src.model.MovieDatabase;
import src.model.Movie;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MovieDatabaseTest {


    MovieDatabase database = new MovieDatabase();

    @Test
    public void addMovie_addsMovieSuccessfully() {
        Movie movie = new Movie("The Matrix", 1999, true, "Action",
                8.7, "A hacker discovers reality is a simulation.", "Wachowski", 463517383L);

        database.addMovie(movie);

        Movie storedMovie = database.getMovie(1);
        assertNotNull(storedMovie);
        assertEquals("The Matrix", storedMovie.getTitle());
    }

    @Test
    public void addMovie_failCase_wrongTitleShouldNotMatch() {
        Movie movie = new Movie("Inception", 2010, true, "Sci-Fi",
                8.8, "A thief steals information through dreams.", "Christopher Nolan", 836836967L);

        database.addMovie(movie);

        Movie storedMovie = database.getMovie(1);
        assertNotNull(storedMovie);
        assertFalse("Interstellar".equals(storedMovie.getTitle()));
    }

    @Test
    public void addMovie_allowsNullMovieValue() {

        database.addMovie(null);

        assertEquals(1, database.getAllMovies().size());
        assertNull(database.getMovie(1));
    }
}
