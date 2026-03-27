import java.util.ArrayList;
import java.util.Scanner;

import controller.Controller;
import model.Movie;
import util.CsvFileHandler;
import util.HelperMethods;
import view.InputView;
import view.MainMenus;

import static controller.Controller.scanner;


public class Main {
	private static final MainMenus MM = new MainMenus();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MainMenus mainMenus = new MainMenus();
		InputView inputView = new InputView(scanner);
		Controller controller = new Controller();
		int firstMenu, mainMenuChoice = 0, searchDatabaseChoice, highlightsChoice, manageChoice, entryChoice;

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
				mainMenuChoice = mainMenus.printMainMenu(scanner);

					if (mainMenuChoice == 1) {

						searchDatabaseChoice = mainMenus.printSearchDatabaseMenu(scanner);

						if (searchDatabaseChoice == 1) {

							String title = InputView.getStringInput(scanner, "Enter the title of the movie you want.");
							Controller.handleGetMovie(title);
							HelperMethods.pressEnterToContinue(scanner);

						} else if (searchDatabaseChoice == 2) {

							System.out.println(Controller.handlePrintAllMovies());
							HelperMethods.pressEnterToContinue(scanner);

						}

					} else if (mainMenuChoice == 2) {

						do {

						highlightsChoice = mainMenus.printDatabaseHighlightsMenu(scanner);

							if (highlightsChoice == 1) {
								System.out.println(Controller.getTop5());
								HelperMethods.pressEnterToContinue(scanner);


							} else if (highlightsChoice == 2) {
								String highestRated = Controller.handleHighestRating().toString();
								System.out.println(highestRated);
								HelperMethods.pressEnterToContinue(scanner);


							} else if (highlightsChoice == 3) {
								String lowestRated = Controller.handleLowestRating().toString();
								System.out.println(lowestRated);
								HelperMethods.pressEnterToContinue(scanner);


							} else if (highlightsChoice == 4 || highlightsChoice == 5 || highlightsChoice == 6) {
								System.out.println("Series Database coming soon!");
								HelperMethods.pressEnterToContinue(scanner);

							}

						} while (highlightsChoice != 7);


					}  else if (mainMenuChoice == 3) {

//                        |   1    | Add movie                 | Choose an add method           |
//                        |   2    | Update movie              | Modify an existing movie       |
//                        |   3    | Remove movie              | Delete a movie from database   |

						do {

						manageChoice = mainMenus.printManageDatabaseMenu(scanner);

							if (manageChoice == 1) {

								entryChoice = mainMenus.printAddMenu(scanner);

								do {

									if (entryChoice == 1) {
										ArrayList<String> target = InputView.multilineEntryProcess(scanner);
										Controller.handleAddMovie(target);
										HelperMethods.pressEnterToContinue(scanner);
										entryChoice = 3;

									} else if (entryChoice == 2) {
										ArrayList<String> target = InputView.singlelineEntryProcess(scanner);
										Controller.handleAddMovie(target);
										HelperMethods.pressEnterToContinue(scanner);
										entryChoice = 3;
									}

								} while (entryChoice != 3);

							} else if (manageChoice == 2) {

								String title = HelperMethods.getStringInput(scanner, "Enter the title of the movie you want.");
								int field = HelperMethods.getIntegerInput(scanner,"Enter the field you want to change in the movie.");
								String value = HelperMethods.getStringInput(scanner, "Enter the value you want to insert");
								Controller.handleUpdateMovie(field,value,title); // id field targetvalue title
								HelperMethods.pressEnterToContinue(scanner);

							} else if (manageChoice == 3) {
								String removeTarget = HelperMethods.getStringInput(
										scanner,
										"Enter the movie ID or title you want to remove."
								);

								boolean removed;
								// Numeric input is treated as the database key; anything else is an
								// exact title lookup so the same prompt supports both remove paths.
								if (HelperMethods.isNumeric(removeTarget)) {
									removed = Controller.handleRemoveMovie(Integer.parseInt(removeTarget), null);
								} else {
									removed = Controller.handleRemoveMovie(0, removeTarget);
								}

								if (removed) {
									System.out.println("Movie removed successfully.");
								}
								HelperMethods.pressEnterToContinue(scanner);
							}

							else if (manageChoice == 7) {
								// Save current in-memory database to CSV
								Controller.saveAllMoviesToCsv();
								HelperMethods.pressEnterToContinue(scanner);

							} else if (manageChoice == 8) {
								// Load movies from CSV into memory
								Controller.loadMoviesFromCsv();
								HelperMethods.pressEnterToContinue(scanner);
							}

						} while (manageChoice != 9);

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
