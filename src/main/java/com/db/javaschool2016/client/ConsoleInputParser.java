package com.db.javaschool2016.client;

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
    public ConsoleInputParser() {
        System.out.println("parser");
    }

    public Message parseString(String inString) throws ExitClientException {
        Pattern p = Pattern.compile("^/(\\w+)(.*)$");
        Matcher m = p.matcher(inString);
        if(m.matches()) {
            switch (m.group(1)) {
                case "snd":
                    return new Message(m.group(2));
                case "exit":
                    throw new ExitClientException();
                default:
                    System.out.println("[WRONG COMMAND] Inapplicable command.");
            }
        }
        else {
            System.out.println("[WRONG INPUT] Your command contains a mistake." + System.lineSeparator() +
                            "[WRONG INPUT] Your message should be separated from command with space.");
        }
        return null;
    }

    public static void main(String[] args) {
        ConsoleInputParser c = new ConsoleInputParser();
        try {
            Message m = c.parseString("/snd 1");
        } catch (ExitClientException e) {
            e.printStackTrace();
        }
    }
}
