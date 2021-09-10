package com.ntukhpi.otp.nadirian.labeight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.language);
        String get_languagename = Locale.getDefault().getDisplayLanguage();
        String get_country = Locale.getDefault().getCountry();
        String mystring_country = getResources().getString(R.string.country);
        String mystring_lang = getResources().getString(R.string.lang);

        textView.setText(mystring_country+get_country+ "\n" +mystring_lang +get_languagename);
    }
}
