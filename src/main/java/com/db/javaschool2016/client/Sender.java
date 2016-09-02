package com.db.javaschool2016.client;

import java.io.*;

public class Sender{
    private DataOutputStream out;

    public Sender(DataOutputStream dataOutputStream) throws IOException{
            this.out = dataOutputStream;
    }

    /**
     * Send message to socket's OutputStream.
     * @param message Message to send.
     */

    public void sendMessage(String message) throws IOException{
            out.writeUTF(message);
            out.flush();
    }

    /**
     * Try to close resource.
     * @throws IOException
     */
    public void close() throws IOException {
        out.close();
    }
}
