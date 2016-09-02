package com.db.javaschool2016.client;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private Socket socket;
    private Sender sender;
    private Getter getter;
    private ConsoleInputParser consoleInputParser;
    private ExecutorService consoleListenerAndSender = Executors.newSingleThreadExecutor();
    private ExecutorService serverListenerAndConsoleWriter = Executors.newSingleThreadExecutor();
    private volatile boolean isServerAvailable;
    private volatile boolean isQuitCommandAppear;

    public boolean isQuitCommandAppear() {
        return isQuitCommandAppear;
    }

    public void setQuitCommandAppear(boolean quitCommandAppear) {
        isQuitCommandAppear = quitCommandAppear;
    }

    public boolean isServerAvailable() {
        return isServerAvailable;
    }

    public void setServerAvailable(boolean serverAvailable) {
        isServerAvailable = serverAvailable;
    }


    public Client(Socket socket, ConsoleInputParser consoleInputParser) throws IOException {
        this.socket = socket;
        this.getter = new Getter(socket);
        this.sender = new Sender(socket);
        this.consoleInputParser = consoleInputParser;
        this.isServerAvailable = false;
        this.isQuitCommandAppear = false;
    }

    public void process () {
        consoleListenerAndSender.execute(() -> {
            String message = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while (!isServerAvailable()) {
                    if (reader.ready()) {
                        message = consoleInputParser.parseString(
                                reader.readLine()
                        );
                    }

                    if(message == null) {
                        continue;
                    }

                    if (Objects.equals(message, "/quit")){
                        close();
                        setQuitCommandAppear(true);
                        break;
                    }

                    sender.sendMessage(message);
                    message = null;

                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        serverListenerAndConsoleWriter.execute(() -> {
            try {
                while (!isServerAvailable()) {
                    System.out.println(getter.getInputMessage());
                }
            } catch (IOException e) {
                if (!isQuitCommandAppear()) {
                    System.out.println("[SERVER ISSUE] server is down.");
                    close();
                    setServerAvailable(true);
                }
                else {
                    System.out.println("Quit...");
                }
            }
        });
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
    }

    public static void main(String[] args) {

        Client client = null;
        try {
            client = new Client(new Socket("localhost", 1234),new ConsoleInputParser());
        } catch (IOException e) {
            System.out.println("[SERVER ISSUE] server isn't reachable.");
            System.exit(0);
        }

        client.process();

    }
}
