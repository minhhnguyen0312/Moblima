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

    public void read() throws IOException {
        // A class to manage all the Cinema in all cineplex
        // As each Cinema having its own data file, we need to
        // loops through all the file and combine them into a single variable.
        // Do we really need to do this??
        // File format is logic as each Cinema need record for all the movies scheduled.    
        // Does the structure logic??
        // DBManager, the parent class is currently designed to handing a single file only
        // Then it's not possible to handle all Cinemas' schedule by 1 Manager object.
    };

    public void showCinema(String movieName){
        System.out.println("Calling this method");
        return;
    }

    @Override
    public Cinema constructFromArr(ArrayList<String> ele) throws NumberFormatException, IOException {
        Cinema c = new Cinema(
            ele.get(0),
            ele.get(1),
            Integer.parseInt(ele.get(2))
        );
        return c;
    }

    @Override
    public ArrayList<String> decodeFromObj(Cinema obj) {
        // TODO Auto-generated method stub
        return null;
    }
}
