package MVC.Model;

import BE.Movie;
import BLL.MovieManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieModel
{
    private MovieManager movieManager = new MovieManager();
    private ObservableList<Movie> movieList;

    public MovieModel() throws IOException
    {
        movieList = FXCollections.observableArrayList();
    }

   public ObservableList<Movie> getObservableMovie() throws SQLException
   {
       movieList.clear();
       movieList.addAll(movieManager.getAllMovies());
       return movieList;
   }

    public void createMovie(String title, float movieRating, String url, String imgUrl, String lastView) throws SQLException {
        movieManager.createMovie(title, movieRating, url, imgUrl, lastView);
    }
    public void deleteMovie(Movie movie){
        movieManager.deleteMovie(movie);
    }

    public void searchMovie(String query) throws SQLException {
        List<Movie> allMovies = movieManager.getAllMovies();
        List<Movie> searchResults = movieManager.searchMovies(query, allMovies);
        movieList.clear();
        movieList.addAll(searchResults);
    }

    public String movieImgUpdate(Movie movie){
        return movieManager.updateMovieImage(movie);
    }

    public void updateMovie(Movie movie){
        movieManager.updateMovie(movie);
    }
}
