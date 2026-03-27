package src;

import java.util.Scanner;

import src.controller.Controller;
import src.view.MainMenus;
import src.view.InputView;

import static src.controller.Controller.scanner;


public class Main {
	private static final MainMenus MM = new MainMenus();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MainMenus mainMenus = new MainMenus();
		InputView inputView = new InputView(scanner);

		if (args.length > 0) {
			if ("--load".equals(args[0])) {
				try {
					Controller.loadMoviesFromCsv();
				} catch (Exception e) {
            		System.out.println("Error loading CSV");
				}
			}
		}

		while (true) {
			switch(MM.printMainMenu(scanner)){
				case 1:
					switch(MM.printSearchDatabaseMenu(scanner)){
						case 1:
							Controller.handleAddMovie(InputView.multilineEntryProcess(scanner));
					}
					break;
				case 2:
					MM.printDatabaseHighlightsMenu(scanner);
					break;
				case 3:
					MM.printManageDatabaseMenu(scanner);
					break;
				case 4:
					System.out.println("--Exiting Program--");
					System.exit(1);
			}

		}
	}
}