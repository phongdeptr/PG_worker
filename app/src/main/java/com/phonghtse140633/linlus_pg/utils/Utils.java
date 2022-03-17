package com.phonghtse140633.linlus_pg.utils;

import android.app.Service;

import com.phonghtse140633.linlus_pg.R;
import com.phonghtse140633.linlus_pg.enums.BookStatus;
import com.phonghtse140633.linlus_pg.model.Book;
import com.phonghtse140633.linlus_pg.model.Customer;
import com.phonghtse140633.linlus_pg.model.PhotoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class Utils {
    private static List<Book> books = new ArrayList<>();
    private static List<PhotoService> services = new ArrayList<>();
    public static List<Customer> customers = new ArrayList<>();
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
            books.add(new Book(40, services.get(3), LocalDate.parse("2022-04-03"), LocalTime.of(8, 30), 280, BookStatus.ACCEPTED, "Ho Guom"));
        }
        Map<Integer, String> location = new HashMap<>();
        location.put(1, "23 Lac Long Quan Quan 11, TPHCM");
        location.put(2, "23 Cach Mang Thang Tam, TPHCM");
        location.put(3, "23 Hoang Kieu, Ha Noi");
        getCustomers();
        books.stream().forEach(book -> {

            Random random = new Random();
            int range = random.nextInt(10 - 1) + 1;
            int locationChoice = random.nextInt(3 - 1) + 1;
            int customerChoice = random.nextInt(customers.size() - 0) + 0;
            book.setDeliveryDate(LocalDate.now().plusDays(range));
            book.setDeliveryLocation(location.get(locationChoice));
            book.setCustomer(customers.get(customerChoice));
        });
        return books;
    }

    public static List<Customer> getCustomers(){
        if(customers == null){
            customers = new ArrayList<>();
        }else if (customers.isEmpty()){
            customers.add(new Customer(1, "Hoang Phong", R.drawable.avatar));
            customers.add(new Customer(2, "Kim Phung", R.drawable.avatar));
            customers.add(new Customer(3, "Chi Huy", R.drawable.avatar));
            customers.add(new Customer(4, "Phuong Thao", R.drawable.avatar));
            customers.add(new Customer(5, "Anh Quoc", R.drawable.avatar));
            customers.add(new Customer(6, "Hung Dung", R.drawable.avatar));
            customers.add(new Customer(7, "Tung Chua", R.drawable.avatar));
        }
        return customers;
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
