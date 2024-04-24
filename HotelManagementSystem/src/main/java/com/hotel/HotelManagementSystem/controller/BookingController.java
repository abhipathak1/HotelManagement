package com.hotel.HotelManagementSystem.controller;

import com.hotel.HotelManagementSystem.model.Booking;
//import com.hotel.HotelManagementSystem.repository.RedisBookingRepository;
import com.hotel.HotelManagementSystem.service.BookingService;
import com.hotel.HotelManagementSystem.service.BookingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private  BookingServiceInterface bookingService;

    public BookingController(BookingServiceInterface bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping("")
    public ResponseEntity<String> bookRoom(@RequestBody Booking booking) {
        boolean success = bookingService.bookRoom(booking);
        if (success) {
            return ResponseEntity.ok("Room booked successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to book room or room is not available");
        }
    }

    @DeleteMapping("/{bookingId}")
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
