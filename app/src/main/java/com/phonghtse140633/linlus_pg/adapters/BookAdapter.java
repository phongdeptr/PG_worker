package com.phonghtse140633.linlus_pg.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.phonghtse140633.linlus_pg.BookInfo;
import com.phonghtse140633.linlus_pg.MainActivity2;
import com.phonghtse140633.linlus_pg.R;
import com.phonghtse140633.linlus_pg.enums.BookStatus;
import com.phonghtse140633.linlus_pg.interfaces.IBookingAction;
import com.phonghtse140633.linlus_pg.model.Book;
import com.phonghtse140633.linlus_pg.model.PhotoService;
import com.phonghtse140633.linlus_pg.utils.Utils;

import org.w3c.dom.Text;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  implements IBookingAction {
    private Context context;
    private List<Book> bookList;
    private static final int ACCEPTED = 1;
    private static final int REJECTED = 2;
    private static final int CANCELED = 3;
    private static final int COMPLETED = 4;
    private static final int PENDING = 5;
    public BookAdapter(Context context, List<Book> bookList) {
        this.bookList = bookList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ACCEPTED: {
                return new AcceptBookViewHolder(LayoutInflater.from(context).inflate(R.layout.item_book_accept, parent, false));
            }
            case REJECTED: {
                return new RejectBookViewHolder(LayoutInflater.from(context).inflate(R.layout.item_book_reject, parent, false));
            }

            case CANCELED: {
                return new CancelBookViewHolder(LayoutInflater.from(context).inflate(R.layout.item_book_cancel, parent, false));
            }

            case COMPLETED: {
                return new CompleteBookViewHolder(LayoutInflater.from(context).inflate(R.layout.item_book_completed, parent, false));
            }

            case PENDING: {
                return new PendingBookViewHolder(LayoutInflater.from(context).inflate(R.layout.item_book_pending, parent, false));
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return bookList == null ? 0 : bookList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Book book = bookList.get(position);
        PhotoService service = book.getBookingService();
        SpannableString checkInDate;
        SpannableString checkInTime;
        SpannableString checkInPrice;
        checkInDate = new SpannableString(book.getBookingDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
//        checkInDate.setSpan(new StyleSpan(Typeface.BOLD),0,"Check in Date:".length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        checkInTime = new SpannableString("" + book.getStartingTime());
//        checkInTime.setSpan(new StyleSpan(Typeface.BOLD),0,"Starting Time:".length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        checkInPrice = new SpannableString("" + book.getPrice());
//        checkInPrice.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, "Booking price: ".length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (holder != null) {
            switch (holder.getItemViewType()) {
                case ACCEPTED: {
                    AcceptBookViewHolder acceptBookViewHolder = (AcceptBookViewHolder) holder;
                    acceptBookViewHolder.tvCheckInPrice.setText(checkInPrice);
                    acceptBookViewHolder.tvBookedDate.setText(checkInDate);
                    acceptBookViewHolder.tvBookedTime.setText(checkInTime);
                    acceptBookViewHolder.tvServiceName.setText(book.getBookingService().getName());
                    acceptBookViewHolder.tvCheckInPrice.setText(checkInPrice);
                    //acceptBookViewHolder.ivServiceAvatar.setImageResource(service.getRepresentativeImg());
                    acceptBookViewHolder.chipDetailAction.setOnClickListener((view -> {
                        onDetailed(book.getId());
                    }));
                    break;
                }
                case CANCELED: {
                    CancelBookViewHolder cancelBookViewHolder = (CancelBookViewHolder) holder;
                    cancelBookViewHolder.tvCheckInPrice.setText(checkInPrice);
                    cancelBookViewHolder.tvBookedDate.setText(checkInDate);
                    cancelBookViewHolder.tvBookedTime.setText(checkInTime);
                    cancelBookViewHolder.tvServiceName.setText(book.getBookingService().getName());
                    cancelBookViewHolder.tvCheckInPrice.setText(checkInPrice);
                    //cancelBookViewHolder.ivServiceAvatar.setImageResource(service.getRepresentativeImg());
                    break;
                }
                case PENDING: {
                    PendingBookViewHolder pendingBookViewHolder = (PendingBookViewHolder) holder;
                    pendingBookViewHolder.tvCheckInPrice.setText(checkInPrice);
                    pendingBookViewHolder.tvBookedDate.setText(checkInDate);
                    pendingBookViewHolder.tvBookedTime.setText(checkInTime);
                    pendingBookViewHolder.tvServiceName.setText(book.getBookingService().getName());
                    pendingBookViewHolder.tvCheckInPrice.setText(checkInPrice);
                    pendingBookViewHolder.ivServiceAvatar.setImageResource(service.getRepresentativeImg());
                    pendingBookViewHolder.pending_item_container.setOnClickListener((view -> {
                        onDetailed(book.getId());
                    }));
                    pendingBookViewHolder.btnAccept.setOnClickListener(view -> {
                        openAcceptDialog(book.getId());
                    });
                    pendingBookViewHolder.btnReject.setOnClickListener(view -> {
                        openRejectDialog(book.getId()
                        );
                    });
                    break;
                }
                case COMPLETED: {
                    CompleteBookViewHolder completeBookViewHolder = (CompleteBookViewHolder) holder;
                    completeBookViewHolder.tvCheckInPrice.setText(checkInPrice);
                    completeBookViewHolder.tvBookedDate.setText(checkInDate);
                    completeBookViewHolder.tvBookedTime.setText(checkInTime);
                    completeBookViewHolder.tvServiceName.setText(book.getBookingService().getName());
                    completeBookViewHolder.tvCheckInPrice.setText(checkInPrice);
                    completeBookViewHolder.btnMoreInfo.setOnClickListener(view -> {
                        onDetailed(book.getId());
                        Log.w("Id: ",book.getId()+"");
                        Log.w("status:" , book.getStatus().name());
                    });
                    break;
                }
            }
        }
    }
    @Override
    public int getItemViewType(int position) {
        Book book = bookList.get(position);
        Log.w("book status", book.getStatus().name());
        switch (book.getStatus()) {
            case ACCEPTED:
                return ACCEPTED;
            case REJECTED:
                return REJECTED;
            case CANCELED:
                return CANCELED;
            case PENDING:
                return PENDING;
            case COMPLETED:
                return COMPLETED;
        }
        return 0;
    }

    @Override
    public void onDetailed(Object id) {
        Book book = Utils.getBooking((int) id);
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_book_info);
        TextView captureLocation = dialog.findViewById(R.id.tvCaptureLocValue);
        TextView checkInDateTime = dialog.findViewById(R.id.tvCheckInTimeValues);
        TextView checkOutDateTime = dialog.findViewById(R.id.tvCheckOutTimeValues);
        TextView serviceName = dialog.findViewById(R.id.tvCheckInServiceValue);
        TextView deliveryDate = dialog.findViewById(R.id.tvDeliveryDateValue);
        TextView deliveryLocation = dialog.findViewById(R.id.tvDeliveryLocation);
        serviceName.setText(book.getBookingService().getName());
        captureLocation.setText(book.getLocation());
        deliveryLocation.setText(book.getDeliveryLocation());
        deliveryDate.setText(book.getDeliveryDate().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
        checkInDateTime.setText(book.getBookingDate().format(DateTimeFormatter.ofPattern("MMM dd,yyyy")) + " "
                + book.getStartingTime().format(DateTimeFormatter.ofPattern("hh:ss")));
        checkOutDateTime.setText(book.getBookingDate().plusDays(2).format(DateTimeFormatter.ofPattern("MMM dd,yyyy")));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //dialog.show();
        //dialog.setCanceledOnTouchOutside(true);
        Intent intent = new Intent(context, BookInfo.class);
        intent.putExtra("bookId", book.getId());
        context.startActivity(intent);
    }

    @Override
    public void onAccepted(Object id) {
        Book book = Utils.getBooking((int) id);
        book.setStatus(BookStatus.ACCEPTED);
//        bookList = Utils.getBooksByStatus(BookStatus.ACCEPTED);
        this.notifyDataSetChanged();
//        Intent intent = new Intent(context, MainActivity2.class);
//        context.startActivity(intent);
    }

    @Override
    public void onRejected(Object id) {
        Book book = Utils.getBooking((int) id);
        book.setStatus(BookStatus.REJECTED);
//        bookList = Utils.getBooksByStatus(BookStatus.REJECTED);
        this.notifyDataSetChanged();
//        Intent intent = new Intent(context, MainActivity2.class);
//        context.startActivity(intent);

    }

    @Override
    public void onCompleted(Object id) {
        Book book = Utils.getBooking((int) id);
        book.setStatus(BookStatus.COMPLETED);
        bookList = Utils.getBooksByStatus(BookStatus.COMPLETED);
        this.notifyDataSetChanged();
//        Intent intent = new Intent(context, MainActivity2.class);
//        context.startActivity(intent);
    }

    @Override
    public void onCanceled(Object id) {
        Book book = Utils.getBooking((int) id);
        book.setStatus(BookStatus.CANCELED);
//        bookList = Utils.getBooksByStatus(BookStatus.CANCELED);
        this.notifyDataSetChanged();
    }

    public class AcceptBookViewHolder extends RecyclerView.ViewHolder {
        TextView tvServiceName;
        TextView tvBookedTime, tvBookedDate;
        TextView tvCheckInPrice;
        ImageView ivServiceAvatar;
        ImageButton chipDetailAction;
        public AcceptBookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvBookedDate = itemView.findViewById(R.id.tvBookedDate);
            tvBookedTime = itemView.findViewById(R.id.tvBookedTime);
            tvCheckInPrice = itemView.findViewById(R.id.tvCheckInPrice);
            ivServiceAvatar = itemView.findViewById(R.id.ivServiceAvatar);
            chipDetailAction = itemView.findViewById(R.id.btnMoreInfo);
        }
    }

    public class RejectBookViewHolder extends RecyclerView.ViewHolder {
        TextView tvServiceName;
        TextView tvBookedTime, tvBookedDate;
        TextView tvCheckInPrice;
        ImageView ivServiceAvatar;
        public RejectBookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvBookedDate = itemView.findViewById(R.id.tvBookedDate);
            tvBookedTime = itemView.findViewById(R.id.tvBookedTime);
            tvCheckInPrice = itemView.findViewById(R.id.tvCheckInPrice);
            ivServiceAvatar = itemView.findViewById(R.id.ivServiceAvatar);

        }
    }

    public class PendingBookViewHolder extends RecyclerView.ViewHolder {
        TextView tvServiceName;
        TextView tvBookedTime, tvBookedDate;
        TextView tvCheckInPrice;
        ImageView ivServiceAvatar;
        CardView pending_item_container;
        ImageButton btnAccept, btnReject;
        public PendingBookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvBookedDate = itemView.findViewById(R.id.tvBookedDate);
            tvBookedTime = itemView.findViewById(R.id.tvBookedTime);
            tvCheckInPrice = itemView.findViewById(R.id.tvCheckInPrice);
            ivServiceAvatar = itemView.findViewById(R.id.ivServiceAvatar);
            pending_item_container = itemView.findViewById(R.id.pending_item_container);
            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnReject = itemView.findViewById(R.id.btnReject);
        }
    }

    public class CancelBookViewHolder extends RecyclerView.ViewHolder {
        TextView tvServiceName;
        TextView tvBookedTime, tvBookedDate;
        TextView tvCheckInPrice;
        ImageView ivServiceAvatar;

        public CancelBookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvBookedDate = itemView.findViewById(R.id.tvBookedDate);
            tvBookedTime = itemView.findViewById(R.id.tvBookedTime);
            tvCheckInPrice = itemView.findViewById(R.id.tvCheckInPrice);
            ivServiceAvatar = itemView.findViewById(R.id.ivServiceAvatar);
        }
    }


    public class CompleteBookViewHolder extends RecyclerView.ViewHolder {
        TextView tvServiceName;
        TextView tvBookedTime, tvBookedDate;
        TextView tvCheckInPrice;
        ImageView ivServiceAvatar;
        ImageButton btnMoreInfo;
        public CompleteBookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvBookedDate = itemView.findViewById(R.id.tvBookedDate);
            tvBookedTime = itemView.findViewById(R.id.tvBookedTime);
            tvCheckInPrice = itemView.findViewById(R.id.tvCheckInPrice);
            ivServiceAvatar = itemView.findViewById(R.id.ivServiceAvatar);
            btnMoreInfo = itemView.findViewById(R.id.btnMoreInfo);
        }
    }

    public void openRejectDialog(int id){
        Dialog dialog = new Dialog(context);
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
                Toast.makeText(context, "Enter reason to continue", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(context, "Reject Success",Toast.LENGTH_LONG).show();
                onRejected(id);
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(view1 -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    public void openAcceptDialog(int id){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_accept_booking);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        MaterialButton btnConfirm = dialog.findViewById(R.id.btnCancelConfirm);
        MaterialButton btnCancel = dialog.findViewById(R.id.btnDismiss);
        btnConfirm.setOnClickListener((view1) ->{
            Toast.makeText(context, "Accept Success",Toast.LENGTH_LONG).show();
            onAccepted(id);
            dialog.dismiss();
        });
        btnCancel.setOnClickListener(view1 -> {
            dialog.dismiss();
        });
        dialog.show();
    }
}
