package ru.itis.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;

public class ClientKeepUpTask implements Runnable {
    private BufferedReader fromClient;
    private PrintWriter toOtherClient;
    private CountDownLatch latch;
    private Boolean isStopped;

    public ClientKeepUpTask(BufferedReader fromClient, PrintWriter toOtherClient, CountDownLatch latch) {
        this.fromClient = fromClient;
        this.toOtherClient = toOtherClient;
        this.latch = latch;
        this.isStopped = false;
    }

    @Override
    public void run() {
        try {
            System.out.println("now running");
            while (!isStopped) {
                String messageFromClient = fromClient.readLine();
                System.out.println(messageFromClient);
                if (messageFromClient != null) {
                    if (messageFromClient.equals("stop")) { //клиент отключился
                        isStopped = true;
                        latch.countDown(); //+1 отключенный клиент
                    } else { //любые другие сообщения пересылаем другому клиенту, пусть сам разбирается
                        toOtherClient.println(messageFromClient);
                    }
                }
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
