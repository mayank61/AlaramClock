package teammayankteli1307.com.alaramclock;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    SharedPreferences sharedPreferencesring;
    //  private MyReceiver alaram;
    int hour = 0;
    int minute = 0;
    Button selecttime, stop_alarm, start_alarm, notiButton;
    MediaPlayer m1MediaPlayer;
    String string = " No Alaram  is Set";
    SetAlaram inst;
    Context context;
    RadioGroup ringgroup;
    RadioButton ring1, ring2, ring3, ring4, ring5, ring6, ring7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alaram);
        selecttime = findViewById(R.id.timeselect);
        ringgroup = findViewById(R.id.ringgroup);
        ring1 = findViewById(R.id.ring1);
        ring2 = findViewById(R.id.ring2);
        ring3 = findViewById(R.id.ring3);
        ring4 = findViewById(R.id.ring4);
        ring5 = findViewById(R.id.ring5);
        ring6 = findViewById(R.id.ring6);
        ring7 = findViewById(R.id.ring7);
        stop_alarm = (Button) findViewById(R.id.offbutton);
        start_alarm = (Button) findViewById(R.id.onbutton);
        alarmTextView = (TextView) findViewById(R.id.textalaram);
        notiButton = findViewById(R.id.onbutton2);
        this.context = this;
        sharedPreferencesring = getSharedPreferences("Alaram", MODE_PRIVATE);
        sharedPreferencesring = getSharedPreferences("type", MODE_PRIVATE);
        if (sharedPreferencesring.contains("Alaramonn")) {
            alarmTextView.setText(sharedPreferencesring.getString("Alaramonn", null));
        } else {
            alarmTextView.setText(string);
        }

        final Intent myIntent = new Intent(this.context, MyReceiver.class);
        final Intent myIntent1 = new Intent(this.context, QuestionReciver.class);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Calendar calendar = Calendar.getInstance();


        start_alarm.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (minute == 0 && hour == 0) {
                    Toast.makeText(getApplicationContext(), "Please Select Time", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!(m1MediaPlayer == null)) {
                    m1MediaPlayer.reset();
                    m1MediaPlayer.stop();
                }

                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);


                if (ringgroup.getCheckedRadioButtonId() == ring1.getId()) {
                    myIntent1.putExtra("Ring", 1);
                } else if (ringgroup.getCheckedRadioButtonId() == ring2.getId()) {
                    myIntent1.putExtra("Ring", 2);

                } else if (ringgroup.getCheckedRadioButtonId() == ring3.getId()) {
                    myIntent1.putExtra("Ring", 3);

                } else if (ringgroup.getCheckedRadioButtonId() == ring4.getId()) {
                    myIntent1.putExtra("Ring", 4);

                } else if (ringgroup.getCheckedRadioButtonId() == ring5.getId()) {
                    myIntent1.putExtra("Ring", 5);

                } else if (ringgroup.getCheckedRadioButtonId() == ring6.getId()) {
                    myIntent1.putExtra("Ring", 6);

                } else if (ringgroup.getCheckedRadioButtonId() == ring7.getId()) {
                    myIntent1.putExtra("Ring", 7);

                }
                myIntent1.putExtra("extra", "yes");
                SharedPreferences.Editor editor = sharedPreferencesring.edit();

                editor.putString("Alaramonn", setAlarmText(calendar));
                editor.putString("type", "Ques");
                editor.apply();

                setAlarmText(calendar);

                pending_intent = PendingIntent.getBroadcast(SetAlaram.this, 0, myIntent1, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pending_intent);
                Toast.makeText(getApplicationContext(), "Your Alaram is Set Now", Toast.LENGTH_SHORT).show();


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
                if (!(m1MediaPlayer == null)) {
                    m1MediaPlayer.reset();
                    m1MediaPlayer.stop();
                }

                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);


                if (ringgroup.getCheckedRadioButtonId() == ring1.getId()) {
                    myIntent.putExtra("Ring", 1);
                } else if (ringgroup.getCheckedRadioButtonId() == ring2.getId()) {
                    myIntent.putExtra("Ring", 2);

                } else if (ringgroup.getCheckedRadioButtonId() == ring3.getId()) {
                    myIntent.putExtra("Ring", 3);

                } else if (ringgroup.getCheckedRadioButtonId() == ring4.getId()) {
                    myIntent.putExtra("Ring", 4);

                } else if (ringgroup.getCheckedRadioButtonId() == ring5.getId()) {
                    myIntent.putExtra("Ring", 5);

                } else if (ringgroup.getCheckedRadioButtonId() == ring6.getId()) {
                    myIntent.putExtra("Ring", 6);

                } else if (ringgroup.getCheckedRadioButtonId() == ring7.getId()) {
                    myIntent.putExtra("Ring", 7);

                }
                myIntent.putExtra("extra", "yes");
                SharedPreferences.Editor editor = sharedPreferencesring.edit();

                editor.putString("Alaramonn", setAlarmText(calendar));
                editor.putString("type", "Noti");
                editor.apply();

                setAlarmText(calendar);

                pending_intent = PendingIntent.getBroadcast(SetAlaram.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pending_intent);
                Toast.makeText(getApplicationContext(), "Your Alaram is Set Now", Toast.LENGTH_SHORT).show();

            }
        });


        stop_alarm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {


                if (alarmTextView.getText().equals(string)) {
                    Toast.makeText(getApplicationContext(), "No Alaram Found", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (alarmTextView.getText().equals("Alaram Cancelled")) {
                    Toast.makeText(getApplicationContext(), "Already Removed", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!alarmTextView.getText().toString().isEmpty()) {

                    if (sharedPreferencesring.getString("type", null).equals("Ques")) {
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, minute);


                        myIntent1.putExtra("extra", "no");
                        myIntent1.putExtra("Ring", 1);

                        pending_intent = PendingIntent.getBroadcast(SetAlaram.this, 0, myIntent1, PendingIntent.FLAG_UPDATE_CURRENT);

                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() * 50 * 24 * 12 * 30 * 60, AlarmManager.INTERVAL_DAY, pending_intent);
                        alarmTextView.setText("Alaram Cancelled");
                        SharedPreferences.Editor editor = sharedPreferencesring.edit();
                        editor.remove("Alaramonn");
                        editor.remove("type");
                        editor.apply();
                        return;
                    }

                    // sendBroadcast(myIntent);

                    //  alarmManager.cancel(pending_intent);
                    myIntent.putExtra("Ring", 1);
                    myIntent.putExtra("extra", "no");
                    SharedPreferences.Editor editor = sharedPreferencesring.edit();
                    editor.remove("Alaramonn");
                    editor.remove("type");
                    editor.apply();
                    setAlarmText(calendar);
                    alarmTextView.setText("Alaram Cancelled");
                    pending_intent = PendingIntent.getBroadcast(SetAlaram.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() * 1000 * 12 * 31 * 24 * 60 * 60, AlarmManager.INTERVAL_DAY, pending_intent);
                    // sendBroadcast(myIntent);

                    //  alarmManager.cancel(pending_intent);
                }
            }
        });
        selecttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                if (!(m1MediaPlayer == null)) {
                    m1MediaPlayer.reset();
                    m1MediaPlayer.stop();
                }

            }
        });

        ringgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (!(m1MediaPlayer == null)) {
                    m1MediaPlayer.reset();
                    m1MediaPlayer.stop();
                }

                if (radioGroup.getCheckedRadioButtonId() == ring1.getId()) {

                    m1MediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ringtone1);
                } else if (radioGroup.getCheckedRadioButtonId() == ring2.getId()) {

                    m1MediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ringtone2);

                } else if (radioGroup.getCheckedRadioButtonId() == ring3.getId()) {

                    m1MediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ringtone3);

                } else if (radioGroup.getCheckedRadioButtonId() == ring4.getId()) {
                    ;
                    m1MediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ringtone4);

                } else if (radioGroup.getCheckedRadioButtonId() == ring5.getId()) {

                    m1MediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ringtone5);

                } else if (radioGroup.getCheckedRadioButtonId() == ring6.getId()) {

                    m1MediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ringtone6);
                } else if (radioGroup.getCheckedRadioButtonId() == ring7.getId()) {

                    m1MediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ringtone7);

                }
                m1MediaPlayer.setLooping(false);
                m1MediaPlayer.start();

            }
        });

    }

    public String setAlarmText(Calendar c) {
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        alarmTextView.setText(timeText);
        return timeText;

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
        start_alarm.setEnabled(true);
        notiButton.setEnabled(true);
    }
}