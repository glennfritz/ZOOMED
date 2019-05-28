package zuoix.com.zoomed.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import zuoix.com.zoomed.BaseApplication;
import zuoix.com.zoomed.CommandModel;
import zuoix.com.zoomed.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        BaseApplication.instance.onCreate();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();


    }
}
