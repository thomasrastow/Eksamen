package Controller;
import DTOobjects.Ad;
import java.sql.Timestamp;
import java.util.Date;

public class AdController {


    public Ad adDTO;

        public String createAd(int price, int rating, String comment)

        {
            int deleted = 0;
            int locked = 0;
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

        public String editAd (int price, int rating, String comment, int deleted, int locked)

        {
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

        public String reserveAd (int locked)

        {
            locked = 1;
            int userID = 10; //Current user metode
            int id = 0; //Autoincrement?
            java.util.Date date= new java.util.Date();
            java.sql.Timestamp time = new Timestamp(date.getTime()) ;
            int bookID = 0; //get bookid
            int price = 0; //
            int rating = 0;
            String comment = "test";
            int deleted = 0;

        }


}


  //public getAds();
   // get ads were bookID = ? and locked = 0, and deleted = 0

  // public getMyAds();

     //   get ads were userID = correntID, and deleted = 0


 }
}
