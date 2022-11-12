package entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Schedule {
    private Boolean t;
    private String cineplexName;
    private String cinemaName;
    private Integer scheduleId;
    private String movieName;
    private MovieType mType;
    private DateTime datetime;
    private Integer remainSeat;
    private Integer nRows = 10, nCols=5;
    private String filename;
    private ArrayList<ArrayList<Boolean>> seatStatus;

    public Schedule(Integer scheduleId, String movieName, String cinemaName, String cineplexName, String datetime, String mType) throws IOException{
        this.t = false;
        this.scheduleId = scheduleId;
        this.cinemaName = cinemaName;
        this.cineplexName = cineplexName;
        this.movieName = movieName;
        this.datetime = new DateTime();
        this.datetime.setFromString(datetime);
        this.mType = MovieType.valueOf(mType);
        this.filename = "assets/cineplexs/" + cineplexName + "/" + cinemaName + "/" + this.scheduleId + ".txt";
        createSeats();
    }

    public Integer getId(){
        return scheduleId;
    }

    public String getCinemaName(){
        return cinemaName;
    }

    public String getCineplexName(){
        return cineplexName;
    }

    public String getMovieName() {
        return movieName;
    }

    public Integer getRemainSeat(){
        return remainSeat;
    }

    public Integer getRow(){
        return nRows;
    }

    public Integer getCol() {
        return nCols;
    }

    public String getDateTime(){
        return datetime.getDateFormatedString();
    }

    public String getMovieType(){
        return String.format("%s", mType);
    }

    public Boolean hasTicket(){
        return this.t;
    }

    public void setTime(String d){
        this.datetime.setFromString(d);
    }

    public void setType(String type){
        this.mType = MovieType.valueOf(type);
    }

    public void setMovieName(String movie){
        this.movieName = movie;
    }

    public void createSeats() throws IOException{
        File file = new File(filename);
        if (file.exists()) {
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
                    else {
                        r.add(false);
                        this.t = true;
                    }
                }
                this.seatStatus.add(r);
            }
            this.nRows = this.seatStatus.size();
            this.nCols = this.seatStatus.get(0).size();
        }
        else {
            file.createNewFile();
            FileWriter w = new FileWriter(filename);
            String s = "";
            this.remainSeat = 50;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 5; j++) {
                    s += "T ";
                }
                s += "\n";
            }
            w.write(s);
            w.close();
        }
    }

    public void occupiedSeat(Integer row, Integer col) throws IOException{
        this.seatStatus.get(row).set(col, false);
        // To do: write the status to file
        FileWriter writer = new FileWriter(filename, false);
        String s = "";
        for (ArrayList<Boolean> r: this.seatStatus){
            // s += String.join("\t", row);
            for (Boolean status: r){
                s += (status) ? "T" : "F";
                s += " ";
            }
            s += "\n";
        }
        writer.write(s);
        writer.close();
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

    public void delete() {
        File seat = new File(filename);
        seat.delete();
    }
}
