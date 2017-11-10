package com.example.dqtech.bloodbank;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {
    EditText et1;
    Button send;
    DatabaseReference mdatabase;
    RecyclerView rc;
    private FirebaseAuth auth;
    String messageval;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mcurrentuser;
private DatabaseReference mdatabaseusers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et1 = (EditText) findViewById(R.id.messageedit);
        send = (Button) findViewById(R.id.snd);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("message");
        auth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
           /*  if (firebaseAuth.getCurrentUser()==null){
         //   startActivity(new Intent(Main2Activity.this,Registration.class));

}*/
            }
        };

        rc= (RecyclerView) findViewById(R.id.messagerec);
        rc.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        rc.setLayoutManager(linearLayoutManager);
    }

    public void sendbutton(View view) {

        mcurrentuser=auth.getCurrentUser();
        mdatabaseusers=FirebaseDatabase.getInstance().getReference().child("users").child(mcurrentuser.getUid());
        auth.addAuthStateListener(mAuthListener);
        messageval = et1.getText().toString();
        if (!TextUtils.isEmpty(messageval)) {
            final DatabaseReference newpost = mdatabase.push();
            mdatabaseusers.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    newpost.child("content").setValue(messageval);
                    newpost.child("username").setValue(dataSnapshot.child("name").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            rc.scrollToPosition(rc.getAdapter().getItemCount());

        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Message, Messageviewholder> FBRA = new FirebaseRecyclerAdapter<Message, Messageviewholder>(
                Message.class, R.layout.singlemessagelayout, Messageviewholder.class, mdatabase
        ) {
            @Override
            protected void populateViewHolder(Messageviewholder viewHolder, Message model, int position) {
                viewHolder.setContent(model.getContent());
                viewHolder.setusername(model.getUsername());
            }
        };
        rc.setAdapter(FBRA);
    }

    public static class Messageviewholder extends RecyclerView.ViewHolder {

        View mview;

        public Messageviewholder(View itemView) {
            super(itemView);
            mview = itemView;
        }


        public void setContent(String content) {
            TextView messagecontent = (TextView) mview.findViewById(R.id.message);
            messagecontent.setText(content);

        }
        public void setusername(String username){
            TextView usernames= (TextView) mview.findViewById(R.id.usernametext);
            usernames.setText(username);
        }
    }
}
