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

import static java.lang.Integer.parseInt;

/**
 * Created by krist on 17-10-2016.
 */
public class UserEndpoint {

    static EndpointController endpointController = new EndpointController();
    static UserController userController = new UserController();

    public static class GetUsersHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            ArrayList<User> users = userController.getUsers();

            Gson gson = new Gson();

            if(users.isEmpty()) {
                response.append("No users found!");
            } else {
                response.append(gson.toJson(users));
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class CreateUserHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();
            Map<String, String> parms = endpointController.queryToMap(httpExchange.getRequestURI().getQuery());

            User user = new User();
            user.setUsername(parms.get("username"));
            user.setPassword(parms.get("password"));
          //  user.setPhonenumber(parseInt(parms.get("phonenumber")));
            user.setPhonenumber(0);
            user.setAddress(parms.get("address"));
            user.setEmail(parms.get("username"));
          //  user.setMobilepay(parseInt("mobilepay"));
          //  user.setCash(parseInt("cash"));
          //  user.setTransfer(parseInt("transfer"));
            user.setMobilepay(0);
            user.setCash(0);
            user.setTransfer(0);
            user.setType(0);

            userController.createUser(user);

            Gson gson = new Gson();

            if (user == null) {
                response.append("Cannot create user");
            } else {
                response.append(gson.toJson(user));
            }

            endpointController.writeResponse(httpExchange, response.toString());

        }

    }
}









