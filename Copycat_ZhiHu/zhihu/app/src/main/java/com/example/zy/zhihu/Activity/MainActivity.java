package com.example.zy.zhihu.Activity;

import android.content.Intent;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.zy.zhihu.Bean.Theme;
import com.example.zy.zhihu.DataBase.ReadDbHelper;
import com.example.zy.zhihu.DataBase.WebCacheDbHelper;
import com.example.zy.zhihu.HttpGet;
import com.example.zy.zhihu.R;
import com.example.zy.zhihu.UI.MainFragment;
import com.example.zy.zhihu.View.theme;
import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements theme.setToolbar{
    private DrawerLayout drawerLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Toolbar toolbar;
    private String fragment="latest";
    private ReadDbHelper readDbHelper;
    private WebCacheDbHelper helper;
    private String url=null;
    private NavigationView navigationView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView= (NavigationView) findViewById(R.id.test_fragment);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawerlayout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.toolbar,R.string.toolbar);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        readDbHelper=new ReadDbHelper(this,1);
        helper=new WebCacheDbHelper(this,1);

        desScrollbars(navigationView);//取消NavigationView菜单的滚动条
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId()!=R.id.theme_1) {
                    switch (item.getItemId()){
                        case R.id.theme_2:
                            url="2";
                            break;
                        case R.id.theme_3:
                            url="3";
                            break;
                        case R.id.theme_4:
                            url="4";
                            break;
                        case R.id.theme_5:
                            url="5";
                            break;
                        case R.id.theme_6:
                            url="6";
                            break;
                        case R.id.theme_7:
                            url="7";
                            break;
                        case R.id.theme_8:
                            url="8";
                            break;
                        case R.id.theme_9:
                            url="9";
                            break;
                        case R.id.theme_10:
                            url="10";
                            break;
                        case R.id.theme_11:
                            url="11";
                            break;
                        case R.id.theme_12:
                            url="12";
                            break;
                        case R.id.theme_13:
                            url="13";
                            break;
                    }
                    Bundle bundle=new Bundle();
                    bundle.putString("url",url);
                    theme theme=new theme();
                    theme.setArguments(bundle);
                    setSwipRefresh("theme");
                    getSupportFragmentManager().beginTransaction().replace(R.id.float_frame,theme).commit();
                } else {
                    setSwipRefresh("latest");
                    toolbar.setTitle("膜蛤日报");
                    getSupportFragmentManager().beginTransaction().replace(R.id.float_frame,new MainFragment(),"latest").commit();
                }
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;

            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.float_frame,new MainFragment(),"latest").commit();
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (fragment=="latest") {
                    getSupportFragmentManager().beginTransaction().replace(R.id.float_frame,new MainFragment(),"latest").commit();
                } else if(fragment=="theme"){
                    Bundle bundle=new Bundle();
                    bundle.putString("url",url);
                    theme theme=new theme();
                    theme.setArguments(bundle);
                    setSwipRefresh("theme");
                    getSupportFragmentManager().beginTransaction().replace(R.id.float_frame,theme,"theme").commit();
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public ReadDbHelper getReadDbHelper(){
        return readDbHelper;
    }

    public WebCacheDbHelper getThemeHelper(){
        return helper;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public void setSwipeRefreshLayout(boolean enable) {
        swipeRefreshLayout.setEnabled(enable);
    }

    public void desScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    public void setToolbar(String s) {
        toolbar.setTitle(s);

    }

    public void setSwipRefresh(String s) {
        this.fragment=s;
    }
}
