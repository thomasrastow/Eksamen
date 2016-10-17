import DTOobjects.Book;
import ServiveImplementation.ServiceImplementation;
import com.sun.deploy.uitoolkit.Window;
import java.util.ArrayList;
import java.util.Scanner;

package Controller;


public class BookController {

    Scanner input = new Scanner(System.in);
    Book book = new Book();
    ServiceImplementation serviceImpl = new ServiceImplementation();


    public void getBooks() {
        ArrayList<Book> books = serviceImpl.getBooks();
    }

    public void createBook(){

        serviceImpl.createBook(Book book);

    public void onFailure(Throwable caught) {
        System.out.println("Something went wrong!!");
        Window.alert("Could not create book");
    }

    public void onSuccess(Boolean result) {

        if(!result){
            Window.alert("Could not create book");
        } else {
            Window.alert("The book has been created");

        }

    }

    public void deleteBook() {


        }

    public void showBook() {

    }



    public void test() {


    }


}
