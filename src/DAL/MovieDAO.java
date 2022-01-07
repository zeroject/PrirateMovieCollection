package DAL;

import BE.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class MovieDAO
{
    private DatabaseDAO connection;

    public MovieDAO() throws IOException {
        connection = new DatabaseDAO();
    }

    public List<Movie> getAllMovies() {

        ArrayList<Movie> movies = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM Movies;";
            Statement statement = conn.createStatement();

            if (statement.execute(sql)){
                ResultSet rs = statement.getResultSet();
                while (rs.next()){
                    int id = rs.getInt("MovieId");
                    String title = rs.getString("MovieTitle");
                    float rating = rs.getFloat("MovieRating");
                    String fileUrl = rs.getString("MovieFile");
                    String imgUrl = rs.getString("MovieImageFile");
                    String lastView = rs.getString("MovieLastView");

                    Movie movie = new Movie(id, title, rating, fileUrl, imgUrl, lastView);
                    movies.add(movie);
                }
            }

        }catch (SQLException throwable){
            throwable.printStackTrace();
        }
        return movies;
    }

    public Movie createMovie(String title, float movieRating, String url, String imgUrl, String lastView) throws SQLException {
        try (Connection conn = connection.getConnection()){
            String sql = "INSERT INTO Movies(MovieTitle, MovieRating, MovieFile, MovieImageFile, MovieLastView) values (?,?,?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                preparedStatement.setString(1, title);
                preparedStatement.setFloat(2, movieRating);
                preparedStatement.setString(3, url);
                preparedStatement.setString(4, imgUrl);
                preparedStatement.setString(5, lastView);
                preparedStatement.addBatch();
                preparedStatement.executeBatch();

                String sql2 = "SELECT TOP(1) * FROM Movies ORDER by ID desc";
                PreparedStatement preparedStmt = conn.prepareStatement(sql2);
                ResultSet rs = preparedStmt.executeQuery();
                int id = 0;
                if(rs.next()){
                    id = rs.getInt(1);
                }

                Movie movie = new Movie(id, title, movieRating, url, imgUrl, lastView);
                return movie;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
    public void updateMovie(Movie movie){
        //TODO Implement
    }
    public void deleteMovie(Movie movie){
        //TODO Implement
    }
}
