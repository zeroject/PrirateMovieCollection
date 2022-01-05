package MVC.Model;

import BE.Movie;
import BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
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

   public ObservableList<Movie> listToObservableList(){
        List<Movie> tempMovieList = new ArrayList<Movie>();
        tempMovieList = this.movieManager.getAllMovies();
        for(Movie movie : tempMovieList){
            movieList.add(movie);
        }
        return movieList;
    }

    public void createMovie(String title, float movieRating, String url, String imgUrl){
        movieManager.createMovie(title, movieRating, url, imgUrl);
    }
    public void deleteSong(Movie movie){
        movieManager.deleteMovie(movie);
    }

    public void searchMovie(String query){
        List<Movie> searchResults = movieManager.searchMovies(query);
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
