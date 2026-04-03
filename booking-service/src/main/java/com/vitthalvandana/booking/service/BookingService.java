package com.vitthalvandana.booking.service;

import java.util.HashMap;
import java.util.Map;

public class BookingService {
    private Map<Integer, Booking> bookings = new HashMap<>();
    private int currentId = 1;

    public Booking createBooking(Booking booking) {
        booking.setId(currentId++);
        bookings.put(booking.getId(), booking);
        return booking;
    }

    public Booking getBooking(int id) {
        return bookings.get(id);
    }

    public Booking updateBooking(int id, Booking booking) {
        if (bookings.containsKey(id)) {
            booking.setId(id);
            bookings.put(id, booking);
            return booking;
        }
        return null;
    }

    public boolean deleteBooking(int id) {
        return bookings.remove(id) != null;
    }
}

class Booking {
    private int id;
    private String customerName;
    private String bookingDate;

    // Getters and setters for id, customerName, and bookingDate

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
}