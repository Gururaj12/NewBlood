package com.example.dqtech.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public static final String mypreference = "mypref";

    //  public static final String Name = "nameKey";
    public static final String EMAIL = "emailKey";
    public static final String CONTACT = "contactKey";
    public static final String ADDRESS = "addressKey";
    public static final String CITY = "cityKey";
    public static final String USERNAME = "usernameKey";
    public static final String BLOOD = "bloodKey";
    public static final String GENDER = "genderKey";
    DatabaseReference databaseArtists;

    String id, name, email1, contact, address, city, gblood, gender;

    SharedPreferences sharedpreferences;



    EditText user,password;
    Button login,registration;
    private FirebaseAuth auth;

    private FirebaseAuth.AuthStateListener mAuthListener;

  //  public static final String PREFS_NAME = "mypref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
        String id=currentFirebaseUser.getUid();

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

//copied from login

        user= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);

       /* SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

    SharedPreferences.Editor editor = settings.edit();*/

        auth = FirebaseAuth.getInstance();

       // mAuth = FirebaseAuth.getInstance();



        login= (Button) findViewById(R.id.log);
        registration= (Button) findViewById(R.id.reg);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usermail=user.getText().toString();
                String userpass=password.getText().toString();
                if(TextUtils.isEmpty(usermail)){
                    Toast.makeText(getApplicationContext(),"Enter email",Toast.LENGTH_LONG).show();
                }
                if(TextUtils.isEmpty(usermail)){
                    Toast.makeText(getApplicationContext(),"enter password",Toast.LENGTH_LONG).show();
                }
                auth.signInWithEmailAndPassword(usermail, userpass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                //progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        //inputPassword.setError(getString(R.string.minimum_password));
                                        Toast.makeText(MainActivity.this,"password should be of 6 letter", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(MainActivity.this, Home.class);
                                   startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });



        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Registration.class);
                startActivity(intent);
            }
        });


    }


}
