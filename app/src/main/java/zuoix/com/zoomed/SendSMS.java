package zuoix.com.zoomed;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import zuoix.com.zoomed.activities.SharedPref;

public class SendSMS {
    Context context;
    private String DestNu;
    private String message;
    String command;
    broadcastRecieved bdcst;
    String siName;
    SharedPref sp;

    public SendSMS(Context context ,String msg , String command_name,broadcastRecieved bdcst) {
        this.context = context;
        this.message = msg;
        this.command = command_name;
        this.bdcst = bdcst;
        sp = new SharedPref(context);
        this.DestNu = sp.getDestinationNumber();
        siName= sp.getDefaultSim();
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
        send.putExtra ("command",command);
        PendingIntent sentPI = PendingIntent.getBroadcast (context , 0 ,send , 0);

        Intent deliver = new Intent (DELIVERED);
        deliver.putExtra ("command",command);
        PendingIntent deliveredPI = PendingIntent.getBroadcast (context , 0 , deliver , 0);

        try{
            Method method = Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class);
            method.setAccessible(true);
            Object param = method.invoke(null, siName);

            for(int i = 0; i <param.getClass().getMethods().length;i++ ){
                System.out.println("       ///////////////////     "+param.getClass().getMethods()[i].getName()+"\n");
            }

            System.out.println("        /////// "+siName);
            if(param == null){
                System.out.println("        /////// null param");
            }

            method = Class.forName("com.android.internal.telephony.ISms$Stub").getDeclaredMethod("asInterface", IBinder.class);
            method.setAccessible(true);
            Object stubObj = method.invoke(null, param);

            for(int i = 0; i <stubObj.getClass().getMethods().length;i++ ){
                System.out.println("       /////     "+stubObj.getClass().getMethods()[i].getName()+"\n");
            }

            if (Build.VERSION.SDK_INT < 18) {
                method = stubObj.getClass().getMethod("sendTextWithExtraParamsForSubscriber", String.class, String.class, String.class, PendingIntent.class, PendingIntent.class);
                method.invoke(stubObj, DestNu, null, message, sentPI, deliveredPI);
            } else {
                method = stubObj.getClass().getMethod("sendTextWithExtraParamsForSubscriber", String.class, String.class, String.class, String.class, PendingIntent.class, PendingIntent.class);
                method.invoke(stubObj, context, DestNu, null, message, sentPI, deliveredPI);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    BroadcastReceiver messagedelivered = new BroadcastReceiver () {
        @Override
        public void onReceive(Context context , Intent intent) {
            String command = intent.getExtras ().getString ("command");
            switch (getResultCode ()) {
                case Activity.RESULT_OK:
                    Toast.makeText (context , command+"request delivered" ,
                            Toast.LENGTH_SHORT).show ();
                    break;
            }

        }
    };

        BroadcastReceiver messageSent = new BroadcastReceiver () {
            @Override
            public void onReceive(Context context , Intent intent) {
                String command = intent.getExtras ().getString ("command");
                switch (getResultCode ()) {
                    case Activity.RESULT_OK:
                        Toast.makeText (context , command+" request sent succesfully" ,
                                Toast.LENGTH_SHORT).show ();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText (context , "Generic failure" ,
                                Toast.LENGTH_SHORT).show ();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText (context , "No service" ,
                                Toast.LENGTH_SHORT).show ();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText (context , "Null PDU" ,
                                Toast.LENGTH_SHORT).show ();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText (context , "Radio off" ,
                                Toast.LENGTH_SHORT).show ();
                        break;
                    default:
                        Toast.makeText (context , "Not sent" ,
                                Toast.LENGTH_SHORT).show ();
                        break;
                }

            }
        };

            void msgSent(String id , String value) {
                     bdcst.updateview ();
            }


}


