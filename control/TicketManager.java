package control;
import java.util.ArrayList;

import entities.Ticket;

import java.util.*;
import java.io.*;

public class TicketManager extends DBManager<Ticket> {
    private String email;
    private static Integer totalTicketCount = 0;

    public TicketManager() {
        this.email = "admin";
        this.root = "assets/tickets/" + this.email + ".txt";
        // this.data = new ArrayList<ArrayList<String>>();
    }

    public TicketManager(String email){
        this.email = email;
        this.root = "assets/tickets/" + this.email + ".txt";
        // this.data = new ArrayList<ArrayList<String>>();
    }



    public void read() throws IOException{
        this.readDataFromUser();
    }

    public void readDataFromUser() throws IOException{
        if (this.email == "admin")
            super.read(this.root);
        else {
            Scanner sc = new Scanner(new FileInputStream(this.root));
            sc.nextLine();
            String nextLine;
            ArrayList<String> newEle = new ArrayList<String>();
            while (sc.hasNext()) {
                nextLine = sc.nextLine();
                for (String e: nextLine.trim().split(";", 10)){
                    newEle.add(e);
                }
                if (newEle.get(1) == this.email)
                    this.data.add(newEle);
            }

            sc.close();
        }
    }

    public void write(){

    }

    @Override
    public Ticket constructFromArr(ArrayList<String> ele) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<String> decodeFromObj(Ticket obj) {
        // TODO Auto-generated method stub
        return null;
    }
}
