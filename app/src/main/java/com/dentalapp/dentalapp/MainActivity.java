package com.dentalapp.dentalapp;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    Button flossButton, brushButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        brushButton = (Button) findViewById(R.id.brushButton);
        brushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, VideoPlayerActivity.class);
                intent.putExtra("video","android.resource://" + getPackageName() + "/" + R.raw.video1);
                startActivity(intent);
            }
        });
        flossButton = (Button) findViewById(R.id.flossButton);
        flossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, VideoPlayerActivity.class);
                intent.putExtra("video","android.resource://" + getPackageName() + "/" + R.raw.video2);
                startActivity(intent);
            }
        });

        /*
        Drawable drawable = getResources().getDrawable(R.drawable.play_green);
        drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*0.5),
                (int)(drawable.getIntrinsicHeight()*0.5));
        ScaleDrawable sd = new ScaleDrawable(drawable, 0, .4f, .4f);
        flossButton.setCompoundDrawables(sd.getDrawable(), null, null, null);

        drawable = getResources().getDrawable(R.drawable.play_blue);
        drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*0.5),
                (int)(drawable.getIntrinsicHeight()*0.5));
        sd = new ScaleDrawable(drawable, 0, .4f, .4f);
        Button brushButton = (Button) findViewById(R.id.brushButton);
        brushButton.setCompoundDrawables(sd.getDrawable(), null, null, null);*/
        setupActionBar();
    }

    private void setupActionBar() {
        ActionBar ab = getActionBar();
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        ab.setIcon(R.drawable.ic_list_black_36dp);
        LayoutInflater inflator = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_menu_main, null);
        ab.setCustomView(v);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            startActivity(new Intent(MainActivity.this, MoreActivity.class));
        } else if (id == R.id.alarm_list_action) {
            startActivity(new Intent(MainActivity.this, AlarmListActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
