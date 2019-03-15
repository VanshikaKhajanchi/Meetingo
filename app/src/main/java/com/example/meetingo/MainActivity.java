package com.example.meetingo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button Login_button;
    private Button Signup_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*open SignUp Activity*/
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensignup();
            }
        });
            /*Open LOGIN  Activity*/
        Login_button=findViewById(R.id.button_login);
        Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlogin();
            }
        });
    }

    private void opensignup() {
        Intent intent=new Intent(this,RegisterUser.class);
        startActivity(intent);
    }

    private void openlogin() {
        Intent vintent=new Intent(this,LoginActivity.class);
        startActivity(vintent);
    }
}
