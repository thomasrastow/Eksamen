package Main;

import Controller.ServerController;

public class Run {

    public static void main(String[] args) throws Exception {
        ServerController serverController = new ServerController();

        serverController.startServer();
    }
}
