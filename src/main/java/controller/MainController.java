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
import model.Series;
import model.RowEntry;
import util.CsvFileHandler;

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
    public Button averageBtn;

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
    public Label infoCreatorDirector;

    @FXML
    public Label infoSeasonsCertifications;

    @FXML
    public Label infoEpisodesGross;

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
            updateInfoFromSelected();
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
        setMovieHighlights(new ArrayList<>());
    }

    private void setMovieHighlights(List<Movie> movies) {
        highlightsTableView.setItems(FXCollections.observableArrayList(movies));
    }

    private void setSeriesHighlights(List<Series> series) {
        highlightsTableView.setItems(FXCollections.observableArrayList(series));
    }

    private Movie buildAverageMovieHighlightRow(double average) {
        double roundedAverage = Math.round(average * 100.0) / 100.0;
        return new Movie("Average Rating", 0, false, "Action", roundedAverage, "Average movie rating", "", 0);
    }

    private Series buildAverageSeriesHighlightRow(double average) {
        double roundedAverage = Math.round(average * 100.0) / 100.0;
        return new Series("Average Rating", 0, "Action", roundedAverage, "Average series rating", 0, 0, "");
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
        // filters entries from array with enum
        // source
        // https://stackoverflow.com/questions/76174874/filter-list-by-enum
        ArrayList<RowEntry> filtered = new ArrayList<>();
        if (currentMode == DisplayMode.MOVIES) {
            for (Movie movie : controller.getAllMovies()) {
                if (movie.getGenre().equals(selectedGenre)) {
                    filtered.add(movie);
                }
            }
        } else {
            for (Series series : controller.getAllSeries()) {
                if (series.getGenre().equals(selectedGenre)) {
                    filtered.add(series);
                }
            }
        }

        tableView.setItems(FXCollections.observableArrayList(filtered));
    }

    private RowEntry getSelected() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    private void updateInfoFromSelected() {
        RowEntry entry = getSelected();
        if (entry == null) {
            return;
        }

        infoTitleLabel.setText(entry.getTitle());
        infoYearLabel.setText(String.valueOf(entry.getYear()));
        infoGenreLabel.setText(entry.getGenre());
        infoRatingLabel.setText(String.valueOf(entry.getImdbRating()));
        infoDescLabel.setText(entry.getDescription());
        if (entry instanceof Movie movie) {
            infoDirectorLabel.setText(movie.getDirector());
            infoCertLabel.setText(String.valueOf(movie.isCertification()));
            infoGrossLabel.setText(String.valueOf(movie.getGross()));
        }
        if (entry instanceof Series series) {
            infoDirectorLabel.setText(series.getCreator());
            infoCertLabel.setText(String.valueOf(series.getNumberOfSeasons()));
            infoGrossLabel.setText(String.valueOf(series.getNumberOfEpisodes()));
        }
    }

    @FXML
    void handleAboutPage(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("/view/AboutPage.fxml"))));
            Parent aboutPageBox = loader.load();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleAddMovie(ActionEvent event) {
        if (currentMode == DisplayMode.SERIES) {
            handleAddSeries(event);
            return;
        }

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

    @FXML
    void handleAddSeries(ActionEvent event) {
        try {
            //Loading the new fxml popup
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/addSeriesDialog.fxml")));
            Parent addSeriesBox = loader.load();

            //Getting each fields input
            TextField titleField = (TextField) loader.getNamespace().get("titleField");
            TextField yearField = (TextField) loader.getNamespace().get("yearField");
            TextField seasonsField = (TextField) loader.getNamespace().get("seasonsField");
            TextField ratingField = (TextField) loader.getNamespace().get("ratingField");
            ComboBox<String> addSeriesGenreBox = (ComboBox<String>) loader.getNamespace().get("genreField");
            TextField descriptionField = (TextField) loader.getNamespace().get("descriptionField");
            TextField creatorField = (TextField) loader.getNamespace().get("creatorField");
            TextField episodesField = (TextField) loader.getNamespace().get("episodesField");

            //Genre values in combobox
            ArrayList<String> addSeriesGenreOptions = new ArrayList<>();
            for (Genre genre : Genre.values()) {
                addSeriesGenreOptions.add(genre.toString());
            }

            addSeriesGenreBox.setItems(FXCollections.observableArrayList(addSeriesGenreOptions));
            addSeriesGenreBox.setPromptText("Genre");

            // Alert popup with choices
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Add Series");
            alert.getDialogPane().setContent(addSeriesBox);
            alert.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CLOSE);

            Button applyButton = (Button) alert.getDialogPane().lookupButton(ButtonType.APPLY);
            applyButton.addEventFilter(ActionEvent.ACTION, applyEvent -> {
                ArrayList<String> seriesEntries = buildValidatedMovieEntries(
                        titleField,
                        yearField,
                        seasonsField,
                        addSeriesGenreBox,
                        ratingField,
                        descriptionField,
                        creatorField,
                        episodesField
                );

                if (seriesEntries == null) {
                    applyEvent.consume();
                    return;
                }

                controller.handleAddSeries(seriesEntries);
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
            TextField certificationOrSeasonsField,
            ComboBox<String> genreField,
            TextField ratingField,
            TextField descriptionField,
            TextField directorOrCreatorField,
            TextField grossOrEpisodesField
    ) {
        String title = titleField.getText().trim();
        String yearText = yearField.getText().trim();
        String certificationOrSeasonsText = certificationOrSeasonsField.getText().trim();
        String genreText = genreField.getSelectionModel().getSelectedItem();
        String ratingText = ratingField.getText().trim();
        String descriptionText = descriptionField.getText().trim();
        String directorOrCreatorText = directorOrCreatorField.getText().trim();
        String grossOrEpisodesText = grossOrEpisodesField.getText().trim();

        List<String> errors = new ArrayList<>();
        boolean isSeriesMode = currentMode == DisplayMode.SERIES;

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

        if (isSeriesMode) {
            if (certificationOrSeasonsText.isEmpty()) {
                errors.add("Number of Seasons is required.");
            } else {
                try {
                    int seasons = Integer.parseInt(certificationOrSeasonsText);
                    if (seasons < 0) {
                        errors.add("Number of Seasons cannot be negative.");
                    }
                } catch (NumberFormatException e) {
                    errors.add("Number of Seasons must be a whole number.");
                }
            }

            if (grossOrEpisodesText.isEmpty()) {
                errors.add("Number of Episodes is required.");
            } else {
                try {
                    int episodes = Integer.parseInt(grossOrEpisodesText);
                    if (episodes < 0) {
                        errors.add("Number of Episodes cannot be negative.");
                    }
                } catch (NumberFormatException e) {
                    errors.add("Number of Episodes must be a whole number.");
                }
            }

            if (directorOrCreatorText.isEmpty()) {
                errors.add("Creator is required.");
            }
        } else {
            if (!certificationOrSeasonsText.equalsIgnoreCase("true") && !certificationOrSeasonsText.equalsIgnoreCase("false")) {
                errors.add("Certification must be true or false.");
            }

            if (directorOrCreatorText.isEmpty()) {
                errors.add("Director is required.");
            }

            if (grossOrEpisodesText.isEmpty()) {
                errors.add("Gross Earnings is required.");
            } else {
                try {
                    long gross = Long.parseLong(grossOrEpisodesText);
                    if (gross < 0) {
                        errors.add("Gross Earnings cannot be negative.");
                    }
                } catch (NumberFormatException e) {
                    errors.add("Gross Earnings must be a whole number.");
                }
            }
        }

        // New popup to show what needs to be fixed.
        if (!errors.isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle(isSeriesMode ? "Invalid Series Input" : "Invalid Movie Input");
            errorAlert.setHeaderText("Fix the following fields:");
            errorAlert.setContentText(String.join("\n", errors));
            errorAlert.showAndWait();
            return null;
        }

        ArrayList<String> entries = new ArrayList<>();
        entries.add(title);
        entries.add(yearText);
        if (isSeriesMode) {
            entries.add(Genre.fromString(genreText).toString());
            entries.add(ratingText);
            entries.add(descriptionText);
            entries.add(certificationOrSeasonsText);
            entries.add(grossOrEpisodesText);
            entries.add(directorOrCreatorText);
        } else {
            entries.add(certificationOrSeasonsText.toLowerCase());
            entries.add(Genre.fromString(genreText).toString());
            entries.add(ratingText);
            entries.add(descriptionText);
            entries.add(directorOrCreatorText);
            entries.add(grossOrEpisodesText);
        }
        return entries;
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

            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Edit Movie");
            alert.getDialogPane().setContent(editMovieBox);
            alert.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CLOSE);

            String oldMovieTitle = entry.getTitle();
            Button applyButton = (Button) alert.getDialogPane().lookupButton(ButtonType.APPLY);
            applyButton.addEventFilter(ActionEvent.ACTION, applyEvent -> {
                ArrayList<String> movieEntries = buildValidatedMovieEntries(
                        titleField,
                        yearField,
                        certificationField,
                        editMovieGenreBox,
                        ratingField,
                        descriptionField,
                        directorField,
                        grossField
                );

                if (movieEntries == null) {
                    applyEvent.consume();
                    return;
                }

                controller.handleUpdateMovie(2, movieEntries.get(1), oldMovieTitle);
                controller.handleUpdateMovie(3, movieEntries.get(3), oldMovieTitle);
                controller.handleUpdateMovie(4, movieEntries.get(4), oldMovieTitle);
                controller.handleUpdateMovie(5, movieEntries.get(5), oldMovieTitle);
                controller.handleUpdateMovie(6, movieEntries.get(6), oldMovieTitle);
                controller.handleUpdateMovie(7, movieEntries.get(7), oldMovieTitle);
                controller.handleUpdateMovie(1, movieEntries.get(0), oldMovieTitle);

                if(entry instanceof Movie movie) {
                    movie.setCertification(Boolean.parseBoolean(movieEntries.get(2)));
                }

                refreshTable();
                applyGenreFilter();
                tableView.refresh();

                for (RowEntry item : tableView.getItems()) {
                    if (item.getTitle().equals(movieEntries.get(0))) {
                        tableView.getSelectionModel().select(item);
                        break;
                    }
                }
                updateInfoFromSelected();
            });

            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleLoadCSV(ActionEvent event) {
        if (currentMode == DisplayMode.MOVIES) {
            CsvFileHandler movies = new CsvFileHandler("src/main/resources/util/Movies.csv");
            movies.loadCSV();
        } else {
            CsvFileHandler series = new CsvFileHandler("src/main/resources/util/Series.csv");
            series.loadCSV();
        }
        applyGenreFilter();
    }

    @FXML
    void handleLowestRated(ActionEvent event) {
        if (currentMode == DisplayMode.MOVIES) {
            Movie lowestRatedMovie = controller.handleLowestRating();
            if (lowestRatedMovie == null) {
                tableView.setItems(FXCollections.observableArrayList());
                setMovieHighlights(new ArrayList<>());
                return;
            }

            tableView.setItems(FXCollections.observableArrayList(lowestRatedMovie));
            setMovieHighlights(FXCollections.observableArrayList(lowestRatedMovie));
        } else {
            Series lowestRatedSeries = controller.handleLowestSeriesRating();
            if (lowestRatedSeries == null) {
                tableView.setItems(FXCollections.observableArrayList());
                setSeriesHighlights(new ArrayList<>());
                return;
            }

            tableView.setItems(FXCollections.observableArrayList(lowestRatedSeries));
            setSeriesHighlights(FXCollections.observableArrayList(lowestRatedSeries));
        }
        tableView.getSelectionModel().selectFirst();
        updateInfoFromSelected();
    }

    @FXML
    void handleRemoveMovie(ActionEvent event) {
        RowEntry entry = getSelected();
        if (entry == null) {
            return;
        }

        boolean removed;
        if (entry instanceof Movie movie) {
            removed = controller.handleRemoveMovie(0, movie.getTitle());
        } else if (entry instanceof Series series) {
            removed = controller.handleRemoveSeries(0, series.getTitle());
        } else {
            return;
        }

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
        if (currentMode == DisplayMode.MOVIES) {
            controller.saveAllMoviesToCsv();
        } else {
            controller.saveAllSeriesToCsv();
        }
    }

    @FXML
    void handleSearch(ActionEvent event) {
        String rawQuery = searchField.getText();
        String query = rawQuery == null ? "" : rawQuery.trim();

        if (query.isEmpty()) {
            applyGenreFilter();
            return;
        }

        if (currentMode == DisplayMode.MOVIES) {
            Movie foundMovie = controller.handleGetMovie(query);
            if (foundMovie == null) {
                tableView.setItems(FXCollections.observableArrayList());
                return;
            }

            tableView.setItems(FXCollections.observableArrayList(foundMovie));
        } else {
            Series foundSeries = controller.handleGetSeries(query);
            if (foundSeries == null) {
                tableView.setItems(FXCollections.observableArrayList());
                return;
            }

            tableView.setItems(FXCollections.observableArrayList(foundSeries));
        }
        tableView.getSelectionModel().selectFirst();
        updateInfoFromSelected();
    }

    @FXML
    void handleTop5(ActionEvent event) {
        if (currentMode == DisplayMode.MOVIES) {
            ArrayList<Movie> topMovies = controller.getTop5();
            tableView.setItems(FXCollections.observableArrayList(topMovies));
            setMovieHighlights(topMovies);
            if (!topMovies.isEmpty()) {
                tableView.getSelectionModel().selectFirst();
                updateInfoFromSelected();
            }
        } else {
            ArrayList<Series> topSeries = controller.getTop5Series();
            tableView.setItems(FXCollections.observableArrayList(topSeries));
            setSeriesHighlights(topSeries);
            if (!topSeries.isEmpty()) {
                tableView.getSelectionModel().selectFirst();
                updateInfoFromSelected();
            }
        }
    }

    @FXML
    void handleHighestRated(ActionEvent event) {
        if (currentMode == DisplayMode.MOVIES) {
            Movie highestRatedMovie = controller.handleHighestRating();
            if (highestRatedMovie == null) {
                tableView.setItems(FXCollections.observableArrayList());
                setMovieHighlights(new ArrayList<>());
                return;
            }
            tableView.setItems(FXCollections.observableArrayList(highestRatedMovie));
            setMovieHighlights(FXCollections.observableArrayList(highestRatedMovie));
        } else {
            Series highestRatedSeries = controller.handleHighestSeriesRating();
            if (highestRatedSeries == null) {
                tableView.setItems(FXCollections.observableArrayList());
                setSeriesHighlights(new ArrayList<>());
                return;
            }
            tableView.setItems(FXCollections.observableArrayList(highestRatedSeries));
            setSeriesHighlights(FXCollections.observableArrayList(highestRatedSeries));
        }

        tableView.getSelectionModel().selectFirst();
        updateInfoFromSelected();
    }

    @FXML
    void handleAverage(ActionEvent event) {
        genreComboBox.getSelectionModel().select("All");
        refreshTable();
        if (currentMode == DisplayMode.MOVIES) {
            double average = controller.handleAverageRating();
            Movie averageRow = buildAverageMovieHighlightRow(average);
            setMovieHighlights(FXCollections.observableArrayList(averageRow));
        } else {
            double average = controller.handleAverageSeriesRating();
            Series averageRow = buildAverageSeriesHighlightRow(average);
            setSeriesHighlights(FXCollections.observableArrayList(averageRow));
        }

    }

    @FXML
    void handleShowMovies(ActionEvent event) {
        currentMode = DisplayMode.MOVIES;
        setMovieInfoLabels();
        refreshTable();
    }

    @FXML
    void handleShowSeries(ActionEvent event) {
        currentMode = DisplayMode.SERIES;
        setSeriesInfoLabels();
        refreshTable();
    }

    @FXML
    private void setMovieInfoLabels() {
        infoCreatorDirector.setText("DIRECTOR");
        infoSeasonsCertifications.setText("CERTIFICATION");
        infoEpisodesGross.setText("GROSS EARNINGS");
    }

    @FXML
    private void setSeriesInfoLabels() {
        infoCreatorDirector.setText("CREATOR");
        infoSeasonsCertifications.setText("NUMBER OF SEASONS");
        infoEpisodesGross.setText("NUMBER OF EPISODES");
    }
}
