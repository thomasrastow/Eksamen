package Controller;
import DTOobjects.Ad;
public class AdController {
    public Ad adDTO;

        public String createAd(int price, int rating, int bookID, String commment)

        {
            int delete = 0;
            int locked = 0;
            int userID = 10; //Current user metode
            int id = 0; //Autoincrement?

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
            adDTO = new Ad(price, rating, bookID, comment, delete, locked, id);


            // TODO: save in the database.

            return "OK";
        }

<<<<<<< Updated upstream
    }
    public void editAd() {

        NewAd = new ad(id, price, ratin, userID, bookID, deleted, comment,
                locked, time);
=======
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
>>>>>>> Stashed changes



    }

    public void reserveAd() {

        NewAd = new ad(id, price, ratin, userID, bookID, deleted, comment,
                locked, time);

    }

    public void deleteAd() {

        NewAd = new ad(id, price, ratin, userID, bookID, deleted, comment,
                locked, time);


    }
}
