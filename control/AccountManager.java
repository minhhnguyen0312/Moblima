package control;
import java.util.ArrayList;
import java.util.Arrays;

import entities.*;

import java.io.*;

public class AccountManager extends DBManager {

    public AccountManager(String root) throws IOException {
        this.root = root;
        // this.columns = new ArrayList<String>();
        this.data = new ArrayList<ArrayList<String>>();
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
                                    p.getName(), p.getAge(), p.getPhoneNumber(), p.getEmail());
        super.write(line, false);
    }

    public Person getPersonFromAccount(String account) {
        for (ArrayList<String> person : this.data){
            if (account.equals(person.get(1))){
                Person p = new Person(
                    person.get(3),
                    Integer.parseInt(person.get(4)),
                    person.get(5),
                    person.get(6),
                    person.get(1),
                    person.get(2)
                );
                return p;
            }
        }
        return null;
    }

    public Person getPersonFromUserId(String user_id) {
        return null;
    }
}