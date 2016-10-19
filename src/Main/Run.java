/*
package Main;

import java.io.IOException;
import java.net.InetSocketAddress;

import Endpoints.UserEndpoint;
import Endpoints.LoginEndpoint;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import javax.net.ssl.*;
*/
package Main;

import java.io.*;
import java.net.InetSocketAddress;
import java.lang.*;
import java.net.URL;

import Endpoints.LoginEndpoint;
import Endpoints.UserEndpoint;
import com.sun.net.httpserver.HttpsServer;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import com.sun.net.httpserver.*;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

import java.net.InetAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsExchange;


public class Run {

    public static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response";
            HttpsExchange httpsExchange = (HttpsExchange) t;
            t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public static void main(String[] args) throws Exception {
        try {

            // HTTPS server and SSL context:
            HttpsServer server = HttpsServer.create(new InetSocketAddress(8000), 0);
            SSLContext sslContext = SSLContext.getInstance("TLS");

            // Keystore setup:
            char[] password = "brugtbog".toCharArray();
            KeyStore keyStore = KeyStore.getInstance("JKS");
            FileInputStream file = new FileInputStream("/Users/Fogell/Desktop/PBB/book/src/Main/keystore.jks");

            keyStore.load(file, password);

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, password);

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");

            trustManagerFactory.init(keyStore);
            //bøllemisher
            // setup the HTTPS context and parameters
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

            server.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
                

                public void configure(HttpsParameters params) {
                    try {
                        // initialise the SSL context
                        SSLContext c = SSLContext.getDefault();
                        SSLEngine engine = c.createSSLEngine();
                        params.setNeedClientAuth(false);
                        params.setCipherSuites(engine.getEnabledCipherSuites());
                        params.setProtocols(engine.getEnabledProtocols());

                        // get the default parameters
                        SSLParameters defaultSSLParameters = c.getDefaultSSLParameters();
                        params.setSSLParameters(defaultSSLParameters);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


            server.createContext("/login", new LoginEndpoint.LoginHandler());
            server.createContext("/getusers", new UserEndpoint.GetUsersHandler());
            server.createContext("/createuser", new UserEndpoint.CreateUserHandler());
            server.createContext("/deleteuser", new UserEndpoint.DeleteUserHandler());
            server.setExecutor(null);
            server.start();

            System.out.println("The server is running");
        } catch (Exception exception) {
            System.out.println("Cannot start HTTPS server - bøllekode!");

            exception.printStackTrace();

        }
    }
}