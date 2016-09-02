package com.db.javaschool2016.client;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.mock;

public class ConsoleInputParserClassTest {
    private final String WRONG_COMMAND_ALERT = "[WRONG COMMAND] Inapplicable command.";
    private final String WRONG_INPUT_ALERT = "[WRONG INPUT] Your message shouldn't begin with slash." + System.lineSeparator() +
            "[WRONG INPUT] Your message should be separated from command with space.";
    private final String WRONG_MESSAGE_ALERT = "[WRONG INPUT] message shouldn't be empty and more than 150 characters";

    private ByteArrayOutputStream OUT = new ByteArrayOutputStream();

    private ConsoleInputParser consoleInputParser;

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
    public void shouldPrintWrongCommandWhenCommandReceiveSent() {
        //region Given
        consoleInputParser = new ConsoleInputParser();
        //endregion

        //region When
        consoleInputParser.parseString("/recv");
        //endregion

        //region Then
        assertThat(OUT.toString()).contains(WRONG_COMMAND_ALERT);
        //endregion
    }

    @Test
    public void shouldPrintWrongInputWhenAnyStringSent() {
        //region Given
        ConsoleInputParser consoleInputParser = new ConsoleInputParser();
        //endregion

        //region When
        consoleInputParser.parseString("Something");
        //endregion

        //region Then
        assertThat(OUT.toString()).contains(WRONG_INPUT_ALERT);
        //endregion
    }

    @Test
    public void shouldReturnMessageWhenMessageWithCommandSent() {
        //region Given
        consoleInputParser = new ConsoleInputParser();
        //endregion

        //region When
        String parsedString = consoleInputParser.parseString("/snd Hello!");
        //endregion

        //region Then
        assertThat(parsedString).contains("Hello!");
        //endregion
    }

    @Test
    public void shoultPrintWrongMessageAlertWhenEmptyMessageSent() {
        //region Given
        consoleInputParser = new ConsoleInputParser();
        //endregion

        //region When
        String alert = consoleInputParser.parseString("/snd");
        //endregion

        //region Then
        assertThat(OUT.toString()).contains(WRONG_MESSAGE_ALERT);
        //endregion
    }

}
