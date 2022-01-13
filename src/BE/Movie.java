package BE;

import java.util.List;

public class Movie {
    private int id;
    private String title;
    private float movieRating;
    private String url;
    private List<Category> categoryList;

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

    public void addCategoryToMovie(Category category)
    {
        categoryList.add(category);
    }

    public List<Category> getCategories()
    {
        return categoryList;
    }
}