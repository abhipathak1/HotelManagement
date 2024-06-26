package com.hotel.HotelManagementSystem.repository;

import com.hotel.HotelManagementSystem.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class RoomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Room> getAllRooms() {
        String sql = "SELECT * FROM rooms";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Room.class));
    }


    public boolean addRoom(Room room) {
        String sql = "INSERT INTO rooms (room_number, available) VALUES (?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, room.getRoomNumber(), room.isAvailable());
        return rowsAffected > 0;
    }

    public boolean existsByRoomNumber(String roomNumber) {
        String sql = "SELECT COUNT(*) FROM rooms WHERE room_number = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, roomNumber);
        return count > 0;
    }

    public boolean removeRoom(Long roomId) {
        String sql = "DELETE FROM rooms WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, roomId);
        return rowsAffected > 0;
    }

    public Room getRoomById(Long roomId) {
        String sql = "SELECT * FROM rooms WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{roomId}, BeanPropertyRowMapper.newInstance(Room.class));
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }


    public boolean updateRoom(Room room) {
        String sql = "UPDATE rooms SET room_number = ?, available = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, room.getRoomNumber(), room.isAvailable(), room.getId());
        return rowsAffected > 0;
    }

}
