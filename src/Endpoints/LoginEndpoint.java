package Endpoints;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Map;

import Controller.EndpointController;
import Controller.LoginController;

import DTOobjects.User;

/**
 * Created by krist on 17-10-2016.
 */
public class LoginEndpoint {

    static EndpointController endpointController = new EndpointController();
    static LoginController loginController = new LoginController();

    public static class LoginHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();
            Map<String, String> parms = endpointController.queryToMap(httpExchange.getRequestURI().getQuery());

            String username = parms.get("username");
            String password = parms.get("password");

            User user = new User();
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
}
