package com.example.goldmining;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends FragmentPagerAdapter {

    //Initialize ArrayList
    ArrayList<String> arrayList = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();

    //Create Constructor
    public void addFragment(Fragment fragment , String title){
        //Add Title
        arrayList.add(title);
        //Add Fragment
        fragmentList.add(fragment);
    }


    public MainAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //Return Fragment
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        //Return Fragment Size
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayList.get(position);
    }
}
