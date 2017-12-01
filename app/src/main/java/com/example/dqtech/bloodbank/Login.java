package com.example.dqtech.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    public static final String TAG = "login";

    EditText user, password;
    Button login;
    TextView registration;

    DatabaseReference databaseArtists;

    List<Users> userlist;

    private String userID;

    public static final String mypreference = "mypref";

    //  public static final String Name = "nameKey";
    public static final String EMAIL = "emailKey";
    public static final String CONTACT = "contactKey";
    public static final String ADDRESS = "addressKey";
    public static final String CITY = "cityKey";
    public static final String USERNAME = "usernameKey";
    public static final String BLOOD = "bloodKey";
    public static final String GENDER = "genderKey";

    String id, name, email1, contact, address, city, gblood, gender;

    SharedPreferences sharedpreferences;
    //hello
    private FirebaseAuth mAuth;
    //  public static final String mypreference = "mypref";

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.log);
        registration = (TextView) findViewById(R.id.reg);
      //  display = (Button) findViewById(R.id.display);

        mAuth = FirebaseAuth.getInstance();


        userlist = new ArrayList<>();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    id = user.getUid();

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    databaseArtists = FirebaseDatabase.getInstance().getReference("users");

                    sharedpreferences = getSharedPreferences(mypreference,
                            Context.MODE_PRIVATE);
                    if (sharedpreferences.contains(USERNAME)) {
                        name = sharedpreferences.getString(USERNAME, "");
                    }
                    if (sharedpreferences.contains(EMAIL)) {
                        email1 = sharedpreferences.getString(EMAIL, "");

                    }
                    if (sharedpreferences.contains(CONTACT)) {
                        contact = sharedpreferences.getString(CONTACT, "");
                    }
                    if (sharedpreferences.contains(CITY)) {
                        city = sharedpreferences.getString(CITY, "");

                    }
                    if (sharedpreferences.contains(BLOOD)) {
                        gblood = sharedpreferences.getString(BLOOD, "");
                    }
                    if (sharedpreferences.contains(ADDRESS)) {
                        address = sharedpreferences.getString(ADDRESS, "");

                    }
                    if (sharedpreferences.contains(GENDER)) {
                        gender = sharedpreferences.getString(GENDER, "");

                    }


                    Users artist = new Users(id, name, email1, contact, address, city, gblood, gender);

                    //Saving the Artist
                    databaseArtists.child(id).setValue(artist);


                    //Toast.makeText(getApplicationContext(),"Succesfully signed with :"+user.getEmail()")
                    //Toast.makeText(Login.this,user.getEmail(), Toast.LENGTH_LONG)).show();
                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), user.getEmail(), Toast.LENGTH_LONG).show();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(getApplicationContext(), "signout", Toast.LENGTH_LONG).show();
                }
                // ...
            }
        };
    }


    {





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // add();

                String usermail = user.getText().toString();
                String userpass = password.getText().toString();
                if (!usermail.equals("") && !userpass.equals("")) {

                    //    mAuth.signInWithEmailAndPassword(usermail, userpass);

                    mAuth.signInWithEmailAndPassword(usermail, userpass);

                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                           /* .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                   // progressBar.setVisibility(View.GONE);
                                    if (!task.isSuccessful()) {
                                        // there was an error
                                        if (password.length() < 6) {
                                           // inputPassword.setError(getString(R.string.minimum_password));
                                            Toast.makeText(Login.this, "minimum six character", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(Login.this,"failed", Toast.LENGTH_LONG).show();

                                            //Toast.makeText(Login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                        }
                                    } else {



                                        //Toast.makeText(getApplicationContext(),"Succesfully signed with :"+user.getEmail()")
                                        //Toast.makeText(Login.this,user.getEmail(), Toast.LENGTH_LONG)).show();
                                       // Toast.makeText(getApplicationContext(), user.getEmail(), Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Login.this, Home.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });*/
/*
                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);*/


                }

            }
        });


        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);

            }
        });
     /*   display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences(mypreference,MODE_PRIVATE);
                String name=sharedPreferences.getString("nameKey","");
                Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
            }
        });*/


    }


    @Override
    public void onStart () {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop () {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
