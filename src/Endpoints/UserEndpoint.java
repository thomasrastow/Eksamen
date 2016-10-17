package Endpoints;

import Main.Run;

import DTOobjects.User;
import ServiveImplementation.ServiceImplementation;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by krist on 17-10-2016.
 */
public class UserEndpoint {

    Run run = new Run();

    public static class LoginHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();
            Map<String,String> parms = queryToMap(httpExchange.getRequestURI().getQuery());
            response.append("<html><body>");

            String username = parms.get("username");
            String password = parms.get("password");

            Gson gson = new Gson();
            ServiceImplementation db = new ServiceImplementation();
            User user;

            user = db.dbLogin(username,password);
            ArrayList<User> users = new ArrayList<>();
            users.add(user);
            String responseSQL = gson.toJson(users);

            response.append(responseSQL);

            if(user == null) {
                response.append("bruger findes ikke");
            }
            response.append("</body></html>");
            writeResponse(httpExchange, response.toString());
        }
    }
    public static class GetUsersHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            response.append("<html><body>");

            Gson gson = new Gson();
            ServiceImplementation db = new ServiceImplementation();
            User user;

            ArrayList<User> users;
            users = db.getUsers();

            response.append(gson.toJson(users));

            if(users == null) {
                response.append("Liste findes ikke");
            }
            response.append("</body></html>");
            writeResponse(httpExchange, response.toString());
        }
    }

    public static void writeResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public static Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        return result;
    }

}





