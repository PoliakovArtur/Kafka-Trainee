package ru.polyakov.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.polyakov.event.NewOrderEvent;
import ru.polyakov.event.OrderStatusEvent;
import ru.polyakov.service.OrderStatusService;

import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final OrderStatusService orderStatusService;

    @KafkaListener(topics = "order-topic", groupId = "new-orders")
    public void listenNewOrderEvent(ConsumerRecord<String, NewOrderEvent> record) {
        String key = record.key();
        log.info("Received message: {}", record.value());
        log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}", key, record.value(), record.topic(), record.partition());
        orderStatusService.sendOrderStatusEvent(key, new OrderStatusEvent("CREATED", Instant.now()));
    }

}
