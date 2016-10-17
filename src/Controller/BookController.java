package Controller;
import DTOobjects.Book;
import ServiveImplementation.ServiceImplementation;
import java.util.ArrayList;

public class BookController {
ServiceImplementation MySQL = new ServiceImplementation();


    public void getBooks() {
    ArrayList<Book> books = ServiceImplementation.getBooks();

        }

    public void createBook(){

        Service

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

    public void deleteBook() {


        }

    public void showBook() {


        }

    public void test() {


    }


}
