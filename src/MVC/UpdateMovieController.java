package MVC;

import MVC.Model.MovieModel;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;

import java.io.IOException;

public class UpdateMovieController
{
    MovieModel movieModel = new MovieModel();
    @FXML
    Slider slider = new Slider();

    public UpdateMovieController() throws IOException {
    }

    public void updateNumber(){
        float rating = (float) slider.getValue();
        movieModel.updateMovie();
    }
}
