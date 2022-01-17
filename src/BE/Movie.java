package BE;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int id;
    private String title;
    private float movieRating;
    private String url;
    private final ArrayList<Category> categoryList;

    public Movie(int id, String title, float movieRating, String url, String lastView){
        categoryList = new ArrayList<>();
        this.id = id;
        this.title = title;
        this.movieRating = movieRating;
        this.url = url;
    }

    public int getId(){
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public float getMovieRating()
    {
        return movieRating;
    }

    public void setMovieRating(float movieRating)
    {
        this.movieRating = movieRating;
    }

    public String getUrl()
    {
        return url;
    }

    @Override public String toString()
    {
        return title;
    }

    public void addCategoryToMovie(List<Category> category)
    {
        categoryList.addAll(category);
    }

    public List<Category> getCategories()
    {
        return categoryList;
    }
}