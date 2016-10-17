package Controller;

public class AdController {

        public String createAd(int price, int rating, int bookID, String commment)

        {
            if (price == "") {
                return "price cannot be blank.";
            }

            if (rating == "") {
                return "rating cannot be blank.";
            }

            if (bookID == "") {
                return "Phonenumber cannot be blank";
            }

             //new ad();

            // TODO: save in the database.

            return "OK";
        }

    }
    public void editAd() {

        NewAd = new ad(id, price, ratin, userID, bookID, deleted, comment,
                locked, time);



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
