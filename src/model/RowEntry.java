package model;

/**
 * Abstract base class for all database entries.
 * Holds fields common to every type of entry (Movie, Series):
 * a unique ID, title, year, genre, IMDB rating, and description.
 * @author J.J. Rondon 16/03/2026 T10
 */
public abstract class RowEntry {

    // --------ATTRIBUTES-----------
    // ******************************
    // Class Variables
    // ******************************

    // Autoincrement counter shared by every movie and series
    private static int nextId = 1;

    // ******************************
    // Instance Variables
    // ******************************

    // Unique Id
    private final int currentEntryId;
    private String title;
    private int year;
    // TODO: Change genre string for enum
    private Genre genre;
    private double imdbRating;
    private String description;

    // ******************************
    // Constructors
    // ******************************

    /**
     * Constructor when creating entry
     * protected so only a class of movie or series can be created later
     * @param title      name of the movie or series
     * @param year       release or premiere year
     * @param genre      genre(s) of the entry
     * @param imdbRating IMDB rating out of 10
     * @param description   short description
     */
    protected RowEntry(String title, int year, String genre,
                       double imdbRating, String description) {
        this.currentEntryId = nextId;
        nextId++;
        this.title       = title;
        this.year        = year;
        this.genre       = Genre.fromString(genre);
        this.imdbRating  = imdbRating;
        this.description = description;
    }

    // ******************************
    // Abstract Methods
    // ******************************

    /**
     * Returns a CSV row for this entry.
     * Every subclass MUST provide its own implementation
     * since Movie and Series have different fields.
     * @return String CSV row representation of this entry
     */
    public abstract String toCSVStringRow();

    // ******************************
    // Getters
    // ******************************

    /**
     * Returns the unique ID of this entry
     * @return int unique ID
     */
    public int getCurrentEntryId() { return currentEntryId; }

    /**
     * Returns the title of this entry
     * @return String title
     */
    public String getTitle()       { return title; }

    /**
     * Returns the release or premiere year of this entry
     * @return int year
     */
    public int getYear()           { return year; }

    /**
     * Returns the genre(s) of this entry
     * @return String genre
     */
    public String getGenre()       { return genre.toString(); }

    /**
     * Returns the IMDB rating of this entry
     * @return double IMDB rating
     */
    public double getImdbRating()  { return imdbRating; }

    /**
     * Returns the overview description of this entry
     * @return String overview
     */
    public String getDescription() { return description; }

    // ******************************
    // Setters
    // ******************************

    /**
     * Updates the title of this entry.
     * @param title new title
     */
    public void setTitle(String title)             { this.title = title; }

    /**
     * Updates the year of this entry.
     * @param year new year
     */
    public void setYear(int year)                  { this.year = year; }

    /**
     * Updates the genre of this entry.
     * @param genre new genre
     */
    public void setGenre(String genre)             { this.genre = Genre.fromString(genre); }

    /**
     * Updates the IMDB rating of this entry.
     * @param imdbRating new rating
     */
    public void setImdbRating(double imdbRating)   { this.imdbRating = imdbRating; }

    /**
     * Updates the overview of this entry.
     * @param description new overview
     */
    public void setDescription(String description) { this.description = description; }

    // ******************************
    // Methods
    // ******************************

    /**
     * Subclasses must implement equals
     */
    @Override
    public abstract boolean equals(Object other);

    /**
     * Subclasses must implement in inherited
     */
    @Override
    public abstract int hashCode();

    /**
     * Returns a formatted string of the common fields.
     * Child classes should call super.toString() and append their own fields.
     * @return String formatted common field details
     */
    @Override
    public String toString() {
        return
                " Title: "     + title
                + "\n Year: "      + year
                + "\n Genre: "     + genre
                + "\n IMDB Rating: "+ imdbRating
                + "\n Overview: "  + description;
    }

    /**
     * Returns a CSV string of the common fields only.
     * Called by subclasses via super.commonCSVFields() to build their full row.
     * Protected so only subclasses can use it as a building block.
     * @return String CSV representation of common fields
     */
    protected String commonCSVFields() {
        return title + "," + year + ","
                + genre + "," + imdbRating + "," + description;
    }

}
