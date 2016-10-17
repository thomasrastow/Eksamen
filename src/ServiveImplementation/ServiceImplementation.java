package ServiveImplementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServiceImplementation {


    public ServiceImplementation() {
        String url = "http://shop.c50bqctooery.us-east-1.rds.amazonaws.com/";
        String username = "brugtbog";
        String password = "brugtpass";
        String database = "shop";

        private Connection connection = null;

        private PreparedStatement authorizeUserSQL = null;
        private PreparedStatement createUserSQL = null;
        private PreparedStatement updateUserSQL = null;
        private PreparedStatement getUsersSQL = null;
        private PreparedStatement deleteUserSQL = null;

        private PreparedStatement createBookSQL = null;
        private PreparedStatement getBooksSQL = null;
        private PreparedStatement deleteBookSQL = null;

        private PreparedStatement createAdSQL = null;
        private PreparedStatement getAdsSQL = null;
        private PreparedStatement updateAdSQL = null;
        private PreparedStatement deleteAdSQL = null;

    	public ServiceImpl() throws Exception {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                authorizeUserSQL = connection.prepareStatement("SELECT * FROM user where username = ? AND password = ?");
//USERS
                createUserSQL = connection.prepareStatement(
                        "INSERT INTO user" + " (type, username, password, phonenumber, address, email, mobilepay, cash, transfer)"
                                + " VALUES (1, ?, ?, ?, ?, ?, ?, ?, ?)");

                getUsersSQL = connection.prepareStatement("SELECT * FROM user");

                updateUserSQL = connection.prepareStatement("UPDATE user SET username = ?, password = ?, phonenumber = ?, address = ?, email = ?, mobilepay = ?, cash = ?, transfer = ? WHERE id = ?");

                deleteUserSQL = connection.prepareStatement("DELETE * FROM user WHERE id = ?");
//BOOKS
                createBookSQL = connection.prepareStatement(
                        "INSERT INTO book" + " (ISBN, title, edition, author)"
                                + " VALUES (?, ?, ?, ?)");

                getBooksSQL = connection.prepareStatement("SELECT * FROM book");


                deleteBookSQL = connection.prepareStatement("DELETE * FROM book WHERE id = ?");
//ADS
                createAdSQL = connection.prepareStatement(
                        "INSERT INTO ad" + " (price, rating, userID, bookID, comment, locked)"
                                + " VALUES (?, ?, ?, ?, ?, 0)");

                getAdsSQL = connection.prepareStatement("SELECT * FROM ad");

                updateAdSQL = connection.prepareStatement("UPDATE ad SET price = ?, rating = ?, userID = ?, bookID = ?, comment = ?, locked = ? WHERE id = ?");


                deleteAdSQL = connection.prepareStatement("UPDATE ad SET deleted = 1 WHERE id = ?");

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DALException("Database not connected. Pleasy try again.");
            }
        }


        @Override
        public User authorizeUser (String username, String password) throws Exception {
            ResultSet resultSet = null;
            User user = null;

            try {
                authorizeUserSQL.setString(1, username);
                authorizeUserSQL.setString(2, password);

                resultSet = authorizeUserSQL.executeQuery();

                while (resultSet.next()) {
                    user = new User(resultSet.getInt("id"), resultSet.getInt("type"), resultSet.getString("username"),
                            resultSet.getString("address"), resultSet.getString("email"),
                            resultSet.getInt("mobilepay"), resultSet.getInt("cash"), resultSet.getInt("transfer"),
                            resultSet.getInt("phonenumber"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DALException("Not able to login. Please try again.");

            } finally {
                try {
                    resultSet.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                    close();
                }
            }
            return user;
        }

    }


    @Override
    public int createUser(User user) throws Exception {
            try {
                createUserSQL.setInt(1, user.getType());
                createUserSQL.setString(2, user.getUsername());
                createUserSQL.setString(3, user.getPassword());
                createUserSQL.setInt(4, user.getPhonenumber());
                createUserSQL.setString(5, user.getAddress());
                createUserSQL.setString(6, user.getEmail());
                createUserSQL.setInt(7, user.getMobilepay());
                createUserSQL.setInt(8, user.getCash());
                createUserSQL.setInt(9, user.getTransfer());


                createUserSQL.executeUpdate();
            } catch (SQLException e) {
            }

        }
    }

