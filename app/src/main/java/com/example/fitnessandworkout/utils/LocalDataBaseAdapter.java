package com.example.fitnessandworkout.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessandworkout.CameraApi;
import com.example.fitnessandworkout.ImageFragments.LocalFragment;
import com.example.fitnessandworkout.R;

import java.util.ArrayList;

public class LocalDataBaseAdapter extends RecyclerView.Adapter<LocalDataBaseAdapter.MyViewHolder> {
    Context context;
    ArrayList<LocalResponse> singleRowArrayList;
    SQLiteDatabase db;
    DataBaseHandler myDatabase;

    /**
     * Create arraylist to retrieve image from  database
     * @param context
     * @param singleRowArrayList
     * @param db
     * @param myDatabase
     */
    public LocalDataBaseAdapter(Context context, ArrayList<LocalResponse> singleRowArrayList, SQLiteDatabase db, DataBaseHandler myDatabase) {
        this.context = context;
        this.singleRowArrayList = singleRowArrayList;
        this.db = db;
        this.myDatabase = myDatabase;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.local_database_items, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.newsImage.setImageBitmap(getBitmapFromEncodedString(singleRowArrayList.get(i).image));
        myViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(i,singleRowArrayList);
            }
        });
    }

    // View all images in the database
    @Override
    public int getItemCount() {
        return singleRowArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage,delete;
        TextView id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImage = (ImageView) itemView.findViewById(R.id.newsImage);
            delete = (ImageView) itemView.findViewById(R.id.delete);
        }
    }

    /**
     * Delete image in the recyclerview
     * @param position
     * @param singleRowArrayList
     */
    public void deleteData(final int position, final ArrayList<LocalResponse> singleRowArrayList){
        new AlertDialog.Builder(context)
                .setIcon(R.drawable.defaultimage)
                .setTitle("Delete result")
                .setMessage("Are you sure you want delete this result?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    // Called when "delete" icon is clicked
                    public void onClick(DialogInterface dialog, int which) {
                        /* This is where deletions should be handled */
                        myDatabase.deleteEntry(singleRowArrayList.get(position).getUid());
                        singleRowArrayList.remove(position);
                        notifyItemRemoved(position);
                        notifyDataSetChanged();
                        myDatabase.close();
                        ((CameraApi) context).loadFragment(new LocalFragment(), true);

                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


    // Convert string data type in database into bitmap
    private Bitmap getBitmapFromEncodedString(String encodedString){
        byte[] arr = Base64.decode(encodedString, Base64.URL_SAFE);
        Bitmap img = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        return img;
    }
}
