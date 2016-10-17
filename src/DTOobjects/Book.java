package DTOobjects;

/**
 * Created by krist on 17-10-2016.
 */
public class Book {


    //Attributer for book oprettes
    private int id;
    private int ISBN;
    private String title;
    private String edition;
    private String author;


    //Constructor oprettes
    public Book (int id, int ISBN, String title, String edition, String author) {

    this.id = id;
    this.ISBN = ISBN;
    this.title = title;
    this.edition = edition;
    this.author = author;

    }


     // Getters og setters oprettes
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
        return title;
    }

    public void setTitel(String titel) {
        this.title = titel;
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
