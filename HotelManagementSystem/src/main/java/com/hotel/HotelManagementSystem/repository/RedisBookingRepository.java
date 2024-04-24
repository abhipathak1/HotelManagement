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

    @Autowired
    private RedisRoomRepository redisRoomRepository;

//    public boolean bookRoom(Booking booking) {
//        String bookingKey = "booking:" + booking.getId();
//        jedis.hset(bookingKey, "roomId", String.valueOf(booking.getRoom().getId()));
//        jedis.hset(bookingKey, "guestName", booking.getGuestName());
//        return true;
//    }

    public boolean bookRoom(Booking booking) {
        Room room = redisRoomRepository.getRoomById(booking.getRoom().getId());
        if (room != null && room.isAvailable()) {
            String bookingKey = "booking:" + booking.getId();
            jedis.hset(bookingKey, "roomId", String.valueOf(booking.getRoom().getId()));
            jedis.hset(bookingKey, "guestName", booking.getGuestName());
            room.setAvailable(false);
            redisRoomRepository.updateRoom(room);
            return true;
        }
        return false;
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

    public void incrementTotalRoomsBooked() {
        jedis.hincrBy("count_add_cancel","booking_Count",1);
    }

    public void incrementTotalRoomsCancelled() {
        jedis.hincrBy("count_add_cancel","cancel_count",1);
    }
}