package zuoix.com.zoomed;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import zuoix.com.zoomed.activities.MainActivity;
import zuoix.com.zoomed.activities.ResetPassword;
import zuoix.com.zoomed.activities.SplashScreen;

public class ParseDeepLinkActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent == null || intent.getData() == null){
            finish();
        }else{
            openDeepLink(intent.getData());
            finish();
        }
    }

    private void openDeepLink(Uri deepLink){
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        List<String> segments = deepLink.getPathSegments();

        if(segments != null){

            for(int i = 0; i<segments.size(); i++){
                String segment = segments.get(i);
                Intent route = null;
                if("resetpassword".equals(segment)){
                    route = new Intent(this,ResetPassword.class);
                }else if("home".equals(segment)){
                    route = new Intent(this,SplashScreen.class);
                }
            }
        }
        if (stackBuilder.getIntentCount() == 0) {
            stackBuilder.addNextIntent(new Intent(this, MainActivity.class));
        }
        stackBuilder.startActivities();

    }
}
