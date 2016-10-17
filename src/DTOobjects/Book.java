package DTOobjects;

/**
 * Created by krist on 17-10-2016.
 */
public class Book {

    public int id;
    public int ISBN;
    public String titel;
    public String edition;
    public String author;

public Book (int id, int ISBN, String title, String edition, String author) {

    this.id = id;
    this.ISBN = ISBN;
    this.titel = titel;
    this.edition = edition;
    this.author = author;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
