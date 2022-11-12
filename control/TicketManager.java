package control;
import java.util.ArrayList;

import entities.Ticket;

// import java.util.*;
import java.io.*;

public class TicketManager extends DBManager<Ticket> {
    private String email;
    private static Integer totalTicketCount = 0;

    public TicketManager() {
        this.email = "admin";
        this.root = "assets/tickets/" + this.email + ".txt";
        // this.data = new ArrayList<ArrayList<String>>();
    }

    public TicketManager(String email) throws IOException{
        this.email = email;
        this.root = "assets/tickets/" + this.email + ".txt";
        this.data = new ArrayList<Ticket>();
        super.read(this.root);
    }

    public void addTicket(Ticket t, Boolean append) throws IOException{
        super.write(String.format("%d;%s",totalTicketCount, t.toString()), append);
        this.data.add(t);
        totalTicketCount += 1;
    }

    public void showHistory() {
        System.out.println("Showing personal booking history...");
        for (Ticket t: this.data){
            System.out.println(t.toString("\t"));
        }
        System.out.println();
    }

    @Override
    public Ticket constructFromArr(ArrayList<String> ele) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        Ticket t = new Ticket(this.email, 
                            ele.get(3), 
                            ele.get(4),
                            ele.get(1), 
                            ele.get(5),
                            ele.get(6)
                            );
        return t;
    }

    @Override
    public ArrayList<String> decodeFromObj(Ticket obj) {
        // TODO Auto-generated method stub
        return null;
    }
}
