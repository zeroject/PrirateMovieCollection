package MVC;

import MVC.Model.CategoryModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class CreateCategoryController
{

    @FXML
    private TextField textFieldName;

    private CategoryModel categoryModel;

    //Constructor
    public CreateCategoryController(){
        try {
            categoryModel = new CategoryModel();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "" + e, ButtonType.OK);
            alert.setHeaderText("Ohh no an Error happend : Error:0x001");
            alert.showAndWait();
        }
    }

    /*
    * Function thats calls on a function in CategoryModel that needs a String as a paremeter.
     */
    public void createCategory(){
        try {
            categoryModel.createCategory(textFieldName.getText());
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "" + e, ButtonType.OK);
            alert.setHeaderText("Ohh no an Error happend : Error:0x002");
            alert.showAndWait();
        }
    }
}
