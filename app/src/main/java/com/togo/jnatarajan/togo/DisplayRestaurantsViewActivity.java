package com.togo.jnatarajan.togo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.togo.jnatarajan.togo.MyActivity;
import com.togo.jnatarajan.togo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayRestaurantsViewActivity extends Activity {
    private Map<String,List<String>> restaurantsMapByZipCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_restaurants_view);
        getRestaurantsByZipCode(getIntent());

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

    private void getRestaurantsByZipCode(Intent intent){
        String zipCode = intent.getStringExtra(MyActivity.EXTRA_MESSAGE);
        initMap(zipCode);
        TextView text = (TextView)findViewById(R.id.resultListByZip);
        text.setText("Search Results For " + zipCode);
            List<String> restaurantResults = restaurantsMapByZipCode.get(zipCode);
            ListView listview = (ListView) findViewById(R.id.restaurantList);
            listview.setContentDescription("Search Results For" + zipCode);
            final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, android.R.id.text1,restaurantResults);
            listview.setAdapter(adapter);
            YelpApi yelpHelper =new YelpApi();
            yelpHelper.execute(new String[]{"indian restaurants",zipCode});

    }
    /*
    This map should be populated with the actual list of restaurants by making a query with the zipcode to a DB or external service to retrieve restaurants.
     */
    private void initMap(String zipCode){
        restaurantsMapByZipCode = new HashMap<String,List<String>>();
        List<String>restaurants = new ArrayList<String>();
        restaurants.add("Madras Cafe");
        restaurants.add("Taste Buds");
        restaurantsMapByZipCode.put(zipCode,restaurants);
    }
}