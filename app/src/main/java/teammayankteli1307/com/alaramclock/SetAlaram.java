package teammayankteli1307.com.alaramclock;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Set;

public class SetAlaram extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    AlarmManager alarmManager;
    private PendingIntent pending_intent;

    //  private TimePicker alarmTimePicker;
    private TextView alarmTextView;

    //  private MyReceiver alaram;
    int hour = 0;
    int minute = 0;
    Button selecttime, stop_alarm, start_alarm, notiButton;


    SetAlaram inst;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alaram);
        notiButton = findViewById(R.id.onbutton2);
        this.context = this;
        selecttime = findViewById(R.id.timeselect);
        alarmTextView = (TextView) findViewById(R.id.textalaram);
        final Intent myIntent = new Intent(this.context, MyReceiver.class);
        final Intent myIntent1 = new Intent(this.context, QuestionReciver.class);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Calendar calendar = Calendar.getInstance();

        start_alarm = (Button) findViewById(R.id.onbutton);
        start_alarm.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (minute == 0 && hour == 0) {
                    Toast.makeText(getApplicationContext(), "Please Select Time", Toast.LENGTH_SHORT).show();
                    return;
                }


                calendar.add(Calendar.SECOND, 3);


                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

                myIntent1.putExtra("extra", "yes");

                pending_intent = PendingIntent.getBroadcast(SetAlaram.this, 1, myIntent1, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);


                setAlarmText(calendar);


            }


        });
        notiButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (minute == 0 && hour == 0) {
                    Toast.makeText(getApplicationContext(), "Please Select Time", Toast.LENGTH_SHORT).show();
                    return;
                }


                calendar.add(Calendar.SECOND, 3);


                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

                myIntent.putExtra("extra", "yes");

                pending_intent = PendingIntent.getBroadcast(SetAlaram.this, 1, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);


                setAlarmText(calendar);

            }
        });


        stop_alarm = (Button) findViewById(R.id.offbutton);
        stop_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (pending_intent == null) {
                    Toast.makeText(getApplicationContext(), "No Alaram Found", Toast.LENGTH_SHORT).show();
                    return;
                }
                myIntent.putExtra("extra", "no");
                sendBroadcast(myIntent);
                alarmManager.cancel(pending_intent);
                setAlarmText(calendar);
                alarmTextView.setText("Alaram Cancled");
            }
        });
        selecttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

    }

    public void setAlarmText(Calendar c) {
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        alarmTextView.setText(timeText);
    }


    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }


    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        hour = i;
        minute = i1;
    }
}