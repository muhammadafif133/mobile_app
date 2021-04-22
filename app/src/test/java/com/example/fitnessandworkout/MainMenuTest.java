package com.example.fitnessandworkout;

import android.content.res.Resources;
import android.widget.Button;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.collection.SimpleArrayMap;
import androidx.collection.SparseArrayCompat;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.FragmentController;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelStore;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class MainMenuTest {

    @Mock
    GoogleApiClient googleApiClient;
    @Mock
    GoogleSignInOptions gso;
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
    MainMenu mainMenu;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnCreate() throws Exception {
        mainMenu.onCreate(null);
    }

    @Test
    public void testOnStart() throws Exception {
        mainMenu.onStart();
    }

    @Test
    public void testOnConnectionFailed() throws Exception {
        mainMenu.onConnectionFailed(null);
    }
}

