package ru.polyakov.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.polyakov.model.Order;
import ru.polyakov.service.OrderService;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Override
    public void sendOrderToKafka(Order order) throws ExecutionException, InterruptedException {
        SendResult<String, Order> result = kafkaTemplate.send(
                new ProducerRecord<>("order-topic", UUID.randomUUID().toString(), order)).get();
        ProducerRecord<String, Order> record = result.getProducerRecord();
        log.info("Send message: {}", record.value());
        log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}", record.key(), record.partition(), record.topic(), record.timestamp());
    }
}
