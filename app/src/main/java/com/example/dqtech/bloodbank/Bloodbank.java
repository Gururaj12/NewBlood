package com.example.dqtech.bloodbank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

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

public class Bloodbank extends AppCompatActivity {

   ListView listView;
    List<Users> artistList;
    DatabaseReference databaseReference;

//search


    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artistList.clear();
                for (DataSnapshot s :dataSnapshot.getChildren()){
                    Users artist=s.getValue(Users.class);
                    artistList.add(artist);

                }
                ListArtist adapter=new ListArtist(Bloodbank.this,artistList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloodbank);
//search


        listView= (ListView) findViewById(R.id.listdata);
        artistList=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("users");



    }
}
