package MVC;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieScene implements Initializable
{
    @FXML
    public MediaView movieView;

    private File file;
    private Media media;
    private MediaPlayer mp;


    @Override public void initialize(URL location, ResourceBundle resources)
    {
        file = new File("C:\\Users\\caspe\\Pictures\\Camera Roll\\WIN_20220110_10_05_59_Pro.mp4");
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
}
