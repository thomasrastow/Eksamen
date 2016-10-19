package Controller;

import java.util.List;
import java.util.Scanner;



import DTOobjects.Book;
import ServiceImplementation.ServiceImplementation;
//import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

public class BookController {

    public List<Book> getBooks() {

        ServiceImplementation db = new ServiceImplementation();

        List<Book> books = db.getBooks();

        try {
            books = db.getBooks();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }
}
/*
    Scanner input = new Scanner(System.in);


    ServiceImplementation serviceImpl = new ServiceImplementation();


    public ArrayList<Book> getBooks() {
        ArrayList<Book> books = serviceImpl.getBooks();
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


    public ArrayList<Book> books1 = new ArrayList<Book>();

   public void test() {

       books1.add(new Book(0, 1234567891234L, "big", "hey", "harryP"));

    }

        public void deleteBook(int id) {
            serviceImpl.getBooks(id).remove();
        }

        public void showBook(int id) {
            return serviceImpl.getBooks(id);
        }


}
*/