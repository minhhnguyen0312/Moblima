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

    public void showCinema(String movieName){
        System.out.println("Id\tCinema Name");
        int id = 0;
        for (Cinema c: this.data){
            if (c.isShowing(movieName))
                System.out.printf("%d\t%s\n", id, c.getName());
            id++;
        }
        return;
    }

    public Cinema getCinemaById(Integer id){
        return this.data.get(id);
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
