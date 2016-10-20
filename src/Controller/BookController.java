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

        boolean verifyRequest;

        verifyRequest = serviceImpl.createBook(book);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteBook(int id) {

        boolean verifyRequest;

        verifyRequest = serviceImpl.deleteBook(id);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Book> searchBooksTitle(String title) {

        ArrayList<Book> books = serviceImpl.searchBooksTitle(title);

        return books;

    }

    public ArrayList<Book> searchBooksAuthor(String author) {

        ArrayList<Book> books = serviceImpl.searchBooksAuthor(author);

        return books;

    }
}