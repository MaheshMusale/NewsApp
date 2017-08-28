package com.example.hp.navigationwithtab.adapter;

/**
 * Created by HP on 30/04/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hp.navigationwithtab.fragments.HomeFragment;
import com.example.hp.navigationwithtab.fragments.OfflineFragment;
import com.example.hp.navigationwithtab.fragments.TopNewsFrgment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if(position==0)
        {
            TopNewsFrgment topNewsFrgment=new TopNewsFrgment();
            return topNewsFrgment;
        }
        if(position==1)
        { Bundle bundle = new Bundle();
            bundle.putInt("CategoryId", position);
            HomeFragment homeFragment=new HomeFragment();
            homeFragment.setArguments(bundle);
            return homeFragment;}
        if(position==2)
        { Bundle bundle = new Bundle();
            bundle.putInt("CategoryId", position);
            HomeFragment homeFragment=new HomeFragment();
            homeFragment.setArguments(bundle);
            return homeFragment;}
        if(position==3)
        { Bundle bundle = new Bundle();
            bundle.putInt("CategoryId", position);
            HomeFragment homeFragment=new HomeFragment();
            homeFragment.setArguments(bundle);
            return homeFragment;}
        if(position==4)
        { Bundle bundle = new Bundle();
            bundle.putInt("CategoryId", position);
            HomeFragment homeFragment=new HomeFragment();
            homeFragment.setArguments(bundle);
            return homeFragment;}
        if(position==5)
        { Bundle bundle = new Bundle();
            bundle.putInt("CategoryId", position);
            HomeFragment homeFragment=new HomeFragment();
            homeFragment.setArguments(bundle);
            return homeFragment;}
        if(position==6)
        {
            OfflineFragment offlineFragment=new OfflineFragment();
            return offlineFragment;
        }


        return null;
    }


    @Override
    public int getCount() {
        return 7;
    }
}
