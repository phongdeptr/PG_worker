package com.phonghtse140633.linlus_pg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.phonghtse140633.linlus_pg.adapters.ViewPagerAdapter;
import com.phonghtse140633.linlus_pg.fragments.BookingAcceptFragment;
import com.phonghtse140633.linlus_pg.fragments.BookingCancelFragment;
import com.phonghtse140633.linlus_pg.fragments.BookingComplete;
import com.phonghtse140633.linlus_pg.fragments.BookingPendingFragment;
import com.phonghtse140633.linlus_pg.fragments.BookingRejectFragment;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tlStatus;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
    }

    private void bind(){
        viewPager = findViewById(R.id.viewPager);
        tlStatus = findViewById(R.id.tlStatus);
        tlStatus.setupWithViewPager(viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new BookingAcceptFragment(), "Accepted");
        viewPagerAdapter.addFragment(new BookingPendingFragment(), "Pending");
        viewPagerAdapter.addFragment(new BookingComplete(), "Completed");
        viewPagerAdapter.addFragment(new BookingRejectFragment(), "Rejected");
        viewPagerAdapter.addFragment(new BookingCancelFragment(), "Canceled");
        viewPager.setAdapter(viewPagerAdapter);
    }
}