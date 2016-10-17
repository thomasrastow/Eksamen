package Controller;

import DTOobjects.User;

/**
 * Created by krist on 17-10-2016.
 */
public class UserController {

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








