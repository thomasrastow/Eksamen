package Main; /**
 * Created by C.F. Overgaard on 17/10/2016.
 */
import java.net.InetSocketAddress;

import Controller.ConfigController;
import DTOobjects.Config;
import DTOobjects.User;
import Endpoints.UserEndpoint;
import Endpoints.LoginEndpoint;
import com.sun.net.httpserver.HttpServer;



    public class Run {

        public static void main(String[] args) throws Exception {
            Config config = new ConfigController().getConfig();
            int srvPort = Integer.parseInt(config.getSrvPort());
            HttpServer server = HttpServer.create(new InetSocketAddress(srvPort), 0);
            server.createContext("/login", new LoginEndpoint.LoginHandler());
            server.createContext("/getusers", new UserEndpoint.GetUsersHandler());
            server.createContext("/createuser", new UserEndpoint.CreateUserHandler());
            server.createContext("/deleteuser", new UserEndpoint.DeleteUserHandler());
            server.createContext("/updateuser", new UserEndpoint.UpdateUserHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
            System.out.println("The server is running");
        }
    }
