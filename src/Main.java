import java.util.ArrayList;
import java.util.Scanner;
import controller.Controller;
import util.HelperMethods;
import view.InputView;
import view.MainMenus;

public class Main {

	static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MainMenus mainMenus = new MainMenus();
		int  mainMenuChoice, searchDatabaseChoice, highlightsChoice, manageChoice, entryChoice;

		if (args.length > 0) {
			if ("--load".equals(args[0])) {
				try {
					Controller.loadMoviesFromCsv();
				} catch (Exception e) {
					System.out.println("Error loading CSV");
				}
			}
		}

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
								Controller.handleUpdateMovie(field,value,title);
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

