package com.db.javaschool2016.client;

import com.db.javaschool2016.message.Message;

import java.io.*;
import java.net.Socket;

public class Sender {
    private ObjectOutputStream out;

    public Sender() {
        try {
            this.out = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new Socket("localhost", 1234).getOutputStream()
                    )
            );
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
