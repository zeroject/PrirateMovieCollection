package BE;

import java.util.List;

public class Movie {
    private int id;
    private String title;
    private float movieRating;
    private String url;
    private String urlImg;
    private List<Category> categoryList;

    public Movie(int id, String title, float movieRating, String url, String urlImg, String lastView){
        this.id = id;
        this.title = title;
        this.movieRating = movieRating;
        this.url = url;
        this.urlImg = urlImg;
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

    public String getUrlImg()
    {
        return urlImg;
    }

    @Override public String toString()
    {
        return title;
    }
}
