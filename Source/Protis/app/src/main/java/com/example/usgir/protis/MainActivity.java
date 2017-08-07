package com.example.usgir.protis;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    DrawerLayout drawerLayout;
    ListView listView;
    ArrayAdapter adapter;
    Home home;
    Account account;
    Share share;
    ActionBarDrawerToggle drawerToggle;
    FragmentManager manager;
    FragmentTransaction transaction;
    String items[];
    RelativeLayout layout;
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout)findViewById(R.id.base);
        items = new String[]{"Home","Account","Share"};
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        listView = (ListView)findViewById(R.id.list);
        adapter = new ArrayAdapter(getApplicationContext(),R.layout.listitem,R.id.textView5,items);
        listView.setAdapter(adapter);
        home = new Home();
        account = new Account();
        share = new Share();
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.base,account);
        transaction.commit();
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        transaction = manager.beginTransaction();
        switch (position)
        {
            case 0 : transaction.replace(R.id.base,home,"Home");
                     break;
            case 1 : transaction.replace(R.id.base,account,"Account");
                     break;
            default: transaction.replace(R.id.base,share,"Share");
        }
        transaction.commit();
        drawerLayout.closeDrawer(listView);
    }
}
