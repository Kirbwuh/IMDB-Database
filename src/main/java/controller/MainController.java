package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Genre;
import model.Movie;
import model.RowEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainController {

    private enum DisplayMode {
        MOVIES,
        SERIES
    }

    private DisplayMode currentMode = DisplayMode.MOVIES;

    private final Controller controller = new Controller();
    private boolean loadCsvOnStartup = false;

    @FXML
    public Button movieMode;

    @FXML
    public Button seriesMode;

    @FXML
    private Button addMovieCenterBtn;

    @FXML
    private TableColumn<RowEntry, String> description;

    @FXML
    private Button editMovieBtn;

    @FXML
    private TableColumn<RowEntry, String> genreCol;

    @FXML
    private TableColumn<RowEntry, String> hlMovieCol;

    @FXML
    private TableColumn<RowEntry, Double> hlRatingCol;

    @FXML
    private TableView<RowEntry> highlightsTableView;

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
    private TableView<RowEntry> tableView;

    @FXML
    private TableColumn<RowEntry, Double> ratingCol;

    @FXML
    private Button removeMovieBtn;

    @FXML
    private Button saveCSVBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<RowEntry, String> titleCol;

    @FXML
    private Button top5Btn;

    @FXML
    private TableColumn<RowEntry, Integer> yearCol;

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
        hlMovieCol.setCellValueFactory(data ->
            new ReadOnlyStringWrapper(data.getValue().getTitle()));
        hlRatingCol.setCellValueFactory(data ->
            new ReadOnlyObjectWrapper<>(data.getValue().getImdbRating()));

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

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldMovie, newMovie) -> {
            updateInfoFromSelectedMovie();
        });

        // Startup data loading is handled by initializeStartupData().
    }

    public void setLoadCsvOnStartup(boolean loadCsvOnStartup) {
        this.loadCsvOnStartup = loadCsvOnStartup;
    }

    public void initializeStartupData() {
        if (loadCsvOnStartup) {
            controller.loadMoviesFromCsv();
        }
        refreshTable();
        setHighlights(new ArrayList<>());
    }

    private void setHighlights(List<Movie> movies) {
        highlightsTableView.setItems(FXCollections.observableArrayList(movies));
    }

    private void refreshTable() {
        if (currentMode == DisplayMode.MOVIES) {
            tableView.setItems(FXCollections.observableArrayList(controller.getAllMovies()));
        } else {
            tableView.setItems(FXCollections.observableArrayList(controller.getAllSeries()));
        }
    }

    private void applyGenreFilter() {
        String selectedGenre = genreComboBox.getSelectionModel().getSelectedItem();
        // If dropdown options is all returns all movies
        if (selectedGenre == null || selectedGenre.equalsIgnoreCase("All")) {
            refreshTable();
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

        tableView.setItems(FXCollections.observableArrayList(filtered));
    }

    private RowEntry getSelected() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    private void updateInfoFromSelectedMovie() {
        RowEntry entry = getSelected();
        if (entry == null) {
            return;
        }

        infoTitleLabel.setText(entry.getTitle());
        infoYearLabel.setText(String.valueOf(entry.getYear()));
        infoGenreLabel.setText(entry.getGenre());
        infoRatingLabel.setText(String.valueOf(entry.getImdbRating()));
        if (entry instanceof Movie movie) {
            infoDirectorLabel.setText(movie.getDirector());
            infoCertLabel.setText(String.valueOf(movie.isCertification()));
            infoGrossLabel.setText(String.valueOf(movie.getGross()));
        }
        infoDescLabel.setText(entry.getDescription());
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
                refreshTable();

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
        RowEntry entry = getSelected();
        if (entry == null) {
            return;
        }

        try {
            // Loading the edit movie dialog
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/editMovieDialog.fxml")));
            Parent editMovieBox = loader.load();

            // Getting each field input
            TextField titleField = (TextField) loader.getNamespace().get("titleField");
            TextField yearField = (TextField) loader.getNamespace().get("yearField");
            TextField certificationField = (TextField) loader.getNamespace().get("certificationField");
            TextField ratingField = (TextField) loader.getNamespace().get("ratingField");
            ComboBox<String> editMovieGenreBox = (ComboBox<String>) loader.getNamespace().get("genreField");
            TextField descriptionField = (TextField) loader.getNamespace().get("descriptionField");
            TextField directorField = (TextField) loader.getNamespace().get("directorField");
            TextField grossField = (TextField) loader.getNamespace().get("grossField");

            // Genre values in combobox
            ArrayList<String> editMovieGenreOptions = new ArrayList<>();
            for (Genre genre : Genre.values()) {
                editMovieGenreOptions.add(genre.toString());
            }

            editMovieGenreBox.setItems(FXCollections.observableArrayList(editMovieGenreOptions));
            editMovieGenreBox.setPromptText("Genre");

            // Pre-fill selected movie values
            titleField.setText(entry.getTitle());
            yearField.setText(String.valueOf(entry.getYear()));
            editMovieGenreBox.getSelectionModel().select(entry.getGenre());
            ratingField.setText(String.valueOf(entry.getImdbRating()));
            descriptionField.setText(entry.getDescription());
            if (entry instanceof Movie movie) {
                certificationField.setText(String.valueOf(movie.isCertification()));
                directorField.setText(movie.getDirector());
                grossField.setText(String.valueOf(movie.getGross()));
            }

            // Open as a simple window (no alert popup)
            Stage editStage = new Stage();
            editStage.setTitle("Edit Movie");
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.setScene(new Scene(editMovieBox));
            editStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleLoadCSV(ActionEvent event) {
        controller.loadMoviesFromCsv();
        applyGenreFilter();
    }

    @FXML
    void handleLowestRated(ActionEvent event) {
        Movie lowestRatedMovie = controller.handleLowestRating();
        if (lowestRatedMovie == null) {
            tableView.setItems(FXCollections.observableArrayList());
            setHighlights(new ArrayList<>());
            return;
        }

        tableView.setItems(FXCollections.observableArrayList(lowestRatedMovie));
        setHighlights(FXCollections.observableArrayList(lowestRatedMovie));
        tableView.getSelectionModel().selectFirst();
        updateInfoFromSelectedMovie();
    }

    @FXML
    void handleRemoveMovie(ActionEvent event) {
        RowEntry entry = getSelected();
        if (!(entry instanceof Movie movie)) {
            return;
        }

        boolean removed = controller.handleRemoveMovie(0, movie.getTitle());
        if (!removed) {
            return;
        }

        applyGenreFilter();
        tableView.getSelectionModel().clearSelection();
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
        String rawQuery = searchField.getText();
        String query = rawQuery == null ? "" : rawQuery.trim();

        if (query.isEmpty()) {
            applyGenreFilter();
            return;
        }

        Movie foundMovie = controller.handleGetMovie(query);
        if (foundMovie == null) {
            tableView.setItems(FXCollections.observableArrayList());
            return;
        }

        tableView.setItems(FXCollections.observableArrayList(foundMovie));
        tableView.getSelectionModel().selectFirst();
        updateInfoFromSelectedMovie();
    }

    @FXML
    void handleTop5(ActionEvent event) {
        ArrayList<Movie> topMovies = controller.getTop5();
        tableView.setItems(FXCollections.observableArrayList(topMovies));
        setHighlights(topMovies);
        if (!topMovies.isEmpty()) {
            tableView.getSelectionModel().selectFirst();
            updateInfoFromSelectedMovie();
        }
    }

    @FXML
    void handleHighestRated(ActionEvent event) {
        Movie highestRatedMovie = controller.handleHighestRating();
        if (highestRatedMovie == null) {
            tableView.setItems(FXCollections.observableArrayList());
            setHighlights(new ArrayList<>());
            return;
        }

        tableView.setItems(FXCollections.observableArrayList(highestRatedMovie));
        setHighlights(FXCollections.observableArrayList(highestRatedMovie));
        tableView.getSelectionModel().selectFirst();
        updateInfoFromSelectedMovie();
    }

    @FXML
    void handleShowMovies(ActionEvent event) {
        currentMode = DisplayMode.MOVIES;
        controller.loadMoviesFromCsv();
        refreshTable();
    }

    @FXML
    void handleShowSeries(ActionEvent event) {
        currentMode = DisplayMode.SERIES;
        controller.loadSeriesFromCsv();
        refreshTable();
    }
}
