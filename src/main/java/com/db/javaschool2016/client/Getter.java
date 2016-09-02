package com.db.javaschool2016.client;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Getter{

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
    public String getInputMessage() throws IOException{
            return in.readUTF();
    }

    public void close() throws IOException {
        in.close();
    }
}
