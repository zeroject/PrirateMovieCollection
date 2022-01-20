package BE;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int id;
    private String title;
    private float movieRating;
    private String url;
    private Timestamp lastView;
    private final ArrayList<Category> categoryList;

    public Movie(int id, String title, float movieRating, String url, Timestamp lastView){
        categoryList = new ArrayList<>();
        this.id = id;
        this.title = title;
        this.movieRating = movieRating;
        this.url = url;
        this.lastView = lastView;
    }

    /**
     * get methods for getting relevant info from movie object
     * @return
     */
    public int getId(){
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public float getMovieRating()
    {
        return movieRating;
    }

    public String getUrl()
    {
        return url;
    }

    public Timestamp getLastView(){
        return lastView;
    }

    @Override
    public String toString()
    {
        return title;
    }

    public List<Category> getCategories()
    {
        return categoryList;
    }

    /**
     * adds categories to the movie object
     * @param category object to hold name and id of the category
     */
    public void addCategoryToMovie(List<Category> category)
    {
        categoryList.addAll(category);
    }
}