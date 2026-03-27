package model;

import org.junit.jupiter.api.Test;
import src.model.Movie;
import src.model.MovieDatabase;

import static org.junit.jupiter.api.Assertions.*;

public class MovieDatabaseTest {
//    public class MovieDatabase extends src.model.Database<src.model.Movie> {
//
//        public MovieDatabase() {
//            super(src.model.Movie.class);
//        }
//
//        public void addMovie(src.model.Movie movie) {
//            addEntry(movie);
//        }
//
//        public src.model.Movie getMovie(int id) {
//            return getEntry(id);
//        }
//
//        public src.model.Movie getMovie(String title) {
//            return getEntry(title);
//        }
//
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
//\
//                public void removeMovie(String title) {
//                    removeEntry(title);
//                }
/
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
    void test_removeMovie_empty() {
        MovieDatabase database = new MovieDatabase();

        assertDoesNotThrow(() -> {
            database.removeMovie(1);
            database.removeMovie("Missing Title");
        });
        assertTrue(database.getAllMovies().isEmpty());
    }
}
