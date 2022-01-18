package MVC;

import BE.Movie;
import BLL.MovieManager;
import MVC.Model.MovieModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StartupWarningController implements Initializable {

    public Button okButton;
    public TableView<Movie> warningTable;
    public TableColumn titleCol;
    public TableColumn ratingCol;
    public TableColumn lastViewCol;

    private MovieModel movieModel;

    public StartupWarningController() throws IOException {
        movieModel = new MovieModel();

        titleCol = new TableColumn<MovieManager, String>();
        ratingCol = new TableColumn<MovieManager, Float>();
        lastViewCol = new TableColumn<MovieManager, Timestamp>();
        warningTable = new TableView<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleCol.setCellValueFactory(new PropertyValueFactory<MovieManager, String>("Title"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<MovieManager, Float>("MovieRating"));
        lastViewCol.setCellValueFactory(new PropertyValueFactory<MovieManager, Timestamp>("LastView"));
        try
        {
            warningTable.setItems(deleteTheseMovies());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void goToMainWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("View/MainWindow.fxml"));
            Stage stage1 = new Stage();
            stage1.setTitle("Pirate Movies");
            stage1.setScene(new Scene(root));
            stage1.show();

            Stage stage2 = (Stage) okButton.getScene().getWindow();
            stage2.close();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "" + e, ButtonType.OK);
            alert.setHeaderText("Ohh no an Error happend");
            alert.showAndWait();
        }
    }

    public ObservableList<Movie> deleteTheseMovies(){
        int twoYears = 730;
        int lowestAllowedRating = 6;
        java.sql.Timestamp currentTime = new java.sql.Timestamp(System.currentTimeMillis());

        ObservableList<Movie> movieList = FXCollections.observableArrayList();
        for (Movie movie : movieModel.getObservableMovie()) {
            long timeDiff = (currentTime.getTime() - movie.getLastView().getTime()) / (1000*60*60*24);
            if (movie.getMovieRating() <= lowestAllowedRating || timeDiff > twoYears){
                movieList.add(movie);
            }
        }
        return movieList;
    }
}
