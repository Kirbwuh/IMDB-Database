package src;
import java.util.HashMap;
import java.util.Map;

public class CPSC219W26Project {
    // Establish id
    private static int nextId = 1;
    // create movie hashmap
    static HashMap<Integer, String[]> movies = new HashMap<>();

    // String Array indexes within the HashMap
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
        addMovie("forrest gump", 2014, true, "comedy", 8.8,"...","yes",111.1);
        addMovie("matrix",1999,false,"action",8.7,"...","no",463.999);
        printMovieByTitle("matrix");
        printMovieById(1);
    }


    /**
     * Adds a movie to the map and assigns the next available id.
     *
     * @param seriesTitle movie title (non-null)
     * @param releaseYear year the movie was released (e.g., 1999)
     * @param certification true if PG-13, false otherwise
     * @param genre genre or genres (non-null)
     * @param imdbRating rating on a 0.0–10.0 scale
     * @param overview short description (non-null)
     * @param director director's name (non-null)
     * @param gross gross revenue (same units used throughout your program)
     */
    public static void addMovie(String seriesTitle, int releaseYear, boolean certification, String genre, double imdbRating, String overview, String director, double gross) {
        int id = nextId++;
        movies.put(id, new String[] {
                seriesTitle, String.valueOf(releaseYear), String.valueOf(certification), genre, String.valueOf(imdbRating), overview, director, String.valueOf(gross)
        });
    }

    /**
     * Builds a formatted string for a single movie record.
     *
     * @param id movie id associated with the record
     * @param movie record values stored in the fixed index order
     * @return formatted movie details or a "not found" message if the record is null
     */
    public static String movieToString(int id, String[] movie) {
        if (movie == null) return "Movie " + id + " not found.";
        return "Movie {" +
                "\n ID: " + id +
                "\n Series Title: " + movie[SERIES_TITLE] +
                "\n Release Year: " + movie[RELEASE_YEAR] +
                "\n Certification(PG-13): " + movie[CERTIFICATION] +
                "\n Genre: " + movie[GENRE] +
                "\n IMDB Rating: " + movie[IMDB_RATING] +
                "\n Overview: " + movie[OVERVIEW] +
                "\n Director: " + movie[DIRECTOR] +
                "\n Gross: " + movie[GROSS] +
                "\n }";
    }

    /**
     * Prints every movie currently stored in the map.
     */
    public static void printAllMovies() {
        for (Map.Entry<Integer, String[]> entry: movies.entrySet()) {
            int id = entry.getKey();
            String[] movie = entry.getValue();
            System.out.println(movieToString(id, movie));
        }
    }

    /**
     * Print's a single movie by associated title.
     *
     * @param title movie title associated with entry.
     */
    public static void printMovieByTitle(String title) {
        for (Map.Entry<Integer, String[]> entry: movies.entrySet()) {
            int key = entry.getKey();
            String[] movie = entry.getValue();
            if (movie[SERIES_TITLE].equals(title)) {
                System.out.println(movieToString(key,movie));
            }
        }
    }

    /**
     * Print's a single movie by associated ID
     *
     * @param id movie id associated with entry.
     */
    public static void printMovieById(int id) {
        for (Map.Entry<Integer, String[]> entry: movies.entrySet()) {
            int key = entry.getKey();
            String[] movie = entry.getValue();
            if (key == id) {
                System.out.println(movieToString(key,movie));
            }
        }
    }

    /**
     * Deletes a movie entry by its title.
     *
     * @param title movie title associated with entry.
     */
    public static void removeMovieByTitle(String title) {
        for (Map.Entry<Integer, String[]> entry : movies.entrySet()) {
            int id = entry.getKey();
            String[] movie = entry.getValue();
            if (movie[SERIES_TITLE].equals(title)) {
                movies.remove(id);
            }
        }
    }

    /**
     * Deletes a movie entry by its ID.
     *
     * @param id movie id associated with entry.
     */
    public static void removeMovieById(int id) {
        for (Map.Entry<Integer, String[]> entry: movies.entrySet()) {
            int key = entry.getKey();
            if (key == id) {
                movies.remove(id);
            }
        }
    }
}

