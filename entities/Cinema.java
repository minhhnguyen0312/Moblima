package entities;

import java.io.FileInputStream;
import java.io.FileWriter;
// import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
// import java.util.Arrays;
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
    private Integer maxId;
    private Map<String, Boolean> map;
    
    public Cinema(String cineplexName, String cinemaName, Integer totalSeat) throws IOException{
        this.cineplexName = cineplexName;
        this.cinemaName = cinemaName;
        this.type = CinemaType.valueOf(cinemaName.split("_")[0]);
        this.totalSeat = totalSeat;
        this.map = new HashMap<String, Boolean>();
        this.schedules = new ArrayList<Schedule>();
        this.maxId = 0;
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
                ele.get(1),
                ele.get(3)
            );
            maxId = (maxId > sch.getId()) ? maxId : sch.getId();
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

    public Integer getNextSchId(){
        return maxId + 1;
    }

    public void addSchedule(Schedule sch) throws IOException{
        this.schedules.add(sch);
        this.map.put(sch.getMovieName(), true);
        String filename = "assets/cineplexs/" + cineplexName + "/" + cinemaName + ".txt";
        FileWriter f = new FileWriter(filename, true);
        f.write(String.format("\n%d;%s;%s;%s", sch.getId(), sch.getDateTime(), sch.getMovieName(), sch.getMovieType()));
        f.close();
    }

    public void removeSchedule(Schedule sch, Boolean delete) throws IOException{
        this.schedules.remove(sch);
        String filename = "assets/cineplexs/" + cineplexName + "/" + cinemaName + ".txt";
        FileWriter f = new FileWriter(filename);
        String line = "";
        for (Schedule schedule: this.schedules){
            line = line + String.format("\n%d;%s;%s;%s", schedule.getId(), schedule.getDateTime(), schedule.getMovieName(), schedule.getMovieType());
        }
        f.write("scheduleId|time|movie|movieType" + line);
        f.close();
        if (delete)
        sch.delete();
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
}
