package Controller;

import DTOobjects.User;

/**
 * Created by Emma og Thomas on 17-10-2016.
 */
public class UserController {

    public String createUser(String username, String password, int phoneNumber, String address, String email,
                             int mobilePay, int cash, int transfer, int type) {

        // Opretter validering

      if (username.contains("")) {
          return "Username cannot be blank.";
      }

      if (password.contains("")) {
          return "Password cannot be blank.";
      }

      for (int i = 0; i < String.valueOf(phoneNumber).length(); i++){
        if (!(Character.isDigit(String.valueOf(phoneNumber).charAt(i)))) {
            return "Phone number can only contain digits";
        }
      }

      if (phoneNumber < 0) {
          return "Phone number must consist of 8 digits";
      }

      if (address.contains("")) {
            return "Address cannot be blank";
      }

      if (email.contains("")) {
            return "Email cannot be blank";
      }

      User user = new User(0, username, password, phoneNumber, address, email, mobilePay, cash, transfer, 0);

      // TODO: save in the database.

        // response String = DatabaseController.saveUser(user);

        // user.save();

        // return response;

      return "OK";
    }

}
