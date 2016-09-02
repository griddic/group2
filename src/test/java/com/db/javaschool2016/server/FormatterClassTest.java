package com.db.javaschool2016.server;

import org.junit.Test;

import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;

public class FormatterClassTest {

    @Test
    public void shouldReturnFormatedStringWhenSomeStringFormat() {
        //region Given
        String message = "some";
        //endregion

        //region When
        String result = Formatter.format(message);
        Date date = new Date();
        //endregion

        //region Then
        assertThat(result).contains(date.toString());
        assertThat(result).contains(message);
        //endregion
    }
}
