package com.ntukhpi.otp.nadirian.lab02__;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class ViewActivity extends Activity {
        TextView tvView;
        TextView tvError;
    int fName;
    int lName;
    String symbol;
    int result=0;

    protected void onCreate(Bundle savedInstanceState)

    { super.onCreate(savedInstanceState); setContentView(R.layout.viewactivity);
    tvView = (TextView) findViewById(R.id.tvView);
        tvError = (TextView) findViewById(R.id.tvError);
    Intent intent = getIntent();
     fName = intent.getIntExtra("var1",0);
     lName = intent.getIntExtra("var2",0);
     symbol=intent.getExtras().getString("symbol");



       /*    switch (symbol) {
               case "*":
                   result = fName * lName;
                   break;
               case "/":
                   try {
                   result = fName / lName;
                   break;


       }
       catch (ArithmeticException e) {
           // Строки кода в catch будут выполнены.

           tvView.setText("Error: " + e.getMessage());

           // Строки кода в catch будут выполнены.
          // System.out.println("Ignore...");

       }
    }*/}
    public void result(View view){
        switch(view.getId()){
            case R.id.buttonRes:
                switch (symbol) {
                    case "*":
                        result = fName * lName;
                        break;
                    case "/":
                        try {
                            result = fName / lName;
                            break;


                        }
                        catch (ArithmeticException e) {
                            // Строки кода в catch будут выполнены.
                            tvError.setText("Error: " + e.getMessage());
                        }
                }
                tvView.setText("Your enter is: " + fName + symbol + lName +"="+result);

        }
    }
}