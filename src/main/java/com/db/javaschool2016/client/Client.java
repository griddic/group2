package com.db.javaschool2016.client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private ServerSocket serverSocket;
    private Socket socket;
    private Socket console;
    private Sender sender;
    private Getter getter;
    private ExecutorService consoleListenerAndSender = Executors.newSingleThreadExecutor();
    private ExecutorService serverListenerAndConsoleWriter = Executors.newSingleThreadExecutor();
    private volatile boolean isServerAvailable;
    private volatile boolean isQuitCommandAppear;

    /**
     * Create client: try to open socket and create getter and setter with input and output stream l
     * @param socket
     * @throws IOException
     */
    public Client(Socket socket, int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.socket = socket;
        this.getter = new Getter(
                new DataInputStream(
                        new BufferedInputStream(
                                socket.getInputStream()
                        )
                )
        );
        this.sender = new Sender(
                new DataOutputStream(
                        socket.getOutputStream()
                )
        );
        this.isServerAvailable = false;
        this.isQuitCommandAppear = false;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket getConsole() {
        return console;
    }

    public void setConsole(Socket console) {
        this.console = console;
    }

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

    /**
     * Creates 2 threads:
     * first thread listen console and send to server saved message,
     * second thread listen socket and write in console input message.
     */
    public void process () {
        consoleListenerAndSender.execute(() -> {
            String message = null;
            DataInputStream reader = null;
            try {
                reader = new DataInputStream(new BufferedInputStream(console.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                while (!isServerAvailable()) {
                    message = reader.readUTF();

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

    /**
     * Try to close all resources.
     */
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
        System.out.println("[Chat Window]");

        Client client = null;
        try {
            client = new Client(new Socket("localhost", 1234), Integer.parseInt(args[0]));
        } catch (IOException e) {
            System.out.println("[SERVER ISSUE] server isn't reachable.");
            //System.exit(0);
        }

        try {
                client.setConsole(client.getServerSocket().accept());
        } catch (IOException e) {
            e.printStackTrace();
        }

        client.process();

    }
}
