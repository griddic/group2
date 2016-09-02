package com.db.javaschool2016.client;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClientClassTest {



    @Test
    public void shouldReturnTrueWhenCommandQuitSent() throws IOException {
        //region Given
        ConsoleInputParser consoleInputParser = mock(ConsoleInputParser.class);
        Client client = new Client(consoleInputParser);
        //endregion

        //region When
        client.setQuitCommandAppear(true);
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
        Client client = new Client(consoleInputParser);
        //endregion

        //region When
        client.setServerAvailable(true);
        boolean result = client.isServerAvailable();
        //endregion

        //region Then
        assertThat(result).isEqualTo(true);
        //endregion
    }

    @Ignore
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
