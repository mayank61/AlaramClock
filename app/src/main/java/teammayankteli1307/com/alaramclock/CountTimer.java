package teammayankteli1307.com.alaramclock;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class CountTimer extends AppCompatActivity {
    TextView timetv;
    Button startpause, reset;
    private CountDownTimer mcountDownTimer;
    Boolean mruning  = false;
    boolean paused=true;
    private long MiLSECLeft;
    long m = 0;
    EditText minute, second;
    Ringtone ringtone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_timer);
        timetv = findViewById(R.id.timetv);
        startpause = findViewById(R.id.start);
        minute = findViewById(R.id.Minute);
        second = findViewById(R.id.Second);
        reset = findViewById(R.id.Reset);
        startpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                  if(paused)
                  {
                      if (second.getText().toString().isEmpty()) {
                          Toast.makeText(getApplicationContext(), "EnterTime", Toast.LENGTH_SHORT).show();
                          return;
                      }
                      if (!minute.getText().toString().isEmpty()) {
                          m = (Long.parseLong(minute.getText().toString())) * 60 * 1000;

                      }

                      MiLSECLeft = (Long.parseLong(second.getText().toString())) * 1000 + m+1000;

                  }
                   if (mruning) {
                       pauseTimer();

                   } else {
                       startTimer();
                       paused=false;

                   }

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
                minute.getText().clear();
                second.getText().clear();
            }
        });
        updateTimer();


    }

    private void pauseTimer() {
        mcountDownTimer.cancel();
        mruning = false;
        //  startpause.setVisibility(View.VISIBLE);
        startpause.setText("Start");

    }

    private void resetTimer() {
        MiLSECLeft = 00;
        updateTimer();
        pauseTimer();
        timetv.setTextColor(Color.parseColor("#0B0A0A"));
        paused=true;
        ringtone.stop();

        ////   startpause.setVisibility(View.VISIBLE);
        //  reset.setVisibility(View.INVISIBLE);

    }

    private void startTimer() {

        mcountDownTimer = new CountDownTimer(MiLSECLeft, 1000) {


            @Override
            public void onTick(long l) {
                if (l < 5000) {
                    timetv.setTextColor(Color.parseColor("#FB1504"));
                }


                MiLSECLeft = l;
                updateTimer();


            }


            @Override
            public void onFinish() {
                mruning = false;
                //startpause.setVisibility(View.INVISIBLE);
                startpause.setText("Start");
                timetv.setTextColor(Color.parseColor("#0B0A0A"));
                //   reset.setVisibility(View.VISIBLE);
              //  Uri alaramuri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
              //  if(alaramuri==null)
              //  {
                 Uri   alaramuri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
              //  }
                ringtone=RingtoneManager.getRingtone(getApplicationContext(),alaramuri);
                ringtone.play();


            }
        }.start();
        mruning = true;
        startpause.setText("Pause");


    }

    private void updateTimer() {
        int minutes = (int) (MiLSECLeft / 1000) / 60;
        int seconds = (int) (MiLSECLeft / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timetv.setText(timeLeftFormatted);

    }
}