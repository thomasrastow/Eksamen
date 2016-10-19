package Main; /**
 * Created by C.F. Overgaard on 17/10/2016.
 */
import Controller.ConfigController;
import DTOobjects.Config;
import Endpoints.BookEndpoint;
import Endpoints.LoginEndpoint;
import Endpoints.UserEndpoint;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

package Main;

import Controller.ServerController;

public class Run {

    public static void main(String[] args) throws Exception {
        ServerController serverController = new ServerController();

            public static void main(String[] args) throws Exception {
                HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

                Config config = new ConfigController().getConfig();
                int srvPort = Integer.parseInt(config.getSrvPort());
                server = HttpServer.create(new InetSocketAddress(srvPort), 0);
                server.createContext("/login", new LoginEndpoint.LoginHandler());
                server.createContext("/getusers", new UserEndpoint.GetUsersHandler());
                server.createContext("/createuser", new UserEndpoint.CreateUserHandler());
                server.createContext("/deleteuser", new UserEndpoint.DeleteUserHandler());

                // book run
                server.createContext("/getbooks", new BookEndpoint.GetBooksHandler());
                server.createContext("/createbook", new BookEndpoint.CreateBookHandler());
                server.createContext("/deletebook", new BookEndpoint.DeleteBookHandler());


                server.setExecutor(null); // creates a default executor
                server.start();
                System.out.println("The server is running");
            }
        serverController.startServer();
        }
    }
}
