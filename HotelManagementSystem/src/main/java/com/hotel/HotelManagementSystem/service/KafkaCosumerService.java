package com.hotel.HotelManagementSystem.service;

import com.hotel.HotelManagementSystem.repository.RedisBookingRepository;
import com.hotel.HotelManagementSystem.repository.RedisRoomRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Component
@Slf4j
public class KafkaCosumerService {

    @Autowired
    private RedisBookingRepository redisBookingRepository;

    @PostConstruct
    public void start(){
        Thread consumerThread = new Thread(this::consumeMessages);
        consumerThread.start();
    }

    private KafkaConsumer<String,String> consumer;


    public KafkaCosumerService()
    {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "room-consumer-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("room-booked", "room-cancelled"));
    }


    private void consumeMessages() {

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    processMessage(record);
                }
            }
    }

    private void processMessage(ConsumerRecord<String, String> record) {
        String topic = record.topic();
        String message = record.value();
        switch (topic) {
            case "room-booked":
                log.info("helloafldkjhglajhgaoiudsgnvaliraroio");
                    redisBookingRepository.incrementTotalRoomsBooked();
                break;
            case "room-cancelled":
                    redisBookingRepository.incrementTotalRoomsCancelled();
                break;
            default:
                log.warn("Unknown topic: {}", topic);
        }
    }

    @PreDestroy
    private void close()
    {
        consumer.close();
    }
}
