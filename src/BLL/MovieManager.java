package BLL;

import BE.Movie;
import BLL.util.MovieSearcher;
import DAL.MovieDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MovieManager
{
    MovieDAO movieDAO;
    MovieSearcher movieSearcher;

    public MovieManager() throws IOException
    {
        movieSearcher = new MovieSearcher();
        movieDAO = new MovieDAO();
    }

    public void createMovie(String title, float movieRating, String url, String imgUrl){
        movieDAO.createMovie(title,movieRating,url,imgUrl);
    }
    public void updateMovie(Movie movie){
        movieDAO.updateMovie(movie);
    }
    public void deleteMovie(Movie movie){
        movieDAO.deleteMovie(movie);
    }
    public List<Movie> getAllMovies() throws SQLException {
        return movieDAO.getAllMovies();
    }
    public List<Movie> searchMovies(String query) throws SQLException {
        List<Movie> allMovie = getAllMovies();
        List<Movie> searchResult = movieSearcher.search(allMovie, query);
        return searchResult;
    }
    public String updateMovieImage(Movie movie){
        return movie.getUrlImg();
    }
}
