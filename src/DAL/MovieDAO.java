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
        /*
        ArrayList<Movie> movies = new ArrayList<>();

        try(Connection conn = (Connection) connection){
            String sql = "SELECT * FROM Movies;";
            Statement statement = conn.createStatement();

            if (statement.execute(sql)){
                ResultSet rs = statement.getResultSet();
                while (rs.next()){
                    int id = rs.getInt("Id");
                    String title = rs.getString("Title");

                }
            }

        }catch (SQLException throwable){

        }*/
        return null;
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
