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

    private User user;

    //overvej om der skal valideres ud fra, hvilket brugerid, der logget ind, så man ikke skla logge ind igen for at slette sin bruger.
    //i så fald skal der laves en seesion i log in, der gør at man kan tjekke, hvem der er logget idn
    public void deleteUser() {

        for (User user : users) {
            if (user.getType() == 1) {

                String username1 = //input fra klient

                        String password1 = //input fra klient

                for (int i = 1; i < getUsers().size(); i++) {
                    if (getUsers().get(i).getUsername().equals(username1) && getUsers().get(i).getPassword().equals(password1)) {
                        getUsers().remove(i);
                        return;
                    }
                }


                for (int i = 1; i < getUsers().size(); i++) {
                    if (!getUsers().get(i).getUsername().equals(username1) || !getUsers().get(i).getPassword().equals(password1)) {
                        return;
                    }
                }

            }
            if (user.getType() == 0) {

                String username2 = //input fra klient
                int id = //input fra klient


                for (int i = 0; i < getUsers.size(); i++) {
                    if (getUsers().get(i).getUsername().equals(username2) && getUsers().get(i).getID.equals(id)) {
                        getUsers().remove(i);
                        return;
                    }
                }
                for (int i = 1; i < getUsers().size(); i++) {
                    if (!getUsers().get(i).getUsername().equals(username2) || !getUsers().get(i).getId().equals(id)) {
                        return;
                    }
                }


            }


        }
    }
}
