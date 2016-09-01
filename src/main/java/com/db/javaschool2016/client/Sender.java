package com.db.javaschool2016.client;

import java.io.*;
import java.net.Socket;

public class Sender{
    private DataOutputStream out;

    public Sender(Socket socket) throws IOException{
            this.out = new DataOutputStream(
                            socket.getOutputStream()
            );
    }

    public void sendMessage(String message) throws IOException{
            out.writeUTF(message);
            out.flush();
    }

    public void close() throws IOException {
        out.close();
    }
}
