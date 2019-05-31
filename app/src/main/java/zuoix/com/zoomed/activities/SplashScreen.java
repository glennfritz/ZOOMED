package zuoix.com.zoomed.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


import zuoix.com.zoomed.BaseApplication;
import zuoix.com.zoomed.InitCommands;
import zuoix.com.zoomed.R;

public class SplashScreen extends AppCompatActivity {


    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        pb = findViewById(R.id.pb);

            BaseApplication.instance.onCreate();
            RelativeLayout splash_layout = findViewById(R.id.splash_layout);
            Animation animSlideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
            splash_layout.startAnimation(animSlideUp);
            new InitCommands(this);

            Thread timer = new Thread() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void run() {
                    try {
                        pb.setProgress(5);
                        sleep(500);
                        pb.setProgress(30);
                        sleep(1500);
                        pb.setProgress(70);
                        sleep(2500);
                        pb.setProgress(95);
                        sleep(1000);
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

