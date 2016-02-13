package com.dentalapp.dentalapp;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.List;

/**
 * Activity that displays the settings screen.
 */

public class MoreActivity extends Activity {

    //private List<Float> availableOptions = Application.getConfigHelper().getSearchDistanceAvailableOptions();

    ListView moreListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        moreListView = (ListView) findViewById(R.id.moreListView);

        /* MAYBE USE AN ADAPTER?


         // Defined Array values to show in ListView
            String[] values = new String[] { "Android List View",
                                             "Adapter implementation",
                                             "Simple List View In Android",
                                             "Create List View Android",
                                             "Android Example",
                                             "List View Source Code",
                                             "List View Array Adapter",
                                             "Android Example List View"
                                            };

            // Define a new Adapter
            // First parameter - Context
            // Second parameter - Layout for the row
            // Third parameter - ID of the TextView to which the data is written
            // Forth - the Array of data

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
              android.R.layout.simple_list_item_1, android.R.id.text1, values);


            // Assign adapter to ListView
            listView.setAdapter(adapter);
         */



        moreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;
                // ListView Clicked item value
                String  itemValue    = (String) moreListView.getItemAtPosition(position);

                switch (itemPosition) {
                    case 0:

                        startActivity(new Intent(MoreActivity.this, VideoPlayerActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MoreActivity.this, AboutActivity.class));
                        break;
                    case 2:
                        // Call the Parse log out method
                        ParseUser.logOut();
                        // Start and intent for the dispatch activity
                        Intent intent = new Intent(MoreActivity.this, DispatchActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;
                    default:
                        // Show Alert
                        Toast.makeText(getApplicationContext(),
                                "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                                .show();
                        break;
                }
            }

        });
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_more, menu);
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
