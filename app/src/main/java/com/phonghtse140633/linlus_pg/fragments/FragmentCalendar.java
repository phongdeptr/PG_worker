package com.phonghtse140633.linlus_pg.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phonghtse140633.linlus_pg.R;
import com.phonghtse140633.linlus_pg.adapters.CalendarCellAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FragmentCalendar extends Fragment {
    private RecyclerView rvCalendar;
    private ListView lvCalendar;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar, container, false);
        initWidgets();
        setWeekView();
        return view;
    }

    private void initWidgets()
    {
        rvCalendar = view.findViewById(R.id.rvCalendar);
        lvCalendar = view.findViewById(R.id.lvEvent);
    }

    private void setWeekView()
    {
        List<Integer> days = new ArrayList<>();
        days.add(13);
        days.add(14);
        days.add(15);
        days.add(16);
        days.add(17);
        days.add(18);
        days.add(19);

        CalendarCellAdapter calendarAdapter = new CalendarCellAdapter(days);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),7);
        rvCalendar.setLayoutManager(layoutManager);
        rvCalendar.setAdapter(calendarAdapter);
        //setEventAdpater();
    }

}
