package Controller;

import DTOobjects.Book;
import ServiceImplementation.ServiceImplementation;
import java.util.ArrayList;

public class BookController {
    private static ServiceImplementation serviceImpl = new ServiceImplementation();
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
    public static boolean deleteBook(int id) {
        boolean verifyRequest;
        verifyRequest = serviceImpl.deleteBook(id);
        if (verifyRequest) {
            return true;
        } else {
            return false;
        }
    }
}