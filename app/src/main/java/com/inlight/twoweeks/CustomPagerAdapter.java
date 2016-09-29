package com.inlight.twoweeks;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by anderspedersen on 09/09/16.
 */
public class CustomPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] {"News", "Two Weeks", "Options"};
    private Context context;

    public CustomPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NewsFragment();
        } else if (position == 1) {
            return new TwoWeekFragment();
        } else {
            return new OptionsFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}
