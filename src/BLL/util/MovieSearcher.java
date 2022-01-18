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
        System.out.println("works?");
        boolean result = false;
        if (stringOrNumber(query))
        {
            System.out.println("works2?");
            if (movie.getMovieRating() >= Float.parseFloat(query))
            {
                System.out.println("works3?");
                result = true;
            }
            System.out.println(result);
        }
        return result;
    }

    private boolean stringOrNumber(String query)
    {
        boolean result = false;
        String numbers = "0,1,2,3,4,5,6,,7,8,9";
        String letters = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
        System.out.println(query + query.contains(numbers));
        if (query.contains(numbers) && !query.toLowerCase().contains(letters))
        {
            result = true;
        }
        return result;
    }
}
