package DTOobjects;

/**
 * Created by Emma og Thomas on 17-10-2016.
 */
public class User {

    // Attributter oprettes
    private int id;
    private String username;
    private String password;
    private int phoneNumber;
    private String address;
    private String email;
    private int mobilepay;
    private int cash;
    private int transfer;
    private int type;


    //Construtor oprettes
    public User() {
    }

    // Getters og setters for attributterne laves
    public User( int id, String username, String password, int phonenumber, String address,
                 String email, int mobilepay, int cash, int transfer, int type) {


    this.id = id;
    this.username = username;
    this.password = password;
    this.phoneNumber = phonenumber;
    this.address = address;
    this.email = email;
    this.mobilepay = mobilepay;
    this.cash = cash;
    this.transfer = transfer;
    this.type = type;
    }


    // Getters og setters oprettes


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

<<<<<<< HEAD
    public int getPhonenumber() {
        return phoneNumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phoneNumber = phonenumber;
    }
=======
    public int getPhonenumber() { return phonenumber; }

    public void setPhonenumber(int phonenumber) { this.phonenumber = phonenumber; }
>>>>>>> UserBranch

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobilepay() { return mobilepay; }

    public void setMobilepay(int mobilepay) { this.mobilepay = mobilepay; }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getTransfer() {
        return transfer;
    }

    public void setTransfer(int transfer) {
        this.transfer = transfer;
    }

    public int getType() { return type; }

    public void setType(int type) {
        this.type = type;
    }

}


