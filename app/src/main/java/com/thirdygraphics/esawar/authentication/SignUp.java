package com.thirdygraphics.esawar.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.thirdygraphics.esawar.Loading;
import com.thirdygraphics.esawar.R;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.facebook.FacebookSdk;


import com.google.firebase.auth.FacebookAuthProvider;

public class SignUp extends AppCompatActivity {
    private static final String TAG = "SignUp";
    private GoogleApiClient googleApiClient;
    private EditText edtTextUserName, edtEmail, edtContactNo, editPassword, editMatchPassword;
    private Button btnRegister;
    private LinearLayout btnGoogle, btnFacebook;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private static final int RC_SIGN_IN = 123;
    private CallbackManager callbackManager;

    private Boolean whatismyclick; //true = google false = facebook

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Authentication and Firestore
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        // Initialize Facebook SDK
        FacebookSdk.sdkInitialize(getApplicationContext());

        // Initialize Facebook CallbackManager
        callbackManager = CallbackManager.Factory.create();

        // Find views by their IDs
        edtTextUserName = findViewById(R.id.edtTextUserName);
        edtEmail = findViewById(R.id.edtEmail);
        edtContactNo = findViewById(R.id.edtContactNo);
        editPassword = findViewById(R.id.editPassword);
        editMatchPassword = findViewById(R.id.editMatchPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnFacebook = findViewById(R.id.btnFacebook);

        // Set click listener for btnRegister
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform registration when the button is clicked
                registerUser();
            }
        });

        // Set click listener for btnFacebook
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform registration when the button is clicked
                whatismyclick = false;
                Toast.makeText(getApplicationContext(), "Fb Login Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for btnGoogle
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatismyclick = true;
                // Perform registration when the button is clicked
                registerGoogleUser();
            }
        });

    }

    private void registerUser() {
        // Get the values from the EditText fields
        String userName = edtTextUserName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String contactNo = edtContactNo.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String confirmPassword = editMatchPassword.getText().toString().trim();

        // Validate that all fields are filled
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(getApplicationContext(), "Please enter your username", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(contactNo)) {
            Toast.makeText(getApplicationContext(), "Please enter your contact number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(getApplicationContext(), "Please confirm your password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create user with email and password in Firebase Authentication
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registration success
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            if (user != null) {
                                // Save user information in Firestore
                                saveUserToFirestore(user.getUid(), userName, email, contactNo, "user");
                            }
                            Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                        } else {
                            // Registration failed
                            Log.e(TAG, "Registration failed: " + task.getException());
                            Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveUserToFirestore(String userId, String userName, String email, String contactNo, String role) {
        // Create a new document in the "Users" collection with the user's ID
        DocumentReference userRef = firestore.collection("Users").document(userId);

        // Create a map of the user's information
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("username", userName);
        userMap.put("email", email);
        userMap.put("contactno", contactNo);
        userMap.put("role", role); // Add the role field

        // Set the user's information in the Firestore document
        userRef.set(userMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "User information saved to Firestore");
                        startActivity(new Intent(getApplicationContext(), Loading.class));

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error saving user information to Firestore: " + e.getMessage());
                    }
                });
    }

    // Register Google User
    private void registerGoogleUser() {
        // Configure Google Sign-In options
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Create a GoogleApiClient
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        // Google Sign-In connection failed
                        Toast.makeText(getApplicationContext(), "Google Sign-In connection failed", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Launch Google Sign-In Intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(whatismyclick== true){
            // Handle Google Sign-In result
            if (requestCode == RC_SIGN_IN) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                if (result.isSuccess()) {
                    // Google Sign-In successful
                    GoogleSignInAccount account = result.getSignInAccount();
                    if (account != null) {
                        firebaseAuthWithGoogle(account);
                    }
                } else {
                    // Google Sign-In failed
                    Toast.makeText(getApplicationContext(), "Google Sign-In failed", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            // Pass the activity result to the Facebook CallbackManager
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }


    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registration success
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                // Save user information in Firestore
                                saveUserToFirestore(user.getUid(), user.getDisplayName(), user.getEmail(), "", "user");
                            }
                            Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                        } else {
                            // Registration failed
                            Log.e(TAG, "Registration failed: " + task.getException());
                            Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    //Register Facebook User
   // TODO Coming Soon

    @Override
    protected void onStop() {
        super.onStop();
        // Disconnect the GoogleApiClient when the activity is stopped
        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.stopAutoManage(this);
            googleApiClient.disconnect();
        }
    }

}
