import java.util.*;

import boundaries.Loger;
import boundaries.Register;

import java.text.ParseException;
import java.io.IOException;

public class VendorApp{
    
    public static void main(String args[]) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);

        int sel = -1;

        System.out.println("Welcome to MOBLIMA Booking Center");
        System.out.println("================================================================");
        System.out.println("1. Login as Staff");
        System.out.println("2. Login as Movie-goer");
        System.out.println("3. Register new movie-goer account.");
        System.out.println("0. Exit");
        while (sel != 0){
            System.out.print("Enter your choice: ");
            sel = sc.nextInt();
            if (sel == 0)
                break;

            switch(sel) {
                case 0: break;
                case 1: 
                    Loger.main("assets/staffs.txt", "staffapp");
                    break;
                case 2: 
                    Loger.main("./assets/clients.txt", "moviegoer");
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

// class DateTime{
//     private int day;
//     private int month;
//     private int year;
//     private int hour;
//     private int minute;
//     private boolean isHoliday;

//     public DateTime(){
//         this.day=1;
//         this.month=1;
//         this.year=1;
//         this.hour = 0;
//         this.minute=59;
//         this.isHoliday = false;
//     }

//     public DateTime(int day, int month, int year, int hour, int minute){
//         this.day = day;
//         this.month = month;
//         this.year = year;
//         this.hour = hour;
//         this.minute = minute;
//         this.isHoliday = false;
//     }

//     public DateTime(int day, int month, int year, int hour, int minute, boolean isHoliday){
//         this.day = day;
//         this.month = month;
//         this.year = year;
//         this.hour = hour;
//         this.minute = minute;
//         this.isHoliday = isHoliday;
//     }

//     public boolean isHoliday(){
//         return this.isHoliday;
//     }

//     public String getDateFormatedString(){
//         return String.format("%d-%d-%d %d:%d", this.day, this.month, this.year, this.hour, this.minute);
//     }

//     public void setFromString(String dateString){
//         String[] d = dateString.split(" ");
//         String[] date = d[0].split("-");
//         String[] time = d[1].split(":");
//         this.day = Integer.parseInt(date[0]);
//         this.month = Integer.parseInt(date[1]);
//         this.year = Integer.parseInt(date[2]);

//         this.hour = Integer.parseInt(time[0]);
//         this.minute = Integer.parseInt(time[1]);
//     }
// }

// enum AgeRating {
//     G,
//     PG13,
//     PG16,
//     NC16,
//     M18,
//     R21
// }

// enum Status {
//     ComingSoon("ComingSoon"),
//     Preview("Preview"),
//     NowShowing("NowShowing"),
//     EndOfShowing("EndOfShowing");
    
//     private final String status;
//     private Status(String status){
//         this.status = status;
//     }

//     public String toString(){
//         return status;
//     }
// }