package controller;

import org.junit.jupiter.api.Test;
import model.Movie;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MovieControllerTests {
   Movie testEntry = new Movie(
           "The Matrix",
           1999,
           false,
           "Action",
           8.7,
           "Keanu Reeves dodges bullets.",
           "wachowski",
           463999);
   Movie testEntry2 = new Movie(
           "Star Wars",
           1977,
           true,
           "Sci-Fi",
           9.8,
           "I am your father",
           "lucas",
           452123
   );
   Movie testEntry3 = new Movie(
           "Forest Gump",
           1994,
           true,
           "Drama",
           8.8,
           "Jenny",
           "zemeckis",
           463999
   );



   @Test
   public void testHandleAddMovie() {
      ArrayList<String> movieData = new ArrayList<>();

      movieData.add("The Matrix");
      movieData.add("1999");
      movieData.add("false");
      movieData.add("action");
      movieData.add("8.7");
      movieData.add("Keanu Reeves dodges bullets.");
      movieData.add("wachowski");
      movieData.add("4372894");

      Controller.handleAddMovie(movieData);
      Controller.handlePrintAllMovies();
      int expected = 1;
      int actual = Controller.handlePrintAllMovies().size();

      // Verify it was added
      assertEquals(expected,actual,"Awesome");
   }
}
