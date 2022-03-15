package com.phonghtse140633.linlus_pg.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phonghtse140633.linlus_pg.R;
import com.phonghtse140633.linlus_pg.adapters.BookAdapter;
import com.phonghtse140633.linlus_pg.adapters.CalendarCellAdapter;
import com.phonghtse140633.linlus_pg.enums.BookStatus;
import com.phonghtse140633.linlus_pg.model.Book;
import com.phonghtse140633.linlus_pg.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FragmentCalendar extends Fragment {
    View view;
    private RecyclerView rvCalendar, rvBooks;
    private List<Book> books = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar, container, false);
        initWidgets();
        setWeekView();
        setEvent();
        return view;
    }

    private void setEvent() {
        books = Utils.getBooksByStatus(BookStatus.ACCEPTED);
        BookAdapter bookAdapter = new BookAdapter(getContext(), books);
        rvBooks.setAdapter(bookAdapter);
        rvBooks.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    private void initWidgets() {
        rvCalendar = view.findViewById(R.id.rvCalendar);
        rvBooks = view.findViewById(R.id.rvBooks);
    }

    private void setWeekView() {
        List<Integer> days = new ArrayList<>();
        days.add(13);
        days.add(14);
        days.add(15);
        days.add(16);
        days.add(17);
        days.add(18);
        days.add(19);

        CalendarCellAdapter calendarAdapter = new CalendarCellAdapter(days);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        rvCalendar.setLayoutManager(layoutManager);
        rvCalendar.setAdapter(calendarAdapter);
    }

}
