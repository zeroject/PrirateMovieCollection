package MVC.Model;

import BE.Category;
import BLL.CategoryManager;

import java.io.IOException;
import java.sql.SQLException;

public class CategoryModel {

    CategoryManager categoryManager;

    public CategoryModel() throws IOException {
        categoryManager = new CategoryManager();
    }

    public void createCategory(String name) throws SQLException {
        categoryManager.createCategory(name);
    }

    public void deleteCategory(Category category){
        categoryManager.deleteCategory(category);
    }

}
