package com.db.javaschool2016.client;

import com.db.javaschool2016.message.ConsoleCommands;
import com.db.javaschool2016.message.Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ExitClientException extends Exception {
    public ExitClientException() {
        super();
    }
}

/**
 * Parser for input of user
 */
public class ConsoleInputParser {
    private ConsolePrinter printer;

    public ConsoleInputParser(ConsolePrinter printer) {
        this.printer = printer;
    }

    public Message parseString(String inString) throws ExitClientException {
        ConsoleCommands cmd;

        Pattern p = Pattern.compile("^/(\\w+)\\s(.*)$");
        Matcher m = p.matcher(inString);
        if(m.matches()) {
            switch (m.group(1)) {
                case "/snd":
                    cmd = ConsoleCommands.SEND;
                    return new Message(m.group(2));
                case "/exit":
                    throw new ExitClientException();
                case "/rcv":
                    cmd = ConsoleCommands.RECEIVE;
                    break;
                default:
                    printer.print("[WRONG COMMAND] Inapplicable command.");
            }
        }
        else {
            printer.print("[WRONG INPUT] Your command contains a mistake." + System.lineSeparator() +
                            "[WRONG INPUT] Your message should be separated from command with space.");
        }
        return null;
    }

    public static void main(String[] args) {
        ConsoleInputParser c = new ConsoleInputParser(new ConsolePrinter());
        try {
            Message m = c.parseString("/exit");
        } catch (ExitClientException e) {
            e.printStackTrace();
        }
    }
}
