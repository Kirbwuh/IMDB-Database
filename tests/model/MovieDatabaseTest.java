package model;

import org.junit.jupiter.api.Test;
import src.model.Movie;
import src.model.MovieDatabase;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CPSC 219 W26 Project
 * <p>
 * MovieDatabaseTest
 * <p>
 * Test class for all movieDatabase methods
 *
 * @author Christopher Lassota
 * @email chris.lassota1@ucalgary.ca
 */
public class MovieDatabaseTest {

    @Test
    void test_MovieDatabaseConstructor() {
        MovieDatabase database = new MovieDatabase();

        assertAll("new movie database should start empty",
                () -> assertTrue(database.getAllMovies().isEmpty()),
                () -> assertNull(database.getMovie(1)),
                () -> assertNull(database.getMovie("Any Title"))
        );
    }
    @Test
    void test_addMovie_success() {
        MovieDatabase database = new MovieDatabase();
        Movie movie = new Movie("Inception", 2010, false, "Action", 8.8, "Dream heist", "Nolan", 836);

        database.addMovie(movie);

        assertAll("movie should be added and retrievable",
                () -> assertEquals(1, database.getAllMovies().size()),
                () -> assertSame(movie, database.getMovie(1)),
                () -> assertSame(movie, database.getMovie("Inception"))
        );
    }

    @Test
    void test_addMovie_fail() {
        MovieDatabase database = new MovieDatabase();
        Movie movie = new Movie("Tenet", 2020, false, "Action", 7.3, "Time inversion", "Nolan", 363);

        database.addMovie(movie);

        assertAll("non-existent movies should not be returned",
                () -> assertNull(database.getMovie(2)),
                () -> assertNull(database.getMovie("Interstellar"))
        );
    }

    @Test
    void test_addMovie_nullMovie() {
        MovieDatabase database = new MovieDatabase();

        database.addMovie(null);

        assertAll("adding null should still create a slot in the current implementation",
                () -> assertEquals(1, database.getAllMovies().size()),
                () -> assertNull(database.getMovie(1))
        );
    }

    @Test
    void test_getMovie_success() {
        MovieDatabase database = new MovieDatabase();
        Movie movie = new Movie("Inception", 2010, false, "Action", 8.8, "Dream heist", "Nolan", 836);

        database.addMovie(movie);

        assertAll("existing movie should be retrievable",
                () -> assertSame(movie, database.getMovie(1)),
                () -> assertSame(movie, database.getMovie("Inception"))
        );
    }

    @Test
    void test_getMovie_fail() {
        MovieDatabase database = new MovieDatabase();
        Movie movie = new Movie("Tenet", 2020, false, "Action", 7.3, "Time inversion", "Nolan", 363);

        database.addMovie(movie);

        assertAll("non-existent getMovie lookups should return null",
                () -> assertNull(database.getMovie(2)),
                () -> assertNull(database.getMovie("Interstellar"))
        );
    }

    @Test
    void test_getMovie_invalidId() {
        MovieDatabase database = new MovieDatabase();
        Movie movie = new Movie("Tenet", 2020, false, "Action", 7.3, "Time inversion", "Nolan", 363);

        database.addMovie(movie);

        assertAll("invalid ids should return null",
                () -> assertNull(database.getMovie(0)),
                () -> assertNull(database.getMovie(-1)),
                () -> assertNull(database.getMovie(99))
        );
    }

    @Test
    void test_getMovie_title() {
        MovieDatabase database = new MovieDatabase();
        Movie movie = new Movie("Inception", 2010, false, "Action", 8.8, "Dream heist", "Nolan", 836);

        database.addMovie(movie);

        assertAll("title lookups are exact-match in the current implementation",
                () -> assertNull(database.getMovie(null)),
                () -> assertNull(database.getMovie("")),
                () -> assertNull(database.getMovie(" inception ")),
                () -> assertNull(database.getMovie("inception"))
        );
    }

    @Test
    void test_getAllMovies_success() {
        MovieDatabase database = new MovieDatabase();
        Movie firstMovie = new Movie("Inception", 2010, false, "Action", 8.8, "Dream heist", "Nolan", 836);
        Movie secondMovie = new Movie("Tenet", 2020, false, "Action", 7.3, "Time inversion", "Nolan", 363);

        database.addMovie(firstMovie);
        database.addMovie(secondMovie);

        assertAll("getAllMovies should return every stored movie",
                () -> assertEquals(2, database.getAllMovies().size()),
                () -> assertSame(firstMovie, database.getAllMovies().get(1)),
                () -> assertSame(secondMovie, database.getAllMovies().get(2))
        );
    }

    @Test
    void test_getAllMovies_fail() {
        MovieDatabase database = new MovieDatabase();
        Movie movie = new Movie("Inception", 2010, false, "Action", 8.8, "Dream heist", "Nolan", 836);

        database.addMovie(movie);

        assertAll("getAllMovies should not contain missing entries",
                () -> assertNotEquals(2, database.getAllMovies().size()),
                () -> assertNull(database.getAllMovies().get(2))
        );
    }

    @Test
    void test_getAllMovies_emptyDatabase() {
        MovieDatabase database = new MovieDatabase();

        assertAll("getAllMovies should be empty for a new database",
                () -> assertTrue(database.getAllMovies().isEmpty()),
                () -> assertEquals(0, database.getAllMovies().size())
        );
    }

