package BLL.util;

import BE.Movie;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class MoviePlayer {
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
        mp4Player = new MediaView(soundPlayer);
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
        mp4Player.setViewport();
    }

}
