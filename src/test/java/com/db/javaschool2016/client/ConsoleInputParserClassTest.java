package com.db.javaschool2016.client;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.fest.assertions.Assertions.*;

public class ConsoleInputParserClassTest {
    private final String WRONG_COMMAND_ALLERT = "[WRONG COMMAND] Inapplicable command.";
    private final String WRONG_STRING_ALLERT = "[WRONG INPUT] Your command contains a mistake." + System.lineSeparator() +
            "[WRONG INPUT] Your message should be separated from command with space.";
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
        assertThat(OUT.toString()).contains(WRONG_COMMAND_ALLERT);
        //endregion
    }

    @Test
    public void shouldPrintWrongInputWhenAnyStringSend() {
        //region Given
        ConsoleInputParser consoleInputParser = new ConsoleInputParser();
        //endregion

        //region When
        consoleInputParser.parseString("Something");
        //endregion

        //region Then
        assertThat(OUT.toString()).contains(WRONG_STRING_ALLERT);
        //endregion
    }
}
