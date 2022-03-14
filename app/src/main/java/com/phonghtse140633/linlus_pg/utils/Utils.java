package com.phonghtse140633.linlus_pg.utils;

import android.app.Service;

import com.phonghtse140633.linlus_pg.R;
import com.phonghtse140633.linlus_pg.enums.BookStatus;
import com.phonghtse140633.linlus_pg.model.Book;
import com.phonghtse140633.linlus_pg.model.PhotoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Utils {
    private static List<Book> books = new ArrayList<>();
    private static List<PhotoService> services = new ArrayList<>();

    public static List<Book> getBooks() {
        List<PhotoService> services = getServices();
        if (books.isEmpty()) {
            books.add(new Book(1, services.get(1), LocalDate.parse("2022-12-03"), LocalTime.of(8, 30), 82, BookStatus.ACCEPTED, "Ho Guom"));
            books.add(new Book(2, services.get(2), LocalDate.parse("2022-12-04"), LocalTime.of(8, 30), 83, BookStatus.PENDING, "Ho Guom"));
            books.add(new Book(3, services.get(2), LocalDate.parse("2022-12-05"), LocalTime.of(8, 30), 84, BookStatus.PENDING, "Ho Guom"));
            books.add(new Book(4, services.get(2), LocalDate.parse("2022-12-06"), LocalTime.of(8, 30), 85, BookStatus.ACCEPTED, "Ho Guom"));
            books.add(new Book(5, services.get(1), LocalDate.parse("2022-12-07"), LocalTime.of(8, 30), 86, BookStatus.CANCELED, "Ho Guom"));
            books.add(new Book(6, services.get(3), LocalDate.parse("2022-12-08"), LocalTime.of(8, 30), 87, BookStatus.COMPLETED, "Ho Guom"));
            books.add(new Book(7, services.get(4), LocalDate.parse("2022-12-01"), LocalTime.of(8, 30), 88, BookStatus.COMPLETED, "Ho Guom"));
//            books.add(new Book(8, services.get(4), LocalDate.parse("2022-08-01"), LocalTime.of(8, 30), 89, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(9, services.get(1), LocalDate.parse("2022-10-03"), LocalTime.of(8, 30), 91, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(10, services.get(2), LocalDate.parse("2022-10-03"), LocalTime.of(8, 30), 93, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(11, services.get(3), LocalDate.parse("2022-10-03"), LocalTime.of(8, 30), 95, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(12, services.get(4), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 97, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(13, services.get(4), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 99, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(14, services.get(4), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 102, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(15, services.get(4), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 103, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(16, services.get(3), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 200, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(17, services.get(3), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 820, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(18, services.get(4), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 810, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(19, services.get(1), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 804, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(20, services.get(1), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 805, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(22, services.get(2), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 801, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(23, services.get(2), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 802, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(24, services.get(2), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 803, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(25, services.get(2), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 804, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(26, services.get(2), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 803, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(27, services.get(2), LocalDate.parse("2022-03-03"), LocalTime.of(8, 30), 801, BookStatus.COMPLETED, "Ho Guom"));
//            books.add(new Book(28, services.get(3), LocalDate.parse("2022-03-03"), LocalTime.of(8, 30), 802, BookStatus.COMPLETED, "Ho Guom"));
//            books.add(new Book(29, services.get(3), LocalDate.parse("2022-03-03"), LocalTime.of(8, 30), 801, BookStatus.COMPLETED, "Ho Guom"));
//            books.add(new Book(30, services.get(3), LocalDate.parse("2022-03-03"), LocalTime.of(8, 30), 801, BookStatus.COMPLETED, "Ho Guom"));
//            books.add(new Book(31, services.get(3), LocalDate.parse("2022-03-03"), LocalTime.of(8, 30), 801, BookStatus.COMPLETED, "Ho Guom"));
//            books.add(new Book(32, services.get(3), LocalDate.parse("2022-03-03"), LocalTime.of(8, 30), 801, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(33, services.get(3), LocalDate.parse("2022-03-03"), LocalTime.of(8, 30), 801, BookStatus.CANCELED, "Ho Guom"));
//            books.add(new Book(34, services.get(3), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 801, BookStatus.CANCELED, "Ho Guom"));
//            books.add(new Book(35, services.get(3), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 780, BookStatus.CANCELED, "Ho Guom"));
//            books.add(new Book(36, services.get(3), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 680, BookStatus.CANCELED, "Ho Guom"));
//            books.add(new Book(37, services.get(3), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 580, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(38, services.get(3), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 480, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(39, services.get(3), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 380, BookStatus.ACCEPTED, "Ho Guom"));
//            books.add(new Book(40, services.get(3), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 280, BookStatus.ACCEPTED, "Ho Guom"));
        }
        return books;
    }

    public static List<PhotoService> getServices() {
        if (services.isEmpty()) {
            services.add(new PhotoService(1l, "Event Party Moment", "Event", 100, 4.6f, R.drawable.event_photo_al_2, null, 1));
            services.add(new PhotoService(2l, "Birthday Party Moment", "Event", 100, 4.4f, R.drawable.event_photo_al_3, null, 1));
            services.add(new PhotoService(3l, "Food Marketing Capture", "Marketing", 100, 4.2f, R.drawable.food_photo_1, null, 1));
            services.add(new PhotoService(4l, "Landscape Capture 1", "Landscape", 100, 4.1f, R.drawable.landscape_al_1, null, 1));
            services.add(new PhotoService(5l, "Landscape Capture 2", "Landscape", 100, 4.3f, R.drawable.event_photo_al_3, null, 1));
        }
        return services;
    }

    public static PhotoService getServiceById(long id) {
        Optional<PhotoService> first = services.stream().filter(photoService -> photoService.getId() == id).findFirst();
        return first.orElse(null);
    }

    public static Book getBooking(int id) {
        Optional<Book> first = books.stream().filter(book -> book.getId() == id).findFirst();
        return first.orElse(null);
    }

    public static List<Book> getBooksByStatus(BookStatus status) {
        List<Book> first = getBooks().stream().filter(book -> book.getStatus().equals(status)).collect(Collectors.toList());
        return first;
    }
}
