package ru.polyakov.service;

import ru.polyakov.event.OrderStatusEvent;

public interface OrderStatusService {

    void sendOrderStatusEvent(String key, OrderStatusEvent orderStatusEvent);
}
