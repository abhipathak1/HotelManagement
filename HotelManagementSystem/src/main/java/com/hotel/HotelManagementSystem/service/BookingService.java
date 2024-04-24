package com.hotel.HotelManagementSystem.service;

import com.hotel.HotelManagementSystem.model.Booking;
import com.hotel.HotelManagementSystem.model.Room;
import com.hotel.HotelManagementSystem.repository.BookingRepository;
import com.hotel.HotelManagementSystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;


@Service
@ConditionalOnProperty(name ="db_flag", havingValue = "mysql")
public class BookingService implements BookingServiceInterface {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    public boolean bookRoom(Booking booking) {
        Room room = roomRepository.getRoomById(booking.getRoom().getId());
        if (room != null && room.isAvailable()) {
            room.setAvailable(false);
            roomRepository.updateRoom(room);
            return bookingRepository.bookRoom(booking);
        }
        return false;
    }

    public boolean cancelBooking(Long bookingId) {
        return bookingRepository.cancelBooking(bookingId);
    }

    public Booking getBookingDetails(Long bookingId) {
        return bookingRepository.getBookingDetails(bookingId);
    }
}
