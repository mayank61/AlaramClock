package teammayankteli1307.com.alaramclock;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class QuestionAskingService extends Service {
    private boolean isRunning;
    static PendingIntent pendingIntent;

    static MediaPlayer mMediaPlayer;
    int startId;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MyActivity", "In the Richard service");
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent intent11 = new Intent(getApplicationContext(), QuestionActivity.class);


        String state = intent.getExtras().getString("extra");




        assert state != null;
        switch (state) {
            case "no":
                startId = 0;
                break;
            case "yes":
                startId = 1;
                break;
            default:
                startId = 0;
                break;
        }


        if (!this.isRunning && startId == 1) {


            // Log.e("if there was not sound ", " and you want start");

            // pendingIntent = PendingIntent.getActivity(this, 0, intent1, 0);

            //   NotificationHelper notificationHelper = new NotificationHelper(this);
            // NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
            //   notificationHelper.getManager().notify(1, nb.build());


           pendingIntent = PendingIntent.getActivity(this, 0, intent11, 0);

           NotificationHelper notificationHelper = new NotificationHelper(this);
           NotificationCompat.Builder nb = notificationHelper.getChannelNotification2();
            notificationHelper.getManager().notify(1, nb.build());
            mMediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.ringtone1);

            mMediaPlayer.setLooping(true);
            mMediaPlayer.start();



        } else if (!this.isRunning && startId == 0) {
            Log.e("if there was not sound ", " and you want end");

            this.isRunning = false;
            this.startId = 0;

        } else if (this.isRunning && startId == 1) {
            Log.e("if there is sound ", " and you want start");

            this.isRunning = true;
            this.startId = 0;

        } else {
            Log.e("if there is sound ", " and you want end");

            // mMediaPlayer.stop();
            //  mMediaPlayer.reset();

            this.isRunning = false;
            this.startId = 0;
        }


        Log.e("MyActivity", "In the service");

        return START_NOT_STICKY;

    }


    @Override
    public void onDestroy() {
        Log.e("JSLog", "on destroy called");
        super.onDestroy();

        this.isRunning = false;
    }


}
