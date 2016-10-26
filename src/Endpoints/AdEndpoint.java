package Endpoints;

import Controller.EndpointController;
import Controller.AdController;

import Controller.SessionController;
import DTOobjects.Ad;

import DTOobjects.Session;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.simple.JSONObject;

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
    static SessionController sessionController = new SessionController();

    static Gson gson = new Gson();

    public static class GetAdsHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            ArrayList<Ad> ads = adController.getAds();

            if (!ads.isEmpty()) {
                response.append(gson.toJson(ads));
            } else {
                response.append("Failure: Can not find ads");
            }

            endpointController.writeResponse(httpExchange, response.toString());

        }
    }

    public static class CreateAdHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

            if (jsonObject.containsKey("isbn") & jsonObject.containsKey("rating") &
                    jsonObject.containsKey("comment") & jsonObject.containsKey("price")) {

                int verifySession = endpointController.getSessionUserId(httpExchange);

                if (verifySession != 0) {
                    Ad ad = new Ad();
                    ad.setUserId(verifySession);
                    ad.setIsbn((Long) jsonObject.get("isbn"));
                    ad.setRating(((Long) jsonObject.get("rating")).intValue());
                    ad.setComment((String) jsonObject.get("comment"));
                    ad.setPrice(((Long) jsonObject.get("price")).intValue());

                    if (ad != null & adController.createAd(ad)) {
                        response.append(gson.toJson(ad));
                    } else {
                        response.append("Failure: Can not create ad");
                    }
                } else {
                    response.append("Failure: Session not verified");
                }
            } else {
                response.append("Failure: Incorrect parameters");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class UpdateAdHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

            if (jsonObject.containsKey("id") & jsonObject.containsKey("comment") &
                    jsonObject.containsKey("price") & jsonObject.containsKey("rating")) {

                int adId = (((Long) jsonObject.get("id")).intValue());

                Ad ad = adController.getAd(adId);

                if (ad != null) {
                    if (!jsonObject.get("comment").equals("")) {
                        ad.setComment((String) jsonObject.get("comment"));
                    }

                    if (!jsonObject.get("price").equals("")) {
                        ad.setPrice(((Long) jsonObject.get("price")).intValue());
                    }

                    if (!jsonObject.get("rating").equals("")) {
                        ad.setRating(((Long) jsonObject.get("rating")).intValue());
                    }

                    boolean verifySession = endpointController.checkSession(httpExchange, ad.getUserId());

                    if (verifySession) {
                        if (adController.updateAd(ad)) {
                            response.append(gson.toJson(ad));
                        } else {
                            response.append("Failure: Can not update ad");
                        }
                    } else {
                        response.append("Failure: Session not verified");
                    }

                } else {
                    response.append("Failure: Can not update ad");
                }
            } else {
                response.append("Failure: Incorrect parameters");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class DeleteAdHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

            if (jsonObject.containsKey("id")) {

                int adId = (((Long) jsonObject.get("id")).intValue());

                Ad ad = adController.getAd(adId);

                if (ad != null) {
                    boolean verifySession = endpointController.checkSession(httpExchange, ad.getUserId());

                    if (verifySession) {
                        if (adController.deleteAd(adId)) {
                            response.append(gson.toJson("Success: Ad with ID: " + adId + " deleted"));
                        } else {
                            response.append("Failure: Can not delete ad");
                        }
                    } else {
                        response.append("Failure: Session not verified");
                    }
                } else {
                    response.append("Failure: Can not delete ad");
                }
            } else {
                response.append("Failure: Incorrect parameters");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class LockAdHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

            if (jsonObject.containsKey("id")) {

                int adId = (((Long) jsonObject.get("id")).intValue());

                Ad ad = adController.getAd(adId);

                if (ad != null) {
                    boolean verifySession = endpointController.checkSession(httpExchange, ad.getUserId());

                    if (verifySession) {
                        if (adController.lockAd(adId)) {
                            response.append(gson.toJson("Success: Ad with ID: " + adId + " locked"));
                        } else {
                            response.append("Failure: Can not lock ad");
                        }
                    } else {
                        response.append("Failure: Session not verified");
                    }
                } else {
                    response.append("Failure: Can not lock ad");
                }
            } else {
                response.append("Failure: Incorrect parameters");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class UnlockAdHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

            if (jsonObject.containsKey("id")) {

                int adId = (((Long) jsonObject.get("id")).intValue());

                Ad ad = adController.getAd(adId);

                if (ad != null) {
                    boolean verifySession = endpointController.checkSession(httpExchange, ad.getUserId());

                    if (verifySession) {
                        if (adController.unlockAd(adId)) {
                            response.append(gson.toJson("Success: Ad with ID: " + adId + " unlocked"));
                        } else {
                            response.append("Failure: Can not unlock ad");
                        }
                    } else {
                        response.append("Failure: Session not verified");
                    }
                } else {
                    response.append("Failure: Can not unlock ad");
                }
            } else {
                response.append("Failure: Incorrect parameters");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class GetMyAdsHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

            int verifySession = endpointController.getSessionUserId(httpExchange);

            if (verifySession != 0) {
                ArrayList<Ad> myAds = adController.getMyAds(verifySession);

                if (!myAds.isEmpty()) {
                    response.append(gson.toJson(myAds));
                } else {
                    response.append("Failure: Can not find ads");
                }
            } else {
                response.append("Failure: Session not verified");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class GetAdsUserHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

            if (jsonObject.containsKey("id")) {
                ArrayList<Ad> ads = adController.getAdsUser(((Long) jsonObject.get("id")).intValue());

                if (!ads.isEmpty()) {
                    response.append(gson.toJson(ads));
                } else {
                    response.append("Failure: Can not find ads");
                }
            } else {
                response.append("Failure: Incorrect parameters");
            }

            endpointController.writeResponse(httpExchange, response.toString());

        }
    }

    public static class GetAdsBookHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

            if (jsonObject.containsKey("isbn")) {

                ArrayList<Ad> ads = adController.getAdsBook(((Long) jsonObject.get("isbn")));

                if (!ads.isEmpty()) {
                    response.append(gson.toJson(ads));
                } else {
                    response.append("Failure: Can not find ads");
                }
            } else {
                response.append("Failure: Incorrect parameters");
            }

            endpointController.writeResponse(httpExchange, response.toString());

        }
    }

    public static class GetAdsAllHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            boolean verifySession = endpointController.checkSession(httpExchange, 0);

            if (verifySession) {
                ArrayList<Ad> ads = adController.getAdsAll();

                if (!ads.isEmpty()) {
                    response.append(gson.toJson(ads));
                } else {
                    response.append("Failure: Can not find ads");
                }
            } else {
                response.append("Failure: Session not verified");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class GetAdHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

            if (jsonObject.containsKey("id")) {

                int adId = (((Long) jsonObject.get("id")).intValue());

                Ad ad = adController.getAd(adId);

                if (ad != null) {
                    response.append(gson.toJson(ad));
                } else {
                    response.append("Failure: Can not find ad");
                }
            } else {
                response.append("Failure: Incorrect parameters");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class GetAdPublicHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

            if (jsonObject.containsKey("id")) {

                int adId = (((Long) jsonObject.get("id")).intValue());

                Ad ad = adController.getAdPublic(adId);

                if (ad != null) {
                    response.append(gson.toJson(ad));
                } else {
                    response.append("Failure: Can not find ad");
                }

                endpointController.writeResponse(httpExchange, response.toString());
            } else {
                response.append("Failure: Incorrect parameters");
            }
        }
    }
}