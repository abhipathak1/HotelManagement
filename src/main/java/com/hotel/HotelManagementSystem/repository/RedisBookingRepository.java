package com.hotel.HotelManagementSystem.repository;

import com.hotel.HotelManagementSystem.model.Booking;
import com.hotel.HotelManagementSystem.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisBookingRepository extends CrudRepository<Booking,Long> {
}
