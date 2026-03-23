package view;

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

/*
 *  showMainMenu()
 *  |
 *  +-- 1. Manage Database --> showDatabaseMenu()
 *  |         |
 *  |         +-- 1. Add Movie --> showAddMovieMenu()
 *  |         |         |
 *  |         |         +-- 1. Step by step
 *  |         |         +-- 2. Single line
 *  |         |         +-- 3. Back
 *  |         |
 *  |         +-- 2. Search by ID
 *  |         +-- 3. Update movie
 *  |         +-- 4. Remove movie
 *  |         +-- 5. Print all movies
 *  |         +-- 6. Back
 *  |
 *  +-- 2. Exit
 */

public class ConsoleView {

    public int printMainMenu(Scanner scanner) {
        String choice;

        System.out.println("********************* IMDb Movie Database - CPSC219 W26  *********************");
        System.out.println("Track and store your favourite movies with ratings, directors, genres and more.");
        System.out.println();

        System.out.println("""
        +--------+---------------------------+--------------------------------+
        | Option | Action                    | Description                    |
        +--------+---------------------------+--------------------------------+
        |   1    | Manage database           | Add, search, update or remove  |
        |   2    | Exit                      | Close the program              |
        +--------+---------------------------+--------------------------------+""");

        do {
            System.out.println("Please enter an option (1 or 2):");
            choice = scanner.nextLine().trim();

            if (!choice.equals("1") && !choice.equals("2")) {
                System.out.println("Invalid input. Please enter 1 or 2.");
            }

        } while (!choice.equals("1") && !choice.equals("2"));

        return Integer.parseInt(choice);
    }

    public int printDatabaseMenu(Scanner scanner) {
        String choice;

        System.out.println("\n==================== Database Management ====================");
        System.out.println("""
                        +--------+---------------------------+--------------------------------+
                        | Option | Action                    | Description                    |
                        +--------+---------------------------+--------------------------------+
                        |   1    | Add movie                 | Choose an add method           |
                        |   2    | Search movie by ID        | Find a movie using its ID      |
                        |   3    | Update movie              | Modify an existing movie       |
                        |   4    | Remove movie              | Delete a movie from database   |
                        |   5    | Print all movies          | Display all stored movies      |
                        |   6    | Reviews                   | Average & individual ratings   |
                        |   7    | Highest rated movie       | Display the highest rated movie|
                        |   8    | Lowest rated movie        | Display the lowest rated movie |
                        |   9    | Print Top 5 Movies        | Display the top 5 rated movies |
                        |   10   | Back                      | Return to main menu            |
                        +--------+---------------------------+--------------------------------+""");

        do {
            System.out.println("Please enter an option (1-10):");
            choice = scanner.nextLine().trim();

            if (!choice.matches("([1-9]|10)")) {
                System.out.println("Invalid input. Please enter a number between 1 and 10.");
            }

        } while (!choice.matches("([1-9]|10)"));

        return Integer.parseInt(choice);
    }

    public int printAddMovieMenu(Scanner scanner) {
        String choice;

        System.out.println("\n==================== Add Movie ====================");
        System.out.println("""
        +--------+---------------------------+--------------------------------+
        | Option | Action                    | Description                    |
        +--------+---------------------------+--------------------------------+
        |   1    | Add movie (step by step)  | Answer one value at a time     |
        |   2    | Add movie (single line)   | Enter all 8 values with commas |
        |   3    | Back                      | Return to database menu        |
        +--------+---------------------------+--------------------------------+""");

        do {
            System.out.println("Please enter an option (1-3):");
            choice = scanner.nextLine().trim();

            if (!choice.matches("[1-3]")) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
            }

        } while (!choice.matches("[1-3]"));

        return Integer.parseInt(choice);
    }
}
