package src.controller;
import Model.MovieDatabase;
import src.view.InputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class    MovieController {

    private static final Model.MovieDatabase MD = new MovieDatabase();
    private static Scanner scanner;



    // calling MovieDatabase
    private static void addMovieGetter(String choice){
        int choice_int = InputView.showAddMovieMenu(scanner);
        switch(choice_int){
            case 1:
                MD.addMovie(InputView.multilineEntryProcess(scanner));

        }
    }


}
