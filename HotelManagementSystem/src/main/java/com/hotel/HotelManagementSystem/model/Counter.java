package com.hotel.HotelManagementSystem.model;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("count_add_cancel")
public class Counter {

    private Integer booking_Count;
    private Integer cancel_count;
}
