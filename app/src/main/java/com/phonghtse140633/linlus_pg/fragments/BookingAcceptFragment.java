package com.phonghtse140633.linlus_pg.fragments;

import static com.phonghtse140633.linlus_pg.constants.Constants.MAPVIEW_BUNDLE_KEY;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.MapView;
import com.phonghtse140633.linlus_pg.R;
import com.phonghtse140633.linlus_pg.adapters.BookAdapter;
import com.phonghtse140633.linlus_pg.enums.BookStatus;
import com.phonghtse140633.linlus_pg.model.Book;
import com.phonghtse140633.linlus_pg.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class BookingAcceptFragment extends Fragment {
    private RecyclerView rvBook;
    private BookAdapter bookAdapter;
    private List<Book> books = new ArrayList<>();
    private MapView mMapView;
    private CheckBox cbPending, cbAccepted;

    private boolean mLocationPermissionGranted= false;

    public BookingAcceptFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking_accept__fragement, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvBook = view.findViewById(R.id.rvBook);
        cbPending = view.findViewById(R.id.cbPending);
        cbAccepted = view.findViewById(R.id.cbAccepted);

        books.addAll(Utils.getBooksByStatus(BookStatus.PENDING));
        books.addAll(Utils.getBooksByStatus(BookStatus.ACCEPTED));

        loadBookings(books);
        cbPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbPending.isChecked()) {
                    books.addAll(Utils.getBooksByStatus(BookStatus.PENDING));
                }
                else {
                    books.removeAll(Utils.getBooksByStatus(BookStatus.PENDING));
                }
                bookAdapter.notifyDataSetChanged();
            }
        });

        cbAccepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbAccepted.isChecked()) {
                    books.addAll(Utils.getBooksByStatus(BookStatus.ACCEPTED));
                }
                else {
                    books.removeAll(Utils.getBooksByStatus(BookStatus.ACCEPTED));
                }
                bookAdapter.notifyDataSetChanged();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private void loadPendingBookings() {

    }

    private void loadBookings(List<Book> books) {
        bookAdapter = new BookAdapter(getContext(), books);
        rvBook.setAdapter(bookAdapter);
        rvBook.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
    }
}