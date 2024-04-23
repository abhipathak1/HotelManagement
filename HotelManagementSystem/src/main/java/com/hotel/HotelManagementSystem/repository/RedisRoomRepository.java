package com.hotel.HotelManagementSystem.repository;

import com.hotel.HotelManagementSystem.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import java.util.*;


@Repository
public class RedisRoomRepository{

    @Autowired
    private Jedis jedis;

    public boolean addRoom(Room room) {
       String roomKey = "room:" + room.getId();
//        if (jedis.exists(roomKey)) {
//            return false;
//        } else {
            jedis.hset(roomKey, "id", String.valueOf(room.getId()));
            jedis.hset(roomKey, "roomNumber", room.getRoomNumber());
            jedis.hset(roomKey, "available", String.valueOf(room.isAvailable()));
            String ROOMS_KEY = "rooms";
            jedis.sadd(ROOMS_KEY, roomKey);
            return true;
//        }
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

        Set<String> roomSet = jedis.smembers("rooms");
         for(String key: roomSet) {
             Map<String,String> roomMap = jedis.hgetAll(key);
             Room room= new Room();
             room.setRoomNumber(roomMap.get("roomNumber"));
             room.setId(Long.parseLong(roomMap.get("id")));
             room.setAvailable(Boolean.parseBoolean(roomMap.get("available")));

             list.add(room);
         }
         return list;
    }
}