package com.hotel.HotelManagementSystem.service;

import com.hotel.HotelManagementSystem.model.Booking;

public interface BookingServiceInterface {

    boolean bookRoom(Booking booking);
    boolean cancelBooking(Long bookingId);
    Booking getBookingDetails(Long bookingId);


}
