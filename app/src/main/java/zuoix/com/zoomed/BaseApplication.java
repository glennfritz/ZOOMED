package zuoix.com.zoomed;

import android.app.Application;
import java.util.ArrayList;


public class BaseApplication extends Application {
    public static ArrayList<CommandModel> firstGenerationCommand;
    public static ArrayList<CommandModel> secondGenerationCommand;
    public static ArrayList<CommandModel> thirdGenerationCommand;
    public String srcNumber;
    public String destNumber;

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

        firstGenerationCommand = new ArrayList<>();
        secondGenerationCommand = new ArrayList<>();
        thirdGenerationCommand = new ArrayList<>();

        firstGenerationCommand.add(new CommandModel("Hello ","Command hello",getResources().getDrawable(R.drawable.default_command)));
        firstGenerationCommand.add(new CommandModel("Hello1 ","Command hello",getResources().getDrawable(R.drawable.default_command)));
        firstGenerationCommand.add(new CommandModel("Hello2 ","Command hello",getResources().getDrawable(R.drawable.default_command)));
        firstGenerationCommand.add(new CommandModel("Hello3 ","Command hello",getResources().getDrawable(R.drawable.default_command)));
        firstGenerationCommand.add(new CommandModel("Hello4 ","Command hello",getResources().getDrawable(R.drawable.default_command)));
        firstGenerationCommand.add(new CommandModel("Hello5 ","Command hello",getResources().getDrawable(R.drawable.default_command)));
        firstGenerationCommand.add(new CommandModel("Hello6 ","Command hello",getResources().getDrawable(R.drawable.default_command)));
        firstGenerationCommand.add(new CommandModel("Hello7 ","Command hello",getResources().getDrawable(R.drawable.default_command)));

        firstGenerationCommand.add(new CommandModel("Hello ","Command hello",getResources().getDrawable(R.drawable.default_command)));
        firstGenerationCommand.add(new CommandModel("Hello1 ","Command hello",getResources().getDrawable(R.drawable.default_command)));
        firstGenerationCommand.add(new CommandModel("Hello2 ","Command hello",getResources().getDrawable(R.drawable.default_command)));
        firstGenerationCommand.add(new CommandModel("Hello3 ","Command hello",getResources().getDrawable(R.drawable.default_command)));
        firstGenerationCommand.add(new CommandModel("Hello4 ","Command hello",getResources().getDrawable(R.drawable.default_command)));
        firstGenerationCommand.add(new CommandModel("Hello5 ","Command hello",getResources().getDrawable(R.drawable.default_command)));
        firstGenerationCommand.add(new CommandModel("Hello6 ","Command hello",getResources().getDrawable(R.drawable.default_command)));
        firstGenerationCommand.add(new CommandModel("Hello7 ","Command hello",getResources().getDrawable(R.drawable.default_command)));

        //second generation
        secondGenerationCommand.add(new CommandModel("bigginner 1","",getResources().getDrawable(R.drawable.default_command)));
        secondGenerationCommand.add(new CommandModel("bigginner 2","",getResources().getDrawable(R.drawable.default_command)));
        secondGenerationCommand.add(new CommandModel("bigginner 3","",getResources().getDrawable(R.drawable.default_command)));
        secondGenerationCommand.add(new CommandModel("bigginner 4","",getResources().getDrawable(R.drawable.default_command)));
        secondGenerationCommand.add(new CommandModel("bigginner 5","",getResources().getDrawable(R.drawable.default_command)));
        secondGenerationCommand.add(new CommandModel("bigginner 6","",getResources().getDrawable(R.drawable.default_command)));
        secondGenerationCommand.add(new CommandModel("bigginnerb7","",getResources().getDrawable(R.drawable.default_command)));

        //third generation
        thirdGenerationCommand .add(new CommandModel("generation 1","",getResources().getDrawable(R.drawable.default_command)));
        thirdGenerationCommand .add(new CommandModel("generation 2","",getResources().getDrawable(R.drawable.default_command)));
        thirdGenerationCommand .add(new CommandModel("generation fffffffffff","",getResources().getDrawable(R.drawable.default_command)));
        thirdGenerationCommand .add(new CommandModel("generation 3","",getResources().getDrawable(R.drawable.default_command)));
        thirdGenerationCommand .add(new CommandModel("generation 4","",getResources().getDrawable(R.drawable.default_command)));
        thirdGenerationCommand .add(new CommandModel("generationv5","",getResources().getDrawable(R.drawable.default_command)));
        thirdGenerationCommand .add(new CommandModel("generation 6","",getResources().getDrawable(R.drawable.default_command)));

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
