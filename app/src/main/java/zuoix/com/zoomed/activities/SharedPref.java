package zuoix.com.zoomed.activities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    // shared pref mode
    int PRIVATE_MODE = 0;
    // Shared preferences name
    //private static final String PREF_NAME = "zoomed-welcome";
    private static final String PREF_NAME = "pref";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public SharedPref(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sp.edit();
    }

    public void setGeneration(int generation){
        editor.putInt("generation",generation);
        editor.commit();
    }

    public int getGeneration(){
        return sp.getInt("generation",1);
    }

    //First Time Launch Methods
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return sp.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
