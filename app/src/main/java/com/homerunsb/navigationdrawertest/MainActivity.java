package com.homerunsb.navigationdrawertest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private ViewPager viewPager;
    Handler handler ;

//    private RecyclerView recyclerView;
//    private CompanyListAdapter companyListAdapter;
//    private MaterialListAdapter materialListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //상단 이미지 뷰페이저
        viewPager = (ViewPager)findViewById(R.id.pager);
        ImageViewPagerAdapter adapter = new ImageViewPagerAdapter(this);

        CircleIndicator indicator = (CircleIndicator)findViewById(R.id.indicator);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        adapter.registerDataSetObserver(indicator.getDataSetObserver());
        pageSwitcher();

        if (savedInstanceState == null) {
            CompanyListFragment fragment = new CompanyListFragment();
            replaceFragement(fragment);
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //네비게이션 드로워 부분
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //하단 대분류 버튼
        Button btn_materialsList = (Button)findViewById(R.id.btn_materialsList);
        Button btn_companyList = (Button)findViewById(R.id.btn_companyList);
        btn_materialsList.setOnClickListener(mainCategoryClickListener);
        btn_companyList.setOnClickListener(mainCategoryClickListener);

    }

    public void replaceFragement(Fragment fragment){
        //컨텐츠 부분 프레그먼트 배치
        Log.i(TAG, "replaceFragement() : " + fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.ll_content, fragment);
        transaction.commit();
        Log.i(TAG, "##########");

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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //하단 대분류 버튼 리스너
    View.OnClickListener mainCategoryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_companyList :
                    Log.i(TAG, "mainCategoryClickListener : btn_companyList");
                    CompanyListFragment cFragment = new CompanyListFragment();
                    replaceFragement(cFragment);
                    break;
                case R.id.btn_materialsList :
                    Log.i(TAG, "mainCategoryClickListener : btn_materialsList");
                    MaterialListFragment mFragment = new MaterialListFragment();
                    replaceFragement(mFragment);
                    break;
            }
        }
    };

    /*이미지 뷰페이저 페이지 자동 교체*/
    int page = 0;
    public void pageSwitcher() {
        handler = new Handler();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (page > 4) {
                    page = 0;
                } else {
                    viewPager.setCurrentItem(page++);
                }
                handler.postDelayed(this, 3000);
            }
        });
    }
}
