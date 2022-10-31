package entities;


public class Person {
    private String name;
    private int age;
    private String email;
    private String phoneNumber;
    private String account;
    private String password;

    public Person(){
        // when no information is available
        // treat as anonimous guess
        this.name = "Anonymous";
        this.age = 6; // Set age as kid to restrict booking some movies as guess
        this.email = "";
        this.phoneNumber = "";
        this.account = "guess";
        this.password = "admin123456";
    }

    public Person(String name, int age, String email, String phoneNumber, String account, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.account = account;
        this.password = password;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public String getAgeGroup() {
        if (age <= 13)
            return "PG13";
        else if (age <= 16)
            return "PG16";
        else if (age <= 18)
            return "M18";
        return "R21";
    }

    public String getEmail(){
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getAccount(){
        return this.account;
    }

    public String getPassword(){
        return this.password;
    }

    // TODO: Implement getters, setters methods
    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setAccount(String account){
        this.account = account;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
