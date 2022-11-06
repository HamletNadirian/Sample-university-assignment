package com.ntukhpi.otp.nadirian.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    TextView text_size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_size = findViewById(R.id.text_size);
      new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    show();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
  @SuppressLint("SetTextI18n")
  private void show() throws IOException {
   URL url = new URL("https://nafsk.se/pipermail/dcml/1995-April/003883.html");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Accept-Encoding", "identity"); 
      final int length = (int) connection.getContentLengthLong ();
      final String convertSizeToString = String.valueOf(length);
      text_size.post(new Runnable() {
          public void run() {
              text_size.setText(convertSizeToString);
          }
      });
   }
}
