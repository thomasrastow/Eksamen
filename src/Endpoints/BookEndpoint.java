package Endpoints;

import Controller.EndpointController;
import Controller.BookController;
import com.google.gson.Gson;
import com.sun.deploy.net.proxy.StaticProxyManager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import DTOobjects.Book;
import java.util.ArrayList;



/**
 * Created by krist on 17-10-2016.
 */


public class BookEndpoint {
    static EndpointController endpointController = new EndpointController();
    static BookController bookController = new BookController();

    public static class DeleteBookHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();
            Map<String, String> parms = endpointController.queryToMap(httpExchange.getRequestURI().getQuery());

            int id = Integer.parseInt(parms.get("id"));

            Gson gson = new Gson();

            if (id != 0 && BookController.deleteBook(id)) {
                response.append(gson.toJson(id));
            } else {
                response.append("Cannot delete book!");
            }

            endpointController.writeResponse(httpExchange, response.toString());

        }
    }

    // klasse som håndterer books kaldet

    public static class GetBooksHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            ArrayList<Book> books = bookController.getBooks();

            Gson gson = new Gson();

            if(books.isEmpty()) {
                response.append("No books found!");
            } else {
                response.append(gson.toJson(books));
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class CreateBookHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();
            Map<String, String> parms = endpointController.queryToMap(httpExchange.getRequestURI().getQuery());

            Book book = new Book();
            book.setISBN(Long.parseLong(parms.get("ISBN")));
            book.setTitle(parms.get("Title"));
            book.setEdition(parms.get("Edition"));
            book.setAuthor(parms.get("Author"));

            Gson gson = new Gson();

            if(book != null && bookController.createBook(book)) {
                response.append(gson.toJson(book));
            }
            else {
                response.append("Cannot create book!");

             // Burde det ikke hedde "Book already exists"?
            }

            endpointController.writeResponse(httpExchange, response.toString());

        }
    }
}

