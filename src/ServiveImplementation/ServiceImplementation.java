package ServiveImplementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DTOobjects.*;

import static sun.plugin.javascript.navig.JSType.URL;

public class ServiceImplementation {

        String url = "http://shop.c50bqctooery.us-east-1.rds.amazonaws.com/";
        String username = "brugtbog";
        String password = "brugtpass";
        String database = "shop";

        Connection connection = null;

        PreparedStatement authorizeUserSQL = null;
        PreparedStatement createUserSQL = null;
        PreparedStatement updateUserSQL = null;
        PreparedStatement getUsersSQL = null;
        PreparedStatement deleteUserSQL = null;

        PreparedStatement createBookSQL = null;
        PreparedStatement getBooksSQL = null;
        PreparedStatement deleteBookSQL = null;

        PreparedStatement createAdSQL = null;
        PreparedStatement getAdsSQL = null;
        PreparedStatement updateAdSQL = null;
        PreparedStatement deleteAdSQL = null;


    	public void ServiceImpl() throws Exception {
            try {
                connection = DriverManager.getConnection(url, username, password);

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
                        "INSERT INTO ad" + " (price, rating, userID, bookID, comment, locked, deleted)"
                                + " VALUES (?, ?, ?, ?, ?, 0, 0)");

                getAdsSQL = connection.prepareStatement("SELECT * FROM ad");

                updateAdSQL = connection.prepareStatement("UPDATE ad SET price = ?, rating = ?, userID = ?, bookID = ?, comment = ?, locked = ? WHERE id = ?");


                deleteAdSQL = connection.prepareStatement("UPDATE ad SET deleted = 1 WHERE id = ?");

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }

        private void close() {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public User authorizeUser (String username, String password) throws Exception {
            ResultSet resultSet = null;
            User user = null;

            try {
                authorizeUserSQL.setString(1, username);
                authorizeUserSQL.setString(2, password);

                resultSet = authorizeUserSQL.executeQuery();

                while (resultSet.next()) {
                    user = new User();

                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setPhonenumber(resultSet.getInt("phonenumber"));
                    user.setAddress(resultSet.getString("address"));
                    user.setEmail(resultSet.getString("email"));
                    user.setMobilePay(resultSet.getInt("mobilepay"));
                    user.setCash(resultSet.getInt("cash"));
                    user.setTransfer(resultSet.getInt("transfer"));
                    user.setType(resultSet.getInt("type"));

                }

            } catch (SQLException e) {
                e.printStackTrace();


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

    public boolean createUser(User user) throws Exception {
            try {
                createUserSQL.setInt(1, user.getType());
                createUserSQL.setString(2, user.getUsername());
                createUserSQL.setString(3, user.getPassword());
                createUserSQL.setInt(4, user.getPhonenumber());
                createUserSQL.setString(5, user.getAddress());
                createUserSQL.setString(6, user.getEmail());
                createUserSQL.setInt(7, user.getMobilePay());
                createUserSQL.setInt(8, user.getCash());
                createUserSQL.setInt(9, user.getTransfer());


                createUserSQL.executeUpdate();
            } catch (SQLException e) {

            }
            return false;
        }

    public boolean updateUser(User user) throws Exception {

        try {
            updateUserSQL.setInt(1, user.getType());
            updateUserSQL.setString(2, user.getUsername());
            updateUserSQL.setString(3, user.getPassword());
            updateUserSQL.setInt(4, user.getPhonenumber());
            updateUserSQL.setString(5, user.getAddress());
            updateUserSQL.setString(6, user.getEmail());
            updateUserSQL.setInt(7, user.getMobilePay());
            updateUserSQL.setInt(8, user.getCash());
            updateUserSQL.setInt(9, user.getTransfer());

            int rowsAffected = updateUserSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getUsers() throws Exception {

        List<User> userlist = null;
        ResultSet resultSet = null;
        User user = null;

        PreparedStatement getUsersSQL = connection
                .prepareStatement("SELECT * FROM brugere WHERE type == 1");

        try {
            resultSet = getUsersSQL.executeQuery();
            userlist = new ArrayList<User>();

            while (resultSet.next()) {
                user = new User();

                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setPhonenumber(resultSet.getInt("phonenumber"));
                user.setAddress(resultSet.getString("address"));
                user.setEmail(resultSet.getString("email"));
                user.setMobilePay(resultSet.getInt("mobilepay"));
                user.setCash(resultSet.getInt("cash"));
                user.setTransfer(resultSet.getInt("transfer"));
                user.setType(resultSet.getInt("type"));

            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        return userlist;
    }

    public boolean deleteUser(int id) throws Exception {
        try {
            deleteUserSQL.setInt(1, id);

            int rowsAffected = deleteUserSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean createBook(Book book) throws Exception {
        try {
            createBookSQL.setInt(1, book.getISBN());
            createBookSQL.setString(2, book.getTitle());
            createBookSQL.setString(3, book.getEdition());
            createBookSQL.setString(4, book.getAuthor());


            createBookSQL.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Book> getBooks() throws Exception {

        List<Book> booklist = null;
        ResultSet resultSet = null;
        PreparedStatement getBooksSQL = connection
                .prepareStatement("SELECT * FROM book");

        try {
            resultSet = getBooksSQL.executeQuery();
            booklist = new ArrayList<Book>();

            while (resultSet.next()) {
                booklist.add(new Book(resultSet.getInt("id"), resultSet.getInt("ISBN"),
                        resultSet.getString("title"), resultSet.getString("edition"), resultSet.getString("author")));

            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        return booklist;
    }

    public boolean deleteBook(int id) throws Exception {
        try {
            deleteBookSQL.setInt(1, id);

            int rowsAffected = deleteBookSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public int createAd(Ad ad) throws Exception {
        try {
            createAdSQL.setInt(1, ad.getPrice());
            createAdSQL.setInt(2, ad.getRating());
            createAdSQL.setInt(3, ad.getUserID());
            createAdSQL.setInt(4, ad.getBookID());
            createAdSQL.setString(5, ad.getComment());
            createAdSQL.setInt(6, ad.getLocked());
            createAdSQL.setInt(7, ad.getDeleted());


            createUserSQL.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Ad> getAds() throws Exception {

        List<Ad> adlist = null;
        ResultSet resultSet = null;
        PreparedStatement getAdsSQL = connection
                .prepareStatement("SELECT * FROM ad WHERE deleted IS NULL");

        try {
            resultSet = getAdsSQL.executeQuery();
            adlist = new ArrayList<Ad>();

            while (resultSet.next()) {
                adlist.add(new Ad(resultSet.getInt("id"), resultSet.getInt("price"), resultSet.getInt("rating"), resultSet.getInt("userID"), resultSet.getInt("bookID"),
                        resultSet.getString("comment"), resultSet.getInt("locked")));

            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        return adlist;
    }


    public boolean updateAd(Ad ad) throws Exception {

        try {
            updateAdSQL.setInt(1, ad.getPrice());
            updateAdSQL.setInt(2, ad.getRating());
            updateAdSQL.setInt(3, ad.getUserID());
            updateAdSQL.setInt(4, ad.getBookID());
            updateAdSQL.setString(5, ad.getComment());
            updateAdSQL.setInt(6, ad.getLocked());
            updateAdSQL.setInt(7, ad.getDeleted());

            int rowsAffected = updateAdSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAd(int id) throws Exception {
        try {
            deleteAdSQL.setInt(1, id);

            int rowsAffected = deleteAdSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}



