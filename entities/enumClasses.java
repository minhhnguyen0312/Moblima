package entities;

public class enumClasses {
    
}

class DateTime{
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private boolean isHoliday;

    public DateTime(){
        this.day=1;
        this.month=1;
        this.year=1;
        this.hour = 0;
        this.minute=59;
        this.isHoliday = false;
    }

    public DateTime(int day, int month, int year, int hour, int minute){
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.isHoliday = false;
    }

    public DateTime(int day, int month, int year, int hour, int minute, boolean isHoliday){
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.isHoliday = isHoliday;
    }

    public boolean isHoliday(){
        return this.isHoliday;
    }

    public String getDateFormatedString(){
        return String.format("%d-%d-%d %d:%d %s",
         this.day, this.month, this.year, this.hour, this.minute,
         this.isHoliday? "H": "N");
    }

    public void setFromString(String dateString){
        String[] d = dateString.split(" ");
        String[] date = d[0].split("-");
        String[] time = d[1].split(":");
        this.day = Integer.parseInt(date[0]);
        this.month = Integer.parseInt(date[1]);
        this.year = Integer.parseInt(date[2]);

        this.hour = Integer.parseInt(time[0]);
        this.minute = Integer.parseInt(time[1]);
        if (d[2].equals("H"))
                this.isHoliday = true;
    }
}

enum AgeRating {
    G("G"),
    PG13("PG13"),
    PG16("PG16"),
    NC16("NC16"),
    M18("M18"),
    R21("R21");

    private final String p;
    private AgeRating(String p){
        this.p =p;
    }
    public String toString(){
        return p;
    }
}

enum Status {
    ComingSoon("ComingSoon"),
    Preview("Preview"),
    NowShowing("NowShowing"),
    EndOfShowing("EndOfShowing");
    
    private final String status;
    private Status(String status){
        this.status = status;
    }

    public String toString(){
        return status;
    }
}

enum CinemaType{
    StandardCinema,
    FirstClass,
    PlatinumMovieSuite,
}