package ru.polyakov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.polyakov.dto.ResponseMessageDto;
import ru.polyakov.model.Order;
import ru.polyakov.service.OrderService;

import java.util.concurrent.ExecutionException;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ResponseMessageDto> save(@RequestBody Order order) throws ExecutionException, InterruptedException {
        orderService.sendOrderToKafka(order);
        return ResponseEntity.status(CREATED).body(new ResponseMessageDto("Заказ отправлен"));
    }
}
