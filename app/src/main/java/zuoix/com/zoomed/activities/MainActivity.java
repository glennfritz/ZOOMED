package zuoix.com.zoomed.activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

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
        getSupportActionBar().setTitle(R.string.app_name);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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



    }
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            if(active != command){
                navigation.setSelectedItemId(R.id.command);
            }else{
                finish();
            }
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