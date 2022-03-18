package com.phonghtse140633.linlus_pg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.phonghtse140633.linlus_pg.adapters.ViewPagerAdapter;
import com.phonghtse140633.linlus_pg.fragments.BookingAcceptFragment;
import com.phonghtse140633.linlus_pg.fragments.BookingCancelFragment;
import com.phonghtse140633.linlus_pg.fragments.BookingComplete;
import com.phonghtse140633.linlus_pg.fragments.BookingPendingFragment;
import com.phonghtse140633.linlus_pg.fragments.BookingRejectFragment;
import com.phonghtse140633.linlus_pg.fragments.FragmentCalendar;
import com.phonghtse140633.linlus_pg.fragments.FragmentHome;
import com.phonghtse140633.linlus_pg.fragments.FragmentPersonal;
import com.phonghtse140633.linlus_pg.fragments.FragmentService;
import com.phonghtse140633.linlus_pg.utils.FileUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        setUpBottomNav();
    }

    private void setUpBottomNav() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.page_home:
                        fragment = new FragmentHome();
                        item.setChecked(true);
                        break;
                    case R.id.page_services:
                        fragment = new FragmentService();
                        item.setChecked(true);
                        break;
                    case R.id.page_calendar:
                        fragment = new FragmentCalendar();
                        item.setChecked(true);
                        break;
                    case R.id.page_settings:
                        fragment = new FragmentPersonal();
                        item.setChecked(true);
                        break;
                }
                loadFragment(fragment);
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.page_home);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flMain, fragment)
                .commit();
    }
}