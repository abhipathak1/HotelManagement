package com.hotel.HotelManagementSystem.repository;

import com.hotel.HotelManagementSystem.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean bookRoom(Booking booking) {
        String sql = "INSERT INTO bookings (room_id, guest_name) VALUES (?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, booking.getRoom().getId(), booking.getGuestName());
        return rowsAffected > 0;
    }

    public boolean cancelBooking(Long bookingId) {
        String sql = "DELETE FROM bookings WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, bookingId);
        return rowsAffected > 0;
    }

//    public Booking getBookingDetails(Long bookingId) {
//        String sql = "SELECT * FROM bookings WHERE id = ?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{bookingId}, new BookingRowMapper());
//    }
}
