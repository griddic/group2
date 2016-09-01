package com.db.javaschool2016.client;

import com.db.javaschool2016.message.Message;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private Sender sender;
    private Getter getter;
    private ConsoleInputParser consoleInputParser;


    public Client(ConsoleInputParser consoleInputParser) throws IOException {
        socket = new Socket("localhost", 1234);
        this.getter = new Getter(socket);
        this.sender = new Sender(socket);
        this.consoleInputParser = consoleInputParser;

    }

    public void process() {
        DataInputStream reader = new DataInputStream(new BufferedInputStream(System.in));
        try {
            while (true) {
                String n = reader.readUTF();
                String message = consoleInputParser.parseString(n);
                if(message != null) {

                }
            }
        } catch (ExitClientException e) {
            System.out.println("Exiting....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        Client s = null;
        try {
            s = new Client(new ConsoleInputParser());
        } catch (IOException e) {
            e.printStackTrace();
        }
        s.process();
    }
}
