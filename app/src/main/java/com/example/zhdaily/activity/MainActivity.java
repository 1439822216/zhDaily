package com.example.zhdaily.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.example.zhdaily.R;
import com.example.zhdaily.fragment.CollectionFragment;
import com.example.zhdaily.fragment.HistoryFragment;
import com.example.zhdaily.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private TextView tv_main;
    private TextView tv_collection;
    private TextView tv_history;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        replaceFregment(new HomeFragment());
        tv_main.setBackgroundColor(getResources().getColor(R.color.check));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    //点击图标，打开或关闭侧滑
    public boolean onOptionsItemSelected(MenuItem item){
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        tv_main = (TextView) findViewById(R.id.tv_main);
        tv_collection = (TextView) findViewById(R.id.tv_collection);
        tv_history = (TextView) findViewById(R.id.tv_history);
        drawerLayout = findViewById(R.id.drawerLayout);
        tv_main.setOnClickListener(this);
        tv_collection.setOnClickListener(this);
        tv_history.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_collection:
                replaceFregment(new CollectionFragment());
                tv_collection.setBackgroundColor(getResources().getColor(R.color.check));
                tv_main.setBackgroundColor(getResources().getColor(android.R.color.white));
                tv_history.setBackgroundColor(getResources().getColor(android.R.color.white));

                drawerLayout.closeDrawers();
                break;
            case R.id.tv_history:
                replaceFregment(new HistoryFragment());
                tv_history.setBackgroundColor(getResources().getColor(R.color.check));
                tv_collection.setBackgroundColor(getResources().getColor(android.R.color.white));
                tv_main.setBackgroundColor(getResources().getColor(android.R.color.white));
                drawerLayout.closeDrawers();
                break;
            case R.id.tv_main:
                replaceFregment(new HomeFragment());
                tv_main.setBackgroundColor(getResources().getColor(R.color.check));
                tv_collection.setBackgroundColor(getResources().getColor(android.R.color.white));
                tv_history.setBackgroundColor(getResources().getColor(android.R.color.white));
                drawerLayout.closeDrawers();
                break;
        }
    }
    private void replaceFregment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }
}
