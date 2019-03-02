package com.example.meetingo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button Login_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*open NAvigation Activity*/
     /*   button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });*/
            /*Open Bottom Activity*/
        Login_button=findViewById(R.id.button_login);
        Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbottomnavi();
            }
        });
    }

    private void openbottomnavi() {
        Intent intent=new Intent(this,BottomNavigation.class);
        startActivity(intent);
    }

    private void openActivity2() {
        Intent intent=new Intent(this,HomePage.class);
        startActivity(intent);
    }
}
