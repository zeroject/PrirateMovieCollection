import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Date;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MVC/View/MainWindow.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Pirate Movies");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(true);
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
