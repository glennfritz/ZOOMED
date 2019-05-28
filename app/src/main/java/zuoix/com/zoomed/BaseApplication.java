package zuoix.com.zoomed;

import android.app.Application;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.content.res.AppCompatResources;

import java.util.ArrayList;


public class BaseApplication extends Application {
    public static ArrayList<CommandModel> firstGenerationCommand;
    public static ArrayList<CommandModel> secondGenerationCommand;
    public static ArrayList<CommandModel> thirdGenerationCommand;
    public String srcNumber;
    public String destNumber;
    public Context context;



    public String getSrcNumber() {
        return srcNumber;
    }

    public void setSrcNumber(String srcNumber) {
        this.srcNumber = srcNumber;
    }

    public String getDestNumber() {
        return destNumber;
    }

    public void setDestNumber(String destNumber) {
        this.destNumber = destNumber;
    }

    public static BaseApplication instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = this;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


        firstGenerationCommand = new ArrayList<>();
        secondGenerationCommand = new ArrayList<>();
        thirdGenerationCommand = new ArrayList<>();


    }

    public static BaseApplication getInstance(){
        return instance;
    }

    public ArrayList<CommandModel> getCommandList(int generation) {
        if(generation == 1){
            return firstGenerationCommand;
        }else if(generation == 2){
            return secondGenerationCommand;
        }else{
            return thirdGenerationCommand;
        }
    }

}
