package DTOobjects;

/**
 * Created by Emma og Thomas on 17-10-2016.
 */
public class User {

    // Attributter oprettes
    private int id;
    private String username;
    private String password;
    private int phonenumber;
    private String address;
    private String email;
    private boolean mobilePay;
    private boolean cash;
    private boolean transfer;
    private int type;


    //Construtor oprettes
    public User() {
    }

    // Getters og setters for attributterne laves
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

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

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

    public boolean isMobilePay() {
        return mobilePay;
    }

    public void setMobilePay(boolean mobilePay) {
        this.mobilePay = mobilePay;
    }

    public boolean isCash() {
        return cash;
    }

    public void setCash(boolean cash) {
        this.cash = cash;
    }

    public boolean isTransfer() {
        return transfer;
    }

    public void setTransfer(boolean transfer) {
        this.transfer = transfer;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
