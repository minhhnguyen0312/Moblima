package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import entities.Movie;

public class MoviesManager extends DBManager<Movie> {
    private ArrayList<Movie> data;
    public MoviesManager() {
        this.root = "assets/movies/name.txt";
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

    public Movie getMovieById(Integer id){
        return null;
    }

    @Override
    public Movie constructFromArr(ArrayList<String> ele) throws NumberFormatException, IOException{
        Movie m = new Movie(
            ele.get(1),
            ele.get(2),
            Float.parseFloat(ele.get(3)),
            Integer.parseInt(ele.get(4)),
            ele.get(5)
        );
        return m;
    }

    @Override
    public ArrayList<String> decodeFromObj(Movie obj) {
        // TODO Auto-generated method stub
        return null;
    }
}
