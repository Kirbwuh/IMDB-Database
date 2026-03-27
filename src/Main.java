package src;

import java.util.Scanner;

import src.controller.Controller;
import src.view.ConsoleView;
import src.view.InputView;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ConsoleView consoleView = new ConsoleView();
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
			InputView.showMainMenu(scanner);
		}
	}
}