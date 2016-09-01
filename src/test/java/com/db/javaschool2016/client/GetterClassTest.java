package com.db.javaschool2016.client;

import com.db.javaschool2016.message.Message;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetterClassTest {

    @Mock
    Message message = mock(Message.class);

    @Mock
    DataInputStream in  = mock(DataInputStream.class);

    @Mock
    Socket socket = mock(Socket.class);


    @Test
    public void shouldGetTheMessageFromConsole() {
        //region Given
        Getter getter = mock(Getter.class);
        //endregion

        //region When
        getter.getInputMessage();
        //endregion

        //region Then
        verify(getter).getInputMessage();
        //endregion
    }
}
