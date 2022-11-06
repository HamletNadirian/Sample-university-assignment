package com.ntukhpi.otp.nadirian.lab02__;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URISyntaxException;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String oper[]={"*","/"};
    EditText numberOne;
    EditText numberTwo;
    Button button;
    TextView textView;
    String enter="=";
    int var1=0;
    int var2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numberOne = (EditText) findViewById(R.id.editText);
                numberTwo = (EditText) findViewById(R.id.editText2);
                var1 = Integer.parseInt(numberOne.getText() + "");
                var2 = Integer.parseInt(numberTwo.getText() + "");
                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                intent.putExtra("var1", var1);
                intent.putExtra("var2", var2);
                intent.putExtra("symbol", enter.toString());
                startActivity(intent);
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, oper);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this, "Position " +position,Toast.LENGTH_SHORT).show();
                        switch (position) {
                            case 0:
                                enter="*";
                                break;
                            case 1:enter="/";
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
    }
    public void onClick(View view){


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

