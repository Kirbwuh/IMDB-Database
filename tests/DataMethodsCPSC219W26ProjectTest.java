import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static src.CPSC219W26Project.*;

public class DataMethodsCPSC219W26ProjectTest {

    HashMap<Integer, String> testEntry = new HashMap<Integer, String>(
            Map.of(
                    SERIES_TITLE, "matrix",
                    RELEASE_YEAR, "1999",
                    CERTIFICATION,"false",
                    GENRE,"action",
                    IMDB_RATING,"8.7",
                    OVERVIEW,"...",
                    DIRECTOR,"wachowski",
                    GROSS,"463.999"
            )
    );

    HashMap<Integer, String> testEntry2 = new HashMap<Integer, String>(
            Map.of(
                    SERIES_TITLE, "forrest gump",
                    RELEASE_YEAR, "1994",
                    CERTIFICATION,"true",
                    GENRE,"drama",
                    IMDB_RATING,"8.8",
                    OVERVIEW,"...",
                    DIRECTOR,"zemeckis",
                    GROSS,"678.2"
            )
    );
    HashMap<Integer, String> testEntry3 = new HashMap<Integer, String>(
            Map.of(
                    SERIES_TITLE, "star wars",
                    RELEASE_YEAR, "1977",
                    CERTIFICATION,"true",
                    GENRE,"sci-fi",
                    IMDB_RATING,"9.8",
                    OVERVIEW,"...",
                    DIRECTOR,"george lucas",
                    GROSS,"6678.2"
            )
    );




    /**
     * CL-3/6/2026-T10
     * Verifies that adding a movie persists all provided field values,
     * and that the movie can be retrieved by title afterward.
     */
    @Test
    public void addMovieStoresMovieData() {
        movies.clear();

        addMovie(testEntry);

        String[] movie = getMovieByTitle("matrix");
        assertNotNull(movie);
        assertEquals("matrix", movie[SERIES_TITLE]);
        assertEquals("1999", movie[RELEASE_YEAR]);
        assertEquals("false", movie[CERTIFICATION]);
        assertEquals("action", movie[GENRE]);
        assertEquals("8.7", movie[IMDB_RATING]);
        assertEquals("...", movie[OVERVIEW]);
        assertEquals("wachowski", movie[DIRECTOR]);
        assertEquals("463.999", movie[GROSS]);
    }

    /**
     * CL-3/6/2026-T10
     * Verifies that a movie can be retrieved by its generated ID.
     */
    @Test
    public void getMovieByIdReturnsMatchingMovie() {
        movies.clear();
        addMovie(testEntry2);

        int movieId = -1;
        for (Map.Entry<Integer, String[]> entry : movies.entrySet()) {
            if (entry.getValue()[SERIES_TITLE].equals("forrest gump")) {
                movieId = entry.getKey();
            }
        }

        assertTrue(movieId != -1);
        String[] movie = getMovieById(movieId);
        assertNotNull(movie);
        assertEquals("forrest gump", movie[SERIES_TITLE]);
    }

    /**
     * CL-3/6/2026-T10
     * Verifies that updating by title changes only the targeted field value.
     */
    @Test
    public void updateMovieByTitleUpdatesSingleField() {
        movies.clear();
        addMovie(testEntry);

        updateMovieByTitle("matrix", IMDB_RATING, "4.5");

        String[] movie = getMovieByTitle("matrix");
        assertNotNull(movie);
        assertEquals("4.5", movie[IMDB_RATING]);
    }

    /**
     * CL-3/6/2026-T10
     * Verifies that updating by ID changes the requested field for that movie.
     */
    @Test
    public void updateMovieByIdUpdatesSingleField() {
        movies.clear();
        addMovie(testEntry);

        int movieId = -1;
        for (Map.Entry<Integer, String[]> entry : movies.entrySet()) {
            if (entry.getValue()[SERIES_TITLE].equals("matrix")) {
                movieId = entry.getKey();
            }
        }

        assertTrue(movieId != -1);
        updateMovieById(movieId, GENRE, "sci-fi");
        assertEquals("sci-fi", getMovieById(movieId)[GENRE]);
    }

    /**
     * CL-3/6/2026-T10
     * Verifies that requesting a field index returns values for all stored movies.
     */
    @Test
    public void getInformationReturnsValuesForRequestedIndex() {
        movies.clear();
        addMovie(testEntry);
        addMovie(testEntry2);

        ArrayList<String> genres = getInformation(GENRE);

        assertEquals(2, genres.size());
        assertTrue(genres.contains("drama"));
        assertTrue(genres.contains("action"));
    }

    /**
     * CL-3/6/2026-T10
     * Verifies that removing a movie by ID deletes it from storage
     * so it is no longer retrievable by that ID.
     */
    @Test
    public void removeMovieByIdRemovesEntry() {
        movies.clear();
        addMovie(testEntry);

        int movieId = -1;
        for (Map.Entry<Integer, String[]> entry : movies.entrySet()) {
            if (entry.getValue()[SERIES_TITLE].equals("matrix")) {
                movieId = entry.getKey();
            }
        }

        assertTrue(movieId != -1);
        removeMovieById(movieId);
        assertNull(getMovieById(movieId));
    }
    @Test
    public void testTop5Size(){
        movies.clear();
        addMovie(testEntry);
        addMovie(testEntry2);
        ArrayList<String[]>  result = getTop5();
        int expectedMaxSize = 5;
        assertTrue(result.size() <= expectedMaxSize, "The list size should be less than or equal to " + expectedMaxSize);
    }
    @Test
    public void testTop5CorrectOrder(){
        movies.clear();
        addMovie(testEntry);
        addMovie(testEntry2);
        ArrayList<String[]>  result = getTop5();
        double first = Double.parseDouble(result.get(0)[IMDB_RATING]);
        double second = Double.parseDouble(result.get(1)[IMDB_RATING]);
        assertTrue(first >= second, "First score should be higher than or equal to the second score " + first);
    }
    @Test
    public void testEmptyDatabase(){
        movies.clear();
        ArrayList<String[]>  result = getTop5();
        int expectedSize = 0;
        assertTrue(expectedSize == result.size(), "The list size should be less than or equal to " + expectedSize);
    }
    @Test
    public void testHighestValue(){
        movies.clear();
        addMovie(testEntry);
        addMovie(testEntry2);
        addMovie(testEntry3);
        ArrayList<String[]>  result = getTop5();
        double first = Double.parseDouble(result.get(0)[IMDB_RATING]);
        assertEquals(9.8 , first, "The first IMDB Rating value should be " + 9.8);
    }

}
