package DAL;

import BE.Movie;
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

    /**
     * In this method we extract all movies from the movieTable in the database to the program with the help of the SQL command SELECT*
     * @return an arraylist of all movies
     */
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
            throwable.getNextException();
        }
        return movies;
    }

    /**
     * Uses the SQL command INSERT INTO to create a new movie in the database table movies
     * @param title of the specific movie
     * @param movieRating of the movie
     * @param url the filepath to the song .mp3 or .wav file
     * @param lastView the last time the movie was watched or updated
     * @return
     * @throws SQLException
     */
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
                throwables.getNextException();
            }
        }
        return null;
    }

    /**
     * takes every parameter of an already existing movie object and then sets the new values into an updated movie
     * @param movie returns the value of the updated movie
     */
    public void updateMovie(Movie movie){
        try(Connection conn = connection.getConnection()){
            String sql = "UPDATE Movies SET MovieTitle=?, MovieRating=?, MovieFile=?, MovieLastView=? WHERE MovieId=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setFloat(2, movie.getMovieRating());
            preparedStatement.setString(3, movie.getUrl());
            preparedStatement.setTimestamp(4, movie.getLastView());
            preparedStatement.setInt(5, movie.getId());
            if(preparedStatement.executeUpdate() != 1){
                throw new Exception("Could not update Movie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * checks where the ID of a specific movie is and then deletes the movie from first the catMovie table and then the movies table
     * @param movie song to be deleted
     */
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
