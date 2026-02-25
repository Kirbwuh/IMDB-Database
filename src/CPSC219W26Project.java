package src;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

public class CPSC219W26Project {
    // Establish id
    private static int nextId = 1;
    // create movie hashmap
    static HashMap<Integer, String[]> movies = new HashMap<>();

    // Hash map indexes
    private static final int SERIES_TITLE = 0;    // Name of movie (String)
    private static final int RELEASE_YEAR = 1;   // Year movie was released (int)
    private static final int CERTIFICATION = 2;   // Age / Content Rating PG-13 (boolean)
    private static final int GENRE = 3;           // Genre(s) of the movies (String)
    private static final int IMDB_RATING = 4;     // Rating of movie on IMDB (double)
    private static final int OVERVIEW = 5;        // Short description of movie (String)
    private static final int DIRECTOR = 6;      // Name of director (String)
    private static final int GROSS = 7;        // money made by movie (double)

    // Movie array

    public static void main(String[] args) {
        // Test
        addMovie("Forrest Gump", 2014, true, "comedy", 8.8,"...","Yes",111.1);
        System.out.println(movieToString(1, movies.get(1)));
    }


    /**Method to add movie to movie hashmap.
     * Id is automatically generated.
     * @param seriesTitle Title of movie (string)
     * @param releaseYear Release year of movie (int)
     * @param certification PG-13 True or false (boolean)
     * @param genre Genre of movie (String)
     * @param imdbRating Rating of movie 0-10 (double)
     * @param overview Short description of movie (String)
     * @param director Name of Director First and Last (String)
     * @param gross How much money movie made (double)
     * @return the Id(Key) of hashMap entry
     */
    public static int addMovie(String seriesTitle, int releaseYear, boolean certification, String genre, double imdbRating, String overview, String director, double gross) {
        int id = nextId++;
        movies.put(id, new String[] {
                seriesTitle, String.valueOf(releaseYear), String.valueOf(certification), genre, String.valueOf(imdbRating), overview, director, String.valueOf(gross)
        });
        return id;
    }

    public static String movieToString(int id, String[] movies) {
        if (movies == null) return "Movie " + id + " not found.";
        return "Movie {" +
                "\n ID: " + id +
                "\n Series Title: " + movies[SERIES_TITLE] +
                "\n Release Year: " + movies[RELEASE_YEAR] +
                "\n Certification(PG-13): " + movies[CERTIFICATION] +
                "\n Genre: " + movies[GENRE] +
                "\n IMDB Rating: " + movies[IMDB_RATING] +
                "\n Overview: " + movies[OVERVIEW] +
                "\n Director: " + movies[DIRECTOR] +
                "\n Gross: " + movies[GROSS] +
                "\n }";
    }
}

