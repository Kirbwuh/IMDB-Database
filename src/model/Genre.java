package model;

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
     * @return The matching Genre constant, or Action as a fallback.
     */
    public static Genre fromString(String movieText) {
        // Only need to handle null since inputs will be normalized
        if (movieText == null) return Action;

        return switch (movieText) {
            case "action" -> Action;
            case "horror" -> Horror;
            case "comedy" -> Comedy;
            case "romance" -> Romance;
            case "fantasy" -> Fantasy;
            case "mystery" -> Mystery;
            case "animation" -> Animation;
            case "drama" -> Drama;
            //if nothing is inputted Action is default genre
            default -> Action;
        };
    }
    @Override
    public String toString() { return name().charAt(0) + name().substring(1).toLowerCase();}
    }
