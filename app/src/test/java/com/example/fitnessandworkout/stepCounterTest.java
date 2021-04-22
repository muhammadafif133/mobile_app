package com.example.fitnessandworkout;

import android.content.res.Resources;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.collection.SimpleArrayMap;
import androidx.collection.SparseArrayCompat;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.FragmentController;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelStore;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class stepCounterTest {
    @Mock
    TextView tvStepDetector;
    @Mock
    AppCompatDelegate mDelegate;
    @Mock
    Resources mResources;
    @Mock
    FragmentController mFragments;
    @Mock
    LifecycleRegistry mFragmentLifecycleRegistry;
    @Mock
    SparseArrayCompat<String> mPendingFragmentActivityResults;
    @Mock
    LifecycleRegistry mLifecycleRegistry;
    //Field mSavedStateRegistryController of type SavedStateRegistryController - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    ViewModelStore mViewModelStore;
    //Field mOnBackPressedDispatcher of type OnBackPressedDispatcher - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    SimpleArrayMap<Class<? extends ComponentActivity.ExtraData>, ComponentActivity.ExtraData> mExtraDataMap;

    @InjectMocks
    com.example.fitnessandworkout.stepCounter stepCounter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnCreate() throws Exception {
        stepCounter.onCreate(null);

    }

    @Test
    public void testOnPause() throws Exception {
        stepCounter.onPause();
    }

    @Test
    public void testOnStop() throws Exception {
        stepCounter.onStop();
    }

    @Test
    public void testOnResume() throws Exception {
        stepCounter.onResume();

    }
}

