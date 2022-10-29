package boundaries;
// package src;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import control.AccountManager;
import entities.Person;

public class Loger {
    private static AccountManager accountManager;

    public static void main(String root, String use_case, Scanner sc) throws IOException, ParseException  {
        accountManager = new AccountManager("assets/clients.txt");
        login(use_case, sc);
    }

    public static void login(String use_case, Scanner sc){
        String account, password;
        Person p;
        App app;
        int choice = 1;
        System.out.println("Please login first!");
        System.out.println("1. Login with existed account.");
        System.out.println("2. Exit");
        while (choice != 2){
            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1){
                System.out.println("Enter your username:");
                account = sc.nextLine().trim();
                p = accountManager.getPersonFromAccount(account);
                if (p == null) {
                    System.out.println("The account does not exists. Please try again.");
                    System.out.println("You might want to re-enter (press 1) or exit (press 2).");
                    continue;
                }
                System.out.println("Enter your password:");
                password = sc.nextLine().trim();
                if (password.equals(p.getPassword())){
                    if (use_case.equals("moviegoer")){
                        System.out.println("Login as " + p.getName());
                        app = new MovieGoerApp(p);
                        app.runningSession();
                    }
                    break;
                }
            }
            else break;
        }
    }
}
