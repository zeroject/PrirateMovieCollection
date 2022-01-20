package MVC;

import BE.Movie;
import MVC.Model.MovieModel;
import MVC.Model.ParseModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

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
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "" + e, ButtonType.OK);
            alert.setHeaderText("Ohh no an Error happend : Error:0x0011");
            alert.showAndWait();
        }
    }

    /**
     * gets the movie from the ParseModel.
     * it makes a new datetime to give movie so it knows when the last time it has been watched.
     */
    public void updateMovie(){
        Movie tempMovie = ParseModel.tempMovie;

        java.sql.Timestamp lastView = new java.sql.Timestamp(System.currentTimeMillis());
        Movie movie = new Movie(tempMovie.getId(), tempMovie.getTitle(), (float) slider.getValue(), tempMovie.getUrl(), lastView);
        movieModel.updateMovie(movie);

        Stage stage2 = (Stage) updateButton.getScene().getWindow();
        stage2.close();
    }
}
