package zuoix.com.zoomed.activities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public SharedPref(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("pref", 0);
        editor = sp.edit();
    }

    public void setGeneration(int generation){
        editor.putInt("generation",generation);
        editor.commit();
    }

    public String getDefaultSim(){
        return sp.getString("sim","isms");
    }

    public String getDestinationNumber(){
        return sp.getString("number","672081771");
    }
    public void setDestinationNumber(String number){
        editor.putString("number",number);
        editor.commit();
    }

    public void setDefaultSim(int id){
        editor.putString("sim","isms"+id);
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
}
