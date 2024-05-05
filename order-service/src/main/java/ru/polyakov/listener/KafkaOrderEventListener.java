package ru.polyakov.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.polyakov.listener.event.OrderStatusEvent;

@Slf4j
@Component
public class KafkaOrderEventListener {

    @KafkaListener(topics = "order-status-topic", groupId = "order-statuses")
    public void listen(ConsumerRecord<String, OrderStatusEvent> record) {
        log.info("Received message: {}", record.value());
        log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}", record.key(), record.partition(), record.topic(), record.timestamp());
    }

}
