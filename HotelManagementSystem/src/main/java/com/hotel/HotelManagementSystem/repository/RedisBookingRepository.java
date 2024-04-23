package com.hotel.HotelManagementSystem.repository;

import com.hotel.HotelManagementSystem.model.Booking;
import com.hotel.HotelManagementSystem.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

@Repository
public class RedisBookingRepository{

    @Autowired
    private Jedis jedis;

    public boolean bookRoom(Booking booking) {
        String bookingKey = "booking:" + booking.getId();
        jedis.hset(bookingKey, "roomId", String.valueOf(booking.getRoom().getId()));
        jedis.hset(bookingKey, "guestName", booking.getGuestName());
        return true;
    }

    public boolean cancelBooking(Long bookingId) {
        String bookingKey = "booking:" + bookingId;
        if (jedis.exists(bookingKey)) {
            jedis.del(bookingKey);
            return true;
        } else {
            return false;
        }
    }

    public Booking getBookingDetails(Long bookingId) {
        String bookingKey = "booking:" + bookingId;
        if (jedis.exists(bookingKey)) {
            String roomId = jedis.hget(bookingKey, "roomId");
            String guestName = jedis.hget(bookingKey, "guestName");
            Room room = new Room(Long.parseLong(roomId), "", true);
            return new Booking(bookingId, room, guestName);
        } else {
            return null;
        }
    }
}