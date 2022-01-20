package BLL.util;

import BE.Category;
import BE.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSearcher {

    /**
     * linear seach that go through searchbase to match with query
     * @param searchBase specific list of movies
     * @param query string to compare against
     * @return a list of movies that have a title or category that contain the string query
     */
    public List<Movie> search(List<Movie> searchBase, String query) {
        List<Movie> searchResult = new ArrayList<>();

        for (Movie movie : searchBase) {
            if (compareToTitle(query, movie) || compareToGenre(query, movie)) {
                searchResult.add(movie);
            }
        }
        return searchResult;
    }

    /**
     * compares query to movie title to see if movie contains query
     * @param query string to compare against
     * @param movie object to get title from
     * @return true if query is within the title
     */
    private boolean compareToTitle(String query, Movie movie) {
        return movie.getTitle().toLowerCase().contains(query.toLowerCase());
    }

    /**
     * compares query to category name to see if name contains query
     * @param query string to compare against
     * @param movie to get a list of categories from
     * @return true if movie contains a category name that contains query
     */
    private boolean compareToGenre(String query, Movie movie) {
        boolean result = false;
        if (movie.getCategories() != null) {
            for (Category category : movie.getCategories()) {
                if (category.getName().toLowerCase().contains(query.toLowerCase())) {
                    result = true;
                    break;
                } else {
                    result = false;
                }
            }
        }
        return result;
    }
}

