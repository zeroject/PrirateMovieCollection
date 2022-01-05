package MVC.Model;

import BE.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class MovieModel
{
    private ObservableList<Movie> movieList;

    public MovieModel(){
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

    public void createMovie(String title, String )
}
