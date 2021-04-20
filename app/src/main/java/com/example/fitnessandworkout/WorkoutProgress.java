package com.example.fitnessandworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessandworkout.utils.ActivityModel;
import com.example.fitnessandworkout.utils.DataBaseHandler;

public class WorkoutProgress extends AppCompatActivity {

    EditText etDate, etLevel, etActivity;
    Button btnAdd, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_progress);

        etDate = findViewById(R.id.etDate);
        etLevel = findViewById(R.id.etLevel);
        etActivity = findViewById(R.id.etActivity);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strDate = etDate.getText().toString();
                String strLevel = etLevel.getText().toString();
                String strActivity = etActivity.getText().toString();

                if (strDate.length() <=0 || strActivity.length() <=0 || strLevel.length() <=0){
                    Toast.makeText(WorkoutProgress.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                }else {
                    DataBaseHandler dataBaseHandler = new DataBaseHandler(WorkoutProgress.this);
                    ActivityModel activityModel = new ActivityModel(strDate,strLevel,strActivity);
                    dataBaseHandler.addActivity(activityModel);
                    Toast.makeText(WorkoutProgress.this, "Add activity successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutProgress.this, ViewActivity.class);
                startActivity(intent);
            }
        });

    }
}