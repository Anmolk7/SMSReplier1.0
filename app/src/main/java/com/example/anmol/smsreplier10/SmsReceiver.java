package com.example.anmol.smsreplier10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by Anmol on 1/22/2017.
 */

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();

        if(bundle!= null)
        {
           Object[] pdus= (Object[]) bundle.get("pdus");//portable description object
            String senderNumber=null;
            for(int i=0; i<pdus.length;i++)
            {
               android.telephony.gsm.SmsMessage sms= android.telephony.gsm.SmsMessage.createFromPdu((byte[]) pdus[i]);

                senderNumber=sms.getOriginatingAddress();
                String message=sms.getDisplayMessageBody();
                Toast.makeText(context,"From"+senderNumber+"Message:"+message,Toast.LENGTH_SHORT).show();


            }
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(senderNumber,null,"soory",null,null);
        }
    }
}
