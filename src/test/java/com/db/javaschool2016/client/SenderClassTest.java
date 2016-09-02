package com.db.javaschool2016.client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.DataOutputStream;
import java.io.IOException;

import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Sender.class)
public class SenderClassTest {

    private DataOutputStream out;
    private Sender sender;

    @Before
    public void setUp() throws IOException {
        out = PowerMockito.mock(DataOutputStream.class);
        sender = new Sender(out);
    }

    @After
    public void reset() {
    }

    @Test
    public void shouldSendTheMessageFromConsole() throws IOException {
        //region When
        sender.sendMessage("test");
        //endregion

        //region then
        verify(out).writeUTF("test");
        verify(out).flush();
        //endregion
    }

}

