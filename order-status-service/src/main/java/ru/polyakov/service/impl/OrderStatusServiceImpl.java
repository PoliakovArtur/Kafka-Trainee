package ru.polyakov.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.polyakov.event.OrderStatusEvent;
import ru.polyakov.service.OrderStatusService;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {

    private final KafkaTemplate<String, OrderStatusEvent> kafkaTemplate;

    @Override
    public void sendOrderStatusEvent(String key, OrderStatusEvent orderStatusEvent) {
        kafkaTemplate.send(new ProducerRecord<>("order-status-topic", key, orderStatusEvent))
                .whenComplete((result, ex) -> {
                    ProducerRecord<String, OrderStatusEvent> record = result.getProducerRecord();
                    if(ex == null) {
                        log.info("Send message: {}", record.value());
                        log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}", record.key(), record.partition(), record.topic(), record.timestamp());
                    } else {
                        log.error("can't send order status to order-status-topic");
                    }
                });
    }
}
