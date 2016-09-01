package com.db.javaschool2016.client;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Getter {
    private DataInputStream in;

    public Getter(Socket socket) {
        try {
            this.in = new DataInputStream(
                    new BufferedInputStream(
                            socket.getInputStream()
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Receive message from socket's InputStream.
     * @return received message.
     */
    public String getInputMessage() {
        String message = "";
        try {
            message = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}
