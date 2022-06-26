package me.paradis.gettest;

import static android.Manifest.permission.INTERNET;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new GetTest().execute("test");

        Toast.makeText(getApplicationContext(), "TEXT HERE", Toast.LENGTH_LONG).show();
    }
}


