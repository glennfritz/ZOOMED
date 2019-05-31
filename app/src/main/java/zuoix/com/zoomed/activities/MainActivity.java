package zuoix.com.zoomed.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.content.IntentFilter;
import android.location.Location;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mapswithme.maps.api.MWMPoint;
import com.mapswithme.maps.api.MWMResponse;
import com.mapswithme.maps.api.MapsWithMeApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import zuoix.com.zoomed.GPSTracker;
import zuoix.com.zoomed.R;
import zuoix.com.zoomed.fragment.AccountFragment;
import zuoix.com.zoomed.fragment.CommandFragment;
import zuoix.com.zoomed.fragment.SettingFragment;
import zuoix.com.zoomed.models.User;


public class MainActivity extends AppCompatActivity  {

    Fragment account = new AccountFragment();
    Fragment setting = new SettingFragment();
    Fragment command = new CommandFragment();

    Toolbar toolbar;
    BottomNavigationView navigation;

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = command;
    Context context;
    SharedPref sp;
    private DrawerLayout mDrawerLayout;
    static User user;


    public static void setUserData(User u){
        user = u;
    }

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
        //passing user data to account fragment
        
        Log.d("Userdata", String.valueOf(user));
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

        if (gpsTracker.getLocation() != null) {
            Location location = gpsTracker.getLocation();
            sp.setLatitude(String.valueOf(location.getLatitude()));
            sp.setLongitude(String.valueOf(location.getLongitude()));

        }else {
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
            }
        // Handle intent you specified with PandingIntent
        // Now it has additional information (MWMPoint).
        //handleIntent(getIntent());
       // showSomethingOnTheMap();

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