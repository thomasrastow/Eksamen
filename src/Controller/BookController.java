package Controller;
import DTOobjects.Book;
import ServiveImplementation.ServiceImplementation;
import java.util.ArrayList;

import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.title;
import com.sun.deploy.uitoolkit.Window;
import java.util.ArrayList;
import java.util.Scanner;

public class BookController {
    ServiceImplementation MySQL = new ServiceImplementation();

    Scanner input = new Scanner(System.in);
    Book book = new Book();
    ServiceImplementation serviceImpl = new ServiceImplementation();

    public class getBooks() {
    ArrayList<Book> books = ServiceImplementation.getBooks();

        }
    public void getBooks() {
        ArrayList<Book> books = serviceImpl.getBooks();



    }

    // public void createBook(){
public String createBook(int id, int ISBN, String title, String edition, String author) {

   if (ISBN==10) {
       return "ISBN has to be 10 digits";
   }

   if (title==""){
       return "Title cannot be blank";
   }

   if (edition==""){
       return "Edition cannot be blank";
   }

   if (author==""){
       return "Author cannot be blank";
   }

   return "OK";
}

    serviceImpl.createBook(Book book);

    public void onFailure(Throwable caught) {
        System.out.println("Something went wrong!!");
        Window.alert("Could not create book");
    }

    public void onSuccess(Boolean result) {

        if (!result) {
            Window.alert("The book has been created");

        } else {
            Window.alert("Could not create book");
        }

        books.add(Book);

    }



    public void deleteBook() {


        }

    public void showBook() {


        }

    public void test() {


    }


}
