package com.phonghtse140633.linlus_pg.model;



import com.phonghtse140633.linlus_pg.enums.BookStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class Book {
    private int id;
    private PhotoService bookingService;
    private LocalDate bookingDate;
    private LocalTime startingTime;
    private float price;
    private BookStatus status;
    private String location;

    public Book(int id, PhotoService bookingService, LocalDate bookingDate, LocalTime startingTime, float price, BookStatus status, String location) {
        this.id = id;
        this.bookingService = bookingService;
        this.bookingDate = bookingDate;
        this.startingTime = startingTime;
        this.price = price;
        this.status = status;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PhotoService getBookingService() {
        return bookingService;
    }

    public void setBookingService(PhotoService bookingService) {
        this.bookingService = bookingService;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
