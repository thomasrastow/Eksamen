package Controller;

import DTOobjects.Book;
import ServiveImplementation.ServiceImplementation;
import java.util.ArrayList;
import java.util.Scanner;

public class BookController {

    Scanner input = new Scanner(System.in);


    ServiceImplementation serviceImpl = new ServiceImplementation();


    public void getBooks() {
        ArrayList<Book> books = serviceImpl.getBooks();
        return books;
    }

    //Opretter validering

    public String createBook(int ISBN, String title, String edition, String author) {

        if (ISBN==10) {
            return "ISBN has to be 10 digits";
        }

        if (title.contains("")){
       return "Title cannot be blank";
        }

        if (edition==""){
            return "Edition cannot be blank";
        }

        if (author==""){
            return "Author cannot be blank";
        }

        Book book = new Book (0, ISBN, title, edition, author);

        //gem i database??

   return "OK";

    }

    public void deleteBook(int id) {
        serviceImpl.getBook(id).remove();
    }

    public void showBook(int id) {
        return serviceImpl.getBooks(id);
        }
}
