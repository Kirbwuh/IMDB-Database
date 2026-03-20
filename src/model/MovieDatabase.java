package Model;
import java.util.HashMap;
import java.util.Map;
import model.Movie;

//Javadoc on all public methods

public class MovieDatabase {

    private HashMap<Integer, Movie> movieDatabase;
    private int nextId = 1;

    public void addMovie(Movie movie) {
        movieDatabase.put(nextId, movie);
        nextId ++;
    }

    public void removeMovie(int id) {
        movieDatabase.remove(id);
    }

    public Movie getMovie(int id) {
        return movieDatabase.get(id);
    }

    public Movie getMovie(String title) {
        for (Map.Entry<Integer, Movie> entry: movieDatabase.entrySet()) {
            Movie movie = entry.getValue();
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

    public HashMap<Integer, Movie> getAllMovies() {
        return movieDatabase;
    }

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

    public void removeMovie(String title) {
        for (Map.Entry<Integer, Movie> entry : movieDatabase.entrySet()) {
            int id = entry.getKey();
            Movie movie = entry.getValue();
            if (movie.getTitle().equals(title)) {
                movieDatabase.remove(id);
            }
        }
    }
}