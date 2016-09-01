package com.db.javaschool2016.client;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.ObjectOutputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SenderClassTest {

    @Test
    public void shouldSendTheMessageFromConsole() throws IOException {
        //region Given
        Sender sender = mock(Sender.class);
        //endregion

        //region When
        sender.sendMessage("test message");
        //endregion

        //region then
        verify(sender).sendMessage("test message");
        //endregion
    }

}

