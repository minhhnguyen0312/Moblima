package boundaries;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import control.*;
import entities.*;

public class MovieGoerApp extends App {
    private TicketManager ticketManager;
    private MoviesManager movieManager;
    private CinemaManager cinemaManager;
    private Person user;

    public MovieGoerApp(){

    }

    public MovieGoerApp(Person user, CinemaManager c, MoviesManager m) throws IOException, ParseException{
        this.user = user;
        this.ticketManager = new TicketManager(user.getAccount());
        this.movieManager = m;
        this.cinemaManager = c;
        // runningSession();
    }

    @Override
    public void runningSession() throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        int choice = 1;
        while (choice != 0){
            System.out.println("\nWelcome to MOBLIMA Movie-Goer App");
            System.out.println("================================================================");
            System.out.println("1. Search for movies.");
            System.out.println("2. View movie details.");
            System.out.println("3. Check seat availability");
            System.out.println("4. Book a tickets");
            System.out.println("5. View booking history");
            System.out.println("6. List top 5 movies ranking.");
            System.out.println("0. Exit");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1: 
                    movieManager.movieListing();
                    break;
                case 2:
                    // movieManager.movieListing();
                    System.out.println("What movie do you want to see in detail.");
                    System.out.println(movieManager.getMovieByName(sc.nextLine().trim()).rprStringToUser());
                    break;
                case 3:
                case 4:
                    bookTicket(sc);
                    break;
                case 5:
                    ticketManager.showHistory();
                    break;
                case 6:
                    if (MoviesManager.allowAccess == 1)
                        movieManager.showBestSales();
                    else if(MoviesManager.allowAccess == 2)
                        movieManager.showBestRating();
                    else {
                        System.out.println("Choose one of the options:");
                        System.out.println("1. List by total sales");
                        System.out.println("2. List by rating");
                        System.out.println("Press any other key to exit.");
                        int cc = sc.nextInt(); sc.nextLine();
                        if (cc == 1) movieManager.showBestSales();
                        if (cc == 2) movieManager.showBestRating();
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public boolean bookTicket(Scanner sc) throws IOException{
        // Write tickets information to database
            
            System.out.println("These movies are currently showing.");
            this.movieManager.showMovies();
            System.out.println("Please choose one by enter movie Id: (-1 to cancel)");
            Integer movieId = sc.nextInt();
            sc.nextLine();
            if (movieId == -1) return false;
            Movie movie = this.movieManager.getMovieById(movieId);
            
            System.out.printf("These cinema are currently showing %s\n", movie.getName());
            this.cinemaManager.showCinema(movie.getName());
            System.out.println("Please choose one by enter cinema Id (-1 to cancel):");
            Integer cinemaId = sc.nextInt();
            if (cinemaId == -1) return false;
            Cinema c = this.cinemaManager.getCinemaById(cinemaId);
            sc.nextLine();
            
            System.out.printf("Schedule for %s in %s\n", movie.getName(), c.getName());
            c.printMovieSchedule(movie.getName());
            System.out.println("Please choose one by enter schedule id (-1 to cancel):");
            Integer scheduleId = sc.nextInt();
            if (scheduleId == -1) return false;
            sc.nextLine();
            Schedule sch = c.getScheduleById(scheduleId);

            sch.presentSeat();
            System.out.println("Please choose a seat by enter its row and colum. [X] are occupied seats");
            Integer row = sc.nextInt(), col = sc.nextInt();
            sc.nextLine();

            System.out.println("Creating ticket...");
            Ticket t = new Ticket(user.getName(), movie.getName(), c.getName(), sch.getDateTime(), user.getAgeGroup(), sch.getMovieType());
            System.out.println("Ticket created successfully. Here is your receipt:");
            
            String receipt = t.getReceipt();
            System.out.println(receipt);
            System.out.println("Please enter \"confirm\" to pay");
            if (sc.nextLine().trim().equals("confirm")){
                ticketManager.addTicket(t, true);
                sch.occupiedSeat(row, col);
                movie.incrSales();
                movieManager.remove("movieName", movie.getName(), true);
                movieManager.addMovie(movie);
            }
        
        return false;
    }

    public boolean cancelTicket(String ticketId) {
        //TODO: optional, to be implemented
        return false;
    }


}