    @Test
    void test_updateMovieById_success() {
        MovieDatabase database = new MovieDatabase();
        Movie movie = new Movie("Inception", 2010, false, "Action", 8.8, "Dream heist", "Nolan", 836);

        database.addMovie(movie);
        database.updateMovie(1, 1, "Interstellar");
        database.updateMovie(1, 6, "Christopher Nolan");
        database.updateMovie(1, 7, "1000");

        assertAll("updateMovie by id should update stored movie fields",
                () -> assertEquals("Interstellar", database.getMovie(1).getTitle()),
                () -> assertEquals("Christopher Nolan", database.getMovie(1).getDirector()),
                () -> assertEquals(1000, database.getMovie(1).getGross())
        );
    }

    @Test
    void test_updateMovieByTitle_success() {
        MovieDatabase database = new MovieDatabase();
        Movie movie = new Movie("Tenet", 2020, false, "Action", 7.3, "Time inversion", "Nolan", 363);

        database.addMovie(movie);
        database.updateMovie("Tenet", 2, "2021");
        database.updateMovie("Tenet", 6, "Christopher Nolan");
        database.updateMovie("Tenet", 7, "500");

        assertAll("updateMovie by title should update stored movie fields",
                () -> assertEquals(2021, database.getMovie("Tenet").getYear()),
                () -> assertEquals("Christopher Nolan", database.getMovie("Tenet").getDirector()),
                () -> assertEquals(500, database.getMovie("Tenet").getGross())
        );
    }

    @Test
    void test_updateMovie_fail() {
        MovieDatabase database = new MovieDatabase();
        Movie movie = new Movie("Inception", 2010, false, "Action", 8.8, "Dream heist", "Nolan", 836);

        database.addMovie(movie);

        assertAll("updateMovie should not affect missing targets",
                () -> assertThrows(NullPointerException.class, () -> database.updateMovie(2, 1, "Interstellar")),
                () -> assertThrows(NullPointerException.class, () -> database.updateMovie("Missing Title", 6, "Someone Else"))
        );
    }

    @Test
    void test_updateMovie_invalidFieldDoesNothing() {
        MovieDatabase database = new MovieDatabase();
        Movie movie = new Movie("Inception", 2010, false, "Action", 8.8, "Dream heist", "Nolan", 836);

        database.addMovie(movie);
        database.updateMovie(1, 99, "Ignored");

        assertAll("unsupported field numbers should leave the movie unchanged",
                () -> assertEquals("Inception", database.getMovie(1).getTitle()),
                () -> assertEquals("Nolan", database.getMovie(1).getDirector()),
                () -> assertEquals(836, database.getMovie(1).getGross())
        );
    }

    @Test
    void test_removeMovieById_success() {
        MovieDatabase database = new MovieDatabase();
        Movie movie = new Movie("Inception", 2010, false, "Action", 8.8, "Dream heist", "Nolan", 836);

        database.addMovie(movie);
        database.removeMovie(1);

        assertAll("removeMovie by id should remove the stored movie",
                () -> assertTrue(database.getAllMovies().isEmpty()),
                () -> assertNull(database.getMovie(1)),
                () -> assertNull(database.getMovie("Inception"))
        );
    }

    @Test
    void test_removeMovieByTitle_success() {
        MovieDatabase database = new MovieDatabase();
        Movie movie = new Movie("Tenet", 2020, false, "Action", 7.3, "Time inversion", "Nolan", 363);

        database.addMovie(movie);
        database.removeMovie("Tenet");

        assertAll("removeMovie by title should remove the stored movie",
                () -> assertTrue(database.getAllMovies().isEmpty()),
                () -> assertNull(database.getMovie(1)),
                () -> assertNull(database.getMovie("Tenet"))
        );
    }

    @Test
    void test_removeMovie_fail() {
        MovieDatabase database = new MovieDatabase();
        Movie movie = new Movie("Inception", 2010, false, "Action", 8.8, "Dream heist", "Nolan", 836);

        database.addMovie(movie);

        assertDoesNotThrow(() -> {
            database.removeMovie(2);
            database.removeMovie("Missing Title");
        });
        assertAll("removing missing movies should leave existing entries untouched",
                () -> assertEquals(1, database.getAllMovies().size()),
                () -> assertSame(movie, database.getMovie(1)),
                () -> assertSame(movie, database.getMovie("Inception"))
        );
    }

    @Test
    void test_removeMovie_emptyDatabase() {
        MovieDatabase database = new MovieDatabase();

        assertDoesNotThrow(() -> {
            database.removeMovie(1);
            database.removeMovie("Missing Title");
        });
        assertTrue(database.getAllMovies().isEmpty());
    }

    @Test
    void test_removeMovie_sameMovieTwice() {
        MovieDatabase database = new MovieDatabase();
        Movie movie = new Movie("Inception", 2010, false, "Action", 8.8, "Dream heist", "Nolan", 836);

        database.addMovie(movie);
        database.removeMovie(1);

        assertDoesNotThrow(() -> database.removeMovie(1));
        assertAll("removing the same movie twice should keep the database empty",
                () -> assertTrue(database.getAllMovies().isEmpty()),
                () -> assertNull(database.getMovie(1))
        );
    }
}
