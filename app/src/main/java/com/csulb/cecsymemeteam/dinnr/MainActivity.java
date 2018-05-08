package com.csulb.cecsymemeteam.dinnr;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.DatabaseMetaData;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataStorage.generateRestaurants();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_restaurantPlaque);
        mDrawerLayout =(DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        updateProfile();
        if(DataStorage.isRestaurantSeleccted()){
            tintScreen(true);
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu nav_menu = navigationView.getMenu();

        //////////////////////////////////////////////////////////////////////////
        //                       reset Button Listeners                         //
        //////////////////////////////////////////////////////////////////////////
        ImageButton reset = findViewById(R.id.main_resetButton);
        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!DataStorage.getQueue().isEmpty()){
                    DataStorage.getListOfRestaurants().clear();
                    DataStorage.getListOfRestaurants().add(DataStorage.getQueue().get((int)(Math.random() * DataStorage.getQueue().size())));   //in line coding, the best coding :^)`
                    DataStorage.getQueue().clear();
                    DataStorage.setRestaurantSeleccted(true);
                    gotoProfile(findViewById(R.id.main_resetButton));
                    // Moves them to the profile screen
                }
                else{
                    DataStorage.setBeenGenerated(false);
                    if(DataStorage.isRestaurantSeleccted()){
                        tintScreen(false);
                    }
                    DataStorage.setRestaurantSeleccted(false);
                    ImageButton temp = findViewById(R.id.main_leftBtn);
                    temp.setEnabled(true);
                    temp = findViewById(R.id.main_rightBtn);
                    temp.setEnabled(true);
                    DataStorage.getListOfRestaurants().clear();
                    DataStorage.generateRestaurants();
                }
                updateProfile();
            }
        });
        //////////////////////////////////////////////////////////////////////////
        //                       reset Button Listeners                         //
        //////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////
        //                      profile Plaque Listeners                        //
        //////////////////////////////////////////////////////////////////////////
        if(DataStorage.isLoggedIn()) {
            nav_menu.findItem(R.id.Account).setVisible(true);
            nav_menu.findItem(R.id.newAccount).setVisible(false);
            nav_menu.findItem(R.id.login).setVisible(false);
        }
        linearLayout.setOnTouchListener(new GestureListener(MainActivity.this)
        {
            @Override
            public void onSwipeRight()
            {
                if(!DataStorage.getListOfRestaurants().empty() && !DataStorage.isRestaurantSeleccted()) {
                    DataStorage.getQueue().add(DataStorage.getListOfRestaurants().pop());

                }
                updateProfile();
            }
            public void onSwipeLeft()
            {
                if(!DataStorage.getListOfRestaurants().empty() && !DataStorage.isRestaurantSeleccted()) {
                    DataStorage.getListOfRestaurants().pop();
                    if(DataStorage.getListOfRestaurants().empty()){
                    }
                }
                updateProfile();
            }
        });
        //////////////////////////////////////////////////////////////////////////
        //                      profile Plaque Listeners                        //
        //////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////
        //                        like Button Listeners                         //
        //////////////////////////////////////////////////////////////////////////
        ImageButton like =(ImageButton) findViewById(R.id.main_rightBtn);
        if(DataStorage.isRestaurantSeleccted()){
            like.setEnabled(false);
        }
        like.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!DataStorage.getListOfRestaurants().empty()) {
                    DataStorage.getQueue().add(DataStorage.getListOfRestaurants().pop());
                }
                updateProfile();
            }
        });
        //////////////////////////////////////////////////////////////////////////
        //                        like Button Listeners                         //
        //////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////
        //                      dislike Button Listeners                        //
        //////////////////////////////////////////////////////////////////////////
        ImageButton dislike =(ImageButton) findViewById(R.id.main_leftBtn);
        if(DataStorage.isRestaurantSeleccted()) {
            dislike.setEnabled(false);
        }
        dislike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!DataStorage.getListOfRestaurants().empty() && !DataStorage.isRestaurantSeleccted()) {
                    DataStorage.getListOfRestaurants().pop();
                    if(DataStorage.getListOfRestaurants().empty()){
                    }
                }
                updateProfile();
            }
        });
        //////////////////////////////////////////////////////////////////////////
        //                      dislike Button Listeners                        //
        //////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////
        //                      Profile Button Listeners                        //
        //////////////////////////////////////////////////////////////////////////
        ImageButton profile = (ImageButton) findViewById(R.id.main_profileBtn);
        profile.setOnTouchListener(new GestureListener(MainActivity.this){
            @Override
            public void onSwipeRight()
            {
                if(!DataStorage.getListOfRestaurants().empty() && !DataStorage.isRestaurantSeleccted()) {
                    DataStorage.getQueue().add(DataStorage.getListOfRestaurants().pop());
                }
                updateProfile();
            }
            public void onSwipeLeft()
            {
                if(!DataStorage.getListOfRestaurants().empty() && !DataStorage.isRestaurantSeleccted()) {
                    DataStorage.getListOfRestaurants().pop();
                    if(DataStorage.getListOfRestaurants().empty()){
                    }
                }
                updateProfile();
            }

            @Override
            public void onTap() {
                if(!DataStorage.getListOfRestaurants().empty()) {
                    gotoProfile(findViewById(R.id.main_profileBtn));
                }
            }
        });
        //////////////////////////////////////////////////////////////////////////
        //                      Profile Button Listeners                        //
        //////////////////////////////////////////////////////////////////////////


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateProfile(){
        if(!DataStorage.getListOfRestaurants().empty()){
            ImageView []setStar = {findViewById(R.id.main_star1),findViewById(R.id.main_star2),findViewById(R.id.main_star3),
                    findViewById(R.id.main_star4),findViewById(R.id.main_star5)};
            ImageButton imgBtn = (ImageButton) findViewById(R.id.main_profileBtn);
            imgBtn.setImageResource(DataStorage.getRestaurant().getRefToImg());
            TextView temp = (TextView) findViewById(R.id.main_restaurantName);
            temp.setText(DataStorage.getRestaurant().getName());

            temp = findViewById(R.id.main_restaurantDistance);
            temp.setText(DataStorage.getRestaurant().getDistance());


            for(int i = 0;i < DataStorage.getRestaurant().getStarRating();i++)
            {
                setStar[i].setImageResource(android.R.drawable.btn_star_big_on);

            }
            if(DataStorage.getRestaurant().getStarRating() <5)
            {
                for(int j=4;j>=DataStorage.getRestaurant().getStarRating();j--)
                {
                    setStar[j].setImageResource(android.R.drawable.btn_star_big_off);
                }
            }
            // ImageView star = (ImageView)findViewById(R.id.main_star1);
            // star.setImageResource(android.R.drawable.btn_star_big_on);
        }
        else{
            noMoreRestaurants();
        }
    }

    public void noMoreRestaurants(){
        ImageView []setStar = {findViewById(R.id.main_star1),findViewById(R.id.main_star2),findViewById(R.id.main_star3),
                findViewById(R.id.main_star4),findViewById(R.id.main_star5)};
        ImageButton imgBtn = (ImageButton) findViewById(R.id.main_profileBtn);
        if(DataStorage.getListOfRestaurants().size() > 0){
            DataStorage.getListOfRestaurants().remove(0);
        }
        imgBtn.setImageResource(R.drawable.no_more_restaurants);
        TextView temp = findViewById(R.id.main_restaurantName);
        temp.setText("");

        temp = findViewById(R.id.main_restaurantDistance);
        temp.setText("");

        for(int j = 0; j < 5; j++)
        {
            setStar[j].setImageResource(android.R.drawable.btn_star_big_off);
        }
    }

    public void gotoFavorites(View v){
        startActivity(new Intent(this, FavoritesActivity.class));
    }

    public void gotoProfile(View v){
        startActivity(new Intent(this, ProfileActivity.class));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.login) {
            // Toast.makeText(this,"login",Toast.LENGTH_SHORT.show());
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (id == R.id.newAccount) {
            // Toast.makeText(this,"login",Toast.LENGTH_SHORT.show());
            startActivity(new Intent(this, NewAccountActivity.class));
        }
        if (id == R.id.Favorite)
        {
            startActivity(new Intent(this, FavoritesActivity.class));
        }
        return false;
    }

    public void tintScreen(boolean on){
        ImageView temp = findViewById(R.id.main_screenTint);
        if(on){
            temp.setVisibility(View.VISIBLE);
            temp = findViewById(R.id.main_screenTint2);
            temp.setVisibility(View.VISIBLE);
            temp = findViewById(R.id.main_screenTint3);
            temp.setVisibility(View.VISIBLE);
        }
        else{
            temp.setVisibility(View.INVISIBLE);
            temp = findViewById(R.id.main_screenTint2);
            temp.setVisibility(View.INVISIBLE);
            temp = findViewById(R.id.main_screenTint3);
            temp.setVisibility(View.INVISIBLE);
        }
    }
}
