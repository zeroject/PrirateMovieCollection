package MVC.Model;

import BE.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MovieModel
{
    private ObservableList<Movie> movieList;

    public MovieModel(){
        movieList = FXCollections.observableArrayList();
    }
}
