package com.phonghtse140633.linlus_pg.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phonghtse140633.linlus_pg.R;
import com.phonghtse140633.linlus_pg.adapters.BookAdapter;
import com.phonghtse140633.linlus_pg.enums.BookStatus;
import com.phonghtse140633.linlus_pg.model.Book;
import com.phonghtse140633.linlus_pg.utils.Utils;

import java.util.List;

public class BookingPendingFragment extends Fragment {
    private RecyclerView rvBook;
    private BookAdapter bookAdapter;
    private List<Book> books = null;

    public BookingPendingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking_pending, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bind();
        rvBook = view.findViewById(R.id.rvBook);
        rvBook.setAdapter(bookAdapter);
        rvBook.setLayoutManager(new LinearLayoutManager(this.getContext()));
        super.onViewCreated(view, savedInstanceState);
    }

    private void bind(){
        books = Utils.getBooksByStatus(BookStatus.PENDING);
        bookAdapter = new BookAdapter(this.getContext(), books);
    }
}