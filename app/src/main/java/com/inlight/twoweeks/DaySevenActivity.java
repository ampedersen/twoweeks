package com.inlight.twoweeks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static com.inlight.twoweeks.MainActivity.addCompletedTask;
import static com.inlight.twoweeks.MainActivity.setTierLevel;

public class DaySevenActivity extends AppCompatActivity {

    TextView mWelcomeText;
    Button mCompleteTask;
    ArrayList<Integer> mCompletedTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_seven);

        mCompleteTask = (Button) findViewById(R.id.day_seven_button_complete);
        mCompleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCompletedTask(R.id.task_seven);
                setTierLevel(6);
                Intent completeTask = new Intent(DaySevenActivity.this, MainActivity.class);
                startActivity(completeTask);
            }
        });
    }
}
