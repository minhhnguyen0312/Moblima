package boundaries;

import java.io.IOException;
import java.text.ParseException;

import entities.Person;

/**
 * Represents a generic app.
 */
public abstract class App {
    /**
     * The person currently using this app.
     */
    public Person user;

    public App() {
        this.user = new Person();
    }

    public App(Person user) {
        this.user = user;
    }

    /**
     * The main method of the app; call this to start executing.
     * 
     * @throws IOException
     * @throws ParseException
     */
    public abstract void runningSession() throws IOException, ParseException;
}