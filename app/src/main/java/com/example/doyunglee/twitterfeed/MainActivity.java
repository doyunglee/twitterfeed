package com.example.doyunglee.twitterfeed;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;


public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "HwiPDI5bhNA41rUpCwkG6lBBw";
    private static final String TWITTER_SECRET = "P4Fwf7WtTbxs5W3sdKLBPZiUaHcZAKPDHOJZnSsOWl5fjDkSaB";

    static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        Log.i(LOG_TAG, "We in the MainActivity");

        //check logged in status
        if(!userLoggedIn()){
            Log.i(LOG_TAG, "userLoggedIn() if statement we are in.");
            finish();
            startLoginActivity();
            return;
        }
    }

    //Check if the user has been checked-in or not
    public boolean userLoggedIn(){
        //the default status is false.
        boolean status = getIntent().getBooleanExtra("LOGGED_IN", false);
        return status;
    }

    //Open Login Activity
    public void startLoginActivity(){
        Intent startLoginIntent = new Intent(this, LoginActivity.class);
        startActivity(startLoginIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
