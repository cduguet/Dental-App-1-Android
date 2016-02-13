package com.dentalapp.dentalapp;

import android.app.Application;
import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseCrashReporting;
import com.parse.ParseFacebookUtils;

/**
 * Created by cristian on 8/3/15.
 */



public class DentalAppApplication extends Application {

    /*
    public static SQLiteDatabase db;
    public static SharedPreferences sp;
    */

    @Override
    public void onCreate() {
        super.onCreate();
        ParseCrashReporting.enable(this);
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "cb6Walyl86WGebjFMK7retuFjMESdf7mvKHolcen", "c8WRClRF44O3cCNV9SEeKjNwlSDOMGvM1rkYTV6F");

        FacebookSdk.sdkInitialize(getApplicationContext());
        ParseFacebookUtils.initialize(this);

        // For the Alarms
        /*
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        sp = PreferenceManager.getDefaultSharedPreferences(this);

        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();

        */
    }

    /*
    public static String getRingtone() {
        return sp.getString(RemindMe.RINGTONE_PREF, DEFAULT_NOTIFICATION_URI.toString());
    }
    */
}
