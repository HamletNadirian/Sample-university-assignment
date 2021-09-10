package com.ntukhpi.otp.nadirian.lab05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private Toolbar nv;

    int result=0;
    private TextView textView;
    private EditText num1;
    private EditText num2;
    String enter="=";
    TextView tvError;
    String oper[]={"*","/"};
    Button button;
    Button buttonSave;
    public static  final  String SHARED_RES = "RESULT;";
    private String text_s;
    public static String TEXT="text";
    Button buttonLoad;String s3;
    final String LOG_TAG = "myLogs";
    SharedPreferences sharedPreferences;
    private static String KEY="1";
    String res1= String.valueOf(0);
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSave=findViewById(R.id.btnsave);
        buttonLoad=findViewById(R.id.btnload);
        sharedPreferences = getSharedPreferences(KEY, MODE_PRIVATE);
        nv = findViewById(R.id.toolbar);

        textView=(TextView)findViewById(R.id.textV);
        num1=(EditText)findViewById(R.id.number_one);
        num2=(EditText)findViewById(R.id.number_two);










        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                textView.setText(res1);
                loadData();
                updateViews();

            }
        });


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, oper);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);///////

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

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here

                tvError = (TextView) findViewById(R.id.tvError);
                int a = Integer.parseInt(num1.getText().toString());
                int a1 = Integer.parseInt(num2.getText().toString());

                 s3= a+ " " +a1;
                String text = String.valueOf(s3);
                textView.setText("Результат: "+text);
                switch(v.getId()){
                    case R.id.button:
                        switch (enter) {
                            case "*":
                                result = a * a1;
                                break;
                            case "/":
                                try {
                                    result = a / a1;
                                    break;
                                }
                                catch (ArithmeticException e) {
                                    // Строки кода в catch будут выполнены.
                                    tvError.setText("Error: " + e.getMessage());
                                }
                        }        Log.d(LOG_TAG, "RESULT:"+result);
                        textView.setText("Your enter is: " + a + enter + a1 +"="+result);
                }

            }

        });

       // textView.setText(result);

        res1= String.valueOf(result);
    }


    private void saveData() {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_RES,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString(TEXT,textView.getText().toString());

        editor.apply();

        Toast.makeText(this,"Data saved",Toast.LENGTH_SHORT).show();
    }
    public void loadData(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_RES,MODE_PRIVATE);
        text_s=sharedPreferences.getString(TEXT,"");
        Toast.makeText(this,"Load saved",Toast.LENGTH_SHORT).show();

    }
    public void updateViews(){
        textView.setText(text_s);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_tems,menu);
    return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.Res_tool_bar)
            Toast.makeText(this,"Click",Toast.LENGTH_SHORT).show();
        textView.setText(res1);
        loadData();
        updateViews();
        return super.onOptionsItemSelected(item);
    }
}
