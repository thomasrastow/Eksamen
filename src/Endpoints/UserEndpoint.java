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
import org.json.simple.JSONObject;

import static java.lang.Integer.parseInt;

/**
 * Created by krist on 17-10-2016.
 */
public class UserEndpoint {

    static EndpointController endpointController = new EndpointController();
    static UserController userController = new UserController();

    static Gson gson = new Gson();

    public static class GetUsersHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            ArrayList<User> users = userController.getUsers();


            boolean verifySession = endpointController.checkSession(httpExchange, 0);

            if(verifySession) {
                if (users.isEmpty()) {
                    response.append("Failure: No users found");
                } else {
                    response.append(gson.toJson(users));
                }
            } else {
                response.append("Failure: Session not verified");
            }

            endpointController.writeResponse(httpExchange, response.toString());

        }
    }

    public static class CreateUserHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

            User user = new User();
            user.setUsername((String) jsonObject.get("username"));
            user.setPassword((String) jsonObject.get("password"));
            user.setPhonenumber(((Long) jsonObject.get("phonenumber")).intValue());
            user.setAddress((String) jsonObject.get("address"));
            user.setEmail((String) jsonObject.get("email"));
            user.setMobilepay(((Long) jsonObject.get("mobilepay")).intValue());
            user.setCash(((Long) jsonObject.get("cash")).intValue());
            user.setTransfer(((Long) jsonObject.get("transfer")).intValue());

            user.setType(0);

            if (user != null & userController.createUser(user)) {
                    response.append(gson.toJson(user));
            } else {
                    response.append("Failure: Can not create user");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class DeleteUserHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

            int userId = (((Long) jsonObject.get("id")).intValue());

            boolean verifySession = endpointController.checkSession(httpExchange, 0);

            if(verifySession) {
                if (userController.deleteUser(userId)) {
                    response.append(gson.toJson("Success: User with ID: " + userId + " deleted"));
                } else {
                    response.append("Failure: Can not delete user");
                }
            } else {
                response.append("Failure: Session not verified");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

   public static class UpdateUserAdminHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException{
           StringBuilder response = new StringBuilder();

            JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

            boolean verifySession = endpointController.checkSession(httpExchange, 0);

            if (verifySession) {

                int userId = (((Long) jsonObject.get("id")).intValue());

                User user = userController.getUser(userId);

                if (user != null) {
                    if (!jsonObject.get("username").equals("")) {
                        user.setUsername((String) jsonObject.get("username"));
                    }

                    user.setPassword((String) jsonObject.get("password"));

                    if (!jsonObject.get("phonenumber").equals("")) {
                        user.setPhonenumber(((Long) jsonObject.get("phonenumber")).intValue());
                    }

                    if (!jsonObject.get("address").equals("")) {
                        user.setAddress((String) jsonObject.get("address"));
                    }

                    if (!jsonObject.get("email").equals("")) {
                        user.setEmail((String) jsonObject.get("email"));
                    }

                    if (!jsonObject.get("mobilepay").equals("")) {
                        user.setMobilepay(((Long) jsonObject.get("mobilepay")).intValue());
                    }

                    if (!jsonObject.get("cash").equals("")) {
                        user.setCash(((Long) jsonObject.get("cash")).intValue());
                    }

                    if (!jsonObject.get("transfer").equals("")) {
                        user.setTransfer(((Long) jsonObject.get("transfer")).intValue());
                    }

                    if (user != null & userController.updateUser(user)) {
                        response.append(gson.toJson(user));
                    }
                } else {
                    response.append("Failure: Can not update user");
                }
            } else {
                response.append("Failure: Session not verified");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class UpdateUserHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException{
            StringBuilder response = new StringBuilder();

            JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

            int verifySession = endpointController.getSessionUserId(httpExchange);

            if (verifySession != 0) {

                int userId = (verifySession);

                User user = userController.getUser(userId);

                if (user != null) {
                    if (!jsonObject.get("username").equals("")) {
                        user.setUsername((String) jsonObject.get("username"));
                    }

                    user.setPassword((String) jsonObject.get("password"));

                    if (!jsonObject.get("phonenumber").equals("")) {
                        user.setPhonenumber(((Long) jsonObject.get("phonenumber")).intValue());
                    }

                    if (!jsonObject.get("address").equals("")) {
                        user.setAddress((String) jsonObject.get("address"));
                    }

                    if (!jsonObject.get("email").equals("")) {
                        user.setEmail((String) jsonObject.get("email"));
                    }

                    if (!jsonObject.get("mobilepay").equals("")) {
                        user.setMobilepay(((Long) jsonObject.get("mobilepay")).intValue());
                    }

                    if (!jsonObject.get("cash").equals("")) {
                        user.setCash(((Long) jsonObject.get("cash")).intValue());
                    }

                    if (!jsonObject.get("transfer").equals("")) {
                        user.setTransfer(((Long) jsonObject.get("transfer")).intValue());
                    }

                    if (user != null & userController.updateUser(user)) {
                        response.append(gson.toJson(user));
                    }
                } else {
                    response.append("Failure: Can not update user");
                }
            } else {
                response.append("Failure: Session not verified");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

}
