package com.acme.edu;

import java.io.*;
import java.net.Socket;

/**
 * Created by Java_5 on 31.08.2016.
 */
public class ClientExample{
    private final String host;
    private final int port;
    DataOutputStream out;
    DataInputStream in;

    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 1234;
        ClientExample instance = new ClientExample("localhost", 1234);
        Socket socket = new Socket(host,  port);
        instance.out = new DataOutputStream(socket.getOutputStream());
        instance.in = new DataInputStream(
                        new BufferedInputStream(
                                socket.getInputStream()));
        instance.write("hello.");
        while (true) {
            try {
                Thread.sleep(2_000);
                System.out.println("GET: " + instance.in.readUTF());
            }
            catch (Exception e) {
                System.out.print('.');
            }
        }

    }

    public ClientExample(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void write(String message) {
        try {
            System.out.println(";;;;; " + message);
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}