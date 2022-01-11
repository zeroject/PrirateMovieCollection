package MVC;

import BE.Movie;
import BLL.MovieManager;
import MVC.Model.ParseModel;
import MVC.Model.MovieModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ChooseMovieController implements Initializable
{
    @FXML
    private TableView<Movie> tableView;
    @FXML
    private TableColumn column;
    @FXML
    private TextField searchTextField;

    private MovieModel movieModel;

    public ChooseMovieController() throws IOException
    {
        column = new TableColumn<MovieManager, String>();
        tableView = new TableView<>();
        movieModel = new MovieModel();
    }



    @Override public void initialize(URL location, ResourceBundle resources)
    {
        column.setCellValueFactory(new PropertyValueFactory<MovieManager, String>("Title"));
        try
        {
			tableView.setItems(movieModel.getObservableMovie());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void movieSelected() throws IOException
    {
        if (ParseModel.isDELETING == true){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure, you want to delete?", ButtonType.YES, ButtonType.CANCEL);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES){
                movieModel.deleteMovie(tableView.getSelectionModel().getSelectedItem());
            }
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("View/UpdateMovie.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println(stage + " Laoded and the scene : " + scene + "has loaded");
        }
    }
}
