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
    private ExecutorService consoleListenerAndSender = Executors.newSingleThreadExecutor();
    private ExecutorService serverListenerAndConsoleWriter = Executors.newSingleThreadExecutor();
    private boolean status;


    public Client(ConsoleInputParser consoleInputParser) throws IOException {
        socket = new Socket("localhost", 1234);
        this.getter = new Getter(socket);
        this.sender = new Sender(socket);
        this.consoleInputParser = consoleInputParser;
        this.status = false;
    }

    public void process () {
        consoleListenerAndSender.execute(new ConsoleListenerAndSender());
        serverListenerAndConsoleWriter.execute(new ServerListenerAndConsoleWriter());
    }

    public void close()  {
        this.consoleListenerAndSender.shutdownNow();
        this.serverListenerAndConsoleWriter.shutdownNow();
        try {
            this.getter.close();
            this.sender.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.status = true;
    }

    public boolean getStatus() {
        return status;
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
                System.out.println("server is down");
                close();
            }
        }
    }

    private class ServerListenerAndConsoleWriter implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(getter.getInputMessage());
                }
            } catch (IOException e) {
                System.out.println("server is down");
                close();
            }
        }
    }

    public static void main(String[] args) {

        Client client = null;
        try {
            client = new Client(new ConsoleInputParser());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        client.process();

        while (true) {
            if (client.getStatus()) {
                System.exit(0);
            }
        }
    }
}
