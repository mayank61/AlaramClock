package teammayankteli1307.com.alaramclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Button setAlaram1;

    Button counttimer, stopwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAlaram1 = findViewById(R.id.SetAlaram);

        stopwatch = findViewById(R.id.StopWatch);
        counttimer = findViewById(R.id.countimer);
        setAlaram1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   DialogFragment timePicker = new TimePickerFragment();
                // timePicker.show(getSupportFragmentManager(), "time picker");
                Intent intent = new Intent(MainActivity.this, SetAlaram.class);
                startActivity(intent);
            }
        });
        counttimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CountTimer.class);
                startActivity(intent);
            }
        });
        stopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StopWatch.class);
                startActivity(intent);
            }
        });
    }

    //  @Ove// public void onTimeSet(TimePicker timePicker, int i, int i1) {
    //      time.setText("Hours"+i+"Minute"+i1);
    // }
}