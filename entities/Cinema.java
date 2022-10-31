package entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cinema {
    private CinemaType type;
    private String cineplexName;
    private String cinemaName;
    private Integer totalSeat;
    // private Integer nRows, nCols;
    private ArrayList<Schedule> schedules;
    private Map<String, Boolean> map;
    
    public Cinema(String cineplexName, String cinemaName, Integer totalSeat) throws IOException{
        this.cineplexName = cineplexName;
        this.cinemaName = cinemaName;
        this.type = CinemaType.valueOf(cinemaName.split("_")[0]);
        this.totalSeat = totalSeat;
        this.map = new HashMap<String, Boolean>();
        this.schedules = new ArrayList<Schedule>();
        retrieveSchedule(cineplexName, cinemaName);
    }

    public void retrieveSchedule(String cineplexName, String cinemaName) throws IOException {
        String filename = "assets/cineplexs/" + cineplexName + "/" + cinemaName + ".txt";
        Scanner sc = new Scanner(new FileInputStream(filename));
        sc.nextLine();
        ArrayList<String> ele;
        while(sc.hasNext()) {
            ele = new ArrayList<String>();
            for (String s: sc.nextLine().trim().split(";"))
                ele.add(s);

            Schedule sch = new Schedule(
                Integer.parseInt(ele.get(0)),
                ele.get(2),
                this.cinemaName,
                this.cineplexName,
                ele.get(1)
            );
            this.schedules.add(sch);
            // System.out.println(ele);
            this.map.put(sch.getMovieName(), true);
        }
    }

    public String getName(){
        return cinemaName;
    }

    public String getCineplex(){
        return cineplexName;
    }

    public CinemaType getType(){
        return type;
    }

    public Integer getTotalSeat(){
        return totalSeat;
    }

    public void printSchedule(){
        for(Schedule schedule : schedules)
            System.out.println(schedule.toString());
    }
    
    public void printMovieSchedule(String movieName){
        int count = 0;
        System.out.println("Schedule Id\tTime\tMovie\tSeat Available");
        for (Schedule schedule : schedules){
            if (schedule.getMovieName().equals(movieName) && schedule.getRemainSeat() != 0){
                System.out.println(schedule.toString());
                count ++;
            }
        }
        if (count == 0)
            System.out.println("Sorry there are no more available seat in this time slot.");
        return;
    }

    public Schedule getScheduleById(Integer id){
        for (Schedule sch : this.schedules){
            if (sch.getId() == id)
                return sch;
        }
        return null;
    }

    public String getDateTime(Integer id){
        return this.schedules.get(id).getDateTime();
    }
    
    public boolean isShowing(String movieName){
        return this.map.getOrDefault(movieName, false);
    }

    public void updateSchedule(Integer id){

    }
}
