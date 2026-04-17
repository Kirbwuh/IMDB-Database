package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 * JavaFX controller for the main movie and series screen.
 * Handles table setup, filtering, dialog actions, and display mode switching.
 */
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
    private Button helpBtn;

    @FXML
    private TableColumn<RowEntry, Integer> yearCol;

    /**
     * Initializes the main view after FXML fields have been injected.
     * Sets up table columns, genre filtering, and selection listeners.
     */
    @FXML
    public void initialize() {
        // Connect each table column to the corresponding RowEntry property.
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

        // Build the genre drop-down from the enum so it stays in sync with supported genres.
        ArrayList<String> genreOptions = new ArrayList<>();
        genreOptions.add("All");
        for (Genre genre : Genre.values()) {
            genreOptions.add(genre.toString());
        }
        genreComboBox.setItems(FXCollections.observableArrayList(genreOptions));
        genreComboBox.getSelectionModel().select("All");

        // Re-filter table rows whenever the user changes the genre.
        genreComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldGenre, newGenre) -> {
            applyGenreFilter();
        });

        // Keep the details panel synchronized with the selected table row.
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldMovie, newMovie) -> {
            updateInfoFromSelected();
        });

        // Startup data loading is handled by initializeStartupData().
    }

    /**
     * Controls whether CSV data should be loaded when startup initialization runs.
     *
     * @param loadCsvOnStartup true to load CSV data during startup, false otherwise
     */
    public void setLoadCsvOnStartup(boolean loadCsvOnStartup) {
        this.loadCsvOnStartup = loadCsvOnStartup;
    }

    /**
     * Loads initial data if requested and refreshes the main table and highlights area.
     */
    public void initializeStartupData() {
        if (loadCsvOnStartup) {
            controller.loadMoviesFromCsv();
        }
        refreshTable();
        setMovieHighlights(new ArrayList<>());
    }

    /**
     * Displays the provided movies in the highlights table.
     *
     * @param movies movies to display in the highlights table
     */
    private void setMovieHighlights(List<Movie> movies) {
        highlightsTableView.setItems(FXCollections.observableArrayList(movies));
    }

    /**
     * Displays the provided series entries in the highlights table.
     *
     * @param series series entries to display in the highlights table
     */
    private void setSeriesHighlights(List<Series> series) {
        highlightsTableView.setItems(FXCollections.observableArrayList(series));
    }

    /**
     * Creates a display-only movie row for the average rating result.
     *
     * @param average average movie rating
     * @return movie row containing the rounded average rating
     */
    private Movie buildAverageMovieHighlightRow(double average) {
        double roundedAverage = Math.round(average * 100.0) / 100.0;
        return new Movie("Average Rating", 0, false, "Action", roundedAverage, "Average movie rating", "", 0);
    }

    /**
     * Creates a display-only series row for the average rating result.
     *
     * @param average average series rating
     * @return series row containing the rounded average rating
     */
    private Series buildAverageSeriesHighlightRow(double average) {
        double roundedAverage = Math.round(average * 100.0) / 100.0;
        return new Series("Average Rating", 0, "Action", roundedAverage, "Average series rating", 0, 0, "");
    }

    /**
     * Refreshes the main table using the active display mode.
     */
    private void refreshTable() {
        if (currentMode == DisplayMode.MOVIES) {
            tableView.setItems(FXCollections.observableArrayList(controller.getAllMovies()));
        } else {
            tableView.setItems(FXCollections.observableArrayList(controller.getAllSeries()));
        }
    }

    /**
     * Applies the selected genre filter to the current movie or series list.
     */
    private void applyGenreFilter() {
        String selectedGenre = genreComboBox.getSelectionModel().getSelectedItem();
        // "All" means the full current list should be shown without filtering.
        if (selectedGenre == null || selectedGenre.equalsIgnoreCase("All")) {
            refreshTable();
            return;
        }

        // Filter entries from the active list by the selected enum display text.
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

    /**
     * Gets the currently selected movie or series row from the main table.
     *
     * @return selected row entry, or null when nothing is selected
     */
    private RowEntry getSelected() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    /**
     * Copies the selected row's values into the details labels.
     */
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

    /**
     * Opens the about page dialog.
     *
     * @param event JavaFX action event from the help/about control
     */
    @FXML
    void handleAboutPage(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("/view/aboutPage.fxml"))));
            Parent aboutPageBox = loader.load();

            // Alert popup with choices
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("about page");
            alert.getDialogPane().setContent(aboutPageBox);
            alert.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
            alert.showAndWait();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the add dialog for the active mode.
     * In movie mode this creates a movie, while series mode delegates to {@link #handleAddSeries(ActionEvent)}.
     *
     * @param event JavaFX action event from the add button
     */
    @FXML
    void handleAddMovie(ActionEvent event) {
        if (currentMode == DisplayMode.SERIES) {
            handleAddSeries(event);
            return;
        }

        try {
            // Load the movie dialog layout and keep the loader so its named controls can be read.
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/addMovieDialog.fxml")));
            Parent addMovieBox = loader.load();

            // Get each input field from the dialog namespace by the fx:id in the FXML.
            TextField titleField = (TextField) loader.getNamespace().get("titleField");
            TextField yearField = (TextField) loader.getNamespace().get("yearField");
            TextField certificationField = (TextField) loader.getNamespace().get("certificationField");
            TextField ratingField = (TextField) loader.getNamespace().get("ratingField");
            ComboBox<String> addMovieGenreBox = (ComboBox<String>) loader.getNamespace().get("genreField");
            TextField descriptionField = (TextField) loader.getNamespace().get("descriptionField");
            TextField directorField = (TextField) loader.getNamespace().get("directorField");
            TextField grossField = (TextField) loader.getNamespace().get("grossField");

            // Populate genre choices from the enum before the dialog is shown.
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
                // Validate the form before the alert closes; consuming the event keeps it open.
                ArrayList<String> movieEntries = buildValidatedEntry(
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

    /**
     * Opens the add series dialog, validates the input, and adds the new series.
     *
     * @param event JavaFX action event from the add button
     */
    @FXML
    void handleAddSeries(ActionEvent event) {
        try {
            // Load the series dialog layout and keep the loader so its named controls can be read.
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/addSeriesDialog.fxml")));
            Parent addSeriesBox = loader.load();

            // Get each input field from the dialog namespace by the fx:id in the FXML.
            TextField titleField = (TextField) loader.getNamespace().get("titleField");
            TextField yearField = (TextField) loader.getNamespace().get("yearField");
            TextField seasonsField = (TextField) loader.getNamespace().get("seasonsField");
            TextField ratingField = (TextField) loader.getNamespace().get("ratingField");
            ComboBox<String> addSeriesGenreBox = (ComboBox<String>) loader.getNamespace().get("genreField");
            TextField descriptionField = (TextField) loader.getNamespace().get("descriptionField");
            TextField creatorField = (TextField) loader.getNamespace().get("creatorField");
            TextField episodesField = (TextField) loader.getNamespace().get("episodesField");

            // Populate genre choices from the enum before the dialog is shown.
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
                // Validate the form before the alert closes; consuming the event keeps it open.
                ArrayList<String> seriesEntries = buildValidatedEntry(
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

    /**
     * Validates add/edit dialog input and converts it to the string field order expected by the controller.
     * The third, seventh, and eighth fields have different meanings for movies and series.
     *
     * @param titleField title input field
     * @param yearField year input field
     * @param certificationOrSeasonsField certification field for movies or seasons field for series
     * @param genreField genre combo box
     * @param ratingField IMDB rating input field
     * @param descriptionField description input field
     * @param directorOrCreatorField director field for movies or creator field for series
     * @param grossOrEpisodesField gross earnings field for movies or episodes field for series
     * @return validated values in controller field order, or null when validation fails
     */
    private ArrayList<String> buildValidatedEntry(
            TextField titleField,
            TextField yearField,
            TextField certificationOrSeasonsField,
            ComboBox<String> genreField,
            TextField ratingField,
            TextField descriptionField,
            TextField directorOrCreatorField,
            TextField grossOrEpisodesField
    ) {
        // Normalize user-entered values once so validation and saved values match.
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

        // Shared fields are validated first because both movies and series require them.
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

        // Mode-specific fields are validated separately because their meanings differ.
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

        // Show every validation problem at once so the user can fix the full form.
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
        // Keep this order aligned with Controller.stringToSeries and Controller.stringToMovie.
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

    /**
     * Opens the edit dialog for the selected entry.
     * In movie mode this edits a movie, while series mode delegates to {@link #handleEditSeries(ActionEvent)}.
     *
     * @param event JavaFX action event from the edit button
     */
    @FXML
    void handleEditMovie(ActionEvent event) {
        if (currentMode == DisplayMode.SERIES) {
            handleEditSeries(event);
            return;
        }

        RowEntry entry = getSelected();
        if (entry == null) {
            return;
        }

        try {
            // Load the edit movie dialog and keep the loader for access to FXML controls.
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/editMovieDialog.fxml")));
            Parent editMovieBox = loader.load();

            // Get each editable field by the fx:id defined in the dialog FXML.
            TextField titleField = (TextField) loader.getNamespace().get("titleField");
            TextField yearField = (TextField) loader.getNamespace().get("yearField");
            TextField certificationField = (TextField) loader.getNamespace().get("certificationField");
            TextField ratingField = (TextField) loader.getNamespace().get("ratingField");
            ComboBox<String> editMovieGenreBox = (ComboBox<String>) loader.getNamespace().get("genreField");
            TextField descriptionField = (TextField) loader.getNamespace().get("descriptionField");
            TextField directorField = (TextField) loader.getNamespace().get("directorField");
            TextField grossField = (TextField) loader.getNamespace().get("grossField");

            // Populate genre choices before selecting the existing movie genre.
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

            // Save the original title because Controller updates look up the row by title.
            String oldMovieTitle = entry.getTitle();
            Button applyButton = (Button) alert.getDialogPane().lookupButton(ButtonType.APPLY);
            applyButton.addEventFilter(ActionEvent.ACTION, applyEvent -> {
                // Validate before applying changes; consuming the event leaves the dialog open.
                ArrayList<String> movieEntries = buildValidatedEntry(
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

                // Update all title-dependent fields first, then update the title last.
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

                // Reapply the active filter and reselect the edited row for the details panel.
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

    /**
     * Opens the edit series dialog for the selected series and applies validated changes.
     *
     * @param event JavaFX action event from the edit button
     */
    @FXML
    void handleEditSeries(ActionEvent event) {
        RowEntry entry = getSelected();
        if (entry == null) {
            return;
        }

        try {
            // Load the edit series dialog and keep the loader for access to FXML controls.
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/editSeriesDialog.fxml")));
            Parent editSeriesBox = loader.load();

            // Get each editable field by the fx:id defined in the dialog FXML.
            TextField titleField = (TextField) loader.getNamespace().get("titleField");
            TextField yearField = (TextField) loader.getNamespace().get("yearField");
            TextField seasonsField = (TextField) loader.getNamespace().get("seasonsField");
            TextField ratingField = (TextField) loader.getNamespace().get("ratingField");
            ComboBox<String> editSeriesGenreBox = (ComboBox<String>) loader.getNamespace().get("genreField");
            TextField descriptionField = (TextField) loader.getNamespace().get("descriptionField");
            TextField creatorField = (TextField) loader.getNamespace().get("creatorField");
            TextField episodesField = (TextField) loader.getNamespace().get("episodesField");

            // Populate genre choices before selecting the existing series genre.
            ArrayList<String> editSeriesGenreOptions = new ArrayList<>();
            for (Genre genre : Genre.values()) {
                editSeriesGenreOptions.add(genre.toString());
            }

            editSeriesGenreBox.setItems(FXCollections.observableArrayList(editSeriesGenreOptions));
            editSeriesGenreBox.setPromptText("Genre");

            // Pre-fill selected series values
            titleField.setText(entry.getTitle());
            yearField.setText(String.valueOf(entry.getYear()));
            editSeriesGenreBox.getSelectionModel().select(entry.getGenre());
            ratingField.setText(String.valueOf(entry.getImdbRating()));
            descriptionField.setText(entry.getDescription());
            if (entry instanceof Series series) {
                seasonsField.setText(String.valueOf(series.getNumberOfSeasons()));
                creatorField.setText(series.getCreator());
                episodesField.setText(String.valueOf(series.getNumberOfEpisodes()));
            }

            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Edit Series");
            alert.getDialogPane().setContent(editSeriesBox);
            alert.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CLOSE);

            // Save the original title because Controller updates look up the row by title.
            String oldSeriesTitle = entry.getTitle();
            Button applyButton = (Button) alert.getDialogPane().lookupButton(ButtonType.APPLY);
            applyButton.addEventFilter(ActionEvent.ACTION, applyEvent -> {
                // Validate before applying changes; consuming the event leaves the dialog open.
                ArrayList<String> seriesEntries = buildValidatedEntry(
                        titleField,
                        yearField,
                        seasonsField,
                        editSeriesGenreBox,
                        ratingField,
                        descriptionField,
                        creatorField,
                        episodesField
                );

                if (seriesEntries == null) {
                    applyEvent.consume();
                    return;
                }

                // Update all title-dependent fields first, then update the title last.
                controller.handleUpdateSeries(2, seriesEntries.get(1), oldSeriesTitle);
                controller.handleUpdateSeries(3, seriesEntries.get(2), oldSeriesTitle);
                controller.handleUpdateSeries(4, seriesEntries.get(3), oldSeriesTitle);
                controller.handleUpdateSeries(5, seriesEntries.get(4), oldSeriesTitle);
                controller.handleUpdateSeries(6, seriesEntries.get(5), oldSeriesTitle);
                controller.handleUpdateSeries(7, seriesEntries.get(6), oldSeriesTitle);
                controller.handleUpdateSeries(8, seriesEntries.get(7), oldSeriesTitle);
                controller.handleUpdateSeries(1, seriesEntries.get(0), oldSeriesTitle);

                // Reapply the active filter and reselect the edited row for the details panel.
                refreshTable();
                applyGenreFilter();
                tableView.refresh();

                for (RowEntry item : tableView.getItems()) {
                    if (item.getTitle().equals(seriesEntries.get(0))) {
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

    /**
     * Loads the CSV file for the active display mode and reapplies the current genre filter.
     *
     * @param event JavaFX action event from the load CSV button
     */
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

    /**
     * Shows the lowest-rated movie or series for the active display mode.
     *
     * @param event JavaFX action event from the lowest-rated button
     */
    @FXML
    void handleLowestRated(ActionEvent event) {
        // Each mode asks the backing controller for its own lowest-rated entry type.
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

        // Selecting the result keeps the details panel aligned with the filtered table.
        tableView.getSelectionModel().selectFirst();
        updateInfoFromSelected();
    }

    /**
     * Removes the selected movie or series from the active data set.
     *
     * @param event JavaFX action event from the remove button
     */
    @FXML
    void handleRemoveMovie(ActionEvent event) {
        RowEntry entry = getSelected();
        if (entry == null) {
            return;
        }

        // Remove based on the concrete row type because movies and series use separate storage.
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
        // Clear the details panel after the selected row is removed.
        infoTitleLabel.setText("—");
        infoYearLabel.setText("—");
        infoGenreLabel.setText("—");
        infoRatingLabel.setText("—");
        infoDirectorLabel.setText("—");
        infoCertLabel.setText("—");
        infoGrossLabel.setText("—");
        infoDescLabel.setText("—");

    }

    /**
     * Saves the active movie or series data set back to CSV.
     *
     * @param event JavaFX action event from the save CSV button
     */
    @FXML
    void handleSaveCSV(ActionEvent event) {
        if (currentMode == DisplayMode.MOVIES) {
            controller.saveAllMoviesToCsv();
        } else {
            controller.saveAllSeriesToCsv();
        }
    }

    /**
     * Searches the active data set by title and displays the matching row.
     *
     * @param event JavaFX action event from the search button
     */
    @FXML
    void handleSearch(ActionEvent event) {
        String rawQuery = searchField.getText();
        String query = rawQuery == null ? "" : rawQuery.trim();

        // Empty searches reset the table to the active genre filter.
        if (query.isEmpty()) {
            applyGenreFilter();
            return;
        }

        // Search only the data type currently visible in the table.
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

    /**
     * Shows the top five movies or series for the active display mode.
     *
     * @param event JavaFX action event from the top five button
     */
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

    /**
     * Shows the highest-rated movie or series for the active display mode.
     *
     * @param event JavaFX action event from the highest-rated button
     */
    @FXML
    void handleHighestRated(ActionEvent event) {
        // Each mode asks the backing controller for its own highest-rated entry type.
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

        // Selecting the result keeps the details panel aligned with the filtered table.
        tableView.getSelectionModel().selectFirst();
        updateInfoFromSelected();
    }

    /**
     * Calculates the average rating for the active display mode and shows it in highlights.
     *
     * @param event JavaFX action event from the average button
     */
    @FXML
    void handleAverage(ActionEvent event) {
        // Average is calculated across all entries, so reset any genre filter first.
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

    /**
     * Switches the main screen to movie mode.
     *
     * @param event JavaFX action event from the movie mode button
     */
    @FXML
    void handleShowMovies(ActionEvent event) {
        currentMode = DisplayMode.MOVIES;
        setMovieInfoLabels();
        refreshTable();
    }

    /**
     * Switches the main screen to series mode.
     *
     * @param event JavaFX action event from the series mode button
     */
    @FXML
    void handleShowSeries(ActionEvent event) {
        currentMode = DisplayMode.SERIES;
        setSeriesInfoLabels();
        refreshTable();
    }

    /**
     * Updates the details labels for movie-specific fields.
     */
    @FXML
    private void setMovieInfoLabels() {
        infoCreatorDirector.setText("DIRECTOR");
        infoSeasonsCertifications.setText("CERTIFICATION");
        infoEpisodesGross.setText("GROSS EARNINGS");
    }

    /**
     * Updates the details labels for series-specific fields.
     */
    @FXML
    private void setSeriesInfoLabels() {
        infoCreatorDirector.setText("CREATOR");
        infoSeasonsCertifications.setText("NUMBER OF SEASONS");
        infoEpisodesGross.setText("NUMBER OF EPISODES");
    }
}
