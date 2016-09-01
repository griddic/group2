package com.db.javaschool2016.server;

import com.db.javaschool2016.message.Message;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

/**
 * Created by Java_5 on 01.09.2016.
 */
public class SingleClient {
    private final Socket socket;
    private final ExecutorService listenersPool;


    public SingleClient(Socket socket, ExecutorService listenersPool, BlockingQueue<Message> queue) {
        this.socket = socket;
        this.listenersPool = listenersPool;
        listenersPool.execute(new MessageListener(queue));
    }

    public void send(Message message) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new BufferedOutputStream(
                            socket.getOutputStream()));
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MessageListener implements Runnable{
        private BlockingQueue<Message> queue;

        public MessageListener(BlockingQueue<Message> queue) {
            this.queue = queue;
        }
        @Override
        public void run() {
            ObjectInputStream inputStream;
            try {
                inputStream = new ObjectInputStream(
                        new BufferedInputStream(
                                socket.getInputStream()
                        )
                );
                while (true) {

                    Object o = inputStream.readObject();
                    if (o instanceof Message) {
                        Message m = (Message)o;
                        queue.add(m);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
