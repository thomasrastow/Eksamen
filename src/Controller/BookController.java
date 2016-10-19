package Controller;
import java.util.List;
import DTOobjects.Book;
import ServiceImplementation.ServiceImplementation;

public class BookController {

    ServiceImplementation db = new ServiceImplementation();

    public List<Book> getBooks() {

        List<Book> books = db.getBooks();

        try {
            books = db.getBooks();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    public String createBook(long ISBN, String title, String edition, String author) {

        if (ISBN==13) {
            return "ISBN has to be 13 digits";
        }

        if (title.contains("")){
       return "Title cannot be blank";
        }

        if (edition.contains("")){
       return "Edition cannot be blank";
        }

        if (author.contains("")){
       return "Author cannot be blank";
        }

   Book book = new Book (0, ISBN, title, edition, author);

        ServiceImplementation serviceImpl = new ServiceImplementation();

        try {
            serviceImpl.createBook(book);
        }

        catch (Exception e) {
            e.printStackTrace();
        }

   return "Error";

    }

    public boolean deleteBook(int id) {
        boolean verifyRequest;

        verifyRequest = db.deleteBook(id);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }
    }

    public Book showBook(int id) {
            return db.getBooks().remove(id);
        }


}
