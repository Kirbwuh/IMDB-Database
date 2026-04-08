package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private Button addMovieCenterBtn;

    @FXML
    private TableColumn<?, ?> description;

    @FXML
    private Button editMovieBtn;

    @FXML
    private TableColumn<?, ?> genreCol;

    @FXML
    private ComboBox<?> genreComboBox;

    @FXML
    private Button highestRatedBtn;

    @FXML
    private TableView<?> highlightsTableView;

    @FXML
    private TableColumn<?, ?> hlMovieCol;

    @FXML
    private TableColumn<?, ?> hlRatingCol;

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
    private TableView<?> moviesTableView;

    @FXML
    private TableColumn<?, ?> ratingCol;

    @FXML
    private Button removeMovieBtn;

    @FXML
    private Button saveCSVBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private Button top5Btn;

    @FXML
    private TableColumn<?, ?> yearCol;

    @FXML
    void handleAddMovie(ActionEvent event) {

    }

    @FXML
    void handleEditMovie(ActionEvent event) {

    }

    @FXML
    void handleHighestRated(ActionEvent event) {

    }

    @FXML
    void handleLoadCSV(ActionEvent event) {

    }

    @FXML
    void handleLowestRated(ActionEvent event) {

    }

    @FXML
    void handleRemoveMovie(ActionEvent event) {

    }

    @FXML
    void handleSaveCSV(ActionEvent event) {

    }

    @FXML
    void handleSearch(ActionEvent event) {

    }

    @FXML
    void handleTop5(ActionEvent event) {

    }

}
