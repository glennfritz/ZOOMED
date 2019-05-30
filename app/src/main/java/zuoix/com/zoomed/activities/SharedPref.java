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

    public int getDefaultSim(){
        return sp.getInt("sim",-1);
    }

    public String getDestinationNumber(){
        return sp.getString("number","653251366");

    }
    public void setDestinationNumber(String number){
        editor.putString("number",number);
        editor.commit();
    }

    public void setDefaultSim(int id){
        editor.putInt("sim", id);
        editor.commit();
    }

    public Double getLatitude(){
        return Double.parseDouble(sp.getString("latitude","37.4220041"));
    }

    public Double getLongitude(){
        return Double.parseDouble(sp.getString("longitude","-122.0862515"));
    }
    public void setLatitude(String latitude){
        editor.putString("latitude",latitude);
        editor.commit();
    }

    public void setLongitude(String longitude){
        editor.putString("longitude",longitude);
        editor.commit();
    }

    public void setCountry(String country){
        editor.putString("country",country);
        editor.commit();
    }
    public String getCountry(){
        return sp.getString("country","Cameroon");
    }


    public int getGeneration(){
        return sp.getInt("generation",1);
    }

    //First Time Launch Methods
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean istrue(){
        return true;
    }


    public boolean isFirstTimeLaunch() {
        return sp.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
