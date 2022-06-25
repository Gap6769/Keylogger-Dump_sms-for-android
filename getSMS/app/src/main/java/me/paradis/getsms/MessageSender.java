package me.paradis.getsms;

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
        String ip = "10.0.2.2";
        int port = 505;
        try {
            System.out.println("trying");

            s = new Socket(ip, port);
            pw = new PrintWriter(s.getOutputStream());
            String mess = voids[0];
            mess = "$#$ " + mess + "\n";
            pw.write(mess);
            pw.flush();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
