package tests;

import org.junit.jupiter.api.Test;
import src.model.Movie;
import src.controller.Controller;
import src.model.MovieDatabase;

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
@Test
public void testGetTop5Sorted() {
   // Add multiple movies
   for (int i = 0; i < 6; i++) {
      ArrayList<String> movie = new ArrayList<>();
      movie.add("Movie" + i);
      movie.add("2000");
      movie.add("false");
      movie.add("action");
      movie.add(String.valueOf(5.0 + i)); // increasing ratings
      movie.add("desc");
      movie.add("director");
      movie.add("1000");

      Controller.handleAddMovie(movie);
   }

   var top5 = Controller.getTop5();

   // Check size
   assertEquals(5, top5.size());

   // Check descending order
   for (int i = 0; i < top5.size() - 1; i++) {
      assertTrue(
              top5.get(i).getImdbRating() >= top5.get(i + 1).getImdbRating()
      );
   }
}



