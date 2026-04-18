package model;

public enum Genre {
    Action("Action"),
    Horror("Horror"),
    Drama("Drama"),
    Comedy("Comedy"),
    Romance("Romance"),
    Fantasy("Fantasy"),
    Mystery("Mystery"),
    Animation("Animation"),
    Crime("Crime"),
    SciFi("Sci-Fi");

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

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

        String normalized = normalize(movieText);
        for (Genre genre : Genre.values()) {
            if (normalize(genre.name()).equals(normalized)
                    || normalize(genre.displayName).equals(normalized)) {
                return genre;
            }
        }

        throw new IllegalArgumentException("Invalid genre: " + movieText);
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().replaceAll("[^A-Za-z0-9]", "").toLowerCase();
    }

    @Override
    public String toString() {
        return displayName;
    }
}
