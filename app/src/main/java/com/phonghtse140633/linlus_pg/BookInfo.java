package com.phonghtse140633.linlus_pg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.phonghtse140633.linlus_pg.enums.BookStatus;
import com.phonghtse140633.linlus_pg.interfaces.IBookingAction;
import com.phonghtse140633.linlus_pg.model.Book;
import com.phonghtse140633.linlus_pg.model.Customer;
import com.phonghtse140633.linlus_pg.model.PhotoService;
import com.phonghtse140633.linlus_pg.utils.Utils;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BookInfo extends AppCompatActivity implements IBookingAction {
    Book book;
    MaterialButton btnAccept;
    MaterialButton btnReject, btnCancel;
    TextView tvStatus;
    TextView captureLocation;
    TextView checkInDateTime;
    TextView checkInPrice;
    TextView checkOutDateTime;
    TextView serviceName;
    TextView booker;
    TextView deliveryFormatOptions, deliveryLocation, deliveryDate;
    MaterialCardView action_bar;
    View footerSpace;
    private static final int FILE_SELECT_CODE = 0;
    int SELECT_PICTURE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        init();
    }

    private void init() {
        MaterialCardView cardView = findViewById(R.id.cardUseAvatar);
        cardView.setCardBackgroundColor(Color.TRANSPARENT);
        cardView.setCardElevation(0);
        book = Utils.getBooking(this.getIntent().getIntExtra("bookId",0));
        Log.w("book_info_id ", book.getId()+"");
        booker = findViewById(R.id.tvBookerName);
        serviceName = findViewById(R.id.tvCheckInServiceValue);
        captureLocation = findViewById(R.id.tvCaptureLocValue);
        checkInDateTime = findViewById(R.id.tvCheckInTimeValues);
        checkOutDateTime = findViewById(R.id.tvCheckOutTimeValues);
        checkInPrice = findViewById(R.id.tvCheckInPriceValues);
        tvStatus = findViewById(R.id.tvBookStatusValue);

        deliveryFormatOptions = findViewById(R.id.tvDeliveryFormatOptions);
        deliveryLocation = findViewById(R.id.tvDeliveryLocation);
        deliveryDate = findViewById(R.id.tvDeliveryDateValue);
        action_bar = findViewById(R.id.action_bar);
        footerSpace = findViewById(R.id.footerSpace);
        btnCancel = findViewById(R.id.btnCancel);
        btnAccept = findViewById(R.id.btnAccept);
        btnReject = findViewById(R.id.btnReject);
        if(book.getStatus().equals(BookStatus.PENDING)){
            btnAccept.setVisibility(View.VISIBLE);
            btnReject.setVisibility(View.VISIBLE);
            btnCancel.setVisibility(View.GONE);
        }else{
            btnAccept.setVisibility(View.GONE);
            btnReject.setVisibility(View.GONE);
        }
        config();
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    private void config() {
        PhotoService service = book.getBookingService();
        Customer customer = book.getCustomer();
        if(book.getStatus().equals(BookStatus.PENDING)){
            btnAccept.setVisibility(View.VISIBLE);
            btnReject.setVisibility(View.VISIBLE);
            btnCancel.setVisibility(View.GONE);
        }
        btnAccept.setOnClickListener(view -> {
            openAcceptDialog(book.getId());
        });
        btnReject.setOnClickListener(view -> {
            openRejectDialog(book.getId());
        });
        List<String> options = new ArrayList<>();
        options.add("\u2022 2x A4 picture\n");
        options.add("\u2022 2x A3 picture\n");
        options.add("\u2022 3x A2 picture\n");
        StringBuilder builder = new StringBuilder();
        options.forEach((option -> {
            builder.append(option);
        }));
        booker.setText(customer.getName());
        serviceName.setText(service.getName());
        tvStatus.setText(book.getStatus().name());
        checkInPrice.setText(book.getPrice() + " USD");
        checkInDateTime.setText(book.getBookingDate().format(DateTimeFormatter.ofPattern("MMM dd,yyyy")) + " " + book.getStartingTime().format(DateTimeFormatter.ofPattern("hh:ss")));
        checkOutDateTime.setText(book.getBookingDate().plusDays(2).format(DateTimeFormatter.ofPattern("MMM dd,yyyy")));
        captureLocation.setText(book.getLocation());
        deliveryLocation.setText(book.getDeliveryLocation());
        deliveryDate.setText(book.getDeliveryDate().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
        deliveryFormatOptions.setText(builder.toString());
        if(book.getStatus().equals(BookStatus.ACCEPTED)){
            btnCancel.setVisibility(View.VISIBLE);
        }else{
            btnCancel.setVisibility(View.GONE);
        }
        btnCancel.setOnClickListener(view -> {
            openCancelDialog(book.getId());});
    }

    @Override
    public void onDetailed(Object id) {
    }

    @Override
    public void onAccepted(Object id) {
        book.setStatus(BookStatus.ACCEPTED);
        tvStatus.setText(BookStatus.ACCEPTED.name());
        btnAccept.setVisibility(View.GONE);
        btnCancel.setVisibility(View.VISIBLE);
        btnReject.setVisibility(View.GONE);
        action_bar.setVisibility(View.VISIBLE);
        footerSpace.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRejected(Object id) {
        book.setStatus(BookStatus.REJECTED);
        tvStatus.setText(BookStatus.REJECTED.name());
        btnAccept.setVisibility(View.GONE);
        btnReject.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
    }

    @Override
    public void onCanceled(Object id) {
        book.setStatus(BookStatus.CANCELED);
        tvStatus.setText(BookStatus.CANCELED.name());
        btnAccept.setVisibility(View.GONE);
        btnReject.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
    }

    @Override
    public void onCompleted(Object id) {

    }

    public void openCancelDialog(int id){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_cancel);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextInputEditText editText = dialog.findViewById(R.id.etCancelReason);
        MaterialButton btnConfirm = dialog.findViewById(R.id.btnCancelConfirm);
        MaterialButton btnCancel = dialog.findViewById(R.id.btnDismiss);
        btnConfirm.setOnClickListener((view1) ->{
            Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
            if(!pattern.matcher(editText.getText().toString()).matches()){
                Toast.makeText(this, "Enter reason to continue", Toast.LENGTH_LONG).show();
                return;
            }else {
                Toast.makeText(this, "Cancel Success",Toast.LENGTH_LONG).show();
                onCanceled(id);
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(view1 -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    public void openRejectDialog(int id){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_cancel);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tvCancelTitle = dialog.findViewById(R.id.tvCancelTitle);
        TextInputEditText editText = dialog.findViewById(R.id.etCancelReason);
        MaterialButton btnConfirm = dialog.findViewById(R.id.btnCancelConfirm);
        MaterialButton btnCancel = dialog.findViewById(R.id.btnDismiss);
        tvCancelTitle.setText("Reject Book Dialog");
        btnConfirm.setOnClickListener((view1) ->{
            Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
            if(!pattern.matcher(editText.getText().toString()).matches()){
                Toast.makeText(this, "Enter reason to continue", Toast.LENGTH_LONG).show();
            }else {
                onRejected(id);
                Toast.makeText(this, "Reject Success",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(view1 -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    public void openAcceptDialog(int id){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_accept_booking);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        MaterialButton btnConfirm = dialog.findViewById(R.id.btnCancelConfirm);
        MaterialButton btnCancel = dialog.findViewById(R.id.btnDismiss);
        btnConfirm.setOnClickListener((view1) ->{
            Toast.makeText(this, "Accept Success",Toast.LENGTH_LONG).show();
            onAccepted(id);
            dialog.dismiss();
        });
        btnCancel.setOnClickListener(view1 -> {
            dialog.dismiss();
        });
        dialog.show();
    }
}
