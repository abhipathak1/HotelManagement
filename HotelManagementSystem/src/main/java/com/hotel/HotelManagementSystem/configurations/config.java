package com.hotel.HotelManagementSystem.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class config {

    @Bean
    public Jedis jedisConnectionFactory() {
        Jedis jedis = new Jedis("redis://localhost:6379");
        return jedis;
    }
}