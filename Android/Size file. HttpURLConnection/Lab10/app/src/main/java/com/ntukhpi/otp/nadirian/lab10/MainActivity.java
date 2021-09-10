package com.ntukhpi.otp.nadirian.lab10;

import androidx.appcompat.app.AppCompatActivity;

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
private static final String TAG = "myLogs";

  private void show() throws IOException {
   URL url = new URL("http://home.mcom.com");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Accept-Encoding", "identity"); // <--- Add this line
      final int length = (int) connection.getContentLengthLong ();
      text_size.setText("The size of file is = " + String.valueOf(length) + "bytes");
      String tag;
      Log.d(TAG, String.valueOf(length));
   }
}
