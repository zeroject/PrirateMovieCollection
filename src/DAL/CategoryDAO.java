package DAL;

import BE.Category;
import BE.Movie;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private DatabaseDAO connection;

    public CategoryDAO() throws IOException {
        connection = new DatabaseDAO();
    }

    public List<Category> getAllCategories() {

        ArrayList<Category> categories = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM Categories;";
            Statement statement = conn.createStatement();

            if (statement.execute(sql)){
                ResultSet rs = statement.getResultSet();
                while (rs.next()){
                    int id = rs.getInt("CategoryId");
                    String name = rs.getString("CategoryName");

                    Category category = new Category(id, name);
                    categories.add(category);
                }
            }

        }catch (SQLException throwable){
            throwable.printStackTrace();
        }
        return categories;
    }

    public Category createCategory(String name) throws SQLException {
        try (Connection conn = connection.getConnection()){
            String sql = "INSERT INTO Categories(CategoryName) values (?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();

                int id = 0;
                if(rs.next()){
                    id = rs.getInt(1);
                }

                Category category = new Category(id, name);
                return category;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public void deleteCategory(Category category){
        try(Connection conn = connection.getConnection()){
            String sql = "DELETE FROM Categories WHERE CategoryId =?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, category.getId());
            if(preparedStatement.executeUpdate() != 1){
                throw new Exception("Could not delete Category");
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
