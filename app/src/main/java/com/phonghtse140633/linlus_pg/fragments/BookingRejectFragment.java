package com.phonghtse140633.linlus_pg.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phonghtse140633.linlus_pg.R;
import com.phonghtse140633.linlus_pg.adapters.BookAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookingRejectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingRejectFragment extends Fragment {
    private RecyclerView rvBook;
    private BookAdapter bookAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookingRejectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookingRejectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookingRejectFragment newInstance(String param1, String param2) {
        BookingRejectFragment fragment = new BookingRejectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View inflate = inflater.inflate(R.layout.fragment_booking_reject_fragment, container, false);
        rvBook = inflate.findViewById(R.id.rvBook);
        rvBook.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvBook.setAdapter(bookAdapter);
        return inflate;
    }
}