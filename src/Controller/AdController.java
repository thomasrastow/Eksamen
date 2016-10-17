package Controller;

public class AdController {

        public String createAd(int price, int rating, int userID, int bookID, int deleted, string commment, boolean locked, timestamp time)

        {
            if (price == "") {
                return "Username cannot be blank.";
            }

            if (password == "") {
                return "Password cannot be blank.";
            }

            if (phonenumber == "") {
                return "Phonenumber cannot be blank";
            }

            if (address == "") {
                return "Address cannot be blank";
            }

            if (email == "") {
                return "Email cannot be blank";
            }

            if (mobilePay == "") {
                return "Address cannot be blank";
            }

            // new User();

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
