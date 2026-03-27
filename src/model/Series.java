package model;

import java.util.Objects;

/**
 * Represents a single series entry.
 * Extends  RowEntry
 * (title, year, genre, imdbRating, description).
 * Series adds: numberOfSeasons, numberOfEpisodes, and creator.
 * @author J.J. Rondon 23/03/2026 T10
 */
public class Series extends RowEntry {

    // --------ATTRIBUTES-----------
    // ******************************
    // Instance Variables
    // ******************************

    private int numberOfSeasons;
    private int numberOfEpisodes;
    private String creator;

    // ******************************
    // Constructors
    // ******************************

    /**
     * Constructor when creating a new Series
     * @param title            series title
     * @param year             premiere year
     * @param genre            genre(s) of the series
     * @param imdbRating       IMDB rating out of 10
     * @param description      short description of the series
     * @param numberOfSeasons  total number of seasons
     * @param numberOfEpisodes total number of episodes
     * @param creator          name of the creator(s)
     */
    public Series(String title, int year, String genre, double imdbRating,
                  String description, int numberOfSeasons,
                  int numberOfEpisodes, String creator) {
        // from parent rowEntry class
        super(title, year, genre, imdbRating, description);
        this.numberOfSeasons  = numberOfSeasons;
        this.numberOfEpisodes = numberOfEpisodes;
        this.creator          = creator;
    }

    // ******************************`
    // Getters
    // ******************************

    /**
     * Returns the total number of seasons of this series
     * @return int number of seasons
     */
    public int getNumberOfSeasons()  { return numberOfSeasons; }

    /**
     * Returns the total number of episodes of this series
     * @return int number of episodes
     */
    public int getNumberOfEpisodes() { return numberOfEpisodes; }

    /**
     * Returns the name of the creator(s) of this series
     * @return String creator name
     */
    public String getCreator()       { return creator; }

    // ******************************
    // Setters
    // ******************************

    /**
     * Updates the number of seasons of this series
     * @param numberOfSeasons new number of seasons
     */
    public void setNumberOfSeasons(int numberOfSeasons)   { this.numberOfSeasons = numberOfSeasons; }

    /**
     * Updates the number of episodes of this series
     * @param numberOfEpisodes new number of episodes
     */
    public void setNumberOfEpisodes(int numberOfEpisodes) { this.numberOfEpisodes = numberOfEpisodes; }

    /**
     * Updates the creator of this series
     * @param creator new creator name
     */
    public void setCreator(String creator) { this.creator = creator; }

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
        Series other = (Series) obj;
        return getYear() == other.getYear()
                && Objects.equals(getTitle(), other.getTitle())
                && Objects.equals(creator, other.creator);
    }

    /**
     * Overrides hashcode() in RowEntry.
     * @return An integer hash made from the director and title of an entry.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), creator, getYear());
    }

    /**
     * Returns a formatted string of all movie fields.
     * @return String formatted movie details
     */
    @Override
    public String toString() {
        return super.toString() // from parent method csv
                + "\n Number of seasons: "  + numberOfSeasons
                + "\n Number of episodes: " + numberOfEpisodes
                + "\n Creator: "            + creator
                + "\n";
    }

    /**
     * Returns a CSV row for this movie.
     * @return String CSV row representation of this movie
     */
    @Override
    public String toCSVStringRow() {
        return super.commonCSVFields() // id,title,year,genre,imdbRating,description
                + "," + numberOfSeasons
                + "," + numberOfEpisodes
                + "," + creator;
    }
}
