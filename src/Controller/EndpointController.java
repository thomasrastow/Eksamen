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
        JSONObject jsonObject = new JSONObject();

        SessionController sessionController = new SessionController();

        String hasCookie = httpExchange.getRequestHeaders().getFirst(("Cookie"));
        if(hasCookie != null ) {
            jsonObject = gson.fromJson(hasCookie, JSONObject.class);
            Session session = new Session();
            session.setSessionToken((String) jsonObject.get("value"));

            boolean verifySession = sessionController.getSession(session.getSessionToken(), userId);

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
        JSONObject jsonObject = new JSONObject();

        SessionController sessionController = new SessionController();

        String hasCookie = httpExchange.getRequestHeaders().getFirst(("Cookie"));

        if(hasCookie != null ) {
            jsonObject = gson.fromJson(hasCookie, JSONObject.class);
            Session session = new Session();
            session.setSessionToken((String) jsonObject.get("value"));

            int verifySession = sessionController.getSessionUserId(session.getSessionToken());

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
        Gson gson = new Gson();

        JSONObject jsonObject = new JSONObject();
        Session session = new Session();
        session.setSessionToken(sessionController.generateToken());
        session.setUserId(user.getId());
        session.setUserType(user.getType());

        HttpCookie httpCookie = new HttpCookie("sessionId", session.getSessionToken());
        httpCookie.setSecure(true);
        httpCookie.setHttpOnly(true);

        boolean verifySession = sessionController.createSession(session);

        if (verifySession) {
            httpExchange.getResponseHeaders().set("Set-Cookie", gson.toJson(httpCookie));

            return true;
        } else {
            return false;
        }
    }

    public JSONObject parsePostRequest(HttpExchange httpExchange)  {
        JSONObject jsonObject = new JSONObject();
        JSONParser jsonParser = new JSONParser();

        try {
            InputStreamReader inputRequestBody = new InputStreamReader(httpExchange.getRequestBody(),"latin1");

            Object requestObject = jsonParser.parse(inputRequestBody);

            jsonObject = (JSONObject) requestObject;

            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
