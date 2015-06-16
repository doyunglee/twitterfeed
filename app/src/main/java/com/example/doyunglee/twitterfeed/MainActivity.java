package com.example.doyunglee.twitterfeed;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;


public class MainActivity extends Activity {
    static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView browser = (WebView) findViewById(R.id.webview);

        Log.i(LOG_TAG, "About to start AuthService!!!!");
        Intent oAuthIntent = new Intent(this, AuthService.class);
        //msgIntent.putExtra(AuthService.PARAM_IN_MSG, strInputMsg);
        startService(oAuthIntent);
    }

}
