package MVC;

import BE.Category;
import BE.Movie;
import BLL.MovieManager;
import MVC.Model.CategoryModel;
import MVC.Model.ParseModel;
import MVC.Model.MovieModel;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable
{
    MovieModel movieModel;
    CategoryModel categoryModel;


    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TableView<Movie> movieTableView;
    @FXML
    private TableColumn movieColumn;
    @FXML
    private TableColumn ratingColumn;
    @FXML
    private TableColumn lastViewColumn;
    @FXML
    private TextField searchTextField;
    @FXML
    private ComboBox<Category> comboBox;


    public MainWindowController(){
        try {
            movieColumn = new TableColumn<MovieManager, String>();
            ratingColumn = new TableColumn<MovieManager, Float>();
            lastViewColumn = new TableColumn<MovieManager, Timestamp>();
            movieTableView = new TableView<>();
            movieModel = new MovieModel();
            categoryModel = new CategoryModel();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "" + e, ButtonType.OK);
            alert.setHeaderText("Ohh no an Error happend : Error:0x006");
            alert.showAndWait();
        }
    }



    @Override public void initialize(URL location, ResourceBundle resources)
    {
        movieColumn.setCellValueFactory(new PropertyValueFactory<MovieManager, String>("Title"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<MovieManager, Float>("MovieRating"));
        lastViewColumn.setCellValueFactory(new PropertyValueFactory<MovieManager, Timestamp>("LastView"));
        try
        {
            movieTableView.setItems(movieModel.getObservableMovie());
        } catch (Exception e){
            e.printStackTrace();
        }
        searchTextField.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            try {
                movieModel.searchMovie(newValue);
            } catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "" + e, ButtonType.OK);
                alert.setHeaderText("Ohh no an Error happend : Error:0x007");
                e.printStackTrace();
                alert.showAndWait();
            }
        }));

        comboBox.setItems(categoryModel.getObservableCategory());
    }

    public void refreshTable() {
        try
        {
            movieTableView.getItems().clear();
            movieTableView.setItems(movieModel.getObservableMovie());
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "" + e, ButtonType.OK);
            alert.setHeaderText("Ohh no an Error happend : Error:0x008");
            alert.showAndWait();
        }
    }

    public void createCategoryScene(){
        createScenes("View/CreateCategory.fxml",  false);
    }
    public void deleteCategory(){
        categoryModel.deleteCategory(comboBox.getSelectionModel().getSelectedItem());
        comboBox.getItems().remove(comboBox.getSelectionModel().getSelectedItem());
    }

    public void createMovieScene()
    {
        createScenes("View/CreateMovie.fxml",  false);
    }

    public void movieScene()
    {
        java.sql.Timestamp currentTime = new java.sql.Timestamp(System.currentTimeMillis());
        Movie movie = movieTableView.getSelectionModel().getSelectedItem();
        System.out.println(movie.getTitle());
        Movie tempMovie = new Movie(movie.getId(), movie.getTitle(), movie.getMovieRating(), movie.getUrl(), currentTime);
        System.out.println(tempMovie.getLastView());
        movieModel.updateMovie(tempMovie);

        ParseModel.movieURL = movieTableView.getSelectionModel().getSelectedItem().getUrl();
        createScenes("View/MoviesScene.fxml",  true);
    }
    public void updateMovie()
    {
        ParseModel.tempMovie = movieTableView.getSelectionModel().getSelectedItem();
        if (ParseModel.tempMovie != null){
            createScenes("View/UpdateMovie.fxml", false);
        } else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please Select a Movie first!", ButtonType.OK);
            alert.showAndWait();
        }
    }
    public void chooseMovieSceneDEL()
    {
        if (movieTableView.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please Select a Movie first!", ButtonType.OK);
            alert.showAndWait();
        } else {
            movieModel.deleteMovie(movieTableView.getSelectionModel().getSelectedItem());
            refreshTable();
        }
    }

    private void createScenes(String fxmlPlace, boolean undecorated)
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPlace));
            root.getStylesheets().add("file:css/style.css");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            if (undecorated){
                stage.initStyle(StageStyle.UNDECORATED);
                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    }
                });

                //move around here
                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        stage.setX(event.getScreenX() - xOffset);
                        stage.setY(event.getScreenY() - yOffset);
                    }
                });
            }
            stage.show();
            System.out.println(stage + " Loaded and the scene : " + scene + "has loaded");
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "" + e, ButtonType.OK);
            alert.setHeaderText("Ohh no an Error happend : Error:0x009");
            alert.showAndWait();
        }
    }
}
