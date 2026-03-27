import java.util.Scanner;

import controller.Controller;
import view.ConsoleView;
import view.InputView;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ConsoleView consoleView = new ConsoleView();
		InputView inputView = new InputView(scanner);
		Controller controller = new Controller();
		int firstMenu, mainMenuChoice = 0, searchDatabaseChoice, highlightsChoice, manageChoice;

		if (args.length > 0) {
			if ("--load".equals(args[0])) {
				try {
					Controller.loadMoviesFromCsv();
				} catch (Exception e) {
					System.out.println("Error loading CSV");
				}
			}
		}

//		System.out.println(Controller.handlePrintAllMovies());

		do {
				mainMenuChoice = consoleView.printMainMenu(scanner);

					if (mainMenuChoice == 1) {

						searchDatabaseChoice = consoleView.printSearchDatabaseMenu(scanner);

						if (searchDatabaseChoice == 1) {
							// now input view stuff
							//
						}

					} else if (mainMenuChoice == 2) {

						do {

						highlightsChoice = consoleView.printDatabaseHighlightsMenu(scanner);

							if (highlightsChoice == 1) {
								Controller.getTop5();

							} else if (highlightsChoice == 2) {
								String highestRated = Controller.handleHighestRating().toString();
								System.out.println(highestRated);

							} else if (highlightsChoice == 3) {
								String lowestRated = Controller.handleLowestRating().toString();
								System.out.println(lowestRated);

							} else if (highlightsChoice == 4 || highlightsChoice == 5 || highlightsChoice == 6) {
								System.out.println("Series Database coming soon!");
							}

						} while (highlightsChoice != 7);


					}  else if (mainMenuChoice == 3) {
						manageChoice = consoleView.printManageDatabaseMenu(scanner);

						if (manageChoice == 1) {

						}

					}

			} while (mainMenuChoice != 4);
				System.out.println("Goodbye!");
        		scanner.close();
        		System.exit(0);
		}
	}
//
//        do {
//            mainChoice = showMainMenu(scanner);
//
//            if (mainChoice == 1) {
//                do {
//                    dbChoice = showDatabaseMenu(scanner);
//
//                    if (dbChoice == 1) {
//                        do {
//                            addChoice = showAddMovieMenu(scanner);
//
//                            if (addChoice == 1) {
//                                final HashMap<Integer, String> movieRow = singleEntryProcess(scanner);
//                                addMovie(movieRow);
//                                System.out.println("Movie added successfully!");
//                                pressEnterToContinue(scanner);
//
//                            } else if (addChoice == 2) {
//                                final HashMap<Integer, String> movieRow = multilineEntryProcess(scanner);
//                                addMovie(movieRow);
//                                System.out.println("Movie added successfully!");
//                                pressEnterToContinue(scanner);
//                            }
//
//                        } while (addChoice != 3);
//
//                    } else if (dbChoice == 2) {
//                        int id = getNumericInput(scanner, "Enter movie ID:").intValue();
//                        if (getMovieById(id) == null) {
//                            System.out.println("No movie found with ID: " + id);
//                            pressEnterToContinue(scanner);
//                        } else {
//                            printMovieById(id);
//                            pressEnterToContinue(scanner);
//                        }
//
//                    } else if (dbChoice == 3) {
//                        int id = getNumericInput(scanner, "Enter movie ID to update:").intValue();
//                        if (getMovieById(id) == null) {
//                            System.out.println("No movie found with ID: " + id);
//                            pressEnterToContinue(scanner);
//                        } else {
//                            int field = getNumericInput(scanner, "Enter field (0 = Title, 1 = Year, 2 = PG13, 3 = Genre, 4 = Rating, 5 = Overview, 6 = Director, 7 = Gross):").intValue();
//                            String newValue = getStringInput(scanner, "Enter new value:");
//                            updateMovieById(id, field, newValue);
//                            System.out.println("Movie updated successfully!");
//                            pressEnterToContinue(scanner);
//                        }
//
//                    } else if (dbChoice == 4) {
//                        int id = getNumericInput(scanner, "Enter movie ID to remove:").intValue();
//                        removeMovieById(id);
//                        System.out.println("Movie removed successfully!");
//                        pressEnterToContinue(scanner);
//
//                    } else if (dbChoice == 5) {
//                        if (movies.isEmpty()) {
//                            System.out.println("No movies in the database yet. Add some movies first!");
//                            pressEnterToContinue(scanner);
//                        } else {
//                            printAllMovies();
//                            System.out.println("--- End of movie list ---");
//                            pressEnterToContinue(scanner);
//                        }
//
//                    } else if (dbChoice == 6) {
//                        if (movies.isEmpty()) {
//                            System.out.println("No movies in the database yet. Add some movies first!");
//                            pressEnterToContinue(scanner);
//                        } else {
//                            System.out.println("The movies you watch are rated " + getRatingAverage(0, 0) + " on average.");
//                            System.out.println("\nHere's the rating of every movie you've watched:");
//                            System.out.println("----------------------------------------------------");
//                            for (Map.Entry<Integer, String[]> entry : movies.entrySet()) {
//                                System.out.println("ID: " + entry.getKey() + " | " + entry.getValue()[SERIES_TITLE] + " | Rating: " + entry.getValue()[IMDB_RATING]);
//                            }
//                            System.out.println("----------------------------------------------------");
//                            pressEnterToContinue(scanner);
//                        }
//
//                    } else if (dbChoice == 7) {
//                        if (movies.isEmpty()) {
//                            System.out.println("No movies in the database yet. Add some movies first!");
//                            pressEnterToContinue(scanner);
//                        } else { System.out.println("Highest Rated Movie:"); highestValue(); pressEnterToContinue(scanner); }
//
//                    } else if (dbChoice == 8) {
//                        if (movies.isEmpty()) {
//                            System.out.println("No movies in the database yet. Add some movies first!");
//                            pressEnterToContinue(scanner);
//                        } else {
//                            System.out.println("Lowest Rated Movie:");
//                            lowestValue();
//                            pressEnterToContinue(scanner);
//                        }
//
//                    } else if (dbChoice == 9) {
//                        if (movies.isEmpty()) {
//                            System.out.println("No movies in the database yet. Add some movies first!");
//                            pressEnterToContinue(scanner);
//                        } else {
//                            printTop5();
//                            pressEnterToContinue(scanner);
//                        }
//                    }
//
//                } while (dbChoice != 10);
//            }
//
//        } while (mainChoice != 2);
//
//        System.out.println("Goodbye!");
//        scanner.close();
//        System.exit(0);
//    }