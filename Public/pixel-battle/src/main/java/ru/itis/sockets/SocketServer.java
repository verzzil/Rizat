package ru.itis.sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {

    public void start(int port) {
        ServerSocket socket;

        try {
            socket = new ServerSocket(port);

            Socket client1 = socket.accept();
            Socket client2 = socket.accept();

            CountDownLatch latch = new CountDownLatch(2); //сервер завершит работу, когда отключатся оба клиента

            //задачи по поддержанию работы клиентов
            ClientKeepUpTask client1KeepUp = new ClientKeepUpTask(readerFromClient(client1), writerToClient(client2), latch);
            ClientKeepUpTask client2KeepUp = new ClientKeepUpTask(readerFromClient(client2), writerToClient(client1), latch);

            //запускаем задачи в сервисе
            ExecutorService keepUpService = Executors.newFixedThreadPool(2);
            keepUpService.execute(client1KeepUp);
            keepUpService.execute(client2KeepUp);

            try {
                latch.await(); //ждем, пока оба клиента отключатся
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("closing server");
            keepUpService.shutdownNow(); //останавливаем сервис
            socket.close(); //закрываем сервер

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private BufferedReader readerFromClient(Socket client) {
        try {
            return new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private PrintWriter writerToClient(Socket client) {
        try {
            return new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
