package com.inlight.twoweeks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static com.inlight.twoweeks.MainActivity.addCompletedTask;
import static com.inlight.twoweeks.MainActivity.getCompletedTasks;
import static com.inlight.twoweeks.MainActivity.setTierLevel;

public class DayFourActivity extends AppCompatActivity {

    TextView mWelcomeText;
    Button mCompleteTask;
    ArrayList<Integer> mCompletedTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_four);

        mCompleteTask = (Button) findViewById(R.id.day_four_button_complete);
        mCompleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCompletedTask(R.id.task_four);
                mCompletedTasks = getCompletedTasks();
                Log.i("test", "CompletedTasks: " + getCompletedTasks().toString());
                if (mCompletedTasks.contains(R.id.task_four) && mCompletedTasks.contains(R.id.task_five)) {
                    setTierLevel(4);
                }
                Intent completeTask = new Intent(DayFourActivity.this, MainActivity.class);
                startActivity(completeTask);
            }
        });
    }
}
