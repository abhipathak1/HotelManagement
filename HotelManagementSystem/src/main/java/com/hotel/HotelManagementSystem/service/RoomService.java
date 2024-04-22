package com.hotel.HotelManagementSystem.service;
import com.hotel.HotelManagementSystem.model.Room;
//import com.hotel.HotelManagementSystem.repository.RedisRoomRepository;
import com.hotel.HotelManagementSystem.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;



    public List<Room> getAllRooms() {
        return roomRepository.getAllRooms();
    }

    public boolean addRoom(Room room) {
        if (roomRepository.existsByRoomNumber(room.getRoomNumber())) {
            return false;
        }
        return roomRepository.addRoom(room);
    }

    public boolean removeRoom(Long roomId) {
        return roomRepository.removeRoom(roomId);
    }
}

