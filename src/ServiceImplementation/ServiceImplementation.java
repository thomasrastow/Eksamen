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
    PreparedStatement getUserPublicSQL = null;

    PreparedStatement createBookSQL = null;
    PreparedStatement getBooksSQL = null;
    PreparedStatement deleteBookSQL = null;

    PreparedStatement createAdSQL = null;
    PreparedStatement getAdsAllSQL = null;
    PreparedStatement getAdsSQL = null;

    PreparedStatement getMyAdsSQL = null;
    PreparedStatement updateAdSQL = null;
    PreparedStatement lockAdSQL = null;
    PreparedStatement unlockAdSQL = null;

    PreparedStatement createReservationSQL = null;
    PreparedStatement deleteReservationSQL = null;
    PreparedStatement getMyReservationsSQL = null;
    PreparedStatement getReservationSQL = null;

    PreparedStatement getAdsUserSQL = null;
    PreparedStatement getAdsBookSQL = null;
    PreparedStatement deleteAdSQL = null;

    PreparedStatement getAdSQL = null;
    PreparedStatement getAdPublicSQL = null;

    PreparedStatement createSessionSQL = null;
    PreparedStatement getSessionSQL = null;
    PreparedStatement clearSessionsSQL = null;

    public ServiceImplementation() {
        try {
            connection = DriverManager.getConnection(url, username, password);

            authorizeUserSQL = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
//USERS
            createUserSQL = connection.prepareStatement(
                    "INSERT INTO users (username, password, phonenumber, address, email, mobilepay, cash, transfer, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            getUsersSQL = connection.prepareStatement("SELECT * FROM users WHERE type != 1");

            // updateUserSQL = connection.prepareStatement("UPDATE user SET phonenumber = ?, address = ?, email = ?, mobilepay = ?, cash = ?, transfer = ? WHERE id = ?");
            updateUserSQL = connection.prepareStatement("UPDATE users SET username = ?, password = COALESCE(?,password), phonenumber = ?, address = ?, email = ?, mobilepay = ?, cash = ?, transfer = ? WHERE userid = ? AND type != 1");

            deleteUserSQL = connection.prepareStatement("DELETE FROM users WHERE userid = ? AND type != 1");

            getUserSQL = connection.prepareStatement("SELECT * FROM users WHERE userid = ? AND type != 1");

            getUserPublicSQL = connection.prepareStatement("SELECT address, mobilepay, cash, transfer FROM users WHERE username = ? AND type != 1");

//BOOKS
            createBookSQL = connection.prepareStatement(
                    "INSERT INTO books (isbn, title, edition, author) VALUES (?, ?, ?, ?)");

            getBooksSQL = connection.prepareStatement("SELECT * FROM books");


            deleteBookSQL = connection.prepareStatement("DELETE FROM books WHERE isbn = ?");
//ADS
            createAdSQL = connection.prepareStatement(
                    "INSERT INTO ads (userid, isbn, rating, comment, price) VALUES (?, ?, ?, ?, ?)");

            getAdsAllSQL = connection.prepareStatement("SELECT * FROM ads");

            getAdsSQL = connection.prepareStatement("SELECT ads.adid, ads.isbn, ads.price, ads.rating, users.username, books.title, books.edition, books.author FROM ads INNER JOIN users ON ads.userid = users.userid INNER JOIN books ON ads.isbn = books.isbn WHERE deleted = 0 AND locked = 0");

            getMyAdsSQL = connection.prepareStatement("SELECT * FROM ads WHERE userid = ? AND deleted = 0");

            getAdsUserSQL = connection.prepareStatement("SELECT ads.adid, ads.isbn, ads.price, ads.rating, books.title, books.edition, books.author FROM ads INNER JOIN users ON ads.userid = users.userid INNER JOIN books ON ads.isbn = books.isbn WHERE users.username = ? AND deleted = 0 AND locked = 0");

            getAdsBookSQL = connection.prepareStatement("SELECT ads.adid, ads.isbn, ads.price, ads.rating, users.username, books.title, books.edition, books.author FROM ads INNER JOIN users ON ads.userid = users.userid INNER JOIN books ON ads.isbn = books.isbn WHERE books.isbn = ? AND deleted = 0 AND locked = 0");

            updateAdSQL = connection.prepareStatement("UPDATE ads SET rating = ?, comment = ?, price = ? WHERE adid = ? AND locked = 0 AND deleted = 0");

            deleteAdSQL = connection.prepareStatement("UPDATE ads SET deleted = 1 WHERE adid = ?");
//RESERVATION
            createReservationSQL = connection.prepareStatement("INSERT INTO reservations (adid, userid) VALUES (?, ?)");

            deleteReservationSQL = connection.prepareStatement("DELETE FROM reservations WHERE adid = ?");

            getMyReservationsSQL = connection.prepareStatement("SELECT reservations.adid, reservations.timestamp, ads.isbn, users.username, users.phonenumber FROM reservations INNER JOIN ads ON reservations.adid = ads.adid INNER JOIN users ON ads.userid = users.userid WHERE reservations.userid = ? AND deleted = 0");

            getReservationSQL = connection.prepareStatement("SELECT * FROM reservations WHERE adid = ?");

            lockAdSQL = connection.prepareStatement("UPDATE ads SET locked = 1 WHERE adid = ? AND deleted = 0");

            unlockAdSQL = connection.prepareStatement("UPDATE ads SET locked = 0 WHERE adid = ? AND deleted = 0");

            getAdSQL = connection.prepareStatement("SELECT * from ads WHERE adid = ?");

            getAdPublicSQL = connection.prepareStatement("SELECT ads.adid, ads.isbn, ads.price, ads.rating, ads.comment, users.username, users.address, users.mobilepay, users.cash, users.transfer, books.title, books.edition, books.author FROM ads INNER JOIN users ON ads.userid = users.userid INNER JOIN books ON ads.isbn = books.isbn WHERE ads.adid = ? AND deleted = 0 AND locked = 0");

//SESSIONS
            createSessionSQL = connection.prepareStatement("INSERT INTO sessions (sessionid, userid) VALUES (?, ?)");

            getSessionSQL = connection.prepareStatement("SELECT sessions.*, users.type FROM sessions INNER JOIN users ON sessions.userid=users.userid WHERE sessionid = ?");

            clearSessionsSQL = connection.prepareStatement("DELETE FROM sessions WHERE userid = ?");

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

    public User getUserPublic(String username) {
        ResultSet resultSet = null;
        User user = null;

        try{

            getUserPublicSQL.setString(1, username);

            resultSet = getUserPublicSQL.executeQuery();

            while (resultSet.next()){
                user = new User();

                user.setAddress(resultSet.getString("address"));
                user.setMobilepay(resultSet.getInt("mobilepay"));
                user.setCash(resultSet.getInt("cash"));
                user.setTransfer(resultSet.getInt("transfer"));
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
            sqlException.printStackTrace();
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

    public boolean deleteBook(Long isbn) {
        try {
            deleteBookSQL.setLong(1, isbn);

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
            createAdSQL.setInt(1, ad.getUserId());
            createAdSQL.setLong(2, ad.getIsbn());
            createAdSQL.setInt(3, ad.getRating());
            createAdSQL.setString(4, ad.getComment());
            createAdSQL.setInt(5, ad.getPrice());

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
                ad.setUserUsername(resultSet.getString("username"));
                ad.setIsbn(resultSet.getLong("isbn"));
                ad.setBookTitle(resultSet.getString("title"));
                ad.setBookAuthor(resultSet.getString("author"));
                ad.setBookEdition(resultSet.getString("edition"));
                ad.setRating(resultSet.getInt("rating"));
                ad.setPrice(resultSet.getInt("price"));

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
                ad.setIsbn(resultSet.getLong("isbn"));
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

        try {
            getMyAdsSQL.setInt(1, id);

            resultSet = getMyAdsSQL.executeQuery();

            while (resultSet.next()) {
                Ad ad = new Ad();

                ad.setId(resultSet.getInt("adid"));
                ad.setPrice(resultSet.getInt("price"));
                ad.setRating(resultSet.getInt("rating"));
                ad.setUserId(resultSet.getInt("userid"));
                ad.setIsbn(resultSet.getLong("isbn"));
                ad.setComment(resultSet.getString("comment"));
                ad.setLocked(resultSet.getInt("locked"));
                ad.setDeleted(resultSet.getInt("deleted"));

                listMyAds.add(ad);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
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
            updateAdSQL.setInt(1, ad.getRating());
            updateAdSQL.setString(2, ad.getComment());
            updateAdSQL.setInt(3, ad.getPrice());
            updateAdSQL.setInt(4, ad.getId());

            int rowsAffected = updateAdSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Ad> getAdsUser(String username) {

        ArrayList<Ad> listAds = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            getAdsUserSQL.setString(1, username);

            resultSet = getAdsUserSQL.executeQuery();

            while (resultSet.next()) {
                Ad ad = new Ad();

                ad.setId(resultSet.getInt("adid"));
                ad.setIsbn(resultSet.getLong("isbn"));
                ad.setBookTitle(resultSet.getString("title"));
                ad.setBookAuthor(resultSet.getString("author"));
                ad.setBookEdition(resultSet.getString("edition"));
                ad.setRating(resultSet.getInt("rating"));
                ad.setPrice(resultSet.getInt("price"));

                listAds.add(ad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        return listAds;
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
                ad.setUserUsername(resultSet.getString("username"));
                ad.setIsbn(resultSet.getLong("isbn"));
                ad.setBookTitle(resultSet.getString("title"));
                ad.setBookAuthor(resultSet.getString("author"));
                ad.setBookEdition(resultSet.getString("edition"));
                ad.setRating(resultSet.getInt("rating"));
                ad.setPrice(resultSet.getInt("price"));

                listAds.add(ad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
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
                ad.setUserId(resultSet.getInt("userid"));
                ad.setIsbn(resultSet.getLong("isbn"));
                ad.setPrice(resultSet.getInt("price"));
                ad.setRating(resultSet.getInt("rating"));
                ad.setComment(resultSet.getString("comment"));
                ad.setLocked(resultSet.getInt("locked"));
                ad.setDeleted(resultSet.getInt("deleted"));
            }

            resultSet.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ad;
    }

    public Ad getAdPublic(int id) {
        ResultSet resultSet = null;
        Ad ad = null;
        try {
            getAdPublicSQL.setInt(1, id);

            resultSet = getAdPublicSQL.executeQuery();

            while(resultSet.next()){
                ad = new Ad();

                ad.setId(resultSet.getInt("adid"));
                ad.setUserUsername(resultSet.getString("username"));
                ad.setUserMobilepay(resultSet.getInt("mobilepay"));
                ad.setUserCash(resultSet.getInt("cash"));
                ad.setUserTransfer(resultSet.getInt("transfer"));
                ad.setUserAddress(resultSet.getString("address"));
                ad.setIsbn(resultSet.getLong("isbn"));
                ad.setBookTitle(resultSet.getString("title"));
                ad.setBookAuthor(resultSet.getString("author"));
                ad.setBookEdition(resultSet.getString("edition"));
                ad.setPrice(resultSet.getInt("price"));
                ad.setRating(resultSet.getInt("rating"));
                ad.setComment(resultSet.getString("comment"));
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

    public boolean lockAd(int id) {
        try {
            lockAdSQL.setInt(1, id);

            int rowsAffected = lockAdSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean unlockAd(int id) {
        try {
            unlockAdSQL.setInt(1, id);

            int rowsAffected = unlockAdSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public boolean createReservation(Reservation reservation) {
        try {
            createReservationSQL.setInt(1, reservation.getAdId());
            createReservationSQL.setInt(2, reservation.getUserId());

            int rowsAffected = createReservationSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteReservation(int adId) {
        try {
            deleteReservationSQL.setInt(1, adId);

            int rowsAffected = deleteReservationSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Reservation> getMyReservations(int userId) {

        ArrayList<Reservation> listReservations = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            getMyReservationsSQL.setInt(1, userId);

            resultSet = getMyReservationsSQL.executeQuery();

            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setAdId(resultSet.getInt("adid"));
                reservation.setTimestamp(resultSet.getTimestamp("timestamp"));
                reservation.setBookIsbn(resultSet.getLong("isbn"));
                reservation.setUserUsername(resultSet.getString("username"));
                reservation.setUserPhonenumber(resultSet.getInt("phonenumber"));

                listReservations.add(reservation);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        return listReservations;
    }

    public Reservation getReservation(int adId) {
        ResultSet resultSet = null;
        Reservation reservation = null;
        try {
            getReservationSQL.setInt(1, adId);

            resultSet = getReservationSQL.executeQuery();

            while(resultSet.next()) {
                reservation = new Reservation();
                reservation.setAdId(resultSet.getInt("adid"));
                reservation.setUserId(resultSet.getInt("userid"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservation;
    }



    public boolean createSession (Session session) {

        try {
            createSessionSQL.setString(1, md5Hash(session.getSessionId()));
            createSessionSQL.setInt(2, session.getUserId());

            int rowsAffected = createSessionSQL.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Session getSession (String sessionId) {
        ResultSet resultSet = null;
        Session session = null;

        try {
            getSessionSQL.setString(1, md5Hash(sessionId));

            resultSet = getSessionSQL.executeQuery();

            while (resultSet.next()) {
                session = new Session();

                session.setSessionId(resultSet.getString("sessionid"));
                session.setUserId(resultSet.getInt("userid"));
                session.setUserType(resultSet.getInt("type"));
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
        return session;
    }

    public boolean clearSessions(int userId) {
        try {
            clearSessionsSQL.setInt(1, userId);

            int rowsAffected = clearSessionsSQL.executeUpdate();

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
                ex.printStackTrace();
            }
        }

        return null;
    }

}
