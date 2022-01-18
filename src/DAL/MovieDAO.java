package DAL;

import BE.Category;
import BE.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;

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
                    Timestamp lastView = rs.getTimestamp("MovieLastView");

                    Movie movie = new Movie(id, title, rating, fileUrl, lastView);
                    movies.add(movie);
                }
            }

        }catch (SQLException throwable){
            throwable.printStackTrace();
        }
        return movies;
    }

    public Movie createMovie(String title, float movieRating, String url, Timestamp lastView) throws SQLException {
        try (Connection conn = connection.getConnection()){
            String sql = "INSERT INTO Movies(MovieTitle, MovieRating, MovieFile, MovieLastView) values (?,?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, title);
                preparedStatement.setFloat(2, movieRating);
                preparedStatement.setString(3, url);
                preparedStatement.setTimestamp(4, lastView);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();

                int id = 0;
                if(rs.next()){
                    id = rs.getInt(1);
                }

                Movie movie = new Movie(id, title, movieRating, url, lastView);
                return movie;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public void updateMovie(Movie movie){
        try(Connection conn = connection.getConnection()){
            String sql = "UPDATE Movies SET MovieTitle=?, MovieRating=?, MovieFile=? WHERE MovieId=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setFloat(2, movie.getMovieRating());
            preparedStatement.setString(3, movie.getUrl());
            preparedStatement.setInt(4, movie.getId());
            if(preparedStatement.executeUpdate() != 1){
                throw new Exception("Could not update Movie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMovie(Movie movie){
        try(Connection conn = connection.getConnection()){
            String sql1 = "DELETE FROM CatMovie WHERE MovieId =?;";
            String sql2 = "DELETE FROM Movies WHERE MovieId =?;";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.setInt(1, movie.getId());
            preparedStatement1.execute();

            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.setInt(1, movie.getId());
            preparedStatement2.execute();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
