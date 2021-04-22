package com.example.fitnessandworkout;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Executor;

import static org.mockito.Mockito.*;

public class GetNearbyGymTest {
    //Field mMap of type GoogleMap - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    Executor SERIAL_EXECUTOR;
    @Mock
    Executor THREAD_POOL_EXECUTOR;
    @InjectMocks
    GetNearbyGym getNearbyGym;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoInBackground() throws Exception {
        String result = getNearbyGym.doInBackground("objects");
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    public void testOnPostExecute() throws Exception {
        getNearbyGym.onPostExecute("s");
    }
}

