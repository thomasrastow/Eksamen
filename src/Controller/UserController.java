package Controller;

import DTOobjects.User;

/**
 * Created by krist on 17-10-2016.
 */
public class UserController {

    public String createUser(String username, String password, int phonenumber, String address, String email,
                                 int mobilePay, int cash, int transfer, int type) {

            // Opretter validering

            if (username.contains("")) {
                return "Username cannot be blank.";
            }

            if (password.contains("")) {
                return "Password cannot be blank.";
            }

            if (phonenumber < 0) {
                return "Phonenumber must consist of 8 digits";
            }

            if (address.contains("")) {
                return "Address cannot be blank";
            }

            if (email.contains("")) {
                return "Email cannot be blank";
            }

            // new User();

            // TODO: save in the database.

            return "OK";
        }

    }







