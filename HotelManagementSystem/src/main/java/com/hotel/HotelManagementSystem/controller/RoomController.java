package com.hotel.HotelManagementSystem.controller;

import com.hotel.HotelManagementSystem.service.BookingService;
import com.hotel.HotelManagementSystem.service.RoomService;
import com.hotel.HotelManagementSystem.service.RoomServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hotel.HotelManagementSystem.model.Booking;
import com.hotel.HotelManagementSystem.model.Room;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private RoomServiceInterface roomService;

    RoomController(RoomServiceInterface roomService){
        this.roomService=roomService;
    }


//    @Autowired
//    private RoomService roomService;

    @GetMapping("/all")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRoom(@RequestBody Room room) {
        boolean success = roomService.addRoom(room);
        if (success) {
            return ResponseEntity.ok("Room added successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to add room");
        }
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<String> removeRoom(@PathVariable Long roomId) {
        boolean success = roomService.removeRoom(roomId);
        if (success) {
            return ResponseEntity.ok("Room removed successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to remove room");
        }
    }
}

