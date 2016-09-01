package com.db.javaschool2016.client;

import java.io.*;
import java.net.Socket;

public class Sender{
    private DataOutputStream out;

    public Sender(Socket socket) {
        try {
            this.out = new DataOutputStream(
                            socket.getOutputStream()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send message to socket's OutputStream.
     * @param message Message to send.
     */

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
