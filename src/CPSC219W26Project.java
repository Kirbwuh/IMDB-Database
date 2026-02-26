package src;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class CPSC219W26Project {
    // Establish id
    private static int nextId = 1;
    // create movie hashmap
    public static HashMap<Integer, String[]> movies = new HashMap<>();

    // String Array indexes within the HashMap
    public static final int SERIES_TITLE = 0;    // Name of movie (String)
    public static final int RELEASE_YEAR = 1;   // Year movie was released (int)
    public static final int CERTIFICATION = 2;   // Age / Content Rating PG-13 (boolean)
    public static final int GENRE = 3;           // Genre(s) of the movies (String)
    public static final int IMDB_RATING = 4;     // Rating of movie on IMDB (double)
    public static final int OVERVIEW = 5;        // Short description of movie (String)
    public static final int DIRECTOR = 6;      // Name of director (String)
    public static final int GROSS = 7;        // money made by movie (double)

    public static void main(String[] args) {
        // Test
        addMovie("forrest gump", 2014, true, "comedy", 8.8,"...","yes",111.1);
        addMovie("matrix",1999,false,"action",8.7,"...","no",463.999);
        updateMovieByTitle("matrix",IMDB_RATING,"4.5");
        removeMovieById(2);
        printAllMovies();
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
     * Returns movie object by associated title
     *
     * @param title movie title associated with entry.
     * @return movie object with associated title.
     */
    public static String[] getMovieByTitle(String title) {
        for (Map.Entry<Integer, String[]> entry: movies.entrySet()) {
            int key = entry.getKey();
            String[] movie = entry.getValue();
            if (movie[SERIES_TITLE].equals(title)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Returns movie object by associated ID
     *
     * @param id movie ID in HashMap
     * @return movie object
     */
    public static String[] getMovieById(int id) {
        for (Map.Entry<Integer, String[]> entry: movies.entrySet()) {
            int key = entry.getKey();
            String[] movie = entry.getValue();
            if (key == id) {
                return movie;
            }
        }
        return null;
    }

    /**
     *Returns an ArrayList of specified values present in the HashMap. For example using GENRE will return all the genres.
     *
     * @param index Use the constants to index the information you want returned
     * @return ArrayList of specified values.
     */
    public static ArrayList<String> getInformation(int index) {
        ArrayList<String> values = new ArrayList<String>();
        for (Map.Entry<Integer, String[]> entry : movies.entrySet()) {
            int key = entry.getKey();
            String[] movie = entry.getValue();
            values.add(movie[index]);
        }
        return values;
    }

    /**
     *Updates specified movie data by ID matching.
     *
     * @param id id (key) of movie
     * @param index The index of the data you want to update based on constants.
     * @param update The update/change you want to make
     */
    public static void updateMovieById(int id,int index,String update) {
        String[] movie = movies.get(id);
        if (movie != null) {
            movie[index] = update;
        }
    }

    /**
     *Updates specified movie data by title matching.
     *
     * @param title title of movie
     * @param index The index of the data you want to update based on constants.
     * @param update the update/change you want to make.
     */
    public static void updateMovieByTitle(String title, int index, String update) {
        for (Map.Entry<Integer, String[]> entry: movies.entrySet()) {
            String[] movie = entry.getValue();
            if (movie[SERIES_TITLE].equals(title)) {
                movie[index] = update;
            }
        }
    }

    /**
     * Prints every movie currently stored in the map.
     */
    public static void printAllMovies() {
        for (Map.Entry<Integer, String[]> entry: movies.entrySet()) {
            String[] movie = entry.getValue();
            System.out.println(movieToString(movie));
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

    /**
     * Builds a formatted string for a single movie record.
     *
     * @param movie record values stored in the fixed index order
     * @return formatted movie details
     */
    public static String movieToString(String[] movie) {
        return "Movie {" +
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
     * Print's a single movie by associated title.
     *
     * @param title movie title associated with entry.
     */
    public static void printMovieByTitle(String title) {
        System.out.println(movieToString(getMovieByTitle(title)));
    }

    /**
     * Print's a single movie by associated ID
     *
     * @param id movie id associated with entry.
     */
    public static void printMovieById(int id) {
        System.out.println(movieToString(getMovieById(id)));
    }
}

