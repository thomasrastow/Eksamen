package Controller;

        import DTOobjects.Book;
        import ServiceImplementation.ServiceImplementation;
        import java.util.ArrayList;
/**
 * Created by krist on 17-10-2016.
 */
public class BookController {

    ServiceImplementation serviceImpl = new ServiceImplementation();

    public ArrayList<Book> getBooks() {

        ArrayList<Book> books = serviceImpl.getBooks();

        return books;
    }

    public boolean createBook(Book book) {

        boolean verifyRequest = serviceImpl.createBook(book);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteBook(Long isbn) {

        boolean verifyRequest = serviceImpl.deleteBook(isbn);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }
    }

}