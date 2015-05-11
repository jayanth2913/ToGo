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

public class DisplayRestaurantsViewActivity extends Activity implements YelpResponse {
    private Map<String,List<String>> restaurantsMapByZipCode;
    protected YelpApi yHelper = null;

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
        TextView text = (TextView)findViewById(R.id.resultListByZip);
        text.setText("Search Results For " + zipCode);
        yHelper =new YelpApi();
        yHelper.yelpResponse=this;
        yHelper.execute("indian restaurants", zipCode);

    }

    @Override

    public void processYelpResponse(List<String> results){
        ListView listview = (ListView) findViewById(R.id.restaurantList);
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, android.R.id.text1,results);
        listview.setAdapter(adapter);
    }

}