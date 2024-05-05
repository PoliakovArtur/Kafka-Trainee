package ru.polyakov.listener.event;

import java.time.Instant;

public record OrderStatusEvent(String status, Instant creationDate) {
}
