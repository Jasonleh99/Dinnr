package com.csulb.cecsymemeteam.dinnr;

import android.widget.ImageView;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.Stack;

/*
    Lmao this is just a dummy class to hold all the runtime variables and makes it easier for activities to access them outside of doing troublesome stuff like using an actual database or using intents
 */
public class DataStorage {
    // Describes whether the user has logged in to the app and whether or not they have access to the favorites functionality
    private static boolean loggedIn = false;
    // Describes whether the list of restaurants has already been generated
    private static boolean beenGenerated = false;
    // Describes whether or not the reset button has been used to select a restaurant already
    private static boolean restaurantSeleccted = false;
    private static String loginName = "";
    private static Stack<RestaurantProfile> listOfRestaurants = new Stack<>();
    private static ArrayList<RestaurantProfile> queue = new ArrayList<>();


    public static void generateRestaurants(){
        if(!beenGenerated) {
            //Mcdonalds should have 2 reviews
            listOfRestaurants.push(new RestaurantProfile("Mcdonalds", "2290 N Bellflower Blvd, Long Beach, CA 90815", "0.5 Miles", 4, R.drawable.mcdonalds));
            listOfRestaurants.peek().generateGenericReviews();

            //Tacobell should have 4 reviews
            listOfRestaurants.push(new RestaurantProfile("Taco Bell", "6407 Stearns St, Long Beach, CA 90815", "2.0 Miles", 3, R.drawable.innnout));
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();

            //Inn and out should have 3 reviews
            listOfRestaurants.push(new RestaurantProfile("Inn N Out", "4600 E Los Coyotes Diagonal, Long Beach, CA 90815", "0.2 Miles", 5, R.drawable.tacobell));
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();

            //Subway
            listOfRestaurants.push(new RestaurantProfile("Subway", "1212 N Bellflower Blvd, Long Beach, CA 90815", "0.4 Miles", 4, R.drawable.subway));
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();


            //KFC
            listOfRestaurants.push(new RestaurantProfile("KFC", "2970 N Bellflower Blvd, Long Beach, CA 90815", "1.4 Miles", 2, R.drawable.kfc));
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();

            //Burger King
            listOfRestaurants.push(new RestaurantProfile("Burger King", "2955 N Bellflower Blvd, Long Beach, CA 90815", "0.5 Miles", 4, R.drawable.burger_king));
            listOfRestaurants.peek().generateGenericReviews();

            //Pizza Hut
            listOfRestaurants.push(new RestaurantProfile("Pizza Hut", "4502 Los Coyotes Rd, Long Beach, CA 90815", "0.8 Miles", 3, R.drawable.pizza_hut));
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();
            //Starbucks
            listOfRestaurants.push(new RestaurantProfile("Starbucks", "6049 E 7th St, Long Beach, CA 90840", "1.1 Miles", 4, R.drawable.starbucks));
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();
            //Wendy's
            listOfRestaurants.push(new RestaurantProfile("Wendy's", "2201 N Bellflower Blvd, Long Beach, CA 90815", "1.7 Miles", 2, R.drawable.wendys));
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();
            //Dunkin' Donuts
            listOfRestaurants.push(new RestaurantProfile("Dunkin' Donuts", "5560 E 7th St, Long Beach, CA 90804", "1.3 Miles", 3, R.drawable.dunkind));
            listOfRestaurants.peek().generateGenericReviews();
            //Chick-fil-A
            listOfRestaurants.push(new RestaurantProfile("Chick-fil-A", "7681 Carson Blvd , Long Beach , CA 90808", "2.1 Miles", 4, R.drawable.chickfila));
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();
            //Arby's
            listOfRestaurants.push(new RestaurantProfile("Arby's", "3602 Atlantic Ave, Long Beach, CA 90807", "2.2 Miles", 4, R.drawable.arby));
            listOfRestaurants.peek().generateGenericReviews();
            //Chipotle Mexican Grill
            listOfRestaurants.push(new RestaurantProfile("Chipotle Mexican Grill", "1800 Ximeno Ave, Long Beach, CA 90815", "1.8 Miles", 5, R.drawable.chipotle));
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();
            //Papa John's
            listOfRestaurants.push(new RestaurantProfile("Papa John's", "2836 N Bellflower Blvd, Long Beach, CA 90815", "0.8 Miles", 3, R.drawable.papaj));
            listOfRestaurants.peek().generateGenericReviews();
            //Hardee's
            listOfRestaurants.push(new RestaurantProfile("Hardee's", "2551 Harrison Ave, Butte, MT 59701", "0.6 Miles", 2, R.drawable.hardee));
            listOfRestaurants.peek().generateGenericReviews();
            //Popeyes Louisiana Kitchen
            listOfRestaurants.push(new RestaurantProfile("Popeyes Louisiana Kitchen", "21700 Norwalk Blvd, Hawaiian Gardens, CA 90716", "1.9 Miles", 5, R.drawable.popeye));
            listOfRestaurants.peek().generateGenericReviews();
            //Wingstop
            listOfRestaurants.push(new RestaurantProfile("Wingstop", "1806 Ximeno Ave, Long Beach, CA 90815", "2.6 Miles", 4, R.drawable.wingstop));
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();
            //Panda Express
            listOfRestaurants.push(new RestaurantProfile("Panda Express", "CSU LONG BEACH PX, 6049 E 7th St,, Long Beach, CA 90840", "0.3 Miles", 3, R.drawable.pandae));
            listOfRestaurants.peek().generateGenericReviews();
            //Domino's Pizza
            listOfRestaurants.push(new RestaurantProfile("Domino's Pizza", "2221 Palo Verde Ave Unit B, Long Beach, CA 90815", "2.5 Miles", 2, R.drawable.domino));
            listOfRestaurants.peek().generateGenericReviews();
            //Dairy Queen
            listOfRestaurants.push(new RestaurantProfile("Dairy Queen", "12510 Valley View St, Garden Grove, CA 92845", "2.6 Miles", 2, R.drawable.dairyqueen));
            listOfRestaurants.peek().generateGenericReviews();
            //Panera Bread
            listOfRestaurants.push(new RestaurantProfile("Panera Bread", "12241 Seal Beach Blvd, Seal Beach, CA 90740", "1.2 Miles", 3, R.drawable.panera));
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();
            //Sonic Drive-In
            listOfRestaurants.push(new RestaurantProfile("Sonic Drive-In", "17811 Beach Blvd Ste. 101, Huntington Beach, CA 92647", "3.5 Miles", 4, R.drawable.sonic));
            listOfRestaurants.peek().generateGenericReviews();
            listOfRestaurants.peek().generateGenericReviews();

            //Jack in the Box
            listOfRestaurants.push(new RestaurantProfile("Jack in the Box", "5601 Pacific Coast Hwy, Long Beach, CA 90804", "0.5 Miles", 4, R.drawable.jack));
            listOfRestaurants.peek().generateGenericReviews();
            beenGenerated = true;

        }
    }

