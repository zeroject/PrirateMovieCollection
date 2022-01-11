package MVC;

import BE.Movie;
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

        Movie movie = movieModel.createMovie(textFieldTitle.getText(), (float) sliderRating.getValue(), textFieldUrl.getText(), textFieldImgUrl.getText(), dtf.format(systemDate));

        System.out.println(movie.getTitle() + movie.getId());
    }

    /**
     * Opens a file explorer to choose an image (*.png, *.jpg, *.jpeg)
     */
    public void chooseImageFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        fileChooser.setInitialDirectory(new File("Images/" ));
        File file = fileChooser.showOpenDialog(null);

        if (file != null){
            textFieldImgUrl.setText("Image\\" + file.getName());

        }
    }

    /**
     * Opens a file explorer to choose a movie (*.mp4, *.mpeg4)
     */
    public void chooseMovieFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.mpeg4"));
        fileChooser.setInitialDirectory(new File("Movies/"));
        File file = fileChooser.showOpenDialog(null);


        if (file != null){
            textFieldUrl.setText("Movies\\" + file.getName());

            String fileName = file.getName();
            String[] filenames = {".mp4", ".mpeg4"};
            for (String words: filenames){

                fileName = fileName.replace(words, "".repeat(words.length()));
            }
            textFieldTitle.setText(fileName);
        }
    }
}
