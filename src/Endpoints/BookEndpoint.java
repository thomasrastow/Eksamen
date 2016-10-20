package Endpoints;
        import Controller.BookController;
        import Controller.EndpointController;
        import DTOobjects.Book;
        import com.google.gson.Gson;
        import com.sun.net.httpserver.HttpExchange;
        import com.sun.net.httpserver.HttpHandler;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.Map;
/**
 * Created by krist
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

            if (bookController.deleteBook(id)) {
                response.append(gson.toJson(id));
            } else {
                response.append("Cannot delete book!");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    // klasse som håndterer get books kaldet
    public static class GetBooksHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            ArrayList<Book> books = bookController.getBooks();

            Gson gson = new Gson();

            if (books.isEmpty()) {
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
            book.setTitle(parms.get("title"));
            book.setEdition(parms.get("edition"));
            book.setAuthor(parms.get("author"));

            Gson gson = new Gson();

            if (book != null && bookController.createBook(book)) {
                response.append(gson.toJson(book));
            } else {
                response.append("Cannot create book!");
                System.out.println(gson.toJson(book));
                // Burde det ikke hedde "Book already exists"
            }

            endpointController.writeResponse(httpExchange, response.toString());
            //https://localhost:8000/createbook?ISBN=1234567891111&title=hehj123&edition=1&author=jens
        }
    }

    public static class SearchBooksHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            Map<String, String> parms = endpointController.queryToMap(httpExchange.getRequestURI().getQuery());
            StringBuilder response = new StringBuilder();

            ArrayList<Book> books = new ArrayList<>();

           if(parms.containsKey("title")) {
                books = bookController.searchBooksTitle(parms.get("title"));
            } else if (parms.containsKey("author")) {
                books = bookController.searchBooksAuthor(parms.get("author"));
            }

            Gson gson = new Gson();

            if (books.isEmpty()) {
                response.append("No books found!");
            } else {
                response.append(gson.toJson(books));
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }
}