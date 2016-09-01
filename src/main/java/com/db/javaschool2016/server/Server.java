package com.db.javaschool2016.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.*;

public class Server {
    private Collection<SingleClient> clientsList = Collections.synchronizedList(new ArrayList<SingleClient>());
    private ExecutorService listenersPool = Executors.newCachedThreadPool();
    private LinkedBlockingQueue<String> messagesQueue = new LinkedBlockingQueue<>();

    private void mainLoop() {
        ExecutorService clientsAccepter = Executors.newSingleThreadExecutor();
        clientsAccepter.execute(new NewClientAcceptor());

        ExecutorService messagesProcessing = Executors.newSingleThreadExecutor();
        messagesProcessing.execute(new MessagesProcessor());


    }
    public static void main(String[] args) {
        new Server().mainLoop();
    }

    private class NewClientAcceptor implements Runnable {
        @Override
        public void run() {
            while (true) {
                try (ServerSocket serverSocket = new ServerSocket(1234, 100)) {
                    serverSocket.setSoTimeout(300_000);

                    while (true) {
                        Socket clientSocket = serverSocket.accept();
                        clientsList.add(new SingleClient(clientSocket, listenersPool, messagesQueue));
                    }

                } catch (SocketTimeoutException e) {
                    System.out.println("Server closed due to timeout.");
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private class MessagesProcessor implements Runnable {

        @Override
        public void run() {
            while (true) {
                String message;
                try {
                    message = messagesQueue.take();
                    for (SingleClient client : clientsList) {
                        new Thread(() -> client.send(message)).start();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

