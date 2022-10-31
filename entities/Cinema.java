package entities;

import java.util.ArrayList;

public class Cinema {
    private CinemaType type;
    private String cineplexName;
    private String cinemaName;
    private Integer totalSeat;
    private ArrayList<String> schedules;
    
    public Cinema(String cineplexName, String cinemaName, Integer totalSeat){
        this.cineplexName = cineplexName;
        this.cinemaName = cinemaName;
        this.type = CinemaType.valueOf(cinemaName.split("_")[0]);
        this.totalSeat = totalSeat;
    }
}
