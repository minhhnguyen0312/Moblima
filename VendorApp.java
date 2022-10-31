import java.util.*;

import boundaries.Loger;
import boundaries.MovieGoerApp;
import boundaries.Register;
import control.CinemaManager;
import control.MoviesManager;
import entities.Person;

import java.text.ParseException;
import java.io.IOException;

public class VendorApp{
    public static void main(String args[]) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        int sel = -1;
        CinemaManager cinemaManager = new CinemaManager();
        MoviesManager moviesManager = new MoviesManager(); 
        Person p;
        
        System.out.println("Welcome to MOBLIMA Booking Center");
        System.out.println("================================================================");
        System.out.println("1. Login as Staff");
        System.out.println("2. Login as Movie-goer");
        System.out.println("3. Register new movie-goer account.");
        System.out.println("0. Exit");
        // NOTE: Passing too much variables can cause problems when adding new class
        // Preventing further development???? Hard to debug.
        while (sel != 0){
            System.out.print("Enter your choice: ");
            sel = sc.nextInt();
            if (sel == 0)
                break;

            switch(sel) {
                case 0: break;
                case 1: 
                    p = Loger.main("assets/staffs.txt", "staffapp");
                    break;
                case 2: 
                    p = Loger.main("./assets/clients.txt", "moviegoer");
                    if (p != null){
                        (new MovieGoerApp(p, cinemaManager, moviesManager)).runningSession();
                    }
                    break;
                case 3:
                    Register.main("./assets/clients.txt");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
        sc.close();
    }
}