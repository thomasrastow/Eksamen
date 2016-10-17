package Endpoints;

import Main.Run;

import DTOobjects.User;
import ServiceImplementation.ServiceImplementation;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Controller.EndpointController;
import Controller.UserController;

/**
 * Created by krist on 17-10-2016.
 */
public class UserEndpoint {

    static EndpointController endpointController = new EndpointController();
    static UserController userController = new UserController();

    public static class GetUsersHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            Gson gson = new Gson();

            ArrayList<User> users = new ArrayList<>();

            users = userController.getUsers();

            if(users.isEmpty()) {
                response.append("No users found!");
            } else {
                response.append(gson.toJson(users));
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }
}









