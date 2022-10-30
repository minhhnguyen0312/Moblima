package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ReviewsManager extends DBManager {
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
        this.data = new ArrayList<ArrayList<String>>();
    }
    
    public void read() throws IOException{
        super.read(this.root);
    }

    public Float getRating() throws IOException{
        return (float) 0.0;
    }
}
