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
import java.util.ResourceBundle;

public class MainWindowController implements Initializable
{
    MovieModel movieModel;
    CategoryModel categoryModel;

    @FXML
    private TableView<Movie> movieTableView;
    @FXML
    private TableColumn movieColumn;
    @FXML
    private TextField searchTextField;
    @FXML
    private ComboBox<Category> comboBox;


    public MainWindowController() throws IOException{
        movieColumn = new TableColumn<MovieManager, String>();
        movieTableView = new TableView<>();
        movieModel = new MovieModel();
        categoryModel = new CategoryModel();
    }



    @Override public void initialize(URL location, ResourceBundle resources)
    {
        movieColumn.setCellValueFactory(new PropertyValueFactory<MovieManager, String>("Title"));
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
        Parent root = FXMLLoader.load(getClass().getResource("View/Create Category.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void deleteCategory(){
        categoryModel.deleteCategory(comboBox.getSelectionModel().getSelectedItem());
        comboBox.getItems().remove(comboBox.getSelectionModel().getSelectedItem());
    }

    public void createMovieScene() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("View/Create Movie.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(stage + " Laoded and the scene : " + scene + "has loaded");
    }

    public void movieScene() throws IOException
    {
        ParseModel.movieURL = movieTableView.getSelectionModel().getSelectedItem().getUrl();
        Parent root = FXMLLoader.load(getClass().getResource("View/MovieScene.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreen(true);
        stage.show();
        System.out.println(stage + " Laoded and the scene : " + scene + "has loaded");
    }
    public void chooseMovieScene() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("View/UpdateMovie.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(stage + " Laoded and the scene : " + scene + "has loaded");
    }
    public void chooseMovieSceneDEL()
    {
        movieModel.deleteMovie(movieTableView.getSelectionModel().getSelectedItem());
    }
}
