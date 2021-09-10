package com.ntukhpi.otp.nadirian.labseven;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PeopleActivity extends AppCompatActivity {

    Button delButton,saveButton;
    EditText etSurname,etFirstName,etPatronym,etAge,etCity;

    private long userId=0;
    private DatabaseAdapter databaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        delButton = (Button) findViewById(R.id.deleteButton);
        saveButton = (Button) findViewById(R.id.saveButton);

        etSurname=findViewById(R.id.etSurname);
        etFirstName=findViewById(R.id.etFirstName);
        etPatronym=findViewById(R.id.etPatronym);
        etCity=findViewById(R.id.etCity);
        etAge=findViewById(R.id.etAge);
        databaseAdapter=new DatabaseAdapter(this);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            userId=extras.getLong("id");
        }
        if(userId>0){
            databaseAdapter.open();
            People people = databaseAdapter.getPeople(userId);
            etFirstName.setText(people.getName());
            etSurname.setText(people.getSurname());
            etPatronym.setText(people.getPatronym());
            etAge.setText(String.valueOf(people.getAge()));
            etCity.setText(people.getCity());
            databaseAdapter.close();
        }
        else {
       delButton.setVisibility(View.GONE);
        }
    }
    public void save(View view){
        String name=etFirstName.getText().toString();
        String surname=etSurname.getText().toString();
        String patronym=etPatronym.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        String city = etCity.getText().toString();
        People people = new People(userId,name,surname,patronym,age,city);
        databaseAdapter.open();
        if(userId>0){
            databaseAdapter.update(people);
        }
        else {
            databaseAdapter.insert(people);
        }
        databaseAdapter.close();
        goHome();
    }
    public void delete(View view){
        databaseAdapter.open();
        databaseAdapter.delete(userId);
        databaseAdapter.close();
        goHome();
    }
    private void goHome(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
