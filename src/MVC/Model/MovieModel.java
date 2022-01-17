package MVC.Model;

import BE.Movie;
import BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MovieModel
{
    private MovieManager movieManager = new MovieManager();
    private ObservableList<Movie> movieList;

    public MovieModel() throws IOException
    {
        movieList = FXCollections.observableArrayList();
    }

   public ObservableList<Movie> getObservableMovie()
   {
       movieList.clear();
       movieList.addAll(movieManager.getAllMovies());
       for (Movie movie : movieList)
       {
           System.out.println(movie.getCategories().toString()+2);
       }
       return movieList;
   }

    public Movie createMovie(String title, float movieRating, String url, String lastView) throws SQLException {
        return movieManager.createMovie(title, movieRating, url, lastView);
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


    public void updateMovie(Movie movie){
        movieManager.updateMovie(movie);
    }
}
