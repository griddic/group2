package com.db.javaschool2016.server;

import java.io.*;
import java.net.Socket;

/**
 * Instance responsible communication with a single client
 */
public class SingleClient {
    public Socket getSocket() {
        return socket;
    }

    public Server getServer() {
        return server;
    }

    private final Socket socket;
    private final Server server;


    public SingleClient(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void send(String message) {
        if (!socket.isConnected()) return;
        try {
            DataOutputStream out = new DataOutputStream(
                            socket.getOutputStream());
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

class MessageListener implements Runnable{
    private SingleClient client;

    public MessageListener(SingleClient client) {
        this.client = client;
    }
    @Override
    public void run() {
        try (DataInputStream inputStream =
                     new DataInputStream(
                             new BufferedInputStream(
                                     client.getSocket().getInputStream())))
        {

            while (!Thread.interrupted()) {
                if (!client.getSocket().isConnected()) return;
                String message = inputStream.readUTF();
                System.out.println(message);
                if (message.startsWith("/hist")) {
                    client.send(client.getServer().getMessagesProcessor().getHistory());
                    continue;
                }
                System.out.println(Formatter.format(message));
                this.client.getServer().getMessagesQueue().put(Formatter.format(message));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}