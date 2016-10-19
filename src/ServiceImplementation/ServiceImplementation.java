package ServiceImplementation;

import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Controller.ConfigController;

import DTOobjects.*;

<<<<<<< HEAD
=======


>>>>>>> master
public class ServiceImplementation {

    Config config = new ConfigController().getConfig();

    String url = config.getDbType()+config.getDbHost()+":"+config.getDbPort()+"/" + config.getDbName()+ "?useSSL=false";
    String username = config.getDbUser();
    String password = config.getDbPass();

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
        PreparedStatement getMyAdsSQL = null;


    	public ServiceImplementation() {
            try {
                connection = DriverManager.getConnection(url, username, password);

                authorizeUserSQL = connection.prepareStatement("SELECT * FROM user where username = ? AND password = ?");
//USERS
                createUserSQL = connection.prepareStatement(
                        "INSERT INTO user" + " (username, password, phonenumber, address, email, mobilepay, cash, transfer, type)"
                                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

                getUsersSQL = connection.prepareStatement("SELECT * FROM user");

                updateUserSQL = connection.prepareStatement("UPDATE user SET username = ?, password = ?, phonenumber = ?, address = ?, email = ?, mobilepay = ?, cash = ?, transfer = ? WHERE id = ?");

                deleteUserSQL = connection.prepareStatement("DELETE FROM user WHERE id = ?");
//BOOKS
                createBookSQL = connection.prepareStatement(
                        "INSERT INTO book" + " (ISBN, title, edition, author)"
                                + " VALUES (?, ?, ?, ?)");

                getBooksSQL = connection.prepareStatement("SELECT * FROM book");


                deleteBookSQL = connection.prepareStatement("DELETE FROM book WHERE id = ?");
//ADS
                createAdSQL = connection.prepareStatement(
                        "INSERT INTO ad" + " (price, rating, userID, bookID, comment, locked, deleted)"
                                + " VALUES (?, ?, ?, ?, ?, 0, 0)");

                getAdsSQL = connection.prepareStatement("SELECT * FROM ad WHERE deleted IS NULL AND locked IS NULL");

                getMyAdsSQL = connection.prepareStatement("SELECT * FROM ad WHERE deleted IS NULL AND WHERE userID = ?");
				
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

        public User authorizeUser (String username, String password) {
            ResultSet resultSet = null;
            User user = null;

            try {
                authorizeUserSQL.setString(1, username);
                authorizeUserSQL.setString(2, md5Hash(password));

                resultSet = authorizeUserSQL.executeQuery();

                while (resultSet.next()) {
                    user = new User();

                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setPhonenumber(resultSet.getInt("phonenumber"));
                    user.setAddress(resultSet.getString("address"));
                    user.setEmail(resultSet.getString("email"));
                    user.setMobilepay(resultSet.getInt("mobilepay"));
                    user.setCash(resultSet.getInt("cash"));
                    user.setTransfer(resultSet.getInt("transfer"));
                    user.setType(resultSet.getInt("type"));

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
            return user;
        }

    public boolean createUser(User user) {
            try {
                createUserSQL.setString(1, user.getUsername());
                createUserSQL.setString(2, md5Hash(user.getPassword()));
                createUserSQL.setInt(3, user.getPhonenumber());
                createUserSQL.setString(4, user.getAddress());
                createUserSQL.setString(5, user.getEmail());
                createUserSQL.setInt(6, user.getMobilepay());
                createUserSQL.setInt(7, user.getCash());
                createUserSQL.setInt(8, user.getTransfer());
                createUserSQL.setInt(9, user.getType());

                int rowsAffected = createUserSQL.executeUpdate();

                if (rowsAffected == 1) {
                    return true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return false;
        }

    public boolean updateUser(User user) {

        try {
            updateUserSQL.setInt(1, user.getType());
            updateUserSQL.setString(2, user.getUsername());
            updateUserSQL.setString(3, md5Hash(user.getPassword()));
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
        }

        return false;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> userlist = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            resultSet = getUsersSQL.executeQuery();

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setPhonenumber(resultSet.getInt("phonenumber"));
                user.setAddress(resultSet.getString("address"));
                user.setEmail(resultSet.getString("email"));
                user.setMobilepay(resultSet.getInt("mobilepay"));
                user.setCash(resultSet.getInt("cash"));
                user.setTransfer(resultSet.getInt("transfer"));
                user.setType(resultSet.getInt("type"));

                userlist.add(user);

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
        return (ArrayList<User>) userlist;
    }

    public boolean deleteUser(int id) {
        try {
            deleteUserSQL.setInt(1, id);

            int rowsAffected = deleteUserSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean createBook(Book book) {
        try {

            createBookSQL.setLong(1, book.getISBN());
            createBookSQL.setString(2, book.getTitle());
            createBookSQL.setString(3, book.getEdition());
            createBookSQL.setString(4, book.getAuthor());

            createBookSQL.executeUpdate();

            int rowsAffected = createBookSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Book> getBooks() {

        ArrayList<Book> booklist = new ArrayList<>(); //ændret fra = null
        ResultSet resultSet = null;


        try {
            resultSet = getBooksSQL.executeQuery();
            booklist = new ArrayList<Book>();

            while (resultSet.next()) {
                Book book = new Book();

                book.setId(resultSet.getInt("id"));
                book.setISBN(resultSet.getLong("isbn"));
                book.setTitle(resultSet.getString("title"));
                book.setEdition(resultSet.getString("edition"));
                book.setAuthor(resultSet.getString("author"));

                booklist.add(book);
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

    public boolean deleteBook(int id) {
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


    public boolean createAd(Ad ad) {
        try {
            createAdSQL.setInt(1, ad.getPrice());
            createAdSQL.setInt(2, ad.getRating());
            createAdSQL.setInt(3, ad.getUserID());
            createAdSQL.setInt(4, ad.getBookID());
            createAdSQL.setString(5, ad.getComment());


            createUserSQL.executeUpdate();

            int rowsAffected = createAdSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public List<Ad> getAds() {

        ArrayList<Ad> adlist = new ArrayList<>();
        ResultSet resultSet = null;
        Ad ad = null;

        try {
            resultSet = getAdsSQL.executeQuery();
            adlist = new ArrayList<Ad>();

            while (resultSet.next()) {
                ad = new Ad();

                ad.setId(resultSet.getInt("id"));
                ad.setPrice(resultSet.getInt("price"));
                ad.setRating(resultSet.getInt("rating"));
                ad.setUserID(resultSet.getInt("userID"));
                ad.setBookID(resultSet.getInt("bookID"));
                ad.setComment(resultSet.getString("comment"));
                ad.setLocked(resultSet.getInt("locked"));
                ad.setTime(resultSet.getTimestamp("time"));
                ad.setDeleted(resultSet.getInt("deleted"));

                adlist.add(ad);
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
	
    public List<Ad> getMyAds()  {

        List<Ad> myadlist = null;
        ResultSet resultSet = null;
        Ad ad = null;


        try {
            resultSet = getMyAdsSQL.executeQuery();
            myadlist = new ArrayList<Ad>();

            while (resultSet.next()) {
                ad = new Ad();

                ad.setId(resultSet.getInt("id"));
                ad.setPrice(resultSet.getInt("price"));
                ad.setRating(resultSet.getInt("rating"));
                ad.setUserID(resultSet.getInt("userID"));
                ad.setBookID(resultSet.getInt("bookID"));
                ad.setComment(resultSet.getString("comment"));
                ad.setLocked(resultSet.getInt("locked"));
                ad.setTime(resultSet.getTimestamp("time"));
                ad.setDeleted(resultSet.getInt("deleted"));

                myadlist.add(ad);
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
        return myadlist;
    }
	


    public boolean updateAd(Ad ad) {

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

    public boolean deleteAd(int id) {
        try {
            deleteAdSQL.setInt(1, id);

            int rowsAffected = deleteAdSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public String md5Hash(String password) {
        if(!password.isEmpty()) {
            try {
                MessageDigest digestPassword = MessageDigest.getInstance("MD5");
                byte[] bytesPassword = digestPassword.digest(password.getBytes("UTF-8"));
                StringBuffer sbPassword = new StringBuffer();
                for (int i = 0; i < bytesPassword.length; ++i) {
                    sbPassword.append(Integer.toHexString((bytesPassword[i] & 0xFF) | 0x100).substring(1,3));
                }
                return sbPassword.toString();
            } catch (Exception ex) {
                System.out.println("Fejl med hashing");
            }
        }

        return null;
    }

}



