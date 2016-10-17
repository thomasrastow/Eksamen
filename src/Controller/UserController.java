package Controller;

import DTOobjects.User;
import ServiceImplementation.ServiceImplementation;

import java.util.ArrayList;

/**
 * Created by krist on 17-10-2016.
 */
public class UserController {

    public ArrayList<User> getUsers() {

        ServiceImplementation db = new ServiceImplementation();

        ArrayList<User> users = db.getUsers();

        return users;
    }
}

