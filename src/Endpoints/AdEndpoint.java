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

              if (adController.createAd(ad)) {
                  response.append(gson.toJson(ad));
              } else {
                  response.append("Cannot create ad!");
              }

              endpointController.writeResponse(httpExchange, response.toString());

          }
      }

      public static class UpdateAdHandler implements HttpHandler {
          public void handle(HttpExchange httpExchange) throws IOException {
              StringBuilder response = new StringBuilder();
              Map<String, String> parms = endpointController.queryToMap(httpExchange.getRequestURI().getQuery());

              // http://localhost:8000/updatead?id=1&price=2&rating=10&userID=2&bookID=2&comment=Dig%20med%20hej&time=2016-10-17%2016:00:00

              int id = Integer.parseInt(parms.get("id"));

              Ad ad = adController.getAd(id);

              if(parms.get("price") != null) {
                  ad.setPrice(Integer.parseInt(parms.get("price")));
              }

              if (parms.get("rating") != null){
                  ad.setRating(Integer.parseInt(parms.get("rating")));
              }
              if (parms.get("comment") != null){
                  ad.setComment(parms.get("comment"));
              }

              Gson gson = new Gson();

              if (adController.updateAd(ad)) {
                  response.append(gson.toJson(ad));
              } else {
                  response.append("Cannot update ad!");
              }

              endpointController.writeResponse(httpExchange, response.toString());
          }
      }

      public static class DeleteAdHandler implements HttpHandler {
          public void handle(HttpExchange httpExchange) throws IOException {
              StringBuilder response = new StringBuilder();
              Map<String, String> parms = endpointController.queryToMap(httpExchange.getRequestURI().getQuery());

              int id = Integer.parseInt(parms.get("id"));

              Gson gson = new Gson();

              if (adController.deleteAd(id)) {
                  response.append(gson.toJson(id));
              } else {
                  response.append("Cannot delete ad!");
              }

              endpointController.writeResponse(httpExchange, response.toString());

          }
      }



      public static class GetMyAdsHandler implements HttpHandler {
          public void handle(HttpExchange httpExchange) throws IOException {
              StringBuilder response = new StringBuilder();
              Map<String, String> parms = endpointController.queryToMap(httpExchange.getRequestURI().getQuery());

              ArrayList<Ad> myAds = adController.getMyAds(Integer.parseInt(parms.get("userID")));

              Gson gson = new Gson();

              if (myAds.isEmpty()) {
                  response.append("No ads found!");
              } else {
                  response.append(gson.toJson(myAds));
              }

              endpointController.writeResponse(httpExchange, response.toString());

          }
      }
  }