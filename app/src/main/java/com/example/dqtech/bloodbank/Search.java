package com.example.dqtech.bloodbank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by DQTECH on 10/30/2017.
 */

public class Search extends AppCompatActivity {
    private FirebaseDatabase database;
    TextView textView;
    EditText editText;
    Button b1;

    //list
    /*ListView listView;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;*/



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchdonor);
        textView= (TextView) findViewById(R.id.hint);
        editText= (EditText) findViewById(R.id.et);
        database = FirebaseDatabase.getInstance();
        b1= (Button) findViewById(R.id.s);


        //listview

       /* listView= (ListView) findViewById(R.id.list);

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

         listView.setAdapter(adapter);*/


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef = database.getReference();
                String a=editText.getText().toString();
                Query query = myRef.child("users").orderByChild("name").equalTo(a);
                //  Query query = myRef.child("users").orderByChild("name").equalTo("varunteja");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                /*String n= (String) dataSnapshot.child("name").getValue();

                textView.setText(n);*/



                        for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()){
                            String name= (String) messageSnapshot.child("name").getValue();
                            String gender= (String) messageSnapshot.child("gender").getValue();
                            String phone= (String) messageSnapshot.child("contact").getValue();
                            String city= (String) messageSnapshot.child("city").getValue();
                         //listview display
                          /*  arrayList.add(name);
                            arrayList.add(gender);
                            arrayList.add(phone);
                            arrayList.add(city);
*/
                         //   adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
                            // String age= (String) messageSnapshot.getValue();

                            Toast.makeText(Search.this, "found " +name +"", Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


    }
}
