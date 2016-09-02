package com.db.javaschool2016.client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ClientClassTest {
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
    public void shouldReturnTrueWhenCommandQuitSent() throws IOException {
        //region Given
        ConsoleInputParser consoleInputParser = mock(ConsoleInputParser.class);
        Socket socket = mock(Socket.class);
        Client client = new Client(socket, consoleInputParser);
        client.setQuitCommandAppear(true);
        //endregion

        //region When
        boolean result = client.isQuitCommandAppear();
        //endregion

        //region Then
        assertThat(result).isEqualTo(true);
        //endregion
    }

    @Test
    public void shouldReturnTrueWhenServerIsAvailable() throws IOException {
        //region Given
        ConsoleInputParser consoleInputParser = mock(ConsoleInputParser.class);
        Socket socket = mock(Socket.class);
        Client client = new Client(socket, consoleInputParser);
        //endregion

        //region When
        client.setServerAvailable(true);
        //endregion

        //region Then
        boolean result = client.isServerAvailable();
        assertThat(result).isEqualTo(true);
        //endregion
    }

    @Test
    public void shouldCallTheProcessMethodFromClient() {
        //region Given
        Client client = mock(Client.class);
        //endregion

        //region When
        client.process();
        //endregion

        //region Then
        verify(client).process();
        //endregion
    }
}
