package com.example.fitnessandworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * REFERENCES
 * Accelerometer sensor to detect step is referred from
 * Sarti Technology Youtube Channel
 * https://youtu.be/NnvJylicKvE
 */

public class stepCounter extends AppCompatActivity {

    private TextView tvStepDetector, title;
    private double MagnitudePrevious = 0;
    private Integer stepCount = -1;
    Animation animTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        tvStepDetector = findViewById(R.id.tvStepDetector);
        title = (TextView)findViewById(R.id.title);

        animTitle = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        title.setVisibility(View.VISIBLE);
        title.startAnimation(animTitle);

        // Call Sensor Manager to user accelerometer sensor service
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Save step count in shared preferences
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        stepCount = sharedPreferences.getInt("stepCount", 0);

        // Counting step using 3 dimensional accelerometer value
        SensorEventListener stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                //calculating magnitude using accelerometer sensor
                if (sensorEvent != null ){
                    float x_acceleration = sensorEvent.values[0];
                    float y_acceleration = sensorEvent.values[1];
                    float z_acceleration = sensorEvent.values[2];

                    double Magnitude = Math.sqrt(x_acceleration * x_acceleration + y_acceleration * y_acceleration + z_acceleration * z_acceleration);
                    double MagnitudeDelta = Magnitude - MagnitudePrevious;
                    MagnitudePrevious = Magnitude;

                    //count step
                    if(MagnitudeDelta > 6){
                        stepCount++;
                        tvStepDetector.setText(stepCount.toString());
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    // Called step count value when app is on pause
    protected void onPause(){
        super.onPause();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", stepCount);
        editor.apply();
    }

    // Reset step count value when app is on stop
    protected void onStop(){
        super.onStop();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", 0);
        editor.apply();
    }

    // Get step count when app is on resume
    protected void onResume(){
        super.onResume();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        stepCount = sharedPreferences.getInt("stepCount", 0);
    }
}