package com.example.doyunglee.twitterfeed;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import java.util.Scanner;

/**
 * Created by doyunglee on 6/15/15.
 */
public class AuthService extends IntentService {

    private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";
    static final String LOG_TAG = AuthService.class.getSimpleName();

    public AuthService() {
        super("AuthService");
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        Log.i(LOG_TAG, "WE ARE IN IT TO WIN IT!");
        // Gets data from the incoming Intent
        String dataString = workIntent.getDataString();
        // Do work here, based on the contents of dataString
        // If you choose to use a callback, "oauth_verifier" will be the return value by Twitter (request param)
        OAuthService service = new ServiceBuilder()
                .provider(TwitterApi.class)
                .apiKey("GOHJL17on7MnH2cM2jAAeTYcf")
                .apiSecret("1ohQRpKtTEJWQ56PQ6if5juPQaI3Zbd7VoUCQMQaUrFEswkCE7")
                .build();
        Scanner in = new Scanner(System.in);

        System.out.println("=== Twitter's OAuth Workflow ===");

        // Obtain the Request Token
        System.out.println("Fetching the Request Token...");
        Token requestToken = service.getRequestToken();
        System.out.println("Got the Request Token!");

        System.out.println("Now go and authorize Scribe here:");
        System.out.println(service.getAuthorizationUrl(requestToken));

        //send broadcast to main activity.
        Intent broadcastIntent = new Intent();
        broadcastIntent.putExtra("String", service.getAuthorizationUrl(requestToken));
        sendBroadcast(broadcastIntent);

        System.out.println("broadcast sent!!!");
        //verifyAndOauth(in, service, requestToken);
    }

    public void verifyAndOauth(Scanner in, OAuthService service, Token requestToken){
        System.out.println("And paste the verifier here");
        System.out.print(">>");
        Verifier verifier = new Verifier(in.nextLine());
        System.out.println();

        // Trade the Request Token and Verfier for the Access Token
        System.out.println("Trading the Request Token for an Access Token...");
        Token accessToken = service.getAccessToken(requestToken, verifier);
        System.out.println("Got the Access Token!");
        System.out.println("(if you're curious, it looks like this: " + accessToken + " )");

        // Now let's go and ask for a protected resource!
        System.out.println("Now we're going to access a protected resource...");
        OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        service.signRequest(accessToken, request);
        Response response = request.send();
        System.out.println("Got it! Lets see what we found...");
        System.out.println(response.getBody());

        System.out.println();
        System.out.println("That's it man! Go and build something awesome with Scribe! :)");
    }
}
