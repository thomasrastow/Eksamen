package Endpoints;

import Controller.SessionController;
import DTOobjects.Session;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import Controller.EndpointController;
import Controller.LoginController;

import DTOobjects.User;
import org.json.simple.JSONObject;

/**
 * Created by krist on 17-10-2016.
 */
public class LoginEndpoint {

    static EndpointController endpointController = new EndpointController();
    static LoginController loginController = new LoginController();
    static SessionController sessionController = new SessionController();

    static Gson gson = new Gson();

    public static class LoginHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();
            JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

            if (jsonObject.containsKey("username") & jsonObject.containsKey("username") &
                    !jsonObject.containsValue("")) {

                String username = (String) jsonObject.get("username");
                String password = (String) jsonObject.get("password");

                User user = new User();
                user = loginController.login(username, password);

                if (user != null) {
                    boolean verifySession = endpointController.createSession(httpExchange, user);
                    if (verifySession) {
                        response.append(gson.toJson(user));
                    } else {
                        response.append("Failure: Can not create session");
                    }
                } else {
                    response.append("Failure: Wrong username or password");
                }

            } else {
                response.append("Failure: Incorrect parameters");
            }
            endpointController.writeResponse(httpExchange, response.toString());

        }
    }

    public static class LogoutHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            Session session = endpointController.checkSession(httpExchange);

            if (session != null && session.getUserId() != 0) {

                boolean verifyLogout = sessionController.clearSessions(session.getUserId());

                if (verifyLogout) {
                    response.append(gson.toJson("Success: User with ID: " + session.getUserId() + " has been logged out"));
                } else {
                    response.append("Failure: Can not log user out");
                }
            } else {
                response.append("Failure: Session not verified");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }
}
