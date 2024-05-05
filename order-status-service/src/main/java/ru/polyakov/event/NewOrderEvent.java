package ru.polyakov.event;

public record NewOrderEvent(String product, Integer quantity) {
}
