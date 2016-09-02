package com.db.javaschool2016.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.*;
import org.apache.commons.io.IOUtils;

public class Server {
    private Collection<SingleClient> clientsList = Collections.synchronizedList(new ArrayList<SingleClient>());
    private ExecutorService listenersPool = Executors.newCachedThreadPool();

    /** thread-safe: http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/BlockingQueue.html */
    private BlockingQueue<String> messagesQueue = new LinkedBlockingQueue<>();

    public BlockingQueue<String> getMessagesQueue() {
        return messagesQueue;
    }

    public void addClient(SingleClient client) {
        this.clientsList.add(client);
        this.listenersPool.execute(new MessageListener(client));
    }

    public MessagesProcessor getMessagesProcessor() {
        return messagesProcessor;
    }

    private final MessagesProcessor messagesProcessor = new MessagesProcessor(messagesQueue, clientsList);

    private void mainLoop() {
        ExecutorService clientsAccepter = Executors.newSingleThreadExecutor();
        clientsAccepter.execute(new NewClientAcceptor(this));

        ExecutorService messagesProcessing = Executors.newSingleThreadExecutor();
        messagesProcessing.execute(this.messagesProcessor);
    }

    public static void main(String[] args) {
        new Server().mainLoop();
    }




}

class NewClientAcceptor implements Runnable {

    private Server server;

    public NewClientAcceptor(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(1234, 100)) {
                serverSocket.setSoTimeout(300_000);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client knocked: " + clientSocket);
                    server.addClient(new SingleClient(clientSocket, server));
                }

            } catch (SocketTimeoutException e) {
                System.out.println("Server closed due to timeout.");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

/**
 * !!!! Single thread function !!!!
 */
class MessagesProcessor implements Runnable {
    private String historyFile = "history.txt";
    private StringBuilder history = new StringBuilder("");
    private BlockingQueue<String> messagesQueue;
    private Collection<SingleClient> clientsList;

    public MessagesProcessor(BlockingQueue<String> messagesQueue, Collection<SingleClient> clientsList) {
        this.messagesQueue = messagesQueue;
        this.clientsList = clientsList;
        createHistoryFileIfNotExist();
        restoreHistFromFile();
    }

    private void createHistoryFileIfNotExist() {
        File yourFile = new File(this.historyFile);
        try {
            yourFile.createNewFile(); // if file already exists will do nothing
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            try (OutputStreamWriter historyStream = new OutputStreamWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(this.historyFile, true)
                    )
            )) {
                while (true) {
                    String message;
                    try {
                        message = messagesQueue.take();
                        new Thread(() -> {
                            try {
                                historyStream.write(message + System.lineSeparator());
                                historyStream.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).start();
                        history.append(message + System.lineSeparator());
                        for (SingleClient client : clientsList) {
                            new Thread(() -> client.send(message)).start();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getHistory() {
        return history.toString();
    }
    private void restoreHistFromFile() {
        try (FileInputStream fisTargetFile = new FileInputStream(new File(this.historyFile));) {
            String targetFileStr = IOUtils.toString(fisTargetFile, "UTF-8");
            this.history = new StringBuilder(targetFileStr);
        } catch (FileNotFoundException e) {
            System.out.println("History will not be overloaded, no history file.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("History will not be overloaded, history file seems to be broken.");
            e.printStackTrace();
        }
    }
}
