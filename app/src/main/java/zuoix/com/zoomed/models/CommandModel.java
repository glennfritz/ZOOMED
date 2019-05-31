package zuoix.com.zoomed.models;

import android.graphics.drawable.Drawable;

public class CommandModel {
   String name;
   Drawable image;
   String command;

    public CommandModel(String name, String command,Drawable image) {
        this.name = name;
        this.command = command;
        this.image = image;
    }


    public String getCommand() {
        return command;
    }
    public String getName() {
        return name;
    }
    public Drawable getImage() {
        return image;
    }

}
