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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

public class Registration extends AppCompatActivity {
    EditText name,email,contact,address,city,username,password;

    ImageView imageView;
    Button register;
    Spinner  spinner;
    Spinner sgender;
    private ProgressBar progressBar;

    DatabaseReference databaseArtists;

    //String id, user_name,usermail, usercontact, useraddress, usercity, blood, usermale;
    String id1, name1, email11, contact1, address1, city1, gblood1, gender1;
//sharedpref

    public static final String mypreference = "mypref";

    public static final String Name = "nameKey";
    public static final String EMAIL = "emailKey";
    public static final String CONTACT = "contactKey";
    public static final String ADDRESS = "addressKey";
    public static final String CITY = "cityKey";
    public static final String USERNAME = "usernameKey";
    public static final String BLOOD = "bloodKey";
    public static final String GENDER = "genderKey";
    SharedPreferences sharedpreferences;

   // private FirebaseAuth auth;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    List<Users> user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name= (EditText) findViewById(R.id.name);
        email= (EditText) findViewById(R.id.email);
        contact= (EditText) findViewById(R.id.contact);
        address= (EditText) findViewById(R.id.address);
        city= (EditText) findViewById(R.id.city);
        username= (EditText) findViewById(R.id.user);
        password= (EditText) findViewById(R.id.userpswd);

        sgender= (Spinner) findViewById(R.id.genderspinner);


        imageView= (ImageView) findViewById(R.id.image);

        register= (Button) findViewById(R.id.register);
        spinner= (Spinner) findViewById(R.id.bloodgroup);

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        mAuth = FirebaseAuth.getInstance();
        user = new ArrayList<>();

        databaseArtists = FirebaseDatabase.getInstance().getReference("users");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adddata();
            }


    public void shared()
    {
        String user_name=username.getText().toString();
        String usermail=email.getText().toString();
        String usercontact=contact.getText().toString();
        String useraddress=address.getText().toString();
        String usercity=city.getText().toString();
        String  gender=sgender.getSelectedItem().toString();
        String blood=spinner.getSelectedItem().toString();






        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(EMAIL,usermail);
        editor.putString(CONTACT,usercontact);
        editor.putString(ADDRESS,useraddress);
        editor.putString(CITY,usercity);
        editor.putString(USERNAME,user_name);
        editor.putString(BLOOD,blood);
        editor.putString(GENDER,gender);

        editor.commit();
        Toast.makeText(Registration.this,"Thanks",Toast.LENGTH_LONG).show();
    }
    private void adddata(){

        String user_name=name.getText().toString();
        String usermail=email.getText().toString();
        String usercontact=contact.getText().toString();
        String useraddress=address.getText().toString();
        String usercity=city.getText().toString();
        String users=username.getText().toString();
        String userpassword=password.getText().toString();
        String blood=spinner.getSelectedItem().toString();
        String  gender=sgender.getSelectedItem().toString();
//                String userfemale=fenmale.getText().toString();

        // add();




        if (TextUtils.isEmpty(user_name)) {
            Toast.makeText(getApplicationContext(), "Enter username!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(usermail)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(usercontact)) {
            Toast.makeText(getApplicationContext(), "Enter your contact!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(usercity)) {
            Toast.makeText(getApplicationContext(), "Enter your city!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(useraddress)) {
            Toast.makeText(getApplicationContext(), "Enter your address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(users)) {
            Toast.makeText(getApplicationContext(), "Enter your username!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userpassword.length() < 6) {
            Toast.makeText(getApplicationContext(), "Enter your password!", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(usermail, userpassword)
                .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Registration.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        ///* progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Registration.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // add();
                            shared();
                            adding();


                            startActivity(new Intent(Registration.this,MainActivity.class));

                            finish();
                        }
                    }
                });
    }
        });
    }
    public void adding(){
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
        String id1=currentFirebaseUser.getUid();





        databaseArtists = FirebaseDatabase.getInstance().getReference("users");

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(USERNAME)) {
            name1 = sharedpreferences.getString(USERNAME, "");
        }
        if (sharedpreferences.contains(EMAIL)) {
            email11= sharedpreferences.getString(EMAIL, "");

        }
        if (sharedpreferences.contains(CONTACT)) {
            contact1 = sharedpreferences.getString(CONTACT, "");
        }
        if (sharedpreferences.contains(CITY)) {
            city1 = sharedpreferences.getString(CITY, "");

        }
        if (sharedpreferences.contains(BLOOD)) {
            gblood1 = sharedpreferences.getString(BLOOD, "");
        }
        if (sharedpreferences.contains(ADDRESS)) {
            address1 = sharedpreferences.getString(ADDRESS, "");

        }
        if (sharedpreferences.contains(GENDER)) {
            gender1 = sharedpreferences.getString(GENDER, "");

        }

        String UId=mAuth.getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");

     //   Users user = new Users(UId,name,email,phone,address,dob,bloodgroup);

        myRef.child(UId).setValue(user);



        Users artist = new Users(UId, name1, email11,contact1,address1, city1,gblood1 , gender1);

        //Saving the Artist
        databaseArtists.child(id1).setValue(artist);
        Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "" + artist.getEmail(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "" + artist.getName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    }



