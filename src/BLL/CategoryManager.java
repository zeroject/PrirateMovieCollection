package BLL;

import BE.Category;
import DAL.CategoryDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CategoryManager {

    CategoryDAO categoryDAO;

    public CategoryManager() throws IOException {
        categoryDAO = new CategoryDAO();
    }

    public List<Category> getAllCategories(){
        return categoryDAO.getAllCategories();
    }

    public void createCategory(String name) throws SQLException {
        categoryDAO.createCategory(name);
    }

    public void deleteCategory(Category category){
        categoryDAO.deleteCategory(category);
    }

}
