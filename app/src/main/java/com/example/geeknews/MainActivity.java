package com.example.geeknews;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.geeknews.beas.activity.SimpleActivity;
import com.example.geeknews.fragments.GanhuoFragment;
import com.example.geeknews.fragments.ShujuFragment;
import com.example.geeknews.fragments.V2ExFragment;
import com.example.geeknews.fragments.WeixinFragment;
import com.example.geeknews.fragments.ZhihuFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends SimpleActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.view_search)
    MaterialSearchView mViewSearch;
    private MenuItem searchMenuItem;
    private MenuItem mMenuItem;
    private Toolbar mToolbar;

    @Override
    protected void initData() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("知乎日报");
        setSupportActionBar(mToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.app_name, R.string.drawer_group_options);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mMenuItem = navigationView.getMenu().findItem(R.id.drawer_zhihu);
        mMenuItem.setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_content, new ZhihuFragment());
        fragmentTransaction.commit();

    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_settings);

        if (mMenuItem.getItemId() == R.id.drawer_zhihu) {
            item.setCheckable(true);
        } else {
            item.setVisible(false);
        }

        //关联toolbar的搜索按钮
        mViewSearch.setMenuItem(item);
        searchMenuItem = item;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //fragment事务管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (id == R.id.drawer_zhihu) {
            // Handle the camera action
            fragmentTransaction.replace(R.id.fl_content, new ZhihuFragment());
            searchMenuItem.setVisible(false);
            mToolbar.setTitle("知乎日报");
        } else if (id == R.id.drawer_wechat) {
            fragmentTransaction.replace(R.id.fl_content, new WeixinFragment());
            searchMenuItem.setVisible(true);
            mToolbar.setTitle("微信精选");
        } else if (id == R.id.drawer_gank) {
            fragmentTransaction.replace(R.id.fl_content, new GanhuoFragment());
            searchMenuItem.setVisible(true);
            mToolbar.setTitle("干货集中营");
        } else if (id == R.id.drawer_gold) {
            fragmentTransaction.replace(R.id.fl_content, new ShujuFragment());
            searchMenuItem.setVisible(false);
            mToolbar.setTitle("数据智汇");
        } else if (id == R.id.drawer_vtex) {
            fragmentTransaction.replace(R.id.fl_content, new V2ExFragment());
            searchMenuItem.setVisible(false);
            mToolbar.setTitle("V2EX");
        } else if (id == R.id.drawer_like) {

        } else if (id == R.id.drawer_setting) {

        } else if (id == R.id.drawer_about) {

        }

        fragmentTransaction.commit();
        if (mMenuItem != null) {
            mMenuItem.setChecked(false);
        }

        item.setChecked(true);
        mMenuItem = item;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
