package MVC;

import MVC.Model.ParseModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieSceneController implements Initializable
{
    @FXML
    public MediaView movieView;
    @FXML
    public Button button;

    private File file;
    private Media media;
    private MediaPlayer mp;


    /**
     * the file gets the movie url form ParseModel and gives it the media then links up to a MediaView.
     * @param location
     * @param resources
     */
    @Override public void initialize(URL location, ResourceBundle resources)
    {
        file = new File(ParseModel.movieURL);
        media = new Media(file.toURI().toString());
        mp = new MediaPlayer(media);
        movieView.setMediaPlayer(mp);
        mp.play();
    }

    /**
     * starts the movie or resumes the movie if paused.
     */
    public void playMov(){
        mp.play();
    }

    /**
     * stops the movie completely
     */
    public void stopMov(){
        //used when program is closed
        mp.stop();
    }

    /**
     * pauses the movie
     */
    public void pauseMov(){
        mp.pause();
    }

    /**
     * a button to close the window.
     */
    public void closection(){
        Stage stage = (Stage) button.getScene().getWindow();
        stopMov();
        stage.close();
    }
}
