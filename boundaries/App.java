package boundaries;


import java.io.IOException;
import java.text.ParseException;

import entities.Person;

public abstract class App {
    public Person user;
    public App() {
        this.user = new Person();
    }
    public App(Person user){
        this.user = user;
    }

    public abstract void runningSession() throws IOException, ParseException;
}