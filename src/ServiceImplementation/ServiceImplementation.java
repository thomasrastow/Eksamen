package ServiceImplementation;

import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Controller.ConfigController;

import DTOobjects.*;


public class ServiceImplementation {

    Config config = new ConfigController().getConfig();

    String url = config.getDbType() + config.getDbHost() + ":" + config.getDbPort() + "/" + config.getDbName() + "?useSSL=false";
    String username = config.getDbUser();
    String password = config.getDbPass();

    Connection connection = null;

    PreparedStatement authorizeUserSQL = null;

    PreparedStatement createUserSQL = null;
    PreparedStatement updateUserSQL = null;
    PreparedStatement getUsersSQL = null;
    PreparedStatement deleteUserSQL = null;
    PreparedStatement getUserSQL = null;

    PreparedStatement createBookSQL = null;
    PreparedStatement getBooksSQL = null;
    PreparedStatement deleteBookSQL = null;

    PreparedStatement createAdSQL = null;
    PreparedStatement getAdsAllSQL = null;
    PreparedStatement getAdsSQL = null;
    PreparedStatement getMyAdsSQL = null;
    PreparedStatement getAdsBookSQL = null;
    PreparedStatement updateAdSQL = null;
    PreparedStatement deleteAdSQL = null;

    PreparedStatement getAdSQL = null;

    PreparedStatement searchBooksTitleSQL = null;
    PreparedStatement searchBooksAuthorSQL = null;

