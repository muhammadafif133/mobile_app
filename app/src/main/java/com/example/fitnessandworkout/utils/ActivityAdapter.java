package com.example.fitnessandworkout.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessandworkout.R;

import java.util.List;


public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    List<ActivityModel> activity;
    Context context;
    DataBaseHandler dataBaseHandler;

    public ActivityAdapter(List<ActivityModel> activity, Context context){
        this.activity = activity;
        this.context = context;
        dataBaseHandler = new DataBaseHandler(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Create list view for workout progress in database
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ActivityModel activityModel = activity.get(position);

        // Retrieve and view data from database
        holder.textViewID.setText(Integer.toString(activityModel.getId()));
        holder.etDate.setText(activityModel.getDate());
        holder.etLevel.setText(activityModel.getLevel());
        holder.etActivity.setText(activityModel.getActivity());

        // Called when "Edit" button is clicked
        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update input fields
                String strDate = holder.etDate.getText().toString();
                String strLevel = holder.etLevel.getText().toString();
                String strActivity = holder.etActivity.getText().toString();

                // Update input fields into database
                dataBaseHandler.updateActivity(new ActivityModel(activityModel.getId(),strDate,strLevel,strActivity));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        // Called when "Delete" button is clicked
        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete data from database
                dataBaseHandler.deleteActivity(activityModel.getId());
                activity.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    /**
     * List all data from database
     * @return all workout progress
     */
    @Override
    public int getItemCount() {
        return activity.size();
    }

    // Initialize and create shortcut for input fields
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText etDate, etLevel, etActivity;
        Button button_Edit;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            etDate = itemView.findViewById(R.id.etDate);
            etLevel = itemView.findViewById(R.id.etLevel);
            etActivity = itemView.findViewById(R.id.etActivity);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);

        }
    }
}


