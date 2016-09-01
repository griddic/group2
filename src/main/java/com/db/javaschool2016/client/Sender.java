package com.db.javaschool2016.client;

import com.db.javaschool2016.message.Message;

import java.io.*;
import java.net.Socket;

public class Sender {
    private ObjectOutputStream out;

    public Sender(Socket socket) {
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("sender");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message) {
        try {
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
