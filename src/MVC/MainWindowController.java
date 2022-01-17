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
    private TableColumn catColumn;
    @FXML
    private TableColumn ratingColumn;
    @FXML
    private TextField searchTextField;
    @FXML
    private ComboBox<Category> comboBox;


    public MainWindowController() throws IOException{
        movieColumn = new TableColumn<MovieManager, String>();
        catColumn = new TableColumn<MovieManager, List<Category>>();
        ratingColumn = new TableColumn<MovieManager, Float>();
        movieTableView = new TableView<>();
        movieModel = new MovieModel();
        categoryModel = new CategoryModel();
    }



    @Override public void initialize(URL location, ResourceBundle resources)
    {
        movieColumn.setCellValueFactory(new PropertyValueFactory<MovieManager, String>("Title"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<MovieManager, Float>("MovieRating"));
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
            } catch (SQLException throwables) {
                throwables.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public void createCategoryScene() throws IOException {
        createScenes("View/CreateCategory.fxml",  false);
    }
    public void deleteCategory(){
        categoryModel.deleteCategory(comboBox.getSelectionModel().getSelectedItem());
        comboBox.getItems().remove(comboBox.getSelectionModel().getSelectedItem());
    }

    public void createMovieScene() throws IOException
    {
        createScenes("View/CreateMovie.fxml",  false);
    }

    public void movieScene() throws IOException
    {
        ParseModel.movieURL = movieTableView.getSelectionModel().getSelectedItem().getUrl();
        createScenes("View/MovieScene.fxml",  true);
    }
    public void updateMovie() throws IOException
    {
        ParseModel.tempMovie = movieTableView.getSelectionModel().getSelectedItem();
        createScenes("View/UpdateMovie.fxml", false);
    }
    public void chooseMovieSceneDEL()
    {
        movieModel.deleteMovie(movieTableView.getSelectionModel().getSelectedItem());
    }

    private void createScenes(String fxmlPlace, boolean undecorated) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPlace));
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
    }
}
