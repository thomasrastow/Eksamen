package Controller;

import DTOobjects.User;
import ServiceImplementation.ServiceImplementation;

/**
 * Created by krist on 17-10-2016.
 */
public class LoginController {

    public User login(String username, String password)  {

        ServiceImplementation serviceImpl = new ServiceImplementation();

        User user = new User();
        user = serviceImpl.authorizeUser(username, password);

        return user;
    }
}
