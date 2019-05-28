package zuoix.com.zoomed;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SendSMS {
    Context context;
    private String address;
    private String message;
    private DBHandler dbHandler;
    private SQLiteDatabase db;
    String id;
    broadcastRecieved bdcst;

    public SendSMS(Context context , String phoneNo , String msg , String id,broadcastRecieved bdcst) {
        this.context = context;
        this.address = phoneNo;
        this.message = msg;
        this.id = id;
        dbHandler = new DBHandler (context , null , null , 1);
        db = dbHandler.getWritableDatabase ();
        this.bdcst = bdcst;
    }

    public interface broadcastRecieved{
        void updateview();
    }

    public void sendSMS() {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";
        context.registerReceiver (messageSent,new IntentFilter (SENT));
        context.registerReceiver (messagedelivered,new IntentFilter (DELIVERED));

        Intent send = new Intent (SENT);
        send.putExtra ("id",id);
        PendingIntent sentPI = PendingIntent.getBroadcast (context , 0 ,send , 0);

        Intent deliver = new Intent (DELIVERED);
        deliver.putExtra ("id",id);
        PendingIntent deliveredPI = PendingIntent.getBroadcast (context , 0 , deliver , 0);
        SmsManager sms = SmsManager.getDefault ();
        sms.sendTextMessage (address , null , message , sentPI , deliveredPI);
        }

    BroadcastReceiver messagedelivered = new BroadcastReceiver () {
        @Override
        public void onReceive(Context context , Intent intent) {
            String mid = intent.getExtras ().getString ("id");
            switch (getResultCode ()) {
                case Activity.RESULT_OK:
                    Toast.makeText (context , "SMS delivered " ,
                            Toast.LENGTH_SHORT).show ();
                    msgSent ( mid,"2");
                    break;
            }

        }
    };

        BroadcastReceiver messageSent = new BroadcastReceiver () {
            @Override
            public void onReceive(Context context , Intent intent) {
                String mid = intent.getExtras ().getString ("id");
                switch (getResultCode ()) {
                    case Activity.RESULT_OK:
                        Toast.makeText (context , "SMS sent" ,
                                Toast.LENGTH_SHORT).show ();
                        msgSent ( mid,"1");
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText (context , "Generic failure" ,
                                Toast.LENGTH_SHORT).show ();
                        msgSent ( mid,"0");
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText (context , "No service" ,
                                Toast.LENGTH_SHORT).show ();
                        msgSent ( mid,"0");
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText (context , "Null PDU" ,
                                Toast.LENGTH_SHORT).show ();
                        msgSent ( mid,"0");
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText (context , "Radio off" ,
                                Toast.LENGTH_SHORT).show ();
                        msgSent ( mid,"0");
                        break;
                    default:
                        Toast.makeText (context , "Not sent" ,
                                Toast.LENGTH_SHORT).show ();
                        msgSent ( mid,"0");
                        break;
                }

            }
        };

            void msgSent(String id , String value) {
                    dbHandler.updatemessageStatus (id , value);
                    bdcst.updateview ();
            }


}


