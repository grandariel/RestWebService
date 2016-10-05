package com.restwebservice.model;

/**
 * Created by Daniel Jastrzębski on 03.09.2016.
 */
public class Message {

    private final String content;

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
