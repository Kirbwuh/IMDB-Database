package model;

import java.util.Locale;

public enum Genre {
    Action,
    Horror,
    Drama,
    Comedy,
    Romance,
    Fantasy,
    Mystery,
    Animation, ;

    /**
     * Duku - 20/03/2026 - T10
     * Factory method to convert a String into a Genre enum.
     * @param movieText The raw string (from the csv file or wherever we're keeping the movies at)
     * @return The matching Genre constant.
     */
    public static Genre fromString(String movieText) {
        if (movieText == null || movieText.trim().isEmpty()) {
            throw new IllegalArgumentException("Genre must be one of the enum options.");
        }

        String normalized = movieText.trim();
        for (Genre genre : Genre.values()) {
            if (genre.name().equalsIgnoreCase(normalized)) {
                return genre;
            }
        }

        throw new IllegalArgumentException("Invalid genre: " + movieText);
    }
    @Override
    public String toString() { return name().charAt(0) + name().substring(1).toLowerCase();}
    }
