package com.hotel.HotelManagementSystem.service;

import com.hotel.HotelManagementSystem.model.Room;

import java.util.List;

public interface RoomServiceInterface {

    boolean addRoom(Room room);
    boolean removeRoom(Long roomId);

    List<Room> getAllRooms();
}
