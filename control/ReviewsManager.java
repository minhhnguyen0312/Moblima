package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import entities.Review;

public class ReviewsManager extends DBManager<Review> {
    private String movieName;
    public ReviewsManager(){
        this.root = "";
        this.columns = new ArrayList<String>(
            Arrays.asList("review_id", "user", "title", "description", "rating")
        );
    }

    public ReviewsManager(String root, String movieName) throws IOException{
        this.root = root;
        this.movieName = movieName;
        this.columns = new ArrayList<String>(
            Arrays.asList("review_id", "user", "title", "description", "rating")
        );
        this.data = new ArrayList<Review>();
        super.read(root);
    }

    public Float getRating() {
        // Get overall rating from 5 best rated reviews.
        Float rate = 0.0f;
        Integer count = 0;
        for (Review r : this.data){
            rate += r.getRating();
            count += 1;
        }
        return rate / count;
    }

    public Review getBest(){
        Float rate = 0.0f;
        Review res = new Review(movieName);
        for (Review r : this.data){
            if (r.getRating() > rate){
                res = r;
                rate = r.getRating();
            }
        }
        return res;
    }

    @Override
    public Review constructFromArr(ArrayList<String> ele) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        return new Review(ele.get(2), Float.parseFloat(ele.get(1)), movieName);
    }

    @Override
    public ArrayList<String> decodeFromObj(Review obj) {
        // TODO Auto-generated method stub
        return null;
    }

}