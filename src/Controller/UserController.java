package Controller;

import DTOobjects.User;
import ServiceImplementation.ServiceImplementation;

import java.util.ArrayList;

/**
 * Created by krist on 17-10-2016.
 */
public class UserController {

    ServiceImplementation serviceImpl = new ServiceImplementation();

    public ArrayList<User> getUsers() {

        ArrayList<User> users = serviceImpl.getUsers();

        try {
            users =  serviceImpl.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public boolean createUser(User user) {

        boolean verifyRequest;

        verifyRequest = serviceImpl.createUser(user);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteUser(int id) {

        boolean verifyRequest;

        verifyRequest = serviceImpl.deleteUser(id);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }

    }
}

