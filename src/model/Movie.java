package model;

import java.util.Objects;

/**
 * Represents a single movie entry.
 * Extends RowEntry
 * Movie adds: certification (PG-13), director, and gross earnings.
 * Overrides .equals() and .hashcode().
 * @author J.J. Rondon 16/03/2026 T10
 * @coauthor Haitam Lembaid 23/03/2026 T10
 */
public class Movie extends RowEntry {

    // --------ATTRIBUTES-----------
    // ******************************
    // Instance Variables
    // ******************************

    private boolean certification;
    private String director;
    private long gross;

    // ******************************
    // Constructors
    // ******************************

    /**
     * Constructor when creating a new Movie
     * @param title         movie title
     * @param year          release year
     * @param certification true if rated PG-13
     * @param genre         genre(s) of the movie
     * @param imdbRating    IMDB rating out of 10
     * @param description   short description of the movie
     * @param director      name of the director
     * @param gross         total box office gross earnings
     */
    public Movie(String title, int year, boolean certification, String genre,
                 double imdbRating, String description, String director, long gross) {
        // from parent rowEntry class
        super(title, year, genre, imdbRating, description);
        this.certification = certification;
        this.director      = director;
        this.gross         = gross;
    }

    // ******************************
    // Getters
    // ******************************

    /**
     * Returns whether this movie is rated PG-13
     * @return boolean true if PG-13
     */
    public boolean isCertification() { return certification; }

    /**
     * Returns the name of the director
     * @return String director name
     */
    public String getDirector() { return director; }

    /**
     * Returns the gross box office earnings
     * @return long gross earnings in dollars
     */
    public long getGross() { return gross; }

    // ******************************
    // Setters
    // ******************************

    /**
     * Updates the PG-13 certification of this movie
     * @param certification new certification status
     */
    public void setCertification(boolean certification) { this.certification = certification; }

    /**
     * Updates the director of this movie
     * @param director new director name
     */
    public void setDirector(String director) { this.director = director; }

    /**
     * Updates the gross earnings of this movie
     * @param gross new gross earnings
     */
    public void setGross(long gross) { this.gross = gross; }

    // ******************************
    // Methods
    // ******************************

    /**
     * Overrides equals(Obj o) to take in Movie onjects and checks if one entry is the same as another..
     * @param entry - The entry to be entered into the CSV.
     * @return true if two entries are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie other = (Movie) obj;
        return getYear() == other.getYear()
                && Objects.equals(getTitle(), other.getTitle())
                && Objects.equals(director, other.director);
    }

    /**
     * Overrides hashcode() in RowEntry.
     * @return An integer hash made from the director and title of an entry.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), director, getYear());
    }

    /**
     * Returns a formatted string of all movie fields.
     * @return String formatted movie details
     */
    @Override
    public String toString() {
        return super.toString() // from parent method csv
                + "\n Certification (PG-13): " + certification
                + "\n Director: "              + director
                + "\n Gross: $"                + gross
                + "\n";
    }

    /**
     * Returns a CSV row for this movie.
     * @return String CSV row representation of this movie
     */
    @Override
    public String toCSVStringRow() {
        return getTitle()
                + "," + getYear()
                + "," + certification
                + "," + getGenre()
                + "," + getImdbRating()
                + "," + getDescription()
                + "," + director
                + "," + gross;
    }
}
