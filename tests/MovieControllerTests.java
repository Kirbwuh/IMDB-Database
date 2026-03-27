package tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.controller.Controller;
import src.model.MovieDatabase;
import src.model.Movie;



import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;



class MovieControllerTests {
   @BeforeEach
   public void setup(){
      Controller.clearDatabase();
      ArrayList<String> movie1 = new ArrayList<>();
      movie1.add("The Matrix");
      movie1.add("1999");
      movie1.add("false");
      movie1.add("Action");
      movie1.add("8.7");
      movie1.add("Keanu Reeves dodges bullets.");
      movie1.add("wachowski");
      movie1.add("463099");

      ArrayList<String> movie2 = new ArrayList<>();
      movie2.add("Star Wars");
      movie2.add("1977");
      movie2.add("true");
      movie2.add("Sci-Fi");
      movie2.add("9.8");
      movie2.add("I am your father");
      movie2.add("lucas");
      movie2.add("452123");

      ArrayList<String> movie3 = new ArrayList<>();
      movie3.add("Forest Gump");
      movie3.add("1994");
      movie3.add("true");
      movie3.add("Drama");
      movie3.add("8.8");
      movie3.add("Jenny");
      movie3.add("zemeckis");
      movie3.add("463999");

      Controller.handleAddMovie(movie1);
      Controller.handleAddMovie(movie2);
      Controller.handleAddMovie(movie3);}


   @Test
   public void testHandleAddMovie() {
      setup();

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
//   @Test
//   public void testHandleGetMovie(){
//      Controller.handleGetMovie(1,null);
//      assertEquals("The Matrix", );
//   }
   @Test
   public void testGetTop5Sorted() {

      System.out.println(Controller.handlePrintAllMovies());

      ArrayList<Movie> top5 = Controller.getTop5();

      assertEquals(3, top5.size());
   }
   @Test
   public void testhandleHighestRated(){
      Controller controller = new Controller();
      Movie highest = controller.handleHighestRating();

      assertEquals(9.8, highest.getImdbRating());
      assertEquals("Star Wars", highest.getTitle());
   }
   @Test
   public void testLowestRated(){
      Controller controller = new Controller();
      Movie lowest = controller.handleLowestRating();

      assertEquals(8.7, lowest.getImdbRating());
      assertEquals("The Matrix", lowest.getTitle());
   }

}




