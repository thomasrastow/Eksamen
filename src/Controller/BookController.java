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

    public boolean createBook(Book book) {

        ServiceImplementation db = new ServiceImplementation();

        boolean verifyRequest;

        verifyRequest = db.createBook(book);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }

    }


    public static boolean deleteBook(int id) {

        ServiceImplementation db = new ServiceImplementation();

        boolean verifyRequest;

        verifyRequest = db.deleteBook(id);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }

    }
}
