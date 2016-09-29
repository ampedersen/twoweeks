package com.inlight.twoweeks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static com.inlight.twoweeks.MainActivity.addCompletedTask;
import static com.inlight.twoweeks.MainActivity.getCompletedTasks;
import static com.inlight.twoweeks.MainActivity.setTierLevel;

public class DayTwoActivity extends AppCompatActivity {

    TextView mWelcomeText;
    Button mCompleteTask;
    ArrayList<Integer> mCompletedTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_two);

        mCompleteTask = (Button) findViewById(R.id.day_two_button_complete);
        mCompleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCompletedTask(R.id.task_two);
                Log.i("test", "CompletedTasks: " + getCompletedTasks().toString());
                mCompletedTasks = getCompletedTasks();
                if (mCompletedTasks.contains(R.id.task_two) && mCompletedTasks.contains(R.id.task_three)) {
                    setTierLevel(3);
                }
                Intent completeTask = new Intent(DayTwoActivity.this, MainActivity.class);
                startActivity(completeTask);
            }
        });

    }
}
