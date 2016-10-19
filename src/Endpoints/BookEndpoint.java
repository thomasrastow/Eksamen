package Endpoints;

import Main.Run;

import DTOobjects.Book;
import ServiceImplementation.ServiceImplementation;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Controller.EndpointController;
import Controller.BookController;

import static java.lang.Integer.parseInt;

/**
 * Created by krist on 17-10-2016.
 */



public class BookEndpoint {

    static EndpointController endpointController = new EndpointController();
    static BookController bookController = new BookController();

    // klasse som håndterer books kaldet

    public static class GetBooksHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            List<Book> books = bookController.getBooks();

            Gson gson = new Gson();

            if(books.isEmpty()) {
                response.append("No books found!");
            } else {
                response.append(gson.toJson(books));
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

}
