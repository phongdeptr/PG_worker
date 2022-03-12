package com.phonghtse140633.linlus_pg.fragments;

import static com.phonghtse140633.linlus_pg.constants.Constants.MAPVIEW_BUNDLE_KEY;
import static com.phonghtse140633.linlus_pg.constants.Constants.PERMISSIONS_REQUEST_ENABLE_GPS;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.phonghtse140633.linlus_pg.R;
import com.phonghtse140633.linlus_pg.adapters.BookAdapter;
import com.phonghtse140633.linlus_pg.enums.BookStatus;
import com.phonghtse140633.linlus_pg.model.Book;
import com.phonghtse140633.linlus_pg.utils.Utils;
import com.phonghtse140633.linlus_pg.constants.Constants;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookingAcceptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingAcceptFragment extends Fragment {
    private RecyclerView rvBook;
    private BookAdapter bookAdapter;
    private List<Book> books = null;
    private MapView mMapView;

    private boolean mLocationPermissionGranted= false;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookingAcceptFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookingAcceptFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookingAcceptFragment newInstance(String param1, String param2) {
        BookingAcceptFragment fragment = new BookingAcceptFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onResume() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        books = Utils.getBooksByStatus(BookStatus.ACCEPTED);
        bookAdapter = new BookAdapter(getContext(), books);
        rvBook.setAdapter(bookAdapter);
        rvBook.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
    }
}