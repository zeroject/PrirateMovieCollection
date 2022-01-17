package MVC;

import BE.Movie;
import MVC.Model.MovieModel;
import MVC.Model.ParseModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateMovieController
{
    @FXML
    private Button updateButton;
    @FXML
    private Slider slider = new Slider();
    private MovieModel movieModel = new MovieModel();

    public UpdateMovieController() throws IOException {
    }

    public void updateMovie(){
        float rating = (float) slider.getValue();
        Movie tempMovie = ParseModel.tempMovie;
        tempMovie.setMovieRating(rating);
        movieModel.updateMovie(tempMovie);

        Stage stage2 = (Stage) updateButton.getScene().getWindow();
        stage2.close();
    }
}
