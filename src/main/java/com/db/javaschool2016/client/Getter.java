package com.db.javaschool2016.client;


import com.db.javaschool2016.message.Message;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Getter {
    private ObjectInputStream in;
    private Message message;
    private ConsolePrinter printer;

    public Getter(ConsolePrinter printer) {
        this.printer = printer;
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

    public void saveInputMessage() {
        try {
            Object o = in.readObject();
            if (o instanceof Message) {
                message = (Message) o;
                printer.print(message.toString());
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
