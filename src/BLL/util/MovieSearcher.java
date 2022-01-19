package BLL.util;

import BE.Category;
import BE.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSearcher
{
    public List<Movie> search(List<Movie> searchBase, String query){
        List<Movie> searchResult = new ArrayList<>();

        for (Movie movie : searchBase){
            if(compareToTitle(query, movie) || compareToGenre(query, movie) || compareToRating(query, movie)){
                searchResult.add(movie);
            }
        }
        return searchResult;
    }

    private boolean compareToTitle(String query, Movie movie){
        return movie.getTitle().toLowerCase().contains(query.toLowerCase());
    }

    private boolean compareToGenre(String query, Movie movie) {
        boolean result = false;
        if (movie.getCategories() != null)
        {
            for (Category category : movie.getCategories())
            {
                if (category.getName().toLowerCase().contains(query.toLowerCase()))
                {
                    result = true;
                    break;
                }
                 else {
                     result = false;
                }
            }
        }
        return result;
    }

    private boolean compareToRating(String query, Movie movie)
    {
        boolean result = false;
        if (stringOrNumber(query))
        {
            if (movie.getMovieRating() >= Float.parseFloat(query))
            {
                result = true;
            }
            System.out.println(result);
        }
        return result;
    }

    private boolean stringOrNumber(String query)
    {
        boolean result = false;
        for (int i = 0; i <query.length(); i++)
        {
            if (Character.isDigit(query.charAt(i)))
            {
                result = true;
            }
        }
        return result;
    }
}
