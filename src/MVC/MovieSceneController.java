package MVC;

import MVC.Model.DeletingModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
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


    @Override public void initialize(URL location, ResourceBundle resources)
    {
        file = new File(DeletingModel.movieURL);
        media = new Media(file.toURI().toString());
        mp = new MediaPlayer(media);
        movieView.setMediaPlayer(mp);
        DoubleProperty mvw = movieView.fitWidthProperty();
        DoubleProperty mvh = movieView.fitHeightProperty();
        mvw.bind(Bindings.selectDouble(movieView.sceneProperty(), "width"));
        mvh.bind(Bindings.selectDouble(movieView.sceneProperty(), "height"));
        movieView.setPreserveRatio(true);
        movieView.setSmooth(false);
        mp.play();
    }

    public void playMov(){
        mp.play();
    }

    public void stopMov(){
        //used when program is closed
        mp.stop();
    }

    public void pauseMov(){
        mp.pause();
    }

    public void closection(){
        Stage stage = (Stage) button.getScene().getWindow();
        stopMov();
        stage.close();
    }
}
