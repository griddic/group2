package com.db.javaschool2016.client;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClientClassTest {

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
