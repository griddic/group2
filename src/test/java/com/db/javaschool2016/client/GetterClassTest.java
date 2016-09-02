package com.db.javaschool2016.client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.DataInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Getter.class)
public class GetterClassTest {

    private DataInputStream in;
    private Getter getter;

    @Before
    public void setUp() throws IOException {
        in = PowerMockito.mock(DataInputStream.class);
        getter = new Getter(in);
    }

    @After
    public void reset() {
    }

    @Test
    public void shouldGetMessageFromInputStream() throws IOException {
        //region When
        PowerMockito.when(in.readUTF()).thenReturn("test message");
        //endregion

        //region Then
        assertEquals("test message", getter.getInputMessage());
        //endregion
    }
}
