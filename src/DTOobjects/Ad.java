package DTOobjects;


import java.sql.Time;
import java.sql.Timestamp;

public class Ad {

    // Attributer for Ad oprettes
    private int id;
    private int price;
    private int rating;
    private int userID;
    private int bookID;
    private int deleted;
    private String comment;
    private int locked;
    private Timestamp time;
    private Long bookISBN;
    private String bookTitle;
    private String bookEdition;
    private String bookAuthor;

    public Ad() {

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

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Timestamp getTime () {
        return time;
    }

    public void setBookISBN(Long bookISBN) {
        this.bookISBN = bookISBN;
    }

    public Long getBookISBN() {
        return bookISBN;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookEdition(String bookEdition) {
        this.bookEdition = bookEdition;
    }

    public String getBookEdition() {
        return bookEdition;
    }
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }
}

