package com.example.fitnessandworkout;

import android.content.res.Resources;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.collection.SimpleArrayMap;
import androidx.collection.SparseArrayCompat;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.FragmentController;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelStore;

import com.example.fitnessandworkout.utils.DataBaseHandler;
import com.google.android.gms.common.api.GoogleApiClient;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class MainActivityTest {
    @Mock
    EditText userEmail;
    @Mock
    EditText password;
    //Field googleSignin of type SignInButton - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    Button signUp;
    @Mock
    Button signIn;
    @Mock
    TextView info;
    @Mock
    TextView title;
    @Mock
    DataBaseHandler DB;
    @Mock
    Animation animSI;
    @Mock
    Animation animSU;
    @Mock
    GoogleApiClient googleApiClient;
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
    MainActivity mainActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnCreate() throws Exception {
        when(DB.insertData(anyString(), anyString())).thenReturn(Boolean.TRUE);
        when(DB.checkEmail(anyString())).thenReturn(Boolean.TRUE);

        mainActivity.onCreate(null);
    }

    @Test
    public void testOnActivityResult() throws Exception {
        mainActivity.onActivityResult(0, 0, null);
    }

    @Test
    public void testOnConnectionFailed() throws Exception {
        mainActivity.onConnectionFailed(null);
    }
}

