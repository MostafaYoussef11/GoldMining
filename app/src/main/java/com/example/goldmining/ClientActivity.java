package com.example.goldmining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ClientActivity extends AppCompatActivity {
    private  static ProgressBar pg;
    private  static TabLayout tabLayout;
    private  static ViewPager viewPager;
    private  static ArrayList<String> arrayList;
    private static MainAdapter mainAdapter;
    private RequestQueue requestQueue  ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        //pg = findViewById(R.id.pg2);
        //Assign Variable
        tabLayout = findViewById(R.id.tabsClint);
        viewPager = findViewById(R.id.viewPage);
        //mainAdapter = new MainAdapter(getSupportFragmentManager());
        //new LoadFragment().execute();
        //Initialize arrayList
        arrayList = new ArrayList<>();
        arrayList.add("عميل جديد");
        arrayList.add("العملاء");
       // arrayList.add("test");

//        //Prepare View Pager
//
        prepareViewPager(viewPager , arrayList);
         tabLayout.setupWithViewPager(viewPager);
    }

    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        AddClientFragment fragment = new AddClientFragment();
        ClientFragment clientFragment1 = new ClientFragment();
        mainAdapter.addFragment(fragment , arrayList.get(0));
        mainAdapter.addFragment(clientFragment1,arrayList.get(1));
        viewPager.setAdapter(mainAdapter);


    }

    public static class LoadFragment extends AsyncTask<Void , Void ,Void>{

        @Override
        protected void onPreExecute() {
         //   super.onPreExecute();
            pg.setVisibility(View.VISIBLE);
            pg.setVisibility(View.GONE);
            arrayList = new ArrayList<>();
            arrayList.add("عميل جديد");
            arrayList.add("العملاء");

        }

        @Override
        protected void onPostExecute(Void unused) {
            //super.onPostExecute(unused);
            //finishActivityFromChild(MainActivity.class.,545);
            pg.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            AddClientFragment fragment = new AddClientFragment();
            ClientFragment clientFragment1 = new ClientFragment();
            mainAdapter.addFragment(fragment , arrayList.get(0));
            mainAdapter.addFragment(clientFragment1,arrayList.get(1));
            viewPager.setAdapter(mainAdapter);

            //prepareViewPager(viewPager , arrayList);
            //tabLayout.setupWithViewPager(viewPager);

           // pg.setVisibility(View.INVISIBLE);
            return null;
        }
    }
}