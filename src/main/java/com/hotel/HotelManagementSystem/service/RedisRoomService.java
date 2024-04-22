package com.hotel.HotelManagementSystem.service;

import com.hotel.HotelManagementSystem.model.Room;
import com.hotel.HotelManagementSystem.repository.RedisRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "db_flag", havingValue = "mysql")
public class RedisRoomService {
    @Autowired
    private RedisRoomRepository redisRoomRepository;

    public List<Room> getAllRooms() {
        return (List<Room>) redisRoomRepository.findAll();
    }

    public boolean addRoom(Room room) {
        redisRoomRepository.save(room);
        return true;
    }


}
