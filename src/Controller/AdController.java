package Controller;
import DTOobjects.Ad;

import ServiceImplementation.ServiceImplementation;

import java.util.ArrayList;



public class AdController {

    ServiceImplementation serviceImpl = new ServiceImplementation();

    public ArrayList<Ad> getAds() {

        ServiceImplementation db = new ServiceImplementation();

        ArrayList<Ad> ads = db.getAds();

        return ads;
    }


    public boolean createAd(Ad ad) {

        ServiceImplementation db = new ServiceImplementation();

        boolean verifyRequest;

        verifyRequest = db.createAd(ad);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteAd(int id) {

        ServiceImplementation db = new ServiceImplementation();

        boolean verifyRequest;

        verifyRequest = db.deleteAd(id);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<Ad> getMyAds(int userID) {

        ServiceImplementation db = new ServiceImplementation();

        ArrayList<Ad> myAds = db.getMyAds(userID);

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

        Ad ad = null;

        try{
            ad = serviceImpl.getAd(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ad;

    }

}
