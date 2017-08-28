package com.example.hp.navigationwithtab.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.hp.navigationwithtab.R;
import com.example.hp.navigationwithtab.Services.MyService;
import com.example.hp.navigationwithtab.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ViewPager mNewsPager;
    private DrawerLayout mNewsDrawer;
    private TabLayout mNewsTabLayout;
    public static SQLiteDatabase db;
    /*private String[] pageTitle = {"Top News", "Business", "Entertainment","World","Technology","Sports"};*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* MyService myService=new MyService();
        startService(new Intent(MainActivity.this,MyService.class));*/
        db=openOrCreateDatabase("db_news", Activity.MODE_PRIVATE,null);
        Resources res = getResources();
         String[] pageTitle = res.getStringArray(R.array.News_array);


        mNewsPager = (ViewPager)findViewById(R.id.view_pager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mNewsDrawer = (DrawerLayout) findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);

        //create default navigation mNewsDrawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mNewsDrawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mNewsDrawer.addDrawerListener(toggle);
        toggle.syncState();

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        mNewsTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (int i = 0; i < 7; i++) {
            mNewsTabLayout.addTab(mNewsTabLayout.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        mNewsTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //handling navigation view item event
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mNewsPager.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        mNewsPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mNewsTabLayout));

        //change ViewPager page when tab selected
        mNewsTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mNewsPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.TopNews) {
            mNewsPager.setCurrentItem(0);
        } else if (id == R.id.Business) {
            mNewsPager.setCurrentItem(1);
        } else if (id == R.id.Entertainment) {
            mNewsPager.setCurrentItem(2);
        } else if (id == R.id.World) {
            mNewsPager.setCurrentItem(3);
        } else if (id == R.id.Technology) {
            mNewsPager.setCurrentItem(4);
        } else if (id == R.id.Sports) {
            mNewsPager.setCurrentItem(5);
        }else if (id == R.id.Offline) {
                mNewsPager.setCurrentItem(6);
        } else if (id == R.id.go) {
            Intent intent = new Intent(this, NewsDescription.class);
            startActivity(intent);
        } else if (id == R.id.close) {
            System.exit(0);
        }

        mNewsDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert mNewsDrawer != null;
        if (mNewsDrawer.isDrawerOpen(GravityCompat.START)) {
            mNewsDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
