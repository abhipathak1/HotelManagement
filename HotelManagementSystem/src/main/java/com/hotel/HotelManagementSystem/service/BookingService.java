package com.hotel.HotelManagementSystem.service;

import com.hotel.HotelManagementSystem.model.Booking;
import com.hotel.HotelManagementSystem.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;


@Service
@ConditionalOnProperty(name ="db_flag", havingValue = "mysql")
public class BookingService implements BookingServiceInterface {
    @Autowired
    private BookingRepository bookingRepository;

    public boolean bookRoom(Booking booking) {
        return bookingRepository.bookRoom(booking);
    }

    public boolean cancelBooking(Long bookingId) {
        return bookingRepository.cancelBooking(bookingId);
    }

    public Booking getBookingDetails(Long bookingId) {
        return bookingRepository.getBookingDetails(bookingId);
    }
}
