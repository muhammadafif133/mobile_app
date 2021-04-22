package com.example.fitnessandworkout;

import android.location.Location;

import androidx.collection.SimpleArrayMap;
import androidx.collection.SparseArrayCompat;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.FragmentController;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelStore;

import com.google.android.gms.common.api.GoogleApiClient;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class GoogleMapsActivityTest {
    //Field mMap of type GoogleMap - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    GoogleApiClient googleApiClient;
    //Field locationRequest of type LocationRequest - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    Location lastLocation;
    //Field currentUserLocationMarker of type Marker - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
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
    GoogleMapsActivity googleMapsActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnCreate() throws Exception {
        googleMapsActivity.onCreate(null);
    }

    @Test
    public void testOnClick() throws Exception {
        googleMapsActivity.onClick(null);
    }

    @Test
    public void testOnMapReady() throws Exception {
        googleMapsActivity.onMapReady(null);
    }

    @Test
    public void testCheckUserLocationPermission() throws Exception {
        boolean result = googleMapsActivity.checkUserLocationPermission();

        Assert.assertEquals(true, result);
    }

    @Test
    public void testOnRequestPermissionsResult() throws Exception {
        googleMapsActivity.onRequestPermissionsResult(0, new String[]{"permissions"}, new int[]{0});
    }

    @Test
    public void testBuildGoogleApiClient() throws Exception {
        googleMapsActivity.buildGoogleApiClient();
    }

    @Test
    public void testOnLocationChanged() throws Exception {
        googleMapsActivity.onLocationChanged(null);
    }

    @Test
    public void testOnConnected() throws Exception {
        googleMapsActivity.onConnected(null);
    }

    @Test
    public void testOnConnectionSuspended() throws Exception {
        googleMapsActivity.onConnectionSuspended(0);
    }

    @Test
    public void testOnConnectionFailed() throws Exception {
        googleMapsActivity.onConnectionFailed(null);
    }
}

