package boundaries;


import entities.Person;

public class App {
    public Person user;
    public App() {
        this.user = new Person();
    }
    public App(Person user){
        this.user = user;
    }

    public void runningSession(){
        return;
    }
}