package com.hotel.HotelManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private Long id;
    private Room room;
    private String guestName;
}
