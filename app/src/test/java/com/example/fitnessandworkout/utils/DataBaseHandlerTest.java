package com.example.fitnessandworkout.utils;

import android.content.Context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class DataBaseHandlerTest {
    @Mock
    Context context;
    @InjectMocks
    DataBaseHandler dataBaseHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnCreate() throws Exception {
        dataBaseHandler.onCreate(null);
    }

    @Test
    public void testOnUpgrade() throws Exception {
        dataBaseHandler.onUpgrade(null, 0, 0);
    }

    @Test
    public void testDeleteEntry() throws Exception {
        dataBaseHandler.deleteEntry(0L);
    }

    @Test
    public void testInsertData() throws Exception {
        Boolean result = dataBaseHandler.insertData("email", "password");
        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void testCheckEmail() throws Exception {
        Boolean result = dataBaseHandler.checkEmail("email");
        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void testCheckEmailPassword() throws Exception {
        Boolean result = dataBaseHandler.checkEmailPassword("email", "password");
        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void testAddActivity() throws Exception {
        dataBaseHandler.addActivity(new ActivityModel(null, "date", "level", "activity"));
    }

    @Test
    public void testGetActivityList() throws Exception {
        List<ActivityModel> result = dataBaseHandler.getActivityList();
        Assert.assertEquals(Arrays.<ActivityModel>asList(new ActivityModel(Integer.valueOf(0), "date", "level", "activity")), result);
    }

    @Test
    public void testUpdateActivity() throws Exception {
        dataBaseHandler.updateActivity(new ActivityModel(Integer.valueOf(0), "date", "level", "activity"));
    }

    @Test
    public void testDeleteActivity() throws Exception {
        dataBaseHandler.deleteActivity(0);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme