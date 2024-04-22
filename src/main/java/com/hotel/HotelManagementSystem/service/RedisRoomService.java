package com.hotel.HotelManagementSystem.service;

import com.hotel.HotelManagementSystem.model.Room;
import com.hotel.HotelManagementSystem.repository.RedisRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name ="db_flag", havingValue = "redis")
public class RedisRoomService {

    @Autowired
    private RedisRoomRepository redisRoomRepository;

    public boolean addRoom(Room room){
        return redisRoomRepository.addRoom(room);
    }

    public boolean removeRoom(Long roomID){
        return redisRoomRepository.removeRoom(roomID);
    }

}
