package src.model;

import java.util.HashMap;
import java.util.Map;


public class MovieDatabase {

    private HashMap<Integer, Movie> movieDatabase;
    private int nextId = 1;

    /**
     * Creates an empty movie database and initializes its internal storage.
     */
    public MovieDatabase() {
        movieDatabase = new HashMap<>();
    }

    /**
     * Adds a movie to the database using the next available integer ID.
     *
     * @param movie the movie to add
     */
    public void addMovie(Movie movie) {
        movieDatabase.put(nextId, movie);
        nextId ++;
    }

    /**
     * Removes the movie associated with the given ID.
     *
     * @param id the ID of the movie to remove
     */
    public void removeMovie(int id) {
        movieDatabase.remove(id);
    }

    /**
     * Retrieves the movie associated with the given ID.
     *
     * @param id the ID of the movie to retrieve
     * @return the matching movie, or {@code null} if no movie exists with that ID
     */
    public Movie getMovie(int id) {
        return movieDatabase.get(id);
    }

    /**
     * Retrieves the first movie whose title matches the given title.
     *
     * @param title the title of the movie to retrieve
     * @return the matching movie, or {@code null} if no movie with that title is found
     */
    public Movie getMovie(String title) {
        for (Map.Entry<Integer, Movie> entry: movieDatabase.entrySet()) {
            Movie movie = entry.getValue();
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Returns all movies currently stored in the database.
     *
     * @return a map of movie IDs to movie objects
     */
    public HashMap<Integer, Movie> getAllMovies() {
        return movieDatabase;
    }

    /**
     * Updates a field of the movie associated with the given ID.
     *
     * @param id the ID of the movie to update
     * @param field the field number to update
     * @param value the new value for the selected field
     */
    public void updateMovie(int id, int field, String value) {
        Movie target = getMovie(id);
        switch(field) {
            case 1:
                target.setTitle(value);
                break;
            case 2:
                target.setYear(Integer.parseInt(value));
                break;
            case 3:
                target.setGenre(value);
                break;
            case 4:
                target.setImdbRating(Double.parseDouble(value));
                break;
            case 5:
                target.setDescription(value);
                break;
            case 6:
                target.setDirector(value);
                break;
            case 7:
                target.setGross(Long.parseLong(value));
                break;
        }
    }

    /**
     * Updates a field of the first movie whose title matches the given title.
     *
     * @param title the title of the movie to update
     * @param field the field number to update
     * @param value the new value for the selected field
     */
    public void updateMovie(String title, int field, String value) {
        Movie target = getMovie(title);
        switch(field) {
            case 1:
                target.setTitle(value);
                break;
            case 2:
                target.setYear(Integer.parseInt(value));
                break;
            case 3:
                target.setGenre(value);
                break;
            case 4:
                target.setImdbRating(Double.parseDouble(value));
                break;
            case 5:
                target.setDescription(value);
                break;
            case 6:
                target.setDirector(value);
                break;
            case 7:
                target.setGross(Long.parseLong(value));
                break;
        }
    }

    /**
     * Removes the first movie whose title matches the given title.
     *
     * @param title the title of the movie to remove
     */
    public void removeMovie(String title) {
        for (Map.Entry<Integer, Movie> entry : movieDatabase.entrySet()) {
            int id = entry.getKey();
            Movie movie = entry.getValue();
            if (movie.getTitle().equals(title)) {
                movieDatabase.remove(id);
            }
        }
    }

    // *********************************************************
    // Comparator Constants
    // *********************************************************

    public static final java.util.Comparator<Movie> BY_RATING =
            java.util.Comparator.comparingDouble(Movie::getImdbRating);

    public static final java.util.Comparator<Movie> BY_GROSS =
            java.util.Comparator.comparingDouble(Movie::getGross);

    public static final java.util.Comparator<Movie> BY_YEAR =
            java.util.Comparator.comparingInt(Movie::getYear);

    // *********************************************************
    // Calculation Methods
    // *********************************************************

    /**
     * By: Duku - 2026/03/23 - T10
     * Calculates the average IMDb rating of all movies in the database.
     *
     * @return double average rating (0.0 if empty)
     */
    public double getRatingAverage() {

        double ratingTotal = 0;

        if (movieDatabase.isEmpty()) return 0.0;

        for (Map.Entry<Integer, Movie> entry : movieDatabase.entrySet()) {
            ratingTotal += entry.getValue().getImdbRating();
        }

        return ratingTotal / movieDatabase.size();
    }

    /**
     * By: Duku Wani 2026/03/23 - T10
     * Returns the top 5 highest rated movies.
     * Uses Comparator.comparingDouble with secondary sort by year.
     *
     * @return java.util.ArrayList<Movie> top 5 movies
     */
    public java.util.ArrayList<Movie> getTop5() {

        java.util.ArrayList<Movie> movieList = new java.util.ArrayList<>(movieDatabase.values());

        movieList.sort(java.util.Comparator.comparingDouble(Movie::getImdbRating).reversed().thenComparing(BY_YEAR.reversed()));

        java.util.ArrayList<Movie> top5 = new java.util.ArrayList<>();

        for (int i = 0; i < 5 && i < movieList.size(); i++) {
            top5.add(movieList.get(i));
        }

        return top5;
    }

    /**
     * By: Duku Wani 2026/03/23 - T10
     * Returns the highest rated movie in the database.
     *
     * @return Movie highest rated movie or null if empty
     */
    public Movie getHighestRated() {

        java.util.ArrayList<Movie> list = new java.util.ArrayList<>(movieDatabase.values());

        list.sort(BY_RATING.reversed().thenComparing(BY_YEAR.reversed()));

        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * By: Duku - 2026/03/23 - T10
     * Returns the lowest rated movie in the database.
     *
     * @return Movie lowest rated movie or null if empty
     */
    public Movie getLowestRated() {

        java.util.ArrayList<Movie> list = new java.util.ArrayList<>(movieDatabase.values());

        list.sort(BY_RATING.thenComparing(BY_YEAR));

        return list.isEmpty() ? null : list.get(0);
    }
}