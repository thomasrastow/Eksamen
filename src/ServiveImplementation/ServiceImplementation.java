package ServiveImplementation;

import DTOobjects.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                        "INSERT INTO ad" + " (price, rating, userID, bookID, comment, locked, deleted)"
                                + " VALUES (?, ?, ?, ?, ?, 0, 0)");

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
    @Override
    public boolean updateUser(User user) throws Exception {

        try {
            updateUserSQL.setInt(1, user.getType());
            updateUserSQL.setString(2, user.getUsername());
            updateUserSQL.setString(3, user.getPassword());
            updateUserSQL.setInt(4, user.getPhonenumber());
            updateUserSQL.setString(5, user.getAddress());
            updateUserSQL.setString(6, user.getEmail());
            updateUserSQL.setInt(7, user.getMobilepay());
            updateUserSQL.setInt(8, user.getCash());
            updateUserSQL.setInt(9, user.getTransfer());

            int rowsAffected = updateUserSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("There is was an error. Please try again");
        }
        return false;
    }
    @Override
    public List<User> getUsers() throws Exception {

        List<User> userlist = null;
        ResultSet userlistSet = null;
        PreparedStatement getUsersSQL = connection
                .prepareStatement("SELECT * FROM brugere WHERE type == 1");

        try {
            userlistSet = getUsersSQL.executeQuery();
            userlist = new ArrayList<User>();

            while (userlistSet.next()) {
                userlist.add(new User(userlistSet.getInt("id"), userlistSet.getString("username"),
                        userlistSet.getString("password"), userlistSet.getInt("phonenumber"), userlistSet.getString("address"), userlistSet.getString("email")
                        , userlistSet.getInt("mobilepay"), userlistSet.getInt("cash"), userlistSet.getInt("transfer")));

            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } finally {
            try {
                userlistSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        return userlist;
    }

    @Override
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
            throw new DALException("There was an error. Please try again.");
        }
    }

    @Override
    public int createBook(Book book) throws Exception {
        try {
            createBookSQL.setInt(1, book.getISBN());
            createBookSQL.setString(2, user.getTitle());
            createBookSQL.setString(3, user.getEdition());
            createBookSQL.setString(4, user.getAuthor());


            createBookSQL.executeUpdate();
        } catch (SQLException e) {
        }

    }
    @Override
    public List<Book> getBooks() throws Exception {

        List<Book> booklist = null;
        ResultSet booklistSet = null;
        PreparedStatement getBooksSQL = connection
                .prepareStatement("SELECT * FROM book");

        try {
            booklistSet = getBooksSQL.executeQuery();
            booklist = new ArrayList<Book>();

            while (booklistSet.next()) {
                booklist.add(new Book(booklistSet.getInt("id"), booklistSet.getInt("ISBN"),
                        booklistSet.getString("title"), booklistSet.getString("edition"), booklistSet.getString("author")));

            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } finally {
            try {
                booklistSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        return booklist;
    }
    @Override
    public boolean deleteBook(int id) throws Exception {
        try {
            deleteBookSQL.setInt(1, id);

            int rowsAffected = deleteBookSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("There is was an error. Please try again.");
        }
    }

    @Override
    public int createAd(Ad ad) throws Exception {
        try {
            createAdSQL.setInt(1, user.getPrice());
            createAdSQL.setInt(2, user.getRating());
            createAdSQL.setInt(3, user.getUserID());
            createAdSQL.setInt(4, user.getBookID());
            createAdSQL.setString(5, user.getComment());
            createAdSQL.setInt(6, user.getLocked());
            createAdSQL.setInt(7, user.getDeleted());


            createUserSQL.executeUpdate();
        } catch (SQLException e) {
        }

    }
    @Override
    public List<Ad> getAds() throws Exception {

        List<Ad> adlist = null;
        ResultSet adlistSet = null;
        PreparedStatement getAdsSQL = connection
                .prepareStatement("SELECT * FROM ad WHERE deleted IS NULL");

        try {
            adlistSet = getAdsSQL.executeQuery();
            adlist = new ArrayList<Ad>();

            while (adlistSet.next()) {
                adlist.add(new Ad(adlistSet.getInt("id"), adlistSet.getInt("price"), adlistSet.getInt("rating"), adlistSet.getInt("userID"), adlistSet.getInt("bookID"),
                        adlistSet.getString("comment"), adlistSet.getInt("locked")));

            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } finally {
            try {
                adlistSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        return adlist;
    }
    @Override
    public boolean updateAd(Ad ad) throws Exception {

        try {
            updateAdSQL.setInt(1, user.getPrice());
            updateAdSQL.setInt(2, user.getRating());
            updateAdSQL.setInt(3, user.getUserID());
            updateAdSQL.setInt(4, user.getBookID());
            updateAdSQL.setString(5, user.getComment());
            updateAdSQL.setInt(6, user.getLocked());
            updateAdSQL.setInt(7, user.getDeleted());

<<<<<<< HEAD
            int rowsAffected = updateAdSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("There is was an error. Please try again");
        }
        return false;
=======
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
>>>>>>> origin/UserBranch
    }
    @Override
    public boolean deleteAd(int id) throws Exception {
        try {
            deleteAdSQL.setInt(1, id);

<<<<<<< HEAD
            int rowsAffected = deleteAdSQL.executeUpdate();
=======
    //slet bruger
    public boolean deleteUser(User user) throws IllegalArgumentException {
        try {
            PreparedStatement deleteUser = connection
                    .prepareStatement("delete from users where username = ? and password = ?");

            deleteUser.setString(1, user.getUsername());
            deleteUser.setString(2, user.getPassword());

<<<<<<< Updated upstream
    //hent brugere
    @Override
    public List<User> getUsers() throws Exception {

        List<User> userlist = null;
        ResultSet userlistSet = null;
        PreparedStatement getUsersSQL = connection
                .prepareStatement("SELECT * FROM brugere WHERE type == 1");

        try {
            userlistSet = getUsersSQL.executeQuery();
            userlist = new ArrayList<User>();

            while (userresultSet.next()) {
                userlist.add(new User(userlistSet.getInt("id"), userlistSet.getString("username"),
                        userlistSet.getString("password"), userlistSet.getInt("phonenumber"), userlistSet.getString("address"), userlistSet.getString("email")
                        , userlistSet.getInt("mobilepay"), userlistSet.getInt("cash"), userlistSet.getInt("transfer")));

            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } finally {
            try {
                userresultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        return userlist;
    }

=======
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

>>>>>>> origin/UserBranch

            if (rowsAffected == 1) {
                return true;
            }
            return false;
>>>>>>> Stashed changes

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("There is was an error. Please try again.");
        }
    }

}



