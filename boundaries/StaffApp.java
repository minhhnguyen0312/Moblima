package boundaries;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import control.*;
import entities.*;
public class StaffApp extends App {
    // private TicketManager ticketManager;
    private MoviesManager movieManager;
    private CinemaManager cinemaManager;
    // private Person user;
    public StaffApp(Person user, CinemaManager c, MoviesManager m) throws IOException, ParseException{
        // this.user = user;
        this.movieManager = m;
        this.cinemaManager = c;
    }

    @Override
    public void runningSession() throws IOException, ParseException {
            Scanner sc = new Scanner(System.in);
            int choice = 1;
            while (choice != 0){
                System.out.println("\nWelcome to MOBLIMA Admin App");
                System.out.println("================================================================");
                System.out.println("1. Create/Update/Remove movie listing.");
                System.out.println("2. Create/Update/Remove cinema showtimes and the movies to be shown");
                System.out.println("3. System settings.");
                System.out.println("4. See best sales movies");
                System.out.println("5. See best rating movies");
                System.out.println("0. Exit");
                choice = sc.nextInt();
                int c;
                sc.nextLine();
                switch(choice){
                    case 0:
                        break;
                    case 1: 
                        System.out.println("==============================================================");
                        System.out.println("1. Add new movie.");
                        System.out.println("2. Remove movie.");
                        System.out.println("3. Update movie");
                        System.out.println("4. Cancel");
                        c = sc.nextInt();
                        sc.nextLine();
                        if (c == 1){
                            addMovie(sc);
                        }
                        else if (c == 2){
                            removeMovie(sc);
                        }
                        else if (c == 3){
                            updateMovie(sc);
                        }
                        break;
                    case 2:
                        System.out.println("==============================================================");
                        System.out.println("1. Add new schedule.");
                        System.out.println("2. Remove schedule.");
                        System.out.println("3. Update schedule.");
                        System.out.println("4. Cancel");
                        c = sc.nextInt();
                        sc.nextLine();
                        if (c == 1){
                            addSchedule(sc);
                        }
                        else if (c == 2){
                            removeSchedule(sc);
                        }
                        else if (c == 3){
                            updateSchedule(sc);
                        }
                        break;
                    case 3:
                        systemSetting(sc);
                        break;
                    case 4:
                        movieManager.showBestSales();
                        break;
                    case 5:
                        movieManager.showBestRating();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
        }
    }

    public void addMovie(Scanner sc) throws IOException{
        System.out.println("Please enter movie name");
        String name = sc.nextLine().trim();
        System.out.println("Please enter movie's director name:");
        String director = sc.nextLine().trim();
        Movie m = new Movie(name, "ComingSoon", 1f, 0, director);
        movieManager.addMovie(m);
        System.out.println("Movie added successfully!");
    }

    public void removeMovie(Scanner sc) throws IOException{
        movieManager.movieListing();
        System.out.println("Please enter the movie name to remove:");
        String name = sc.nextLine().trim();
        try{
        movieManager.remove("movieName", name, true);
            System.out.printf("Movie %s has been removed\n", name);
        }
        catch (Exception e){
            System.out.printf("There is no such movie named %s.", name);
        }
    }

    public void updateMovie(Scanner sc) throws IOException {
        movieManager.movieListing();
        System.out.println("Please choose one movie to update by enter its id.");
        Movie m = movieManager.getMovieById(sc.nextInt());
        sc.nextLine();
        int c = 1;
        while (c != 0) {
            System.out.println("Select property you want to update.");
            System.out.println("1. Showing status.");
            System.out.println("2. Sypnosis.");
            System.out.println("3. Add actor");
            System.out.println("0. End Editing.");
            c = sc.nextInt();
            sc.nextLine();
            switch(c) {
                case 0: break;
                case 1:
                    System.out.println("Please enter the movie status (NowShowing/Preview/ComingSoon/EndOfShowing");
                    m.setStatus(sc.nextLine().trim());
                    break;
                case 2:
                    System.out.println("Enter movie sypnosis:");
                    m.setSypnosis(sc.nextLine().trim());
                    break;
                case 3:
                    System.out.println("Enter caster name");
                    m.addCast(sc.nextLine().trim());
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        m.updateAll();
        movieManager.remove("movieName", m.getName(), true);
        movieManager.addMovie(m);
    }

    public void addSchedule(Scanner sc) throws IOException {
        System.out.println("The following movies are currently showing.");
        // Showing list of Movie to get movie name
        movieManager.showMovies();
        System.out.println("Please choose a movie by enter its id.");
        Movie m = movieManager.getMovieById(sc.nextInt());
        // Showing list of Cinema to get Cinema
        cinemaManager.showAllCinemas();
        Cinema c = cinemaManager.getCinemaById(sc.nextInt());
        sc.nextLine();
        // Get DateTime in format - default = not holiday
        System.out.println("Enter date in format dd-MM-YYY HH:mm");
        String d = sc.nextLine().trim() + " N";
        // Set schedule Id
        int schId = c.getNextSchId();
        Schedule sch = new Schedule(schId, m.getName(), c.getName(), c.getCineplex(), d, "normal");
        c.addSchedule(sch);
    }

    public void removeSchedule(Scanner sc) throws IOException{
        System.out.println("Please choose a cinema by enter its id.");
        cinemaManager.showAllCinemas();
        Cinema c = cinemaManager.getCinemaById(sc.nextInt());
        sc.nextLine();
        System.out.println("Please choose a schedule by enter its id.");
        c.printSchedule();
        Schedule sch = c.getScheduleById(sc.nextInt());
        sc.nextLine();
        c.removeSchedule(sch, true);
    }

    public void updateSchedule(Scanner sc) throws IOException{
        System.out.println("Please choose a cinema by enter its id.");
        cinemaManager.showAllCinemas();
        Cinema c = cinemaManager.getCinemaById(sc.nextInt());
        sc.nextLine();
        System.out.println("Please choose a schedule by enter its id.");
        c.printSchedule();
        Schedule sch = c.getScheduleById(sc.nextInt());
        sc.nextLine();
        if (sch.hasTicket()){
            System.out.println("Someones already have book a seat in this schedule. Do you want to change?");
        }
        // To do: implement this function in here (no calling)
        c.removeSchedule(sch, false);
        int choice = -1;
        while (choice != 0){
            System.out.println("Choose a property you want to update");
            System.out.println("1. Schedule time");
            System.out.println("2. Movie type");
            System.out.println("3. Movie name");
            System.out.println("0. End Editing");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 0: break;
                case 1:
                    System.out.println("Enter date in format dd-MM-YYY HH:mm (N/H)");
                    System.out.println("Where H means it is holiday.");
                    String d = sc.nextLine().trim();
                    sch.setTime(d);
                    break;
                case 2:
                    System.out.println("Enter how the movie is show (movie3D / normal)");
                    String type = sc.nextLine().trim();
                    sch.setType(type);
                    break;
                case 3:
                    System.out.println("Choose a new movie name by enter its id");
                    movieManager.showMovies();
                    Movie m = movieManager.getMovieById(sc.nextInt());
                    sc.nextLine();
                    sch.setMovieName(m.getName());
                    break;
                
            }
        }
        c.addSchedule(sch);
    }

    public void systemSetting(Scanner sc) {
        System.out.println("Please choose a property you want to set");
        System.out.println("1. Ticket price");
        System.out.println("2. Allow customers access on Movie ticket sales");
        System.out.println("0. End Editing");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1){
            System.out.println("Please enter the price (correct to 1 decimals place");
            Ticket.setBasePrice(sc.nextFloat());
            sc.nextLine();
        }
        if (choice == 2){
            System.out.println("Allow customers to choose how they view top 5 movies? (1/2/3)");
            System.out.println("1. By ticket sales");
            System.out.println("2. By rating");
            System.out.println("3. Both");
            MoviesManager.allowAccess = sc.nextInt(); sc.nextLine();
        }
    }
}
