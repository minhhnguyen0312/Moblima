package entities;
public class Review {
    private String description;
    private Float rating;
    private String movieName;

    public Review(String description, Float rating, String movieName) {
        this.description = description;
        this.rating = rating;
        this.movieName = movieName;
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
