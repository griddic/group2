package com.db.javaschool2016.server;

import java.util.Date;

/**
 * Format origin message
 */
public class Formatter {
    /**
     * Method formats origin message to Date: message
     * @param message to format
     * @return formatted message
     */
    public static String format(String message) {
        return String.format("%s: %s", new Date(), message);
    }
}
