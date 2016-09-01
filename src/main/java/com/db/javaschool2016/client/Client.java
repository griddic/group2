package com.db.javaschool2016.client;

import com.db.javaschool2016.message.Message;

import java.util.Scanner;

public class Client {
    private Sender sender;
    private Getter getter;
    private ConsoleInputParser consoleInputParser;
    private ConsolePrinter printer;


    public Client(Sender sender, Getter getter, ConsoleInputParser consoleInputParser, ConsolePrinter printer) {
        this.sender = sender;
        this.getter = getter;
        this.consoleInputParser = consoleInputParser;
        this.printer = printer;
    }

    private void process() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                Message message = consoleInputParser.parseString(scanner.nextLine());
                if(message != null) {

                }
            }
        } catch (ExitClientException e) {
            printer.print("Exiting....");
        }
    }

    public static void main(String[] args) {
        ConsolePrinter printWriter = new ConsolePrinter();
        new Client(new Sender(), new Getter(printWriter), new ConsoleInputParser(printWriter), printWriter).process();
    }
}
