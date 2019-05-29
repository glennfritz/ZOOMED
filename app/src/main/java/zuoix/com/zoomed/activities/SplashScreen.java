package zuoix.com.zoomed.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zuoix.com.zoomed.BaseApplication;
import zuoix.com.zoomed.CommandModel;
import zuoix.com.zoomed.R;

public class SplashScreen extends AppCompatActivity {

    //ist of all permissions for the app
    String[] appPermissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.INTERNET,
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_PHONE_STATE
    };

    private static final int PERMISSION_REQUEST_CODE = 1240;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Check for app permissions
        //In case one or more permissions are not granted
        //ActivityCompat.requestPermissions() will request permissions
        //and the control goes to onRequestPermissionResult() callback method
        if (checkAndRequestPermissions()) {

            BaseApplication.instance.onCreate();
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel("Hello ", "Command hello", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel("Hello1 ", "Command hello", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel("Hello2 ", "Command hello", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel("Hello3 ", "Command hello", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel("Hello4 ", "Command hello", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel("Hello5 ", "Command hello", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel("Hello6 ", "Command hello", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel("Hello7 ", "Command hello", getResources().getDrawable(R.drawable.default_command)));

            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel("Hello ", "Command hello", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel("Hello1 ", "Command hello", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel("Hello2 ", "Command hello", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel("Hello3 ", "Command hello", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel("Hello4 ", "Command hello", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel("Hello5 ", "Command hello", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel("Hello6 ", "Command hello", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel("Hello7 ", "Command hello", getResources().getDrawable(R.drawable.default_command)));

            //second generation
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel("bigginner 1", "", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel("bigginner 2", "", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel("bigginner 3", "", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel("bigginner 4", "", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel("bigginner 5", "", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel("bigginner 6", "", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel("bigginnerb7", "", getResources().getDrawable(R.drawable.default_command)));

            //third generation
            BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel("generation 1", "", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel("generation 2", "", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel("generation fffffffffff", "", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel("generation 3", "", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel("generation 4", "", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel("generationv5", "", getResources().getDrawable(R.drawable.default_command)));
            BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel("generation 6", "", getResources().getDrawable(R.drawable.default_command)));


            RelativeLayout splash_layout = findViewById(R.id.splash_layout);
            Animation animSlideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
            splash_layout.startAnimation(animSlideUp);

            Thread timer = new Thread() {
                @Override
                public void run() {
                    try {
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

    private boolean checkAndRequestPermissions() {
        //Check which permissions are granted
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String pem : appPermissions){
            if (ContextCompat.checkSelfPermission(this, pem) != PackageManager.PERMISSION_GRANTED){
                listPermissionsNeeded.add(pem);
            }
        }

        //ask for non-granted permissions
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), PERMISSION_REQUEST_CODE);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            HashMap<String, Integer> permissionResults = new HashMap<>();
            int deniedCount = 0;

            //Gather permission grant result
            for(int i=0; i<grantResults.length; i++) {
                //Add only permissions which are denied
                if(grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    permissionResults.put(permissions[i], grantResults[i]);
                    deniedCount++;
                }
            }

            //check if all permissions are granted
            if (deniedCount != 0) {
                for (Map.Entry<String, Integer> entry : permissionResults.entrySet()) {
                    String permName = entry.getKey();
                    int permResult = entry.getValue();
                    //permission is denied (this is the first time, when "never ask again" is not checked)
                    //so ask again explaining the usage for the permission
                    //shouldShowRequestPermissionRationale will return true
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permName)) {
                        showDialog("", "This app needs Location and Internet permission to get map for offline use, Call and SMS  permission offline to work without problems", "Yes, Grant permissions",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        dialog.dismiss();
                                        checkAndRequestPermissions();
                                    }
                                },
                                "No, Exit app", new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        dialog.dismiss();
                                        finish();
                                    }
                                }, false);
                    }
                    //permission is denied (and never ask again is checked)
                    //shouldShowRequestPermissionRationale will return false
                    else {
                        //Ask user to go to setting and manually allow permissions
                        showDialog("", "You have denied some permissions. Allow all permissions at [Setting] > [Permissions]", "Go to Setting", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        //Go to app settings
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null));
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                },
                                "No, Exit app", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        finish();
                                    }
                                }, false);
                        break;
                    }
                }
            }
        }
    }

    private AlertDialog showDialog(String title, String msg, String positiveLabel, DialogInterface.OnClickListener positiveOnClickListener, String negativeLable, DialogInterface.OnClickListener negativeOnClickListener1, boolean b) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setCancelable(true);
        builder.setMessage(msg);
        builder.setPositiveButton(positiveLabel, positiveOnClickListener);
        builder.setNegativeButton(negativeLable, negativeOnClickListener1);

        AlertDialog alert = builder.create();
        alert.show();
        return alert;
    }
}