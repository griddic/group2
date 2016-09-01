package com.db.javaschool2016.server;

import com.db.javaschool2016.message.Message;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Java_5 on 01.09.2016.
 */
public class SingleClient {
    private final Socket socket;
    private final ExecutorService listenersPool;


    public SingleClient(Socket socket, ExecutorService listenersPool, LinkedBlockingQueue<String> queue) {
        this.socket = socket;
        this.listenersPool = listenersPool;
        listenersPool.execute(new MessageListener(queue));
    }

    public void send(String message) {
        if (!socket.isConnected()) return;
        try {
            DataOutputStream out = new DataOutputStream(
                            socket.getOutputStream());
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MessageListener implements Runnable{
        private LinkedBlockingQueue<String> queue;

        public MessageListener(LinkedBlockingQueue<String> queue) {
            this.queue = queue;
        }
        @Override
        public void run() {
            //DataInputStream inputStream;
            try (DataInputStream inputStream =
                        new DataInputStream(
                        new BufferedInputStream(
                        socket.getInputStream())))
            {

                while (!Thread.interrupted()) {
                    if (!socket.isConnected()) return;
                    String message = inputStream.readUTF();
                    queue.put(Formatter.format(message));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
