import static org.junit.Assert.*;
import static src.CPSC219W26Project.*;

import java.util.ArrayList;
import java.util.Map;
import org.junit.Test;

public class CPSC219W26ProjectTest {

    @Test
    public void addMovieStoresMovieData() {
        movies.clear();

        addMovie("matrix", 1999, false, "action", 8.7, "...", "wachowski", 463.999);

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

    @Test
    public void getMovieByIdReturnsMatchingMovie() {
        movies.clear();
        addMovie("forrest gump", 1994, true, "drama", 8.8, "...", "zemeckis", 678.2);

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

    @Test
    public void updateMovieByTitleUpdatesSingleField() {
        movies.clear();
        addMovie("matrix", 1999, false, "action", 8.7, "...", "wachowski", 463.999);

        updateMovieByTitle("matrix", IMDB_RATING, "4.5");

        String[] movie = getMovieByTitle("matrix");
        assertNotNull(movie);
        assertEquals("4.5", movie[IMDB_RATING]);
    }

    @Test
    public void updateMovieByIdUpdatesSingleField() {
        movies.clear();
        addMovie("matrix", 1999, false, "action", 8.7, "...", "wachowski", 463.999);

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

    @Test
    public void getInformationReturnsValuesForRequestedIndex() {
        movies.clear();
        addMovie("forrest gump", 1994, true, "drama", 8.8, "...", "zemeckis", 678.2);
        addMovie("matrix", 1999, false, "action", 8.7, "...", "wachowski", 463.999);

        ArrayList<String> genres = getInformation(GENRE);

        assertEquals(2, genres.size());
        assertTrue(genres.contains("drama"));
        assertTrue(genres.contains("action"));
    }

    @Test
    public void removeMovieByIdRemovesEntry() {
        movies.clear();
        addMovie("matrix", 1999, false, "action", 8.7, "...", "wachowski", 463.999);

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
}
