package control;
import java.util.ArrayList;
import java.util.Arrays;

import entities.*;

import java.io.*;

public class AccountManager extends DBManager<Person> {

    public AccountManager(String root) throws IOException {
        this.root = root;
        // this.columns = new ArrayList<String>();
        this.data = new ArrayList<Person>();
        this.columns = new ArrayList<String>(Arrays.asList("id", "account", "password", "name", "age", "email", "phoneNumber"));
        this.read(root);
    }

    public void read(String root) throws IOException {
        super.read(root);
    }

    public void createAccount(Person p) throws IOException{
        int id = this.data.size();
        String line = String.format("%d;%s;%s;%s;%d;%s;%s",
                                    id + 1, p.getAccount(), p.getPassword(),
                                    p.getName(), p.getAge(), p.getEmail(), p.getPhoneNumber());
        super.write(line, true);
        String filename = "assets/tickets/" + p.getAccount() + ".txt";
        File f = new File(filename);
        f.createNewFile();
        FileWriter w = new FileWriter(filename);
        w.write("ticket_id|time|email|movie|cinema|ageGroup|movieType");
        w.close();
    }

    public Person getPersonFromAccount(String account) {
        for (Person person : this.data){
            if (account.equals(person.getAccount())){
                return person;
            }
        }
        return null;
    }

    @Override
    public Person constructFromArr(ArrayList<String> ele) throws NumberFormatException, IOException {
        System.out.println("Retrieving peronal information.");
        Person m = new Person(
            ele.get(3),
            Integer.parseInt(ele.get(4)),
            ele.get(5),
            ele.get(6),
            ele.get(1),
            ele.get(2)
        );
        return m;
    }

    @Override
    public ArrayList<String> decodeFromObj(Person obj) {
        // TODO Auto-generated method stub
        return null;
    }
}