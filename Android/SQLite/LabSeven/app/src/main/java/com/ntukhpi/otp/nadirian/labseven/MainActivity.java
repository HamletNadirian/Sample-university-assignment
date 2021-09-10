package com.ntukhpi.otp.nadirian.labseven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView peopleList;
    ArrayAdapter<People> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        peopleList=(ListView)findViewById(R.id.list);

        peopleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                People people=arrayAdapter.getItem(position);
                if (people!=null){
                    Intent intent=new Intent(getApplicationContext(),PeopleActivity.class);
                    intent.putExtra("id",people.getId());
                    intent.putExtra("click",25);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        DatabaseAdapter adapter = new DatabaseAdapter(this);
        adapter.open();

        List<People> people = adapter.getPeople();

        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,people);
        peopleList.setAdapter(arrayAdapter);
        adapter.close();
    }
    public void add(View view){
        Intent intent = new Intent(this,PeopleActivity.class);
        startActivity(intent);
    }



}
