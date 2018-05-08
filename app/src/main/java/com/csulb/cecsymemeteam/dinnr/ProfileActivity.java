package com.csulb.cecsymemeteam.dinnr;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.WrapperListAdapter;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        LinearLayout scrollView = findViewById(R.id.profile_reviewsContainer);

        for(int i = 0; i < DataStorage.getListOfReviews().size(); i++){
            scrollView.addView(ReviewFactory.generateReview(this, DataStorage.getListOfReviews().get(i), i), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        }

        LinearLayout restaurantImg = findViewById(R.id.profile_restaurantLayout);
        restaurantImg.setBackgroundResource(DataStorage.getRestaurant().getRefToImg());
        ImageView []setStar = {findViewById(R.id.profile_star1),findViewById(R.id.profile_star2),findViewById(R.id.profile_star3), findViewById(R.id.profile_star4), findViewById(R.id.profile_star5)};
        for(int i = 0;i < DataStorage.getListOfRestaurants().peek().getStarRating();i++)
        {
            setStar[i].setImageResource(android.R.drawable.btn_star_big_on);
        }
        if(DataStorage.getListOfRestaurants().peek().getStarRating() <5)
        {
            for(int j=4; j>=DataStorage.getListOfRestaurants().peek().getStarRating(); j--)
            {
                setStar[j].setImageResource(android.R.drawable.btn_star_big_off);
            }
        }

        TextView temp = findViewById(R.id.profile_restaurantName);
        temp.setText(DataStorage.getRestaurant().getName());

        temp = findViewById(R.id.profile_restaurantDistance);
        temp.setText(DataStorage.getRestaurant().getDistance());

        temp = findViewById(R.id.profile_addressTxt);
        String addressSnip = DataStorage.getRestaurant().address;
        for(int i = 0; i < addressSnip.length(); i++){
            if(addressSnip.charAt(i) == ','){
                addressSnip = addressSnip.substring(0, i);
                break;
            }
        }
        temp.setText(addressSnip);

    }



    /**
     * Directs users to the write review activity
     * @param v
     */
    public void writeReview(View v){
        startActivity(new Intent(this, WriteReviewActivity.class));
    }

    /**
     * Directs users to another page where they can see reviews more clearly
     * @param v
     */
    public void gotoMoreReviews(View v){
        startActivity(new Intent(this, MoreReviewsActivity.class));
    }

    public void directToMaps(View v){
        String url = "https://www.google.com/maps/dir/?api=1&origin=Current+Location";
        String temp = DataStorage.getRestaurant().address;
        for(int i = 0; i < temp.length(); i++){
            if(temp.charAt(i) == ' '){
                temp = temp.substring(0, i) + "+" + temp.substring(i+1, temp.length());
            }
            else if(temp.charAt(i) == ','){
                temp = temp.substring(0, i) + "%2C" + temp.substring(i+1, temp.length());
            }
        }
        url += "&destination=" + temp;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    /**
     * Directs users back to the main activity
     * @param v
     */
    public void goBack(View v){
        startActivity(new Intent(this, MainActivity.class));
    }
}
