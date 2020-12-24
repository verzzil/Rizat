package ru.itis.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    private Socket client;

    private PrintWriter toServer; // на SocketServer fromClient
    private BufferedReader fromServer; // на SocketServer toClient

    public SocketClient(String host, int port) {
        try {
            client = new Socket(host, port);
            toServer = new PrintWriter(client.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void sendMessage(String message) {
        toServer.println(message);
    }

    public BufferedReader getFromServer() {
        return fromServer;
    }

    public void stop() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
