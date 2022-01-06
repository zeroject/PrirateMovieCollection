package MVC;

import MVC.Model.MovieModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import java.io.IOException;
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

    public void createMovie() throws SQLServerException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime systemDate = LocalDateTime.now();
        System.out.println(dtf.format(systemDate));

        movieModel.createMovie(textFieldTitle.getText(), (float) sliderRating.getValue(), textFieldUrl.getText(), textFieldImgUrl.getText(), systemDate.toString());
    }
}
