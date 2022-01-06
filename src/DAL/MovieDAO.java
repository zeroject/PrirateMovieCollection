package DAL;

import BE.Movie;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.List;

public class MovieDAO
{
    private DatabaseDAO connection;

    public MovieDAO() throws IOException {
        connection = new DatabaseDAO();
    }

    public List<Movie> getAllMovies() throws SQLException {

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
                    Date lastView = rs.getDate("MovieLastView");

                    Movie movie = new Movie(id, title, rating, fileUrl, imgUrl, lastView);
                    movies.add(movie);
                }
            }

        }catch (SQLException throwable){
            throwable.printStackTrace();
        }
        return movies;
    }

    public void createMovie(String title, float movieRating, String url, String imgUrl){
        //TODO Implement Create movie
    }
    public void updateMovie(Movie movie){
        //TODO Implement
    }
    public void deleteMovie(Movie movie){
        //TODO Implement
    }
}
