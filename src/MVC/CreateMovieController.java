package MVC;

import BE.Category;
import BE.Movie;
import MVC.Model.CategoryModel;
import MVC.Model.MovieModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateMovieController implements Initializable {

    @FXML
    private TextField textFieldTitle;
    @FXML
    private Slider sliderRating;
    @FXML
    private TextField textFieldUrl;
    @FXML
    private TextField textFieldCategory;
    @FXML
    private ComboBox<Category> categoryCombobox;
    @FXML
    private Button createButton;

    private List<Category> categoryList;

    private MovieModel movieModel;
    private CategoryModel categoryModel;

    public CreateMovieController() throws IOException {
        movieModel = new MovieModel();
        categoryModel = new CategoryModel();
        categoryList = new ArrayList<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoryCombobox.setItems(categoryModel.getObservableCategory());
    }

    public void createMovie() throws SQLException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime systemDate = LocalDateTime.now();
        // Beginning of Error Handling
        if (textFieldTitle.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This movie instance is missing a title", ButtonType.OK);
            alert.showAndWait();
            
        }else if (textFieldUrl.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This movie instance is missing a movie", ButtonType.OK);
            alert.showAndWait();
            
        }else if (textFieldCategory.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This movie instance is missing one or more categories", ButtonType.OK);
            alert.showAndWait();
            
        }
        // End of Error Handling
        else{
            Movie movie = movieModel.createMovie(textFieldTitle.getText(), (float) sliderRating.getValue(), textFieldUrl.getText(), dtf.format(systemDate));

            for (Category category : categoryList) {
                categoryModel.insertCategoryIntoMovie(movie.getId(), category.getId());
            }


            Stage stage = (Stage) createButton.getScene().getWindow();
            stage.close();
        }
    }

    public void addCategoryToMovie(){
        if (!categoryList.contains(categoryCombobox.getSelectionModel().getSelectedItem()) && categoryCombobox.getSelectionModel().getSelectedItem() != null) {
            categoryList.add(categoryCombobox.getSelectionModel().getSelectedItem());
            textFieldCategory.setText(categoryList.toString());

        }else if (!categoryList.contains(categoryCombobox.getSelectionModel().getSelectedItem()) && categoryCombobox.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This movie already contains this category", ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * Opens a file explorer to choose a movie (*.mp4, *.mpeg4)
     */
    public void chooseMovieFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.mpeg4"));
        fileChooser.setInitialDirectory(new File("Movies/"));
        File file = fileChooser.showOpenDialog(null);


        if (file != null){
            textFieldUrl.setText("Movies\\" + file.getName());

            String fileName = file.getName();
            String[] filenames = {".mp4", ".mpeg4"};
            for (String words: filenames){

                fileName = fileName.replace(words, "".repeat(words.length()));
            }
            textFieldTitle.setText(fileName);
        }
    }
}
