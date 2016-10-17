package Controller;

<<<<<<< HEAD
=======
import DTOobjects.User;
import ServiveImplementation.ServiceImplementation;

>>>>>>> UserBranch
/**
 * Created by Emma og Thomas on 17-10-2016.
 */
public class UserController {

    public String createUser(String username, String password, int phonenumber, String address, String email,
<<<<<<< HEAD
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
=======
                             int mobilepay, int cash, int transfer, int type) {

        // Opretter validering

        if (username.contains("")) {
            return "Username cannot be blank.";
        }

        if (password.contains("")) {
            return "Password cannot be blank.";
        }

        for (int i = 0; i < String.valueOf(phonenumber).length(); i++) {
            if (!(Character.isDigit(String.valueOf(phonenumber).charAt(i)))) {
                return "Phone number can only contain digits";
            }
        }

        if (phonenumber < 0) {
            return "Phone number must consist of 8 digits";
        }

        if (address.contains("")) {
            return "Address cannot be blank";
        }

        if (email.contains("")) {
            return "Email cannot be blank";
        }

        User user = new User(0, username, password, phonenumber, address, email, mobilepay, cash, transfer, 0);

        ServiceImplementation serviceImpl = new ServiceImplementation();

        try {
            serviceImpl.createUser(user);
        } catch (Exception e) {
            e.printStackTrace();

            return "Error";
        }

        // TODO: save in the database.

        // response String = DatabaseController.saveUser(user);

        // user.save();

        // return response;
>>>>>>> UserBranch

        return "OK";
    }

    public void deleteUser(int id) {
        ServiceImplementation serviceImpl = new ServiceImplementation();
        serviceImpl.getUser(id).remove();
    }

    //public String deleteUser(int id) {
        //User user = deleteUser(id);

      //  ServiceImplementation serviceImpl = new ServiceImplementation();

        //try {
          //  serviceImpl.deleteUser(id);
        //} catch (Exception e) {
          //  e.printStackTrace();

            //return "error";
    }

