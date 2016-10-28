package Controller;

import DTOobjects.Session;
import DTOobjects.User;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpCookie;
import java.util.*;

/**
 * Created by alanziberi on 17/10/2016.
 */
public class EndpointController {

    SessionController sessionController = new SessionController();
    ConfigController configController = new ConfigController();

    public void writeResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.getResponseHeaders().set("Access-Control-Allow-Credentials", "true");
        httpExchange.getResponseHeaders().set("Access-Control-Allow-Origin", configController.getConfig().getAllowOrigin());
        httpExchange.getResponseHeaders().set("Content-Type", "application/json");

        httpExchange.sendResponseHeaders(200, response.length());

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes("latin1"));
        os.close();
    }

    public Session checkSession(HttpExchange httpExchange) throws IOException {

        SessionController sessionController = new SessionController();

        String cookieHeader = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieHeader != null) {
            List<HttpCookie> cookiesHeader = HttpCookie.parse(cookieHeader);
            String sessionId = cookiesHeader.get(0).getValue();

            if (sessionId != null && !sessionId.isEmpty()) {
                Session session = sessionController.getSession(sessionId);

                if (session != null) {
                    return session;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean createSession(HttpExchange httpExchange, User user) throws IOException {

        Session session = new Session();
        session.setSessionId(sessionController.generateSessionId());
        session.setUserId(user.getId());
        session.setUserType(user.getType());

        boolean verifySession = sessionController.createSession(session);

        if (verifySession) {
            httpExchange.getResponseHeaders().set("Set-Cookie", "sessionid=" + session.getSessionId() + "; Secure; HttpOnly");

            return true;
        } else {
            return false;
        }
    }

    public JSONObject parsePostRequest(HttpExchange httpExchange)  {
        JSONObject jsonObject = new JSONObject();
        JSONParser jsonParser = new JSONParser();

        if (httpExchange.getRequestMethod().equalsIgnoreCase("POST")) {

            try {
                InputStreamReader inputRequestBody = new InputStreamReader(httpExchange.getRequestBody(), "latin1");

                Object requestObject = jsonParser.parse(inputRequestBody);

                jsonObject = (JSONObject) requestObject;

                return jsonObject;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return jsonObject;
    }
}
