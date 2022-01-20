package BLL;

import BE.Category;
import DAL.CategoryDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CategoryManager {

    private CategoryDAO categoryDAO;

    public CategoryManager() throws IOException {
        categoryDAO = new CategoryDAO();
    }

    /**
     * pases methods from CategoryDAO
     * @return
     */
    public List<Category> getAllCategories(){
        return categoryDAO.getAllCategories();
    }

    public void insertCategoryIntoMovie(int movieId, int categoryId){
        categoryDAO.addCategoryToMovie(movieId, categoryId);
    }

    public void createCategory(String name) throws SQLException {
        categoryDAO.createCategory(name);
    }

    public void deleteCategory(Category category){
        categoryDAO.deleteCategory(category);
    }

}
