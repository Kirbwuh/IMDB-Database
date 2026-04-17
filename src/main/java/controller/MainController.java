package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.*;
import model.Genre;
import model.Movie;
import util.CsvFileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainController {

    private final Controller controller = new Controller();

    @FXML
    private Button addMovieCenterBtn;

    @FXML
    private TableColumn<Movie, String> description;

    @FXML
    private Button editMovieBtn;

    @FXML
    private TableColumn<Movie, String> genreCol;

    @FXML
    private ComboBox<String> genreComboBox;

    @FXML
    private Button highestRatedBtn;

    @FXML
    private Label infoCertLabel;

    @FXML
    private Label infoDescLabel;

    @FXML
    private Label infoDirectorLabel;

    @FXML
    private Label infoGenreLabel;

    @FXML
    private Label infoGrossLabel;

    @FXML
    private Label infoRatingLabel;

    @FXML
    private Label infoTitleLabel;

    @FXML
    private Label infoYearLabel;

    @FXML
    private Button loadCSVBtn;

    @FXML
    private Button lowestRatedBtn;

    @FXML
    private TableView<Movie> moviesTableView;

    @FXML
    private TableColumn<Movie, Double> ratingCol;

    @FXML
    private Button removeMovieBtn;

    @FXML
    private Button saveCSVBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<Movie, String> titleCol;

    @FXML
    private Button top5Btn;

    @FXML
    private TableColumn<Movie, Integer> yearCol;

    @FXML
    public void initialize() {
        // get info from table object
        // source
        // https://stackoverflow.com/questions/23378725/javafx-tableview-do-i-have-to-wrap-my-fields-in-simpleobject-integer-stringpr/23379448#23379448
        titleCol.setCellValueFactory(data ->
            new ReadOnlyStringWrapper(data.getValue().getTitle()));
        yearCol.setCellValueFactory(data ->
            new ReadOnlyObjectWrapper<>(data.getValue().getYear()));
        genreCol.setCellValueFactory(data ->
            new ReadOnlyStringWrapper(data.getValue().getGenre()));
        ratingCol.setCellValueFactory(data ->
            new ReadOnlyObjectWrapper<>(data.getValue().getImdbRating()));
        description.setCellValueFactory(data ->
            new ReadOnlyStringWrapper(data.getValue().getDescription()));

        ArrayList<String> genreOptions = new ArrayList<>();
        genreOptions.add("All");
        for (Genre genre : Genre.values()) {
            genreOptions.add(genre.toString());
        }
        genreComboBox.setItems(FXCollections.observableArrayList(genreOptions));
        genreComboBox.getSelectionModel().select("All");
        genreComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldGenre, newGenre) -> {
            applyGenreFilter();
        });

        moviesTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldMovie, newMovie) -> {
            updateInfoFromSelectedMovie();
        });

        controller.loadMoviesFromCsv();
        refreshMoviesTable();
    }

    private void refreshMoviesTable() {
        ObservableList<Movie> data = FXCollections.observableArrayList(controller.getAllMovies());
        moviesTableView.setItems(data);
    }

    private void applyGenreFilter() {
        String selectedGenre = genreComboBox.getSelectionModel().getSelectedItem();
        // If dropdown options is all returns all movies
        if (selectedGenre == null || selectedGenre.equalsIgnoreCase("All")) {
            refreshMoviesTable();
            return;
        }
        // filters movies from array with enum
        // source
        // https://stackoverflow.com/questions/76174874/filter-list-by-enum
        ArrayList<Movie> filtered = new ArrayList<>();
        for (Movie movie : controller.getAllMovies()) {
            if (movie.getGenre().equals(selectedGenre)) {
                filtered.add(movie);
            }
        }

        moviesTableView.setItems(FXCollections.observableArrayList(filtered));
    }

    private Movie getSelectedMovie() {
        return moviesTableView.getSelectionModel().getSelectedItem();
    }

    private void updateInfoFromSelectedMovie() {
        Movie movie = getSelectedMovie();
        if (movie == null) {
            return;
        }

        infoTitleLabel.setText(movie.getTitle());
        infoYearLabel.setText(String.valueOf(movie.getYear()));
        infoGenreLabel.setText(movie.getGenre());
        infoRatingLabel.setText(String.valueOf(movie.getImdbRating()));
        infoDirectorLabel.setText(movie.getDirector());
        infoCertLabel.setText(String.valueOf(movie.isCertification()));
        infoGrossLabel.setText(String.valueOf(movie.getGross()));
        infoDescLabel.setText(movie.getDescription());
    }

    @FXML
    void handleAddMovie(ActionEvent event) {
        try {
            //Loading the new fxml popup
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/addMovieDialog.fxml")));
            Parent addMovieBox = loader.load();

            //Getting each fields input
            TextField titleField = (TextField) loader.getNamespace().get("titleField");
            TextField yearField = (TextField) loader.getNamespace().get("yearField");
            TextField certificationField = (TextField) loader.getNamespace().get("certificationField");
            TextField ratingField = (TextField) loader.getNamespace().get("ratingField");
            ComboBox<String> addMovieGenreBox = (ComboBox<String>) loader.getNamespace().get("genreField");
            TextField descriptionField = (TextField) loader.getNamespace().get("descriptionField");
            TextField directorField = (TextField) loader.getNamespace().get("directorField");
            TextField grossField = (TextField) loader.getNamespace().get("grossField");

            //Genre values in combobox
            ArrayList<String> addMovieGenreOptions = new ArrayList<>();
            for (Genre genre : Genre.values()) {
                addMovieGenreOptions.add(genre.toString());
            }

            addMovieGenreBox.setItems(FXCollections.observableArrayList(addMovieGenreOptions));
            addMovieGenreBox.setPromptText("Genre");

            // Alert popup with choices
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Add Movie");
            alert.getDialogPane().setContent(addMovieBox);
            alert.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CLOSE);

            Button applyButton = (Button) alert.getDialogPane().lookupButton(ButtonType.APPLY);
            applyButton.addEventFilter(ActionEvent.ACTION, applyEvent -> {
                ArrayList<String> movieEntries = buildValidatedMovieEntries(
                        titleField,
                        yearField,
                        certificationField,
                        addMovieGenreBox,
                        ratingField,
                        descriptionField,
                        directorField,
                        grossField
                );

                if (movieEntries == null) {
                    applyEvent.consume();
                    return;
                }

                controller.handleAddMovie(movieEntries);
                refreshMoviesTable();

            });

            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Checking if input is valid
    private ArrayList<String> buildValidatedMovieEntries(
            TextField titleField,
            TextField yearField,
            TextField certificationField,
            ComboBox<String> genreField,
            TextField ratingField,
            TextField descriptionField,
            TextField directorField,
            TextField grossField
    ) {
        String title = titleField.getText().trim();
        String yearText = yearField.getText().trim();
        String certificationText = certificationField.getText().trim();
        String genreText = genreField.getSelectionModel().getSelectedItem();
        String ratingText = ratingField.getText().trim();
        String descriptionText = descriptionField.getText().trim();
        String directorText = directorField.getText().trim();
        String grossText = grossField.getText().trim();

        List<String> errors = new ArrayList<>();

        if (title.isEmpty()) {
            errors.add("Title is required.");
        }

        if (yearText.isEmpty()) {
            errors.add("Year is required.");
        } else {
            try {
                Integer.parseInt(yearText);
            } catch (NumberFormatException e) {
                errors.add("Year must be a whole number.");
            }
        }

        if (!certificationText.equalsIgnoreCase("true") && !certificationText.equalsIgnoreCase("false")) {
            errors.add("Certification must be true or false.");
        }

        if (genreText == null || genreText.isBlank()) {
            errors.add("Genre must be selected.");
        } else try {
            Genre.fromString(genreText);
        } catch (IllegalArgumentException e) {
            errors.add("Genre must be one of: Action, Horror, Drama, Comedy, Romance, Fantasy, Mystery, Animation.");
        }

        if (ratingText.isEmpty()) {
            errors.add("IMDB Rating is required.");
        } else {
            try {
                double rating = Double.parseDouble(ratingText);
                if (rating < 0.0 || rating > 10.0) {
                    errors.add("IMDB Rating must be between 0.0 and 10.0.");
                }
            } catch (NumberFormatException e) {
                errors.add("IMDB Rating must be a number.");
            }
        }

        if (descriptionText.isEmpty()) {
            errors.add("Description is required.");
        }

        if (directorText.isEmpty()) {
            errors.add("Director is required.");
        }

        if (grossText.isEmpty()) {
            errors.add("Gross Earnings is required.");
        } else {
            try {
                long gross = Long.parseLong(grossText);
                if (gross < 0) {
                    errors.add("Gross Earnings cannot be negative.");
                }
            } catch (NumberFormatException e) {
                errors.add("Gross Earnings must be a whole number.");
            }
        }

        // New popup to show what needs to be fixed.
        if (!errors.isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Invalid Movie Input");
            errorAlert.setHeaderText("Fix the following fields:");
            errorAlert.setContentText(String.join("\n", errors));
            errorAlert.showAndWait();
            return null;
        }

        // Adds the inputs from the popup to our Movies file format
        ArrayList<String> movieEntries = new ArrayList<>();
        movieEntries.add(title);
        movieEntries.add(yearText);
        movieEntries.add(certificationText.toLowerCase());
        movieEntries.add(Genre.fromString(genreText).toString());
        movieEntries.add(ratingText);
        movieEntries.add(descriptionText);
        movieEntries.add(directorText);
        movieEntries.add(grossText);
        return movieEntries;
    }

    @FXML
    void handleEditMovie(ActionEvent event) {
        Movie movie = getSelectedMovie();
        if (movie == null) {
            return;
        }

    }

    @FXML
    void handleLoadCSV(ActionEvent event) {
        CsvFileHandler movies = new CsvFileHandler("src/main/resources/util/Movies.csv");
        movies.loadCSV();
        applyGenreFilter();
    }

    @FXML
    void handleLowestRated(ActionEvent event) {

    }

    @FXML
    void handleRemoveMovie(ActionEvent event) {
        Movie movie = getSelectedMovie();
        if (movie == null) {
            return;
        }

        boolean removed = controller.handleRemoveMovie(0, movie.getTitle());
        if (!removed) {
            return;
        }

        applyGenreFilter();
        moviesTableView.getSelectionModel().clearSelection();
        infoTitleLabel.setText("—");
        infoYearLabel.setText("—");
        infoGenreLabel.setText("—");
        infoRatingLabel.setText("—");
        infoDirectorLabel.setText("—");
        infoCertLabel.setText("—");
        infoGrossLabel.setText("—");
        infoDescLabel.setText("—");

    }

    @FXML
    void handleSaveCSV(ActionEvent event) {
        // controller save movie
        controller.saveAllMoviesToCsv();
    }

    @FXML
    void handleSearch(ActionEvent event) {

    }

    @FXML
    void handleTop5(ActionEvent event) {

    }

    @FXML
    void handleHighestRated(ActionEvent event) {

    }

}
