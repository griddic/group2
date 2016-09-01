package com.db.javaschool2016.client;

import java.io.*;
import java.net.Socket;

public class Sender implements Runnable{
    private DataOutputStream out;

    public Sender(Socket socket) {
        try {
            this.out = new DataOutputStream(
                    new BufferedOutputStream(
                            socket.getOutputStream()
                    )
            );
            System.out.println("sender");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
}
