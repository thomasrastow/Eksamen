package Controller;
//import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import ServiceImplementation.*;
public class BookController {

    private static ServiceImplementation serviceImpl = new ServiceImplementation();

    public static ArrayList<Book> getBooks() {

        ArrayList<Book> books = serviceImpl.getBooks();

        return books;
    }

    public static boolean createBook(Book book) {
        ServiceImplementation serviceImpl = new ServiceImplementation();

        boolean verifyRequest;

        verifyRequest = serviceImpl.createBook(book);

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
