package Main; /**
 * Created by C.F. Overgaard on 17/10/2016.
 */
import java.net.InetSocketAddress;

import Endpoints.UserEndpoint;
import Endpoints.LoginEndpoint;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;



    public class Run {

        public static void main(String[] args) throws Exception {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            //server.setHttpsConfigurator(new HttpsConfigurator(createSSLContext()));
            server.createContext("/login", new LoginEndpoint.LoginHandler());
            server.createContext("/getusers", new UserEndpoint.GetUsersHandler());
            server.createContext("/createuser", new UserEndpoint.CreateUserHandler());
            server.createContext("/deleteuser", new UserEndpoint.DeleteUserHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
            System.out.println("The server is running");
        }
        //kode er fundet på: http://stackoverflow.com/questions/24959605/create-pure-java-https-server-with-single-sided-authentication
        private static SSLContext createSSLContext() throws Exception {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream is = new FileInputStream("/Users/mikkelaltmann/book/src/Main/book.crt");
            InputStream caInput = new BufferedInputStream(is);
            Certificate ca = cf.generateCertificate(caInput);
            caInput.close();

            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            // Create an SSLContext that uses our TrustManager
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);

            return context;
        }
    }
