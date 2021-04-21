package com.example.fitnessandworkout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.fitnessandworkout.utils.DataBaseHandler;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Google sign in tutorial is referred from ProgrammingKnowledge Youtube Channel
 * (https://youtu.be/uPg1ydmnzpk)
 * Sign up and sign in tutorial is referred from AllCoding Tutorial Youtube Channel
 * (https://youtu.be/8obgNNlj3Eo)
 */
public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    EditText userEmail, password;
    SignInButton googleSignin;
    Button signUp, signIn;
    TextView info, title;
    DataBaseHandler DB;
    Animation animSI, animSU;

    private GoogleApiClient googleApiClient;
    private static final int Google_SI = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        userEmail = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.etPassword);
        info = (TextView)findViewById(R.id.tvInfo);
        title = (TextView)findViewById(R.id.textView);
        signUp = (Button)findViewById(R.id.btnSignUp);
        signIn = (Button)findViewById(R.id.btnSignIn);
        googleSignin = (SignInButton)findViewById(R.id.btnGoogle);
        DB = new DataBaseHandler(this);

        // Sign up button to register new users
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  email = userEmail.getText().toString();
                String  pass = password.getText().toString();

                animSU = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                title.setVisibility(View.VISIBLE);
                title.startAnimation(animSU);

                if(email.equals("") || pass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkemail = DB.checkEmail(email);
                    if(checkemail == false){
                        Boolean insert = DB.insertData(email, pass);
                        if(insert == true) {
                            Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Email already exist. Please use different email", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        // Sign in button to log in registered user into the app
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animSI = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                title.setVisibility(View.VISIBLE);
                title.startAnimation(animSI);

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        // Google sign in button to register the app using google account
        googleSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, Google_SI);
            }
        });
    }

    /**
     * Function to access Google account API
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Google_SI){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    /**
     * Verifying google account
     * @param result
     */
    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            gotoMainMenu();
        }else{
            Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Grant access to main menu activity
     */
    private void gotoMainMenu(){
        Intent intent=new Intent(MainActivity.this,MainMenu.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}