    public ServiceImplementation() {
        try {
            connection = DriverManager.getConnection(url, username, password);

            authorizeUserSQL = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
//USERS
            createUserSQL = connection.prepareStatement(
                    "INSERT INTO users (username, password, phonenumber, address, email, mobilepay, cash, transfer, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            getUsersSQL = connection.prepareStatement("SELECT * FROM users");

            // updateUserSQL = connection.prepareStatement("UPDATE user SET phonenumber = ?, address = ?, email = ?, mobilepay = ?, cash = ?, transfer = ? WHERE id = ?");
            updateUserSQL = connection.prepareStatement("UPDATE users SET username = ?, password = ?, phonenumber = ?, address = ?, email = ?, mobilepay = ?, cash = ?, transfer = ? WHERE userid = ?");

            deleteUserSQL = connection.prepareStatement("DELETE FROM users WHERE userid = ?");

            getUserSQL = connection.prepareStatement("SELECT * FROM users WHERE userid = ?");

//BOOKS
            createBookSQL = connection.prepareStatement(
                    "INSERT INTO books (isbn, title, edition, author) VALUES (?, ?, ?, ?)");

            getBooksSQL = connection.prepareStatement("SELECT * FROM books");


            deleteBookSQL = connection.prepareStatement("DELETE FROM books WHERE isbn = ?");
//ADS
            createAdSQL = connection.prepareStatement(
                    "INSERT INTO ads (price, rating, userid, isbn, comment, locked, deleted) VALUES (?, ?, ?, ?, ?, ?, ?)");

            getAdsAllSQL = connection.prepareStatement("SELECT * FROM ads");

            getAdsSQL = connection.prepareStatement("SELECT * FROM ads WHERE deleted=0 AND locked=0");

            getMyAdsSQL = connection.prepareStatement("SELECT * FROM ads WHERE deleted=0 AND userid = ?");

            getAdsBookSQL = connection.prepareStatement("SELECT ads.adid, ads.price, ads.rating, ads.userid, ads.comment, ads.isbn, books.title, books.edition, books.author FROM ads INNER JOIN books ON books.isbn = ? WHERE ads.locked=0 AND ads.deleted=0");

            updateAdSQL = connection.prepareStatement("UPDATE ads SET price = ?, rating = ?, userid = ?, isbn = ?, comment = ?, locked = ? WHERE id = ?");

            deleteAdSQL = connection.prepareStatement("UPDATE ads SET deleted = 1 WHERE id = ?");

            getAdSQL = connection.prepareStatement("SELECT * from ads WHERE id = ?");

            searchBooksTitleSQL = connection.prepareStatement("SELECT * FROM books WHERE title LIKE ?");

            searchBooksAuthorSQL = connection.prepareStatement("SELECT * FROM books WHERE author LIKE ?");

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

                user.setId(resultSet.getInt("userid"));
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

        ResultSet resultSet = null;

        try {
         /*   updateUserSQL.setInt(1, user.getPhonenumber());
            updateUserSQL.setString(2, user.getAddress());
            updateUserSQL.setString(3, user.getEmail());
            updateUserSQL.setInt(4, user.getMobilepay());
            updateUserSQL.setInt(5, user.getCash());
            updateUserSQL.setInt(6, user.getTransfer());
            updateUserSQL.setInt(7, user.getId());*/
            updateUserSQL.setString(1, user.getUsername());
            updateUserSQL.setString(2, md5Hash(user.getPassword()));
            updateUserSQL.setInt(3, user.getPhonenumber());
            updateUserSQL.setString(4, user.getAddress());
            updateUserSQL.setString(5, user.getEmail());
            updateUserSQL.setInt(6, user.getMobilepay());
            updateUserSQL.setInt(7, user.getCash());
            updateUserSQL.setInt(8, user.getTransfer());
            updateUserSQL.setInt(9, user.getId());



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
        ArrayList<User> listUsers = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            resultSet = getUsersSQL.executeQuery();

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("userid"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setPhonenumber(resultSet.getInt("phonenumber"));
                user.setAddress(resultSet.getString("address"));
                user.setEmail(resultSet.getString("email"));
                user.setMobilepay(resultSet.getInt("mobilepay"));
                user.setCash(resultSet.getInt("cash"));
                user.setTransfer(resultSet.getInt("transfer"));
                user.setType(resultSet.getInt("type"));

                listUsers.add(user);

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
        return listUsers;
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


    public User getUser(int id) {
        ResultSet resultSet = null;
        User user = null;

        try{

            getUserSQL.setInt(1, id);

            resultSet = getUserSQL.executeQuery();

            while (resultSet.next()){
                user = new User();

                user.setId(resultSet.getInt("userid"));
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

            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }


    public boolean createBook(Book book) {
        try {
            createBookSQL.setLong(1, book.getISBN());
            createBookSQL.setString(2, book.getTitle());
            createBookSQL.setString(3, book.getEdition());
            createBookSQL.setString(4, book.getAuthor());

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

        ArrayList<Book> listBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Book book = null;

        try {
            resultSet = getBooksSQL.executeQuery();
            listBooks = new ArrayList<Book>();

            while (resultSet.next()) {
                book = new Book();

                book.setISBN(resultSet.getLong("isbn"));
                book.setTitle(resultSet.getString("title"));
                book.setEdition(resultSet.getString("edition"));
                book.setAuthor(resultSet.getString("author"));

                listBooks.add(book);
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
        return listBooks;
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
            createAdSQL.setInt(3, ad.getUserId());
            createAdSQL.setLong(4, ad.getBookISBN());
            createAdSQL.setString(5, ad.getComment());
            createAdSQL.setInt(6, ad.getLocked());
            createAdSQL.setInt(7, ad.getDeleted());

            int rowsAffected = createAdSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public ArrayList<Ad> getAds(){

        ArrayList<Ad> listAds = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            resultSet = getAdsSQL.executeQuery();

            while (resultSet.next()) {
                Ad ad = new Ad();

                ad.setId(resultSet.getInt("adid"));
                ad.setPrice(resultSet.getInt("price"));
                ad.setRating(resultSet.getInt("rating"));
                ad.setUserId(resultSet.getInt("userid"));
                ad.setBookISBN(resultSet.getLong("isbn"));
                ad.setComment(resultSet.getString("comment"));
                ad.setLocked(resultSet.getInt("locked"));
                ad.setDeleted(resultSet.getInt("deleted"));

                listAds.add(ad);
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
        return listAds;
    }

    public ArrayList<Ad> getAdsAll(){

        ArrayList<Ad> listAds = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            resultSet = getAdsAllSQL.executeQuery();

            while (resultSet.next()) {
                Ad ad = new Ad();

                ad.setId(resultSet.getInt("adid"));
                ad.setPrice(resultSet.getInt("price"));
                ad.setRating(resultSet.getInt("rating"));
                ad.setUserId(resultSet.getInt("userid"));
                ad.setBookISBN(resultSet.getLong("isbn"));
                ad.setComment(resultSet.getString("comment"));
                ad.setLocked(resultSet.getInt("locked"));
                ad.setDeleted(resultSet.getInt("deleted"));

                listAds.add(ad);
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
        return listAds;
    }

    public ArrayList<Ad> getMyAds(int id) {
        ArrayList<Ad> listMyAds = new ArrayList<>();
        ResultSet resultSet = null;
        Ad ad = null;

        try {
            getMyAdsSQL.setInt(1, id);

            resultSet = getMyAdsSQL.executeQuery();
            listMyAds = new ArrayList<Ad>();

            while (resultSet.next()) {
                ad = new Ad();

                ad.setId(resultSet.getInt("adid"));
                ad.setPrice(resultSet.getInt("price"));
                ad.setRating(resultSet.getInt("rating"));
                ad.setUserId(resultSet.getInt("userid"));
                ad.setBookISBN(resultSet.getLong("isbn"));
                ad.setComment(resultSet.getString("comment"));
                ad.setLocked(resultSet.getInt("locked"));
                ad.setDeleted(resultSet.getInt("deleted"));

                listMyAds.add(ad);
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
        return listMyAds;
    }

    public boolean updateAd(Ad ad) {
        try {
            updateAdSQL.setInt(1, ad.getPrice());
            updateAdSQL.setInt(2, ad.getRating());
            updateAdSQL.setInt(3, ad.getUserId());
            updateAdSQL.setLong(4, ad.getBookISBN());
            updateAdSQL.setString(5, ad.getComment());
            updateAdSQL.setInt(6, ad.getLocked());
            updateAdSQL.setInt(7, ad.getId());

            int rowsAffected = updateAdSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Ad> getAdsBook(long ISBN) {

        ArrayList<Ad> listAds = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            getAdsBookSQL.setLong(1, ISBN);

            resultSet = getAdsBookSQL.executeQuery();

            while (resultSet.next()) {
                Ad ad = new Ad();

                ad.setId(resultSet.getInt("adid"));
                ad.setPrice(resultSet.getInt("price"));
                ad.setRating(resultSet.getInt("rating"));
                ad.setUserId(resultSet.getInt("userid"));
                ad.setComment(resultSet.getString("comment"));
                ad.setBookISBN(resultSet.getLong("isbn"));
                ad.setBookTitle(resultSet.getString("title"));
                ad.setBookEdition(resultSet.getString("edition"));
                ad.setBookAuthor(resultSet.getString("author"));

                listAds.add(ad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listAds;
    }

    public Ad getAd(int id) {
        ResultSet resultSet = null;
        Ad ad = null;

        try{
            getAdSQL.setInt(1, id);

            resultSet = getAdSQL.executeQuery();

            while(resultSet.next()){
                ad = new Ad();

                ad.setId(resultSet.getInt("adid"));
                ad.setPrice(resultSet.getInt("price"));
                ad.setRating(resultSet.getInt("rating"));
                ad.setUserId(resultSet.getInt("userid"));
                ad.setComment(resultSet.getString("comment"));
                ad.setLocked(resultSet.getInt("locked"));
                ad.setDeleted(resultSet.getInt("deleted"));
                ad.setBookISBN(resultSet.getLong("isbn"));
            }

            resultSet.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ad;
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

    public ArrayList<Book> searchBooksTitle(String title) {

        ArrayList<Book> listBooks = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            searchBooksTitleSQL.setString(1, "%" + title + "%");

            resultSet = searchBooksTitleSQL.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();

                book.setISBN(resultSet.getLong("isbn"));
                book.setTitle(resultSet.getString("title"));
                book.setEdition(resultSet.getString("edition"));
                book.setAuthor(resultSet.getString("author"));

                listBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listBooks;
    }

    public ArrayList<Book> searchBooksAuthor(String author) {

        ArrayList<Book> listBooks = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            searchBooksAuthorSQL.setString(1, "%" + author + "%");

            resultSet = searchBooksAuthorSQL.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();

                book.setISBN(resultSet.getLong("isbn"));
                book.setTitle(resultSet.getString("title"));
                book.setEdition(resultSet.getString("edition"));
                book.setAuthor(resultSet.getString("author"));

                listBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listBooks;
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



