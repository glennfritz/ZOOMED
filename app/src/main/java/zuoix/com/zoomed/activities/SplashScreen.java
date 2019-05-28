package zuoix.com.zoomed.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

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

        RelativeLayout splash_layout = findViewById(R.id.splash_layout);
        Animation animSlideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        splash_layout.startAnimation(animSlideUp);

        Thread timer = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(SplashScreen.this, WelcomeActivity.class));
                    finish();
                }
            }
        };

        timer.start();


    }
}
