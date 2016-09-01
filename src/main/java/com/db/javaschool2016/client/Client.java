package com.db.javaschool2016.client;

import java.io.PrintWriter;
import java.util.Scanner;

public class Client {
    private Sender sender;
    private Getter getter;
    private ConsoleInputParser consoleInputParser;


    public Client(Sender sender, Getter getter, ConsoleInputParser consoleInputParser) {
        this.sender = sender;
        this.getter = getter;
        this.consoleInputParser = consoleInputParser;
    }

    private void process() {
        Scanner scanner = new Scanner(System.in);

//        System.in.read(input);
    }

    public static void main(String[] args) {
        ConsolePrinter printWriter = new ConsolePrinter();
        new Client(new Sender(), new Getter(printWriter), new ConsoleInputParser(printWriter)).process();
    }
}
