package BLL.util;

import BE.Movie;
import javafx.fxml.Initializable;
import javafx.geometry.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MoviePlayer implements Initializable
{
    MediaView mp4Player;
    MediaPlayer soundPlayer;
    Media media;
    Movie currentMovie;
    boolean playing;

    public MoviePlayer()
    {
        currentMovie = null;
        playing = false;
    }

    public void setCurrentMovie(Movie movie)
    {
        File file = new File(movie.getUrl());
        media = new Media(file.toURI().toString());
        currentMovie = movie;
        MediaPlayer soundPlayer = new MediaPlayer(media);
        mp4Player = new MediaView();
        mp4Player.setMediaPlayer(soundPlayer);
    }

    public void playMovie()
    {
        if (!playing)
        soundPlayer.play();
        playing = true;
    }

    public void pauseMovie()
    {
        if (playing)
        soundPlayer.pause();
    }

    public void stopMovie()
    {
        if (playing)
        soundPlayer.stop();
    }

    public void setVolume(double volume)
    {
        if (soundPlayer != null)
        {
            soundPlayer.setVolume(volume);
        }
    }

    public void setMediaWindow()
    {
        mp4Player.setViewport(get2D());
    }

    public Rectangle2D get2D(){
        double value = 2.0;

        return new Rectangle2D(100, 100, 100, 100);
    }
    Date date = new Date();
    public static void main(String[] args) {
        MoviePlayer mp = new MoviePlayer();
        Movie movie = new Movie(1, "gay", 8, "C:\\Users\\kaspe\\Desktop\\PrirateMovieCollection\\topgear.mp4", "C:\\Users\\kaspe\\Pictures\\Lenovo\\LenovoWelcome\\icons\\Support.ico", null);
        mp.setCurrentMovie(movie);
        mp.playMovie();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
