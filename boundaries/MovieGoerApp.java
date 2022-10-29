package boundaries;
// import java.io.IOException;
// import java.text.ParseException;
import java.util.*;

import control.*;
import entities.*;

public class MovieGoerApp extends App {
    private TicketManager ticketManager;
    private MoviesManager movieManager;
    private CinemaManager cinemaManager;
    private Person user;

    public MovieGoerApp(){
        super();
        this.ticketManager = null;
    }

    public MovieGoerApp(Person user){
        this.user=user;
        // this.ticketManager = new TicketManager(user.getEmail());
    }

    public void runningSession() {
        System.out.println("Welcome to MOBLIMA Booking Center");
        System.out.println("================================================================");
        System.out.println("1. Search for movies.");
        System.out.println("2. See top reviewed movies");
        System.out.println("3. get seat");
        System.out.println("4. Book a tickets");
        System.out.println("5. View booking history");
        System.out.println("0. Exit");
        Scanner sc = new Scanner(System.in);
        int choice = 1;
        while (choice != 0){
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
            }
        }
        sc.close();
    }

    public void searchMovie(){

    }

    public ArrayList<ArrayList<String>> getHistory() {
        return null;
    }

    public boolean bookTicket(){
        // Write tickets information to database
        Scanner sc = new Scanner(System.in);
        System.out.println("These movies are currently showing.");
        this.movieManager.showMovies();
        System.out.println("Please choose one by enter movie Id:");
        String movieId = sc.nextLine().trim();
        Movie movie = this.movieManager.getMovieById(movieId);
        System.out.printf("These cinema are currently showing %s", movie.getName());
        this.cinemaManager.showCinema(movie.getName());
        System.out.println("Please choose one by enter movie Id:");
        return false;
    }

    public boolean cancelTicket(String ticketId) {
        //TODO: optional, to be implemented
        return false;
    }


}