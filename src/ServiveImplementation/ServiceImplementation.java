package ServiveImplementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    try{
        Connection connection = DriverManager.getConnection(url, username, password, database));
        System.out.println("Database connected successfully!");
    } catch(SQLException e) {
        throw new IllegalStateException("Database not connected. Pleasy try again.", e);
    }

    public RpcServiceImpl() throws Exception {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);


        } catch (SQLException sqlException) {
            throw new Exception("Database not connected. Please try again.");
        }
    }

    // til at oprette en ny bog

    public void createBook(Book book) throws IllegalArgumentException {

        try {

            // The PreparedStatement som indsaetter/opretter en ny bog.

            PreparedStatement createBook = connection.prepareStatement("INSERT INTO Book (id, ISBN, Title, Edition, Author) VALUES (?, ?, ?, ?, ?)");


            createBook.setInt(1, book.getId());
            createBook.setInt(2, book.getISBN());
            createBook.setString(3, book.getTitle());
            createBook.setString(4, book.getEdition());
            createBook.setString(5, book.getAuthor());


            // preparedStatement bliver udført og den returnerer en row count


            int rowsAffected = createBook.executeUpdate();
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



