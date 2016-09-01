package com.db.javaschool2016.server;

import com.db.javaschool2016.message.Message;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private Collection<SingleClient> clientsList = new CopyOnWriteArrayList<SingleClient>();
    private ExecutorService listenersPool = Executors.newCachedThreadPool();
    private BlockingQueue<Message> messagesQueue;

    private void mainLoop() {
        ExecutorService clientsAccepter = Executors.newSingleThreadExecutor();
        clientsAccepter.execute(new NewClientAcceptor());

        ExecutorService messagesProcessing = Executors.newSingleThreadExecutor();


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
                if (messagesQueue.isEmpty()) continue;
                Message message = messagesQueue.remove();
                for (SingleClient client: clientsList) {
                    client.send(message);
                }

            }
        }
    }
}
