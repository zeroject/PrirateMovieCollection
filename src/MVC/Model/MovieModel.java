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

    /**
     * updates the list with all the movies.
     * @return
     */
   public ObservableList<Movie> getObservableMovie()
   {
       movieList.clear();
       movieList.addAll(movieManager.getAllMovies());

       return movieList;
   }

    /**
     * sorts the movies after the given category
     * @param category
     */
   public void CategorySort(Category category)
   {
       List<Movie> allMovies = movieManager.getAllMovies();
       sortedMovies = movieManager.searchMovies(category.getName(), allMovies);
       movieList.clear();
       movieList.addAll(sortedMovies);
   }

    /**
     * calls a function in the moviemanger to create a movie with the given parameters.
     * @param title
     * @param movieRating
     * @param url
     * @param lastView
     * @return
     * @throws SQLException
     */
    public Movie createMovie(String title, float movieRating, String url, Timestamp lastView) throws SQLException {
        return movieManager.createMovie(title, movieRating, url, lastView);
    }

    /**
     * calls a function in moviemanager to delete the given movie from the database.
     * @param movie
     */
    public void deleteMovie(Movie movie){
        movieManager.deleteMovie(movie);
    }

    /**
     * updates the list with movies that match the user has searched after.
     * @param query
     */
    public void searchMovie(String query) {
        List<Movie> searchResults = movieManager.searchMovies(query, sortedMovies);
        movieList.clear();
        movieList.addAll(searchResults);
    }

    /**
     * calls a function in moviemanager update the given movie.
     * @param movie
     */
    public void updateMovie(Movie movie){
        movieManager.updateMovie(movie);
        System.out.println(movie.getMovieRating());
    }
}
