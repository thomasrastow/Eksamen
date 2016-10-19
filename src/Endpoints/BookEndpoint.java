package Endpoints;

import Controller.EndpointController;
import Controller.BookController;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Map;



import DTOobjects.Book;

import java.util.List;



/**
 * Created by krist on 17-10-2016.
 */

public class BookEndpoint {
    EndpointController endpointController = new EndpointController();
    BookController bookController = new BookController();

    public class DeleteBookHandler implements HttpHandler {
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

    public class GetBooksHandler implements HttpHandler {
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
