package teammayankteli1307.com.alaramclock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {
    TextView ques,ans1,ans2,ans3,ans4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ques = findViewById(R.id.textques);
        ans1 = findViewById(R.id.textans1);
        ans2 = findViewById(R.id.textans2);
        ans3 = findViewById(R.id.textans3);
        ans4 = findViewById(R.id.textans4);
        ques.setText(R.string.ques);
        ans1.setText(R.string.ans1);

        ans3.setText(R.string.ans3);

        ans4.setText(R.string.ans4);

        ans2.setText(R.string.ans2);
        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(QuestionAskingService.mMediaPlayer == null)) {
                    QuestionAskingService.mMediaPlayer.stop();
                    QuestionAskingService.mMediaPlayer.reset();
                    Toast.makeText(getApplicationContext(), "Ring Off", Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        });
        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Wroung",Toast.LENGTH_SHORT).show();
            }
        });

        ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Wroung",Toast.LENGTH_SHORT).show();

            }
        });
        ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Wroung",Toast.LENGTH_SHORT).show();
            }
        });

    }

}