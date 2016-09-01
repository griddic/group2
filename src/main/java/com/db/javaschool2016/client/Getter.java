package com.db.javaschool2016.client;


import com.db.javaschool2016.message.Message;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Getter {
    private ObjectInputStream in;
    private Message message;

    public Getter() {
        try {
            this.in = new ObjectInputStream(
                    new BufferedInputStream(
                            new Socket("localhost", 1234).getInputStream()
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMessage() {
        try {
            Object o = in.readObject();
            if (o instanceof Message) {
                message = (Message) o;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeMessage() {
        System.out.println(this.message.getMessage());
    }
}
