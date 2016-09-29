package com.inlight.twoweeks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static com.inlight.twoweeks.MainActivity.addCompletedTask;
import static com.inlight.twoweeks.MainActivity.setTierLevel;

public class DaySixActivity extends AppCompatActivity {

    TextView mWelcomeText;
    Button mCompleteTask;
    ArrayList<Integer> mCompletedTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_six);

        mCompleteTask = (Button) findViewById(R.id.day_six_button_complete);
        mCompleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCompletedTask(R.id.task_six);
                setTierLevel(5);
                Intent completeTask = new Intent(DaySixActivity.this, MainActivity.class);
                startActivity(completeTask);
            }
        });
    }
}
