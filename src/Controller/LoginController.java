package Controller;

import DTOobjects.User;
import ServiveImplementation.ServiceImplementation;

/**
 * Created by krist on 17-10-2016.
 */
public class LoginController {

    public User login(String username, String password) {

        ServiceImplementation db = new ServiceImplementation();

        User user = new User();
        user = db.dbLogin(username,password);

        return user;
    }
}
