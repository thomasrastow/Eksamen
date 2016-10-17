package DTOobjects;


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
    private java.sql.Timestamp time;




    //Constructor oprettes
    public Ad (int id, int price, int rating, int userID, int bookID, int deleted, String comment, int locked,
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

    public int Locked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }


}

