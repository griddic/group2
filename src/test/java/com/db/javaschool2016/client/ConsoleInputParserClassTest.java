package com.db.javaschool2016.client;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.fest.assertions.Assertions.*;

public class ConsoleInputParserClassTest {
    private final String WRONG_MESSAGE_ALLERT = "[WRONG COMMAND] Inapplicable command.";
    private ByteArrayOutputStream OUT = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        OUT.reset();
        System.setOut(new PrintStream(OUT));
    }

    @After
    public void resetOut() {
        OUT.reset();
    }
    @Test
    public void shouldPrintWrongCommandWhenCommandReceiveSend() {
        //region Given
        ConsoleInputParser consoleInputParser = new ConsoleInputParser();
        //endregion

        //region When
        consoleInputParser.parseString("/recv");
        //endregion

        //region Then
        assertThat(OUT.toString()).contains(WRONG_MESSAGE_ALLERT);
        //endregion
    }
}
