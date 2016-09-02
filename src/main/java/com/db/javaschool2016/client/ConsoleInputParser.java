package com.db.javaschool2016.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser for input of user
 */
public class ConsoleInputParser {

    /**
     * Parse string with a command
     * @param inString String from command line.
     * @return processed string.
     */
    public String parseString(String inString) {
        Pattern p = Pattern.compile("^(/\\w+)(\\s[^/]*)?$");
        Matcher m = p.matcher(inString);
        String command;
        String message;
        if(m.matches()) {
            command = m.group(1);
            message = m.group(2);
            switch (command) {
                case "/snd":
                    if (message != null && message.length() > 1 && message.length() <= 151) {
                        return message.trim();
                    }
                    else {
                        System.out.println("[WRONG INPUT] message shouldn't be empty and more than 150 characters");
                    }
                    break;
                case "/hist":
                case "/quit":
                    return command;
                default:
                    System.out.println("[WRONG COMMAND] Inapplicable command.");
                    break;
            }
        }
        else {
            System.out.println("[WRONG INPUT] Your message shouldn't begin with slash." + System.lineSeparator() +
                            "[WRONG INPUT] Your message should be separated from command with space.");
        }
        return null;
    }
}
