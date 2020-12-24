package ru.itis.application;

import ru.itis.sockets.SocketServer;

public class MainForServer {
    public static void main(String[] args) {
        SocketServer server = new SocketServer();
        server.start(7777);
    }
}
