package MVC;

import BE.Movie;
import MVC.Model.MovieModel;
import MVC.Model.ParseModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateMovieController
{
    @FXML
    private Button updateButton;
    @FXML
    private Slider slider;
    private MovieModel movieModel;

    public UpdateMovieController(){
        try {
            slider = new Slider();
            movieModel = new MovieModel();
        } catch (IOException e){

        }
    }

    public void updateMovie(){
        Movie tempMovie = ParseModel.tempMovie;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime systemDate = LocalDateTime.now();
        Movie movie = new Movie(tempMovie.getId(), tempMovie.getTitle(), (float) slider.getValue(), tempMovie.getUrl(), dtf.format(systemDate));
        movieModel.updateMovie(movie);

        Stage stage2 = (Stage) updateButton.getScene().getWindow();
        stage2.close();
    }
}
