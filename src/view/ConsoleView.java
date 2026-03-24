package src.view;

import java.util.Scanner;

//Extract ALL System.out menu printing into a dedicated ConsoleView class. No data logic here — pure display.
//
//Checklist
//
//Create ConsoleView with printMainMenu()
//
//printDatabaseMenu()
//
//printAddMovieMenu()
//
//printMovie(Movie m)
//
//printAllMovies(List list)
//
//printRatings(List, double avg)
//
//printMessage(String msg) for generic output
//
//printError(String msg) for error output
//
//Javadoc on every method


public class ConsoleView {

    private int promptForChoice(Scanner scanner, String prompt, String validPattern, String errorMessage) {
        String choice;

        do {
            System.out.println(prompt);
            choice = scanner.nextLine().trim();

            if (!choice.matches(validPattern)) {
                System.out.println(errorMessage);
            }
        } while (!choice.matches(validPattern));

        return Integer.parseInt(choice);
    }

    public int printMainMenu(Scanner scanner) {
        System.out.println("********************* IMDb Movie Database - CPSC219 W26  *********************");
        System.out.println("Track and store your favourite movies with ratings, directors, genres and more.");
        System.out.println();

        System.out.println("""
                        +--------+---------------------------+--------------------------------+
                        | Option | Action                    | Description                    |
                        +--------+---------------------------+--------------------------------+
                        |   1    | Search Database           | See all movies or search       |
                        |   2    | Database Highlights       | Top 5's, highest rated, etc..  |
                        |   3    | Manage Database           | Add, update or remove movies   |
                        |   4    | Exit                      | Close the program              |
                        +--------+---------------------------+--------------------------------+""");

        return promptForChoice(scanner, "Please enter an option (1, 2, 3, 4):", "[1-4]",
                "Invalid input. Please enter 1, 2, 3, or 4.");
    }

    public int printSearchDatabaseMenu(Scanner scanner) {
        System.out.println("\n==================== Search Database ====================");
        System.out.println("""
                        +--------+---------------------------+--------------------------------+
                        | Option | Action                    | Description                    |
                        +--------+---------------------------+--------------------------------+
                        |   1    | Search movie by ID        | Search for a movie             |
                        |   2    | Print all movies          | Find a movie using its ID      |
                        |   3    | Exit                      | Find a movie using its ID      |
                        +--------+---------------------------+--------------------------------+""");

        return promptForChoice(scanner, "Please enter an option (1, 2 or 3 ):", "[1-3]",
                "Invalid input. Please enter 1, 2 or 3.");
    }

    public int printDatabaseHighlightsMenu(Scanner scanner) {
        System.out.println("\n==================== Database Highlights ====================");
        System.out.println("""
                        +--------+---------------------------+--------------------------------+
                        | Option | Action                    | Description                    |
                        +--------+---------------------------+--------------------------------+
                        |   1    | Top 5                     | Search for a movie             |
                        |   2    | Highest Rated Movie       | Search for a movie             |
                        |   3    | Lowest Rated Movie        | Search for a movie             |
                        |   4    | Reviews                   | Search for a movie             |
                        |   5    | Exit                      | Find a movie using its ID      |
                        +--------+---------------------------+--------------------------------+""");

        return promptForChoice(scanner, "Please enter an option (1-5):", "[1-5]",
                "Invalid input. Please enter a number between 1 and 5.");
    }


    public int printManageDatabaseMenu(Scanner scanner) {
        System.out.println("\n==================== Manage Database ====================");
        System.out.println("""
                        +--------+---------------------------+--------------------------------+
                        | Option | Action                    | Description                    |
                        +--------+---------------------------+--------------------------------+
                        |   1    | Add movie                 | Choose an add method           |
                        |   2    | Update movie              | Modify an existing movie       |
                        |   3    | Remove movie              | Delete a movie from database   |
                        |   4    | Back                      | Return to main menu            |
                        +--------+---------------------------+--------------------------------+""");

        return promptForChoice(scanner, "Please enter an option (1-4):", "([1-4])",
                "Invalid input. Please enter a number between 1 and 4.");
    }

//    public int printAddMovieMenu(Scanner scanner) {
//        System.out.println("\n==================== Add Movie ====================");
//        System.out.println("""
//        +--------+---------------------------+--------------------------------+
//        | Option | Action                    | Description                    |
//        +--------+---------------------------+--------------------------------+
//        |   1    | Add movie (step by step)  | Answer one value at a time     |
//        |   2    | Add movie (single line)   | Enter all 8 values with commas |
//        |   3    | Back                      | Return to database menu        |
//        +--------+---------------------------+--------------------------------+""");
//
//        return promptForChoice(scanner, "Please enter an option (1-3):", "[1-3]",
//                "Invalid input. Please enter a number between 1 and 3.");
//    }
}
