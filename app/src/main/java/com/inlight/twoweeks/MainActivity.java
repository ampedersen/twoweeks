package com.inlight.twoweeks;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static int tierLevel = 1;

    private static ArrayList<Integer> completedTasks = new ArrayList<>();

    private static CountDownTimer timeLock;
    public static final int TIME_TRESHOLD_IN_MIN = 720;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_speaker_notes_white_24dp,
            R.drawable.ic_layers_white_24dp,
            R.drawable.ic_settings_white_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        FragmentPagerAdapter adapter = new CustomPagerAdapter(getSupportFragmentManager(), this);

        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();

        tabLayout.getTabAt(1).select();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    public static int getTierLevel() {
        return tierLevel;
    }

    public static void setTierLevel(int newTierlevel) {
        tierLevel = newTierlevel;
    }

    public static ArrayList<Integer> getCompletedTasks() {
        return completedTasks;
    }

    public static void addCompletedTask(Integer taskCompleted) {
        completedTasks.add(taskCompleted);
    }
}
