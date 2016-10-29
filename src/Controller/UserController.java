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

        return users;
    }

    public boolean createUser(User user) {

        boolean verifyRequest = serviceImpl.createUser(user);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteUser(int id) {

        boolean verifyRequest = serviceImpl.deleteUser(id);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }

    }

    public boolean updateUser(User user){

        boolean verifyRequest = serviceImpl.updateUser(user);

        if (verifyRequest) {
            return true;
        }else {
            return false;

        }
    }


    public User getUser(int id) {

        User user = serviceImpl.getUser(id);

        return user;
    }


    public User getUserPublic(String username) {

        User user = serviceImpl.getUserPublic(username);

        return user;
    }

}

