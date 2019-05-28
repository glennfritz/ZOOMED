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

    public int getGeneration(){
        return sp.getInt("generation",1);
    }
}
