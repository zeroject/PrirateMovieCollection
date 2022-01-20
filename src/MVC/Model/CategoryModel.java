package MVC.Model;

import BE.Category;
import BLL.CategoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class    CategoryModel {

    private CategoryManager categoryManager;
    private ObservableList<Category> categoryList;

    public CategoryModel() throws IOException {
        categoryManager = new CategoryManager();
        categoryList = FXCollections.observableArrayList();
    }

    /**
     * updates the caregory list
     * @return ObservableList
     */
    public ObservableList<Category> getObservableCategory()
    {
        categoryList.clear();
        categoryList.addAll(categoryManager.getAllCategories());
        return categoryList;
    }

    /**
     * binds a movie to a category one or more.
     * @param movieId
     * @param categoryId
     */
    public void insertCategoryIntoMovie(int movieId, int categoryId){
        categoryManager.insertCategoryIntoMovie(movieId, categoryId);
    }

    /**
     * Calls on categoryManager to create a category with the given name.
     * @param name
     * @throws SQLException
     */
    public void createCategory(String name) throws SQLException {
        categoryManager.createCategory(name);
    }

    /**
     * calls on the categoryManager to delete the given category.
     * @param category
     */
    public void deleteCategory(Category category){
        categoryManager.deleteCategory(category);
    }

}
