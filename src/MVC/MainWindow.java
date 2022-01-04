package MVC;

import BE.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow {
    @FXML
    private TableView<Movie> movieTableView;
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
}
