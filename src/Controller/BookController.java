import DTOobjects.Book;
import ServiveImplementation.ServiceImplementation;
import com.sun.org.apache.xpath.internal.SourceTreeManager;

import java.security.Provider;
import java.util.InputMismatchException;
import java.util.Scanner;

package Controller;


public class BookController {

    Scanner input = new Scanner(System.in);
    Book book = new Book(int, int, String, String, String);
    ServiceImplementation serviceImpl = new ServiceImplementation(int, int, String, String, String);


    public void getBooks() {


        }

    public void createBook(){

        serviceImpl.createBook(Book book);

    public void onFailure(Throwable caught) {
        System.out.println("Something went wrong!!");
        Window.alert("Could not create book");
    }

    public void onSuccess(Boolean result) {

        if(!result){
            Window.alert("Could not create the book");
        } else {
            Window.alert("The book has been created");

        }

    }
}


    public void deleteBook() {


        }

    public void showBook() {


        }

    public void test() {


    }


}
