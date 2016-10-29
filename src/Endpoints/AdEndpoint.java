package Endpoints;

import Controller.EndpointController;
import Controller.AdController;

import Controller.SessionController;
import DTOobjects.Ad;

import DTOobjects.Reservation;
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

            Session session = endpointController.checkSession(httpExchange);

            if (session != null && session.getUserId() != 0) {

                JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

                if (jsonObject.containsKey("isbn") & jsonObject.containsKey("rating") &
                        jsonObject.containsKey("comment") & jsonObject.containsKey("price")) {

                    Ad ad = new Ad();
                    ad.setUserId(session.getUserId());
                    ad.setIsbn((Long) jsonObject.get("isbn"));
                    ad.setRating(((Long) jsonObject.get("rating")).intValue());
                    ad.setComment((String) jsonObject.get("comment"));
                    ad.setPrice(((Long) jsonObject.get("price")).intValue());

                    boolean verifyRequest = adController.createAd(ad);

                    if (verifyRequest) {
                        response.append(gson.toJson(ad));
                    } else {
                        response.append("Failure: Can not create ad");
                    }
                } else {
                    response.append("Failure: Incorrect parameters");
                }
            } else {
                response.append("Failure: Session not verified");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class UpdateAdHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            Session session = endpointController.checkSession(httpExchange);

            if (session != null && session.getUserId() != 0) {

                JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

                if (jsonObject.containsKey("id") & jsonObject.containsKey("comment") &
                        jsonObject.containsKey("price") & jsonObject.containsKey("rating")) {

                    int adId = (((Long) jsonObject.get("id")).intValue());

                    Ad ad = adController.getAd(adId);

                    if (ad != null && session.getUserId() == ad.getUserId() || ad != null && session.getUserType() == 1) {
                        if (!jsonObject.get("comment").equals("")) {
                            ad.setComment((String) jsonObject.get("comment"));
                        }

                        if (((Long) jsonObject.get("price")).intValue() != 0) {
                            ad.setPrice(((Long) jsonObject.get("price")).intValue());
                        }

                        if (((Long) jsonObject.get("rating")).intValue() != 0) {
                            ad.setRating(((Long) jsonObject.get("rating")).intValue());
                        }

                        boolean verifyRequest = adController.updateAd(ad);

                        if (verifyRequest) {
                            response.append(gson.toJson("Success: Ad has been updated"));
                        } else {
                            response.append("Failure: Can not update ad");
                        }
                    } else {
                        response.append("Failure: Can not update ad");
                    }
                } else {
                    response.append("Failure: Incorrect parameters");
                }
            } else {
                response.append("Failure: Session not verified");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class DeleteAdHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            Session session = endpointController.checkSession(httpExchange);

            if (session != null && session.getUserId() != 0) {

                JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

                if (jsonObject.containsKey("id")) {

                    int adId = (((Long) jsonObject.get("id")).intValue());

                    Ad ad = adController.getAd(adId);

                    if (ad != null && session.getUserId() == ad.getUserId() || ad != null && session.getUserType() == 1) {

                        boolean verifyRequest = adController.deleteAd(adId);

                        if(verifyRequest) {
                            response.append(gson.toJson("Success: Ad with ID: " + adId + " deleted"));
                        } else {
                            response.append("Failure: Can not delete ad");
                        }
                    } else {
                        response.append("Failure: Can not delete ad");
                    }
                } else {
                    response.append("Failure: Incorrect parameters");
                }
            }  else {
                response.append("Failure: Session not verified");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class UnlockAdHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            Session session = endpointController.checkSession(httpExchange);

            if (session != null && session.getUserId() != 0) {

                JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

                if (jsonObject.containsKey("id")) {

                    int adId = (((Long) jsonObject.get("id")).intValue());

                    Ad ad = adController.getAd(adId);

                    if (ad != null && session.getUserId() == ad.getUserId() || ad != null && session.getUserType() == 1) {

                        boolean verifyRequestDelete = adController.deleteReservation(adId);
                        boolean verifyRequestUnlock = adController.unlockAd(adId);

                        if (verifyRequestDelete & verifyRequestUnlock) {
                            response.append(gson.toJson("Success: Ad with ID: " + adId + " unlocked"));
                        } else {
                            response.append("Failure: Can not unlock ad");
                        }
                    } else {
                        response.append("Failure: Can not unlock ad");
                    }
                } else {
                    response.append("Failure: Incorrect parameters");
                }
            } else {
                response.append("Failure: Session not verified");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class ReserveAdHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            Session session = endpointController.checkSession(httpExchange);

            if (session != null && session.getUserId() != 0) {

                JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

                if (jsonObject.containsKey("id")) {

                    int adId = (((Long) jsonObject.get("id")).intValue());

                    Ad ad = adController.getAd(adId);

                    if (ad != null && ad.getDeleted() != 1 & ad.getDeleted() != 1) {

                        Reservation reservation = new Reservation();
                        reservation.setAdId(adId);
                        reservation.setUserId(session.getUserId());

                        boolean verifyRequestReserve = adController.reserveAd(reservation);
                        boolean verifyRequestLock = adController.lockAd(adId);

                        if (verifyRequestReserve & verifyRequestLock) {
                            response.append(gson.toJson("Success: Ad with ID: " + adId + " reserved"));
                        } else {
                            response.append("Failure: Can not reserve ad");
                        }
                    } else {
                        response.append("Failure: Can not reserve ad");
                    }
                } else {
                    response.append("Failure: Incorrect parameters");
                }
            } else {
                response.append("Failure: Session not verified");
            }


            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class GetMyReservationsHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            Session session = endpointController.checkSession(httpExchange);

            if (session != null && session.getUserId() != 0) {

                ArrayList<Reservation> reservations = adController.getMyReservations(session.getUserId());

                if (!reservations.isEmpty()) {
                    response.append(gson.toJson(reservations));
                } else {
                    response.append("Failure: Can not find reservations");
                }
            } else {
                response.append("Failure: Session not verified");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class DeleteReservationHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            Session session = endpointController.checkSession(httpExchange);

            if (session != null && session.getUserId() != 0) {

                JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

                if (jsonObject.containsKey("id")) {

                    int adId = (((Long) jsonObject.get("id")).intValue());

                    Reservation reservation = adController.getReservation(adId);

                    if (reservation != null && session.getUserId() == reservation.getUserId()) {

                        boolean verifyRequestDelete = adController.deleteReservation(adId);
                        boolean verifyRequestUnlock = adController.unlockAd(adId);

                        if (verifyRequestDelete & verifyRequestUnlock) {
                            response.append(gson.toJson("Success: Reservation with Ad ID: " + adId + " deleted"));
                        } else {
                            response.append("Failure: Can not delete reservation");
                        }
                    } else {
                        response.append("Failure: Can not delete reservation");
                    }
                } else {
                    response.append("Failure: Incorrect parameters");
                }
            } else {
                response.append("Failure: Session not verified");
            }

            endpointController.writeResponse(httpExchange, response.toString());
        }
    }

    public static class GetMyAdsHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();

            Session session = endpointController.checkSession(httpExchange);

            if (session != null && session.getUserId() != 0) {
                ArrayList<Ad> myAds = adController.getMyAds(session.getUserId());

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

            if (jsonObject.containsKey("username")) {

                ArrayList<Ad> ads = adController.getAdsUser((String) jsonObject.get("username"));

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

            Session session = endpointController.checkSession(httpExchange);

            if (session != null && session.getUserType() == 1) {
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

            Session session = endpointController.checkSession(httpExchange);

            if (session != null && session.getUserId() != 0) {

                JSONObject jsonObject = endpointController.parsePostRequest(httpExchange);

                if (jsonObject.containsKey("id")) {

                    int adId = (((Long) jsonObject.get("id")).intValue());

                    Ad ad = adController.getAd(adId);

                    if (ad != null && session.getUserId() == ad.getUserId() || ad != null && session.getUserType() == 1) {
                        response.append(gson.toJson(ad));
                    } else {
                        response.append("Failure: Can not find ad");
                    }
                } else {
                    response.append("Failure: Incorrect parameters");
                }
            } else {
                response.append("Failure: Session not verified");
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