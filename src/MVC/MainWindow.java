package MVC;

import BE.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainWindow implements Initializable
{
    @FXML
    private TableView<Movie> movieTableView;
    @FXML
    private TableColumn movieColumn;
    @FXML
    private TextField searchTextField;
    @FXML
    private ComboBox comboBox;

    public MainWindow(){

    }

    public void debugConsole() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/Debug Console.fxml"));
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {
        movieColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("Movie"));
        try
        {
            movieTableView.setItems();
        }catch (IOException | SQLException e){
            e.printStackTrace();
        }
    }
}
