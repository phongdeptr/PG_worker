package com.phonghtse140633.linlus_pg.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.phonghtse140633.linlus_pg.R;
import com.phonghtse140633.linlus_pg.adapters.ViewPagerAdapter;

public class FragmentHome extends Fragment {
    ViewPager viewPager;
    TabLayout tlStatus;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        tlStatus = view.findViewById(R.id.tlStatus);
        setUpViewPager();
        return view;
    }

    private void setUpViewPager() {
        tlStatus.setupWithViewPager(viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new BookingAcceptFragment(), "Active");
        viewPagerAdapter.addFragment(new BookingComplete(), "Completed");
        viewPagerAdapter.addFragment(new BookingCancelFragment(), "Canceled");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
