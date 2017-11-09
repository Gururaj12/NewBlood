package com.example.dqtech.bloodbank;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DQTECH on 10/30/2017.
 */

public class Mydetails extends AppCompatActivity {
    EditText name, email1, contact, address, city, user12;
    TextView t;

    List<Users> artistList;
    //DatabaseReference databaseReference;

    private static final String TAG = "ViewDatabase";
    ListView  listView;

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String userID;

   /* public static final String mypreference = "mypref";

    public static final String Name = "nameKey";
    public static final String EMAIL = "emailKey";
    public static final String CONTACT = "contactKey";
    public static final String ADDRESS = "addressKey";
    public static final String CITY = "cityKey";
    public static final String USERNAME = "usernameKey";
    public static final String BLOOD = "bloodKey";
    SharedPreferences sharedpreferences;*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydetails);
        listView= (ListView) findViewById(R.id.list);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    // toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    // toastMessage("Successfully signed out.");
                }
                // ...
            }
        };

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               showData(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Users uInfo = new Users();
//            uInfo.setName(ds.child(userID).getValue(Users.class).getName());
            uInfo.setGender(ds.child(userID).getValue(Users.class).getGender());//set the name
            uInfo.setEmail(ds.child(userID).getValue(Users.class).getEmail()); //set the email
            uInfo.setContact(ds.child(userID).getValue(Users.class).getContact());
            uInfo.setCity(ds.child(userID).getValue(Users.class).getCity());
            uInfo.setBgroup(ds.child(userID).getValue(Users.class).getBgroup());
            uInfo.setAddress(ds.child(userID).getValue(Users.class).getAddress());

            ArrayList<String> array  = new ArrayList<>();
            array.add(uInfo.getName());
            array.add(uInfo.getEmail());
            array.add(uInfo.getCity());
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            listView.setAdapter(adapter);

           /* String em=uInfo.getEmail();
            email1.setText(em);*/

/*

            Users artist = ds.getValue(Users.class);
            String nam = artist.getName();
            String email = artist.getEmail();
            String con = artist.getContact();
            String add = artist.getAddress();
            String cit = artist.getCity();
            String use = artist.getUsername();


            email1.setText(email);
            contact.setText(con);
            address.setText(add);
            city.setText(cit);
            user12.setText(use);*//*
*/
/*
*/

            //set the phone_num

            //display all the information
            Log.d(TAG, "showData: name: " + uInfo.getName());
            Log.d(TAG, "showData: email: " + uInfo.getEmail());
            Log.d(TAG, "showData: phone_num: " + uInfo.getCity());
           /* ArrayList<String> array  = new ArrayList<>();
            array.add(uInfo.getName());
            array.add(uInfo.getEmail());
            array.add(uInfo.getPhone_num());
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            mListView.setAdapter(adapter);*//**//**/
        }
    }
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

        //   databaseReference= FirebaseDatabase.getInstance().getReference().child("users");


       /* name= (EditText) findViewById(R.id.name);
        email1= (EditText) findViewById(R.id.email);
        contact= (EditText) findViewById(R.id.contact);
        t= (TextView) findViewById(R.id.m);
        address= (EditText) findViewById(R.id.address);
        city= (EditText) findViewById(R.id.city);
        user= (EditText) findViewById(R.id.user);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(EMAIL)) {
            email1.setText(sharedpreferences.getString(EMAIL, ""));

        }
        if (sharedpreferences.contains(CONTACT)) {
            contact.setText(sharedpreferences.getString(CONTACT, ""));
        }
        if (sharedpreferences.contains(ADDRESS)) {
            address.setText(sharedpreferences.getString(ADDRESS, ""));

        }
        if (sharedpreferences.contains(CITY)) {
            city.setText(sharedpreferences.getString(CITY, ""));
        }
        if (sharedpreferences.contains(BLOOD)) {
            user.setText(sharedpreferences.getString(BLOOD, ""));

        }*/




      /*  Bundle b = getIntent().getExtras();
        name.setText(b.getCharSequence("name"));
        email.setText(b.getCharSequence("email"));
        contact.setText(b.getCharSequence("contact"));
        address.setText(b.getCharSequence("address"));
        city.setText(b.getCharSequence("city"));
        user.setText(b.getCharSequence("users"));
      //  name.setText(b.getCharSequence("name"));*/

       /* databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//               artistList.clear();
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    Users artist = s.getValue(Users.class);
                    String nam=artist.getName();
                    String email=artist.getEmail();
                    String  con=artist.getContact();
                    String add=artist.getAddress();
                    String cit=artist.getCity();
                    String use=artist.getUsername();

                   *//* name.setText(nam);
                    email1.setText(email);
                    contact.setText(con);
                    address.setText(add);
                    city.setText(cit);
                    user.setText(use)*//*;




                }
                ListArtist adapter = new ListArtist(Mydetails.this, artistList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/



/*    @Override
    protected void onStart() {
        super.onStart();
        databaseReference= FirebaseDatabase.getInstance().getReference("users").child("name");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                artistList.clear();
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    Users artist = s.getValue(Users.class);
                    String name1 = artist.getAddress();
                    name.setText(name1);

                }
                ListArtist adapter = new ListArtist(Mydetails.this, artistList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/

