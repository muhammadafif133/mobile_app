package com.example.fitnessandworkout.ImageFragments;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.fitnessandworkout.CameraApi;
import com.example.fitnessandworkout.R;
import com.example.fitnessandworkout.utils.DataBaseHandler;

import java.io.ByteArrayOutputStream;

public class CameraFragment extends Fragment {
    private static final int CAMERA_REQUEST = 1888;
    TextView text,text1;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    String photo;
    DataBaseHandler databaseHandler;
    private SQLiteDatabase db;
    Bitmap theImage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.camera_fragment,container,false);

        // Create shortcut for fragment text
        text = view.findViewById(R.id.text);
        text1 = view.findViewById(R.id.text1);
        databaseHandler = new DataBaseHandler(getContext());

        // Called when "SHOW OFF YOUR MUSCLE!" text is clicked
        text.setOnClickListener(
                new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View v) {
                        // Request permission to use device's camera
                        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                        {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                        }
                        // Use device's camera if permission is granted
                        else
                        {
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_REQUEST);

                        }
                    }
                });

        // Called when "TRACK YOUR MUSCLE" text is clicked
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call Local fragment to view images
                ((CameraApi) getActivity()).loadFragment(new LocalFragment(), true);
            }
        });
        return view;
    }

    private void setDataToDataBase() {
        db = databaseHandler.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(databaseHandler.IMG_URL,getEncodedString(theImage));

        // Save image after into database after being captured
        long id = db.insert(databaseHandler.IMAGE_TABLE, null, cv);
        if (id < 0) {
            Toast.makeText(getContext(), "Something went wrong. Please try again later...", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Add successful", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Reuqesting for premissons
     * @param requestCode
     * @param permissions
     * @param grantResults
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(getActivity(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Start an activity for result
     * @param requestCode
     * @param resultCode
     * @param data
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            // Pass string type of image to be saved into database
            theImage = (Bitmap) data.getExtras().get("data");
            photo = getEncodedString(theImage);
            setDataToDataBase();
        }
    }

    // Convert bitmap into string to save into database
    private String getEncodedString(Bitmap bitmap){

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, os);
        byte[] imageArr = os.toByteArray();
        return Base64.encodeToString(imageArr, Base64.URL_SAFE);

    }



}
