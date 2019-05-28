package zuoix.com.zoomed;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MessageReciever extends BroadcastReceiver{
    public void onReceive( Context context, Intent intent )
    {
        final Bundle bundle = intent.getExtras();

        try {
            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String n = currentMessage.getDisplayOriginatingAddress();
                    String m = currentMessage.getDisplayMessageBody();
                    Toast.makeText(context, "new Recieved Message", Toast.LENGTH_SHORT).show();

                } // end for loop
            } // bundle is null
        } catch (Exception e) {}
       }
}
