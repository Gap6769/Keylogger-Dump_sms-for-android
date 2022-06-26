package me.paradis.gettest;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

public class GetTest extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... Voids) {

        try {
            System.out.println("trying get");
            new URL("http://45.56.113.154:507").openConnection().connect();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
