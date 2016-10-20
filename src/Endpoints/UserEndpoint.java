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
            user.setPhonenumber(Integer.parseInt(parms.get("phonenumber")));
            user.setAddress(parms.get("address"));
            user.setEmail(parms.get("username"));
            user.setMobilepay(Integer.parseInt(parms.get("mobilepay")));
            user.setCash(Integer.parseInt(parms.get("cash")));
            user.setTransfer(Integer.parseInt(parms.get("transfer")));
            user.setType(0);

            Gson gson = new Gson();

            if(user != null && userController.createUser(user)) {
                response.append(gson.toJson(user));
            } else {
                response.append("Cannot create user!");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class DeleteUserHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();
            Map<String, String> parms = endpointController.queryToMap(httpExchange.getRequestURI().getQuery());

            int id = Integer.parseInt(parms.get("id"));

            Gson gson = new Gson();

            if (userController.deleteUser(id)) {
                response.append(gson.toJson("The user with id: "+id+" is now deleted"));
            } else {
                response.append("Cannot delete user!");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

   public static class UpdateUserHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException{
           StringBuilder response = new StringBuilder();

            Map<String, String> parms = endpointController.queryToMap(httpExchange.getRequestURI().getQuery());

            int userID = Integer.parseInt(parms.get("id"));

            User user = userController.getUser(userID);

            /*
                Vi skal ikke ændre brugernavn og password endnu, da vi derved skal have tjekket
                om brugernavn er unikt, samt passworden skal hashes.

                http://localhost:8000/updateuser?userID=32&phonenumber=12345678&address=CBS&email=hej@farvel.dk&mobilepay=1&cash=1&transfer=1
            */

            if (parms.get("username") != null) {
                user.setUsername(parms.get("username"));
            }

            if (parms.get("password") != null) {
                user.setPassword(parms.get("password"));
            }

            if (parms.get("phonenumber") != null) {
                user.setPhonenumber(Integer.parseInt(parms.get("phonenumber")));
            }

            if (parms.get("address") != null) {
                user.setAddress(parms.get("address"));
            }

            if (parms.get("email") != null) {
                user.setEmail(parms.get("email"));
            }

            if (parms.get("mobilepay") != null) {
                user.setMobilepay(Integer.parseInt(parms.get("mobilepay")));
            }

            if (parms.get("cash") != null) {
                user.setCash(Integer.parseInt(parms.get("cash")));
            }

            if (parms.get("transfer") != null) {
                user.setTransfer(Integer.parseInt(parms.get("transfer")));
            }

            Gson gson = new Gson();

            if (user != null && userController.updateUser(user)) {
                response.append(gson.toJson(user));
            } else {
                response.append("Cannot update user!");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }
}
