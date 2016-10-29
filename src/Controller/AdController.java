package Controller;
import DTOobjects.Ad;

import DTOobjects.Reservation;
import ServiceImplementation.ServiceImplementation;

import java.util.ArrayList;



public class AdController {

    ServiceImplementation serviceImpl = new ServiceImplementation();

    public ArrayList<Ad> getAds() {

        ArrayList<Ad> ads = serviceImpl.getAds();

        return ads;
    }


    public boolean createAd(Ad ad) {

        boolean verifyRequest = serviceImpl.createAd(ad);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteAd(int id) {

        boolean verifyRequest = serviceImpl.deleteAd(id);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }

    }

    public boolean lockAd(int id) {

        boolean verifyRequest = serviceImpl.lockAd(id);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }

    }

    public boolean reserveAd(Reservation reservation) {

        boolean verifyRequest = serviceImpl.createReservation(reservation);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteReservation(int adId) {

        boolean verifyRequest = serviceImpl.deleteReservation(adId);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<Reservation> getMyReservations(int userId) {

        ArrayList<Reservation> myAdReservations = serviceImpl.getMyReservations(userId);

        return myAdReservations;
    }

    public Reservation getReservation(int adId) {

        Reservation reservation = serviceImpl.getReservation(adId);

        return reservation;
    }

    public boolean unlockAd(int id) {

        boolean verifyRequest;

        verifyRequest = serviceImpl.unlockAd(id);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<Ad> getMyAds(int id) {

        ArrayList<Ad> myAds = serviceImpl.getMyAds(id);

        return myAds;
    }

    public boolean updateAd(Ad ad) {

        boolean verifyRequest = serviceImpl.updateAd(ad);

        if (verifyRequest){
            return true;
        } else {
            return false;
        }

    }

    public Ad getAd(int id) {

        Ad ad = serviceImpl.getAd(id);

        return ad;
    }


    public Ad getAdPublic(int id) {

        Ad ad = serviceImpl.getAdPublic(id);

        return ad;
    }

    public ArrayList<Ad> getAdsAll() {

        ArrayList<Ad> ads = serviceImpl.getAdsAll();

        return ads;
    }

    public ArrayList<Ad> getAdsUser(String username) {

        ArrayList<Ad> ads = serviceImpl.getAdsUser(username);

        return ads;
    }

    public ArrayList<Ad> getAdsBook(long isbn) {

        ArrayList<Ad> ads = serviceImpl.getAdsBook(isbn);

        return ads;
    }

}
