package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private AgeRating ageGroup;
    private DateTime date;
    private String movieName;
    private String userName;
    private String cinema;
    private MovieType mType;
    private Float price;
    private static Float basePrice = (float) 100;

    public Ticket(String userName, String movieName, String cinema, String datetime, String ageGroup, String movieType) {
        this.userName = userName;
        this.date = new DateTime();
        this.date.setFromString(datetime);
        this.movieName = movieName;
        this.cinema = cinema;
        this.ageGroup = AgeRating.valueOf(ageGroup);
        this.price = computePrice();
        this.mType = MovieType.valueOf(movieType);
    }

    public Float getPrice() { return price; }
    
    public Float computePrice() {
        Float price = basePrice;
        // Age group, cinema type, holiday
        if (ageGroup == AgeRating.Student)
            price = price * 0.9f;
        else if (ageGroup == AgeRating.Senior_Citizen)
            price = price * 0.8f;
        
        if (cinema.split("_", 2)[0].equals("FisrtClass"))
            price = price * 1.1f;
        else if (cinema.split("_", 2)[0].equals("PlatinumMovieSuite"))
            price = price * 1.4f;

        if (mType == MovieType.movie3D)
            price = price * 1.05f;
        
        if (date.isHoliday())
            price += 30f;
        return price;
    }

    public String getTransactionId(){
        DateTimeFormatter myFmt = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String c = cinema.split("_", 10)[0];
        String s = c + LocalDateTime.now().format(myFmt);
        return s;
    }

    public String getReceipt(){
        return String.format("Movie: %s\nCinema: %s\nShowing Time: %s\nTransaction Id: %s\nPrice: %.1f\n",
                                movieName, cinema, date.getDateFormatedString(),
                                getTransactionId(), getPrice()); //)
    }

    public static void setBasePrice(Float b){
        Ticket.basePrice = basePrice;
    }

    public String toString(String sep){
        // time|email|movie|cinema|ageGroup|price|paid
        return String.format("%s%s%s%s%s%s%s%s%s", this.date.getDateFormatedString(), sep,
        this.userName, sep,
        this.movieName, sep,
        this.cinema, sep,
        this.ageGroup.toString());
    }

    public String toString(){
        // time|email|movie|cinema|ageGroup|price|paid
        return String.format("%s;%s;%s;%s;%s;%s", this.date.getDateFormatedString(), this.userName, this.movieName, this.cinema, this.ageGroup.toString(), this.mType);
    }
}
