package com.example.doyunglee.twitterfeed;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ListActivity {
    static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //check logged in status
        if(!userLoggedIn()){
            finish();
            startLoginActivity();
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
