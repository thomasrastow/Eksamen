package DTOobjects;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;


public class Ad {

    // Attributer for Ad oprettes
    private int id;
    private int price;
    private int rating;
    private int userID;
    private int bookID;
    private byte deleted;
    private String comment;
    private boolean locked;
    private java.sql.Timestamp time;




    //Constructor oprettes
    public Ad (int id, int price, int rating, int userID, int bookID, byte deleted, String comment, boolean locked,
               java.sql.Timestamp time) {

        this.id = id;
        this.price = price;
        this.rating = rating;
        this.userID = userID;
        this.bookID = bookID;
        this.deleted = deleted;
        this.comment = comment;
        this.locked = locked;
        this.time = time;

    }




    // Getters og setters oprettes

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public byte getDeleted() {
        return deleted;
    }

    public void setDeleted(byte deleted) {
        this.deleted = deleted;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }


}

