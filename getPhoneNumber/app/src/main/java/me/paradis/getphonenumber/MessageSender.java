package me.paradis.getphonenumber;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageSender extends AsyncTask<String, Void, Void> {

    Socket s;
    DataOutputStream dos;
    PrintWriter pw;

    @Override
    protected Void doInBackground(String... voids) {
        String ip = "45.56.113.154";
        int port = 506;
        try {
            System.out.println("trying");

            s = new Socket(ip, port);
            pw = new PrintWriter(s.getOutputStream());
            pw.write(voids[0]);
            pw.flush();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
