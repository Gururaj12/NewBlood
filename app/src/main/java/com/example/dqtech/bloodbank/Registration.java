package com.example.dqtech.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Registration extends AppCompatActivity {
    EditText name,email,contact,address,city,username,password;
    RadioButton male,fenmale;
    ImageView imageView;
    Button browse,register;
    Spinner  spinner;
    CheckBox  checkBox;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    DatabaseReference databaseArtists;
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
        male= (RadioButton) findViewById(R.id.male);
        fenmale= (RadioButton) findViewById(R.id.female);
        imageView= (ImageView) findViewById(R.id.image);
        browse= (Button) findViewById(R.id.browse);
        register= (Button) findViewById(R.id.register);
        spinner= (Spinner) findViewById(R.id.bloodgroup);
        checkBox= (CheckBox) findViewById(R.id.ch);

      //  sharedpreferences=getSharedPreferences();

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        auth = FirebaseAuth.getInstance();
        user = new ArrayList<>();





        databaseArtists = FirebaseDatabase.getInstance().getReference("users");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name=name.getText().toString();
                String usermail=email.getText().toString();
                String usercontact=contact.getText().toString();
                String useraddress=address.getText().toString();
                String usercity=city.getText().toString();
                String users=username.getText().toString();
                String userpassword=password.getText().toString();
                String blood=spinner.getSelectedItem().toString();
                String usermale=male.getText().toString();
                String userfemale=fenmale.getText().toString();

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
                auth.createUserWithEmailAndPassword(usermail, userpassword)
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
                                    add();
                                    shared();
                                    startActivity(new Intent(Registration.this,Login.class));
                                    finish();
                                }
                            }
                        });
            }
        });



    }

    public void shared()
    {
        String user_name=username.getText().toString();
        String usermail=email.getText().toString();
        String usercontact=contact.getText().toString();
        String useraddress=address.getText().toString();
        String usercity=city.getText().toString();
       // String users=username.getText().toString();
        String usermale=male.getText().toString();
        String blood=spinner.getSelectedItem().toString();




      //  sharedpreferences=getSharedPreferences(mypreference,MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
       // editor.putString(Name,user_name);
        editor.putString(EMAIL,usermail);
        editor.putString(CONTACT,usercontact);
        editor.putString(ADDRESS,useraddress);
        editor.putString(CITY,usercity);
        editor.putString(USERNAME,user_name);
        editor.putString(BLOOD,blood);
        editor.putString(GENDER,usermale);

        editor.commit();
        Toast.makeText(Registration.this,"Thanks",Toast.LENGTH_LONG).show();
    }
    private void add(){

        String user_name=name.getText().toString();
        String usermail=email.getText().toString();
        String usercontact=contact.getText().toString();
        String useraddress=address.getText().toString();
        String usercity=city.getText().toString();
        String users=username.getText().toString();
      //  String userpassword=password.getText().toString();
        String usermale=male.getText().toString();
        String userfemale=fenmale.getText().toString();
        String blood=spinner.getSelectedItem().toString();


        if (!TextUtils.isEmpty(user_name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseArtists.push().getKey();

           /* //creating an Artist Object
            Users artist = new Users(id, user_name,usermale,usermail,
                    usercontact,useraddress,usercity,users,blood);
*/
            //Saving the Artist
         //   databaseArtists.child(id).setValue(artist);

            //setting edittext to blank again
            name.setText("");

            //displaying a success toast
          /*  Toast.makeText(this, "Registered", Toast.LENGTH_LONG).show();

            Intent intent=new Intent(getApplicationContext(),Mydetails.class);
            Bundle b=new Bundle();
            b.putString("name",user_name);
            b.putString("email",usermail);
            b.putString("contact",usercontact);
            b.putString("address",useraddress);
            b.putString("city",usercity);
            b.putString("users",users);
            b.putString("male",usermale);
            intent.putExtras(b);*/


        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }

    }


