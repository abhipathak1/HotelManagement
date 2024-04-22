package com.hotel.HotelManagementSystem.repository;


import com.hotel.HotelManagementSystem.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class RedisRoomRepository{

    @Autowired
    private Jedis jedis;

    public boolean addRoom(Room room) {
        String roomKey = "room:" + room.getId();
        if (jedis.exists(roomKey)) {
            return false;
        } else {
            jedis.hset(roomKey, "id", String.valueOf(room.getId()));
            jedis.hset(roomKey, "roomNumber", room.getRoomNumber());
            jedis.hset(roomKey, "available", String.valueOf(room.isAvailable()));
            String ROOMS_KEY = "rooms";
            jedis.sadd(ROOMS_KEY, roomKey);
            return true;
        }
    }

    public boolean removeRoom(Long roomId) {
        String roomKey = "room:" + roomId;
        if (jedis.exists(roomKey)) {
            jedis.del(roomKey);
            String ROOMS_KEY = "rooms";
            jedis.srem(ROOMS_KEY, roomKey);
            return true;
        } else {
            return false;
        }
    }


    public List<Room> getAllRooms() {
        List<Room> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : jedis.hgetAll("rooms").entrySet()) {
            Room room = new Room();
            room.setId(Long.parseLong(entry.getKey()));
            Map<String, String> roomDetails = parseRoomDetails(entry.getValue());
            room.setRoomNumber(roomDetails.get("roomNumber"));
            room.setAvailable(Boolean.parseBoolean(roomDetails.get("available")));
            list.add(room);
        }
        return list;
    }

    private Map<String, String> parseRoomDetails(String value) {
        Map<String, String> roomDetails = new HashMap<>();
        String[] pairs = value.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            roomDetails.put(keyValue[0], keyValue[1]);
        }
        return roomDetails;
    }

}