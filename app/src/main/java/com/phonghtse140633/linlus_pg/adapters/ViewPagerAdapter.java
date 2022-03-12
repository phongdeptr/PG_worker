package com.phonghtse140633.linlus_pg.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.phonghtse140633.linlus_pg.fragments.BookingAcceptFragment;
import com.phonghtse140633.linlus_pg.fragments.BookingCancelFragment;
import com.phonghtse140633.linlus_pg.fragments.BookingComplete;
import com.phonghtse140633.linlus_pg.fragments.BookingPendingFragment;
import com.phonghtse140633.linlus_pg.fragments.BookingRejectFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return fragments.get(0);
            }
            case 1: {
                return fragments.get(1);
            }
            case 2: {
                return fragments.get(2);

            }
            case 3: {
                return fragments.get(3);
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: {
                return "Accepted";
            }
            case 1: {
                return "Pending";
            }
            case 2: {
                return "Canceled";

            }
            case 3: {
                return "Completed";
            }
        }
        return "Accepted";
    }

    public void addFragment(Fragment fragment, String title) {
        titles.add(title);
        fragments.add(fragment);
    }
}
