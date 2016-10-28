package Endpoints;
import Controller.BookController;
import Controller.EndpointController;
import DTOobjects.Book;
import DTOobjects.Session;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/**
 * Created by krist
 */
public class BookEndpoint {
    static EndpointController endpointController = new EndpointController();
    static BookController bookController = new BookController();

    static Gson gson = new Gson();

    public static class DeleteBookHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            Session session = endpointController.checkSession(httpExchange);

            if (session != null && session.getUserType() == 1) {

                JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

                if (jsonObject.containsKey("isbn")) {

                    Long bookIsbn = ((Long) jsonObject.get("isbn"));

                    if (bookController.deleteBook(bookIsbn)) {
                        response.append(gson.toJson("Success: Book with ISBN: " + bookIsbn + " deleted"));
                    } else {
                        response.append("Failure: Can not delete book");
                    }
                } else {
                    response.append("Failure: Incorrect parameters");
                }
            } else {
                response.append("Failure: Session not verified");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class GetBooksHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            ArrayList<Book> books = bookController.getBooks();

            if (!books.isEmpty()) {
                response.append(gson.toJson(books));
            } else {
                response.append("Failure: Can not not find books");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class CreateBookHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            Session session = endpointController.checkSession(httpExchange);

            if (session != null && session.getUserType() == 1) {

                JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

                if (jsonObject.containsKey("isbn") & jsonObject.containsKey("title") &
                        jsonObject.containsKey("edition") & jsonObject.containsKey("author")) {

                    Book book = new Book();
                    book.setISBN((Long) jsonObject.get("isbn"));
                    book.setTitle((String) jsonObject.get("title"));
                    book.setEdition((String) jsonObject.get("edition"));
                    book.setAuthor((String) jsonObject.get("author"));

                    if (book != null && bookController.createBook(book)) {
                        response.append(gson.toJson(book));
                    } else {
                        response.append("Failure: Can not create book");
                    }
                } else {
                    response.append("Failure: Incorrect parameters");
                }
            } else {
                response.append("Failure: Session not verified");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

}