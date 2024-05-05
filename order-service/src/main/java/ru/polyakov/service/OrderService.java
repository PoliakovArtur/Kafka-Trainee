package ru.polyakov.service;

import ru.polyakov.model.Order;

import java.util.concurrent.ExecutionException;

public interface OrderService {

    void sendOrderToKafka(Order order) throws ExecutionException, InterruptedException;
}
