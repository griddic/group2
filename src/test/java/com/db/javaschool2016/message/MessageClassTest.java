package com.db.javaschool2016.message;

import org.junit.Test;

import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;

public class MessageClassTest {
    @Test
    public void shouldCreateMessage () {
        Message m = new Message("abc");
        assertThat(m.getMessage()).isEqualTo("abc");
    }

    @Test
    public void shouldSaveDateInMessage () {
        Message m = new Message("abc");
        m.setDateTime(new Date(2016,12,2));
        assertThat(m.getDateTime()).isEqualTo(new Date(2016,12,2));
    }
}
