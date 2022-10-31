package entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Schedule {
    private String cineplexName;
    private String cinemaName;
    private Integer scheduleId;
    private String movieName;
    private DateTime datetime;
    private Integer remainSeat;
    private Integer nRows, nCols;
    private ArrayList<ArrayList<Boolean>> seatStatus;

    public Schedule(Integer scheduleId, String movieName, String cinemaName, String cineplexName, String datetime) throws FileNotFoundException{
        this.scheduleId = scheduleId;
        this.cinemaName = cinemaName;
        this.cineplexName = cineplexName;
        this.movieName = movieName;
        this.datetime = new DateTime();
        this.datetime.setFromString(datetime);
        createSeats();
    }

    public Integer getId(){
        return scheduleId;
    }

    public String getMovieName() {
        return movieName;
    }

    public Integer getRemainSeat(){
        return remainSeat;
    }

    public String getDateTime(){
        return datetime.getDateFormatedString();
    }

    public void createSeats() throws FileNotFoundException{
        String filename = "assets/cineplexs/" + cineplexName + "/" + cinemaName + "/" + this.scheduleId + ".txt";
        Scanner sc = new Scanner(new FileInputStream(filename));
        ArrayList<Boolean> r;
        this.remainSeat = 0;
        this.seatStatus = new ArrayList<ArrayList<Boolean>>();
        while (sc.hasNext()){
            r = new ArrayList<Boolean>();
            for (String s: sc.nextLine().trim().split(" ")){
                if (s.equals("T")){
                    r.add(true);
                    this.remainSeat += 1;
                }
            }
            this.seatStatus.add(r);
        }
        this.nRows = this.seatStatus.size();
        this.nCols = this.seatStatus.get(0).size();
    }

    public void occupiedSeat(Integer row, Integer col){
        this.seatStatus.get(row).set(col, false);
        this.remainSeat -= 1;
    }

    public void presentSeat(){
        String s = "";
        for (ArrayList<Boolean> row: this.seatStatus){
            // s += String.join("\t", row);
            for (Boolean status: row){
                s += (status) ? "[ ]" : "[X]";
                s += "\t";
            }
            s += "\n";
        }
        System.out.println(s);
    }

    public String toString(){
        return String.format("%d\t%s\t%s\t%d", this.scheduleId, this.datetime.getDateFormatedString(), this.movieName, this.remainSeat);
    }
}
