package BLL;

import BE.Movie;
import BLL.util.MovieSearcher;
import DAL.CategoryDAO;
import DAL.MovieDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MovieManager
{
    private CategoryDAO categoryDAO;
    private MovieDAO movieDAO;
    private MovieSearcher movieSearcher;

    public MovieManager() throws IOException
    {
        movieSearcher = new MovieSearcher();
        movieDAO = new MovieDAO();
        categoryDAO = new CategoryDAO();
    }

    /**
     * pases methods from MovieDAO and MovieSearcher
     * @throws SQLException
     */
    public Movie createMovie(String title, float movieRating, String url, Timestamp lastView) throws SQLException {
        return movieDAO.createMovie(title,movieRating,url,lastView);
    }
    public void updateMovie(Movie movie){
        movieDAO.updateMovie(movie);
    }
    public void deleteMovie(Movie movie){
        movieDAO.deleteMovie(movie);
    }
    public List<Movie> getAllMovies() {
        List<Movie> allMovies = new ArrayList<Movie>();
        for (Movie movie : movieDAO.getAllMovies()) {
            movie.addCategoryToMovie(categoryDAO.getAllCategoryMovies(movie.getId()));
            allMovies.add(movie);
        }

        return allMovies;
    }
    public List<Movie> searchMovies(String query, List<Movie> movieList) {
        return movieSearcher.search(movieList, query);
    }

}
