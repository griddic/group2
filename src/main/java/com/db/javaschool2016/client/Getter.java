package com.db.javaschool2016.client;

import java.io.DataInputStream;
import java.io.IOException;

public class Getter{
    private DataInputStream in;

    public Getter(DataInputStream dataInputStream) throws IOException {
            this.in = dataInputStream;
    }

    /**
     * Receive message from socket's InputStream.
     * @return received message.
     */
    public String getInputMessage() throws IOException{
            return in.readUTF();
    }

    public void close() throws IOException {
        in.close();
    }
}
