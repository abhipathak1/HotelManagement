package com.hotel.HotelManagementSystem.service;

import com.hotel.HotelManagementSystem.model.Room;

public interface RoomServiceInterface {

    boolean addRoom(Room room);
    boolean removeRoom(Long roomId);

}
