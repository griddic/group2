package com.db.javaschool2016.client;

import com.db.javaschool2016.server.Server;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.io.IOException;
import java.net.Socket;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientClassTest {

    @Ignore
    @Test
    public void shouldReturnTrueWhenCommandQuitSent() throws IOException {
        //region Given
        ConsoleInputParser consoleInputParser = mock(ConsoleInputParser.class);
        Socket socket = mock(Socket.class);
        Client client = new Client(socket, 1300);
        client.setQuitCommandAppear(true);
        //endregion

        //region When
        boolean result = client.isQuitCommandAppear();
        //endregion

        //region Then
        assertThat(result).isEqualTo(true);
        //endregion
    }

    @Ignore
    @Test
    public void shouldReturnTrueWhenServerIsAvailable() throws IOException {
        //region Given
        ConsoleInputParser consoleInputParser = mock(ConsoleInputParser.class);
        Socket socket = mock(Socket.class);
        Client client = new Client(socket, 1300);
        client.setServerAvailable(true);
        //endregion

        //region When
        boolean result = client.isServerAvailable();
        //endregion

        //region Then
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
