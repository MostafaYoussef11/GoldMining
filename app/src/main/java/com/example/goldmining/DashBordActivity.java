package com.example.goldmining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class DashBordActivity extends AppCompatActivity {
    CardView client;
    CardView Stone;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_bord);

        client = findViewById(R.id.client);
        Stone = findViewById(R.id.cardSton);

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ClientActivity.class);
                startActivity(intent);
                //new ClientActivity.LoadFragment().execute();
//                ClientActivity.LoadFragment load = new ClientActivity.LoadFragment();
//                load.execute();
            }
        });

        Stone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }





}