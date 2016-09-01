package com.db.javaschool2016.client;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public void process () {
        ExecutorService consoleListenerAndSender = Executors.newSingleThreadExecutor();
        consoleListenerAndSender.execute(new ConsoleListenerAndSender());

        ExecutorService serverListenerAndConsoleWriter = Executors.newSingleThreadExecutor();
        serverListenerAndConsoleWriter.execute(new ServerListenerAndConsoleWriter());
    }

    private class ConsoleListenerAndSender implements Runnable {

        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while (true) {
                    String n = reader.readLine();
                    String message = consoleInputParser.parseString(n);
                    if(message != null) {
                        sender.sendMessage(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ServerListenerAndConsoleWriter implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println(getter.getInputMessage());
            }
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
