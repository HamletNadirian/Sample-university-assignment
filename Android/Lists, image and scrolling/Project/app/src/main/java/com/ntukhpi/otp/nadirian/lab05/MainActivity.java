package com.ntukhpi.otp.nadirian.lab05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;


public class MainActivity extends AppCompatActivity /*implements NavigationView.OnNavigationItemSelectedListener */{
    private NavigationView nv;
    MyStringRandomGen msr = new MyStringRandomGen();
    private final char [] characters = "aeiou".toCharArray();

  private ActionBarDrawerToggle toggle;
  ArrayList<Product>products=new ArrayList<>();
  ArrayList<Product>products_fil=new ArrayList<>();
    BoxAdapter boxAdapter;
    BoxAdapter newboxAdapter;
    private DrawerLayout drawerLayout;
    ListView listView;
    ListView newlistView;
    Product product = new Product();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.floating_action_button);

       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));
       products.add(new Product(msr.generateRandomString(),"decrypt me please!!!",R.drawable.khpi,false));



        boxAdapter=new BoxAdapter(this,products);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(boxAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ArrayList<Product> filteredList = new ArrayList<>();

                for (int i = 0; i < products.size(); i++) {
                    final Product product = products.get(i);
                    if(!checkIfContains_(product.getName().toLowerCase())) {
                        filteredList.add(product);
                    }
                }
                boxAdapter.setData(filteredList);

                /*А что дальше?*/
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer_layout);
         toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawe_open,R.string.navigation_drawe_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nv = (NavigationView)findViewById(R.id.nav_view);
        final ComparatorByclass sortm=new ComparatorByclass();
        final ComparatorByClassRevers revers=new ComparatorByClassRevers();


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.A_Z:
                        products.sort(sortm);
                        listView.setAdapter(boxAdapter);
                        boxAdapter.notifyDataSetChanged();

                        Toast.makeText(MainActivity.this, "My Account",Toast.LENGTH_SHORT).show();break;
                    case R.id.Z_A:
                        products.sort(revers);
                        listView.setAdapter(boxAdapter);
                        boxAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "My Account",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_down:
                    listView.smoothScrollToPosition(boxAdapter.getCount()-1);break;
                    case R.id.nav_upp:
                        listView.smoothScrollToPosition(0);
                    default:
                        return true;
                }


                return true;

            }
        });


    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer_menu,menu);
        return true;
    }

    public void showResult(View v) {
        String result = "Товары в корзине:";
        for (Product p : boxAdapter.getBox()) {
            if (p.box)
                result += "\n" + p.name;

        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) { if(toggle.onOptionsItemSelected(item)) return true; return super.onOptionsItemSelected(item); }

    public boolean checkIfContains(String string) {
        for (char character : characters) {
            if (character == string.charAt(0)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfContains_(String string) {
       char[] character = new char[characters.length];
        for (int i=0;i<characters.length;i++) {
            character=characters;
            if (character[i] == string.charAt(0)) {
                return true;
            }
        }
        return false;
    }
}