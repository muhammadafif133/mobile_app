package com.example.fitnessandworkout.utils;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ActivityAdapterTest {
    @Mock
    List<ActivityModel> activity;
    @Mock
    Context context;
    @Mock
    DataBaseHandler dataBaseHandler;
    @InjectMocks
    ActivityAdapter activityAdapter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnCreateViewHolder() throws Exception {
        ActivityAdapter.ViewHolder result = activityAdapter.onCreateViewHolder(null, 0);
        Assert.assertEquals(new ActivityAdapter(Arrays.<ActivityModel>asList(new ActivityModel(Integer.valueOf(0), "date", "level", "activity")), null).new ViewHolder(null), result);
    }

    @Test
    public void testOnBindViewHolder() throws Exception {
        activityAdapter.onBindViewHolder(new ActivityAdapter(Arrays.<ActivityModel>asList(new ActivityModel(Integer.valueOf(0), "date", "level", "activity")), null).new ViewHolder(null), 0);
    }

    @Test
    public void testGetItemCount() throws Exception {
        int result = activityAdapter.getItemCount();
        Assert.assertEquals(0, result);
    }
}

