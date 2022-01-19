package MVC.Model;

import BE.Category;
import BE.Movie;
import BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class MovieModel
{
    private MovieManager movieManager = new MovieManager();
    private ObservableList<Movie> movieList;
    private List<Movie> sortedMovies;

    public MovieModel() throws IOException
    {
        movieList = FXCollections.observableArrayList();
    }

   public ObservableList<Movie> getObservableMovie()
   {
       movieList.clear();
       movieList.addAll(movieManager.getAllMovies());

       return movieList;
   }

   public void CategorySort(Category category)
   {
       List<Movie> allMovies = movieManager.getAllMovies();
       sortedMovies = movieManager.searchMovies(category.getName(), allMovies);
       movieList.clear();
       movieList.addAll(sortedMovies);
   }

    public Movie createMovie(String title, float movieRating, String url, Timestamp lastView) throws SQLException {
        return movieManager.createMovie(title, movieRating, url, lastView);
    }
    public void deleteMovie(Movie movie){
        movieManager.deleteMovie(movie);
    }

    public void searchMovie(String query) throws SQLException {
        List<Movie> searchResults = movieManager.searchMovies(query, sortedMovies);
        movieList.clear();
        movieList.addAll(searchResults);
    }

    public void updateMovie(Movie movie){
        movieManager.updateMovie(movie);
        System.out.println(movie.getMovieRating());
    }
}
