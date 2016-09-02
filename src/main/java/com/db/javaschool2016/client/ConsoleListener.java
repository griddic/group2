package com.db.javaschool2016.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Objects;

public class ConsoleListener {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", Integer.parseInt(args[0]));
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ConsoleInputParser consoleInputParser = new ConsoleInputParser();
        String message;
        while (true) {
            if (reader.ready()) {
                message = consoleInputParser.parseString(reader.readLine());
                dataOutputStream.writeUTF(message);
                if (Objects.equals(message, "/quit")) {
                    System.exit(0);
                }
            }
        }
    }
}
