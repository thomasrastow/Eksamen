package DTOobjects;


import java.sql.Time;
import java.sql.Timestamp;

public class Ad {

    // Attributer for Ad oprettes
    private int adId;
    private int userId;
    private int isbn;
    private int price;
    private int rating;
    private int deleted;
    private String comment;
    private int locked;
    private Long bookISBN;
    private String bookTitle;
    private String bookEdition;
    private String bookAuthor;

    public Ad() {

    }

    // Getters og setters oprettes

    public int getId() {
        return adId;
    }

    public void setId(int adId) {
        this.adId = adId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userID) {
        this.userId = userId;
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

