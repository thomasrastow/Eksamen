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

import Controller.*;

/**
 * Created by krist on 17-10-2016.
 */
public class UserEndpoint {

    Run run = new Run();

    static LoginController loginController = new LoginController();

    static UserController userController = new UserController();

    static User user = new User();

    static EndpointController endpointController = new EndpointController();


    public static class LoginHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();
            Map<String, String> parms = endpointController.queryToMap(httpExchange.getRequestURI().getQuery());

            String username = parms.get("username");
            String password = parms.get("password");

            user = loginController.login(username, password);

            Gson gson = new Gson();

            if (user == null) {
                response.append("User not found!");
            } else {
                response.append(gson.toJson(user));
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class GetUsersHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            Gson gson = new Gson();

            response.append(gson.toJson(userController.getUsers()));

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }
}









