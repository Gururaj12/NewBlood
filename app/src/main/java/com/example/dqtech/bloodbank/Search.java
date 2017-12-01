package com.example.dqtech.bloodbank;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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

    Spinner sp;

    //list
    ListView listView;

    private ArrayAdapter<String> adapter;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchdonor);
      //  textView= (TextView) findViewById(R.id.hint);
        editText= (EditText) findViewById(R.id.et);

        database = FirebaseDatabase.getInstance();
        b1= (Button) findViewById(R.id.s);
        sp= (Spinner) findViewById(R.id.bgroup);


        //listview

        listView= (ListView) findViewById(R.id.list);

       // arrayList = new ArrayList<String>();

       /* adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

         listView.setAdapter(adapter);*/

//hello
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef = database.getReference();

                String a=sp.getSelectedItem().toString();
            //  String a=editText.getText().toString();
                Query query = myRef.child("users").orderByChild("bgroup").equalTo(a);
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
    public class CustomAdapter extends ArrayAdapter<Searched_data>{
        private ArrayList<Searched_data> arrayList;
        Context mContext;

        public CustomAdapter(ArrayList<Searched_data>arrayList,Context mContext ) {
            super(mContext, R.layout.searchedata);
            this.arrayList=arrayList;
            this.mContext=mContext;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.searchedata,parent,false);

            TextView tename= (TextView) convertView.findViewById(R.id.textname);
            TextView tgender= (TextView) convertView.findViewById(R.id.textgender);
            TextView tnumber= (TextView) convertView.findViewById(R.id.textnumber);
            TextView tgrp= (TextView) convertView.findViewById(R.id.textbgrp);

            Searched_data searched_data=getItem(position);
            tename.setText(searched_data.getUname());
            tgender.setText(searched_data.getGen());
            tnumber.setText(searched_data.getNumber());
            tgrp.setText(searched_data.getGrp());
            return convertView;

        }

    }
}
