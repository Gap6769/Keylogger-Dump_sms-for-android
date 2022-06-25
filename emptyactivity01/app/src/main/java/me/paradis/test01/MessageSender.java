package me.paradis.test01;

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
    protected Void doInBackground(String... voids)
    {
        String ip = "45.56.113.154";
        int port = 506;

        System.out.println("working on background");

        String message = voids[0];
        try
        {
            System.out.println("trying");

            System.out.println("p0");
            s = new Socket(ip, port);
            System.out.println("p1");
            pw =  new PrintWriter(s.getOutputStream());
            System.out.println("p2");
            // $#"#$ password
            pw.write(message.concat("\n"));
            System.out.println("p3");
            pw.flush();
            System.out.println("p4");
            s.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            // otro intento
        }
        return null;
    }
}
