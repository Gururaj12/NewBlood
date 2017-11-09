package com.example.dqtech.bloodbank;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
    Button search,bloodbank,mydetails,logout;
    public static final String TAG="login";




    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private  String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        search= (Button) findViewById(R.id.search);
        bloodbank= (Button) findViewById(R.id.bb);
        mydetails= (Button) findViewById(R.id.mydetail);
        logout= (Button) findViewById(R.id.logout);



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
                    //Toast.makeText(getApplicationContext(),"Succesfully signed with :"+user.getEmail()")
                    //Toast.makeText(Login.this,user.getEmail(), Toast.LENGTH_LONG)).show();
                    Toast.makeText(getApplicationContext(),user.getEmail(),Toast.LENGTH_LONG).show();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(getApplicationContext(),"signout",Toast.LENGTH_LONG).show();
                }
                // ...
            }
        };


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent=new Intent(Home.this,Login.class);
                startActivity(intent);

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s=new Intent(Home.this,Search.class);
                startActivity(s);

            }
        });

        bloodbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s=new Intent(Home.this,Bloodbank.class);
                startActivity(s);

            }
        });
        mydetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                       // showData(dataSnapshot);
                        Intent s=new Intent(Home.this,ViewDatabase.class);
                        startActivity(s);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });
    }
    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Users uInfo = new Users();
            uInfo.setName(ds.child(userID).getValue(Users.class).getName());
            uInfo.setGender(ds.child(userID).getValue(Users.class).getGender());//set the name
            uInfo.setEmail(ds.child(userID).getValue(Users.class).getEmail()); //set the email
            uInfo.setContact(ds.child(userID).getValue(Users.class).getContact());
            uInfo.setCity(ds.child(userID).getValue(Users.class).getCity());
            uInfo.setBgroup(ds.child(userID).getValue(Users.class).getBgroup());
            uInfo.setAddress(ds.child(userID).getValue(Users.class).getAddress());
            Toast.makeText(getApplicationContext(),uInfo.getName(),Toast.LENGTH_LONG);
            Toast.makeText(getApplicationContext(),uInfo.getEmail(),Toast.LENGTH_LONG);
            Toast.makeText(getApplicationContext(),uInfo.getContact(),Toast.LENGTH_LONG);
            Toast.makeText(getApplicationContext(),uInfo.getCity(),Toast.LENGTH_LONG);

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
            mListView.setAdapter(adapter);*/
        }
    }
}
