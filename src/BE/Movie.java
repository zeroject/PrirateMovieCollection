package BE;

import javafx.collections.ObservableList;

import java.util.List;

public class Movie {
    private int id;
    private String title;
    private float movieRating;
    private String url;
    private ObservableList<Category> categoryList;

    public Movie(int id, String title, float movieRating, String url, String lastView){
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

    public ObservableList<Category> addCategoryToMovie(List<Category> category)
    {
        categoryList.clear();
        categoryList.addAll(category);
        return categoryList;
    }

    public ObservableList<Category> getCategories()
    {
        return categoryList;
    }
}