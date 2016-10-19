  package Endpoints;

import Controller.EndpointController;
import Controller.AdController;

import DTOobjects.Ad;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import static Endpoints.UserEndpoint.userController;


  /**
 * Created by krist on 17-10-2016.
 */
public class AdEndpoint {

    static EndpointController endpointController = new EndpointController();
    static AdController adController = new AdController();

    public static class GetAdHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            ArrayList<Ad> ads = adController.getAds();

            Gson gson = new Gson();

            if (ads.isEmpty()) {
                response.append("No ads found!");
            } else {
                response.append(gson.toJson(ads));
            }

            endpointController.writeResponse(httpExchange, response.toString());

        }
    }

        public static class CreateAdHandler implements HttpHandler {
            public void handle(HttpExchange httpExchange) throws IOException {
                StringBuilder response = new StringBuilder();
                Map<String, String> parms = endpointController.queryToMap(httpExchange.getRequestURI().getQuery());

                Ad ad = new Ad();

                // http://localhost:8000/createad?price=199&rating=2&userID=3&bookID=7&deleted=0&comment=123&locked=0&time=2016-10-17%2016:00:00

                ad.setPrice(Integer.parseInt(parms.get("price")));
                ad.setRating(Integer.parseInt(parms.get("rating")));
                ad.setUserID(Integer.parseInt(parms.get("userID")));
                ad.setBookID(Integer.parseInt(parms.get("bookID")));
                ad.setDeleted(Integer.parseInt(parms.get("deleted")));
                ad.setComment(parms.get("comment"));
                ad.setLocked(Integer.parseInt(parms.get("locked")));
                ad.setTime(Timestamp.valueOf(parms.get("time")));

                Gson gson = new Gson();

                if(adController.createAd(ad)) {
                    response.append(gson.toJson(ad));
                } else {
                    response.append("Cannot create ad!");
                }

                endpointController.writeResponse(httpExchange, response.toString());

                // localhost:8000/createad?id=1&price=100&rating=2&userID=2&bookID=6&deleted=0&comment=123&locked=0&time=null

            }
        }
    }






    /*
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

