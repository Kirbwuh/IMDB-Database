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

This application functions as a personal IMDb-style movie database manager where users can store and organize information about movies. The core functionality revolves around two main areas:

### 1. CRUD Operations - Movie Management

CRUD stands for Create, Read, Update, and Delete. These are the four basic operations for managing movie data:

**Create (Add)**
- Users can add new movies to the database in two ways:
  - Single line entry mode - answer questions one at a time
  - Multiline entry mode - for entering longer descriptions
- Each movie gets a unique ID automatically when added
- The following information is stored for each movie:
  - Movie title
  - Release year
  - PG-13 certification status
  - Genre
  - IMDb rating (score out of 10)
  - Description/overview
  - Director name
  - Box office gross (revenue)

**Read (Retrieve)**
- View a specific movie by entering its ID number
- Display all movies in the database at once
- Access individual movie details whenever needed

**Update (Modify)**
- Change any piece of information about an existing movie
- Select which field to update (title, year, rating, etc.)
- Correct mistakes or add new information

**Delete (Remove)**
- Remove movies from the database completely
- Delete by specifying the movie ID
- Permanently removes all associated data

### 2. Summary Functions - Data Analysis

- **Calculate Average Rating** - Computes the average IMDb rating across all movies in the database
- **View Top 5 Movies** - Displays the top 5 highest-rated movies from the database, sorted by rating
- **View Statistics** - Display information about the collection of movies

## How to Use It

The program uses a menu-driven interface where users navigate through options to perform different operations. When the program starts, users are presented with a main menu to either access the database or exit the application.

### src.Main Menu Flow

1. Select "Database" to access movie management options
2. Choose from the available CRUD operations or summary functions
3. Follow prompts to enter information or view data
4. Press Enter to continue after each operation

### CRUD Operation Examples

- **Adding a movie**: Select "Add Movie" from the menu, choose entry type, and answer the prompts for each field
- **Finding a movie**: Select "View Movie" and enter the movie ID
- **Modifying data**: Select "Update Movie", specify the ID and field to change, then enter the new value
- **Removing a movie**: Select "Delete Movie" and provide the movie ID for removal

## How the Code Works

The project uses a **HashMap** data structure to store all the movies. Each movie is assigned a unique ID number automatically when it is added to the database. The movie information is organized in a String array within the HashMap.

Constants are defined at the beginning of the code to track the position of each data field:
- SERIES_TITLE = 0 (movie name)
- RELEASE_YEAR = 1
- CERTIFICATION = 2 (PG-13 rating)
- GENRE = 3
- IMDB_RATING = 4
- OVERVIEW = 5 (description)
- DIRECTOR = 6
- GROSS = 7 (box office revenue)

### Example Movie Entry

Here is how a movie is stored in the HashMap:

```
Movie ID: 1

movies.get(1) = [
    "The Shawshank Redemption",     // Index 0: SERIES_TITLE
    "1994",                          // Index 1: RELEASE_YEAR
    "true",                          // Index 2: CERTIFICATION
    "Drama",                         // Index 3: GENRE
    "9.3",                           // Index 4: IMDB_RATING
    "Two imprisoned men bond...",    // Index 5: OVERVIEW
    "Frank Darabont",                // Index 6: DIRECTOR
    "28341469"                       // Index 7: GROSS
]
```

Each movie stored in the HashMap uses this structure, allowing easy access to specific information using the defined constants.


## Testing

The project includes comprehensive unit tests for validating different components:

- **DataMethodsCPSC219W26ProjectTest.java** - Tests the CRUD operations and database management methods
- **InputMethodsCPSC219W26ProjectTest.java** - Tests input validation and data handling
- **HelperMethodsCPSC219W26ProjectTest.java** - Tests supporting utility functions
- **getRatingAverageTest.java** - Tests the rating calculation functionality

These tests ensure that all major functions work correctly and handle edge cases properly.


## How to Run

1. Make sure you have Java installed
2. Navigate to the project folder
3. Compile the program: `javac src/CPSC219W26Project.java`
4. Run it: `java src.CPSC219W26Project`
5. Follow the on-screen prompts.



