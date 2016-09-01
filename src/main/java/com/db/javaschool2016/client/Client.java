package com.db.javaschool2016.client;

public class Client {
    private Sender sender;
    private Getter getter;

    public Client(Sender sender, Getter getter) {
        this.sender = sender;
        this.getter = getter;
    }
}
