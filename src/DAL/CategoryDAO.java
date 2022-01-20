package DAL;

import BE.Category;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private final DatabaseDAO connection;

    public CategoryDAO() throws IOException {
        connection = new DatabaseDAO();
    }

    /**
     * In this method we extract all categories from the categoryTable in the database to the program with the help of the SQL command SELECT*
     * @return an arraylist of all categories
     */
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

    /**
     * uses the SQL command SELECT * on the catMovie table to extract all categories bound to a specific movieId
     * @param movieId used get get all categories bound to this ID
     * @return a list of categories from a movie
     */
    public List<Category> getAllCategoryMovies(int movieId) {
        List<Category> categoriesInMovie = new ArrayList<>();
        try (Connection conn = connection.getConnection()) {
            String sql = "SELECT * FROM CatMovie WHERE MovieId = ? ORDER BY MovieId";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, movieId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("CategoryId");
                String songSql = "SELECT top(1) * FROM Categories WHERE CategoryId = ?";
                PreparedStatement songPs = conn.prepareStatement(songSql);
                songPs.setInt(1, id);
                ResultSet rSet = songPs.executeQuery();

                rSet.next();
                int Id = rSet.getInt("CategoryId");
                String name = rSet.getString("CategoryName");
                Category category = new Category(Id, name);

                categoriesInMovie.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categoriesInMovie;
    }
    /**
     *Uses the SQL command INSERT INTO to create a new category in the database table Categories
     * @param name the parameter is the name of the category
     */
    public void createCategory(String name) throws SQLException {
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
                new Category(id, name);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * checks where the ID of a specific category is and then deletes the category first from the CatMovie table and then the category table
     * @param category to be deleted
     */
    public void deleteCategory(Category category){
        try(Connection conn = connection.getConnection()){
            String sql1 = "DELETE FROM CatMovie WHERE CategoryId =?;";
            String sql2 = "DELETE FROM Categories WHERE CategoryId =?;";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.setInt(1, category.getId());
            preparedStatement1.execute();

            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.setInt(1, category.getId());
            preparedStatement2.execute();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * adds a song to a playlist using the third table Playlistsongs with the SQL command INSERT INTO
     * @param movieId the id of the movie that it's getting added to
     * @param categoryId the id of the category that is getting added to the movie
     */
    public void addCategoryToMovie(int movieId, int categoryId)
    {
        String sql = "INSERT INTO CatMovie(MovieId, CategoryId) VALUES (?, ?)";
        try (Connection conn = connection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, movieId);
            preparedStatement.setInt(2, categoryId);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
