package com.db.javaschool2016.client;


import com.db.javaschool2016.message.Message;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Getter {
    private ObjectInputStream in;
    private Message message;

    public Getter(Socket socket) {
        try {
//            System.out.println(socket.getInputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            System.out.println("getter");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveInputMessage() {
        try {
            Object o = in.readObject();
            if (o instanceof Message) {
                message = (Message) o;
                System.out.println(message.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    public Message getMessage() {
//        return message;
//    }
}
