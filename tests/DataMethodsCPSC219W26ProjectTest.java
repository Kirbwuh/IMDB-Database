package tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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


    //"forrest gump", 1994, true, "drama", 8.8, "...", "zemeckis", 678.2
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

    @Test
    public void getMovieByIdReturnsMatchingMovie() {
        movies.clear();
        addMovie(testEntry);

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
        addMovie(testEntry);

        updateMovieByTitle("matrix", IMDB_RATING, "4.5");

        String[] movie = getMovieByTitle("matrix");
        assertNotNull(movie);
        assertEquals("4.5", movie[IMDB_RATING]);
    }

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
}
