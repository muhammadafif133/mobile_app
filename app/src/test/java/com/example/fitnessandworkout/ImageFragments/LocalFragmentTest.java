package com.example.fitnessandworkout.ImageFragments;

import android.database.Cursor;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentHostCallback;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessandworkout.utils.DataBaseHandler;
import com.example.fitnessandworkout.utils.LocalResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class LocalFragmentTest {
    @Mock
    RecyclerView recyclerView;
    @Mock
    DataBaseHandler myDatabase;
    //Field db of type SQLiteDatabase - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    ArrayList<LocalResponse> singleRowArrayList;
    @Mock
    LocalResponse singleRow;
    @Mock
    Cursor cursor;
    @Mock
    Object USE_DEFAULT_TRANSITION;
    //Field mSavedFragmentState of type Bundle - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    SparseArray<Parcelable> mSavedViewState;
    //Field mArguments of type Bundle - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    Fragment mTarget;
    //Field mFragmentManager of type FragmentManagerImpl - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    FragmentHostCallback mHost;
    //Field mChildFragmentManager of type FragmentManagerImpl - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    Fragment mParentFragment;
    @Mock
    ViewGroup mContainer;
    @Mock
    View mView;
    @Mock
    View mInnerView;
    @Mock
    Runnable mPostponedDurationRunnable;
    @Mock
    LayoutInflater mLayoutInflater;
    //Field mMaxState of type State - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    LifecycleRegistry mLifecycleRegistry;
    @Mock
    MutableLiveData<LifecycleOwner> mViewLifecycleOwnerLiveData;
    //Field mSavedStateRegistryController of type SavedStateRegistryController - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    LocalFragment localFragment;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnCreateView() throws Exception {
        View result = localFragment.onCreateView(null, null, null);
        Assert.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme