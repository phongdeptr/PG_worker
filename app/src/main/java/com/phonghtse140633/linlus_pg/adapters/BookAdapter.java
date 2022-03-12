package com.phonghtse140633.linlus_pg.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.phonghtse140633.linlus_pg.MainActivity2;
import com.phonghtse140633.linlus_pg.R;
import com.phonghtse140633.linlus_pg.enums.BookStatus;
import com.phonghtse140633.linlus_pg.interfaces.IBookingAction;
import com.phonghtse140633.linlus_pg.model.Book;
import com.phonghtse140633.linlus_pg.model.PhotoService;
import com.phonghtse140633.linlus_pg.utils.Utils;

import java.time.format.DateTimeFormatter;
import java.util.List;

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
        checkInDate = new SpannableString("Check in Date: " + book.getBookingDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        checkInDate.setSpan(new StyleSpan(Typeface.BOLD),0,"Check in Date:".length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        checkInTime = new SpannableString("Starting Time: " +  book.getStartingTime());
        checkInTime.setSpan(new StyleSpan(Typeface.BOLD),0,"Starting Time:".length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        checkInPrice = new SpannableString("Booking price: " + book.getPrice() + "$");
        checkInPrice.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, "Booking price: ".length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (holder != null) {
            switch (holder.getItemViewType()) {
                case ACCEPTED: {
                    AcceptBookViewHolder acceptBookViewHolder = (AcceptBookViewHolder) holder;
                    acceptBookViewHolder.tvCheckInPrice.setText(checkInPrice);
                    acceptBookViewHolder.tvBookedDate.setText(checkInDate);
                    acceptBookViewHolder.tvBookedTime.setText(checkInTime);
                    acceptBookViewHolder.tvServiceName.setText(book.getBookingService().getName());
                    acceptBookViewHolder.tvCheckInPrice.setText(checkInPrice);
                    acceptBookViewHolder.ivServiceAvatar.setImageResource(service.getRepresentativeImg());
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
                    cancelBookViewHolder.ivServiceAvatar.setImageResource(service.getRepresentativeImg());
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
                    break;
                }
                case COMPLETED: {
                    CompleteBookViewHolder completeBookViewHolder = (CompleteBookViewHolder) holder;
                    completeBookViewHolder.tvCheckInPrice.setText(checkInPrice);
                    completeBookViewHolder.tvBookedDate.setText(checkInDate);
                    completeBookViewHolder.tvBookedTime.setText(checkInTime);
                    completeBookViewHolder.tvServiceName.setText(book.getBookingService().getName());
                    completeBookViewHolder.tvCheckInPrice.setText(checkInPrice);
                    completeBookViewHolder.ivServiceAvatar.setImageResource(service.getRepresentativeImg());
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
        Intent intent = new Intent(context, MainActivity2.class);
        intent.putExtra("bookId", book.getId());
        context.startActivity(intent);
    }

    @Override
    public void onAccepted(Object id) {
        Book book = Utils.getBooking((int) id);
        book.setStatus(BookStatus.ACCEPTED);
        bookList = Utils.getBooksByStatus(BookStatus.ACCEPTED);
        this.notifyDataSetChanged();
        Intent intent = new Intent(context, MainActivity2.class);
        context.startActivity(intent);
    }

    @Override
    public void onRejected(Object id) {
        Book book = Utils.getBooking((int) id);
        book.setStatus(BookStatus.REJECTED);
        bookList = Utils.getBooksByStatus(BookStatus.REJECTED);
        this.notifyDataSetChanged();
        Intent intent = new Intent(context, MainActivity2.class);
        context.startActivity(intent);

    }

    @Override
    public void onCompleted(Object id) {
        Book book = Utils.getBooking((int) id);
        book.setStatus(BookStatus.COMPLETED);
        bookList = Utils.getBooksByStatus(BookStatus.REJECTED);
        this.notifyDataSetChanged();
        Intent intent = new Intent(context, MainActivity2.class);
        context.startActivity(intent);
    }

    public class AcceptBookViewHolder extends RecyclerView.ViewHolder {
        TextView tvServiceName;
        TextView tvBookedTime, tvBookedDate;
        TextView tvCheckInPrice;
        ImageView ivServiceAvatar;
        Chip chipDetailAction;
        public AcceptBookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvBookedDate = itemView.findViewById(R.id.tvBookedDate);
            tvBookedTime = itemView.findViewById(R.id.tvBookedTime);
            tvCheckInPrice = itemView.findViewById(R.id.tvCheckInPrice);
            ivServiceAvatar = itemView.findViewById(R.id.ivServiceAvatar);
            chipDetailAction = itemView.findViewById(R.id.chipDetailAction);
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
        public PendingBookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvBookedDate = itemView.findViewById(R.id.tvBookedDate);
            tvBookedTime = itemView.findViewById(R.id.tvBookedTime);
            tvCheckInPrice = itemView.findViewById(R.id.tvCheckInPrice);
            ivServiceAvatar = itemView.findViewById(R.id.ivServiceAvatar);

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
        public CompleteBookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvBookedDate = itemView.findViewById(R.id.tvBookedDate);
            tvBookedTime = itemView.findViewById(R.id.tvBookedTime);
            tvCheckInPrice = itemView.findViewById(R.id.tvCheckInPrice);
            ivServiceAvatar = itemView.findViewById(R.id.ivServiceAvatar);
        }
    }

}
