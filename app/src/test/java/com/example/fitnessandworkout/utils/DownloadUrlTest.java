package com.example.fitnessandworkout.utils;

import org.junit.Assert;
import org.junit.Test;

public class DownloadUrlTest {
    DownloadUrl downloadUrl = new DownloadUrl();

    @Test
    public void testReadTheURL() throws Exception {
        String result = downloadUrl.ReadTheURL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=52.402565,-1.4299317&radius=100000&type=gym&sensor=true&key=AIzaSyBtzVTP4QSPauHYMsQ5KJ1olyPQC8RKs1Q");
        Assert.assertEquals("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=52.402565,-1.4299317&radius=100000&type=gym&sensor=true&key=AIzaSyBtzVTP4QSPauHYMsQ5KJ1olyPQC8RKs1Q", result);
    }
}

