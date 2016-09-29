package com.inlight.twoweeks;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import static com.inlight.twoweeks.MainActivity.getTierLevel;

/**
 * Created by anderspedersen on 09/09/16.
 */
public class TwoWeekFragment extends Fragment implements View.OnClickListener {

    private ImageButton taskOne, taskTwo, taskThree, taskFour, taskFive, taskSix, taskSeven;
    private ImageButton taskEight, taskNine, taskTen, taskEleven, taskTwelve;
    private ImageButton taskThirteen, taskFourteen;

    private TextView unlockMessage;

    private LinearLayout week_one, week_two;

    private ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.two_weeks, container, false);

        taskOne = (ImageButton) rootView.findViewById(R.id.task_one);
        taskOne.setOnClickListener(this);
        taskTwo = (ImageButton) rootView.findViewById(R.id.task_two);
        taskTwo.setOnClickListener(this);
        taskThree = (ImageButton) rootView.findViewById(R.id.task_three);
        taskThree.setOnClickListener(this);
        taskFour = (ImageButton) rootView.findViewById(R.id.task_four);
        taskFour.setOnClickListener(this);
        taskFive = (ImageButton) rootView.findViewById(R.id.task_five);
        taskFive.setOnClickListener(this);
        taskSix = (ImageButton) rootView.findViewById(R.id.task_six);
        taskSix.setOnClickListener(this);
        taskSeven = (ImageButton) rootView.findViewById(R.id.task_seven);
        taskSeven.setOnClickListener(this);
        taskEight = (ImageButton) rootView.findViewById(R.id.task_eight);
        taskEight.setOnClickListener(this);
        taskNine = (ImageButton) rootView.findViewById(R.id.task_nine);
        taskNine.setOnClickListener(this);
        taskTen = (ImageButton) rootView.findViewById(R.id.task_ten);
        taskTen.setOnClickListener(this);
        taskEleven = (ImageButton) rootView.findViewById(R.id.task_eleven);
        taskEleven.setOnClickListener(this);
        taskTwelve = (ImageButton) rootView.findViewById(R.id.task_twelve);
        taskTwelve.setOnClickListener(this);
        taskThirteen = (ImageButton) rootView.findViewById(R.id.task_thirteen);
        taskThirteen.setOnClickListener(this);
        taskFourteen = (ImageButton) rootView.findViewById(R.id.task_fourteen);
        taskFourteen.setOnClickListener(this);


        unlockMessage = (TextView) rootView.findViewById(R.id.unlock_message);

        week_one = (LinearLayout) rootView.findViewById(R.id.week_one);
        week_two = (LinearLayout) rootView.findViewById(R.id.week_two);
        scrollView = (ScrollView) rootView.findViewById(R.id.scrollView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUnlocked(getTierLevel());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("test", "onResume just got called, with TierLevel: " + getTierLevel());
        setUnlocked(getTierLevel());
        if(getTierLevel() == 6) {
            week_two.setVisibility(View.VISIBLE);

            // Start 1 second timer
            new CountDownTimer(1000, 20) {
                public void onTick(long millisUntilFinished) {
                    // Nothing...
                }

                // When over, start smoothScroll
                public void onFinish() {
                    scrollView.smoothScrollTo( 0, week_two.getBottom() );
                }
            }.start();
            unlockMessage.setText("Good luck with Week 2!")
            ;
        }
    }

    private void setUnlocked(int tierLevel) {
        switch (tierLevel) {
            case 1:
                taskOne.setBackgroundColor(getResources().getColor(R.color.primary_color));
                break;
            case 2:
                taskOne.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskTwo.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskThree.setBackgroundColor(getResources().getColor(R.color.primary_color));
                break;
            case 3:
                taskOne.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskTwo.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskThree.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskFour.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskFive.setBackgroundColor(getResources().getColor(R.color.primary_color));
                break;
            case 4:
                taskOne.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskTwo.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskThree.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskFour.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskFive.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskSix.setBackgroundColor(getResources().getColor(R.color.primary_color));
                break;
            case 5:
                taskOne.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskTwo.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskThree.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskFour.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskFive.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskSix.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskSeven.setBackgroundColor(getResources().getColor(R.color.primary_color));
                break;
            case 6:
                taskOne.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskTwo.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskThree.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskFour.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskFive.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskSix.setBackgroundColor(getResources().getColor(R.color.primary_color));
                taskSeven.setBackgroundColor(getResources().getColor(R.color.primary_color));



        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.task_one:
                // Launch task 1 activity
                Intent dayOne = new Intent(getActivity(), DayOneActivity.class);
                startActivity(dayOne);
                break;

            case R.id.task_two:
                // Launch task 2 activity
                Intent dayTwo = new Intent(getActivity(), DayTwoActivity.class);
                startActivity(dayTwo);
                break;

            case R.id.task_three:
                // Launch task 3 activity
                Intent dayThree = new Intent(getActivity(), DayThreeActivity.class);
                startActivity(dayThree);
                break;

            case R.id.task_four:
                //Launch task 4 activity
                Intent dayFour = new Intent(getActivity(), DayFourActivity.class);
                startActivity(dayFour);
                break;

            case R.id.task_five:
                //Launch task 5 activity
                Intent dayFive = new Intent(getActivity(), DayFiveActivity.class);
                startActivity(dayFive);
                break;

            case R.id.task_six:
                //Launch task 6 activity
                Intent daySix = new Intent(getActivity(), DaySixActivity.class);
                startActivity(daySix);
                break;

            case R.id.task_seven:
                //Launch task 7 activity
                Intent daySeven = new Intent(getActivity(), DaySevenActivity.class);
                startActivity(daySeven);
                break;





        }
    }
}
