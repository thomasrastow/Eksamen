package Controller;

import DTOobjects.User;
import ServiceImplementation.ServiceImplementation;

/**
 * Created by krist on 17-10-2016.
 */
public class LoginController {

    ServiceImplementation serviceImpl = new ServiceImplementation();

    public User login(String username, String password)  {

        User user = serviceImpl.authorizeUser(username, password);

        return user;
    }

}
