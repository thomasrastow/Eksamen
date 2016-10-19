package Controller;
import java.util.ArrayList;
import java.util.List;
import DTOobjects.Book;
import ServiceImplementation.ServiceImplementation;

public class BookController {

    ServiceImplementation db = new ServiceImplementation();

    public ArrayList<Book> getBooks() {

        ArrayList<Book> books = db.getBooks();

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
