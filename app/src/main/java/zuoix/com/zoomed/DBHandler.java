package zuoix.com.zoomed;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHandler extends SQLiteOpenHelper {
public static final int VERSION = 2;
private SQLiteDatabase database;
Context context;


    public DBHandler(Context context , String name , SQLiteDatabase.CursorFactory factory , int version ) {
        super (context , "command.db" , factory , VERSION);
        database = getWritableDatabase ();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_message_table = "CREATE TABLE messages (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "message TEXT NOT NULL, " +
                "title TEXT NOT NULL, " +
                "contact TEXT NOT NULL, "+
                "time TEXT NOT NULL, " +
                "status TEXT NOT NULL " +
                ");";
        db.execSQL (create_message_table);
        }

    public void updatemessageStatus(String where_arg,String value) {
        ContentValues contentValues = new ContentValues ();
        contentValues.put ("status",value);
        long ret = database.update ("messages",contentValues,"id =  \"" + where_arg +"\"" ,null);
    }
    public void updatetime(String id,String time) {
        ContentValues contentValues = new ContentValues ();
        contentValues.put ("time",time);
        long ret = database.update ("messages",contentValues,"id =  \"" + id +"\"" ,null);
    }
    public void updatedeviceNu(String id,String deviceNu) {
        ContentValues contentValues = new ContentValues ();
        contentValues.put ("contact",deviceNu);
        long ret = database.update ("messages",contentValues,"id =  \"" + id +"\"" ,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion , int newVersion) {
        db.execSQL ("DROP TABLE IF EXISTS messages");
        onCreate (db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db , int oldVersion , int newVersion) {
        db.execSQL ("DROP TABLE IF EXISTS messages");
        onCreate (db);
    }
    public long insertData(String tablename,String[] colums,String[] data) {
        ContentValues contentValues = new ContentValues ();
            for (int i = 0; i < colums.length; i++) {
                contentValues.put (colums[i] , data[i]);
            }
        long ret = database.insert (tablename , null , contentValues);
            return  ret;
    }
}
