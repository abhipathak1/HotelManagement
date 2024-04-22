package com.hotel.HotelManagementSystem.service;
import com.hotel.HotelManagementSystem.model.Room;
import com.hotel.HotelManagementSystem.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

//    public List<Room> getAllRooms() {
//        return roomRepository.getAllRooms();
//    }

    public boolean addRoom(Room room) {
        return roomRepository.addRoom(room);
    }

    public boolean removeRoom(Long roomId) {
        return roomRepository.removeRoom(roomId);
    }
}

