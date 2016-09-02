package com.db.javaschool2016.server;

import java.util.Date;

public class Formatter {
    public static String format(String message) {
        return String.format("%s: %s", new Date(), message);
    }
}
