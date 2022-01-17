package MVC;

import MVC.Model.CategoryModel;
import javafx.fxml.FXML;
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
        } catch (IOException e){

        }
    }

    public void createCategory(){
        try {
            categoryModel.createCategory(textFieldName.getText());
        } catch (SQLException e){

        }
    }
}
