package BLL;

import BE.Movie;
import BLL.util.MovieSearcher;
import DAL.CategoryDAO;
import DAL.MovieDAO;

import java.io.IOException;
import java.sql.SQLException;
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

    public Movie createMovie(String title, float movieRating, String url, String lastView) throws SQLException {
        return movieDAO.createMovie(title,movieRating,url,lastView);
    }
    public void updateMovie(Movie movie){
        movieDAO.updateMovie(movie);
    }
    public void deleteMovie(Movie movie){
        movieDAO.deleteMovie(movie);
    }
    public List<Movie> getAllMovies() {
        for (Movie movie : movieDAO.getAllMovies()) {
            movie.addCategoryToMovie(categoryDAO.getAllCategoryMovies(movie.getId()));
            System.out.println(movie.getCategories().toString());
        }

        return movieDAO.getAllMovies();
    }
    public List<Movie> searchMovies(String query, List<Movie> movieList) {
        return movieSearcher.search(movieList, query);
    }

}
