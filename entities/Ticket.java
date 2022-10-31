package entities;
public class Ticket {
    private AgeRating ageGroup;
    private DateTime date;
    private String movieName;
    private String userName;
    private String cinema;
    private Float price;
    private final static Float basePrice = (float)100;

    public Ticket(String userName, String movieName, String cinema, String datetime, String ageGroup) {
        this.userName = userName;
        this.date = new DateTime();
        this.date.setFromString(datetime);
        this.movieName = movieName;
        this.cinema = cinema;
        this.ageGroup = AgeRating.valueOf(ageGroup);
        this.price = computePrice();
    }

    public Float getPrice() { return price; }
    
    public Float computePrice() {
        Float price = basePrice;
        if (ageGroup == AgeRating.M18)
            price = price * (float)0.8;
        if (date.isHoliday())
            price = price * 2;
        return price;
    }

    public String getReceipt(){
        return String.format("Movie: %s\nCinema: %s\nShowing Time: %s", movieName, cinema, date.getDateFormatedString()); //)
    }

    public String toString(){
        // time|email|movie|cinema|ageGroup|price|paid
        return String.format("%s;%s;%s;%s;%s", this.date.getDateFormatedString(), this.userName, this.movieName, this.cinema, this.ageGroup.toString());
    }
}
