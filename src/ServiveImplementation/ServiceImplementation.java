package ServiveImplementation;

import DTOobjects.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by krist on 17-10-2016.
 */
public class ServiceImplementation {

    public ServiceImplementation() {
    String url = "http://shop.c50bqctooery.us-east-1.rds.amazonaws.com/";
    String username = "brugtbog";
    String password = "brugtpass";
    String database = "shop";

        private Connection connection = null;

        public User authorize(String username, String password) throws IllegalArgumentException {
            ResultSet resultSet = null;
            User userData = null;
            try {
                PreparedStatement getUser = connection
                        .prepareStatement("SELECT * FROM user where username = ? AND password = ?");
                getUser.setString(1, username);
                getUser.setString(2, password);
                resultSet = getUser.executeQuery();

                //Her tjekkes navn, kodeord og type
                while (resultSet.next()) {
                    userData = new User();
                    userData.setUsername(resultSet.getString("username"));
                    userData.setPassword(resultSet.getString("password"));
                    userData.setType(resultSet.getInt("type"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    close();
                }
            }
            return userLogin;
        }

        try (Connection connection = DriverManager.getConnection(url, username, password, database)) {
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            throw new IllegalStateException("Database not connected. Pleasy try again.", e);
        }

	public RpcServiceImpl() throws Exception {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);


            } catch (SQLException sqlException) {
                throw new Exception("Database not connected. Pleasy try again.");
            }
        }

    //hent brugere
    public ArrayList<User> getUsers(String username) throws IllegalArgumentException {
        ResultSet resultSet = null;
        ArrayList<User> users = new ArrayList<>();

        try {
            PreparedStatement getUsers = connection
                    .prepareStatement("SELECT * FROM users WHERE type = 1");
            getUsers.setString(1, username);
            resultSet = getUsers.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setType(resultSet.getInt("type"));

                users.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                close();
            }
        }
        return users;
    }

    //slet bruger
    public boolean deleteUser(User user) throws IllegalArgumentException {
        try {
            PreparedStatement deleteUser = connection
                    .prepareStatement("delete from users where username = ? and password = ?");

            deleteUser.setString(1, user.getUsername());
            deleteUser.setString(2, user.getPassword());

            int rowsAffected = deleteUser.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}



}

