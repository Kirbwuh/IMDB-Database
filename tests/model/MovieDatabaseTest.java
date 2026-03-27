package model;

import org.junit.jupiter.api.Test;
import src.model.Movie;
import src.model.MovieDatabase;

import static org.junit.jupiter.api.Assertions.*;

public class MovieDatabaseTest {

//        public HashMap<Integer, src.model.Movie> getAllMovies() {
//            return getAllEntries();
//        }
//
//        public void updateMovie(int id, int field, String value) {
//
//            public void updateMovie(String title, int field, String value) {
//
//                public void removeMovie(int id) {
//                    removeEntry(id);
//                }
//
//                public void removeMovie(String title) {
//                    removeEntry(title);
//                }

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
}
