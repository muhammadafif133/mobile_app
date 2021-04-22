package com.example.fitnessandworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ResultCallback;

public class MainMenu extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    Button btnStep, btnProgress, btnMuscle, btnMap, btnSignOut;

    GoogleApiClient googleApiClient;
    GoogleSignInOptions gso;

    Animation animTitle;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Shortcut to each buttons
        btnStep = findViewById(R.id.btnStep);
        btnProgress = findViewById(R.id.btnProgress);
        btnMuscle = findViewById(R.id.btnMuscle);
        btnMap = findViewById(R.id.btnMap);
        btnSignOut = findViewById(R.id.btnSignOut);
        title = (TextView)findViewById(R.id.tvTitle);

        // Title fade in animation
        animTitle = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        title.setVisibility(View.VISIBLE);
        title.startAnimation(animTitle);

        // To recognize which Google account is signed in
        gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        // Button to redirect to Step counter activity
        btnStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), stepCounter.class);
                startActivity(intent);
            }
        });

        // Button to redirect to Creating note for workout progress
        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WorkoutProgress.class);
                startActivity(intent);
            }
        });

        // Button to redirect to Camera activity
        btnMuscle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CameraApi.class);
                startActivity(intent);
            }
        });

        // Button to redirect to Google Maps activity
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GoogleMapsActivity.class);
                startActivity(intent);
            }
        });

        // Button for signing out from Google account
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                if (status.isSuccess()){
                                    gotoMainActivity();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Session not close", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        // Request API permission for sign in using Google account
        OptionalPendingResult<GoogleSignInResult> opr= Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        }else{
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    /**
     * Verify if sign in using google account is successful
     * @param result
     */
    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
        }
    }

    /**
     * Redirect to MainActivity page
     */
    private void gotoMainActivity (){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}