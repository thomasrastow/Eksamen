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

    public boolean checkSession(HttpExchange httpExchange, int userId) throws IOException {
        Gson gson = new Gson();
        JSONObject jsonObject;

        SessionController sessionController = new SessionController();

        List<HttpCookie> cookiesHeader = HttpCookie.parse(httpExchange.getRequestHeaders().getFirst("Cookie"));
        String sessionId = cookiesHeader.get(0).getValue();

        if(sessionId != null) {
            boolean verifySession = sessionController.getSession(sessionId, userId);

            if(verifySession) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public int getSessionUserId(HttpExchange httpExchange) throws IOException {
        Gson gson = new Gson();
        JSONObject jsonObject;

        SessionController sessionController = new SessionController();

        List<HttpCookie> cookiesHeader = HttpCookie.parse(httpExchange.getRequestHeaders().getFirst("Cookie"));
        String sessionId = cookiesHeader.get(0).getValue();

        if(sessionId != null) {
            int verifySession = sessionController.getSessionUserId(sessionId);

            if(verifySession != 0) {
                return verifySession;
            } else {
                return 0;
            }
        } else {
            return 0;
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
