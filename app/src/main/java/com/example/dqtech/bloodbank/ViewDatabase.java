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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by DQTECH on 11/6/2017.
 */

public class ViewDatabase extends AppCompatActivity {
  //  TextView et1,et2,et3,et4,et5,et6,et7;
    private static final String TAG = "ViewDatabase";

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private  String userID;

    private ListView mListView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlay);
     /*  et1= (TextView) findViewById(R.id.uname);
        et2= (TextView) findViewById(R.id.uemail);
        et3= (TextView) findViewById(R.id.ugender);
        et4= (TextView) findViewById(R.id.ucontact);
        et5= (TextView) findViewById(R.id.uaddress);
        et6= (TextView) findViewById(R.id.ucity);
        et7= (TextView) findViewById(R.id.ublood);*/
       mListView = (ListView) findViewById(R.id.listview);

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
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
                    toastMessage("Successfully signed in with: " + user.getEmail());



                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
                // ...
            }
        };

        Toast.makeText(getApplicationContext(),userID,Toast.LENGTH_LONG).show();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Users user= dataSnapshot.getValue(Users.class);



                Toast.makeText(getApplicationContext(),user.getName(),Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),user.getCity(),Toast.LENGTH_LONG).show();
                Toast.makeText(ViewDatabase.this, user.getContact(), Toast.LENGTH_SHORT).show();
  showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    //

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Users uInfo = new Users();
            uInfo.setName(ds.child(userID).getValue(Users.class).getName()); //set the name
            uInfo.setGender(ds.child(userID).getValue(Users.class).getGender()); //set the email
            uInfo.setEmail(ds.child(userID).getValue(Users.class).getEmail());
            uInfo.setContact(ds.child(userID).getValue(Users.class).getContact());
            uInfo.setCity(ds.child(userID).getValue(Users.class).getCity());
            uInfo.setBgroup(ds.child(userID).getValue(Users.class).getBgroup());
            uInfo.setAddress(ds.child(userID).getValue(Users.class).getAddress());///set the phone_num

            //display all the information
            Log.d(TAG, "showData: name: " + uInfo.getName());
            Log.d(TAG, "showData: email: " + uInfo.getEmail());
            Log.d(TAG, "showData: phone_num: " + uInfo.getCity());

            ArrayList<String> array  = new ArrayList<>();
            array.add(uInfo.getName());
            array.add(uInfo.getGender());
            array.add(uInfo.getEmail());//gender display
           array.add(uInfo.getContact());
            array.add(uInfo.getCity());//email display
           array.add(uInfo.getBgroup());
           array.add(uInfo.getAddress());
          /*  et1.setText(uInfo.getName());
            et2.setText(uInfo.getGender());
            et3.setText(uInfo.getEmail());
            et4.setText(uInfo.getContact());
            et5.setText(uInfo.getCity());
            et6.setText(uInfo.getBgroup());
            et7.setText(uInfo.getAddress());*/


            Toast.makeText(this, uInfo.getCity(), Toast.LENGTH_SHORT).show();

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            mListView.setAdapter(adapter);
        }
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



    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }



}
