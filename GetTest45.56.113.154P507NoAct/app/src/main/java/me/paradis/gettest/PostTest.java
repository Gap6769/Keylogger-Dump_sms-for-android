package me.paradis.gettest;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PostTest extends AsyncTask<String, String, String> {


    @Override
    protected String doInBackground(String... params) {
        String url = "http://45.56.113.154:512"; // URL to call
        String data = "test"; //data to post
        try{
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", "UTF-8");

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.connect();

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
