package ru.itis.sockets;

import javafx.application.Platform;
import javafx.concurrent.Task;
import ru.itis.controllers.MainController;

import java.io.BufferedReader;
import java.io.IOException;

// слушатель сообщений от сервера
public class ReceiveMessageTask extends Task<Void> {
    // читаем сообщения с сервера
    private BufferedReader fromServer; // на EchoServerSocket toClient

    private MainController controller;

    public ReceiveMessageTask(BufferedReader fromServer, MainController controller) {
        this.fromServer = fromServer;
        this.controller = controller;
    }

    @Override
    protected Void call() throws Exception {
        while (true) {
            try {
                String messageFromServer = fromServer.readLine();
                if (messageFromServer != null) {
                    if (messageFromServer.equals("start")) { //начинаем игру, когда другой клиент подключился
                        System.out.println(messageFromServer);
                        Platform.runLater(() -> controller.startGame());
                    } else { //другой клиент изменил клетку
                        //покрасим эту клетку у клиента
                        String[] message = messageFromServer.split(",");
                        Platform.runLater(() -> controller.fillCell(message[0], message[1]));
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
