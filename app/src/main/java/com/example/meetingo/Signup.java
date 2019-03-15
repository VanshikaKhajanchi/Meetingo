package com.example.meetingo;

import android.app.Application;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    EditText fullname,phone,email,password;
    Button register_button;

    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("null");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_signup);

        fullname=findViewById(R.id.fullname);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        register_button=findViewById(R.id.register_button);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);






        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();

                myRef.setValue("HO JA YR3");
            }
        });



    }

   /* @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }*/



    public void validate(){
        final String emailid = email.getEditableText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(fullname.getText().toString().trim().length()==0)
        {
            fullname.setError("Enter Name");

        }
        else  if(phone.getText().toString().trim().length()==0)
        {
            phone.setError("Enter Phone Number");


        }
        else  if(phone.getText().toString().trim().length()<10)
        {
            phone.setError("Enter Valid Phone Number");


        }/*^[A-Za-z0-9._%+\-]+@[A-Za-z0-9.\-]+\.[A-Za-z]{2,4}*/
        else  if(!(emailid.matches(emailPattern)) && email.getText().toString().trim().length() < 0)
        {
            email.setError("Enter valid Email");

        }
        else if(password.getText().toString().trim().length()==0)
        {
            password.setError("Enter Password");
        }
        else if(password.getText().toString().trim().length()<=8)
        {
            password.setError("Password should be greater than 8 digits");
        }
        else {
            /*adduser(emailid,passwrd,Phone,name);*/

            progressBar.setVisibility(View.VISIBLE);
            final String fname=fullname.getText().toString();
            final String mail=email.getText().toString();
            final String ph=phone.getText().toString();
            final String pass=password.getText().toString();
            String id= myRef.push().getKey();
             User usr=new User(fname,mail,ph,pass);
             myRef.child("Users").child(id).setValue(usr);
            Intent a = new Intent(this, HomePage.class);
            startActivity(a);


        }

    }
/*

    private void adduser(final String email, final String password, String phone, String name) {

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            com.example.meetingo.User user = new User(
                                   z name,phone,email,password);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
*/


  /*  @Override
    public void onClick(View v) {

    }*/
}
