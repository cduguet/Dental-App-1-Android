package com.dentalapp.dentalapp;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class AlarmListActivity extends ListActivity {

    private AlarmListAdapter mAdapter;
    private AlarmDBHelper dbHelper = new AlarmDBHelper(this);
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        requestWindowFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_alarm_list);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        mAdapter = new AlarmListAdapter(this, dbHelper.getAlarms());

        setListAdapter(mAdapter);

        if (mAdapter.getCount()==0) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.no_alarms_title))
                    .setMessage(getString(R.string.no_alarms_message))
                    .setPositiveButton(getString(R.string.go_schedule), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startAlarmDetailsActivity(-1);
                        }
                    })
                    .setNegativeButton(getString(R.string.Cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(R.drawable.clock_blue)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_alarm_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_new_alarm: {
                startAlarmDetailsActivity(-1);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            mAdapter.setAlarms(dbHelper.getAlarms());
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setAlarmEnabled(long id, boolean isEnabled) {
        AlarmManagerHelper.cancelAlarms(this);
        AlarmModel model = dbHelper.getAlarm(id);
        model.isEnabled = isEnabled;
        dbHelper.updateAlarm(model);
        AlarmManagerHelper.setAlarms(this);
    }

    public void startAlarmDetailsActivity(long id) {
        Intent intent = new Intent(this, AlarmDetailsActivity.class);
        intent.putExtra("id", id);
        startActivityForResult(intent, 0);
    }

    public void deleteAlarm(long id) {
        final long alarmId = id;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please confirm")
                .setTitle("Delete set?")
                .setCancelable(true)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Cancel Alarms
                        AlarmManagerHelper.cancelAlarms(mContext);
                        //Delete alarm from DB by id
                        dbHelper.deleteAlarm(alarmId);
                        //Refresh the list of the alarms in the adaptor
                        mAdapter.setAlarms(dbHelper.getAlarms());
                        //Notify the adapter the data has changed
                        mAdapter.notifyDataSetChanged();
                        //Set the alarms
                        AlarmManagerHelper.setAlarms(mContext);
                    }
                }).show();
    }

}
