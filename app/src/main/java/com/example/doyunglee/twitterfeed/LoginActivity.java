package com.example.doyunglee.twitterfeed;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.media.session.MediaSession;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.content.Intent;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class LoginActivity extends Activity {

    static final String LOG_TAG = LoginActivity.class.getSimpleName();
    private TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        final Button login_button = (Button) findViewById(R.id.login_button);
//        final EditText username_text = (EditText) findViewById(R.id.username);
//        final EditText password_text = (EditText) findViewById(R.id.password);

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                Log.i(LOG_TAG, "SUCCESS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                startMainActivity();
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Log.i(LOG_TAG, "FAILURE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        });

//        login_button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//                Log.i(LOG_TAG, username_text.getText().toString());
//                Log.i(LOG_TAG, password_text.getText().toString());
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    //Open Main Activity
    public void startMainActivity(){

        Intent startMainIntent = new Intent(this, MainActivity.class);
        startActivity(startMainIntent);
    }
}
