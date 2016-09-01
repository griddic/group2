package com.db.javaschool2016.client;

import com.db.javaschool2016.message.Message;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private Sender sender;
    private Getter getter;
    private ConsoleInputParser consoleInputParser;
    private ConsolePrinter printer;


    public Client(ConsoleInputParser consoleInputParser, ConsolePrinter printer) throws IOException {
        socket = new Socket("localhost", 1234);
        this.printer = printer;
        this.sender = new Sender(socket);
        this.getter = new Getter(printer, socket);
        this.consoleInputParser = consoleInputParser;

    }

    public void process() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String n = reader.readLine();
                System.out.println(reader);
                Message message = consoleInputParser.parseString(n);
                if(message != null) {

                }
            }
        } catch (ExitClientException e) {
            printer.print("Exiting....");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        ConsolePrinter printWriter = new ConsolePrinter();
        Client s = null;
        try {
            s = new Client(new ConsoleInputParser(printWriter), printWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("123");
        s.process();
    }
}