    public static ArrayList<Review> getListOfReviews(){
        return listOfRestaurants.peek().getListOfReviews();
    }

    public static RestaurantProfile getRestaurant(){
        return listOfRestaurants.peek();
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
        DataStorage.loggedIn = loggedIn;
    }

    public static String getLoginName() {
        return loginName;
    }

    public static void setLoginName(String loginName) {
        DataStorage.loginName = loginName;
    }

    public static Stack<RestaurantProfile> getListOfRestaurants() {
        return listOfRestaurants;
    }

    public static void setListOfRestaurants(Stack<RestaurantProfile> listOfRestaurants) {
        DataStorage.listOfRestaurants = listOfRestaurants;
    }

    public static ArrayList<RestaurantProfile> getQueue() {
        return queue;
    }

    public static void setQueue(ArrayList<RestaurantProfile> queue) {
        DataStorage.queue = queue;
    }

    public static boolean isBeenGenerated() {
        return beenGenerated;
    }

    public static void setBeenGenerated(boolean beenGenerated) {
        DataStorage.beenGenerated = beenGenerated;
    }

    public static boolean isRestaurantSeleccted() {
        return restaurantSeleccted;
    }

    public static void setRestaurantSeleccted(boolean restaurantSeleccted) {
        DataStorage.restaurantSeleccted = restaurantSeleccted;
    }
}
