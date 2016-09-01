package com.db.javaschool2016.message;

/**
 * Class sending through TCP/IP
 */
public class Message {
    private String message;

    /**
     * Constructor for sending Message
     * @param message String message from user
     */
    public Message(String message) {
        this.message = message;
    }

    /**
     * Getter for message string
     * @return message String
     */
    public String getMessage() {
        return message;
    }
}
