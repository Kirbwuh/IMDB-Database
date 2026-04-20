# CPSC219W26 Movie Database Project

## About This Project

This is a Java application for managing a movie database similar to IMDb. The program allows users to perform full CRUD operations (Create, Read, Update, Delete) on a collection of movies with detailed information about each film.

### Project Team
- HOQUE, Arraf
- LASSOTA, Christopher
- Lembaid, Haitam
- Rondón Rubiano, Joaquín
- WANI, Duku

## What Does It Do?

This application functions as a personal IMDb-style movie database manager where users can store and organize information about movies and TV Shows. The core functionality revolves around two main areas:

### 1. CRUD Operations - Movie Management

CRUD stands for Create, Read, Update, and Delete. These are the four basic operations for managing movie data:

**Create (Add)**
- Users can add new movies or Series to the database with the "Add Entry" button on the left hand of the GUI:
- Each movie gets a unique ID automatically when added
- The following information is stored for each movie / series:
  - Movie / Series title
  - Release year
  - PG-13 certification status (Movie Only)
  - Number of Seasons (Series Only)
  - Genre
  - IMDb rating (score out of 10)
  - Description/overview
  - Director name / Creator
  - Box office gross (revenue)
  - Number of Episodes (Series Only)

**Read (Retrieve)**
- view a specific entry by clicking on it
- Display all movies / series in the database at once
- Access individual details whenever needed

**Update (Modify)**
- Change any piece of information about an existing entry
- Select which field to update (title, year, rating, etc.)
- Correct mistakes or add new information

**Delete (Remove)**
- Remove movies from the database completely
- Delete by specifying the entry
- Permanently removes all associated data

### 2. Summary Functions - Data Analysis

- **view Top 5 Movies** - Displays the top 5 highest-rated movies from the database, sorted by rating
- **View Highest Rated Movie** - Displays the highest-rated movie in the list
- **View Lowest Rated Movie** - Displays the lowest-rated movie in the list
- **Average Rating** - Displays the average rating of all your movies

## How to Use It

The program uses a GUI in which users navigate through options to perform different operations. When the program starts, users are presented with a main menu to either access the database or exit the application.

### Main Menu Flow

- Use the search bar to find a specific movie in your list or click help beside the search button to get a infographic to learn how to use the application.
- Use the functions on the left side to add a movie or utilize the other functions within the application.
- Use the right side to edit or remove entries from the list.

### CRUD Operation Examples

- **Adding an entry**: Select "Add entry" from the GUI, enter your data in the respective fields and click "Apply" to add the movie.
- **Finding an entry**: Select the entry in the list and click enter to find your specific movie. Click on the specific movie to find more information about it.
- **Modifying data**: Select the entry you want to edit, click "Edit" from the right side of the GUI, specify the values you would like to change and press apply.
- **Removing an entry**: Select the entry you want to delete and click the "Remove" button on the right side.

## How the Code Works
The project follows a simple MVC (Model-View-Controller) pattern. Here is a plain explanation for someone new to programming:

- **Model (the data):** this is where movies and series are represented as Java objects and stored in the in-memory database. Classes like `Movie`, `Series`, `RowEntry`, and `MovieDatabase` live here. Think of the model as the place that remembers all the movie details.

- **View (the user interface):** this project uses console menus. The `ConsoleView` and `InputView` classes handle showing menus and asking the user for input. The view only asks questions and prints results — it does not change data directly.

- **Controller :** the controller receives input from the view, converts it into the right data types or objects, and tells the model to add, update, or remove movies. It also asks the model for data to display. `MovieController` is the main place for this logic.


Quick note on storage: the app uses a simple in-memory HashMap for the session. There is a helper that can read a CSV file at startup to pre-load movies when you run the program (see run instructions below).


## Testing

The project includes comprehensive unit controller for validating different components:

- **DataMethodsCPSC219W26ProjectTest.java** - Tests the CRUD operations and database management methods
- **InputMethodsCPSC219W26ProjectTest.java** - Tests input validation and data handling
- **HelperMethodsCPSC219W26ProjectTest.java** - Tests supporting utility functions
- **getRatingAverageTest.java** - Tests the rating calculation functionality

These controller ensure that all major functions work correctly and handle edge cases properly.


## How to Run

1. Make sure you have Java installed
2. Navigate to the project folder
3. Compile the program: `javac src/CPSC219W26Project.java`
4. Run it: `java src.CPSC219W26Project`

Optional: to load `src/util/Movies.csv` into the in-memory database when the program starts, run with the `--load` flag. Example:

```
mvn javafx:run
```

Notes:
- The program is organized by package folders under `src/` (for example `src.model`, `src.view`, `src.controller`).
- The CSV loader only populates the in-memory database for the running session; it does not overwrite source files.
5. Follow the on-screen prompts.



