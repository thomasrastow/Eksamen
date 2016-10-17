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

        try (Connection connection = DriverManager.getConnection(url, username, password, database)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }



    }



}

