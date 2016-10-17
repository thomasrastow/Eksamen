package Controller;
import DTOobjects.Ad;
import java.sql.Timestamp;
import java.util.Date;

public class AdController {


    public Ad adDTO;

        public String createAd(int price, int rating, String comment)

        {
            byte deleted = 0;
            boolean locked = false;
            int userID = 10; //Current user metode
            int id = 0; //Autoincrement?
            java.util.Date date= new java.util.Date();
            java.sql.Timestamp time = new Timestamp(date.getTime()) ;
            int bookID = 0;
            if (price == "") {
                return "price cannot be blank.";
            }

            if (rating == "") {
                return "rating cannot be blank.";
            }

            if (bookID == "") {
                return "bookID cannot be blank";
            }

             //new ad();
            adDTO = new Ad(id,price, rating,userID, bookID, deleted, comment, locked, time);


            // TODO: save in the database.

            return "OK";
        }

        public String editAd (int price, int rating, String comment, byte deleted)

        {
            boolean locked = false;
            int userID = 10; //Current user metode
            int id = 0; //Autoincrement?
            java.util.Date date= new java.util.Date();
            java.sql.Timestamp time = new Timestamp(date.getTime()) ;
            int bookID = 0;

            if (price == "") {
                return "price cannot be blank.";
            }

            if (rating == "") {
                return "rating cannot be blank.";
            }
        }

        public String reserveAd (boolean locked)

        {
            locked = true;
            int userID = 10; //Current user metode
            int id = 0; //Autoincrement?
            java.util.Date date= new java.util.Date();
            java.sql.Timestamp time = new Timestamp(date.getTime()) ;
            int bookID = 0;
            int price = 0;
            int rating = 0;
            String comment = "test";
            byte deleted = 0;




        }






    }

    public void deleteAd() {

        NewAd = new ad(id, price, ratin, userID, bookID, deleted, comment,
                locked, time);


    }


    public getAds();

    //were bookid =,




 }
}
