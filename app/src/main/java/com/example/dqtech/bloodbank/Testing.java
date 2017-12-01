package com.example.dqtech.bloodbank;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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

public class Testing extends AppCompatActivity {
    private FirebaseDatabase database;
    TextView textView;
    EditText editText;
    Button b1;

    Spinner sp;
    String a;
    String emailId;
    //list
    ListView listView;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        editText= (EditText) findViewById(R.id.et);

        database = FirebaseDatabase.getInstance();
        b1= (Button) findViewById(R.id.s);
        sp= (Spinner) findViewById(R.id.bgroup);


        //listview

        listView= (ListView) findViewById(R.id.list);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
        final String emailId=currentFirebaseUser.getEmail();
        Toast.makeText(this, emailId, Toast.LENGTH_SHORT).show();

        DatabaseReference myRef = database.getReference("users");

        // String a=sp.getSelectedItem().toString();

        //   String a=editText.getText().toString();
        Query query = myRef.child("users").orderByChild("email").equalTo(emailId);
        //  Query query = myRef.child("users").orderByChild("name").equalTo("varunteja");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()){
                    String name= (String) messageSnapshot.child("name").getValue();
                    String gender= (String) messageSnapshot.child("gender").getValue();
                    String phone= (String) messageSnapshot.child("contact").getValue();
                    String city= (String) messageSnapshot.child("bgroup").getValue();
                    //listview display
                    ArrayList<String> arrayList  = new ArrayList<>();
                    arrayList.add(name);
                    arrayList.add(gender);
                    arrayList.add(phone);
                    arrayList.add(city);

                    adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
                    listView.setAdapter(adapter);
                    //  String age= (String) messageSnapshot.getValue();

                    Toast.makeText(Testing.this, "found " +name +"", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}



