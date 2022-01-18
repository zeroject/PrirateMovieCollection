package MVC;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class StartupWarningController {

    public Button okButton;

    public StartupWarningController(){

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
}
