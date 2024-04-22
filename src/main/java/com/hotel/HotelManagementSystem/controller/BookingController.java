package com.hotel.HotelManagementSystem.controller;

import com.hotel.HotelManagementSystem.model.Booking;
import com.hotel.HotelManagementSystem.model.BookingRequest;
//import com.hotel.HotelManagementSystem.repository.RedisBookingRepository;
import com.hotel.HotelManagementSystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<String> bookRoom(@RequestBody Booking booking) {
        boolean success = bookingService.bookRoom(booking);
        if (success) {
            return ResponseEntity.ok("Room booked successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to book room");
        }
    }

    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId) {
        boolean success = bookingService.cancelBooking(bookingId);
        if (success) {
            return ResponseEntity.ok("Booking canceled successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to cancel booking");
        }
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingDetails(@PathVariable Long bookingId) {
        Booking booking = bookingService.getBookingDetails(bookingId);
        if (booking != null) {
            return ResponseEntity.ok(booking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
