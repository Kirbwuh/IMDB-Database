package src.model;

/**
 * Represents a single movie entry.
 * Extends RowEntry
 * Movie adds: certification (PG-13), director, and gross earnings.
 * @author J.J. Rondon 16/03/2026 T10
 */
public class Movie extends src.model.RowEntry {

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
        return super.commonCSVFields()    // id,title,year,genre,imdbRating,description
                + "," + certification
                + "," + director
                + "," + gross;
    }
}