package Controller;
import java.util.ArrayList;
import DTOobjects.Book;
import ServiceImplementation.ServiceImplementation;

public class BookController {

    ServiceImplementation serviceImpl = new ServiceImplementation();

    public ArrayList<Book> getBooks() {

        ArrayList<Book> books = serviceImpl.getBooks();

        return books;
    }

    public boolean createBook(Book book) {
        ServiceImplementation serviceImpl = new ServiceImplementation();

        boolean verifyRequest;

        verifyRequest = serviceImpl.createBook(book);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }
    }


    public static showBook(int id){

        ServiceImplementation serviceImpl = new ServiceImplementation();

        boolean verifyRequest;

        verifyRequest = serviceImpl.showBook(id);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteBook(int id) {

        ServiceImplementation serviceImpl = new ServiceImplementation();

        boolean verifyRequest;

        verifyRequest = serviceImpl.deleteBook(id);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }

    }
}
