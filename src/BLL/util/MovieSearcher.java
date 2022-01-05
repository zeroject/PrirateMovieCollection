package BLL.util;

import BE.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSearcher
{
    public List<Movie> search(List<Movie> searchBase, String query){
        List<Movie> searchResult = new ArrayList<>();

        for (Movie movie : searchBase){
            if(compareToTitle(query, movie)){
                searchResult.add(movie);
            }
        }
        return searchResult;
    }

    private boolean compareToTitle(String query, Movie movie){
        return movie.getTitle().toLowerCase().contains(query.toLowerCase());
    }
}
