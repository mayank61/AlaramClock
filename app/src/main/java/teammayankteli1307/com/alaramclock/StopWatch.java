package teammayankteli1307.com.alaramclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class StopWatch extends AppCompatActivity {


    TextView timer ;
    Button start, pause, reset ,lap;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    ArrayAdapter<String> arrayAdapter;
    List<String> listtime;
    int Seconds, Minutes, MilliSeconds ;
    ListView list;
    int i=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        timer = (TextView)findViewById(R.id.tvTimer);
        start = (Button)findViewById(R.id.btStart);
        pause = (Button)findViewById(R.id.btPause);
        list=findViewById(R.id.listtime);
        reset = (Button)findViewById(R.id.btReset);
        lap = findViewById(R.id.btlap);
        listtime = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<>(this,R.layout.list_item,listtime);
        list.setAdapter(arrayAdapter);

        handler = new Handler() ;

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                reset.setEnabled(false);

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimeBuff += MillisecondTime;

                handler.removeCallbacks(runnable);

                reset.setEnabled(true);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MillisecondTime = 0L ;
                StartTime = 0L ;
                TimeBuff = 0L ;
                UpdateTime = 0L ;
                Seconds = 0 ;
                Minutes = 0 ;
                MilliSeconds = 0 ;

                timer.setText("00:00:00");
                listtime.clear();
                arrayAdapter.notifyDataSetChanged();
                i=1;

            }
        });
        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listtime.add(i+": "+(String)timer.getText());
                arrayAdapter.notifyDataSetChanged();
                i++;

            }
        });

    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            timer.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };

}



