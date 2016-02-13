package com.dentalapp.dentalapp;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.List;

public class WelcomeActivity extends Activity {

    Button fbLoginButton, emailLoginButton;

    final List<String> permissions = Arrays.asList("public_profile", "email");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        fbLoginButton = (Button) findViewById(R.id.fbLoginButton);
        emailLoginButton = (Button) findViewById(R.id.emailLoginButton);
        setButtonOnClickListener();
    }


    private void setButtonOnClickListener() {
        fbLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set up a progress dialog
                /*
                final ProgressDialog dialog = new ProgressDialog(WelcomeActivity.this);
                dialog.setMessage(getString(R.string.wait));
                dialog.show();*/
                ParseFacebookUtils.logInWithReadPermissionsInBackground(WelcomeActivity.this, permissions, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException err) {
                        if (user == null) {

                            Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                            Toast.makeText(WelcomeActivity.this, "Login Cancelled", Toast.LENGTH_LONG).show();
                        } else if (user.isNew()) {
                            Log.d("MyApp", "User signed up and logged in through Facebook!");
                            login();

                            Toast.makeText(WelcomeActivity.this, "Welcome!", Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("MyApp", "User logged in through Facebook!");
                            login();

                            Toast.makeText(WelcomeActivity.this, "Welcome Back!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        emailLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start an intent for the dispatch activity
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        // Start an intent for the dispatch activity
        Intent intent = new Intent(WelcomeActivity.this, DispatchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    // From Parse Facebook LoginActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
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
    */
}
