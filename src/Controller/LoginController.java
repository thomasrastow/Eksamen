package Controller;

import DTOobjects.User;
import ServiveImplementation.ServiceImplementation;

public class LoginController {
    ServiceImplementation serviceImpl = new ServiceImplementation();


    private void login(String username, String password) {

        serviceImpl.authorize(username,password);
        
    }


}
