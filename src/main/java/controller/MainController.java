package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Genre;
import model.Movie;
import util.CsvFileHandler;

import java.util.ArrayList;

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
