package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import entities.Movie;

public class MoviesManager extends DBManager<Movie> {
    public MoviesManager() throws IOException {
        this.root = "assets/movies/names.txt";
        this.data = new ArrayList<Movie>();
        this.columns = new ArrayList<String>(Arrays.asList("id","movieName","status","duration","totalSale","director"));
        // System.out.println("Retrieving movies...");
        super.read(this.root);
    }

    public void showTopMovies(String by) {
        return;
    }

    public void movieListing() {
        int i = 1;
        System.out.println("Showing all movies...");
        for (Movie m : this.data){
            System.out.printf("%d. %s\n",i,  m.getName()); i++;
        }
        System.out.println("=============End of list=============");
    }
    
    public void showMovies() throws IOException{
        int count = 1;
        for (Movie m : this.data){
            if (m.isShowing())
                System.out.printf("%d. %s\n", count, m.shortString());
            count ++;
        }
    }

    public void showBestSales() {
        Movie mb;
        sortBySales();
        System.out.println("Movie\tSales");
        for (int i = 0; i < this.data.size(); i++){
            if (i == 5) break;
            mb = this.data.get(i);
            System.out.printf("%s\t%d\n", mb.getName(), mb.getSales());
        }
        System.out.println();
    }

    public void showBestRating() {
        Movie mb;
        sortByRating();
        System.out.println("Movie\tRating");
        for (int i = 0; i < this.data.size(); i++){
            if (i == 5) break;
            mb = this.data.get(i);
            System.out.printf("%s\t%.1f\n", mb.getName(), mb.getRating());
        }
        System.out.println();
    }

    public void sortBySales() {
        this.data.sort((o1, o2) -> (o1.getSales() < o2.getSales()) ? o1.getSales() : o2.getSales());
    }

    public void sortByRating() {
        this.data.sort((o1, o2) -> Math.round((o1.getRating() < o2.getRating()) ? o1.getRating()*10f : o2.getRating()*10f));
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

    public Movie getMovieByName(String movieName){
        for (Movie m : this.data){
            if (m.getName().equals(movieName))
                return m;
        }
        System.out.println("There is no such movie.");
        return null;
    }

    public Movie getMovieById(Integer id){
        return this.data.get(id - 1);
    }

    public void addMovie(Movie m) throws IOException{
        this.data.add(m);
        String line = String.format("%d;%s", this.data.size(), m.toString());
        this.write(line, true);
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
        ArrayList<String> ele = new ArrayList<String>();
        ele.add(obj.getName());
        ele.add(String.format("%s", obj.getStatus()));
        ele.add(String.format("%.1f", obj.getDuration()));
        ele.add(obj.getSales().toString());
        ele.add(obj.getDirector());
        return ele;
    }
}
