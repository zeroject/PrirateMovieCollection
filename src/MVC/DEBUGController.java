package MVC;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/*
 *                       **WARNING DELETE THIS BEFORE HANDING IN**
 *                        **DEBUG CONSOLE USED FROM Cool IT Help.
 * **LINK - https://www.coolithelp.com/2020/06/javafx-redirect-console-output-to.html - LINK**
 */
public class DEBUGController implements Initializable
{
    @FXML
    private TextArea textAreaUI;

    static TextArea staticTxtArea;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        // these will be redirected to textArea on GUI

        System.err.println("@@@@ERROR: This is error");
        System.out.println("####OUTPUT : THIS IS ERROR");
        //generating an exception to print on console
        try
        {
            int x  = 5/0;

        }
        catch(Exception ex)
        {
            ex.printStackTrace();

            // System.out.println(ex.getMessage());

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        staticTxtArea = textAreaUI;
    }

    @FXML private javafx.scene.control.Button closeButton;

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
