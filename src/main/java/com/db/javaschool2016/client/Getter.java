package com.db.javaschool2016.client;


import com.db.javaschool2016.message.Message;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Getter {
    private DataInputStream in;

    public Getter(Socket socket) {
        try {
//            System.out.println(socket.getInputStream());
            this.in = new DataInputStream(
                    new BufferedInputStream(
                            socket.getInputStream()
                    )
            );
            System.out.println("getter");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getInputMessage() {
        String message = "";
        try {
            message = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

//    public Message getMessage() {
//        return message;
//    }
}
