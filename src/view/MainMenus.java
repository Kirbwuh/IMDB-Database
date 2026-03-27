package view;
import java.util.Scanner;

/**
 * CPSC 219 W26 Project
 * <p>
 * ConsoleView
 * <p>
 * Dedicated class to print all displays.
 *
 * @author Christopher Lassota
 * @email chris.lassota1@ucalgary.ca
 */

//
//printAllMovies(List list)
//
//printRatings(List, double avg)
//

public class MainMenus {

    /**
     * Prompts the user until a valid menu option is entered.
     *
     * @param scanner scanner used to read input from the console
     * @param prompt prompt shown before reading input
     * @param validPattern regular expression that defines valid input
     * @param errorMessage message shown when the input is invalid
     * @return the validated choice as an integer
     */
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

    /**
     * Prints the main menu and returns the user's selection.
     *
     * @param scanner scanner used to read input from the console
     * @return the selected main menu option
     */
    public int printMainMenu(Scanner scanner) {
        System.out.println("*************************** IMDb Movie Database - CPSC219 W26  ***************************");
        System.out.println("Track and store your favourite movies and series with ratings, directors, genres and more.");
        System.out.println();

        System.out.println("""
                        +--------+---------------------------+--------------------------------+
                        | Option | Action                    | Description                    |
                        +--------+---------------------------+--------------------------------+
                        |   1    | Search Database           | Search for movies or series    |
                        |   2    | Database Highlights       | Top 5's, highest rated, etc..  |
                        |   3    | Manage Database           | Add, update or remove movies   |
                        |   4    | Exit                      | Close the program              |
                        +--------+---------------------------+--------------------------------+""");
        return promptForChoice(scanner, "Please enter an option (1, 2, 3, 4):", "[1-4]",
                "Invalid input. Please enter 1, 2, 3, or 4.");
    }

    /**
     * Prints the search database menu and returns the user's selection.
     *
     * @param scanner scanner used to read input from the console
     * @return the selected search database menu option
     */
    public int printSearchDatabaseMenu(Scanner scanner) {
        System.out.println("\n==================== Search Database ====================");
        System.out.println("""
                        +--------+---------------------------+--------------------------------+
                        | Option | Action                    | Description                    |
                        +--------+---------------------------+--------------------------------+
                        |   1    | Search movie              | Search for a movie             |
                        |   2    | Print all movies          | See all movies                 |
                        |   3    | Search Series             | COMING SOON!                   |
                        |   4    | Print all series          | COMING SOON!                   |
                        |   5    | Back                      | Return to main menu            |
                        +--------+---------------------------+--------------------------------+""");

        return promptForChoice(scanner, "Please enter an option (1-5):", "[1-5]",
                "Invalid input. Please enter a number between 1 and 5.");
    }

    /**
     * Prints the database highlights menu and returns the user's selection.
     *
     * @param scanner scanner used to read input from the console
     * @return the selected database highlights menu option
     */
    public int printDatabaseHighlightsMenu(Scanner scanner) {
        System.out.println("\n==================== Database Highlights ====================");
        System.out.println("""
                        +--------+---------------------------+--------------------------------+
                        | Option | Action                    | Description                    |
                        +--------+---------------------------+--------------------------------+
                        |   1    | Top 5 Movies              | Show top 5 highest rated movies|
                        |   2    | Highest Rated Movie       | Show highest rated movie       |
                        |   3    | Lowest Rated Movie        | Show lowest rated movie        |
                        |   4    | Top 5 Series              | COMING SOON!                   |
                        |   5    | Highest Rated Series      | COMING SOON!                   |
                        |   6    | Lowest Rated Series       | COMING SOON!                   |
                        |   7    | Back                      | Return to main menu            |
                        +--------+---------------------------+--------------------------------+""");

        return promptForChoice(scanner, "Please enter an option (1-7):", "[1-7]",
                "Invalid input. Please enter a number between 1 and 7.");
    }


    /**
     * Prints the manage database menu and returns the user's selection.
     *
     * @param scanner scanner used to read input from the console
     * @return the selected manage database menu option
     */
    public int printManageDatabaseMenu(Scanner scanner) {
        System.out.println("\n==================== Manage Database ====================");
        System.out.println("""
                        +--------+---------------------------+--------------------------------+
                        | Option | Action                    | Description                    |
                        +--------+---------------------------+--------------------------------+
                        |   1    | Add movie                 | Choose an add method           |
                        |   2    | Update movie              | Modify an existing movie       |
                        |   3    | Remove movie              | Delete a movie from database   |
                        |   4    | Add series                | COMING SOON!                   |
                        |   5    | Update series             | COMING SOON!                   |
                        |   6    | Remove series             | COMING SOON!                   |
                        |   7    | Back                      | Return to main menu            |
                        +--------+---------------------------+--------------------------------+""");

        return promptForChoice(scanner, "Please enter an option (1-7):", "([1-7])",
                "Invalid input. Please enter a number between 1 and 7.");
    }
    public int printAddMenu(Scanner scanner){
        System.out.println("\n==================== Add to Database ====================");
        System.out.println("""
                        +--------+---------------------------+--------------------------------+
                        | Option | Action                    | Description                    |
                        +--------+---------------------------+--------------------------------+
                        |   1    | Multi-Line Entry          | Choose an add method           |
                        |   2    | Single-Line Entry         | Modify an existing movie       |
                        |   3    | Back                      | Return to main menu            |
                        +--------+---------------------------+--------------------------------+""");
        return promptForChoice(scanner, "Please enter an option (1-3):", "([1-3])",
                "Invalid input. Please enter a number between 1 and 3.");
    }

}
