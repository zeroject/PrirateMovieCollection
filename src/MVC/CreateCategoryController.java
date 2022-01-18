package MVC;

import MVC.Model.CategoryModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class CreateCategoryController
{

    @FXML
    private TextField textFieldName;

    private CategoryModel categoryModel;

    public CreateCategoryController(){
        try {
            categoryModel = new CategoryModel();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "" + e, ButtonType.OK);
            alert.setHeaderText("Ohh no an Error happend");
            alert.showAndWait();
        }
    }

    public void createCategory(){
        try {
            categoryModel.createCategory(textFieldName.getText());
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "" + e, ButtonType.OK);
            alert.setHeaderText("Ohh no an Error happend");
            alert.showAndWait();
        }
    }
}
