package com.db.javaschool2016.client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetterClassTest {

    private ByteArrayOutputStream OUT = new ByteArrayOutputStream();

    @Before
    public void setUpOut() {
        OUT.reset();
        System.setOut(new PrintStream(OUT));
    }

    @After
    public void resetOut() {
        OUT.reset();
    }

    @Test
    public void shouldGetMessageFromServer() throws IOException {
        //region Given
        Getter getter = mock(Getter.class);
        //endregion

        //region When
        when(getter.getInputMessage()).thenReturn("test message");
        String expected = getter.getInputMessage();
        //endregion

        //region Then
        assertThat(expected).contains("test message");
        //endregion
    }


}
