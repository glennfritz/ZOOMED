package zuoix.com.zoomed.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;


import java.net.URL;

import zuoix.com.zoomed.GPSTracker;
import zuoix.com.zoomed.R;
import zuoix.com.zoomed.fragment.AccountFragment;
import zuoix.com.zoomed.fragment.CommandFragment;
import zuoix.com.zoomed.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity {

    final Fragment account = new AccountFragment();
    final Fragment setting = new SettingFragment();
    final Fragment command = new CommandFragment();

    Toolbar toolbar;
    BottomNavigationView navigation;

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = command;
    Context context;
    SharedPref sp;
    private DrawerLayout mDrawerLayout;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.command:
                    fm.beginTransaction().hide(active).show(command).commit();
                    active = command;
                    return true;

                case R.id.account:
                    fm.beginTransaction().hide(active).show(account).commit();
                    active = account;
                    return true;

                case R.id.setting:
                    fm.beginTransaction().hide(active).show(setting).commit();
                    active = setting;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sp = new SharedPref(this);
        //getSupportActionBar().setTitle(R.string.app_name);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigation = findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        context = this;
        fm.beginTransaction().add(R.id.main_container, account, "1").hide(account).commit();
        fm.beginTransaction().add(R.id.main_container, setting, "2").hide(setting).commit();
        fm.beginTransaction().add(R.id.main_container,command, "3").commit();
        navigation.setSelectedItemId(R.id.command);

        GPSTracker gpsTracker = new GPSTracker(this);

        if (gpsTracker.getIsGPSTrackingEnabled() ) {
            sp.setLatitude(String.valueOf(gpsTracker.getLatitude()));
            sp.setLongitude(String.valueOf(gpsTracker.getLongitude()));
            sp.setLongitude(String.valueOf(gpsTracker.getLongitude()));

        }else {
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
            }


        mDrawerLayout = findViewById(R.id.container);
        NavigationView mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_about:
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_rate:
                        // TODO replace the empty quotes with the url of app on playstore
                        openUrl("play.google.com");
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_suggest:
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_privacy:
                        openUrl("google.com/#privacy");
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;

                }
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

    }



    private void openUrl(String url){
        if(isNetworkAvailable()){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }
        else{
            AlertDialog.Builder bd = new AlertDialog.Builder(MainActivity.this);
            bd.setMessage(getString(R.string.require_internet));
            bd.create().show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*if(item.getItemId() == android.R.id.home){
            if(active != command){
                navigation.setSelectedItemId(R.id.command);
            }else{
                finish();
            }
        }*/
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            //return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(active != command){
            navigation.setSelectedItemId(R.id.command);
            active = command;
        }else{
            finish();
        }
    }


}