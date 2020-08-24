package teammayankteli1307.com.alaramclock;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Parcelable;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {



            String state = intent.getExtras().getString("extra");

        int a  = intent.getIntExtra("Ring",0);

            Intent serviceIntent = new Intent(context, RingtonePlayingService.class);
            serviceIntent.putExtra("extra", state);

serviceIntent.putExtra("ring",a);

            context.startService(serviceIntent);
            ////   Intent intent2 = new Intent(context,SetAlaram.class);
        }//  context.startActivity(intent2);

}
