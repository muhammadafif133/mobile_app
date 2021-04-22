package com.example.fitnessandworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.fitnessandworkout.utils.ActivityAdapter;
import com.example.fitnessandworkout.utils.ActivityModel;
import com.example.fitnessandworkout.utils.DataBaseHandler;

import java.util.List;

public class ViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    // Call recyclerview function to view data from database
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Create array list to view workout progress data from database
        DataBaseHandler dataBaseHandler = new DataBaseHandler(this);
        List<ActivityModel> activityModels = dataBaseHandler.getActivityList();

        if (activityModels.size() > 0){
            ActivityAdapter activityAdapter = new ActivityAdapter(activityModels,ViewActivity.this);
            recyclerView.setAdapter(activityAdapter);
        }else {
            Toast.makeText(this, "There is no activity in the database", Toast.LENGTH_SHORT).show();
        }
    }
}