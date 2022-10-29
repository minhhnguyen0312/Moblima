package entities;
import java.util.*;

public class Movie {

    private String name;
    private Status showingStatus;
    private ArrayList<Integer> showingTime;
    // private Genres movieType;
    private Integer sales;
    private String sypnosis;
    private String director;
    private ArrayList<String> casts;
    private ArrayList<Reviews> reviews;

    public Movie(){}
    public Movie(String name, Status showStatus, Integer sales, String sypnosis){
        this.name = name;
        this.showingStatus = showStatus;
        this.sales = sales;
        this.sypnosis = sypnosis;
    }

    public String getName(){
        return this.name;
    }
}
