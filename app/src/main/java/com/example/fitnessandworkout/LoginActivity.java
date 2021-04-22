package com.example.fitnessandworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.fitnessandworkout.utils.DataBaseHandler;

public class LoginActivity extends AppCompatActivity {

    EditText userEmail, password;
    Button login;
    DataBaseHandler DB;
    TextView title;
    Animation animTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Shortcut to input fields and buttons
        userEmail = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.etPassword);
        login = (Button)findViewById(R.id.btnLogIn);
        title = (TextView)findViewById(R.id.textView);
        DB = new DataBaseHandler(this);

        // Title animation
        animTitle = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        title.setVisibility(View.VISIBLE);
        title.startAnimation(animTitle);

        // Called when the "Sign in" button is clicked
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  email = userEmail.getText().toString();
                String  pass = password.getText().toString();

                // Check if email and password fields are empty
                if(email.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else{
                    // Verify if email and password are matched
                    Boolean checkemailpassword = DB.checkEmailPassword(email, pass);
                    if(checkemailpassword == true) {
                        Toast.makeText(LoginActivity.this, "Log in successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}