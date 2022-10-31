package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import entities.Reviews;

public class ReviewsManager extends DBManager<Reviews> {
    public ReviewsManager(){
        this.root = "";
        this.columns = new ArrayList<String>(
            Arrays.asList("id", "user", "title", "description", "rating")
        );
    }

    public ReviewsManager(String root){
        this.root = root;
        this.columns = new ArrayList<String>(
            Arrays.asList("id", "user", "title", "description", "rating")
        );
        // this.data = new ArrayList<ArrayList<String>>();
    }
    
    public void read() throws IOException{
        super.read(this.root);
    }

    public Float getRating() throws IOException{
        // Get overall rating from 5 best rated reviews.
        return (float) 0.0;
    }

    @Override
    public Reviews constructFromArr(ArrayList<String> ele) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<String> decodeFromObj(Reviews obj) {
        // TODO Auto-generated method stub
        return null;
    }

}
