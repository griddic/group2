package com.db.javaschool2016.server;

import java.util.Date;

/**
 * Created by Java_5 on 01.09.2016.
 */
public class Formatter {
    public static String format(String message) {
        return String.format("%s: %s", new Date(), message);
    }
}
