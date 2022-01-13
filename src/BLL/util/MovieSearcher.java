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
            if(compareToTitle(query, movie) || compareToGenre(query, movie)){
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
                System.out.println(category.toString());
                if (category.toString().contains(query.toLowerCase()))
                {
                    result = true;
                }
                 else {
                     result = false;
                }
            }
        }
        return result;
    }
}
