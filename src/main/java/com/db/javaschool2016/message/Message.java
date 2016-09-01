package com.db.javaschool2016.message;

import java.io.Serializable;
import java.util.Date;

/**
 * Class sending through TCP/IP
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 1234567890098765L;
    private String message;
    private Date dateTime;

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

    /**
     * setter of dateTime of message
     * @param dateTime Date
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * getter of dateTime of message
     * @return Date of message
     */
    public Date getDateTime() {
        return dateTime;
    }
}
