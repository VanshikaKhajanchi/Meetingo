package com.example.meetingo;

import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BottomNavigation extends AppCompatActivity
       /* implements BottomNavigationView.OnNavigationItemSelectedListener*/{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
/*
        //BottomNavigation
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);

        //loading the default fragment
        loadFragment(new ChatFragment());*/
    }

   /* private boolean loadFragment(Fragment fragment){
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment)
                    .commit();

            return true;

        }
        return false;
    }*/

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;


        switch (item.getItemId()) {

            case R.id.bottom_Chat:
                fragment = new ChatFragment();
                break;

            case R.id.bottom_status:
                fragment = new StatusFragment();
                break;

            case R.id.bottom_audiocall:
                fragment = new CallFragment();
                break;
        }

        return loadFragment(fragment);
    }*/
}
