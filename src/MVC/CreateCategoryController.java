package MVC;

import MVC.Model.CategoryModel;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class CreateCategoryController
{

    public TextField textFieldName;
    CategoryModel categoryModel;

    public CreateCategoryController() throws IOException {
        categoryModel = new CategoryModel();
    }

    public void createCategory() throws SQLException {
        categoryModel.createCategory(textFieldName.getText());
    }
}
