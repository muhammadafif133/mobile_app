package com.example.fitnessandworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.fitnessandworkout.ImageFragments.CameraFragment;

/**
 * REFERENCES
 * Camera Sensor is referred from 6002CEM module
 */

public class CameraApi extends AppCompatActivity {
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_api);

        // Call camera fragment
        loadFragment(new CameraFragment(), false);
    }

    /**
     * Call clicked fragment
     * @param fragment
     * @param bool
     */
    public void loadFragment(Fragment fragment, Boolean bool) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        if (bool)
            transaction.addToBackStack(null);
        transaction.commit();
    }

}
