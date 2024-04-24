package com.hotel.HotelManagementSystem.service;

import com.hotel.HotelManagementSystem.model.Booking;
import com.hotel.HotelManagementSystem.repository.RedisBookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Slf4j
@Service
@ConditionalOnProperty(name ="db_flag", havingValue = "redis")
public class RedisBookingService implements BookingServiceInterface {

    @Autowired
    private Jedis jedis;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private RedisBookingRepository bookingRepository;


    public boolean bookRoom(Booking booking) {
        boolean success = bookingRepository.bookRoom(booking);
        if (success) {
            log.info("Room booked " + booking.getId());
            kafkaProducerService.publishMessage("room-booked", "Room booked:");
            log.info("Topic is Published in Kafka Server " + booking.getRoom().getRoomNumber()) ;
        }
        return success;
    }

    public boolean cancelBooking(Long bookingId) {
        boolean success =  bookingRepository.cancelBooking(bookingId);
        if (success) {
            kafkaProducerService.publishMessage("room-cancelled", "Room cancelled:");
        }
        return success;
    }

    public Booking getBookingDetails(Long bookingId) {
        return bookingRepository.getBookingDetails(bookingId);
    }

}
