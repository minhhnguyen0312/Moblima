package entities;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import control.ReviewsManager;

public class Movie {

    private String name;
    private Status showingStatus;
    private Float duration;
    // private Genres movieType;
    private Integer sales;
    private String sypnosis;
    private String director;
    private ArrayList<String> casts;
    private ReviewsManager reviews;

    public Movie(){}
    public Movie(String name, String showStatus, Float duration, Integer sales, String director) throws IOException{
        this.name = name;
        this.showingStatus = Status.valueOf(showStatus);
        this.duration = duration;
        this.sales = sales;
        this.director = director;
        this.sypnosis = getMovieSypnosis(name);
        this.casts = getMovieCasts(name);
        this.reviews = getRevewManager(name);
    }

    public String getName(){
        return this.name;
    }

    public String getMovieSypnosis(String movieName) throws IOException {
        String filename = String.format("assets/movies/sypnosis/%s.txt", movieName);
        Scanner s = new Scanner(new FileInputStream(filename));
        String syp = s.nextLine().trim();
        s.close();
        return syp;
    }

    public ArrayList<String> getMovieCasts(String movieName) throws IOException {
        ArrayList<String> cast = new ArrayList<String>();
        String filename = String.format("assets/movies/casters/%s.txt", movieName);
        Scanner s = new Scanner(new FileInputStream(filename));
        while (s.hasNext()){
            cast.add(s.nextLine().trim());
        }
        return cast;
    }

    public ReviewsManager getRevewManager(String movieName) throws IOException {
        return new ReviewsManager(String.format("assets/movies/revew/%s.txt", movieName));
    }

    public String rprStringToUser() throws IOException{
        return String.format("Movie : %s\nStatus :%s\nSYPNOSIS : %s\nDirector: %s\nCast: %s, %s,..\nRating: %f",
                             name, showingStatus, sypnosis, director, casts.get(0), casts.get(1), getRating());
    }

    public Float getRating() throws IOException{
        return this.reviews.getRating();
    }
}