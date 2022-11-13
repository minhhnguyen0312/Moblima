package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import entities.Cinema;

public class CinemaManager extends DBManager<Cinema> {
    
    public CinemaManager() throws IOException{
        this.root = "assets/cineplexs/names.txt";
        this.columns = new ArrayList<String>(Arrays.asList("id", "cineplexName", "cinemaName", "totalSeat"));
        this.data = new ArrayList<Cinema>();

        super.read(this.root);
    }
    
    // Get all cinema in a cineplex
    public ArrayList<Cinema> getCinema(String cineplexName) {
        ArrayList<Cinema> l = new ArrayList<Cinema>();
        for (Cinema c : this.data) {
            if (c.getCineplex().equals(cineplexName)){
                l.add(c);
            }
        }
        return l;
    }

    public void showAllCinemas(){
        int i = 1;
        System.out.println("Showing all Cinema");
        System.out.println("Id\tCineplex\tCinema");
        for (Cinema m : this.data){
            System.out.printf("%d\t%s\t%s\n",i, m.getCineplex(),  m.getName()); i++;
        }
        System.out.println("=============End of list=============");
    }

    public void showCinema(String movieName){
        System.out.println("Id\tCinema Name");
        int id = 1;
        for (Cinema c: this.data){
            if (c.isShowing(movieName))
                System.out.printf("%d\t%s\n", id, c.getName());
            id++;
        }
        return;
    }

    public Cinema getCinemaById(Integer id){
        return this.data.get(id - 1);
    }

    @Override
    public Cinema constructFromArr(ArrayList<String> ele) throws NumberFormatException, IOException {
        Cinema c = new Cinema(
            ele.get(1),
            ele.get(2),
            Integer.parseInt(ele.get(3))
        );
        return c;
    }

    @Override
    public ArrayList<String> decodeFromObj(Cinema obj) {
        // TODO Auto-generated method stub
        return null;
    }
}
