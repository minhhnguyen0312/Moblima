package entities;
public class Review {
    private String userName;
    private String tittle;
    private String description;
    private Float rating;
    private String movieName;

    public Review(String username, String tittle, String description, Float rating, String movieName) {
        this.userName = username;
        this.tittle = tittle;
        this.description = description;
        this.rating = rating;
        this.movieName = movieName;
    }

    public void setUserName(String username){
        this.userName = username;
    }

    public void setTittle(String tittle){
        this.tittle = tittle;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setRating(Float rating){
        this.rating = rating;
    }

    public void setMovieName(String movieName){
        this.movieName = movieName;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getTitle(){
        return this.tittle;
    }

    public String getDescription() {
        return this.description;
    }

    public Float getRating() {
        return this.rating;
    }

    public String getMovieName(){
        return this.movieName;
    }
}
