package com.db.javaschool2016.client;


import com.db.javaschool2016.message.Message;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Getter{
    private DataInputStream in;

    public Getter(Socket socket) throws IOException {
            this.in = new DataInputStream(
                    new BufferedInputStream(
                            socket.getInputStream()
                    )
            );
    }

    public String getInputMessage() throws IOException{
            return in.readUTF();
    }
}
