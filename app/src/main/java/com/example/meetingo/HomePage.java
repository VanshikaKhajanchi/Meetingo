package com.example.meetingo;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        //BottomNavigation
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        //Bottom Navigation-----loading the default fragment
        loadFragment(new ChatFragment());



        //Side NAvigattion
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    //Bottom btm_navigation
    private boolean loadFragment(Fragment fragment){
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout,fragment)
                    .commit();

            return true;

        }
        return false;
    }

    //Side NAvigation
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //Side NAvigation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Bttom Navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {

            case R.id.bottom_Chat:
                fragment = new ChatFragment();
                break;

            case R.id.bottom_status:
              Intent intent=new Intent();
              intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
              startActivity(intent);
                break;

            case R.id.bottom_audiocall:
                openactivity();

                break;


            case R.id.nav_videocall:
                Intent vintent = new Intent(HomePage.this, LoginActivity.class);
                startActivity(vintent);

                break;


            case R.id.drawer_layout:
                DrawerLayout drawer =findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                break;

        }

        return loadFragment(fragment);
    }

    private void openactivity() {
        Intent c_intent = new Intent(HomePage.this, CallFragment.class);
        startActivity(c_intent);

    }

    /* @SuppressWarnings("StatementWithEmptyBody")*/
   /* @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle btm_navigation view item clicks here.
        int id = item.getItemId();

       *//* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*//*

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
}
