package MVC.Model;

import BE.Category;
import BLL.CategoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class CategoryModel {

    private CategoryManager categoryManager;
    private ObservableList<Category> categoryList;

    public CategoryModel() throws IOException {
        categoryManager = new CategoryManager();
        categoryList = FXCollections.observableArrayList();
    }

    public ObservableList<Category> getObservableCategory()
    {
        categoryList.clear();
        categoryList.addAll(categoryManager.getAllCategories());
        return categoryList;
    }

    public void insertCategoryIntoMovie(int movieId, int categoryId){
        categoryManager.insertCategoryIntoMovie(movieId, categoryId);
    }

    public void createCategory(String name) throws SQLException {
        categoryManager.createCategory(name);
    }

    public void deleteCategory(Category category){
        categoryManager.deleteCategory(category);
    }

}
