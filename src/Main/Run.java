package Main; /**
 * Created by C.F. Overgaard on 17/10/2016.
 */
import java.net.InetSocketAddress;

import DTOobjects.User;
import Endpoints.UserEndpoint;
import Endpoints.LoginEndpoint;
import Endpoints.AdEndpoint;
import com.sun.net.httpserver.HttpServer;



    public class Run {

        public static void main(String[] args) throws Exception {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/login", new LoginEndpoint.LoginHandler());
            server.createContext("/getusers", new UserEndpoint.GetUsersHandler());
            server.createContext("/createuser", new UserEndpoint.CreateUserHandler());
            server.createContext("/deleteuser", new UserEndpoint.DeleteUserHandler());
            server.createContext("/getads", new AdEndpoint.GetAdHandler());
            server.createContext("/createad", new AdEndpoint.CreateAdHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
            System.out.println("The server is running");
        }
    }
