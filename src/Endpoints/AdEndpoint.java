/*  package Endpoints;

import Controller.EndpointController;
import Controller.AdController;
import DTOobjects.Ad;
import Main.Run;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Map;

/**
 * Created by krist on 17-10-2016.
 */
/*public class AdEndpoint {


    Run run = new Run();

    static AdController adController = new AdController();

    static Ad ad = new Ad();

    static EndpointController endpointController = new EndpointController();

    public static class GetAdHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {

            if (httpExchange.getRequestMethod().equals("GET")) {
                // HENT ADS
                StringBuilder response = new StringBuilder();

                Gson gson = new Gson();

                try {
                    response.append(gson.toJson(adController.getAds()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                endpointController.writeResponse(httpExchange, response.toString());
            } else if (httpExchange.getRequestMethod().equals("POST")) {
                // LAV EN NY AD
                httpExchange.getRequestBody()
            } else if (httpExchange.getRequestMethod().equals("PUT")) {
                // ÆNDRE AD
                httpExchange.getRequestBody()
            } else if (httpExchange.getRequestMethod().equals("DELETE")) {
                // DELETE AD

            StringBuilder response = new StringBuilder();

            Gson gson = new Gson();

            response.append(gson.toJson(adController.getAds()));

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

}
}
*/

