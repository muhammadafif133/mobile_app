package com.example.fitnessandworkout.utils;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class LocalDataBaseAdapterTest {
    @Mock
    Context context;
    @Mock
    ArrayList<LocalResponse> singleRowArrayList;
    //Field db of type SQLiteDatabase - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    DataBaseHandler myDatabase;
    @InjectMocks
    LocalDataBaseAdapter localDataBaseAdapter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnCreateViewHolder() throws Exception {
        LocalDataBaseAdapter.MyViewHolder result = localDataBaseAdapter.onCreateViewHolder(null, 0);
        Assert.assertEquals(new LocalDataBaseAdapter(null, new ArrayList<LocalResponse>(Arrays.asList(new LocalResponse("image", 0))), null, new DataBaseHandler(null)).new MyViewHolder(null), result);
    }

    @Test
    public void testOnBindViewHolder() throws Exception {
        localDataBaseAdapter.onBindViewHolder(new LocalDataBaseAdapter(null, new ArrayList<LocalResponse>(Arrays.asList(new LocalResponse("image", 0))), null, new DataBaseHandler(null)).new MyViewHolder(null), 0);
    }

    @Test
    public void testGetItemCount() throws Exception {
        int result = localDataBaseAdapter.getItemCount();
        Assert.assertEquals(0, result);
    }

    @Test
    public void testDeleteData() throws Exception {
        localDataBaseAdapter.deleteData(0, new ArrayList<LocalResponse>(Arrays.asList(new LocalResponse(null, 0))));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme