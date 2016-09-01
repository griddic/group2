package com.db.javaschool2016.message;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class MessageTest {
    @Test
    public void shouldCreateMessage () {
        Message m = new Message("abc");
        assertThat(m.getMessage()).isEqualTo("abc");
    }
}
