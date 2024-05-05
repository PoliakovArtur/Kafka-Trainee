package ru.polyakov.event;

import java.time.Instant;

public record OrderStatusEvent(String status, Instant creationDate) {
}
