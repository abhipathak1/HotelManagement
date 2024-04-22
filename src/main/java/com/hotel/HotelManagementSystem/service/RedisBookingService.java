package com.hotel.HotelManagementSystem.service;

import com.hotel.HotelManagementSystem.model.Booking;
import com.hotel.HotelManagementSystem.repository.RedisBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "db_flag", havingValue = "mysql")
public class RedisBookingService {


    @Autowired
    private RedisBookingRepository redisBookingRepository;

    public boolean bookRoom(Booking booking) {
        redisBookingRepository.save(booking);
        return true;
    }

    public boolean cancelBooking(Long bookingId) {
        if (redisBookingRepository.existsById(bookingId)) {
            redisBookingRepository.deleteById(bookingId);
            return true;
        }
        return false;
    }

    public Booking getBookingDetails(Long bookingId) {
        return redisBookingRepository.findById(bookingId).orElse(null);
    }
}