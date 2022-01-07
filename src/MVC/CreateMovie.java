package MVC;

import MVC.Model.MovieModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.MapChangeListener;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateMovie
{

    public TextField textFieldTitle;
    public Slider sliderRating;
    public TextField textFieldUrl;
    public TextField textFieldImgUrl;

    private MovieModel movieModel;

    public CreateMovie() throws IOException {
        movieModel = new MovieModel();
    }

    public void createMovie() throws SQLException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime systemDate = LocalDateTime.now();
        System.out.println(dtf.format(systemDate));

        movieModel.createMovie(textFieldTitle.getText(), (float) sliderRating.getValue(), textFieldUrl.getText(), textFieldImgUrl.getText(), systemDate.toString());
    }

    /**
     * Opens a file explorer to choose an image (*.png, *.jpg, *.jpeg)
     */
    public void chooseImageFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        fileChooser.setInitialDirectory(new File("image/" ));
        File file = fileChooser.showOpenDialog(null);

        if (file != null){
            textFieldImgUrl.setText("image\\" + file.getName());

        }
    }

    /**
     * Opens a file explorer to choose a song (*.mp3, *.wav)
     */
    public void chooseMovieFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Music Files", "*.mp3", "*.wav"));
        fileChooser.setInitialDirectory(new File("Movies/" ));
        File file = fileChooser.showOpenDialog(null);


        if (file != null){
            textFieldUrl.setText("Movies\\" + file.getName());
            /*
            Media hit = new Media(new File(file.getAbsolutePath()).toURI().toString());
            hit.getMetadata().addListener((MapChangeListener.Change<? extends String, ? extends Object> c) -> {
                if (c.wasAdded()) {
                    if ("artist".equals(c.getKey())) {
                        String artist = c.getValueAdded().toString();
                        songArtist.setText(artist);
                    }
                    else if ("title".equals(c.getKey())) {
                        String title = c.getValueAdded().toString();
                        songTitle.setText(title);
                    }
                }
            });

            mediaPlayer = new MediaPlayer(hit);
            */
        }
    }
}
