package src;

import java.util.Scanner;
import src.view.ConsoleView;
import src.view.InputView;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ConsoleView consoleView = new ConsoleView();
		InputView inputView = new InputView(scanner);

		consoleView.printMainMenu(scanner);
		InputView.showMainMenu(scanner);
	}
}