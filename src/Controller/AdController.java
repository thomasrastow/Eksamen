package Controller;
import DTOobjects.Ad;

import ServiceImplementation.ServiceImplementation;

import java.util.ArrayList;



public class AdController {

    ServiceImplementation serviceImpl = new ServiceImplementation();

    public ArrayList<Ad> getAds() {

        ArrayList<Ad> ads = serviceImpl.getAds();

        return ads;
    }


    public boolean createAd(Ad ad) {

        boolean verifyRequest;

        verifyRequest = serviceImpl.createAd(ad);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteAd(int id) {

        boolean verifyRequest;

        verifyRequest = serviceImpl.deleteAd(id);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }

    }

    public boolean lockAd(int id) {

        boolean verifyRequest;

        verifyRequest = serviceImpl.lockAd(id);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }

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

        boolean verifyRequest;

        verifyRequest = serviceImpl.updateAd(ad);

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

    public ArrayList<Ad> getAdsUser(int userId) {

        ArrayList<Ad> ads = serviceImpl.getAdsUser(userId);

        return ads;
    }

    public ArrayList<Ad> getAdsBook(long isbn) {

        ArrayList<Ad> ads = serviceImpl.getAdsBook(isbn);

        return ads;
    }

}
