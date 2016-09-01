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
     * @return proccessed string.
     */
    public String parseString(String inString) {
        Pattern p = Pattern.compile("^/(\\w+)(.*)$");
        Matcher m = p.matcher(inString);
        if(m.matches()) {
            switch (m.group(1)) {
                case "snd":
                    return m.group(2);
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
}
