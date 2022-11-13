package entities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import control.ReviewsManager;

// import control.ReviewsManager;

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

    public Movie(){
        this.name = "";
        this.showingStatus = Status.ComingSoon;
        this.duration = 1.f;
        this.sales = 0;
        this.sypnosis = "";
        this.director = "";
        this.casts = new ArrayList<String>();
    }
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

    public Status getStatus(){
        return showingStatus;
    }

    public Float getDuration(){
        return duration;
    }

    public Integer getSales(){
        return sales;
    }

    public String getDirector(){
        return director;
    }

    public boolean isShowing(){
        if (showingStatus == Status.Preview || showingStatus == Status.NowShowing)
            return true;
        return false;
    }

    public void setStatus(String status){
        this.showingStatus = Status.valueOf(status);
    }

    public void setSypnosis(String syp){
        this.sypnosis = syp;
    }

    public void addCast(String name){
        this.casts.add(name);
    }

    public String getMovieSypnosis(String movieName) throws IOException {
        String filename = String.format("assets/movies/sypnosis/%s.txt", movieName);
        File f = new File(filename);
        if (!f.exists()){
            f.createNewFile();
        }
        Scanner s = new Scanner(new FileInputStream(filename));
        if (s.hasNext()){
            String syp = s.nextLine().trim();
            s.close();
            return syp;
        }
        else {
            s.close();
            return "";
        }
    }

    public ArrayList<String> getMovieCasts(String movieName) throws IOException {
        ArrayList<String> cast = new ArrayList<String>();
        String filename = String.format("assets/movies/casters/%s.txt", movieName);
        File f = new File(filename);
        if (!f.exists()){
            f.createNewFile();
        }
        Scanner s = new Scanner(new FileInputStream(filename));
        while (s.hasNext()){
            cast.add(s.nextLine().trim());
        }
        return cast;
    }

    public ReviewsManager getRevewManager(String movieName) throws IOException {
        String filename = String.format("assets/movies/reviews/%s.txt", movieName);
        File f = new File(filename);
        if (!f.exists()){
            f.createNewFile();
            FileWriter w = new FileWriter(filename);
            w.write("review_id|use|title|description|rating");
            w.close();
        }
        return new ReviewsManager(filename, movieName);
    }

    public String rprStringToUser() throws IOException{
        return String.format("Movie : %s\nStatus :%s\nSYPNOSIS : %s\nDirector: %s\nCast: %s, %s,..\nRating: %.1f\nBest Review: %s",
                             name, showingStatus, sypnosis, director, casts.get(0), casts.get(1), getRating(), getBestReview().getDescription());
    }

    public String shortString() {
        return String.format("Movie : %s\nStatus :%s", name, showingStatus);
    }

    public String toString(){
        return String.format("%s;%s;%.1f;%d;%s", name, showingStatus, duration, sales, director);
    }

    public Float getRating() {
        return reviews.getRating();
    }

    public Review getBestReview(){
        return reviews.getBest();
    }

    public void updateAll() throws IOException {
        // Writing sypnosis
        String filename = String.format("assets/movies/sypnosis/%s.txt", name);
        FileWriter f = new FileWriter(filename);
        f.write(this.sypnosis);
        f.close();

        // Writing casts
        filename = String.format("assets/movies/casters/%s.txt", name);
        f = new FileWriter(filename);
        f.write(String.join("\n", this.casts));
        f.close();

        // Writing reviews (no need)
    }

    public void incrSales() throws IOException {
        this.sales += 1;
    }
}
