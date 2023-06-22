package com.thirdygraphics.esawar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.thirdygraphics.esawar.authentication.Login;
import com.thirdygraphics.esawar.authentication.Welcome;

public class Loading extends AppCompatActivity {

    private static final int DELAY_DURATION = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // Delay for 2 seconds and start the new activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Check if user is signed in (non-null) and update UI accordingly.
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();

                if(currentUser != null){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }else{
                    startActivity(new Intent(getApplicationContext(), Welcome.class));
                }
                finish(); // Optional, if you want to close the current activity
            }
        }, DELAY_DURATION);
    }
}
